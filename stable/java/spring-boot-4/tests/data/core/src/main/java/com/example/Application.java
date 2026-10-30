package com.example;

// Rule: core-import-00010
import org.springframework.boot.BootstrapRegistry;
// Rule: core-import-00020
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.context.properties.bind.PropertyMapper;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.boot.SpringApplication;

public class Application implements EnvironmentPostProcessor {

    public static void main(String[] args) {
        // Rule: core-method-00010
        PropertyMapper mapper = PropertyMapper.get();
        mapper.from(() -> "value").alwaysApplyingWhenNonNull();
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        BootstrapRegistry registry = null;
    }
}
