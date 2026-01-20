/*
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain. Use, modify, and
 * redistribute this code in any way without acknowledgement.
 */

package edu.emory.mathcs.backport.java.util.concurrent;

import java.util.*;

import edu.emory.mathcs.backport.java.util.AbstractQueue;
import edu.emory.mathcs.backport.java.util.concurrent.helpers.*;

/**
 * An optionally-bounded {@linkplain BlockingQueue blocking queue} based on
 * linked nodes.
 * This queue orders elements FIFO (first-in-first-out).
 * The <em>head</em> of the queue is that element that has been on the
 * queue the longest time.
 * The <em>tail</em> of the queue is that element that has been on the
 * queue the shortest time. New elements
 * are inserted at the tail of the queue, and the queue retrieval
 * operations obtain elements at the head of the queue.
 * Linked queues typically have higher throughput than array-based queues but
 * less predictable performance in most concurrent applications.
 *
 * <p> The optional capacity bound constructor argument serves as a
 * way to prevent excessive queue expansion. The capacity, if unspecified,
 * is equal to {@link Integer#MAX_VALUE}.  Linked nodes are
 * dynamically created upon each insertion unless this would bring the
 * queue above capacity.
 *
 * <p>This class and its iterator implement all of the
 * <em>optional</em> methods of the {@link Collection} and {@link
 * Iterator} interfaces.
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/../guide/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @since 1.5
 * @author Doug Lea
 * @param <E> the type of elements held in this collection
 *
 **/
