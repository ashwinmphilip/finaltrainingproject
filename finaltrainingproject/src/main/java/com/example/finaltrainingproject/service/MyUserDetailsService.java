package com.example.finaltrainingproject.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.finaltrainingproject.dto.AddCourseConfirmation;
import com.example.finaltrainingproject.dto.AddCourseRequest;
import com.example.finaltrainingproject.dto.DeleteCourseConfirmation;
import com.example.finaltrainingproject.dto.ShowAllCourseConfirmation;
import com.example.finaltrainingproject.dto.ShowConfirmation;
import com.example.finaltrainingproject.dto.ShowCourseConfirmation;
import com.example.finaltrainingproject.dto.ShowCourseListConfirmation;
import com.example.finaltrainingproject.dto.ShowStudentDetailsListConfirmation;
import com.example.finaltrainingproject.dto.ShowStudentListConfirmation;
import com.example.finaltrainingproject.dto.StudentConfirmation;
import com.example.finaltrainingproject.dto.StudentCourseConfirmation;
import com.example.finaltrainingproject.dto.StudentCourseRequest;
import com.example.finaltrainingproject.dto.StudentRequest;
import com.example.finaltrainingproject.dto.UpdateConfirmation;
import com.example.finaltrainingproject.dto.UpdateCourseConfirmation;
import com.example.finaltrainingproject.dto.UpdateCourseRequest;
import com.example.finaltrainingproject.dto.UpdateRequest;



public interface MyUserDetailsService extends UserDetailsService{
	public UserDetails loadUserByUsername(String username);
	public StudentConfirmation registerStudent(StudentRequest studentRequest);
	public UpdateConfirmation updateStudent(UpdateRequest updateRequest, Long id);
	public ShowConfirmation showStudent(Long id);
	public AddCourseConfirmation addCourse(AddCourseRequest addCourseRequest);
	public UpdateCourseConfirmation updateCourse(UpdateCourseRequest updateCourseRequest, Long id);
	public DeleteCourseConfirmation deleteCourse(Long id);
	public ShowCourseConfirmation showCourse(Long id);
	public ShowAllCourseConfirmation showAllCourse();
	public StudentCourseConfirmation updateStudentCourse(StudentCourseRequest studentCourseRequest, Long id);
	public ShowStudentListConfirmation showStudentByDate();
	public ShowStudentListConfirmation showStudentWithoutFees();
	public ShowStudentListConfirmation showStudentWithFees();
	public ShowStudentDetailsListConfirmation showCourseStudent(Long id);
	public ShowCourseListConfirmation showStudentCourse(Long id);
	public Double showTotalFeeCollected();
	public Double showBalanceFeeCollected();
}
