package com.example.apps;

import javax.xml.soap.SOAPElementFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPException;

public class JakartaSOAP {

    public static void main(String[] args) throws SOAPException {
        SOAPElementFactory factory = SOAPElementFactory.newInstance();
        
        Name name = null;
        factory.create(name);
        factory.create("name");
        factory.create("name", "name", "name");
    }
}