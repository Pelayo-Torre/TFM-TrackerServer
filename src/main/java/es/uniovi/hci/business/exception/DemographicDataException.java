package es.uniovi.hci.business.exception;

public class DemographicDataException extends Exception{

	private static final long serialVersionUID = 4001710687990554589L;

	public DemographicDataException() {}

	public DemographicDataException(String message) {
		super(message);
	}

	public DemographicDataException(Throwable cause) {
		super(cause);
	}

	public DemographicDataException(String message, Throwable cause) {
		super(message, cause);
	}

}
