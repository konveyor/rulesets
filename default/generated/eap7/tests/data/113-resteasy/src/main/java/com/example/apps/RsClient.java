package com.example.apps;

import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientRequestFactory;
import org.jboss.resteasy.client.ClientResponse;
//import org.jboss.resteasy.client.LinkHeader;
import org.jboss.resteasy.client.EntityTypeFactory;
import org.jboss.resteasy.client.ClientResponseFailure;
import org.jboss.resteasy.client.cache.CacheEntry;
import org.jboss.resteasy.core.interception.InterceptorRegistry;
import org.jboss.resteasy.core.interception.InterceptorRegistryListener;
import org.jboss.resteasy.spi.Link;
import org.jboss.resteasy.spi.MethodNotAllowedException;
import org.jboss.resteasy.spi.NotAcceptableException;
import org.jboss.resteasy.spi.NotFoundException;
import org.jboss.resteasy.spi.Registry;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.resteasy.spi.UnauthorizedException;
import org.jboss.resteasy.spi.UnsupportedMediaTypeException;
import org.jboss.resteasy.spi.interception.ClientExecutionContext;
import org.jboss.resteasy.spi.interception.ClientExecutionInterceptor;

//import static org.jboss.resteasy.test.TestPortProvider.generateURL;


public class RsClient {


    public static void main(String[] args) throws Exception
    {
         
          ClientRequest request = new ClientRequest("");
          request.addLink("previous chapter", "previous", "http://example.com/TheBook/chapter2", null);
          ClientResponse response = request.post();
//          LinkHeader header = response.getLinkHeader();
        ResteasyProviderFactory providerFactory = ResteasyProviderFactory.getInstance();
        providerFactory.addClientExceptionMapper(null, null);

        UnsupportedMediaTypeException unsupportedMediaTypeException = new UnsupportedMediaTypeException("Unsupported Media Type");
        ClientExecutionContext executionContext = null;
        executionContext.getRequest();
        NotAcceptableException notAcceptableException = new NotAcceptableException("Not Acceptable");
        CacheEntry cacheEntry = null;
        UnauthorizedException unauthorizedException = new UnauthorizedException("Unauthorized");
        ClientExecutor executor = null;
        executor.close();

        InterceptorRegistryListener interceptorRegistryListener = null;
        InterceptorRegistry interceptorRegistry = null;
        Registry registry = null;
        NotFoundException notFoundException = new NotFoundException("Not Found");
        ClientRequestFactory requestFactory = null;
        ClientExecutionInterceptor interceptor = null;
        MethodNotAllowedException methodNotAllowedException = new MethodNotAllowedException("Method Not Allowed");
    }
    
}