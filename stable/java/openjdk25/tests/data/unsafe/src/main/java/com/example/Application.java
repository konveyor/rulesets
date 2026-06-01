package com.example;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class Application {
    public static void main(String[] args) throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        // Rule: openjdk25--to-openjdk25+-00670
        boolean b = unsafe.shouldBeInitialized(String.class);

        // Rule: openjdk25--to-openjdk25+-00680
        unsafe.ensureClassInitialized(String.class);
    }
}
