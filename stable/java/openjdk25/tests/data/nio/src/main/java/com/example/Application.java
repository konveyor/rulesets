package com.example;

// Rule: openjdk25--to-openjdk25+-00690
import com.sun.nio.file.SensitivityWatchEventModifier;

public class Application {
    public static void main(String[] args) {
        // Rule: openjdk25--to-openjdk25+-00690
        SensitivityWatchEventModifier modifier = SensitivityWatchEventModifier.HIGH;
    }
}
