package com.example.ExceptionHandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleValidationErrors(MethodArgumentNotValidException e){
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errors);
	}
	// Handle custom exceptions or generic runtime errors
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String,String>> handleRuntimeException(RuntimeException e){
		Map<String,String> error = new HashMap<>();
		error.put("error: ", e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	// Handle other unexpected exceptions
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
	        Map<String, String> error = new HashMap<>();
	        error.put("error", "An unexpected error occurred: " + ex.getMessage());
	        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
