package com.example;

// Rule: openjdk25--to-openjdk25+-01200
import java.rmi.activation.ActivationInstantiator;

// Rule: openjdk25--to-openjdk25+-01210
import java.rmi.activation.ActivationMonitor;

// Rule: openjdk25--to-openjdk25+-01220
import java.rmi.activation.ActivationSystem;

// Rule: openjdk25--to-openjdk25+-01230
import java.rmi.activation.Activator;

public class Application {
    public static void main(String[] args) {
        // Rule: openjdk25--to-openjdk25+-01200
        ActivationInstantiator instantiator = null;

        // Rule: openjdk25--to-openjdk25+-01210
        ActivationMonitor monitor = null;

        // Rule: openjdk25--to-openjdk25+-01220
        ActivationSystem system = null;

        // Rule: openjdk25--to-openjdk25+-01230
        Activator activator = null;
    }
}
