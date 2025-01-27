package org.konveyor.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;

@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class Config {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Bean
    public AuthenticationManagerResolver<ServerHttpRequest> authenticationManagerResolver() {
        // Custom AuthenticationManagerResolver configuration
        return new AuthenticationManagerResolver<ServerHttpRequest>() {
            @Override
            public AuthenticationManager resolve(ServerHttpRequest context) {
                return null;
            }
        };
    }
}
