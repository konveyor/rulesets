package com.example;

// Rule: resilience-import-00010
import org.springframework.retry.support.RetryTemplate;

// Rule: resilience-import-00020
import org.springframework.retry.RetryPolicy;

public class Application {
    public static void main(String[] args) {
        // Rule: resilience-import-00010
        RetryTemplate retryTemplate = null;

        // Rule: resilience-import-00020
        RetryPolicy retryPolicy = null;
    }
}