public class LinkedBlockingQueue extends AbstractQueue
        implements BlockingQueue, java.io.Serializable {
    private static final long serialVersionUID = -6903933977591709194L;

    class LinkedNode {
        Object value;
        LinkedNode next;
        LinkedNode() {}
        LinkedNode(Object x) { value = x; }
        LinkedNode(Object x, LinkedNode n) { value = x; next = n; }
    }

    /**
     * Dummy header node of list. The first actual node, if it exists, is always
     * at head_.next. After each take, the old first node becomes the head.
     **/
    protected transient LinkedNode head_;

    /**
     * The last node of list. Put() appends to list, so modifies last_
     **/
    protected transient LinkedNode last_;

    private static class Guard implements java.io.Serializable {}

    /**
     * Helper monitor. Ensures that only one put at a time executes.
     **/

    protected final Object putGuard_ = new Guard();

    /**
     * Helper monitor. Protects and provides wait queue for takes
     **/

    protected final Object takeGuard_ = new Guard();


    /** Number of elements allowed **/
    protected int capacity_;


    /**
     * One side of a split permit count.
     * The counts represent permits to do a put. (The queue is full when zero).
     * Invariant: putSidePutPermits_ + takeSidePutPermits_ = capacity_ - length.
     * (The length is never separately recorded, so this cannot be
     * checked explicitly.)
     * To minimize contention between puts and takes, the
     * put side uses up all of its permits before transfering them from
     * the take side. The take side just increments the count upon each take.
     * Thus, most puts and take can run independently of each other unless
     * the queue is empty or full.
     * Initial value is queue capacity.
     **/

    protected transient int putSidePutPermits_;

    /** Number of takes since last reconcile **/
    protected transient int takeSidePutPermits_ = 0;


    /**
     * Creates a <tt>LinkedBlockingQueue</tt> with a capacity of
     * {@link Integer#MAX_VALUE}.
     */
    public LinkedBlockingQueue() {
        this(Integer.MAX_VALUE);
    }

    /**
     * Creates a <tt>LinkedBlockingQueue</tt> with the given (fixed) capacity.
     *
     * @param capacity the capacity of this queue.
     * @throws IllegalArgumentException if <tt>capacity</tt> is not greater
     *         than zero.
     */
    public LinkedBlockingQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException();
        capacity_ = capacity;
        putSidePutPermits_ = capacity;
        head_ =  new LinkedNode(null);
        last_ = head_;
    }

    /**
     * Creates a <tt>LinkedBlockingQueue</tt> with a capacity of
     * {@link Integer#MAX_VALUE}, initially containing the elements of the
     * given collection,
     * added in traversal order of the collection's iterator.
     * @param c the collection of elements to initially contain
     * @throws NullPointerException if <tt>c</tt> or any element within it
     * is <tt>null</tt>
     */
    public LinkedBlockingQueue(Collection c) {
        this(Integer.MAX_VALUE);
        for (Iterator it = c.iterator(); it.hasNext();)
            add(it.next());
    }

    /**
     * Move put permits from take side to put side;
     * return the number of put side permits that are available.
     * Call only under synch on puGuard_ AND this.
     **/
    protected final int reconcilePutPermits() {
      putSidePutPermits_ += takeSidePutPermits_;
      takeSidePutPermits_ = 0;
      return putSidePutPermits_;
    }

    // this doc comment is overridden to remove the reference to collections
    // greater in size than Integer.MAX_VALUE
    /**
     * Returns the number of elements in this queue.
     *
     * @return  the number of elements in this queue.
     */
    public synchronized int size() {
        /*
          This should ideally synch on putGuard_, but
          doing so would cause it to block waiting for an in-progress
          put, which might be stuck. So we instead use whatever
          value of putSidePutPermits_ that we happen to read.
         */
        return capacity_ - (takeSidePutPermits_ + putSidePutPermits_);
    }

    // this doc comment is a modified copy of the inherited doc comment,
    // without the reference to unlimited queues.
    /**
     * Returns the number of elements that this queue can ideally (in
     * the absence of memory or resource constraints) accept without
     * blocking. This is always equal to the initial capacity of this queue
     * less the current <tt>size</tt> of this queue.
     * <p>Note that you <em>cannot</em> always tell if
     * an attempt to <tt>add</tt> an element will succeed by
     * inspecting <tt>remainingCapacity</tt> because it may be the
     * case that a waiting consumer is ready to <tt>take</tt> an
     * element out of an otherwise full queue.
     */
    public int remainingCapacity() {
        return capacity_ - size();
    }

    /** Notify a waiting take if needed **/
    protected final void allowTake() {
        synchronized (takeGuard_) {
            takeGuard_.notify();
        }
    }

    /**
     * Create and insert a node.
     * Call only under synch on putGuard_
     **/
    protected void insert(Object x) {
        --putSidePutPermits_;
        LinkedNode p = new LinkedNode(x);
        synchronized (last_) {
            last_.next = p;
            last_ = p;
        }
    }

    /**
     * Adds the specified element to the tail of this queue, waiting if
     * necessary for space to become available.
     * @param o the element to add
     * @throws InterruptedException if interrupted while waiting.
     * @throws NullPointerException if the specified element is <tt>null</tt>.
     */
    public void put(Object o) throws InterruptedException {
        if (o == null)
            throw new NullPointerException();
        if (Thread.interrupted())
            throw new InterruptedException();

        synchronized (putGuard_) {

            if (putSidePutPermits_ <= 0) { // wait for permit.
                synchronized (this) {
                    if (reconcilePutPermits() <= 0) {
                        try {
                            for (; ; ) {
                                wait();
                                if (reconcilePutPermits() > 0) {
                                    break;
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
            insert(o);
        }
        // call outside of lock to loosen put/take coupling
        allowTake();
    }

    /**
     * Inserts the specified element at the tail of this queue, waiting if
     * necessary up to the specified wait time for space to become available.
     * @param o the element to add
     * @param timeout how long to wait before giving up, in units of
     * <tt>unit</tt>
     * @param unit a <tt>TimeUnit</tt> determining how to interpret the
     * <tt>timeout</tt> parameter
     * @return <tt>true</tt> if successful, or <tt>false</tt> if
     * the specified waiting time elapses before space is available.
     * @throws InterruptedException if interrupted while waiting.
     * @throws NullPointerException if the specified element is <tt>null</tt>.
     */
    public boolean offer(Object o, long timeout, TimeUnit unit)
        throws InterruptedException {

        if (o == null) throw new IllegalArgumentException();
        if (Thread.interrupted()) throw new InterruptedException();
        long nanos = unit.toNanos(timeout);

        synchronized (putGuard_) {

            if (putSidePutPermits_ <= 0) {
                synchronized (this) {
                    if (reconcilePutPermits() <= 0) {
                        if (nanos <= 0)
                            return false;
                        else {
                            try {
                                long deadline = Utils.nanoTime() + nanos;
                                for (; ; ) {
                                    TimeUnit.NANOSECONDS.timedWait(this, nanos);
                                    if (reconcilePutPermits() > 0) {
                                        break;
                                    }
                                    else {
                                        nanos = deadline - Utils.nanoTime();
                                        if (nanos <= 0) {
                                            return false;
                                        }
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
            }

            insert(o);
        }

        allowTake();
        return true;
    }

    /**
     * Inserts the specified element at the tail of this queue if possible,
     * returning immediately if this queue is full.
     *
     * @param o the element to add.
     * @return <tt>true</tt> if it was possible to add the element to
     *         this queue, else <tt>false</tt>
     * @throws NullPointerException if the specified element is <tt>null</tt>
     */
    public boolean offer(Object o) {
        if (o == null) throw new NullPointerException();
        synchronized (putGuard_) {
            if (putSidePutPermits_ <= 0) {
                synchronized (this) {
                    if (reconcilePutPermits() <= 0) return false;
                }
            }
            insert(o);
        }

        allowTake();
        return true;
    }

    /** Main mechanics for take/poll **/
    protected synchronized Object extract() {
        synchronized (head_) {
            Object x = null;
            LinkedNode first = head_.next;
            if (first != null) {
                x = first.value;
                first.value = null;
                head_ = first;
                ++takeSidePutPermits_;
                notify();
            }
            return x;
        }
    }

    public Object take() throws InterruptedException {
        if (Thread.interrupted()) throw new InterruptedException();
        Object x = extract();
        if (x != null)
            return x;
        else {
            synchronized (takeGuard_) {
                try {
                    for (; ; ) {
                        x = extract();
                        if (x != null) {
                            return x;
                        }
                        else {
                            takeGuard_.wait();
                        }
                    }
                }
                catch (InterruptedException ex) {
                    takeGuard_.notify();
                    throw ex;
                }
            }
        }
    }

    public Object poll(long timeout, TimeUnit unit) throws InterruptedException {
        if (Thread.interrupted()) throw new InterruptedException();
        Object x = extract();
        if (x != null)
            return x;
        else {
            long nanos = unit.toNanos(timeout);
            synchronized (takeGuard_) {
                try {
                    long deadline = Utils.nanoTime() + nanos;
                    for (; ; ) {
                        x = extract();
                        if (x != null || nanos <= 0) {
                            return x;
                        }
                        else {
                            TimeUnit.NANOSECONDS.timedWait(takeGuard_, nanos);
                            nanos = deadline - Utils.nanoTime();
                        }
                    }
                }
                catch (InterruptedException ex) {
                    takeGuard_.notify();
                    throw ex;
                }
            }
        }
    }

    public Object poll() {
        return extract();
    }


    public Object peek() {
        synchronized (head_) {
            LinkedNode first = head_.next;
            if (first != null)
                return first.value;
            else
                return null;
        }
    }

    /**
     * Removes a single instance of the specified element from this
     * queue, if it is present.
     */
    public boolean remove(Object o) {
        if (o == null) return false;
        boolean removed = false;

        synchronized (putGuard_) {
            synchronized (takeGuard_) {
                synchronized (this) {
                    synchronized (head_) {
                        LinkedNode trail = head_;
                        LinkedNode p = head_.next;
                        while (p != null) {
                            if (o.equals(p.value)) {
                                removed = true;
                                break;
                            }
                            trail = p;
                            p = p.next;
                        }
                        if (removed) {
                            p.value = null;
                            trail.next = p.next;
                            ++takeSidePutPermits_;
                            notify();
                        }
                    }
                }
            }
        }

        return removed;
    }

    public Object[] toArray() {
        synchronized (putGuard_) {
            synchronized (takeGuard_) {
                int size = size();
                Object[] a = new Object[size];
                int k = 0;
                for (LinkedNode p = head_.next; p != null; p = p.next)
                    a[k++] = p.value;
                return a;
            }
        }
    }

    public Object[] toArray(Object[] a) {
        synchronized (putGuard_) {
            synchronized (takeGuard_) {
                int size = size();
                if (a.length < size)
                    a = (Object[]) java.lang.reflect.Array.newInstance
                        (a.getClass().getComponentType(), size);

                int k = 0;
                for (LinkedNode p = head_.next; p != null; p = p.next)
                    a[k++] = p.value;
                return a;
            }
        }
    }

    public String toString() {
        synchronized (putGuard_) {
            synchronized (takeGuard_) {
                return super.toString();
            }
        }
    }

    /**
     * Atomically removes all of the elements from this queue.
     * The queue will be empty after this call returns.
     */
    public void clear() {
        synchronized (putGuard_) {
            synchronized (takeGuard_) {
                synchronized (this) {
                    synchronized (head_) {
                        head_.next = null;
                        head_.value = null;
                        last_ = head_;
                        putSidePutPermits_ = capacity_;
                        takeSidePutPermits_ = 0;
                        notify();
                    }
                }
            }
        }
    }

    public int drainTo(Collection c) {
        if (c == null)
            throw new NullPointerException();
        if (c == this)
            throw new IllegalArgumentException();
        LinkedNode first;

        synchronized (putGuard_) {
            synchronized (takeGuard_) {
                synchronized (this) {
                    synchronized (head_) {
                        first = head_.next;
                        head_.next = null;
                        head_.value = null;
                        last_ = head_;
                        putSidePutPermits_ = capacity_;
                        takeSidePutPermits_ = 0;
                        notify();
                    }
                }
            }
        }
        // Transfer the elements outside of locks
        int n = 0;
        for (LinkedNode p = first; p != null; p = p.next) {
            c.add(p.value);
            p.value = null;
            ++n;
        }
        return n;
    }

    public int drainTo(Collection c, int maxElements) {
        if (c == null)
            throw new NullPointerException();
        if (c == this)
            throw new IllegalArgumentException();
        if (maxElements <= 0)
            return 0;

        synchronized (putGuard_) {
            synchronized (takeGuard_) {
                synchronized (this) {
                    synchronized (head_) {
                        int n = 0;
                        LinkedNode p = head_.next;
                        while (p != null && n < maxElements) {
                            c.add(p.value);
                            p.value = null;
                            p = p.next;
                            ++n;
                        }
                        if (n != 0) {
                            head_.next = p;
                            if (p == null) {
                                head_.value = null;
                                last_ = head_;
                            }
                            takeSidePutPermits_ += n;
                            notify();
                        }
                        return n;
                    }
                }
            }
        }
    }

    /**
     * Returns an iterator over the elements in this queue in proper sequence.
     * The returned <tt>Iterator</tt> is a "weakly consistent" iterator that
     * will never throw {@link ConcurrentModificationException},
     * and guarantees to traverse elements as they existed upon
     * construction of the iterator, and may (but is not guaranteed to)
     * reflect any modifications subsequent to construction.
     *
     * @return an iterator over the elements in this queue in proper sequence.
     */
    public Iterator iterator() {
      return new Itr();
    }

    private class Itr implements Iterator {
        /*
         * Basic weak-consistent iterator.  At all times hold the next
         * item to hand out so that if hasNext() reports true, we will
         * still have it to return even if lost race with a take etc.
         */
        private LinkedNode current;
        private LinkedNode lastRet;
        private Object currentElement;

        Itr() {
            synchronized (putGuard_) {
                synchronized (takeGuard_) {
                    synchronized (LinkedBlockingQueue.this) {
                        synchronized (head_) {
                            current = head_.next;
                            if (current != null)
                                currentElement = current.value;
                        }
                    }
                }
            }
        }

        public boolean hasNext() {
            return current != null;
        }

        public Object next() {
            synchronized (putGuard_) {
                synchronized (takeGuard_) {
                    synchronized (LinkedBlockingQueue.this) {
                        synchronized (head_) {
                            if (current == null)
                                throw new NoSuchElementException();
                            Object x = currentElement;
                            lastRet = current;
                            current = current.next;
                            if (current != null)
                                currentElement = current.value;
                            return x;
                        }
                    }
                }
            }
        }

        public void remove() {
            if (lastRet == null)
                throw new IllegalStateException();
            synchronized (putGuard_) {
                synchronized (takeGuard_) {
                    synchronized (LinkedBlockingQueue.this) {
                        synchronized (head_) {
                            LinkedNode node = lastRet;
                            lastRet = null;
                            LinkedNode trail = head_;
                            LinkedNode p = head_.next;
                            while (p != null && p != node) {
                                trail = p;
                                p = p.next;
                            }
                            if (p == node) {
                                p.value = null;
                                trail.next = p.next;
                                takeSidePutPermits_++;
                                LinkedBlockingQueue.this.notify();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Save the state to a stream (that is, serialize it).
     *
     * @serialData The capacity is emitted (int), followed by all of
     * its elements (each an <tt>Object</tt>) in the proper order,
     * followed by a null
     * @param s the stream
     */
    private void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException {

        synchronized (putGuard_) {
            synchronized (takeGuard_) {
                synchronized (LinkedBlockingQueue.this) {
                    synchronized (head_) {
                        // Write out any hidden stuff, plus capacity
                        s.defaultWriteObject();

                        // Write out all elements in the proper order.
                        for (LinkedNode p = head_.next; p != null; p = p.next)
                            s.writeObject(p.value);

                            // Use trailing null as sentinel
                        s.writeObject(null);
                    }
                }
            }
        }
    }

    /**
     * Reconstitute this queue instance from a stream (that is,
     * deserialize it).
     * @param s the stream
     */
    private void readObject(java.io.ObjectInputStream s)
        throws java.io.IOException, ClassNotFoundException {

        synchronized (this) {
            // Read in capacity, and any hidden stuff
            s.defaultReadObject();

            putSidePutPermits_ = capacity_;
            takeSidePutPermits_ = 0;
            last_ = head_ = new LinkedNode(null);
        }

        // Read in all elements and place in queue
        for (;;) {
            Object item = (Object)s.readObject();
            if (item == null)
                break;
            add(item);
        }
    }
}
