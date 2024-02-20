package com.hkprogrammer.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatchers;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	// ROLE_USER
	// ROLE_ADMIN

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
				.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(new JWTConverter())));

		
		http.authorizeRequests()
			.requestMatchers(HttpMethod.GET, "/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
			.requestMatchers(HttpMethod.GET, "/pessoa/ativos").hasAnyRole("USER", "ADMIN")
			.requestMatchers(HttpMethod.GET, "/pessoa").hasRole("ADMIN")
			.requestMatchers(HttpMethod.DELETE, "/pessoa/**").hasRole("ADMIN")
			.requestMatchers(HttpMethod.POST, "/pessoa").hasRole("ADMIN")
			.requestMatchers(HttpMethod.PUT, "/pessoa").hasRole("ADMIN")
			.requestMatchers(HttpMethod.PUT, "/pessoa/alterar-status/**").hasRole("ADMIN");
		

		return http.build();
	}
}