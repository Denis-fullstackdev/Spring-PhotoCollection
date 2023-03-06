package com.corsojava.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests()
		
			.requestMatchers("/fotos", "/fotos/**").hasAuthority("ADMIN")
			
			.requestMatchers(HttpMethod.POST, "/fotos/**").hasAuthority("ADMIN")
			
			.requestMatchers("/**").permitAll()
			
//			.and().csrf().ignoringRequestMatchers(request ->
//				request.getRequestURI().equals("/api/fotos") &&
//				request.getMethod().equals("POST")).ignoringRequestMatchers(request ->
//				request.getRequestURI().startsWith("/api/fotos/") &&
//				request.getMethod().equals("DELETE"))
			.and().formLogin()
			.and().logout()
			.and().exceptionHandling()
			.accessDeniedPage("/access-denied.html");
		return http.build();
	}
	
	@Bean
	DatabaseUserDetailsService userDetailsService() {
		return new DatabaseUserDetailsService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();	
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
//		System.out.println("USER: "+passwordEncoder().encode("user"));
//	    System.out.println("ADMIN: "+passwordEncoder().encode("admin"));
		
		return authProvider;
	}

}