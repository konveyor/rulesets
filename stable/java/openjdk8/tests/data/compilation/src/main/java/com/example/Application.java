package com.example;

import org.springframework.web.bind.annotation.RequestMapping;

public class Application {

    // Rule: compilation-annotation-00010
    @RequestMapping("/test")
    public void testMethod() {
    }

    public static void main(String[] args) {
    }
}
