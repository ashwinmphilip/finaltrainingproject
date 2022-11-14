package com.example.finaltrainingproject.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
import com.example.finaltrainingproject.entity.AcademicDetails;
import com.example.finaltrainingproject.entity.Course;
import com.example.finaltrainingproject.entity.StudentCourse;
import com.example.finaltrainingproject.entity.User;
import com.example.finaltrainingproject.repository.MyAcademicDetailsRepository;
import com.example.finaltrainingproject.repository.MyCourseRepository;
import com.example.finaltrainingproject.repository.MyStudentCourseRepository;
import com.example.finaltrainingproject.repository.MyUserRepository;

@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {
	@Autowired
	private MyUserRepository myUserRepository;
	
	@Autowired
	private MyAcademicDetailsRepository myAcademicDetailsRepository;
	
	@Autowired
	private MyCourseRepository myCourseRepository;
	
	@Autowired
	private MyStudentCourseRepository myStudentCourseRepository;
	
	@Autowired
	private AcademicDetails academicDetails;
	
	@Autowired
	private Course course;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = myUserRepository.findByUserName(username);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}
	
	@Override
	@Transactional
	public StudentConfirmation registerStudent(StudentRequest studentRequest) {
		User user = studentRequest.getUser();
		user = myUserRepository.save(user);
		AcademicDetails academicDetails = studentRequest.getAcademicDetails();
		
		academicDetails.setId(user.getId());
		myAcademicDetailsRepository.save(academicDetails);
		return new StudentConfirmation("SUCCESS", user, academicDetails);	
	}
	
	@Override
	public UpdateConfirmation updateStudent(UpdateRequest updateRequest, Long id) {
		
		academicDetails = updateRequest.getAcademicDetails();
		Optional<AcademicDetails> opt = myAcademicDetailsRepository.findById(id);
		academicDetails.setId(opt.get().getId());
		myAcademicDetailsRepository.save(academicDetails);
		return new UpdateConfirmation("SUCCESS", academicDetails);	
	}
	
    @Override
	public ShowConfirmation showStudent(Long id) {	
    	Optional<AcademicDetails> opt = myAcademicDetailsRepository.findById(id);
    	return new ShowConfirmation(opt.get());
	}

	@Override
	public AddCourseConfirmation addCourse(AddCourseRequest addCourseRequest) {
		Course course = addCourseRequest.getCourse();
		myCourseRepository.save(course);
		return new AddCourseConfirmation("SUCCESS", course);
	}

	@Override
	public UpdateCourseConfirmation updateCourse(UpdateCourseRequest updateCourseRequest, Long id) {
		course = updateCourseRequest.getCourse();
		Optional<Course> opt = myCourseRepository.findById(id);
		course.setId(opt.get().getId());
		myCourseRepository.save(course);
		return new UpdateCourseConfirmation("SUCCESS", course);
	}

	@Override
	public DeleteCourseConfirmation deleteCourse(Long id) {
		Optional<Course> opt = myCourseRepository.findById(id);
		myCourseRepository.deleteById(id);
		return new DeleteCourseConfirmation("SUCCESS", opt.get());
	}

	@Override
	public ShowCourseConfirmation showCourse(Long id) {
		Optional<Course> opt = myCourseRepository.findById(id);
    	return new ShowCourseConfirmation(opt.get());
	}

	@Override
	public ShowAllCourseConfirmation showAllCourse() {
		return new ShowAllCourseConfirmation(myCourseRepository.findAll());
	}

	@Transactional
	@Override
	public StudentCourseConfirmation updateStudentCourse(StudentCourseRequest studentCourseRequest, Long id) {
		StudentCourse studentCourse = studentCourseRequest.getStudentCourse();
		studentCourse.setStudentId(id);
		Optional<Course> opt = myCourseRepository.findById(studentCourse.getCourseId());
		studentCourse.setBalance(opt.get().getFees()-studentCourse.getAmount());
		myStudentCourseRepository.save(studentCourse);
		return new StudentCourseConfirmation("SUCCESS", studentCourse);
	}
	
	@Override
	public ShowStudentListConfirmation showStudentByDate() {
		
		List<StudentCourse> listo = myStudentCourseRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
		List<User> list = new ArrayList<User>();
		Iterator<StudentCourse> it = listo.iterator();
		while(it.hasNext()) {
			Optional<User> opt = myUserRepository.findById(it.next().getStudentId());
			list.add(opt.get());
		}
		return new ShowStudentListConfirmation(list);
	}

	@Override
	public ShowStudentListConfirmation showStudentWithoutFees() {
		List<StudentCourse> listo = myStudentCourseRepository.findByBalance(0);
		List<User> list = new ArrayList<User>();
		Iterator<StudentCourse> it = listo.iterator();
		while(it.hasNext()) {
			Optional<User> opt = myUserRepository.findById(it.next().getStudentId());
			list.add(opt.get());
		}
		return new ShowStudentListConfirmation(list);
	}

	@Override
	public ShowStudentListConfirmation showStudentWithFees() {
		List<StudentCourse> listo = myStudentCourseRepository.findByBalanceGreaterThan(0);
		List<User> list = new ArrayList<User>();
		Iterator<StudentCourse> it = listo.iterator();
		while(it.hasNext()) {
			Optional<User> opt = myUserRepository.findById(it.next().getStudentId());
			list.add(opt.get());
		}
		return new ShowStudentListConfirmation(list);
	}

	@Override
	public ShowStudentDetailsListConfirmation showCourseStudent(Long id) {
		List<StudentCourse> listo = myStudentCourseRepository.findByCourseId(id);
		List<AcademicDetails> list = new ArrayList<AcademicDetails>();
		Iterator<StudentCourse> it = listo.iterator();
		while(it.hasNext()) {
			Optional<AcademicDetails> opt = myAcademicDetailsRepository.findById(it.next().getStudentId());
			list.add(opt.get());
		}
		return new ShowStudentDetailsListConfirmation(list);
	}

	@Override
	public ShowCourseListConfirmation showStudentCourse(Long id) {
		List<StudentCourse> listo = myStudentCourseRepository.findByStudentId(id);
		List<Course> list = new ArrayList<Course>();
		Iterator<StudentCourse> it = listo.iterator();
		while(it.hasNext()) {
			Optional<Course> opt = myCourseRepository.findById(it.next().getCourseId());
			list.add(opt.get());
		}
		return new ShowCourseListConfirmation(list);
	}

	@Override
	public Double showTotalFeeCollected() {
		double d = 0;
		List<StudentCourse> listo = myStudentCourseRepository.findAll();
		Iterator<StudentCourse> it = listo.iterator();
		while(it.hasNext()) {
			d = d + it.next().getAmount();
		}
		return d;
	}

	@Override
	public Double showBalanceFeeCollected() {
		double d = 0;
		List<StudentCourse> listo = myStudentCourseRepository.findAll();
		Iterator<StudentCourse> it = listo.iterator();
		while(it.hasNext()) {
			d = d + it.next().getBalance();
		}
		return d;
	}
	
}
