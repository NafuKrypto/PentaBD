package com.penta.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.penta.assignment.model.AuthenticationRequest;
import com.penta.assignment.model.AuthenticationResponse;
import com.penta.assignment.repository.UserRepository;
 
import com.penta.assignment.model.UserModel;
 
@RestController
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	private   AuthenticationManager authenticationManager;
	@PostMapping("/regestered")
	
	private ResponseEntity<?> registeredClient(@RequestBody AuthenticationRequest authenticationRequest){
		String password=authenticationRequest.getPassword();
		String email=authenticationRequest.getEmail();
		UserModel userModel=new UserModel();
		userModel.setEmail(email);
		userModel.setPassword(password);
		userRepository.save(userModel);
		try {
			userRepository.save(userModel);
		}catch (Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse("Error during client regestration "+email));
		}
		return ResponseEntity.ok(new AuthenticationResponse("Successful regestration for client "+email));
	}
	
	
	@PostMapping("/auth")
	private ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest){
		String password=authenticationRequest.getPassword();
		String email=authenticationRequest.getEmail();
		
		try{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
		}catch(BadCredentialsException e) {
			 return ResponseEntity.ok(new AuthenticationResponse("Error during client Authenticatin"+email));
		}
		
		return ResponseEntity.ok(new AuthenticationResponse("Successful Authentication for client"+email));
	}
	
}
