package main;

import java.io.File;

public class Main {

	private static final Matrix[] MATRIX = { 
			new Matrix(new File("A.txt")), 
			new Matrix(new File("B.txt")), 
			new Matrix(new File("C.txt"))
			};

	public static void main(String[] args) throws DivisionByZeroException, IllegalMatrixDimensionException {
		for (int i = 0; i < MATRIX.length; i++) {
			for (int j = 0; j < MATRIX.length; j++) {
				if (i + j % 2 == 0) 
					try {
						System.out.println(MATRIX[i].getName() + " + " + MATRIX[j].getName() + " =");
						System.out.println(MATRIX[i].add(MATRIX[j]));
					}
					catch (IllegalMatrixDimensionException e){
						System.out.println("Matrices have illegal dimensions for this operation\n");
					}
				else 
					try {
						System.out.println(MATRIX[i].getName() + " * " + MATRIX[j].getName() + " =");
						System.out.println(MATRIX[i].mult(MATRIX[j]));
					}
					catch (IllegalMatrixDimensionException e){
						System.out.println("Matrices have illegal dimensions for this operation\n");
					}
			}
			try {
				System.out.println(MATRIX[i].getName() + " / " + i + " =");
				System.out.println(MATRIX[i].div(i));
			}
			catch (DivisionByZeroException e){
				System.out.println("Cannot divide by zero!\n");
			}
		}
	}

}
