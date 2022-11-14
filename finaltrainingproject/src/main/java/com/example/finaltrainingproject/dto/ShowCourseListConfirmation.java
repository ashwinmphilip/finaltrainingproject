package com.example.finaltrainingproject.dto;

import java.util.List;

import com.example.finaltrainingproject.entity.Course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowCourseListConfirmation {
	private List<Course> list;
}
