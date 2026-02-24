package com.example.apps;

import jakarta.json.bind.annotation.JsonbCreator;

public class JakartaJsonBinding {
    public String value;

    public JakartaJsonBinding(String value) {}

    @JsonbCreator
    public String createJson() {
        return "";
    }
}