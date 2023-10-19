package com.movieview360.movieview360.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/auth/login").permitAll()
                                .antMatchers("/users/create","/users/check-email","/users/check-username").permitAll()
                                .antMatchers("/users/all").hasRole("ADMIN")

                                .antMatchers("/v3/api-docs", "/swagger-ui/**", "/swagger-resources/**",
                                        "/webjars/**", "/configuration/security", "/swagger-ui.html", "/v3/**").permitAll()

                                .antMatchers(HttpMethod.DELETE,"/castings", "/movie-castings", "/categories", "/movies").hasRole("ADMIN")
                                .antMatchers(HttpMethod.POST, "/castings", "/movie-castings", "/categories", "/movies").hasRole("ADMIN")
                                .antMatchers(HttpMethod.PUT, "/castings", "/movie-castings", "/categories", "/movies","/users/{id}").hasRole("ADMIN")
                                .antMatchers(HttpMethod.OPTIONS).permitAll()

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
