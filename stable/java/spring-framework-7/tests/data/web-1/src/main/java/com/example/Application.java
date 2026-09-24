package com.example;

// Rule: web-import-00010
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
// Rule: web-import-00020
import org.springframework.web.accept.ServletPathExtensionContentNegotiationStrategy;
// Rule: web-import-00030
import org.springframework.http.HttpHeaders;
// Rule: web-import-00040
import org.springframework.web.client.RestTemplate;
// Rule: web-import-00050
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
// Rule: web-import-00060
import org.springframework.util.PathMatcher;
// Rule: web-import-00070
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class Application {
    public static void main(String[] args) {
        PathExtensionContentNegotiationStrategy strategy1 = null;
        ServletPathExtensionContentNegotiationStrategy strategy2 = null;
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.json();
        PathMatcher matcher = null;
        HandlerMappingIntrospector introspector = null;

        // Rule: web-method-00010
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
    }
}
