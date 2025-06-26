package com.example.apps;

import java.io.IOException;

public class JavaNativeProcess {

    public static void main(String[] args) throws IOException {
        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec("notepad.exe");
        ProcessBuilder pb = new ProcessBuilder("javac hello.java");


    }

}