package com.example;

import javax.naming.Context;
import java.util.Hashtable;

public class Application {
    public static void main(String[] args) {
        Hashtable<String, Object> env = new Hashtable<>();

        // Rule: openjdk21-to-openjdk25+-00290
        env.put(Context.APPLET, "myApplet");

        // Rule: openjdk21-to-openjdk25+-00300
        env.put("java.naming.rmi.security.manager", "true");

        // Rule: openjdk21-to-openjdk25+-00940
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
        boolean trust = Boolean.parseBoolean(System.getProperty("com.sun.jndi.ldap.object.trustURLCodebase"));
    }
}
