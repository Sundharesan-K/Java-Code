package com.trustrace.Switchenergysystembackend.configsec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(){
//        UserDetails admin= User.withUsername ("sundhar")
//                .password (passwordEncoder.encode ("SUN123"))
//                .roles ("ADMIN")
//                .build ();
//        UserDetails user=User.withUsername ("trustrace")
//                .password (passwordEncoder.encode ("TRUST123"))
//                .roles ("USER")
//                .build ();
//        return new InMemoryUserDetailsManager (admin,user);
        return new UserUserDetailsService();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
       return httpSecurity.csrf ().disable ()
                .authorizeHttpRequests ()
                .requestMatchers ("/api/provider","/api/smartMeter","/api/user/new","/api/user/authenticate").permitAll ()
                .and ()
                .authorizeHttpRequests ().requestMatchers ("/api/**")
                .authenticated ().and ().formLogin ().and ().build ();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder ();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider ();
        daoAuthenticationProvider.setUserDetailsService (userDetailsService ());
        daoAuthenticationProvider.setPasswordEncoder (passwordEncoder ());
        return daoAuthenticationProvider;
    }
}
