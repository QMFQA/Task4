package main;

public class DivisionByZeroException extends Exception  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DivisionByZeroException() {
        super("Cannot divide by zero!");
    }

}
