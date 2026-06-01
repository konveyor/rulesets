package com.example;

// Rule: openjdk25--to-openjdk25+-00640
import java.util.zip.ZipError;

public class Application {
    public static void main(String[] args) {
        // Reference ZipError to ensure the import is used
        ZipError error = new ZipError("test");
    }
}
