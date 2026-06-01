package com.example;

import java.lang.Thread;
import java.lang.ThreadGroup;

public class Application {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Thread t = new Thread();
        ThreadGroup tg = new ThreadGroup("test");

        // Rule: openjdk25--to-openjdk25+-00180
        t.resume();

        // Rule: openjdk25--to-openjdk25+-00190
        t.suspend();

        // Rule: openjdk25--to-openjdk25+-00200
        tg.resume();

        // Rule: openjdk25--to-openjdk25+-00210
        tg.stop();

        // Rule: openjdk25--to-openjdk25+-00220
        tg.suspend();

        // Rule: openjdk25--to-openjdk25+-00230
        t.countStackFrames();

        // Rule: openjdk25--to-openjdk25+-00260
        tg.allowThreadSuspension(true);

        // Rule: openjdk25--to-openjdk25+-00510
        t.destroy();
    }
}
