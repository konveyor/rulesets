package com.example;

// Rule: openjdk25--to-openjdk25+-00250
import javax.management.remote.rmi.RMIIIOPServerImpl;

// Rule: openjdk25--to-openjdk25+-00300
// (PACKAGE pattern: java.rmi.activation* - matched by imports below)

// Rule: openjdk25--to-openjdk25+-01140
import java.rmi.activation.Activatable;

// Rule: openjdk25--to-openjdk25+-01150
import java.rmi.activation.ActivationDesc;

// Rule: openjdk25--to-openjdk25+-01160
import java.rmi.activation.ActivationGroup;

// Rule: openjdk25--to-openjdk25+-01170
import java.rmi.activation.ActivationGroupDesc;

// Rule: openjdk25--to-openjdk25+-01180
import java.rmi.activation.ActivationGroupID;

// Rule: openjdk25--to-openjdk25+-01190
import java.rmi.activation.ActivationID;

public class Application {
    public static void main(String[] args) {
        // Rule: openjdk25--to-openjdk25+-00250
        RMIIIOPServerImpl server = null;

        // Rule: openjdk25--to-openjdk25+-01140
        Activatable activatable = null;

        // Rule: openjdk25--to-openjdk25+-01150
        ActivationDesc desc = null;

        // Rule: openjdk25--to-openjdk25+-01160
        ActivationGroup group = null;

        // Rule: openjdk25--to-openjdk25+-01170
        ActivationGroupDesc groupDesc = null;

        // Rule: openjdk25--to-openjdk25+-01180
        ActivationGroupID groupId = null;

        // Rule: openjdk25--to-openjdk25+-01190
        ActivationID activationId = null;
    }
}
