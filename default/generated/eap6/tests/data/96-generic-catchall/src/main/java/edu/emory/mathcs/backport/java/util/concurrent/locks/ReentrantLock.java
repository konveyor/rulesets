/*
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/licenses/publicdomain
 */

package edu.emory.mathcs.backport.java.util.concurrent.locks;

import java.util.*;
import edu.emory.mathcs.backport.java.util.concurrent.*;
import edu.emory.mathcs.backport.java.util.concurrent.helpers.*;

/**
 * A reentrant mutual exclusion {@link Lock} with the same basic
 * behavior and semantics as the implicit monitor lock accessed using
 * <tt>synchronized</tt> methods and statements, but with extended
 * capabilities.
 *
 * <p> A <tt>ReentrantLock</tt> is <em>owned</em> by the thread last
 * successfully locking, but not yet unlocking it. A thread invoking
 * <tt>lock</tt> will return, successfully acquiring the lock, when
 * the lock is not owned by another thread. The method will return
 * immediately if the current thread already owns the lock. This can
 * be checked using methods {@link #isHeldByCurrentThread}, and {@link
 * #getHoldCount}.
 *
 * <p> The constructor for this class accepts an optional
 * <em>fairness</em> parameter.  When set <tt>true</tt>, under
 * contention, locks favor granting access to the longest-waiting
 * thread.  Otherwise this lock does not guarantee any particular
 * access order.  Programs using fair locks accessed by many threads
 * may display lower overall throughput (i.e., are slower; often much
 * slower) than those using the default setting, but have smaller
 * variances in times to obtain locks and guarantee lack of
 * starvation. Note however, that fairness of locks does not guarantee
 * fairness of thread scheduling. Thus, one of many threads using a
 * fair lock may obtain it multiple times in succession while other
 * active threads are not progressing and not currently holding the
 * lock.
 * Also note that the untimed {@link #tryLock() tryLock} method does not
 * honor the fairness setting. It will succeed if the lock
 * is available even if other threads are waiting.
 *
 * <p> It is recommended practice to <em>always</em> immediately
 * follow a call to <tt>lock</tt> with a <tt>try</tt> block, most
 * typically in a before/after construction such as:
 *
 * <pre>
 * class X {
 *   private final ReentrantLock lock = new ReentrantLock();
 *   // ...
 *
 *   public void m() {
 *     lock.lock();  // block until condition holds
 *     try {
 *       // ... method body
 *     } finally {
 *       lock.unlock()
 *     }
 *   }
 * }
 * </pre>
 *
 * <p>In addition to implementing the {@link Lock} interface, this
 * class defines methods <tt>isLocked</tt> and
 * <tt>getLockQueueLength</tt>, as well as some associated
 * <tt>protected</tt> access methods that may be useful for
 * instrumentation and monitoring.
 *
 * <p> Serialization of this class behaves in the same way as built-in
 * locks: a deserialized lock is in the unlocked state, regardless of
 * its state when serialized.
 *
 * <p> This lock supports a maximum of 2147483648 recursive locks by
 * the same thread.
 *
 * @since 1.5
 * @author Doug Lea
 *
 */
