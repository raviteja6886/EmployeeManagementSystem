package com.springJPA.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf->csrf.disable())
			.authorizeHttpRequests((authorize)->authorize.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults());
		return http.build();
		
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		UserDetails admin =User.builder()
							.username("Ravi")
							.password(passwordEncoder().encode("Ravi@123"))
							.roles("ADMIN")
							.build();
		UserDetails user =User.builder()
				.username("Karthik")
				.password(passwordEncoder().encode("Karthik123"))
				.roles("User")
				.build();
		
		return new InMemoryUserDetailsManager(admin,user);

	}

}
