package com.demo.mhm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EntityScan(basePackages = "com.demo.mhm") // Adjust to your package
public class SpringBootRestWebServiceApplication {

	public static void main(String[] args) {
			SpringApplication.run(SpringBootRestWebServiceApplication.class, args);	
			System.out.println("------------------");
			// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			// System.out.println(encoder.encode("1234"));
			// System.out.println("------------------"); 
	}

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
