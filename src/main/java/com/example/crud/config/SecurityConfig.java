package com.example.crud.config;

import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .userDetailsService(this.userService)
                .authorizeHttpRequests(authorization -> {
                    authorization.antMatchers("/user/**").permitAll()
                            .antMatchers("/product/**").hasRole("ADMIN")
                            .anyRequest().authenticated();
                })
                .httpBasic().and().logout(logout -> {
                    logout.logoutUrl("/logout").logoutSuccessUrl("/user/login")
                        .deleteCookies("JSESSIONID")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true);
                }).build();
    }
}
