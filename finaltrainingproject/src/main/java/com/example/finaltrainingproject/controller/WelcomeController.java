package com.example.finaltrainingproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.finaltrainingproject.dto.AddCourseConfirmation;
import com.example.finaltrainingproject.dto.AddCourseRequest;
import com.example.finaltrainingproject.dto.AuthenticationRequest;
import com.example.finaltrainingproject.dto.AuthenticationResponse;
import com.example.finaltrainingproject.dto.DeleteCourseConfirmation;
import com.example.finaltrainingproject.dto.ShowAllCourseConfirmation;
import com.example.finaltrainingproject.dto.ShowConfirmation;
import com.example.finaltrainingproject.dto.ShowCourseConfirmation;
import com.example.finaltrainingproject.dto.StudentConfirmation;
import com.example.finaltrainingproject.dto.StudentCourseConfirmation;
import com.example.finaltrainingproject.dto.StudentCourseRequest;
import com.example.finaltrainingproject.dto.StudentRequest;
import com.example.finaltrainingproject.dto.UpdateConfirmation;
import com.example.finaltrainingproject.dto.UpdateCourseConfirmation;
import com.example.finaltrainingproject.dto.UpdateCourseRequest;
import com.example.finaltrainingproject.dto.UpdateRequest;
import com.example.finaltrainingproject.service.MyUserDetailsServiceImpl;
import com.example.finaltrainingproject.util.JwtUtil;

@RestController
public class WelcomeController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsServiceImpl studentServiceImpl;
	
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
	
	@PostMapping("/studentregister")
	public StudentConfirmation registerUser(@RequestBody StudentRequest studentRequest) {
		return studentServiceImpl.registerStudent(studentRequest);
	}
	
	@PostMapping("/studentupdate/{id}")
	public UpdateConfirmation updateUser(@RequestBody UpdateRequest updateRequest, @PathVariable("id") Long id) {
		return studentServiceImpl.updateStudent(updateRequest, id);
	}
	
	@GetMapping("/studentshow/{id}")
	public ShowConfirmation showUser(@PathVariable("id") Long id) {
		return studentServiceImpl.showStudent(id);
	}
	
	@PostMapping("/courseregister")
	public AddCourseConfirmation registerCourse(@RequestBody AddCourseRequest addCourseRequest) {
		return studentServiceImpl.addCourse(addCourseRequest);
	}
	
	@PostMapping("/courseupdate/{id}")
	public UpdateCourseConfirmation updateCourse(@RequestBody UpdateCourseRequest updateCourseRequest, @PathVariable("id") Long id) {
		return studentServiceImpl.updateCourse(updateCourseRequest, id);
	}
	
	@GetMapping("/courseshow/{id}")
	public ShowCourseConfirmation showCourse(@PathVariable("id") Long id) {
		return studentServiceImpl.showCourse(id);
	}
	
	@DeleteMapping("/coursedelete/{id}")
	public DeleteCourseConfirmation deleteCourse(@PathVariable("id") Long id) {
		return studentServiceImpl.deleteCourse(id);
	}
	
	@GetMapping("/allcourseshow")
	public ShowAllCourseConfirmation showAllCourse() {
		return studentServiceImpl.showAllCourse();
	}
	
	@PostMapping("/studentcourseregister/{id}")
	public StudentCourseConfirmation updateStudentCourse(@RequestBody StudentCourseRequest studentCourseRequest, @PathVariable("id") Long id) {
		return studentServiceImpl.updateStudentCourse(studentCourseRequest, id);
	}
}
