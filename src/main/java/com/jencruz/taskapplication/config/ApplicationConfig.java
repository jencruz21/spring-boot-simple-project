package com.jencruz.taskapplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ApplicationConfig {
    
    @Value("${application.username}")
    private String username;

    @Value("${application.password}")
    private String password;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // this will be changed to dao based user details service
    @Bean
    public UserDetailsService userDetails() {

        // return new UserDetailsService() {
        //     @Override
        //     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //         return User.builder()
        //                 .username(username)
        //                 .password(bCryptPasswordEncoder().encode(password))
        //                 .build();
        //     }
        // };
        
        UserDetails user = User.builder()
                .username(username)
                .password(bCryptPasswordEncoder().encode(password))
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean(name = "customAuthenticationProvider") 
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetails());
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }
}
