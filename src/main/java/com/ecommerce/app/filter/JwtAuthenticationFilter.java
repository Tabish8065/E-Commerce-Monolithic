package com.ecommerce.app.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecommerce.app.authservice.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(
			@NotNull HttpServletRequest request, 
			@NotNull HttpServletResponse response,
			@NotNull FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.substring(7);
		
		//Extract UserEmail
		userEmail = jwtService.extractUserEmail(jwt);
		if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
			if(jwtService.validateToken(jwt, userDetails.getUsername())) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities()
					);
				authToken.setDetails(
						new WebAuthenticationDetailsSource()
							.buildDetails(request)
					);
				SecurityContextHolder
					.getContext()
					.setAuthentication(authToken);
			}
		}
		
		filterChain.doFilter(request, response);

	}

}
