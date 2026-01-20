package com.example.apps;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.interceptors.encoding.*;

/**
 * JUnit4 Test class which makes a request to the RESTful helloworld-rs web service.
 *
 * @author bmincey (Blaine Mincey)
 */
public class JaxRsClient {
    /**
     * Request URLs pulled from system properties in pom.xml
     */
    private static String XML_URL;
    private static String JSON_URL;

    /**
     * Property names used to pull values from system properties in pom.xml
     */
    private static final String XML_PROPERTY = "xmlUrl";
    private static final String JSON_PROPERTY = "jsonUrl";

    /**
     * Responses of the RESTful web service
     */
    private static final String XML_RESPONSE = "<xml><result>Hello World!</result></xml>";
    private static final String JSON_RESPONSE = "{\"result\":\"Hello World!\"}";

    /**
     * Method executes BEFORE the test method. Values are read from system properties that can be modified in the pom.xml.
     */

    public static void main(String[] args) {
        JaxRsClient.XML_URL = System.getProperty(JaxRsClient.XML_PROPERTY);
        JaxRsClient.JSON_URL = System.getProperty(JaxRsClient.JSON_PROPERTY);
        new JaxRsClient().test();
    }

    /**
     * Test method which executes the runRequest method that calls the RESTful helloworld-rs web service.
     */
    void test() {
        String response = runRequest(JaxRsClient.XML_URL, MediaType.APPLICATION_XML_TYPE);

        if (!JaxRsClient.XML_RESPONSE.equals(response)) {
            throw new RuntimeException("Response is wrong:\nXML Response:" + response + "\nshould be: " + XML_RESPONSE);
        }
        response = runRequest(JaxRsClient.JSON_URL, MediaType.APPLICATION_JSON_TYPE);

        if (!JaxRsClient.JSON_RESPONSE.equals(response)) {
            throw new RuntimeException("Response is wrong:\nJSON Response:" + response + "\nshould be: " + JSON_RESPONSE);
        }

    }

    private String runRequest(String jsonUrl, MediaType applicationJsonType) {
        return null;
    }

    /**
     * The purpose of this method is to run the external REST request.
     *
     * @param url       The url of the RESTful service
     * @param mediaType The mediatype of the RESTful service
     */

}
