package org.konveyor;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class Main {

    public static void main(String[] args) {
        RestTemplate rest = new RestTemplate();
        rest.getForEntity(URI.create("http://www.example.com/"), Object.class);

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    }

}
