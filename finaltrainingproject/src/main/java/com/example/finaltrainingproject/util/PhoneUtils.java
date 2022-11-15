package com.example.finaltrainingproject.util;

import com.example.finaltrainingproject.exception.InvalidPhoneException;

public class PhoneUtils {
	public static boolean validatePhone(Long phone) {
		if(Long.toString(phone).length() == 10) {
			return true;
		}
		else {
			throw new InvalidPhoneException("Phone is not valid");
		}
	}
}