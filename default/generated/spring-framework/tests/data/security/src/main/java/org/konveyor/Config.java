package org.konveyor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

@Configuration
public class Config {

    @Bean
    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter(@Value("${account-api.require-tokens:true}") boolean requireTokens) {
        return new WebSecurityConfigurerAdapter() {
            @Override
            protected void configure(HttpSecurity http) throws Exception {

                http
                        .cors().and()
                        .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                        .csrf().disable()

                        .authorizeRequests()

                        // health checks
                        .antMatchers(
                                "/**/diagnostics/**",
                                "/**/health/**",
                                "/**/liveness/**",
                                "/**/readiness/**")
                        .permitAll()

                        // swagger
                        .antMatchers("/v1/api-docs-internal/**","/v1/api-docs/**", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**")
                        .permitAll();

                // v1 users API - tokens should be required in non-local environments.
                ExpressionUrlAuthorizationConfigurer<HttpSecurity>.AuthorizedUrl usersApiAuthorization =
                        http.authorizeRequests().antMatchers("/v1/accounts/**", "/v1/user");

                if (requireTokens) {
                    http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
                    usersApiAuthorization.access("@callContext.hasClientOrUserWithRole('generalAccess') and authenticated");
                } else {
                    usersApiAuthorization.access("@callContext.hasClientOrUserWithRole('generalAccess')");
                }

                // default to deny everything else
                http.authorizeRequests().anyRequest().denyAll();

                final HttpSessionSecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
                http
                        .sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Uses sessions if needed
                        )
                        .sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // Uses sessions if needed
                        )
                        .securityContext(securityContext -> securityContext
                                .securityContextRepository(securityContextRepository) // Stores SecurityContext in session
                        );


            }
        };
    }
}