public class ReentrantLock implements Lock, java.io.Serializable,
                                      CondVar.LockInfo {
    private static final long serialVersionUID = 7373984872572414699L;

    private final Impl impl;

    static abstract class Impl {
        protected Thread owner_ = null;
        protected int holds_ = 0;

        protected Impl() {}

        public abstract void lockInterruptibly() throws InterruptedException;

        public boolean tryLock() {
            Thread caller = Thread.currentThread();
            synchronized (this) {
                if (caller == owner_) {
                    ++holds_;
                    return true;
                }
                else if (owner_ == null) {
                    owner_ = caller;
                    holds_ = 1;
                    return true;
                }
            }
            return false;
        }

        public abstract boolean tryLock(long nanos) throws InterruptedException;

        public abstract void unlock();

        public synchronized int getHoldCount() {
            return isHeldByCurrentThread() ? holds_ : 0;
        }

        public synchronized boolean isHeldByCurrentThread() {
            return Thread.currentThread() == owner_;
        }

        public synchronized boolean isLocked() {
            return owner_ != null;
        }

        public abstract boolean isFair();

        protected synchronized Thread getOwner() {
            return owner_;
        }

        public boolean hasQueuedThreads() {
            throw new UnsupportedOperationException("Use FAIR version");
        }

        public int getQueueLength() {
            throw new UnsupportedOperationException("Use FAIR version");
        }

        public Collection getQueuedThreads() {
            throw new UnsupportedOperationException("Use FAIR version");
        }

        public boolean isQueued(Thread thread) {
            throw new UnsupportedOperationException("Use FAIR version");
        }
    }

    static class NonfairImpl extends Impl implements java.io.Serializable {

        NonfairImpl() {}

        public void lockInterruptibly() throws InterruptedException {
            if (Thread.interrupted()) throw new InterruptedException();
            Thread caller = Thread.currentThread();
            synchronized (this) {
                if (caller == owner_)
                    ++holds_;
                else {
                    try {
                        while (owner_ != null) wait();
                        owner_ = caller;
                        holds_ = 1;
                    }
                    catch (InterruptedException ex) {
                        notify();
                        throw ex;
                    }
                }
            }
        }

        public boolean tryLock(long nanos) throws InterruptedException {
            if (Thread.interrupted()) throw new InterruptedException();
            Thread caller = Thread.currentThread();

            synchronized (this) {
                if (caller == owner_) {
                    ++holds_;
                    return true;
                }
                else if (owner_ == null) {
                    owner_ = caller;
                    holds_ = 1;
                    return true;
                }
                else if (nanos <= 0)
                    return false;
                else {
                    long deadline = Utils.nanoTime() + nanos;
                    try {
                        for (; ; ) {
                            TimeUnit.NANOSECONDS.timedWait(this, nanos);
                            if (caller == owner_) {
                                ++holds_;
                                return true;
                            }
                            else if (owner_ == null) {
                                owner_ = caller;
                                holds_ = 1;
                                return true;
                            }
                            else {
                                nanos = deadline - Utils.nanoTime();
                                if (nanos <= 0)
                                    return false;
                            }
                        }
                    }
                    catch (InterruptedException ex) {
                        notify();
                        throw ex;
                    }
                }
            }
        }

        public synchronized void unlock() {
            if (Thread.currentThread() != owner_)
                throw new IllegalMonitorStateException("Not owner");

            if (--holds_ == 0) {
                owner_ = null;
                notify();
            }
        }

        public final boolean isFair() {
            return false;
        }
    }

    static class FairImpl extends Impl implements WaitQueue.QueuedSync,
                                                  java.io.Serializable  {

        private final WaitQueue wq_ = new FIFOWaitQueue();

        public synchronized boolean recheck(WaitQueue.WaitNode node) {
            Thread caller = Thread.currentThread();
            if (caller == owner_) {
                ++holds_;
                return true;
            }
            else if (owner_ == null) {
                owner_ = caller;
                holds_ = 1;
                return true;
            }
            wq_.insert(node);
            return false;
        }

        public void lockInterruptibly() throws InterruptedException {
            if (Thread.interrupted()) throw new InterruptedException();
            Thread caller = Thread.currentThread();
            synchronized (this) {
                if (caller == owner_) {
                    ++holds_;
                    return;
                }
                else if (owner_ == null) {
                    owner_ = caller;
                    holds_ = 1;
                    return;
                }
            }
            WaitQueue.WaitNode n = new WaitQueue.WaitNode();
            n.doWait(this);

            synchronized (this) {
                assert owner_ == null;
                owner_ = caller;
                holds_ = 1;
            }
        }

        public boolean tryLock(long nanos) throws InterruptedException {
            if (Thread.interrupted()) throw new InterruptedException();
            Thread caller = Thread.currentThread();
            synchronized (this) {
                if (caller == owner_) {
                    ++holds_;
                    return true;
                }
                else if (owner_ == null) {
                    owner_ = caller;
                    holds_ = 1;
                    return true;
                }
            }
            WaitQueue.WaitNode n = new WaitQueue.WaitNode();
            if (n.doTimedWait(this, nanos)) {
                synchronized (this) {
                    assert owner_ == null;
                    owner_ = caller;
                    holds_ = 1;
                }
                return true;
            }
            return false;
        }

        public void unlock() {
            boolean signal = false;
            synchronized (this) {
                if (Thread.currentThread() != owner_)
                    throw new IllegalMonitorStateException("Not owner");

                if (--holds_ == 0) {
                    owner_ = null;
                    signal = true;
                }
            }
            if (signal) {
                // signal exactly one waiting thread at the head of the queue
                while (true) {
                    WaitQueue.WaitNode n = wq_.extract();
                    if (n == null) return;
                    if (n.signal()) return;
                }
            }
        }

        public final boolean isFair() {
            return true;
        }

        public synchronized boolean hasQueuedThreads() {
            return wq_.hasNodes();
        }

        public synchronized int getQueueLength() {
            return wq_.getLength();
        }

        public synchronized Collection getQueuedThreads() {
            return wq_.getWaitingThreads();
        }

        public synchronized boolean isQueued(Thread thread) {
            return wq_.isWaiting(thread);
        }
    }

    /**
     * Creates an instance of <tt>ReentrantLock</tt>.
     * This is equivalent to using <tt>ReentrantLock(false)</tt>.
     */
    public ReentrantLock() {
        impl = new NonfairImpl();
    }

    /**
     * Creates an instance of <tt>ReentrantLock</tt> with the
     * given fairness policy.
     * @param fair true if this lock will be fair; else false
     */
    public ReentrantLock(boolean fair) {
        impl = (fair)? (Impl)new FairImpl() : new NonfairImpl();
    }


    /**
     * Acquires the lock.
     *
     * <p>Acquires the lock if it is not held by another thread and returns
     * immediately, setting the lock hold count to one.
     *
     * <p>If the current thread
     * already holds the lock then the hold count is incremented by one and
     * the method returns immediately.
     *
     * <p>If the lock is held by another thread then the
     * current thread becomes disabled for thread scheduling
     * purposes and lies dormant until the lock has been acquired,
     * at which time the lock hold count is set to one.
     */
    public void lock() {
        boolean wasInterrupted = false;
        while (true) {
            try {
                impl.lockInterruptibly();
                if (wasInterrupted) {
                    Thread.currentThread().interrupt();
                }
                return;
            }
            catch (InterruptedException e) {
                wasInterrupted = true;
            }
        }
    }

    /**
     * Acquires the lock unless the current thread is
     * {@link Thread#interrupt interrupted}.
     *
     * <p>Acquires the lock if it is not held by another thread and returns
     * immediately, setting the lock hold count to one.
     *
     * <p>If the current thread already holds this lock then the hold count
     * is incremented by one and the method returns immediately.
     *
     * <p>If the lock is held by another thread then the
     * current thread becomes disabled for thread scheduling
     * purposes and lies dormant until one of two things happens:
     *
     * <ul>
     *
     * <li>The lock is acquired by the current thread; or
     *
     * <li>Some other thread {@link Thread#interrupt interrupts} the current
     * thread.
     *
     * </ul>
     *
     * <p>If the lock is acquired by the current thread then the lock hold
     * count is set to one.
     *
     * <p>If the current thread:
     *
     * <ul>
     *
     * <li>has its interrupted status set on entry to this method; or
     *
     * <li>is {@link Thread#interrupt interrupted} while acquiring
     * the lock,
     *
     * </ul>
     *
     * then {@link InterruptedException} is thrown and the current thread's
     * interrupted status is cleared.
     *
     * <p>In this implementation, as this method is an explicit interruption
     * point, preference is
     * given to responding to the interrupt over normal or reentrant
     * acquisition of the lock.
     *
     * @throws InterruptedException if the current thread is interrupted
     */
    public void lockInterruptibly() throws InterruptedException {
        impl.lockInterruptibly();
    }

    /**
     * Acquires the lock only if it is not held by another thread at the time
     * of invocation.
     *
     * <p>Acquires the lock if it is not held by another thread and
     * returns immediately with the value <tt>true</tt>, setting the
     * lock hold count to one. Even when this lock has been set to use a
     * fair ordering policy, a call to <tt>tryLock()</tt> <em>will</em>
     * immediately acquire the lock if it is available, whether or not
     * other threads are currently waiting for the lock.
     * This &quot;barging&quot; behavior can be useful in certain
     * circumstances, even though it breaks fairness. If you want to honor
     * the fairness setting for this lock, then use
     * {@link #tryLock(long, TimeUnit) tryLock(0, TimeUnit.SECONDS) }
     * which is almost equivalent (it also detects interruption).
     *
     * <p> If the current thread
     * already holds this lock then the hold count is incremented by one and
     * the method returns <tt>true</tt>.
     *
     * <p>If the lock is held by another thread then this method will return
     * immediately with the value <tt>false</tt>.
     *
     * @return <tt>true</tt> if the lock was free and was acquired by the
     * current thread, or the lock was already held by the current thread; and
     * <tt>false</tt> otherwise.
     */
    public boolean tryLock() {
        return impl.tryLock();
    }

    /**
     * Acquires the lock if it is not held by another thread within the given
     * waiting time and the current thread has not been
     * {@link Thread#interrupt interrupted}.
     *
     * <p>Acquires the lock if it is not held by another thread and returns
     * immediately with the value <tt>true</tt>, setting the lock hold count
     * to one. If this lock has been set to use a fair ordering policy then
     * an available lock <em>will not</em> be acquired if any other threads
     * are waiting for the lock. This is in contrast to the {@link #tryLock()}
     * method. If you want a timed <tt>tryLock</tt> that does permit barging on
     * a fair lock then combine the timed and un-timed forms together:
     *
     * <pre>if (lock.tryLock() || lock.tryLock(timeout, unit) ) { ... }
     * </pre>
     *
     * <p>If the current thread
     * already holds this lock then the hold count is incremented by one and
     * the method returns <tt>true</tt>.
     *
     * <p>If the lock is held by another thread then the
     * current thread becomes disabled for thread scheduling
     * purposes and lies dormant until one of three things happens:
     *
     * <ul>
     *
     * <li>The lock is acquired by the current thread; or
     *
     * <li>Some other thread {@link Thread#interrupt interrupts} the current
     * thread; or
     *
     * <li>The specified waiting time elapses
     *
     * </ul>
     *
     * <p>If the lock is acquired then the value <tt>true</tt> is returned and
     * the lock hold count is set to one.
     *
     * <p>If the current thread:
     *
     * <ul>
     *
     * <li>has its interrupted status set on entry to this method; or
     *
     * <li>is {@link Thread#interrupt interrupted} while acquiring
     * the lock,
     *
     * </ul>
     * then {@link InterruptedException} is thrown and the current thread's
     * interrupted status is cleared.
     *
     * <p>If the specified waiting time elapses then the value <tt>false</tt>
     * is returned.
     * If the time is
     * less than or equal to zero, the method will not wait at all.
     *
     * <p>In this implementation, as this method is an explicit interruption
     * point, preference is
     * given to responding to the interrupt over normal or reentrant
     * acquisition of the lock, and over reporting the elapse of the waiting
     * time.
     *
     * @param timeout the time to wait for the lock
     * @param unit the time unit of the timeout argument
     *
     * @return <tt>true</tt> if the lock was free and was acquired by the
     * current thread, or the lock was already held by the current thread; and
     * <tt>false</tt> if the waiting time elapsed before the lock could be
     * acquired.
     *
     * @throws InterruptedException if the current thread is interrupted
     * @throws NullPointerException if unit is null
     *
     */
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return impl.tryLock(unit.toNanos(timeout));
    }

    /**
     * Attempts to release this lock.
     *
     * <p>If the current thread is the
     * holder of this lock then the hold count is decremented. If the
     * hold count is now zero then the lock is released.  If the
     * current thread is not the holder of this lock then {@link
     * IllegalMonitorStateException} is thrown.
     * @throws IllegalMonitorStateException if the current thread does not
     * hold this lock.
     */
    public void unlock() {
        impl.unlock();
    }

    /**
     * Returns a {@link Condition} instance for use with this
     * {@link Lock} instance.
     *
     * <p>The returned {@link Condition} instance supports the same
     * usages as do the {@link Object} monitor methods ({@link
     * Object#wait() wait}, {@link Object#notify notify}, and {@link
     * Object#notifyAll notifyAll}) when used with the built-in
     * monitor lock.
     *
     * <ul>
     *
     * <li>If this lock is not held when any of the {@link Condition}
     * {@link Condition#await() waiting} or {@link Condition#signal
     * signalling} methods are called, then an {@link
     * IllegalMonitorStateException} is thrown.
     *
     * <li>When the condition {@link Condition#await() waiting}
     * methods are called the lock is released and, before they
     * return, the lock is reacquired and the lock hold count restored
     * to what it was when the method was called.
     *
     * <li>If a thread is {@link Thread#interrupt interrupted} while
     * waiting then the wait will terminate, an {@link
     * InterruptedException} will be thrown, and the thread's
     * interrupted status will be cleared.
     *
     * <li> Waiting threads are signalled in FIFO order
     *
     * <li>The ordering of lock reacquisition for threads returning
     * from waiting methods is the same as for threads initially
     * acquiring the lock, which is in the default case not specified,
     * but for <em>fair</em> locks favors those threads that have been
     * waiting the longest.
     *
     * </ul>
     *
     * @return the Condition object
     */
    public Condition newCondition() {
        return new CondVar(this);
    }

    /**
     * Queries the number of holds on this lock by the current thread.
     *
     * <p>A thread has a hold on a lock for each lock action that is not
     * matched by an unlock action.
     *
     * <p>The hold count information is typically only used for testing and
     * debugging purposes. For example, if a certain section of code should
     * not be entered with the lock already held then we can assert that
     * fact:
     *
     * <pre>
     * class X {
     *   ReentrantLock lock = new ReentrantLock();
     *   // ...
     *   public void m() {
     *     assert lock.getHoldCount() == 0;
     *     lock.lock();
     *     try {
     *       // ... method body
     *     } finally {
     *       lock.unlock();
     *     }
     *   }
     * }
     * </pre>
     *
     * @return the number of holds on this lock by the current thread,
     * or zero if this lock is not held by the current thread.
     */
    public int getHoldCount() {
        return impl.getHoldCount();
    }

    /**
     * Queries if this lock is held by the current thread.
     *
     * <p>Analogous to the {@link Thread#holdsLock} method for built-in
     * monitor locks, this method is typically used for debugging and
     * testing. For example, a method that should only be called while
     * a lock is held can assert that this is the case:
     *
     * <pre>
     * class X {
     *   ReentrantLock lock = new ReentrantLock();
     *   // ...
     *
     *   public void m() {
     *       assert lock.isHeldByCurrentThread();
     *       // ... method body
     *   }
     * }
     * </pre>
     *
     * <p>It can also be used to ensure that a reentrant lock is used
     * in a non-reentrant manner, for example:
     *
     * <pre>
     * class X {
     *   ReentrantLock lock = new ReentrantLock();
     *   // ...
     *
     *   public void m() {
     *       assert !lock.isHeldByCurrentThread();
     *       lock.lock();
     *       try {
     *           // ... method body
     *       } finally {
     *           lock.unlock();
     *       }
     *   }
     * }
     * </pre>
     * @return <tt>true</tt> if current thread holds this lock and
     * <tt>false</tt> otherwise.
     */
    public boolean isHeldByCurrentThread() {
        return impl.isHeldByCurrentThread();
    }

    /**
     * Queries if this lock is held by any thread. This method is
     * designed for use in monitoring of the system state,
     * not for synchronization control.
     * @return <tt>true</tt> if any thread holds this lock and
     * <tt>false</tt> otherwise.
     */
    public boolean isLocked() {
        return impl.isLocked();
    }

    /**
     * Returns true if this lock has fairness set true.
     * @return true if this lock has fairness set true.
     */
    public final boolean isFair() {
        return impl.isFair();
    }

    /**
     * Returns the thread that currently owns this lock, or
     * <tt>null</tt> if not owned. Note that the owner may be
     * momentarily <tt>null</tt> even if there are threads trying to
     * acquire the lock but have not yet done so.  This method is
     * designed to facilitate construction of subclasses that provide
     * more extensive lock monitoring facilities.
     * @return the owner, or <tt>null</tt> if not owned.
     */
    protected Thread getOwner() {
        return impl.getOwner();
    }

    /**
     * Queries whether any threads are waiting to acquire this lock. Note that
     * because cancellations may occur at any time, a <tt>true</tt>
     * return does not guarantee that any other thread will ever
     * acquire this lock.  This method is designed primarily for use in
     * monitoring of the system state.
     *
     * @return true if there may be other threads waiting to acquire
     * the lock.
     */
    public final boolean hasQueuedThreads() {
        return impl.hasQueuedThreads();
    }


    /**
     * Queries whether the given thread is waiting to acquire this
     * lock. Note that because cancellations may occur at any time, a
     * <tt>true</tt> return does not guarantee that this thread
     * will ever acquire this lock.  This method is designed primarily for use
     * in monitoring of the system state.
     *
     * @param thread the thread
     * @return true if the given thread is queued waiting for this lock.
     * @throws NullPointerException if thread is null
     */
    public final boolean hasQueuedThread(Thread thread) {
        return impl.isQueued(thread);
    }


    /**
     * Returns an estimate of the number of threads waiting to
     * acquire this lock.  The value is only an estimate because the number of
     * threads may change dynamically while this method traverses
     * internal data structures.  This method is designed for use in
     * monitoring of the system state, not for synchronization
     * control.
     * @return the estimated number of threads waiting for this lock
     */
    public final int getQueueLength() {
        return impl.getQueueLength();
    }

    /**
     * Returns a collection containing threads that may be waiting to
     * acquire this lock.  Because the actual set of threads may change
     * dynamically while constructing this result, the returned
     * collection is only a best-effort estimate.  The elements of the
     * returned collection are in no particular order.  This method is
     * designed to facilitate construction of subclasses that provide
     * more extensive monitoring facilities.
     * @return the collection of threads
     */
    protected Collection getQueuedThreads() {
        return impl.getQueuedThreads();
    }

