package com.praveen.empMan.SpringSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	public InMemoryUserDetailsManager ConfigureMemoryDetails() {
		
		UserDetails user= User.builder().username("praveen").password("{noop}123").roles("MANAGER").build();
		
		
		
		
		InMemoryUserDetailsManager Details = new InMemoryUserDetailsManager(user);
		
		
		return Details;
		
	
		
	}

}
