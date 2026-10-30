package com.example;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class Application {
    public static void main(String[] args) throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        // Rule: openjdk21-to-openjdk25+-00010
        unsafe.shouldBeInitialized(Application.class);

        // Rule: openjdk21-to-openjdk25+-00020
        unsafe.ensureClassInitialized(Application.class);

        // Rule: openjdk21-to-openjdk25+-00050
        unsafe.park(false, 0L);

        // Rule: openjdk21-to-openjdk25+-00060
        Thread thread = Thread.currentThread();
        unsafe.unpark(thread);

        // Rule: openjdk21-to-openjdk25+-00070
        double[] loadavg = new double[1];
        unsafe.getLoadAverage(loadavg, 1);

        // Rule: openjdk21-to-openjdk25+-00080
        unsafe.loadFence();

        // Rule: openjdk21-to-openjdk25+-00090
        unsafe.storeFence();

        // Rule: openjdk21-to-openjdk25+-00100
        unsafe.fullFence();
    }
}
