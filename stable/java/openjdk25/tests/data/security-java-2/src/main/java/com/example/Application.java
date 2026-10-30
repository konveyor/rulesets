package com.example;

// Rule: openjdk21-to-openjdk25+-00420
import java.lang.RuntimePermission;

// Rule: openjdk21-to-openjdk25+-00430
import java.lang.reflect.ReflectPermission;

// Rule: openjdk21-to-openjdk25+-00440
import java.util.PropertyPermission;

// Rule: openjdk21-to-openjdk25+-00450
import java.net.NetPermission;

// Rule: openjdk21-to-openjdk25+-00460
import java.io.SerializablePermission;

// Rule: openjdk21-to-openjdk25+-00470
import javax.security.auth.AuthPermission;

// Rule: openjdk21-to-openjdk25+-00480
import javax.net.ssl.SSLPermission;

// Rule: openjdk21-to-openjdk25+-00490
import java.sql.SQLPermission;

public class Application {
    public static void main(String[] args) {
        // Imports above trigger the rules
    }
}
