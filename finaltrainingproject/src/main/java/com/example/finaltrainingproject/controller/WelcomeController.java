package com.example.finaltrainingproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finaltrainingproject.dto.AuthenticationRequest;
import com.example.finaltrainingproject.dto.AuthenticationResponse;
import com.example.finaltrainingproject.dto.ShowConfirmation;
import com.example.finaltrainingproject.dto.ShowRequest;
import com.example.finaltrainingproject.dto.StudentConfirmation;
import com.example.finaltrainingproject.dto.StudentRequest;
import com.example.finaltrainingproject.dto.UpdateConfirmation;
import com.example.finaltrainingproject.dto.UpdateRequest;
import com.example.finaltrainingproject.service.MyUserAcademicDetailsService;
import com.example.finaltrainingproject.service.MyUserDetailsService;
import com.example.finaltrainingproject.service.MyUserShowService;
import com.example.finaltrainingproject.util.JwtUtil;

@RestController
public class WelcomeController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService studentService;
	
	@Autowired
	private MyUserAcademicDetailsService updateService;
	
	@Autowired
	private MyUserShowService showService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to jThread for Spring Security";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> generateToken(@RequestBody AuthenticationRequest authenticationRequest){
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		String jwt = jwtUtil.generateToken(authenticationRequest.getUsername());
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@PostMapping("/register")
	public StudentConfirmation registerUser(@RequestBody StudentRequest studentRequest) {
		return studentService.registerStudent(studentRequest);
	}
	
	@PostMapping("/update")
	public UpdateConfirmation updateUser(@RequestBody UpdateRequest updateRequest) {
		return updateService.updateStudent(updateRequest);
	}
	
	@PostMapping("/show")
	public ShowConfirmation showUser(@RequestBody ShowRequest showRequest) {
		return showService.showStudent(showRequest);
	}
}
