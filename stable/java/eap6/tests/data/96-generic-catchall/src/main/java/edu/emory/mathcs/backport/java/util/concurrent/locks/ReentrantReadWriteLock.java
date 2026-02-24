/*
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/licenses/publicdomain
 */

package edu.emory.mathcs.backport.java.util.concurrent.locks;

import edu.emory.mathcs.backport.java.util.concurrent.*;
import java.util.*;
import edu.emory.mathcs.backport.java.util.concurrent.helpers.*;

/**
 * An implementation of {@link ReadWriteLock} supporting similar
 * semantics to {@link ReentrantLock}.
 * <p>This class has the following properties:
 *
 * <ul>
 * <li><b>Acquisition order</b>
 *
 * <p>The order of entry to the
 * lock need not be in arrival order. If readers are
 * active and a writer enters the lock then no subsequent readers will
 * be granted the read lock until after that writer has acquired and
 * released the write lock.
 *
 * DEPARTURE FROM java.util.concurrent: this implementation is implemented
 * as writer-preferring and thus its acquisition order may be different
 * than in java.util.concurrent.
 *
 * <li><b>Reentrancy</b>
 * <p>This lock allows both readers and writers to reacquire read or
 * write locks in the style of a {@link ReentrantLock}. Readers are not
 * allowed until all write locks held by the writing thread have been
 * released.
 * <p>Additionally, a writer can acquire the read lock - but not vice-versa.
 * Among other applications, reentrancy can be useful when
 * write locks are held during calls or callbacks to methods that
 * perform reads under read locks.
 * If a reader tries to acquire the write lock it will never succeed.
 *
 * <li><b>Lock downgrading</b>
 * <p>Reentrancy also allows downgrading from the write lock to a read lock,
 * by acquiring the write lock, then the read lock and then releasing the
 * write lock. However, upgrading from a read lock to the write lock is
 * <b>not</b> possible.
 *
 * <li><b>Interruption of lock acquisition</b>
 * <p>The read lock and write lock both support interruption during lock
 * acquisition.
 *
 * <li><b>{@link Condition} support</b>
 * <p>The write lock provides a {@link Condition} implementation that
 * behaves in the same way, with respect to the write lock, as the
 * {@link Condition} implementation provided by
 * {@link ReentrantLock#newCondition} does for {@link ReentrantLock}.
 * This {@link Condition} can, of course, only be used with the write lock.
 * <p>The read lock does not support a {@link Condition} and
 * <tt>readLock().newCondition()</tt> throws
 * <tt>UnsupportedOperationException</tt>.
 *
 * <li><b>Instrumentation</b>
 * <P> This class supports methods to determine whether locks
 * are held or contended. These methods are designed for monitoring
 * system state, not for synchronization control.
 * </ul>
 *
 * <p> Serialization of this class behaves in the same way as built-in
 * locks: a deserialized lock is in the unlocked state, regardless of
 * its state when serialized.
 *
 * <p><b>Sample usages</b>. Here is a code sketch showing how to exploit
 * reentrancy to perform lock downgrading after updating a cache (exception
 * handling is elided for simplicity):
 * <pre>
 * class CachedData {
 *   Object data;
 *   volatile boolean cacheValid;
 *   ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
 *
 *   void processCachedData() {
 *     rwl.readLock().lock();
 *     if (!cacheValid) {
 *        // upgrade lock manually
 *        rwl.readLock().unlock();   // must unlock first to obtain writelock
 *        rwl.writeLock().lock();
 *        if (!cacheValid) { // recheck
 *          data = ...
 *          cacheValid = true;
 *        }
 *        // downgrade lock
 *        rwl.readLock().lock();  // reacquire read without giving up write lock
 *        rwl.writeLock().unlock(); // unlock write, still hold read
 *     }
 *
 *     use(data);
 *     rwl.readLock().unlock();
 *   }
 * }
 * </pre>
 *
 * ReentrantReadWriteLocks can be used to improve concurrency in some
 * uses of some kinds of Collections. This is typically worthwhile
 * only when the collections are expected to be large, accessed by
 * more reader threads than writer threads, and entail operations with
 * overhead that outweighs synchronization overhead. For example, here
 * is a class using a TreeMap that is expected to be large and
 * concurrently accessed.
 *
 * <pre>
 * class RWDictionary {
 *    private final Map&lt;String, Data&gt;  m = new TreeMap&lt;String, Data&gt;();
 *    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
 *    private final Lock r = rwl.readLock();
 *    private final Lock w = rwl.writeLock();
 *
 *    public Data get(String key) {
 *        r.lock(); try { return m.get(key); } finally { r.unlock(); }
 *    }
 *    public String[] allKeys() {
 *        r.lock(); try { return m.keySet().toArray(); } finally { r.unlock(); }
 *    }
 *    public Data put(String key, Data value) {
 *        w.lock(); try { return m.put(key, value); } finally { w.unlock(); }
 *    }
 *    public void clear() {
 *        w.lock(); try { m.clear(); } finally { w.unlock(); }
 *    }
 * }
 * </pre>
 *
 *
 * <h3>Implementation Notes</h3>
 *
 * <p>A reentrant write lock intrinsically defines an owner and can
 * only be released by the thread that acquired it.  In contrast, in
 * this implementation, the read lock has no concept of ownership, and
 * there is no requirement that the thread releasing a read lock is
 * the same as the one that acquired it.  However, this property is
 * not guaranteed to hold in future implementations of this class.
 *
 * <p> This lock supports a maximum of 65536 recursive write locks
 * and 65536 read locks. Attempts to exceed these limits result in
 * {@link Error} throws from locking methods.
 *
 * @since 1.5
 * @author Doug Lea
 *
 */
