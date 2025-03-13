package org.konveyor.controller.MockTestRule;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.mock.web.MockHttpServletRequest;
// import org.springframework.mock.web.MockHttpSession;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }
}