package com.Ecom.AuthService.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Order(1) // means this filter should executed first if multiple filters are there..
    @Bean
    public SecurityFilterChain filteringCreteria(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeHttpRequests(authorize->authorize.requestMatchers("/auth/**").permitAll())
                .authorizeHttpRequests(authorize->authorize.requestMatchers("/product").authenticated());
        return http.build();
    }
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
