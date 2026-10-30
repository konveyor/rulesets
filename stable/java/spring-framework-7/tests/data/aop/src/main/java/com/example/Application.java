package com.example;

import org.springframework.scheduling.annotation.Async;

public class Application {
    public static void main(String[] args) {
        new AsyncService().processAsync();
    }
}

class AsyncService {
    // Rule: aop-annotation-00010
    @Async
    public void processAsync() {
        System.out.println("Processing asynchronously");
    }
}
