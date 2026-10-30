package com.example;

import java.util.TimeZone;

public class Application {
    public static void main(String[] args) {
        // Locale options are configured in properties file
        System.out.println("Locale test application");

        // Rule: openjdk21-to-openjdk25+-00740
        TimeZone est = TimeZone.getTimeZone("EST");
        System.out.println(est.getDisplayName());
    }
}
