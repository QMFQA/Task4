package exceptions;

@SuppressWarnings("serial")
public class DivisionByZeroException extends ArithmeticException {
	public DivisionByZeroException () {
		super("Cannot divide by zero!\n");		
	}
}
