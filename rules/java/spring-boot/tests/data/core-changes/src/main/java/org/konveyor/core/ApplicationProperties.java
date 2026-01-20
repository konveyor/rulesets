package org.konveyor.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "example")
@ConstructorBinding
public class ApplicationProperties {
}
