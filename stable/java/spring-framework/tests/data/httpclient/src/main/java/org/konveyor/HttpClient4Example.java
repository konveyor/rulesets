package org.konveyor;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

/**
 * Test class demonstrating usage of Apache HttpClient 4.x.
 * Must be migrated to HttpClient 5.x for Spring 6.
 */
public class HttpClient4Example {

    private final CloseableHttpClient httpClient;

    public HttpClient4Example() {
        this.httpClient = HttpClients.createDefault();
    }

    public HttpClient4Example(HttpClientBuilder builder) {
        this.httpClient = builder.build();
    }

    public String fetchData(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return response.getStatusLine().toString();
        }
    }

    public String postData(String url) throws IOException {
        HttpPost request = new HttpPost(url);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return response.getStatusLine().toString();
        }
    }

    public void close() throws IOException {
        httpClient.close();
    }
}
