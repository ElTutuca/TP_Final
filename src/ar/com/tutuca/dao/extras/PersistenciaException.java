package ar.com.tutuca.dao.extras;

public class PersistenciaException extends Exception {


	public PersistenciaException() {
		super();
	}

	public PersistenciaException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public PersistenciaException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistenciaException(String message) {
		super(message);
	}

	public PersistenciaException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 3138647398080802023L;

}
