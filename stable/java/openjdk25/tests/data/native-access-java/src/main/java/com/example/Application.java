package com.example;

import java.lang.System;
import java.lang.Runtime;

public class Application {
    public static void main(String[] args) {
        // Rule: openjdk25--to-openjdk25+-00030
        System.loadLibrary("nativeLib");

        // Rule: openjdk25--to-openjdk25+-00040
        System.load("/path/to/native.so");

        Runtime runtime = Runtime.getRuntime();

        // Rule: openjdk25--to-openjdk25+-00050
        runtime.loadLibrary("anotherLib");

        // Rule: openjdk25--to-openjdk25+-00060
        runtime.load("/path/to/another.so");
    }
}
