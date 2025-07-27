package com.blog.BloggingApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/// WE have to write how the security reacts to different endpoints in this custom configurer
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // keep disabled for Postman testing
                .authorizeHttpRequests(auth -> auth
                        // PUBLIC ENDPOINTS
                        .requestMatchers("/user/register", "/user/admin/register").permitAll()

                        // ONLY ADMINS CAN DELETE USERS OR CREATE ADMINS
                        .requestMatchers("/user/delete/**", "/user/admin/**").hasRole("ADMIN")

                        // ONLY LOGGED-IN USERS CAN CREATE/DELETE BLOGS (USER or ADMIN)
                        .requestMatchers("/blog/**").hasAnyRole("USER", "ADMIN")

                        // EVERYTHING ELSE REQUIRES AUTH
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()); // Basic Auth for now

        return http.build();
    }

}
