package com.viv.test.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
 
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest req) {
		ExceptionResponseMessage message=
				                     new ExceptionResponseMessage(new Date(),ex.getMessage(), req.getDescription(false));
     
		return new ResponseEntity<Object>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> UserNotFoundException(Exception ex, WebRequest req) {
		ExceptionResponseMessage message=
				                     new ExceptionResponseMessage(new Date(),ex.getMessage(), req.getDescription(false));
     
		return new ResponseEntity<Object>(message, HttpStatus.NOT_FOUND);
	}
}
