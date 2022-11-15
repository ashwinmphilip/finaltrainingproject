package com.example.finaltrainingproject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACADEMIC_DETAILS")
public class AcademicDetails {
	
	@Id
	private Long id;
	private String department;
	private int marks;
}
