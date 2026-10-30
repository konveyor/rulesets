package com.example;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class Application {
    public static void main(String[] args) throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        // Rule: openjdk21-to-openjdk25+-00710
        long addr = unsafe.allocateMemory(1024);

        // Rule: openjdk21-to-openjdk25+-00720
        unsafe.freeMemory(addr);

        // Rule: openjdk21-to-openjdk25+-00730
        long addr2 = unsafe.allocateMemory(512);
        long addr3 = unsafe.reallocateMemory(addr2, 2048);

        // Rule: openjdk21-to-openjdk25+-00740
        long src = unsafe.allocateMemory(256);
        long dst = unsafe.allocateMemory(256);
        unsafe.copyMemory(src, dst, 256);

        // Rule: openjdk21-to-openjdk25+-00750
        long mem = unsafe.allocateMemory(128);
        unsafe.setMemory(mem, 128, (byte) 0);

        // Rule: openjdk21-to-openjdk25+-00760
        int intVal = unsafe.getInt(mem);

        // Rule: openjdk21-to-openjdk25+-00770
        unsafe.putInt(mem, 42);

        // Rule: openjdk21-to-openjdk25+-00780
        long longVal = unsafe.getLong(mem);

        unsafe.freeMemory(mem);
        unsafe.freeMemory(src);
        unsafe.freeMemory(dst);
        unsafe.freeMemory(addr3);
    }
}
