package org.konveyor.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.Filter;
import javax.servlet.FilterChain;

/**
 * Test class demonstrating javax.servlet usage that must be migrated to jakarta.servlet
 * for Spring Boot 3.0 compatibility.
 */
public class JakartaEEMigration implements Filter {

    @Override
    public void doFilter(javax.servlet.ServletRequest request, 
                        javax.servlet.ServletResponse response, 
                        FilterChain chain) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // Filter logic
    }
}
