package es.uniovi.hci.business.exception;

public class TrackerException extends Exception{
	
	private static final long serialVersionUID = 4001710687990554589L;

	public TrackerException() {}

	public TrackerException(String message) {
		super(message);
	}

	public TrackerException(Throwable cause) {
		super(cause);
	}

	public TrackerException(String message, Throwable cause) {
		super(message, cause);
	}

}
