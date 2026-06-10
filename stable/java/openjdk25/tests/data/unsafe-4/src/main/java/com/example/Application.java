package com.example;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class Application {
    public static void main(String[] args) throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        long mem = unsafe.allocateMemory(256);

        // Rule: openjdk21-to-openjdk25+-00870
        unsafe.putChar(mem, 'A');

        // Rule: openjdk21-to-openjdk25+-00880
        float floatVal = unsafe.getFloat(mem);

        // Rule: openjdk21-to-openjdk25+-00890
        unsafe.putFloat(mem, 3.14f);

        // Rule: openjdk21-to-openjdk25+-00900
        double doubleVal = unsafe.getDouble(mem);

        // Rule: openjdk21-to-openjdk25+-00910
        unsafe.putDouble(mem, 2.718);

        // Rule: openjdk21-to-openjdk25+-00920
        long addrVal = unsafe.getAddress(mem);

        // Rule: openjdk21-to-openjdk25+-00930
        unsafe.putAddress(mem, 0L);

        unsafe.freeMemory(mem);
    }
}
