package exceptions;

public class DivisionByZeroException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DivisionByZeroException() {
		System.out.println("Cannot divide by zero!");
	}


}
