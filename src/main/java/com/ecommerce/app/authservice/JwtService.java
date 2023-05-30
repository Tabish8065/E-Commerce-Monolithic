package com.ecommerce.app.authservice;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	public static final String SECRET = "4f1feeca525de4cdb064656007da3edac7895a87ff0ea865693300fb8b6e8f9c";
	
	public boolean validateToken(final String token, String userEmail) {
//		Jwts.parserBuilder()
//			.setSigningKey(getSignKey())
//			.build()
//			.parseClaimsJws(token);
		
		//New
		final String extractedUserEmail = extractUserEmail(token);
		return (userEmail.equals(extractedUserEmail)) && !isTokenExpired(token);
		
	}
	
	public String generateToken(String userName) {//Only uses userName to generate token
		Map<String,Object> claims = new HashMap<>();
		return createToken(claims, userName);
	}
	
	private String createToken(Map<String, Object> claims, String userName) {
		// TODO Auto-generated method stub
		
		return Jwts.builder()
					.setClaims(claims)
					.setSubject(userName)
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
					.signWith(getSignKey(), SignatureAlgorithm.HS256)
					.compact();// This method will generate and return the token
	}

	private Key getSignKey() {
		
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		
		return Keys.hmacShaKeyFor(keyBytes);
	}

	
	//New
	
	public String extractUserEmail(String jwt) {
		// TODO Auto-generated method stub

		return extractClaims(jwt, Claims::getSubject);
	}

	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public boolean isTokenExpired(String token) {
		Date extractedDate = extractClaims(token, Claims::getExpiration);
		return extractedDate.before(new Date());
	}
}
