package org.konveyor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/some/greeting")
    public String greeting() {
        return "Hello";
    }

    @GetMapping("/some/greeting/trail/")
    public String greetingWithTrail() {
        return "Hello";
    }
}
