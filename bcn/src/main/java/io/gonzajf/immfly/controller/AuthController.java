package io.gonzajf.immfly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.gonzajf.immfly.dto.AuthenticationRequest;
import io.gonzajf.immfly.dto.AuthenticationResponse;
import io.gonzajf.immfly.security.JwtUtil;
import io.gonzajf.immfly.service.MyUserDetailsService;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello World!";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> createAuthToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		
		try {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							authenticationRequest.getUsername(),
							authenticationRequest.getPassword()));
		
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password");
		}
		
		final UserDetails userDetails = 
				userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String token = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(token));
	} 
}