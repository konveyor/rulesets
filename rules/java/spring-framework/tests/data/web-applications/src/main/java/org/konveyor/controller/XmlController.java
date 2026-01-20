package org.konveyor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

@Controller
public class XmlController {

    @PostMapping("/process-xml")
    @ResponseBody
    public Source processXml(@RequestBody String xmlInput) {
        // For example, log the input or perform some processing here
        System.out.println("Received XML: " + xmlInput);

        // Assuming we want to respond with a modified version of the XML
        String responseXml = "<response><status>Success</status><originalMessage>%s</originalMessage></response>";

        // Return the new XML wrapped in a StreamSource
        return new StreamSource(new StringReader(responseXml));
    }

    @GetMapping(value = "/xml/", produces = "application/xml")
    public Source getXmlData() {
        // Predefined XML string
        String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><greeting><message>Hello, World!</message><timestamp>%s</timestamp></greeting>";

        // Return as Source
        return new StreamSource(new StringReader(xmlContent));
    }
}
