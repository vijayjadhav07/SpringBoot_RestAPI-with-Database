package com.vijay.fp.exception;

import java.util.Date;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public HashMap<String, Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("Time", new Date());
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			map.put(error.getField(), error.getDefaultMessage());
		});
		return map;
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> ProductNotFoundException(ProductNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.OK);

	}

}
// Bindingresult bindingresult= ex.getBindingresult();
// List<FieldError> fieldErrors= bindingresult.getFieldErrors();
// for(FieldError fieldError : fieldErrors){map.put(fieldError.getField(), fieldError.getDefaultMessage());}
//  return map;
