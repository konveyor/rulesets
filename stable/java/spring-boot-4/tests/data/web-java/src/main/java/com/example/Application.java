package com.example;

// Rule: web-change-00010
import org.springframework.boot.http.converter.autoconfigure.HttpMessageConverters;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class Application {
    public static void main(String[] args) {
        // Rule: web-change-00010 - TYPE reference to HttpMessageConverters
        HttpMessageConverters converters = new HttpMessageConverters(
            new MappingJackson2HttpMessageConverter()
        );
    }
}
