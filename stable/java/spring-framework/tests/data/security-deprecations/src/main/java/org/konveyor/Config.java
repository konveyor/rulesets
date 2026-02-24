package org.konveyor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.endpoint.DefaultClientCredentialsTokenResponseClient;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.ClaimAccessor;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import sun.net.www.http.HttpClient;

import java.net.URI;
import java.time.Duration;


@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Config {

    @Bean
    HttpSecurity httpSecurity() {
        return null;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return null;
    }

    @Bean
    DefaultSecurityFilterChain springSecurity(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName("continue");

        http
                .requestCache((cache) -> cache
                        .requestCache(requestCache)
                )
                .antMatcher("/**/*/...")
        ;

        http
                .authorizeRequests((authorize) -> authorize
                                .filterSecurityInterceptorOncePerRequest(true)
                                .mvcMatchers("/app/**").hasRole("APP")
                                .mvcMatchers("/app/**").access("hasIpAddress('127.0.0.1')")
                                .mvcMatchers("/complicated/**").access("hasRole('USER') || hasAuthority('ROLE_USER')")
                );

        http
                .authorizeHttpRequests((authorize) -> authorize
                        .shouldFilterAllDispatcherTypes(false)
                        .mvcMatchers("/app/**").hasRole("APP")
                        .anyRequest().denyAll()
                );
        return http.build();
    }

    @Bean
    public OidcClientInitiatedLogoutSuccessHandler logoutSuccessHandler(ClientRegistrationRepository clientRegistrationRepository) {
        OidcClientInitiatedLogoutSuccessHandler handler =
                new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);

        // Deprecated in Spring Security 5.8
        handler.setPostLogoutRedirectUri(URI.create("https://example.com/logout-success"));

        return handler;
    }

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager() {
        return null;
    }

    @Bean
    public ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2FilterFunction() {
        ServletOAuth2AuthorizedClientExchangeFilterFunction filterFunction =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction();

        // Deprecated in Spring Security 6
        filterFunction.setClientCredentialsTokenResponseClient(
                new DefaultClientCredentialsTokenResponseClient()
        );

        return filterFunction;
    }

    @Bean
    public String configString(OAuth2AuthorizedClientManager authorizedClientManager) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction filterFunction =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
        filterFunction.setAccessTokenExpiresSkew(Duration.ofMinutes(5)); // Deprecated & removed in Spring Security 6

        OidcUserInfo userInfo = OidcUserInfo.builder()
                .subject("user123")
                .phoneNumber("+123456789")
                // Deprecated: this takes a String
                .phoneNumberVerified("true")
                .build();
        userInfo.containsClaim("claim");
//        ClaimAccessor claimAccessor = (ClaimAccessor) userInfo;
//        userInfo.containsClaim("email");
        return "";
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("MYPREFIX_");
    }

    @Bean
    RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
        TokenBasedRememberMeServices.RememberMeTokenAlgorithm encodingAlgorithm = TokenBasedRememberMeServices.RememberMeTokenAlgorithm.SHA256;
        TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices("key", userDetailsService, encodingAlgorithm);
        rememberMe.setMatchingAlgorithm(TokenBasedRememberMeServices.RememberMeTokenAlgorithm.MD5);
        return rememberMe;
    }

    @Bean
    PermissionEvaluator permissionEvaluator() {
        return null;
    }
}
