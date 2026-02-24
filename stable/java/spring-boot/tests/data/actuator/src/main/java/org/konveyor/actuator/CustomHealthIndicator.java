package org.konveyor.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Test class demonstrating actuator customization.
 */
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up()
            .withDetail("custom", "All systems operational");
    }
}

@Component
@Endpoint(id = "customendpoint")
class CustomActuatorEndpoint {
    
    @ReadOperation
    public Map<String, Object> customInfo() {
        return Map.of("status", "ok", "version", "1.0");
    }
}
