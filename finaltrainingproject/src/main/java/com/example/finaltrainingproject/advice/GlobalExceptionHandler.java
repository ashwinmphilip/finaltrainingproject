package com.example.finaltrainingproject.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.finaltrainingproject.exception.InvalidAgeException;
import com.example.finaltrainingproject.exception.InvalidEmailException;
import com.example.finaltrainingproject.exception.InvalidPhoneException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorObject> handleBadCredentialsException(BadCredentialsException e){
		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObject.setMessage("Wrong Username/password");
		return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.OK);
	}
	
	@ExceptionHandler(InvalidEmailException.class)
	public ResponseEntity<ErrorObject> handleInvalidEmailException(InvalidEmailException e){
		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObject.setMessage("Email is not valid");
		return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.OK);
	}
	
	@ExceptionHandler(InvalidPhoneException.class)
	public ResponseEntity<ErrorObject> handleInvalidPhoneException(InvalidPhoneException e){
		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObject.setMessage("Phone is not valid");
		return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.OK);
	}
	
	@ExceptionHandler(InvalidAgeException.class)
	public ResponseEntity<ErrorObject> handleInvalidAgeException(InvalidAgeException e){
		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObject.setMessage("Age is not valid");
		return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.OK);
	}
	
}
