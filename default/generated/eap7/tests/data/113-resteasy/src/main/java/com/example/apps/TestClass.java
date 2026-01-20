package com.example.apps;

import org.jboss.resteasy.client.core.executors.ApacheHttpClientExecutor;
import org.jboss.resteasy.annotations.cache.ServerCached;
import org.jboss.resteasy.plugins.delegates.ServerCookie;
import org.jboss.resteasy.core.ResteasyHttpServletResponseWrapper;
import org.jboss.resteasy.auth.oauth.OAuthValidator;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInputImpl;
import org.jboss.resteasy.plugins.providers.jaxb.JAXBContextWrapper;
import org.jboss.resteasy.core.ResteasyHttpServletResponseWrapper;
import org.jboss.resteasy.spi.ForbiddenException;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jboss.resteasy.core.ResteasyHttpServletRequestWrapper;
import org.jboss.resteasy.core.ResteasyHttpServletResponseWrapper;

@ServerCached
public class TestClass {
    public static void main(String[] args) {
        ServerCookie cookie = new ServerCookie();
        boolean b = cookie.checkName("");
        ServerCookie cookie2 = new ServerCookie();
        cookie2.maybeQuote(1, null, "");
        ResteasyHttpServletResponseWrapper responseWrapper = new ResteasyHttpServletResponseWrapper();
        responseWrapper.encodeRedirectUrl();
        responseWrapper.encodeUrl();
        responseWrapper.setStatus();
        MultipartFormDataInput multipartFormDataInput = new MultipartFormDataInput();
        multipartFormDataInput.getFormData();
        OAuthValidator validator = new OAuthValidator();
        validator.validateMessage();
        MultipartFormDataInputImpl multipartFormDataInputImpl = new MultipartFormDataInputImpl();
        multipartFormDataInputImpl.getFormData();
        ResteasyHttpServletRequestWrapper requestWrapper = new ResteasyHttpServletRequestWrapper();
        requestWrapper.isRequestedSessionIdFromUrl();
    }
}
