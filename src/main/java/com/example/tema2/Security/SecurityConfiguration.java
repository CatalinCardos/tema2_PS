package com.example.tema2.Security;

import com.example.tema2.Utils.MD5PasswordEncoder;
import com.example.tema2.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

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
                    .requestMatchers("/exportFile").hasAuthority("admin")
                    .requestMatchers("/getPublicOrders").permitAll()
                    .requestMatchers("/addPublicOrder").permitAll()
                    .requestMatchers("/getPublicStatistics").permitAll()
                    .requestMatchers("/getBodyDish").hasAuthority("admin")
                    .requestMatchers("/").permitAll()
                    .anyRequest().authenticated();
                })
                .userDetailsService(userDetailsService)
                .headers(headers -> headers.frameOptions().sameOrigin())
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .logout(withDefaults())
                .csrf().disable()
                .build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new MD5PasswordEncoder();
    }
}
