package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BookConfig {
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth->auth.
                requestMatchers("/api/v3/bookapp/**","/api/v3/bookapp/verify")
                .permitAll().
                anyRequest().authenticated());
        http.csrf(c->c.disable());
        return http.build();
    }
}
