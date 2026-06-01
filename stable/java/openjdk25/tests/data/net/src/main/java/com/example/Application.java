package com.example;

import java.net.URL;
import java.net.MalformedURLException;

public class Application {
    public static void main(String[] args) throws MalformedURLException {
        // Rule: openjdk25--to-openjdk25+-00700
        URL url = new URL("https://example.com");
    }
}
