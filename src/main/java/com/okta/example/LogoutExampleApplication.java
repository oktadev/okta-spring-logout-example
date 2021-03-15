package com.okta.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.util.Collections;

@SpringBootApplication
public class LogoutExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogoutExampleApplication.class, args);
    }

    @Configuration
    static class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        ClientRegistrationRepository clientRegistrationRepository;

        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() {
            OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
            successHandler.setPostLogoutRedirectUri(URI.create("http://localhost:8080/"));
            return successHandler;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()

                    // allow anonymous access to the root page
                    .antMatchers("/").permitAll()

                    // all other requests
                    .anyRequest().authenticated()

                    // After we logout, redirect to root page, by default Spring will send you to /login?logout
                    .and().logout().logoutSuccessUrl("/")

                    // RP-initiated logout
                    .and().logout().logoutSuccessHandler(oidcLogoutSuccessHandler())

                    // enable OAuth2/OIDC
                    .and().oauth2Login();
        }
    }

    @Controller
    static class SimpleController {

        @GetMapping("/")
        public String home() {
            return "home";
        }

        @GetMapping("/profile")
        public ModelAndView userDetails(OAuth2AuthenticationToken authentication) {
            return new ModelAndView("profile" , Collections.singletonMap("details", authentication.getPrincipal().getAttributes()));
        }
    }
}
