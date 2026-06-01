package com.example;

// Rule: openjdk25--to-openjdk25+-00020
import sun.misc.Unsafe;
// Rule: openjdk25--to-openjdk25+-00240
import java.lang.Compiler;
import java.lang.invoke.ConstantBootstraps;
import java.lang.reflect.Modifier;
import java.lang.Runtime;
import java.lang.System;

public class Application {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        // Rule: openjdk25--to-openjdk25+-00330
        ConstantBootstraps cb = new ConstantBootstraps();

        // Rule: openjdk25--to-openjdk25+-00340
        Modifier mod = new Modifier();

        // Rule: openjdk25--to-openjdk25+-00370
        Runtime runtime = Runtime.getRuntime();
        runtime.traceInstructions(true);

        // Rule: openjdk25--to-openjdk25+-00380
        runtime.traceMethodCalls(true);

        // Rule: openjdk25--to-openjdk25+-00450
        runtime.runFinalizersOnExit(true);

        // Rule: openjdk25--to-openjdk25+-00500
        System.runFinalizersOnExit(true);
    }
}
