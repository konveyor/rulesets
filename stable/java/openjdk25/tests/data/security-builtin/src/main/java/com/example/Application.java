package com.example;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.spec.OAEPParameterSpec;

public class Application {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
        // Rule: openjdk25--to-openjdk25+-00080
        KeyFactory kf = KeyFactory.getInstance("RSA", "SunJSSE");

        // Rule: openjdk25--to-openjdk25+-00730
        OAEPParameterSpec oaepSpec = OAEPParameterSpec.DEFAULT;
    }
}
