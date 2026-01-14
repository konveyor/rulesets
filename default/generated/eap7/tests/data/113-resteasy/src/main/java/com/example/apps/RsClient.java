package com.example.apps;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
//import org.jboss.resteasy.client.LinkHeader;
import org.jboss.resteasy.client.EntityTypeFactory;
import org.jboss.resteasy.client.ClientResponseFailure;
import org.jboss.resteasy.spi.Link;

//import static org.jboss.resteasy.test.TestPortProvider.generateURL;


public class RsClient {


    public static void main(String[] args) throws Exception
    {
         
          ClientRequest request = new ClientRequest("");
          request.addLink("previous chapter", "previous", "http://example.com/TheBook/chapter2", null);
          ClientResponse response = request.post();
//          LinkHeader header = response.getLinkHeader();
    }
    
}