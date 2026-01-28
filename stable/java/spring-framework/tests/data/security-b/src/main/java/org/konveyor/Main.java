package org.konveyor;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.net.URI;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.client.RestTemplate;


public class Main {

    private static SecurityContextRepository securityContextRepository;

    public static void main(String[] args) throws Exception {
        RestTemplate rest = new RestTemplate();
        rest.getForObject(new URI("http://www.example.com/"), Source.class);
        rest.getForObject("http://www.example.com/", Source.class);
        rest.getForObject("http://www.example.com/", Source.class, "Hey");

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println(authentication.isAuthenticated());

        SecurityContextHolder.setContext(context);
        securityContextRepository.saveContext(context, null, null);


    }


    public Source getXmlData() {
        String xmlData =
                "<greeting>\n" +
                        "<message>Hello, world!</message>\n" +
                        "</greeting>\n";

        return new StreamSource(new StringReader(xmlData));
    }

}
