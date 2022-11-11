package com.example.finaltrainingproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finaltrainingproject.entity.StudentCourse;

public interface MyStudentCourseRepository extends JpaRepository<StudentCourse, Long>{

}
