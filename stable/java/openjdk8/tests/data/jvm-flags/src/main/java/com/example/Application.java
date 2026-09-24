package com.example;

public class Application {
    public static void main(String[] args) {
        // Rule: jvm-flags-pattern-00010
        String flag1 = "-Xverify:none";
        // Rule: jvm-flags-pattern-00020
        String flag2 = "-XX:+UseParNewGC";
        // Rule: jvm-flags-pattern-00030
        String flag3 = "-XX:+CMSIncrementalMode";
        System.out.println(flag1 + " " + flag2 + " " + flag3);
    }
}
