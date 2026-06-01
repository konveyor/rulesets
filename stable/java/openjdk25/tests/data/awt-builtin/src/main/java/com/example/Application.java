package com.example;

import java.awt.Window;

public class Application {
    // Rule: openjdk25--to-openjdk25+-00120
    private String warningString = "test warning";

    public static void main(String[] args) {
        // Rule: openjdk25--to-openjdk25+-00120
        String warningString = "untrusted window warning";
        System.out.println(warningString);
    }
}
