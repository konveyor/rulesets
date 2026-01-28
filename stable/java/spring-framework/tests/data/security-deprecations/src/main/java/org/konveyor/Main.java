package org.konveyor;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.net.URI;
import java.util.Map;

import org.springframework.security.config.annotation.web.configurers.oauth2.client.ImplicitGrantConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.endpoint.AbstractOAuth2AuthorizationGrantRequest;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.endpoint.NimbusAuthorizationCodeTokenResponseClient;


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


        ClientRegistration registration = rest.getForObject("http://www.example.com/", ClientRegistration.class);
        String redirectUri = registration.getRedirectUriTemplate();


        ClientRegistration registration2 = ClientRegistration.withRegistrationId("my-client")
                .clientId("client-id")
                .clientSecret("secret")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}") // Deprecated
                .authorizationUri("https://example.com/oauth2/authorize")
                .tokenUri("https://example.com/oauth2/token")
                .build();


        OAuth2AccessTokenResponseHttpMessageConverter converter = new OAuth2AccessTokenResponseHttpMessageConverter();

        // Deprecated in 5.8
        converter.setTokenResponseConverter((Map<String, String> tokenResponseParameters) -> {
            // convert and return OAuth2AccessTokenResponse
            return OAuth2AccessTokenResponse.withToken("token").build();
        });
        converter.setTokenResponseParametersConverter((Map) -> {
            return null;
        });

        AbstractOAuth2AuthorizationGrantRequest request =
                new AbstractOAuth2AuthorizationGrantRequest(AuthorizationGrantType.CLIENT_CREDENTIALS) {
                    // Implement required abstract methods if needed
                };


        String jwkSetUri = "https://example.com/oauth2/jwks";

        ImplicitGrantConfigurer implicitGrantConfigurer = new ImplicitGrantConfigurer<>();

        ClientRegistration registration3 = ClientRegistration.withRegistrationId("my-client")
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .build();

        ClientRegistration registration4 = ClientRegistration.withRegistrationId("my-client")
                .clientAuthenticationMethod(ClientAuthenticationMethod.POST)
                .build();

        HttpSessionOAuth2AuthorizationRequestRepository authorizationRequestRepository = new HttpSessionOAuth2AuthorizationRequestRepository();
        authorizationRequestRepository.setAllowMultipleAuthorizationRequests(false);

        converter.setTokenResponseConverter(null);
        converter.setTokenResponseParametersConverter(null);

        NimbusAuthorizationCodeTokenResponseClient client = new NimbusAuthorizationCodeTokenResponseClient();
    }


    public Source getXmlData() {
        String xmlData =
                "<greeting>\n" +
                        "<message>Hello, world!</message>\n" +
                        "</greeting>\n";

        return new StreamSource(new StringReader(xmlData));
    }

}
