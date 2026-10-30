package com.example.ws;

import jakarta.jws.WebService;

// Triggers quarkus-rh-unsupported-extensions-00020 (JAX-WS / SOAP)
@WebService
public class SoapService {

    public String hello(String name) {
        return "Hello " + name;
    }
}
