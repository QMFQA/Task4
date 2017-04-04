package exceptions;

public class DivisionByZeroException extends Exception{
	public DivisionByZeroException() {
		super("Cannot divide by zero!");
	}
}
