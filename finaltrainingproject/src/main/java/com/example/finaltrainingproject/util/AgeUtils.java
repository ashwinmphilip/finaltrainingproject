package com.example.finaltrainingproject.util;

import java.util.Date;

import com.example.finaltrainingproject.exception.InvalidAgeException;

public class AgeUtils {
	public static boolean validateAge(Date dob) {
		Date d = new Date();
		long k = d.getTime() - dob.getTime();
		k = (long) (k * 3.171 * Math.pow(10, -11));
		if(k >= 18) {
			return true;
		}
		else {
			throw new InvalidAgeException("Age is not valid");
		}
	}
}
