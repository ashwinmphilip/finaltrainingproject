package com.example.finaltrainingproject.dto;

import com.example.finaltrainingproject.entity.AcademicDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateConfirmation {
	
    private String status;
    private AcademicDetails academicDetails;

}
