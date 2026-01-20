package com.example.apps;// Contains code that should match for the following tests:
//
// weblogic-webapp-04000-test
//

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import weblogic.servlet.annotation.WLFilter;
import weblogic.servlet.annotation.WLInitParam;

import java.io.IOException;

/**
 * This is an example of a servlet that uses the propietary
 * @WLFilter and @WLInitParam annotations.
 *
 * This is not a fully functional class. Its sole purpose is
 * to demonstrate Windup rule addon processing.
 *
 * @author Windup-Team
 */
@WLFilter (
    name = "MyHintsRuleFilter",
    initParams = {
        @WLInitParam (name="catalog", value="spring"),
        @WLInitParam (name="language", value="English")
     },
     mapping = {"/catalog/*"}
)
public class SampleWebLogicFilter implements Filter {
     @Override
     public void init(FilterConfig filterConfig) throws ServletException {

     }

     @Override
     public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

     }

     @Override
     public void destroy() {

     }
     // no-op
}
