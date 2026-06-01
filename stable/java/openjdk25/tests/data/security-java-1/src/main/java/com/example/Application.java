package com.example;

// Rule: openjdk25--to-openjdk25+-00010
import java.lang.SecurityManager;
import java.lang.System;

// Rule: openjdk25--to-openjdk25+-00350
// (PACKAGE pattern: java.security.acl* - matched by import below)
import java.security.acl.Acl;

// Rule: openjdk25--to-openjdk25+-00440
import javax.security.auth.Policy;

// Rule: openjdk25--to-openjdk25+-00090
import java.security.KeyStore;

public class Application {
    public static void main(String[] args) {
        // Rule: openjdk25--to-openjdk25+-00010
        SecurityManager sm = new SecurityManager();

        // Rule: openjdk25--to-openjdk25+-00070
        System.setSecurityManager(sm);

        // Rule: openjdk25--to-openjdk25+-00090
        String defaultType = KeyStore.getDefaultType();

        // Rule: openjdk25--to-openjdk25+-00350
        Acl acl = null;

        // Rule: openjdk25--to-openjdk25+-00440
        Policy policy = null;

        // Rule: openjdk25--to-openjdk25+-00460
        sm.checkAwtEventQueueAccess();

        // Rule: openjdk25--to-openjdk25+-00470
        sm.checkMemberAccess(String.class, 0);

        // Rule: openjdk25--to-openjdk25+-00480
        sm.checkSystemClipboardAccess();
    }
}
