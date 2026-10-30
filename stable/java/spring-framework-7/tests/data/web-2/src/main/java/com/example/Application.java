package com.example;

// Rule: web-import-00080
import org.springframework.web.reactive.function.client.WebClient;
// Rule: web-import-00090
import org.springframework.util.AntPathMatcher;
// Rule: web-import-00100
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class Application {
    public static void main(String[] args) {
        WebClient webClient = WebClient.create();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        WebMvcConfigurer configurer = null;
    }
}
