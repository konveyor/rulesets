package com.example;

import java.lang.Thread;
import java.lang.ThreadGroup;

public class Application {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {});
        thread.start();

        // Rule: openjdk21-to-openjdk25+-00030
        thread.countStackFrames();

        // Rule: openjdk21-to-openjdk25+-00170
        thread.suspend();

        // Rule: openjdk21-to-openjdk25+-00180
        thread.resume();

        ThreadGroup group = new ThreadGroup("test-group");

        // Rule: openjdk21-to-openjdk25+-00190
        group.suspend();

        // Rule: openjdk21-to-openjdk25+-00200
        group.resume();

        // Rule: openjdk21-to-openjdk25+-00210
        group.stop();
    }
}
