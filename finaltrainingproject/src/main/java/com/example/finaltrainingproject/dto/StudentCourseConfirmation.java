package com.example.finaltrainingproject.dto;

import com.example.finaltrainingproject.entity.StudentCourse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseConfirmation {
	private StudentCourse studentCourse;
}