//    /**
//     * Queries whether any threads are waiting on the given condition
//     * associated with this lock. Note that because timeouts and
//     * interrupts may occur at any time, a <tt>true</tt> return does
//     * not guarantee that a future <tt>signal</tt> will awaken any
//     * threads.  This method is designed primarily for use in
//     * monitoring of the system state.
//     * @param condition the condition
//     * @return <tt>true</tt> if there are any waiting threads.
//     * @throws IllegalMonitorStateException if this lock
//     * is not held
//     * @throws IllegalArgumentException if the given condition is
//     * not associated with this lock
//     * @throws NullPointerException if condition null
//     */
//    public boolean hasWaiters(Condition condition) {
//        if (condition == null)
//            throw new NullPointerException();
//        if (!(condition instanceof AbstractQueuedSynchronizer.ConditionObject))
//            throw new IllegalArgumentException("not owner");
//        return sync.hasWaiters((AbstractQueuedSynchronizer.ConditionObject)condition);
//    }

//    /**
//     * Returns an estimate of the number of threads waiting on the
//     * given condition associated with this lock. Note that because
//     * timeouts and interrupts may occur at any time, the estimate
//     * serves only as an upper bound on the actual number of waiters.
//     * This method is designed for use in monitoring of the system
//     * state, not for synchronization control.
//     * @param condition the condition
//     * @return the estimated number of waiting threads.
//     * @throws IllegalMonitorStateException if this lock
//     * is not held
//     * @throws IllegalArgumentException if the given condition is
//     * not associated with this lock
//     * @throws NullPointerException if condition null
//     */
//    public int getWaitQueueLength(Condition condition) {
//        if (condition == null)
//            throw new NullPointerException();
//        if (!(condition instanceof AbstractQueuedSynchronizer.ConditionObject))
//            throw new IllegalArgumentException("not owner");
//        return sync.getWaitQueueLength((AbstractQueuedSynchronizer.ConditionObject)condition);
//    }

