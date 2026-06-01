package com.example;

import java.beans.PropertyChangeListener;
import java.util.logging.LogManager;

public class Application {
    public static void main(String[] args) {
        LogManager logManager = LogManager.getLogManager();
        PropertyChangeListener listener = evt -> {};

        // Rule: openjdk25--to-openjdk25+-00550
        logManager.addPropertyChangeListener(listener);

        // Rule: openjdk25--to-openjdk25+-00560
        logManager.removePropertyChangeListener(listener);
    }
}
