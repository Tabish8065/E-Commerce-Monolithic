package com.ecommerce.app.dto;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

/**
 * @author 2276636
 *
 */
public class UserLoginDto implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Email cannot be null.")
	@Email(message = "Please enter the correct email.")
	private String email;
	@NotNull(message = "Password cannot be null.")
	private String password;
	private String role;

	public UserLoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserLoginDto(
			@NotNull(message = "Email cannot be null.") @Email(message = "Please enter the correct email.") String email,
			@NotNull(message = "Password cannot be null.") String password, String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "UserLoginDto [email=" + email + ", password=" + password + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
