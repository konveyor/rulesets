package com.example.apps;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PostProcessInterceptor;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
//import org.jboss.resteasy.client.core.executors.ApacheHttpClientExecutor;
//import org.jboss.resteasy.plugins.cache.server.SimpleServerCache;

import org.jboss.resteasy.spi.interception.AcceptedByMethod;

import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class MyInterceptor implements PostProcessInterceptor, PreProcessInterceptor, AcceptedByMethod
{

    @Override
    public boolean accept(Class declaring, Method method)
    {
        for (Annotation[] annotations : method.getParameterAnnotations())
        {
            for (Annotation annotation : annotations)
            {
                if (annotation.annotationType() == QueryParam.class)
                {
                    QueryParam queryParam = (QueryParam) annotation;
                    return queryParam.value().equals("name");
                }
            }
        }
        return false;
    }


    @Override
    public ServerResponse preProcess(HttpRequest request, ResourceMethod methodInvoked) throws Failure, WebApplicationException {
        return null;
    }

    @Override
    public void postProcess(ServerResponse serverResponse) {

    }
}
