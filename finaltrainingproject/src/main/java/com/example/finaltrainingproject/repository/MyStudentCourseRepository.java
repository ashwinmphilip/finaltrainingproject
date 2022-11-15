package com.example.finaltrainingproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finaltrainingproject.entity.StudentCourse;

public interface MyStudentCourseRepository extends JpaRepository<StudentCourse, Long>{
	List<StudentCourse> findByBalance(double d);
	List<StudentCourse> findByBalanceGreaterThan(double d);
	List<StudentCourse> findByCourseId(Long id);
	List<StudentCourse> findByStudentId(Long id);
}
