package com.ecommerce.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ecommerce.app.dto.UserLoginDto;
import com.ecommerce.app.model.UserModel;
import com.ecommerce.app.service.UserService;

@Component
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//User fetched from the user service
		UserModel userFetched = userService.readUserByEmail(username);
		
		//Map user to the userLoginDto
		UserLoginDto user = new UserLoginDto(
				userFetched.getEmail(),
				userFetched.getPassword(),
				userFetched.getRole());
		
		//Logging
		System.out.println("User mapped to the UDS class "+user);
		
		return user;
	}

}
