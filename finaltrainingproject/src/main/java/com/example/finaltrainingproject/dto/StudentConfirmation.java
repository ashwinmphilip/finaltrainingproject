package com.example.finaltrainingproject.dto;

import com.example.finaltrainingproject.entity.AcademicDetails;
import com.example.finaltrainingproject.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentConfirmation {
	
    private String status;
    private User user;
    private AcademicDetails academicDetails;

}
