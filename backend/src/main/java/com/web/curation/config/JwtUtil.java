package com.web.curation.config;

import java.security.Key;

import org.springframework.stereotype.Component;

import com.web.curation.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	
	private Key key;
	
	
	public JwtUtil() {
		this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}
	
	public String createToken(User user) {
		String jws = Jwts.builder().claim("email", user.getEmail()).signWith(key).compact();
		return jws;
	}

	public Key getKey() {
		return key;
	}

//	public void verifyToken(String givenToken) {
//		// TODO Auto-generated method stub
//		
//	}
	
	

}
