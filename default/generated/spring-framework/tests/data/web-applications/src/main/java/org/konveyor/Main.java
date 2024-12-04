package org.konveyor;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.web.client.RestTemplate;

public class Main {

    public static void main(String[] args) throws URISyntaxException {
        RestTemplate rest = new RestTemplate();
        rest.getForObject(new URI("http://www.example.com/"), Source.class);
        rest.getForObject("http://www.example.com/", Source.class);
        rest.getForObject("http://www.example.com/", Source.class, "Hey");
    }

    public Source getXmlData() {
        String xmlData =
                "<greeting>\n" +
                        "<message>Hello, world!</message>\n" +
                        "</greeting>\n";

        return new StreamSource(new StringReader(xmlData));
    }

}
