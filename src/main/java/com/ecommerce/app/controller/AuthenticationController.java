package com.ecommerce.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.authservice.AuthService;
import com.ecommerce.app.dto.UserLoginDto;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/token")
	public String generateToken(@RequestBody UserLoginDto user) {
		System.out.println(user);
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return authService.generateToken(user.getUsername());
		}else {
			throw new RuntimeException("Invalid access");
		}
	}
	
//	@GetMapping("/validate")
//    public String validateToken(@RequestParam("token") String token) {
//        authService.validateToken(token);
//        return "Token is valid";
//    }

	@GetMapping("/testPublic")
	public String publicApi() {
		return "Public User";
	}

	@PreAuthorize("hasRole('User')")
	@GetMapping("/testUser")
	public String userApi() {
		return "User Api";
	}

	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/testAdmin")
	public String adminApi() {
		return "Admin Api";
	}
}
