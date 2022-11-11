package com.example.finaltrainingproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finaltrainingproject.entity.Course;

public interface MyCourseRepository extends JpaRepository<Course, Long>{

}
