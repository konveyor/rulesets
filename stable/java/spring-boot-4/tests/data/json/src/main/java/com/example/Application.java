package com.example;

// Rule: json-import-00010
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

// Rule: json-import-00020
import org.springframework.boot.jackson.JsonObjectSerializer;

// Rule: json-import-00030
import org.springframework.boot.jackson.JsonValueDeserializer;

// Rule: json-import-00040
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;

// Rule: json-annotation-00010
import org.springframework.boot.jackson.JsonComponent;

// Rule: json-annotation-00020
import org.springframework.boot.jackson.JsonMixin;

public class Application {
    // Rule: json-annotation-00010
    @JsonComponent
    static class MyComponent {
    }

    // Rule: json-annotation-00020
    @JsonMixin(type = String.class)
    static class MyMixin {
    }

    public static void main(String[] args) {
    }
}
