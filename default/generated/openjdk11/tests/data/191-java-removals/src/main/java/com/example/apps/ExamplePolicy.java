package com.example.apps;

import javax.security.auth.Policy;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;

public class ExamplePolicy {


    public static void main(String[] args) {

        CodeSource source;

        try {
            source = new CodeSource(new URL("file:/c:/*"), (java.security.cert.Certificate[]) null);

            Policy policy = Policy.getPolicy();
            System.out.println(policy.getPermissions(null, source));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}