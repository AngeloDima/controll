package com.example.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfiguration {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.authorizeHttpRequests()
	            .requestMatchers("/create").hasAuthority("ADMIN")
	            .requestMatchers("/edit/**").hasAuthority("ADMIN")
	            .requestMatchers("/delete/**").hasAuthority("ADMIN")
	            .requestMatchers("/foto/**").hasAuthority("USER")
	            .requestMatchers("/**").permitAll()
	            .and().formLogin()
	            .and().logout();
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
	 
	    return authProvider;
	  }  
	  
	  
}



