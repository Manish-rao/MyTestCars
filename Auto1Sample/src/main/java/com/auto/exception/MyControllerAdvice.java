package com.auto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class MyControllerAdvice {
	@ExceptionHandler(ManufacturerNotFoundException.class)
	public final ResponseEntity<ExceptionMessage> ManufacturerNotFoundException(ManufacturerNotFoundException ex, WebRequest request){
		ExceptionMessage error = new ExceptionMessage("Manufacturuer not found", ex.getMessage());
		return new ResponseEntity<ExceptionMessage>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionMessage> ResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
		ExceptionMessage error = new ExceptionMessage("Resource Not Found:", ex.toString());
		return new ResponseEntity<ExceptionMessage>(error, HttpStatus.NOT_FOUND);
	}
}
