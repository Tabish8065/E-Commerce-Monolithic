package com.ecommerce.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecommerce.app.dto.UserLoginDto;
import com.ecommerce.app.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AuthConfiguration {
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	UserDetailsService userDetailsService() {
//		
//		UserDetails normalUser = User.withUsername("Tabish")
//				.password(passwordEncoder().encode("Hello"))
//				.roles("USER")
//				.build();
//		
//		UserDetails adminUser = User.withUsername("TabishA")
//				.password(passwordEncoder().encode("Tabish"))
//				.roles("ADMIN")
//				.build();
//		
//		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(normalUser, adminUser);
//		
//		return inMemoryUserDetailsManager;
//	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf((csrf) -> csrf.disable())
			.authorizeHttpRequests((authorizeHttpRequests) ->
				authorizeHttpRequests
					.anyRequest()
					.authenticated())
			.sessionManagement((sessionManagement) ->
				sessionManagement.sessionCreationPolicy(
						SessionCreationPolicy.STATELESS))
			.authenticationProvider(authenticationProvider())
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin((formLogin) ->
				formLogin
					.usernameParameter("username")
					.passwordParameter("password")
			);
		
		return httpSecurity.build();
	}
    
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return authenticationProvider();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
		return config.getAuthenticationManager();
	}
    
}