public class ReentrantReadWriteLock implements ReadWriteLock, java.io.Serializable  {
    private static final long serialVersionUID = -6992448646407690164L;

    int activeReaders_ = 0;
    Thread activeWriter_ = null;
    int waitingReaders_ = 0;
    int waitingWriters_ = 0;

    final ReaderLock readerLock_ = new ReaderLock();
    final WriterLock writerLock_ = new WriterLock();

    /** Number of acquires on write lock by activeWriter_ thread **/
    int writeHolds_ = 0;

    /** Number of acquires on read lock by any reader thread **/
    HashMap readers_ = new HashMap();

    /** cache/reuse the special Integer value one to speed up readlocks **/
    static final Integer IONE = new Integer(1);

    /**
     * Creates a new <tt>ReentrantReadWriteLock</tt> with
     * default ordering properties.
     */
    public ReentrantReadWriteLock() {}

    public Lock writeLock() { return writerLock_; }
    public Lock readLock()  { return readerLock_; }

    /*
       Each of these variants is needed to maintain atomicity
       of wait counts during wait loops. They could be
       made faster by manually inlining each other. We hope that
       compilers do this for us though.
    */

    synchronized boolean startReadFromNewReader() {
        boolean pass = startRead();
        if (!pass) ++waitingReaders_;
        return pass;
    }

    synchronized boolean startWriteFromNewWriter() {
        boolean pass = startWrite();
        if (!pass) ++waitingWriters_;
        return pass;
    }

    synchronized boolean startReadFromWaitingReader() {
        boolean pass = startRead();
        if (pass) --waitingReaders_;
        return pass;
    }

    synchronized boolean startWriteFromWaitingWriter() {
        boolean pass = startWrite();
        if (pass) --waitingWriters_;
        return pass;
    }

    /*
      A bunch of small synchronized methods are needed
      to allow communication from the Lock objects
      back to this object, that serves as controller
    */


    synchronized void cancelledWaitingReader() { --waitingReaders_; }
    synchronized void cancelledWaitingWriter() { --waitingWriters_; }

    boolean allowReader() {
      return (activeWriter_ == null && waitingWriters_ == 0) ||
        activeWriter_ == Thread.currentThread();
    }

    synchronized boolean startRead() {
        Thread t = Thread.currentThread();
        Object c = readers_.get(t);
        if (c != null) { // already held -- just increment hold count
            readers_.put(t, new Integer( ( (Integer) (c)).intValue() + 1));
            ++activeReaders_;
            return true;
        }
        else if (allowReader()) {
            readers_.put(t, IONE);
            ++activeReaders_;
            return true;
        }
        else
            return false;
    }

