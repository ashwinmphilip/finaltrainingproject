package com.example.finaltrainingproject.dto;

import java.util.List;

import com.example.finaltrainingproject.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowStudentByDateConfirmation {
	private List<User> list;
}
