package com.example;

import javax.crypto.Cipher;

public class Application {

    public static void main(String[] args) throws Exception {
        // Rule: security-method-00010
        // In JDK 7, getMaxAllowedKeyLength returns 128 without JCE unlimited policy files.
        // In JDK 8u161+, it returns Integer.MAX_VALUE.
        int maxKeyLength = Cipher.getMaxAllowedKeyLength("AES");
        if (maxKeyLength < 256) {
            System.out.println("Limited JCE policy: truncating to 128-bit key");
        } else {
            System.out.println("Unlimited JCE policy active");
        }
    }
}
