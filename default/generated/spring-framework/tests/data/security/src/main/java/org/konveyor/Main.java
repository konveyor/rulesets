package org.konveyor;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.net.URI;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.client.RestTemplate;

import java.xml.bind.*;

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

        HttpSecurity http = rest.getForObject("http://www.example.com/", HttpSecurity.class);
        http.securityContext().requireExplicitSave(false);

        http.formLogin(Customizer.withDefaults());


    }


    public Source getXmlData() {
        String xmlData =
                "<greeting>\n" +
                        "<message>Hello, world!</message>\n" +
                        "</greeting>\n";

        return new StreamSource(new StringReader(xmlData));
    }

}
