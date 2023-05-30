package com.ecommerce.app.authservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	
	@Autowired
	private JwtService jwtService;
	
	public String generateToken(String userName) {
		return jwtService.generateToken(userName);
	}
	
//	public boolean validateToken(String token) {
//		jwtService.validateToken(token);
//	}
}
