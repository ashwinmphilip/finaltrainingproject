package com.example.finaltrainingproject.util;

import com.example.finaltrainingproject.exception.InvalidEmailException;

public class EmailUtils {
	public static boolean validateEmail(String email) {
		if(email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}")) {
			return true;
		}
		else {
			throw new InvalidEmailException("Email is not valid");
		}
	}
}
