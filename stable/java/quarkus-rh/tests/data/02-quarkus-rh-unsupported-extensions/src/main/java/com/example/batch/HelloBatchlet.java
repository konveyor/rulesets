package com.example.batch;

import jakarta.batch.api.Batchlet;

// Triggers quarkus-rh-unsupported-extensions-00030 (Jakarta Batch / JSR-352)
public class HelloBatchlet implements Batchlet {

    @Override
    public String process() {
        return "COMPLETED";
    }

    @Override
    public void stop() {
    }
}
