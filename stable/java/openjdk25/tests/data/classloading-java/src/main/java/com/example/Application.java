package com.example;

import java.net.URLClassLoader;
import java.net.URL;

public class Application {
    public static void main(String[] args) throws Exception {
        // Rule: openjdk25--to-openjdk25+-00920
        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        URL[] urls = classLoader.getURLs();
    }
}
