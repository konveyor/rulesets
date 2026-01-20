/*
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/licenses/publicdomain
 */

package edu.emory.mathcs.backport.java.util.concurrent.atomic;

/**
 * A <tt>boolean</tt> value that may be updated atomically. See the
 * {@link edu.emory.mathcs.util.concurrent.atomic} package specification for
 * description of the properties of atomic variables. An
 * <tt>AtomicBoolean</tt> is used in applications such as atomically
 * updated flags, and cannot be used as a replacement for a
 * {@link Boolean}.
 *
 * @since 1.5
 * @author Doug Lea
 */
public class AtomicBoolean implements java.io.Serializable {
    private static final long serialVersionUID = 4654671469794556979L;

    private int value;

    /**
     * Creates a new <tt>AtomicBoolean</tt> with the given initial value.
     *
     * @param initialValue the initial value
     */
    public AtomicBoolean(boolean initialValue) {
        value = initialValue ? 1 : 0;
    }

    /**
     * Creates a new <tt>AtomicBoolean</tt> with initial value <tt>false</tt>.
     */
    public AtomicBoolean() {
    }

    /**
     * Returns the current value.
     *
     * @return the current value
     */
    public final synchronized boolean get() {
        return value != 0;
    }

    /**
     * Atomically set the value to the given updated value
     * if the current value <tt>==</tt> the expected value.
     *
     * @param expect the expected value
     * @param update the new value
     * @return true if successful. False return indicates that
     * the actual value was not equal to the expected value.
     */
    public final synchronized boolean compareAndSet(boolean expect, boolean update) {
        boolean success = (expect == (value != 0));
        if (success)
            value = update ? 1 : 0;
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
    public synchronized boolean weakCompareAndSet(boolean expect, boolean update) {
        boolean success = (expect == (value != 0));
        if (success)
            value = update ? 1 : 0;
        return success;
    }

    /**
     * Unconditionally sets to the given value.
     *
     * @param newValue the new value
     */
    public final synchronized void set(boolean newValue) {
        value = newValue ? 1 : 0;
    }

    /**
     * Sets to the given value and returns the previous value.
     *
     * @param newValue the new value
     * @return the previous value
     */
    public final synchronized boolean getAndSet(boolean newValue) {
        int old = value;
        value = newValue ? 1 : 0;
        return old != 0;
    }

    /**
     * Returns the String representation of the current value.
     * @return the String representation of the current value.
     */
    public String toString() {
        return Boolean.toString(get());
    }

}
