package com.api.restaurantbooking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class SecurityConfig {
	
	@Value("${admin.username}")
	private String adminUsername;

	@Value("${admin.password}")
	private String adminPassword;

    // ==========================================
    // SECURITY FILTER CHAIN
    // ==========================================

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth

                // Public pages
                .requestMatchers(
                    "/",
                    "/restaurant/**",
                    "/css/**",
                    "/images/**"
                ).permitAll()

                // Admin pages
                .requestMatchers("/admin/**")
                .hasRole("ADMIN")

                // Everything else
                .anyRequest()
                .permitAll()
            )

            // Login
            .formLogin(form -> form
                .loginPage("/admin/login")
                .loginProcessingUrl("/admin/login")
                .defaultSuccessUrl("/admin", true)
                .failureUrl("/admin/login?error=true")
                .permitAll()
            )

            // Logout
            .logout(logout -> logout
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/login?logout=true")
                .permitAll()
            );

        return http.build();
    }


    // ==========================================
    // PASSWORD ENCODER
    // ==========================================

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    // ==========================================
    // ADMIN USER
    // ==========================================

    @Bean
    public UserDetailsManager userDetailsManager(
            PasswordEncoder passwordEncoder) {

    	UserDetails admin = User.builder()
    	        .username(adminUsername)
    	        .password(passwordEncoder.encode(adminPassword))
    	        .roles("ADMIN")
    	        .build();
    	
        return new InMemoryUserDetailsManager(admin);
    }
}