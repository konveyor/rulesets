package com.example;

// Rule: data-annotation-00010
import org.springframework.boot.autoconfigure.domain.EntityScan;
// Rule: data-change-00010
import org.springframework.boot.autoconfigure.elasticsearch.RestClientBuilderCustomizer;

@EntityScan(basePackages = "com.example")
public class Application {

    public static void main(String[] args) {
        // Rule: data-change-00010
        RestClientBuilderCustomizer customizer = null;
    }
}