//    /**
//     * Returns a collection containing those threads that may be
//     * waiting on the given condition associated with this lock.
//     * Because the actual set of threads may change dynamically while
//     * constructing this result, the returned collection is only a
//     * best-effort estimate. The elements of the returned collection
//     * are in no particular order.  This method is designed to
//     * facilitate construction of subclasses that provide more
//     * extensive condition monitoring facilities.
//     * @param condition the condition
//     * @return the collection of threads
//     * @throws IllegalMonitorStateException if this lock
//     * is not held
//     * @throws IllegalArgumentException if the given condition is
//     * not associated with this lock
//     * @throws NullPointerException if condition null
//     */
//    protected Collection getWaitingThreads(Condition condition) {
//        if (condition == null)
//            throw new NullPointerException();
//        if (!(condition instanceof AbstractQueuedSynchronizer.ConditionObject))
//            throw new IllegalArgumentException("not owner");
//        return sync.getWaitingThreads((AbstractQueuedSynchronizer.ConditionObject)condition);
//    }

    /**
     * Returns a string identifying this lock, as well as its lock
     * state.  The state, in brackets, includes either the String
     * &quot;Unlocked&quot; or the String &quot;Locked by&quot;
     * followed by the {@link Thread#getName} of the owning thread.
     * @return a string identifying this lock, as well as its lock state.
     */
    public String toString() {
        Thread owner = getOwner();
        return super.toString() + ((owner == null) ?
                                   "[Unlocked]" :
                                   "[Locked by thread " + owner.getName() + "]");
    }
}
