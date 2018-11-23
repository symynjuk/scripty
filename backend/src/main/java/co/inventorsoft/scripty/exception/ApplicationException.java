package co.inventorsoft.scripty.exception;

import org.springframework.http.HttpStatus;

/**
 * ApplicationException 
 * All project's custom exceptions should use or extend it
 */
public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final HttpStatus code;

	public ApplicationException() {
		super();
		this.code = HttpStatus.BAD_REQUEST;
	}

	public ApplicationException(HttpStatus code) {
		super();
		this.code = code;
	}

	public ApplicationException(String message, Throwable cause, HttpStatus code) {
		super(message, cause);
		this.code = code;
	}

	public ApplicationException(String message, HttpStatus code) {
		super(message);
		this.code = code;
	}

	public ApplicationException(Throwable cause, HttpStatus code) {
		super(cause);
		this.code = code;
	}

	public HttpStatus getCode() {
		return code;
	}

}
