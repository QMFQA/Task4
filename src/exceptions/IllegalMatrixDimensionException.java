package exceptions;


@SuppressWarnings("serial")
public class IllegalMatrixDimensionException extends Exception {
    
	public IllegalMatrixDimensionException (){
        super("Matrices have illegal dimensions for this operation\n");
    }
}
