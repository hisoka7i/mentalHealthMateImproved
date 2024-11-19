package com.demo.mhm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.mhm.dto.MedicalHistoryDTO;
import com.demo.mhm.dto.SentUserDetailsDTO;
import com.demo.mhm.dto.UserCredentialDTO;
import com.demo.mhm.jwt_utils.JwtUtils;
import com.demo.mhm.model.Users;
import com.demo.mhm.service.CustomUserDetails;
import com.demo.mhm.service.UserServiceI;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RequestMapping("/user")
@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserServiceI serviceI;

	@Autowired
	JwtUtils jwtUtils; //to generate jwtToken

	@Autowired
	AuthenticationManager manager;

	/*
	 * This is integrated with JWT token
	*/
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody  @Valid UserCredentialDTO userCredentialDTO)
	{
		//we are storing user's username and password in authentication object
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userCredentialDTO.getUname(), userCredentialDTO.getPassword());

		try {
			//authenticate the credential
			Authentication authenticationDetails = manager.authenticate(authenticationToken);
			//this means that authentication was successful
			CustomUserDetails customUserDetails = (CustomUserDetails) authenticationDetails.getPrincipal();
			Users currentUserData = customUserDetails.getUser();
			SentUserDetailsDTO userData = new SentUserDetailsDTO();
			// Create JWT token with minimal data
			String token = jwtUtils.generateJwtToken(authenticationDetails);
			userData.setId(currentUserData.getId());
			userData.setEmail(currentUserData.getEmail());
			userData.setName(currentUserData.getName());
			userData.setJwtToken(token);
			userData.setAge(currentUserData.getAge());
			userData.setGender(currentUserData.getGender());
			userData.setPhoneNumber(currentUserData.getPhoneno());
			userData.setUserName(currentUserData.getUsername());

			return ResponseEntity.ok(userData);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		// Users dtoSend = serviceI.findUserByUserNameAndPassword(dcredential);
		// if(dtoSend!=null) {
		
		// 		return ResponseEntity.ok(dtoSend);
		// 	}else {
		// 	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		// 	}
		
	}

	/*
	 * This test function to test if token is working fine or not
	*/
	@GetMapping("/tokenCheck")
	public ResponseEntity<?> checkTokenValidity(Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
			System.out.println("Authentication Principal: " + authentication.getPrincipal());
			return ResponseEntity.ok("Token is valid");
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Token is invalid.");
		}
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<?> addUser(@RequestBody Users user)
	{
		 Users uData =serviceI.addUser(user);
		
		 if(uData!=null) {
		 return ResponseEntity.ok(uData);}
		 else
		 {
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
		
	}
	
	
	@PatchMapping("/updateAllergies/{id}/{allergy}")
	public ResponseEntity<?> updateAllergyById(@PathVariable("id") int id,@PathVariable("allergy") String allergy,HttpStatus status
	,Authentication authentication){
		if(authentication.isAuthenticated()){
			if(serviceI.updateAllergy(id,allergy)) {
				return ResponseEntity.ok(status);
			}
		}
		return ResponseEntity.status(status).build();
	}
	
	@PostMapping("/addMedicalHistory")
	public ResponseEntity<?> addMedicalHistory(@RequestBody MedicalHistoryDTO medicalHistoryDTO,HttpStatus status, Authentication authentication){
		if(authentication.isAuthenticated()){
			boolean out = serviceI.addMedicalHistory(medicalHistoryDTO);
		}
		return ResponseEntity.ok(status);
	}

}
