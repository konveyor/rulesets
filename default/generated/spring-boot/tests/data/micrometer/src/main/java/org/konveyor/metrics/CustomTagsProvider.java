package org.konveyor.metrics;

import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTagsProvider;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTagsContributor;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.jvm.JvmInfoMetrics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Test class demonstrating deprecated Micrometer tag providers.
 * Must be migrated to ObservationConvention for Spring Boot 3.0.
 */
public class CustomTagsProvider implements WebMvcTagsProvider {

    @Override
    public Iterable<Tag> getTags(HttpServletRequest request, 
                                  HttpServletResponse response,
                                  Object handler, 
                                  Throwable exception) {
        return Tags.of("custom", "value");
    }
    
    // Manual JvmInfoMetrics configuration - no longer needed in Spring Boot 3.0
    public JvmInfoMetrics jvmInfoMetrics() {
        return new JvmInfoMetrics();
    }
}
