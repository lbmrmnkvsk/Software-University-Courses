package org.example.pathfinder1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers(
                                                "/", "/users/login", "/users/register", "/about"
                                        ).permitAll()
                                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                        .anyRequest().authenticated()
                ).formLogin(formLogin ->
                        formLogin
                                .loginPage("/users/login")
                                .loginProcessingUrl("/users/perform_login")
                                .defaultSuccessUrl("/", true)
                                .failureUrl("/users/login?error=true"))
                .logout(logout -> logout
                        .logoutUrl("/users/logout")
                        .logoutSuccessUrl("/home")
                        .invalidateHttpSession(true))
                .build();
    }
}
