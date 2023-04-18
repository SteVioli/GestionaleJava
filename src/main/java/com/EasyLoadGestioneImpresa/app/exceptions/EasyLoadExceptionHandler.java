package com.EasyLoadGestioneImpresa.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class EasyLoadExceptionHandler extends ResponseEntityExceptionHandler {
		
	
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<String> manageEntityNotFoundException(EntityNotFoundException e){
		return new ResponseEntity<String>(e.getMessage() + " --EasyLoad-- ", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<String> RuntimeException(RuntimeException e){
		return new ResponseEntity<String>(e.getMessage() + " --EasyLoad-- ", HttpStatus.NOT_FOUND);
	}
}
