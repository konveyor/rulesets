package com.example.apps;

import jakarta.ws.rs.core.Context;

public class AClass {

    @Context
    private String element;

    public AClass(String element) {
        this.element = element;
    }

    public String get() {
        return element;
    }
}
