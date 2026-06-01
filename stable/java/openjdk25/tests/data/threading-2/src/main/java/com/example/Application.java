package com.example;

import java.lang.Thread;

public class Application {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Thread t = new Thread();

        // Rule: openjdk25--to-openjdk25+-00520
        t.stop(new RuntimeException("stopping"));
    }
}
