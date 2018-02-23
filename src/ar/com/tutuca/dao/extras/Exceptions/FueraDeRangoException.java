package ar.com.tutuca.dao.extras.Exceptions;

public class FueraDeRangoException extends Exception {

	private static final long serialVersionUID = -3264517024653073411L;

	public FueraDeRangoException() {
		super();
	}

	public FueraDeRangoException(String message) {
		super(message);
	}

	public FueraDeRangoException(Throwable cause) {
		super(cause);
	}

	public FueraDeRangoException(String message, Throwable cause) {
		super(message, cause);
	}

	public FueraDeRangoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
