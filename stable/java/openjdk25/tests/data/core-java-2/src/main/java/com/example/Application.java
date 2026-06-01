package com.example;

// Rule: openjdk25--to-openjdk25+-00570
import sun.misc.BASE64Encoder;
// Rule: openjdk25--to-openjdk25+-00580
import sun.misc.BASE64Decoder;
// Rule: openjdk25--to-openjdk25+-00590
import sun.reflect.Reflection;
// Rule: openjdk25--to-openjdk25+-01110
import java.util.regex.Pattern;
import java.lang.Runtime;
import java.lang.System;
import java.lang.Object;

public class Application {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        // Rule: openjdk25--to-openjdk25+-00750
        Runtime runtime = Runtime.getRuntime();
        runtime.runFinalization();

        // Rule: openjdk25--to-openjdk25+-00760
        System.runFinalization();
    }

    // Rule: openjdk25--to-openjdk25+-01130
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
