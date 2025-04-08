package org.konveyor;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

public class SecurityContextRepositoryImpl implements SecurityContextRepository {
    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        return null;
    }

    @Override
    public void saveContext(SecurityContext context, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {

    }

    @Override
    public boolean containsContext(javax.servlet.http.HttpServletRequest request) {
        return false;
    }
}
