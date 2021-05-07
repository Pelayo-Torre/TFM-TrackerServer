package es.uniovi.hci.business.exception;

public class ExperimentException extends Exception{

	private static final long serialVersionUID = 4001710687990554589L;

	public ExperimentException() {}

	public ExperimentException(String message) {
		super(message);
	}

	public ExperimentException(Throwable cause) {
		super(cause);
	}

	public ExperimentException(String message, Throwable cause) {
		super(message, cause);
	}

}
