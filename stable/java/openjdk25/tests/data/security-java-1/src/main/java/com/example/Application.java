package com.example;

import java.lang.System;

// Rule: openjdk21-to-openjdk25+-00270
import java.lang.SecurityManager;

// Rule: openjdk21-to-openjdk25+-00280
import java.security.AccessController;

// Rule: openjdk21-to-openjdk25+-00290
import java.security.AccessControlContext;

// Rule: openjdk21-to-openjdk25+-00300
import java.security.Policy;

// Rule: openjdk21-to-openjdk25+-00390
import java.security.AllPermission;

// Rule: openjdk21-to-openjdk25+-00400
import java.io.FilePermission;

// Rule: openjdk21-to-openjdk25+-00410
import java.net.SocketPermission;

public class Application {
    public static void main(String[] args) {
        // Rule: openjdk21-to-openjdk25+-00310
        System.setSecurityManager(null);
    }
}