    synchronized boolean startWrite() {
        if (activeWriter_ == Thread.currentThread()) { // already held; re-acquire
            ++writeHolds_;
            return true;
        }
        else if (writeHolds_ == 0) {
            if (activeReaders_ == 0 ||
                (readers_.size() == 1 &&
                 readers_.get(Thread.currentThread()) != null)) {
                activeWriter_ = Thread.currentThread();
                writeHolds_ = 1;
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    synchronized Signaller endRead() {
        Thread t = Thread.currentThread();
        Object c = readers_.get(t);
        if (c == null)
            throw new IllegalThreadStateException();
        --activeReaders_;
        if (c != IONE) { // more than one hold; decrement count
            int h = ( (Integer) (c)).intValue() - 1;
            Integer ih = (h == 1) ? IONE : new Integer(h);
            readers_.put(t, ih);
            return null;
        }
        else {
            readers_.remove(t);

            if (writeHolds_ > 0) // a write lock is still held by current thread
                return null;
            else if (activeReaders_ == 0 && waitingWriters_ > 0)
                return writerLock_;
            else
                return null;
        }
    }

    synchronized Signaller endWrite() {
        if (activeWriter_ != Thread.currentThread()) {
            throw new IllegalMonitorStateException();
        }
        --writeHolds_;
        if (writeHolds_ > 0) // still being held
            return null;
        else {
            activeWriter_ = null;
            if (waitingReaders_ > 0 && allowReader())
                return readerLock_;
            else if (waitingWriters_ > 0)
                return writerLock_;
            else
                return null;
        }
    }

    /**
     * Reader and Writer requests are maintained in two different
     * wait sets, by two different objects. These objects do not
     * know whether the wait sets need notification since they
     * don't know preference rules. So, each supports a
     * method that can be selected by main controlling object
     * to perform the notifications.  This base class simplifies mechanics.
     */

    static interface Signaller { // base for ReaderLock and WriterLock
        void signalWaiters();
    }

    class ReaderLock implements Signaller, Lock, java.io.Serializable {

        /**
         * Acquires the read lock.
         *
         * <p>Acquires the read lock if the write lock is not held by
         * another thread and returns immediately.
         *
         * <p>If the write lock is held by another thread then
         * the current thread becomes disabled for thread scheduling
         * purposes and lies dormant until the read lock has been acquired.
         */
        public void lock() {
            boolean wasInterrupted = false;
            while (true) {
                try {
                    lockInterruptibly();
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
         * Acquires the read lock unless the current thread is
         * {@link Thread#interrupt interrupted}.
         *
         * <p>Acquires the read lock if the write lock is not held
         * by another thread and returns immediately.
         *
         * <p>If the write lock is held by another thread then the
         * current thread becomes disabled for thread scheduling
         * purposes and lies dormant until one of two things happens:
         *
         * <ul>
         *
         * <li>The read lock is acquired by the current thread; or
         *
         * <li>Some other thread {@link Thread#interrupt interrupts}
         * the current thread.
         *
         * </ul>
         *
         * <p>If the current thread:
         *
         * <ul>
         *
         * <li>has its interrupted status set on entry to this method; or
         *
         * <li>is {@link Thread#interrupt interrupted} while acquiring
         * the read lock,
         *
         * </ul>
         *
         * then {@link InterruptedException} is thrown and the current
         * thread's interrupted status is cleared.
         *
         * <p>In this implementation, as this method is an explicit
         * interruption point, preference is given to responding to
         * the interrupt over normal or reentrant acquisition of the
         * lock.
         *
         * @throws InterruptedException if the current thread is interrupted
         */
        public void lockInterruptibly() throws InterruptedException {
            if (Thread.interrupted())
                throw new InterruptedException();
            InterruptedException ie = null;
            synchronized (this) {
                if (!startReadFromNewReader()) {
                    for (; ; ) {
                        try {
                            ReaderLock.this.wait();
                            if (startReadFromWaitingReader())
                                return;
                        }
                        catch (InterruptedException ex) {
                            cancelledWaitingReader();
                            ie = ex;
                            break;
                        }
                    }
                }
            }
            if (ie != null) {
                // fall through outside synch on interrupt.
                // This notification is not really needed here,
                //   but may be in plausible subclasses
                writerLock_.signalWaiters();
                throw ie;
            }
        }

        /**
         * Attempts to release this lock.
         *
         * <p> If the number of readers is now zero then the lock
         * is made available for write lock attempts.
         */
        public void unlock() {
            Signaller s = endRead();
            if (s != null)
                s.signalWaiters();
        }

        public synchronized void signalWaiters() {
            ReaderLock.this.notifyAll();
        }

        /**
         * Acquires the read lock only if the write lock is not held by
         * another thread at the time of invocation.
         *
         * <p>Acquires the read lock if the write lock is not held by
         * another thread and returns immediately with the value
         * <tt>true</tt>. Even when this lock has been set to use a
         * fair ordering policy, a call to <tt>tryLock()</tt>
         * <em>will</em> immediately acquire the read lock if it is
         * available, whether or not other threads are currently
         * waiting for the read lock.  This &quot;barging&quot; behavior
         * can be useful in certain circumstances, even though it
         * breaks fairness. If you want to honor the fairness setting
         * for this lock, then use {@link #tryLock(long, TimeUnit)
         * tryLock(0, TimeUnit.SECONDS) } which is almost equivalent
         * (it also detects interruption).
         *
         * <p>If the write lock is held by another thread then
         * this method will return immediately with the value
         * <tt>false</tt>.
         *
         * @return <tt>true</tt> if the read lock was acquired.
         */
        public boolean tryLock() {
            return startRead();
        }

        /**
         * Acquires the read lock if the write lock is not held by
         * another thread within the given waiting time and the
         * current thread has not been {@link Thread#interrupt
         * interrupted}.
         *
         * <p>Acquires the read lock if the write lock is not held by
         * another thread and returns immediately with the value
         * <tt>true</tt>. If this lock has been set to use a fair
         * ordering policy then an available lock <em>will not</em> be
         * acquired if any other threads are waiting for the
         * lock. This is in contrast to the {@link #tryLock()}
         * method. If you want a timed <tt>tryLock</tt> that does
         * permit barging on a fair lock then combine the timed and
         * un-timed forms together:
         *
         * <pre>if (lock.tryLock() || lock.tryLock(timeout, unit) ) { ... }
         * </pre>
         *
         * <p>If the write lock is held by another thread then the
         * current thread becomes disabled for thread scheduling
         * purposes and lies dormant until one of three things happens:
         *
         * <ul>
         *
         * <li>The read lock is acquired by the current thread; or
         *
         * <li>Some other thread {@link Thread#interrupt interrupts} the current
         * thread; or
         *
         * <li>The specified waiting time elapses
         *
         * </ul>
         *
         * <p>If the read lock is acquired then the value <tt>true</tt> is
         * returned.
         *
         * <p>If the current thread:
         *
         * <ul>
         *
         * <li>has its interrupted status set on entry to this method; or
         *
         * <li>is {@link Thread#interrupt interrupted} while acquiring
         * the read lock,
         *
         * </ul> then {@link InterruptedException} is thrown and the
         * current thread's interrupted status is cleared.
         *
         * <p>If the specified waiting time elapses then the value
         * <tt>false</tt> is returned.  If the time is less than or
         * equal to zero, the method will not wait at all.
         *
         * <p>In this implementation, as this method is an explicit
         * interruption point, preference is given to responding to
         * the interrupt over normal or reentrant acquisition of the
         * lock, and over reporting the elapse of the waiting time.
         *
         * @param timeout the time to wait for the read lock
         * @param unit the time unit of the timeout argument
         *
         * @return <tt>true</tt> if the read lock was acquired.
         *
         * @throws InterruptedException if the current thread is interrupted
         * @throws NullPointerException if unit is null
         *
         */
        public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
            if (Thread.interrupted()) throw new InterruptedException();
            InterruptedException ie = null;
            long nanos = unit.toNanos(timeout);
            synchronized (this) {
                if (nanos <= 0)
                    return startRead();
                else if (startReadFromNewReader())
                    return true;
                else {
                    long deadline = Utils.nanoTime() + nanos;
                    for (; ; ) {
                        try {
                            TimeUnit.NANOSECONDS.timedWait(ReaderLock.this, nanos);
                        }
                        catch (InterruptedException ex) {
                            cancelledWaitingReader();
                            ie = ex;
                            break;
                        }
                        if (startReadFromWaitingReader())
                            return true;
                        else {
                            nanos = deadline - Utils.nanoTime();
                            if (nanos <= 0) {
                                cancelledWaitingReader();
                                break;
                            }
                        }
                    }
                }
            }
            // safeguard on interrupt or timeout:
            writerLock_.signalWaiters();
            if (ie != null)
                throw ie;
            else
                return false; // timed out
        }

        /**
         * Throws <tt>UnsupportedOperationException</tt> because
         * <tt>ReadLocks</tt> do not support conditions.
         * @throws UnsupportedOperationException always
         */
        public Condition newCondition() {
            throw new UnsupportedOperationException();
        }

        /**
         * Returns a string identifying this lock, as well as its lock state.
         * The state, in brackets, includes the String
         * &quot;Read locks =&quot; followed by the number of held
         * read locks.
         * @return a string identifying this lock, as well as its lock state.
         */
        public String toString() {
            int r = getReadLockCount();
            return super.toString() +
                "[Read locks = " + r + "]";
        }

    }

    class WriterLock implements Signaller, Lock, java.io.Serializable,
                                CondVar.LockInfo {

        /**
         * Acquire the write lock.
         *
         * <p>Acquires the write lock if neither the read nor write lock
         * are held by another thread
         * and returns immediately, setting the write lock hold count to
         * one.
         *
         * <p>If the current thread already holds the write lock then the
         * hold count is incremented by one and the method returns
         * immediately.
         *
         * <p>If the lock is held by another thread then the current
         * thread becomes disabled for thread scheduling purposes and
         * lies dormant until the write lock has been acquired, at which
         * time the write lock hold count is set to one.
         */
        public void lock() {
            boolean wasInterrupted = false;
            while (true) {
                try {
                    lockInterruptibly();
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
         * Acquires the write lock unless the current thread is {@link
         * Thread#interrupt interrupted}.
         *
         * <p>Acquires the write lock if neither the read nor write lock
         * are held by another thread
         * and returns immediately, setting the write lock hold count to
         * one.
         *
         * <p>If the current thread already holds this lock then the
         * hold count is incremented by one and the method returns
         * immediately.
         *
         * <p>If the lock is held by another thread then the current
         * thread becomes disabled for thread scheduling purposes and
         * lies dormant until one of two things happens:
         *
         * <ul>
         *
         * <li>The write lock is acquired by the current thread; or
         *
         * <li>Some other thread {@link Thread#interrupt interrupts}
         * the current thread.
         *
         * </ul>
         *
         * <p>If the write lock is acquired by the current thread then the
         * lock hold count is set to one.
         *
         * <p>If the current thread:
         *
         * <ul>
         *
         * <li>has its interrupted status set on entry to this method;
         * or
         *
         * <li>is {@link Thread#interrupt interrupted} while acquiring
         * the write lock,
         *
         * </ul>
         *
         * then {@link InterruptedException} is thrown and the current
         * thread's interrupted status is cleared.
         *
         * <p>In this implementation, as this method is an explicit
         * interruption point, preference is given to responding to
         * the interrupt over normal or reentrant acquisition of the
         * lock.
         *
         * @throws InterruptedException if the current thread is interrupted
         */
        public void lockInterruptibly() throws InterruptedException {
            if (Thread.interrupted())
                throw new InterruptedException();
            InterruptedException ie = null;
            synchronized (this) {
                if (!startWriteFromNewWriter()) {
                    for (; ; ) {
                        try {
                            WriterLock.this.wait();
                            if (startWriteFromWaitingWriter())
                                return;
                        }
                        catch (InterruptedException ex) {
                            cancelledWaitingWriter();
                            WriterLock.this.notify();
                            ie = ex;
                            break;
                        }
                    }
                }
            }
            if (ie != null) {
                // Fall through outside synch on interrupt.
                //  On exception, we may need to signal readers.
                //  It is not worth checking here whether it is strictly necessary.
                readerLock_.signalWaiters();
                throw ie;
            }
        }

        /**
         * Attempts to release this lock.
         *
         * <p>If the current thread is the holder of this lock then
         * the hold count is decremented. If the hold count is now
         * zero then the lock is released.  If the current thread is
         * not the holder of this lock then {@link
         * IllegalMonitorStateException} is thrown.
         * @throws IllegalMonitorStateException if the current thread does not
         * hold this lock.
         */
        public void unlock() {
            Signaller s = endWrite();
            if (s != null)
                s.signalWaiters();
        }

        public synchronized void signalWaiters() {
            WriterLock.this.notify();
        }

        /**
         * Acquires the write lock only if it is not held by another thread
         * at the time of invocation.
         *
         * <p>Acquires the write lock if neither the read nor write lock
         * are held by another thread
         * and returns immediately with the value <tt>true</tt>,
         * setting the write lock hold count to one. Even when this lock has
         * been set to use a fair ordering policy, a call to
         * <tt>tryLock()</tt> <em>will</em> immediately acquire the
         * lock if it is available, whether or not other threads are
         * currently waiting for the write lock.  This &quot;barging&quot;
         * behavior can be useful in certain circumstances, even
         * though it breaks fairness. If you want to honor the
         * fairness setting for this lock, then use {@link
         * #tryLock(long, TimeUnit) tryLock(0, TimeUnit.SECONDS) }
         * which is almost equivalent (it also detects interruption).
         *
         * <p> If the current thread already holds this lock then the
         * hold count is incremented by one and the method returns
         * <tt>true</tt>.
         *
         * <p>If the lock is held by another thread then this method
         * will return immediately with the value <tt>false</tt>.
         *
         * @return <tt>true</tt> if the lock was free and was acquired
         * by the current thread, or the write lock was already held
         * by the current thread; and <tt>false</tt> otherwise.
         */
        public boolean tryLock() {
            return startWrite();
        }

        /**
         * Acquires the write lock if it is not held by another thread
         * within the given waiting time and the current thread has
         * not been {@link Thread#interrupt interrupted}.
         *
         * <p>Acquires the write lock if neither the read nor write lock
         * are held by another thread
         * and returns immediately with the value <tt>true</tt>,
         * setting the write lock hold count to one. If this lock has been
         * set to use a fair ordering policy then an available lock
         * <em>will not</em> be acquired if any other threads are
         * waiting for the write lock. This is in contrast to the {@link
         * #tryLock()} method. If you want a timed <tt>tryLock</tt>
         * that does permit barging on a fair lock then combine the
         * timed and un-timed forms together:
         *
         * <pre>if (lock.tryLock() || lock.tryLock(timeout, unit) ) { ... }
         * </pre>
         *
         * <p>If the current thread already holds this lock then the
         * hold count is incremented by one and the method returns
         * <tt>true</tt>.
         *
         * <p>If the lock is held by another thread then the current
         * thread becomes disabled for thread scheduling purposes and
         * lies dormant until one of three things happens:
         *
         * <ul>
         *
         * <li>The write lock is acquired by the current thread; or
         *
         * <li>Some other thread {@link Thread#interrupt interrupts}
         * the current thread; or
         *
         * <li>The specified waiting time elapses
         *
         * </ul>
         *
         * <p>If the write lock is acquired then the value <tt>true</tt> is
         * returned and the write lock hold count is set to one.
         *
         * <p>If the current thread:
         *
         * <ul>
         *
         * <li>has its interrupted status set on entry to this method;
         * or
         *
         * <li>is {@link Thread#interrupt interrupted} while acquiring
         * the write lock,
         *
         * </ul>
         *
         * then {@link InterruptedException} is thrown and the current
         * thread's interrupted status is cleared.
         *
         * <p>If the specified waiting time elapses then the value
         * <tt>false</tt> is returned.  If the time is less than or
         * equal to zero, the method will not wait at all.
         *
         * <p>In this implementation, as this method is an explicit
         * interruption point, preference is given to responding to
         * the interrupt over normal or reentrant acquisition of the
         * lock, and over reporting the elapse of the waiting time.
         *
         * @param timeout the time to wait for the write lock
         * @param unit the time unit of the timeout argument
         *
         * @return <tt>true</tt> if the lock was free and was acquired
         * by the current thread, or the write lock was already held by the
         * current thread; and <tt>false</tt> if the waiting time
         * elapsed before the lock could be acquired.
         *
         * @throws InterruptedException if the current thread is interrupted
         * @throws NullPointerException if unit is null
         *
         */
        public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
            if (Thread.interrupted()) throw new InterruptedException();
            InterruptedException ie = null;
            long nanos = unit.toNanos(timeout);
            synchronized (this) {
                if (nanos <= 0)
                    return startWrite();
                else if (startWriteFromNewWriter())
                    return true;
                else {
                    long deadline = Utils.nanoTime() + nanos;
                    for (; ; ) {
                        try {
                            TimeUnit.NANOSECONDS.timedWait(WriterLock.this, nanos);
                        }
                        catch (InterruptedException ex) {
                            cancelledWaitingWriter();
                            WriterLock.this.notify();
                            ie = ex;
                            break;
                        }
                        if (startWriteFromWaitingWriter())
                            return true;
                        else {
                            nanos = deadline - Utils.nanoTime();
                            if (nanos <= 0) {
                                cancelledWaitingWriter();
                                WriterLock.this.notify();
                                break;
                            }
                        }
                    }
                }
            }

            readerLock_.signalWaiters();
            if (ie != null)
                throw ie;
            else
                return false; // timed out
        }

        /**
         * Returns a {@link Condition} instance for use with this
         * {@link Lock} instance.
         * <p>The returned {@link Condition} instance supports the same
         * usages as do the {@link Object} monitor methods ({@link
         * Object#wait() wait}, {@link Object#notify notify}, and {@link
         * Object#notifyAll notifyAll}) when used with the built-in
         * monitor lock.
         *
         * <ul>
         *
         * <li>If this write lock is not held when any {@link
         * Condition} method is called then an {@link
         * IllegalMonitorStateException} is thrown.  (Read locks are
         * held independently of write locks, so are not checked or
         * affected. However it is essentially always an error to
         * invoke a condition waiting method when the current thread
         * has also acquired read locks, since other threads that
         * could unblock it will not be able to acquire the write
         * lock.)
         *
         * <li>When the condition {@link Condition#await() waiting}
         * methods are called the write lock is released and, before
         * they return, the write lock is reacquired and the lock hold
         * count restored to what it was when the method was called.
         *
         * <li>If a thread is {@link Thread#interrupt interrupted} while
         * waiting then the wait will terminate, an {@link
         * InterruptedException} will be thrown, and the thread's
         * interrupted status will be cleared.
         *
         * <li> Waiting threads are signalled in FIFO order.
         *
         * <li>The ordering of lock reacquisition for threads returning
         * from waiting methods is the same as for threads initially
         * acquiring the lock, which is in the default case not specified,
         * but for <em>fair</em> locks favors those threads that have been
         * waiting the longest.
         *
         * </ul>
         * @return the Condition object
         */
        public Condition newCondition() {
            return new CondVar(this);
        }

        /**
         * Returns a string identifying this lock, as well as its lock
         * state.  The state, in brackets includes either the String
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

        public boolean isHeldByCurrentThread() {
            return isWriteLockedByCurrentThread();
        }
    }

    // Instrumentation and status

    /**
     * Returns true if this lock has fairness set true.
     * @return true if this lock has fairness set true.
     */
    public final boolean isFair() {
        return false;
    }

    /**
     * Returns the thread that currently owns the write lock, or
     * <tt>null</tt> if not owned. Note that the owner may be
     * momentarily <tt>null</tt> even if there are threads trying to
     * acquire the lock but have not yet done so.  This method is
     * designed to facilitate construction of subclasses that provide
     * more extensive lock monitoring facilities.
     * @return the owner, or <tt>null</tt> if not owned.
     */
    protected synchronized Thread getOwner() {
        return activeWriter_;
    }

    /**
     * Queries the number of read locks held for this lock. This
     * method is designed for use in monitoring system state, not for
     * synchronization control.
     * @return the number of read locks held.
     */
    public synchronized int getReadLockCount() {
        return activeReaders_;
    }

    /**
     * Queries if the write lock is held by any thread. This method is
     * designed for use in monitoring system state, not for
     * synchronization control.
     * @return <tt>true</tt> if any thread holds the write lock and
     * <tt>false</tt> otherwise.
     */
    public synchronized boolean isWriteLocked() {
        return activeWriter_ != null;
    }

    /**
     * Queries if the write lock is held by the current thread.
     * @return <tt>true</tt> if the current thread holds the write lock and
     * <tt>false</tt> otherwise.
     */
    public synchronized boolean isWriteLockedByCurrentThread() {
        return activeWriter_ == Thread.currentThread();
    }

    /**
     * Queries the number of reentrant write holds on this lock by the
     * current thread.  A writer thread has a hold on a lock for
     * each lock action that is not matched by an unlock action.
     *
     * @return the number of holds on the write lock by the current thread,
     * or zero if the write lock is not held by the current thread.
     */
    public synchronized int getWriteHoldCount() {
        return writeHolds_;
    }

//    /**
//     * Returns a collection containing threads that may be waiting to
//     * acquire the write lock.  Because the actual set of threads may
//     * change dynamically while constructing this result, the returned
//     * collection is only a best-effort estimate.  The elements of the
//     * returned collection are in no particular order.  This method is
//     * designed to facilitate construction of subclasses that provide
//     * more extensive lock monitoring facilities.
//     * @return the collection of threads
//     */
//    protected Collection getQueuedWriterThreads() {
//        return sync.getExclusiveQueuedThreads();
//    }
//
//    /**
//     * Returns a collection containing threads that may be waiting to
//     * acquire the read lock.  Because the actual set of threads may
//     * change dynamically while constructing this result, the returned
//     * collection is only a best-effort estimate.  The elements of the
//     * returned collection are in no particular order.  This method is
//     * designed to facilitate construction of subclasses that provide
//     * more extensive lock monitoring facilities.
//     * @return the collection of threads
//     */
//    protected Collection getQueuedReaderThreads() {
//        return sync.getSharedQueuedThreads();
//    }
//
//    /**
//     * Queries whether any threads are waiting to acquire the read or
//     * write lock. Note that because cancellations may occur at any
//     * time, a <tt>true</tt> return does not guarantee that any other
//     * thread will ever acquire a lock.  This method is designed
//     * primarily for use in monitoring of the system state.
//     *
//     * @return true if there may be other threads waiting to acquire
//     * the lock.
//     */
//    public final boolean hasQueuedThreads() {
//        return sync.hasQueuedThreads();
//    }
//
//    /**
//     * Queries whether the given thread is waiting to acquire either
//     * the read or write lock. Note that because cancellations may
//     * occur at any time, a <tt>true</tt> return does not guarantee
//     * that this thread will ever acquire a lock.  This method is
//     * designed primarily for use in monitoring of the system state.
//     *
//     * @param thread the thread
//     * @return true if the given thread is queued waiting for this lock.
//     * @throws NullPointerException if thread is null
//     */
//    public final boolean hasQueuedThread(Thread thread) {
//        return sync.isQueued(thread);
//    }

    /**
     * Returns an estimate of the number of threads waiting to acquire
     * either the read or write lock.  The value is only an estimate
     * because the number of threads may change dynamically while this
     * method traverses internal data structures.  This method is
     * designed for use in monitoring of the system state, not for
     * synchronization control.
     * @return the estimated number of threads waiting for this lock
     */
    public final synchronized int getQueueLength() {
        return waitingWriters_ + waitingReaders_;
    }

//    /**
//     * Returns a collection containing threads that may be waiting to
//     * acquire either the read or write lock.  Because the actual set
//     * of threads may change dynamically while constructing this
//     * result, the returned collection is only a best-effort estimate.
//     * The elements of the returned collection are in no particular
//     * order.  This method is designed to facilitate construction of
//     * subclasses that provide more extensive monitoring facilities.
//     * @return the collection of threads
//     */
//    protected Collection getQueuedThreads() {
//        return sync.getQueuedThreads();
//    }
//
//    /**
//     * Queries whether any threads are waiting on the given condition
//     * associated with the write lock. Note that because timeouts and
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
//     * given condition associated with the write lock. Note that because
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
//
//    /**
//     * Returns a collection containing those threads that may be
//     * waiting on the given condition associated with the write lock.
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
     * Returns a string identifying this lock, as well as its lock state.
     * The state, in brackets, includes the String &quot;Write locks =&quot;
     * followed by the number of reentrantly held write locks, and the
     * String &quot;Read locks =&quot; followed by the number of held
     * read locks.
     * @return a string identifying this lock, as well as its lock state.
     */
    public synchronized String toString() {

        return super.toString() +
            "[Write locks = " + getWriteHoldCount() +
            ", Read locks = " + getReadLockCount() + "]";
    }

}
