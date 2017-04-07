package exceptions;

public class IllegalMatrixDimensionException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalMatrixDimensionException() {
		System.out.println("Matrices have illegal dimensions for this operation");
	}

}
