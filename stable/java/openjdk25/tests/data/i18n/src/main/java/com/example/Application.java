package com.example;

// Rule: openjdk25--to-openjdk25+-00890
import java.text.SimpleDateFormat;

// Rule: openjdk25--to-openjdk25+-00900
import java.text.DateFormat;

import java.nio.charset.Charset;
import java.util.Locale;

public class Application {
    public static void main(String[] args) {
        // Rule: openjdk25--to-openjdk25+-00710
        Locale locale = new Locale("en", "US");

        // Rule: openjdk25--to-openjdk25+-00880
        Charset cs = Charset.defaultCharset();
    }
}
