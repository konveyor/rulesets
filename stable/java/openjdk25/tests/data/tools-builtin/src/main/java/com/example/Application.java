package com.example;

// Rule: openjdk25--to-openjdk25+-01310
// This file references com.sun.management.snmp package
import com.sun.management.snmp.SnmpDefinitions;

public class Application {
    public static void main(String[] args) {
        // Using com.sun.management.snmp classes
        System.out.println("SNMP monitoring");
    }
}
