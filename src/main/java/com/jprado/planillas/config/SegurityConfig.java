package com.jprado.planillas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SegurityConfig {

    private final UserDetailsService userDetailService;

    

    @Bean
    public DaoAuthenticationProvider authenticationProvider(BCryptPasswordEncoder encoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }

    @Bean
    SecurityFilterChain  securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
            auth -> auth
            .requestMatchers("/", "/login", "/css/**", "/js/**", "/image/**").permitAll()
            .anyRequest().authenticated())
            .formLogin(form -> form.defaultSuccessUrl("/test", true) // 👈 redirige a /empresa tras login exitoso
                .permitAll())
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );


        return http.build();
    } 
}
