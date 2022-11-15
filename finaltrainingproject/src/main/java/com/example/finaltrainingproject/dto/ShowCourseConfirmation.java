package com.example.finaltrainingproject.dto;

import com.example.finaltrainingproject.entity.Course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowCourseConfirmation {
	private Course course;
}
