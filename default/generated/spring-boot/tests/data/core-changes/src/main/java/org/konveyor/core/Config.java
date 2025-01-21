package org.konveyor.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class Config {

    @Autowired
    private ApplicationProperties applicationProperties;
}
