package com.example.apps;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class HelloWorldController {

    private Controller controller;

    @RequestMapping("/helloWorld")
    public String hello() {
        return "hello";
    }
}
