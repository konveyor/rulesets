/*
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain. Use, modify, and
 * redistribute this code in any way without acknowledgement.
 */

package edu.emory.mathcs.backport.java.util.concurrent;

import java.util.*;

import edu.emory.mathcs.backport.java.util.AbstractQueue;
import edu.emory.mathcs.backport.java.util.PriorityQueue;
import edu.emory.mathcs.backport.java.util.concurrent.locks.*;
import edu.emory.mathcs.backport.java.util.concurrent.helpers.*;

/**
 * An unbounded {@linkplain BlockingQueue blocking queue} that uses
 * the same ordering rules as class {@link PriorityQueue} and supplies
 * blocking retrieval operations.  While this queue is logically
 * unbounded, attempted additions may fail due to resource exhaustion
 * (causing <tt>OutOfMemoryError</tt>). This class does not permit
 * <tt>null</tt> elements.  A priority queue relying on natural
 * ordering also does not permit insertion of non-comparable objects
 * (doing so results in <tt>ClassCastException</tt>).
 *
 * <p>This class and its iterator implement all of the
 * <em>optional</em> methods of the {@link Collection} and {@link
 * Iterator} interfaces.
 * The Iterator provided in method {@link #iterator()} is
 * <em>not</em> guaranteed to traverse the elements of the
 * PriorityBlockingQueue in any particular order. If you need ordered
 * traversal, consider using <tt>Arrays.sort(pq.toArray())</tt>.
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/../guide/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @since 1.5
 * @author Doug Lea
 */
