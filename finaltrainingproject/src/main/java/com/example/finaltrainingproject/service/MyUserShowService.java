package com.example.finaltrainingproject.service;

import java.util.ArrayList;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.finaltrainingproject.dto.ShowConfirmation;
import com.example.finaltrainingproject.dto.ShowRequest;

import com.example.finaltrainingproject.entity.AcademicDetails;
import com.example.finaltrainingproject.entity.User;
import com.example.finaltrainingproject.repository.MyAcademicDetailsRepository;
import com.example.finaltrainingproject.repository.MyUserRepository;

@Service
public class MyUserShowService implements UserDetailsService{

	@Autowired
	private MyUserRepository myUserRepository;
	
	@Autowired
	private MyAcademicDetailsRepository myAcademicDetailsRepository;
	
	@Autowired
	private AcademicDetails academicDetails;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = myUserRepository.findByUserName(username);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}
	
	@Transactional
	public ShowConfirmation showStudent(ShowRequest showRequest) {
		
		academicDetails = myAcademicDetailsRepository.getById(academicDetails.getId());
		return new ShowConfirmation(academicDetails);	
	}

}
