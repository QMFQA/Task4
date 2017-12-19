package exceptions;

public class IllegalMatrixDimensionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalMatrixDimensionException() {
		super("Matrices have illegal dimensions for this operation\n");
	}

	public IllegalMatrixDimensionException(String message) {
		super(message);
	}
	
	
}
