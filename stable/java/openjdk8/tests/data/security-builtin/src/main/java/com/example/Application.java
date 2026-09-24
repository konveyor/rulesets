package com.example;

public class Application {

    // Rule: security-pattern-00020
    // Legacy JCE policy JARs required before JDK 8u161
    private static final String JCE_POLICY_JAR = "local_policy.jar";
    private static final String JCE_EXPORT_JAR = "US_export_policy.jar";

    public static void main(String[] args) throws Exception {
        // Rule: security-pattern-00010
        // SSLv3 was used in JDK 7; disabled by default in JDK 8 due to POODLE
        javax.net.ssl.SSLContext context = javax.net.ssl.SSLContext.getInstance("SSLv3");
        System.out.println("policy: " + JCE_POLICY_JAR + ", " + JCE_EXPORT_JAR);
        System.out.println(context.getProtocol());
    }
}
