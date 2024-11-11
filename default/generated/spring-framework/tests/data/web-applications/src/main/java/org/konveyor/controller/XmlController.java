package org.konveyor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

@Controller
public class XmlController {

    @GetMapping("/xml")
    @ResponseBody
    public Source getXmlData() {
        String xmlData =
                "<greeting>\n" +
                    "<message>Hello, world!</message>\n" +
                "</greeting>\n";

        return new StreamSource(new StringReader(xmlData));
    }
}
