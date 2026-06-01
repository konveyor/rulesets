package com.example;

public class Application {

    // Rule: openjdk25--to-openjdk25+-00740
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static void main(String[] args) {
    }
}
