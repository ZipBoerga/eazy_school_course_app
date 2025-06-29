package com.zip_boerga.eazy_school.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf
                        .ignoringRequestMatchers("/contact")
                        .ignoringRequestMatchers("/public/**"))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/messages").hasRole("ADMIN")
                        .requestMatchers("/messages/{id}/close").hasRole("ADMIN")
                        .requestMatchers("/home", "/").permitAll()
                        .requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/public/**").permitAll()
                ).formLogin(loginConfigurer -> loginConfigurer
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard")
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logoutConfigurer -> logoutConfigurer
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .permitAll())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("12345678"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("12345678"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
