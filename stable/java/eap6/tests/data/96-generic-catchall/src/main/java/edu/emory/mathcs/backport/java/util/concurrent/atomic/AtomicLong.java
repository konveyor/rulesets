/*
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/licenses/publicdomain
 */

package edu.emory.mathcs.backport.java.util.concurrent.atomic;

/**
 * A <tt>long</tt> value that may be updated atomically.  See the
 * {@link edu.emory.mathcs.util.concurrent.atomic} package specification for
 * description of the properties of atomic variables. An
 * <tt>AtomicLong</tt> is used in applications such as atomically
 * incremented sequence numbers, and cannot be used as a replacement
 * for a {@link Long}. However, this class does extend
 * <tt>Number</tt> to allow uniform access by tools and utilities that
 * deal with numerically-based classes.
 *
 * @since 1.5
 * @author Doug Lea
 */
public class AtomicLong extends Number implements java.io.Serializable {
    private static final long serialVersionUID = 1927816293512124184L;

    private long value;

    /**
     * Create a new AtomicLong with the given initial value.
     *
     * @param initialValue the initial value
     */
    public AtomicLong(long initialValue) {
        value = initialValue;
    }

    /**
     * Create a new AtomicLong with initial value <tt>0</tt>.
     */
    public AtomicLong() {
    }

    /**
     * Get the current value.
     *
     * @return the current value
     */
    public final synchronized long get() {
        return value;
    }

    /**
     * Set to the given value.
     *
     * @param newValue the new value
     */
    public final synchronized void set(long newValue) {
        value = newValue;
    }

    /**
     * Set to the give value and return the old value.
     *
     * @param newValue the new value
     * @return the previous value
     */
    public final synchronized long getAndSet(long newValue) {
        long old = value;
        value = newValue;
        return old;
    }

    /**
     * Atomically set the value to the given updated value
     * if the current value <tt>==</tt> the expected value.
     * @param expect the expected value
     * @param update the new value
     * @return true if successful. False return indicates that
     * the actual value was not equal to the expected value.
     */
    public final synchronized boolean compareAndSet(long expect, long update) {
        boolean success = (expect == value);
        if (success)
            value = update;
        return success;
    }

    /**
     * Atomically set the value to the given updated value
     * if the current value <tt>==</tt> the expected value.
     * May fail spuriously.
     * @param expect the expected value
     * @param update the new value
     * @return true if successful.
     */
    public final synchronized boolean weakCompareAndSet(long expect, long update) {
        boolean success = (expect == value);
        if (success)
            value = update;
        return success;
    }

    /**
     * Atomically increment by one the current value.
     * @return the previous value
     */
    public final synchronized long getAndIncrement() {
        return value++;
    }


    /**
     * Atomically decrement by one the current value.
     * @return the previous value
     */
    public final synchronized long getAndDecrement() {
        return value--;
    }


    /**
     * Atomically add the given value to current value.
     * @param delta the value to add
     * @return the previous value
     */
    public final synchronized long getAndAdd(long delta) {
        long old = value;
        value += delta;
        return old;
    }

    /**
     * Atomically increment by one the current value.
     * @return the updated value
     */
    public final synchronized long incrementAndGet() {
        return ++value;
    }

    /**
     * Atomically decrement by one the current value.
     * @return the updated value
     */
    public final synchronized long decrementAndGet() {
        return --value;
    }


    /**
     * Atomically add the given value to current value.
     * @param delta the value to add
     * @return the updated value
     */
    public final synchronized long addAndGet(long delta) {
        return value += delta;
    }

    /**
     * Returns the String representation of the current value.
     * @return the String representation of the current value.
     */
    public String toString() {
        return Long.toString(get());
    }


    public int intValue() {
        return (int)get();
    }

    public long longValue() {
        return (long)get();
    }

    public float floatValue() {
        return (float)get();
    }

    public double doubleValue() {
        return (double)get();
    }

}
