package com.example.apps;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.SingleThreadModel;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.HttpUtils;
import java.util.Enumeration;

public class SingleThreadModelImpl implements SingleThreadModel, HttpSessionContext {
    public static void main(String[] args) throws ServletException {
        SingleThreadModelImpl singleThreadModel = new SingleThreadModelImpl();
        HttpSession httpSession = singleThreadModel.getSession("session");
        HttpSessionContext httpSessionContext = httpSession.getSessionContext();

        HttpUtils.parseQueryString("query string");

        ServletContext context = httpSession.getServletContext();
        context.getServlet("servlet");
        context.getServlets();
        context.getServletNames();
        Exception e = new Exception();
        String exception = "exception";
        context.log(e, exception);

        handleRequest(null);
        handleHttpRequest(null);
        handleHttpResponse(null);
        handleHttpResponse((HttpServletResponse) null);
        handleHttpServletRequest(null);

        String str = "string";
        UnavailableException exception1 = new UnavailableException(str);
        Servlet s = exception1.getServlet();
        UnavailableException exception2 = new UnavailableException(s, str);
        UnavailableException exception3 = new UnavailableException(0, s, str);

        httpSession.getValue("value");
        httpSession.getValueNames();
        httpSession.putValue("key", "value");
        httpSession.removeValue("value");

        if (exception1.isPermanent()) {
            throw exception1;
        } else {
            if (exception2.isPermanent()) {
                throw exception2;
            } else {
                throw exception3;

            }
        }
    }

    public static void handleRequest(ServletRequestWrapper requestWrapper) {
        requestWrapper.getRealPath("string");
        ServletRequest request = requestWrapper.getRequest();
        request.getRealPath("string");
    }

    public static void handleHttpRequest(HttpServletRequestWrapper requestWrapper) {
        requestWrapper.getRealPath("string");
        requestWrapper.getRequest().getRealPath("string");
        requestWrapper.isRequestedSessionIdFromUrl();
    }

    public static void handleHttpServletRequest(HttpServletRequest httpServletRequest) {
        httpServletRequest.isRequestedSessionIdFromUrl();
    }

    public static void handleHttpResponse(HttpServletResponseWrapper responseWrapper) {
        responseWrapper.encodeUrl("string");
        responseWrapper.encodeRedirectUrl("string");
        responseWrapper.setStatus(0, "string");
    }

    public static void handleHttpResponse(HttpServletResponse response) {
        response.encodeUrl("string");
        response.encodeRedirectUrl("string");
        response.setStatus(0, "string");
    }

    @Override
    public HttpSession getSession(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getIds() {
        return null;
    }
}
