package co.inventorsoft.scripty.exception;

/**
 * ScriptyUncheckedException 
 * All project's custom unchecked exceptions should use or extend it
 */
public class ScriptyUncheckedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ScriptyUncheckedException() {
		super();
	}

	public ScriptyUncheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ScriptyUncheckedException(String message) {
		super(message);
	}

	public ScriptyUncheckedException(Throwable cause) {
		super(cause);
	}
}
