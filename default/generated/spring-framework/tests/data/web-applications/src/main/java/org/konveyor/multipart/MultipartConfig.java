package org.konveyor.multipart;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Test class demonstrating usage of removed CommonsMultipartResolver.
 * Should be migrated to StandardServletMultipartResolver in Spring 6.
 */
@Configuration
public class MultipartConfig {

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(10485760); // 10MB
        resolver.setMaxUploadSizePerFile(5242880); // 5MB
        return resolver;
    }

    public void processFile(CommonsMultipartFile file) {
        // Process uploaded file
        System.out.println("Processing file: " + file.getOriginalFilename());
    }
}
