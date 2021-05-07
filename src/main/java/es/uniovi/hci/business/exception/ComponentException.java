package es.uniovi.hci.business.exception;

public class ComponentException extends Exception{

	private static final long serialVersionUID = 4001710687990554589L;

	public ComponentException() {}

	public ComponentException(String message) {
		super(message);
	}

	public ComponentException(Throwable cause) {
		super(cause);
	}

	public ComponentException(String message, Throwable cause) {
		super(message, cause);
	}

}
