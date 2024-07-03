package com.elc1090.shelterhubapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        String[] allRoles = List.of("ADMIN", "FUNCTIONARY", "VOLUNTEER").toArray(new String[3]);
        String[] adminAndFunctionaryRoles = List.of("ADMIN", "FUNCTIONARY").toArray(new String[2]);

        return httpSecurity
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(OPTIONS).permitAll()
                        .requestMatchers(POST, "/login").permitAll()
                        .requestMatchers(POST, "/register").permitAll()
                        .requestMatchers(PUT).hasAnyRole(adminAndFunctionaryRoles)
                        .requestMatchers(DELETE).hasAnyRole(adminAndFunctionaryRoles)
                        .requestMatchers(GET,
                                "/category",
                                "/measurement-unit",
                                "/item",
                                "/item-shelter",
                                "/category/{id}",
                                "/measurement-unit/{id}",
                                "/item/{id}",
                                "/item-shelter/{id}",
                                "/item-shelter/shelter/{shelter_id}",
                                "/shelter/{id}",
                                "/transaction/{id}",
                                "/transaction/shelter/{shelter_id}")
                        .hasAnyRole(allRoles)
                        .requestMatchers(POST,
                                "/category",
                                "/measurement-unit",
                                "/item",
                                "/item-shelter")
                        .hasAnyRole(adminAndFunctionaryRoles)
                        .requestMatchers(POST, "/transaction").hasAnyRole(allRoles)
                        .requestMatchers(GET, "/shelter").hasRole("ADMIN")
                        .requestMatchers(POST, "/shelter").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
