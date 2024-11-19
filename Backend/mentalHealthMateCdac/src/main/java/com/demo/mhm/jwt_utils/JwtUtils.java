package com.demo.mhm.jwt_utils;

import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.crypto.spec.SecretKeySpec; // Correct import for Key
import java.security.Key;  // Key interface is from java.security package
import com.demo.mhm.service.CustomUserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	//these two values will be taken from application.properties
	@Value("${SECRET_KEY}")
	private String jwtSecret;
	
	@Value("${EXP_TIMEOUT}")
	private int jwtExpirationMs;
	
	// This will be invoked by the controller after authentication
    public String generateJwtToken(Authentication authentication) {
        // This token will contain header + payload + signature
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        
        // Create the secret key using the string from the properties file
        Key key = new SecretKeySpec(jwtSecret.getBytes(), SignatureAlgorithm.HS512.getJcaName());
        
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(key)
            .compact();
    }

	public boolean validateJwtToken(String token) {
		// TODO Auto-generated method stub
		try {
			// Create the secret key using the string from the properties file
			Key key = new SecretKeySpec(jwtSecret.getBytes(), SignatureAlgorithm.HS512.getJcaName());
			Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token); // Parse the JWT and validate it

			return true;
		} catch (Exception e) {
			
		}
		return false;
	}

	public String getUsernameFromToken(String token) {
		// TODO Auto-generated method stub
		// Create the secret key using the string from the properties file
		Key key = new SecretKeySpec(jwtSecret.getBytes(), SignatureAlgorithm.HS512.getJcaName());
		return Jwts.parserBuilder()
		.setSigningKey(key)
		.build()
		.parseClaimsJws(token).getBody().getSubject(); // Parse the JWT and validate it
	}
}
