package main;

public class IllegalMatrixDimensionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IllegalMatrixDimensionException() {
        super("Matrices have illegal dimensions for this operation");
    }


}
