package exceptions;

public class IllegalMatrixDimensionException extends Exception {

	public IllegalMatrixDimensionException() {
		// TODO Auto-generated constructor stub
		super( "Matrices have illegal dimensions for this operation" );
	}

	public IllegalMatrixDimensionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IllegalMatrixDimensionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public IllegalMatrixDimensionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IllegalMatrixDimensionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
