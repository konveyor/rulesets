/*
  based on file: QueuedSemaphore.java
  Originally written by Doug Lea and released into the public domain.
  This may be used for any purposes whatsoever without acknowledgment.
  Thanks for the assistance and support of Sun Microsystems Labs,
  and everyone contributing, testing, and using this code.
  History:
  Date       Who                What
  11Jun1998  dl               Create public version
   5Aug1998  dl               replaced int counters with longs
  24Aug1999  dl               release(n): screen arguments
 */

package edu.emory.mathcs.backport.java.util.concurrent.helpers;

import java.util.*;
import edu.emory.mathcs.backport.java.util.concurrent.*;

/**
 * Base class for internal queue classes for semaphores, etc.
 * Relies on subclasses to actually implement queue mechanics.
 * NOTE: this class is NOT present in java.util.concurrent.
 **/

public abstract class WaitQueue {

    public abstract void insert(WaitNode w); // assumed not to block
    public abstract WaitNode extract(); // should return null if empty

    public abstract boolean hasNodes();
    public abstract int getLength();
    public abstract Collection getWaitingThreads();
    public abstract boolean isWaiting(Thread thread);

    public static interface QueuedSync {
        boolean recheck(WaitNode node);
    }

    public static class WaitNode {
        boolean waiting = true;
        WaitNode next = null;
        Thread owner;

        public WaitNode() {
            this.owner = Thread.currentThread();
        }

        public synchronized boolean signal() {
            boolean signalled = waiting;
            if (signalled) {
                waiting = false;
                notify();
            }
            return signalled;
        }

        public synchronized boolean doTimedWait(QueuedSync sync, long nanos)
            throws InterruptedException
        {
            if (sync.recheck(this) || !waiting)
                return true;
            else if (nanos <= 0) {
                waiting = false;
                return false;
            }
            else {
                long deadline = Utils.nanoTime() + nanos;
                try {
                    for (; ; ) {
                        TimeUnit.NANOSECONDS.timedWait(this, nanos);
                        if (!waiting) // definitely signalled
                            return true;
                        else {
                            nanos = deadline - Utils.nanoTime();
                            if (nanos <= 0) { //  timed out
                                waiting = false;
                                return false;
                            }
                        }
                    }
                }
                catch (InterruptedException ex) {
                    if (waiting) { // no notification
                        waiting = false; // invalidate for the signaller
                        throw ex;
                    }
                    else { // thread was interrupted after it was notified
                        Thread.currentThread().interrupt();
                        return true;
                    }
                }
            }
        }

        public synchronized void doWait(QueuedSync sync)
            throws InterruptedException
        {
            if (!sync.recheck(this)) {
                try {
                    while (waiting) wait();
                }
                catch (InterruptedException ex) {
                    if (waiting) { // no notification
                        waiting = false; // invalidate for the signaller
                        throw ex;
                    }
                    else { // thread was interrupted after it was notified
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }
    }

}

