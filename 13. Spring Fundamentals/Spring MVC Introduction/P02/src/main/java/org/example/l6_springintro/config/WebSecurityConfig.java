package org.example.l6_springintro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Apply the request authorization
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                // Define which paths are accessible to everyone
                                .requestMatchers("/", "/index", "/users/register").permitAll()
                                // All other paths must be authenticated
                                .anyRequest().authenticated())
                // Configure form-based login
                .formLogin(formLogin ->
                        formLogin
                                // Define custom login page
                                .loginPage("/users/login")
                                // Define the default success URL
                                .defaultSuccessUrl("/index", true)
                                .permitAll())
                // Configure logout
                .logout(logout ->
                        logout
                                // Define the logout success URL
                                .logoutSuccessUrl("/")
                                .permitAll())
                // Disable CSRF as an example (not recommended for production)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }


}
