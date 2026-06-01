package com.example;

// Rule: openjdk25--to-openjdk25+-00490
import java.lang.SecurityManager;
import java.lang.System;

// Rule: openjdk25--to-openjdk25+-00720
import java.security.spec.PSSParameterSpec;

// Rule: openjdk25--to-openjdk25+-01120
// (System.getSecurityManager is a static method on java.lang.System)

// Rule: openjdk25--to-openjdk25+-01240
import java.security.acl.Acl;

// Rule: openjdk25--to-openjdk25+-01250
import java.security.acl.AclEntry;

// Rule: openjdk25--to-openjdk25+-01260
import java.security.acl.Group;

// Rule: openjdk25--to-openjdk25+-01270
import java.security.acl.Owner;

// Rule: openjdk25--to-openjdk25+-01280
import java.security.acl.Permission;

public class Application {
    public static void main(String[] args) {
        // Rule: openjdk25--to-openjdk25+-00490
        SecurityManager sm = new SecurityManager();
        sm.checkTopLevelWindow(new Object());

        // Rule: openjdk25--to-openjdk25+-00720
        PSSParameterSpec pssSpec = new PSSParameterSpec(20);

        // Rule: openjdk25--to-openjdk25+-01120
        SecurityManager mgr = System.getSecurityManager();

        // Rule: openjdk25--to-openjdk25+-01240
        Acl acl = null;

        // Rule: openjdk25--to-openjdk25+-01250
        AclEntry entry = null;

        // Rule: openjdk25--to-openjdk25+-01260
        Group group = null;

        // Rule: openjdk25--to-openjdk25+-01270
        Owner owner = null;

        // Rule: openjdk25--to-openjdk25+-01280
        Permission permission = null;
    }
}
