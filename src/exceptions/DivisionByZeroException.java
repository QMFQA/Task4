package exceptions;

public class DivisionByZeroException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DivisionByZeroException() {
		super("Cannot divide by zero!\n");
	}

	public DivisionByZeroException(String message) {
		super(message);
	}

}
