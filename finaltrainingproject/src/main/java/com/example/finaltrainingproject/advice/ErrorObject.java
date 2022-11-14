package com.example.finaltrainingproject.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorObject {
	private Integer statusCode;
	private String message;
}
