package io.gonzajf.immfly.service;

import javax.annotation.PostConstruct;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	private InMemoryUserDetailsManager userDetailsManager;
	
	@PostConstruct
	public void initUsers() {
	
		UserDetails admin = User.withUsername("admin")
				.password("admin").roles("ADMIN").build();

		UserDetails user = User.withUsername("user").password("user").roles("USER").build();

		userDetailsManager = new InMemoryUserDetailsManager();
		userDetailsManager.createUser(admin);
		userDetailsManager.createUser(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDetailsManager.loadUserByUsername(username);
	}
}
