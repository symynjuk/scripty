package co.inventorsoft.scripty.exception;

/**
 * ScriptyException 
 * All project's custom exceptions should use or extend it
 */
public class ScriptyException extends Exception {
	private static final long serialVersionUID = 1L;

	public ScriptyException() {
		super();
	}

	public ScriptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public ScriptyException(String message) {
		super(message);
	}

	public ScriptyException(Throwable cause) {
		super(cause);
	}
}