public class PriorityBlockingQueue extends AbstractQueue
    implements BlockingQueue, java.io.Serializable {
    private static final long serialVersionUID = 5595510919245408276L;

    private final PriorityQueue q;
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition notEmpty = lock.newCondition();

    /**
     * Creates a <tt>PriorityBlockingQueue</tt> with the default initial
     * capacity
     * (11) that orders its elements according to their natural
     * ordering (using <tt>Comparable</tt>).
     */
    public PriorityBlockingQueue() {
        q = new PriorityQueue();
    }

    /**
     * Creates a <tt>PriorityBlockingQueue</tt> with the specified initial
     * capacity
     * that orders its elements according to their natural ordering
     * (using <tt>Comparable</tt>).
     *
     * @param initialCapacity the initial capacity for this priority queue.
     * @throws IllegalArgumentException if <tt>initialCapacity</tt> is less
     * than 1
     */
    public PriorityBlockingQueue(int initialCapacity) {
        q = new PriorityQueue(initialCapacity, null);
    }

    /**
     * Creates a <tt>PriorityBlockingQueue</tt> with the specified initial
     * capacity
     * that orders its elements according to the specified comparator.
     *
     * @param initialCapacity the initial capacity for this priority queue.
     * @param comparator the comparator used to order this priority queue.
     * If <tt>null</tt> then the order depends on the elements' natural
     * ordering.
     * @throws IllegalArgumentException if <tt>initialCapacity</tt> is less
     * than 1
     */
    public PriorityBlockingQueue(int initialCapacity, Comparator comparator) {
        q = new PriorityQueue(initialCapacity, comparator);
    }

    /**
     * Creates a <tt>PriorityBlockingQueue</tt> containing the elements
     * in the specified collection.  The priority queue has an initial
     * capacity of 110% of the size of the specified collection. If
     * the specified collection is a {@link SortedSet} or a {@link
     * PriorityQueue}, this priority queue will be sorted according to
     * the same comparator, or according to its elements' natural
     * order if the collection is sorted according to its elements'
     * natural order.  Otherwise, this priority queue is ordered
     * according to its elements' natural order.
     *
     * @param c the collection whose elements are to be placed
     *        into this priority queue.
     * @throws ClassCastException if elements of the specified collection
     *         cannot be compared to one another according to the priority
     *         queue's ordering.
     * @throws NullPointerException if <tt>c</tt> or any element within it
     * is <tt>null</tt>
     */
    public PriorityBlockingQueue(Collection c) {
        q = new PriorityQueue(c);
    }


    // these first few override just to update doc comments

    /**
     * Adds the specified element to this queue.
     * @param o the element to add
     * @return <tt>true</tt> (as per the general contract of
     * <tt>Collection.add</tt>).
     *
     * @throws NullPointerException if the specified element is <tt>null</tt>.
     * @throws ClassCastException if the specified element cannot be compared
     * with elements currently in the priority queue according
     * to the priority queue's ordering.
     */
    public boolean add(Object o) {
        return super.add(o);
    }

    /**
     * Returns the comparator used to order this collection, or <tt>null</tt>
     * if this collection is sorted according to its elements natural ordering
     * (using <tt>Comparable</tt>).
     *
     * @return the comparator used to order this collection, or <tt>null</tt>
     * if this collection is sorted according to its elements natural ordering.
     */
    public Comparator comparator() {
        return q.comparator();
    }

    /**
     * Inserts the specified element into this priority queue.
     *
     * @param o the element to add
     * @return <tt>true</tt>
     * @throws ClassCastException if the specified element cannot be compared
     * with elements currently in the priority queue according
     * to the priority queue's ordering.
     * @throws NullPointerException if the specified element is <tt>null</tt>.
     */
    public boolean offer(Object o) {
        if (o == null) throw new NullPointerException();
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            boolean ok = q.offer(o);
            assert ok;
            notEmpty.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Adds the specified element to this priority queue. As the queue is
     * unbounded this method will never block.
     * @param o the element to add
     * @throws ClassCastException if the element cannot be compared
     * with elements currently in the priority queue according
     * to the priority queue's ordering.
     * @throws NullPointerException if the specified element is <tt>null</tt>.
     */
    public void put(Object o) {
        offer(o); // never need to block
    }

    /**
     * Inserts the specified element into this priority queue. As the queue is
     * unbounded this method will never block.
     * @param o the element to add
     * @param timeout This parameter is ignored as the method never blocks
     * @param unit This parameter is ignored as the method never blocks
     * @return <tt>true</tt>
     * @throws ClassCastException if the element cannot be compared
     * with elements currently in the priority queue according
     * to the priority queue's ordering.
     * @throws NullPointerException if the specified element is <tt>null</tt>.
     */
    public boolean offer(Object o, long timeout, TimeUnit unit) {
        return offer(o); // never need to block
    }

    public Object take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            try {
                while (q.size() == 0)
                    notEmpty.await();
            } catch (InterruptedException ie) {
                notEmpty.signal(); // propagate to non-interrupted thread
                throw ie;
            }
            Object x = q.poll();
            assert x != null;
            return x;
        } finally {
            lock.unlock();
        }
    }


    public Object poll() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return q.poll();
        } finally {
            lock.unlock();
        }
    }

    public Object poll(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            long deadline = Utils.nanoTime() + nanos;
            for (;;) {
                Object x = q.poll();
                if (x != null)
                    return x;
                if (nanos <= 0)
                    return null;
                try {
                    notEmpty.await(nanos, TimeUnit.NANOSECONDS);
                    nanos = deadline - Utils.nanoTime();
                } catch (InterruptedException ie) {
                    notEmpty.signal(); // propagate to non-interrupted thread
                    throw ie;
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public Object peek() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return q.peek();
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return q.size();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Always returns <tt>Integer.MAX_VALUE</tt> because
     * a <tt>PriorityBlockingQueue</tt> is not capacity constrained.
     * @return <tt>Integer.MAX_VALUE</tt>
     */
    public int remainingCapacity() {
        return Integer.MAX_VALUE;
    }

    /**
     * Removes a single instance of the specified element from this
     * queue, if it is present.
     */
    public boolean remove(Object o) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return q.remove(o);
        } finally {
            lock.unlock();
        }
    }

    public boolean contains(Object o) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return q.contains(o);
        } finally {
            lock.unlock();
        }
    }

    public Object[] toArray() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return q.toArray();
        } finally {
            lock.unlock();
        }
    }


    public String toString() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return q.toString();
        } finally {
            lock.unlock();
        }
    }

    public int drainTo(Collection c) {
        if (c == null)
            throw new NullPointerException();
        if (c == this)
            throw new IllegalArgumentException();
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            int n = 0;
            Object e;
            while ( (e = q.poll()) != null) {
                c.add(e);
                ++n;
            }
            return n;
        } finally {
            lock.unlock();
        }
    }

    public int drainTo(Collection c, int maxElements) {
        if (c == null)
            throw new NullPointerException();
        if (c == this)
            throw new IllegalArgumentException();
        if (maxElements <= 0)
            return 0;
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            int n = 0;
            Object e;
            while (n < maxElements && (e = q.poll()) != null) {
                c.add(e);
                ++n;
            }
            return n;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Atomically removes all of the elements from this queue.
     * The queue will be empty after this call returns.
     */
    public void clear() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            q.clear();
        } finally {
            lock.unlock();
        }
    }

    public Object[] toArray(Object[] a) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return q.toArray(a);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns an iterator over the elements in this queue. The
     * iterator does not return the elements in any particular order.
     * The returned iterator is a thread-safe "fast-fail" iterator
     * that will throw {@link
     * ConcurrentModificationException} upon detected
     * interference.
     *
     * @return an iterator over the elements in this queue.
     */
    public Iterator iterator() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return new Itr(q.iterator());
        } finally {
            lock.unlock();
        }
    }

    private class Itr implements Iterator {
        private final Iterator iter;
        Itr(Iterator i) {
            iter = i;
        }

        public boolean hasNext() {
            /*
             * No sync -- we rely on underlying hasNext to be
             * stateless, in which case we can return true by mistake
             * only when next() will subsequently throw
             * ConcurrentModificationException.
             */
            return iter.hasNext();
        }

        public Object next() {
            ReentrantLock lock = PriorityBlockingQueue.this.lock;
            lock.lock();
            try {
                return iter.next();
            } finally {
                lock.unlock();
            }
        }

        public void remove() {
            ReentrantLock lock = PriorityBlockingQueue.this.lock;
            lock.lock();
            try {
                iter.remove();
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * Save the state to a stream (that is, serialize it).  This
     * merely wraps default serialization within lock.  The
     * serialization strategy for items is left to underlying
     * Queue. Note that locking is not needed on deserialization, so
     * readObject is not defined, just relying on default.
     */
    private void writeObject(java.io.ObjectOutputStream s)
        throws java.io.IOException {
        lock.lock();
        try {
            s.defaultWriteObject();
        } finally {
            lock.unlock();
        }
    }

}
