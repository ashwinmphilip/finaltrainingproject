package com.example.finaltrainingproject.service;

import java.util.ArrayList;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.finaltrainingproject.dto.StudentConfirmation;
import com.example.finaltrainingproject.dto.StudentRequest;
import com.example.finaltrainingproject.entity.AcademicDetails;
import com.example.finaltrainingproject.entity.User;
import com.example.finaltrainingproject.repository.MyAcademicDetailsRepository;
import com.example.finaltrainingproject.repository.MyUserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private MyUserRepository myUserRepository;
	
	@Autowired
	private MyAcademicDetailsRepository myAcademicDetailsRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = myUserRepository.findByUserName(username);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}
	
	@Transactional
	public StudentConfirmation registerStudent(StudentRequest studentRequest) {
		User user = studentRequest.getUser();
		user = myUserRepository.save(user);
		AcademicDetails academicDetails = studentRequest.getAcademicDetails();
		
		academicDetails.setId(user.getId());
		myAcademicDetailsRepository.save(academicDetails);
		return new StudentConfirmation("SUCCESS", user, academicDetails);	
	}

}	
