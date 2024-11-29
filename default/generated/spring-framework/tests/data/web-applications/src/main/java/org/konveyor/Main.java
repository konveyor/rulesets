package org.konveyor;

import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Source;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws URISyntaxException {
        RestTemplate rest = new RestTemplate();
        rest.getForObject(new URI("http://www.example.com/"), Source.class);
        rest.getForObject("http://www.example.com/", Source.class);
        rest.getForObject("http://www.example.com/", Source.class, "Hey");

        java.lang.System.getenv();
    }

}
