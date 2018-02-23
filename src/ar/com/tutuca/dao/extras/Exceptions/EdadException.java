package ar.com.tutuca.dao.extras.Exceptions;

public class EdadException extends Exception {

	private static final long serialVersionUID = 8070571258217517271L;
	
	public EdadException() {
		super();
	}

	public EdadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public EdadException(String message, Throwable cause) {
		super(message, cause);
	}

	public EdadException(String message) {
		super(message);
	}

	public EdadException(Throwable cause) {
		super(cause);
	}


}