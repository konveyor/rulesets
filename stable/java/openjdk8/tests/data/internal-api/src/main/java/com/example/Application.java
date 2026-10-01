package com.example;

// Rule: internal-api-import-00010
import sun.misc.Unsafe;

public class Application {
    public static void main(String[] args) throws Exception {
        // Rule: internal-api-import-00010 - sun.misc package reference
        Unsafe unsafe = null;
        try {
            java.lang.reflect.Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
