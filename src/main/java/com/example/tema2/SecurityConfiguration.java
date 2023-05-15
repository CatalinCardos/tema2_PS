package com.example.tema2;

import com.example.tema2.Service.UserDetailsService;
import com.example.tema2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.context.annotation.Bean;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration{

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorizeRequests -> {
            authorizeRequests
                    .requestMatchers("/admin").hasAuthority("admin")
                    .requestMatchers("/addDish").hasAuthority("admin")
                    .requestMatchers("/deleteDish").hasAuthority("admin")
                    .requestMatchers("/modifyDish").hasAuthority("admin")
                    .requestMatchers("/createUser").hasAuthority("admin")
                    .requestMatchers("/raportOrder").hasAuthority("admin")
                    .requestMatchers("/statisticView").hasAuthority("admin")
                    .requestMatchers("/user").hasAuthority("user")
                    .requestMatchers("/addOrder").hasAuthority("user")
                    .requestMatchers("/modifyStatus").hasAuthority("user")
                    .requestMatchers("/").permitAll()
                    .anyRequest().authenticated();
                })
                .userDetailsService(userDetailsService)
                .headers(headers -> headers.frameOptions().sameOrigin())
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
