package com.example.apps;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

public class HelloWorldController {

    @RequestMapping("/helloWorld")
    public String hello() {
        WebContentInterceptor webContentInterceptor = new org.springframework.web.servlet.mvc.WebContentInterceptor();
        Controller controller = new ControllerImpl();
        return "hello";
    }

    private static class ControllerImpl implements Controller {

        @Override
        public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
            return null;
        }
    }
}
