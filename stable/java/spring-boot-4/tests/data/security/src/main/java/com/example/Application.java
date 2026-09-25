package com.example;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;

public class Application {
    public static void main(String[] args) {
        // Rule: security-method-00010
        PathRequest.StaticResourceRequest req = PathRequest.toStaticResources();
    }
}
