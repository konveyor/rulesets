package org.konveyor.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

@RestController
public class XmlRestController {

    public Source method(@RequestBody String xmlInput) {
        // For example, log the input or perform some processing here
        System.out.println("Received XML: " + xmlInput);

        // Assuming we want to respond with a modified version of the XML
        String responseXml = "<response><status>Success</status><originalMessage>%s</originalMessage></response>";

        // Return the new XML wrapped in a StreamSource
        return new StreamSource(new StringReader(responseXml));
    }


}
