package com.jboss.windup.test;

import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
// Commented out javax.xml.registry - not commonly available
// import javax.xml.registry.BulkResponse;
// import javax.xml.registry.BusinessLifeCycleManager;
// import javax.xml.registry.Connection;
// import javax.xml.registry.ConnectionFactory;
// import javax.xml.registry.JAXRException;
// import javax.xml.registry.RegistryService;
// import javax.xml.registry.infomodel.Key;
// import javax.xml.registry.infomodel.Organization;
import javax.validation.constraints.NotNull;


@WebService()
class Hello {
    @NotNull
    private String myAttribute;
    
    private String message = new String("Hello, ");

    public void Hello() {}

    @WebMethod()
    public String sayHello(String name) {
        return message + name + ".";
    }
    
    @PostConstruct
    public void postConstruct() {
        try {
            JAXBContext jc = JAXBContext.newInstance( "com.acme.foo" );
            // Commented out javax.xml.registry usage
            // javax.xml.registry.ConnectionFactory connectionFactory = javax.xml.registry.ConnectionFactory.newInstance();
            System.out.println("nothing");
        } catch (Exception e) {
            // Ignore for compilation
        }
    }


    
} 