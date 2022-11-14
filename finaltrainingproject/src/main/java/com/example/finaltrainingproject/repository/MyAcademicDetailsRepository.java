package com.example.finaltrainingproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.finaltrainingproject.entity.AcademicDetails;

public interface MyAcademicDetailsRepository extends JpaRepository<AcademicDetails, Long>{

}
