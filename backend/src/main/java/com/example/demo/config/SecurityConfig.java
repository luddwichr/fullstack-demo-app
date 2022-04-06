package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // randomly chosen by me for demo. In practice, should be tuned towards ~1 sec computation time in your environment!
    private static final int BCRYPT_STRENGTH = 15;
    private final UserDetailsService userDetailService;
    private final UserDetailsPasswordService userDetailsPasswordService;

    SecurityConfig(UserDetailsService userDetailService, UserDetailsPasswordService userDetailsPasswordService) {
        this.userDetailService = userDetailService;
        this.userDetailsPasswordService = userDetailsPasswordService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeRequests()
                .antMatchers("/registration")
                .permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailService);
        provider.setUserDetailsPasswordService(userDetailsPasswordService);
        return provider;
    }

    @Bean
    PasswordEncoder passwordEncoder(SecureRandom secureRandom) {
        return new BCryptPasswordEncoder(BCRYPT_STRENGTH, secureRandom);
    }

    @Bean
    SecureRandom secureRandom() {
        return new SecureRandom();
    }
}
