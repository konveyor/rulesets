package com.example.apps;

import javax.ws.rs.ext.Provider;
import org.jboss.resteasy.spi.interception.MessageBodyWriterInterceptor;
import org.jboss.resteasy.spi.interception.MessageBodyReaderInterceptor;
import org.jboss.resteasy.spi.interception.MessageBodyWriterContext;
import org.jboss.resteasy.spi.interception.MessageBodyReaderContext;
import org.jboss.resteasy.annotations.interception.ServerInterceptor;

import javax.ws.rs.WebApplicationException;
import java.io.IOException;

@Provider
@ServerInterceptor
public class MyHeaderInterceptor implements MessageBodyWriterInterceptor, MessageBodyReaderInterceptor {

    public void write(MessageBodyWriterContext context) throws IOException, WebApplicationException
    {
       context.getHeaders().add("My-Header", "custom");
       context.proceed();
    }
    
    public Object read(MessageBodyReaderContext context) throws IOException, WebApplicationException
    {
       //todo
        return null;
    }
}
