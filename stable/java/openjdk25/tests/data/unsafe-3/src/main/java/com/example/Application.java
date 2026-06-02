package com.example;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class Application {
    public static void main(String[] args) throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        long mem = unsafe.allocateMemory(256);

        // Rule: openjdk21-to-openjdk25+-00790
        unsafe.putLong(mem, 123456789L);

        // Rule: openjdk21-to-openjdk25+-00800
        Object obj = new Object();
        long objectFieldOffset = unsafe.objectFieldOffset(Application.class.getDeclaredField("testField"));
        Object result = unsafe.getObject(obj, objectFieldOffset);

        // Rule: openjdk21-to-openjdk25+-00810
        unsafe.putObject(obj, objectFieldOffset, "newValue");

        // Rule: openjdk21-to-openjdk25+-00820
        byte byteVal = unsafe.getByte(mem);

        // Rule: openjdk21-to-openjdk25+-00830
        unsafe.putByte(mem, (byte) 42);

        // Rule: openjdk21-to-openjdk25+-00840
        short shortVal = unsafe.getShort(mem);

        // Rule: openjdk21-to-openjdk25+-00850
        unsafe.putShort(mem, (short) 100);

        // Rule: openjdk21-to-openjdk25+-00860
        char charVal = unsafe.getChar(mem);

        unsafe.freeMemory(mem);
    }

    private Object testField;
}
