package com.gonzalodev.saiyajinstore.backend.config;

import com.gonzalodev.saiyajinstore.backend.infrastructure.jwt.JWTAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private static final String[] WHITE_LIST_URL = { "/api/payments/**", "/api/security/**", "/api/home/**", "/api/v1/auth/**", "/v2/api-docs", "/v3/api-docs",
            "/v3/api-docs/**", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
            "/configuration/security", "/swagger-ui/**", "/webjars/**", "/swagger-ui.html", "/api/auth/**",
            "/api/test/**", "/authenticate" };

    private final JWTAuthorizationFilter authorizationFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors( cors -> cors.configurationSource(
                request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
                    corsConfiguration.setAllowedMethods(Arrays.asList("*"));
                    corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
                    return corsConfiguration;
                }
                ))
                .csrf(csrf -> csrf.disable()).authorizeHttpRequests(
                aut -> aut
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers("/api/products/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/products/user/**").hasRole("USER")
                        .requestMatchers("/api/orders/**").hasRole("USER")
                        .anyRequest().authenticated()
        ).addFilterAfter(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
