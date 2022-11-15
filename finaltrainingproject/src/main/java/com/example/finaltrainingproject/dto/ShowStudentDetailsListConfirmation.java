package com.example.finaltrainingproject.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowStudentDetailsListConfirmation {
	private List<StudentConfirmation> list;
}
