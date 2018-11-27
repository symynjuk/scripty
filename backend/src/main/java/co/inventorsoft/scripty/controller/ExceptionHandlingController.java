package co.inventorsoft.scripty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import co.inventorsoft.scripty.exception.ApplicationException;
import co.inventorsoft.scripty.model.dto.StringResponse;

/**
 * global ExceptionHandler for ApplicationException
 */
@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<StringResponse> exceptionHandler(ApplicationException ae) {
		return ResponseEntity.status(ae.getCode()).body(new StringResponse(ae.getMessage()));
	}

}
