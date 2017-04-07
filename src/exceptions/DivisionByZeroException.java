package exceptions;

public class DivisionByZeroException extends Exception{

	private static final long serialVersionUID = -3582697369518315532L;
	
	public DivisionByZeroException(){
		super("Cannot divide by zero!\n");
	}

}
