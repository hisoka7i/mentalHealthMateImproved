package com.demo.mhm.jwt_utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

	//these two values will be taken from applicaation.properties
	@Value("${SECRET_KEY}")
	private String jwtSecret;
	
	@Value("${EXP_TIMEOUT}")
	private int jwtExpirationMs;
	
	
//	public String generateJwtToken(Authentication)
}
