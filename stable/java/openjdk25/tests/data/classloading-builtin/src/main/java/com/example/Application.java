package com.example;

public class Application {
    public static void main(String[] args) {
        // Rule: openjdk25--to-openjdk25+-00930
        // Reference to rt.jar for classpath configuration
        String rtPath = System.getenv("JAVA_HOME") + "/lib/rt.jar";

        // Rule: openjdk25--to-openjdk25+-00940
        // Reference to tools.jar for compilation tooling
        String toolsPath = System.getenv("JAVA_HOME") + "/lib/tools.jar";

        // Rule: openjdk25--to-openjdk25+-00950
        // Reference to java.ext.dirs extension mechanism
        String extDirs = System.getProperty("java.ext.dirs");

        // Rule: openjdk25--to-openjdk25+-00960
        // Reference to java.endorsed.dirs override mechanism
        String endorsedDirs = System.getProperty("java.endorsed.dirs");
    }
}
