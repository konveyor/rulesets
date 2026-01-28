package org.konveyor;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class MethodSecurityExpressionHandlerImpl extends DefaultMethodSecurityExpressionHandler {
    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        return super.createSecurityExpressionRoot(authentication, invocation);
    }
}
