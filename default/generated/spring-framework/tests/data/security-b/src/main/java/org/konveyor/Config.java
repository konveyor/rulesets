package org.konveyor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.transaction.annotation.EnableTransactionManagement;


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
                                .mvcMatchers("/complicated/**").access("hasRole('ADMIN') || hasAuthority('SCOPE_read')")
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
