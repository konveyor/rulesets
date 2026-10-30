package com.example;

// Rule: openjdk21-to-openjdk25+-00500
import java.util.logging.LoggingPermission;

// Rule: openjdk21-to-openjdk25+-00510
import javax.management.MBeanPermission;

// Rule: openjdk21-to-openjdk25+-00520
import javax.management.MBeanServerPermission;

// Rule: openjdk21-to-openjdk25+-00530
import javax.management.MBeanTrustPermission;

// Rule: openjdk21-to-openjdk25+-00540
import javax.management.remote.SubjectDelegationPermission;

// Rule: openjdk21-to-openjdk25+-00550
import java.security.UnresolvedPermission;

// Rule: openjdk21-to-openjdk25+-00590
import javax.security.auth.kerberos.DelegationPermission;

// Rule: openjdk21-to-openjdk25+-00600
import javax.security.auth.kerberos.ServicePermission;

public class Application {
    public static void main(String[] args) {
        // Rule: openjdk21-to-openjdk25+-00500
        LoggingPermission lp = new LoggingPermission("control", "");

        // Rule: openjdk21-to-openjdk25+-00510
        MBeanPermission mp = null;

        // Rule: openjdk21-to-openjdk25+-00520
        MBeanServerPermission msp = null;

        // Rule: openjdk21-to-openjdk25+-00530
        MBeanTrustPermission mtp = null;

        // Rule: openjdk21-to-openjdk25+-00540
        SubjectDelegationPermission sdp = null;

        // Rule: openjdk21-to-openjdk25+-00550
        UnresolvedPermission up = null;

        // Rule: openjdk21-to-openjdk25+-00590
        DelegationPermission dp = null;

        // Rule: openjdk21-to-openjdk25+-00600
        ServicePermission sp = null;
    }
}
