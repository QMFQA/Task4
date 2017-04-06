package main;

import java.io.File;

import exceptions.DivisionByZeroException;
import exceptions.IllegalMatrixDimensionException;
import task.Matrix;

public class Main {

	private static final  Matrix[] MATRIX = { 
			new Matrix(new File("A.txt")), 
			new Matrix(new File("B.txt")), 
			new Matrix(new File("C.txt"))
			};

	public static void main(String[] args) throws IllegalMatrixDimensionException, DivisionByZeroException {
		
		for (int i = 0; i < MATRIX.length; i++) {
			for (int j = 0; j < MATRIX.length; j++) {
				try {
					if (i + j % 2 == 0) {
						System.out.println(MATRIX[i].getName() + " + " + MATRIX[j].getName() + " =");
						System.out.println(MATRIX[i].add(MATRIX[j]));
					} else {
						System.out.println(MATRIX[i].getName() + " * " + MATRIX[j].getName() + " =");
						System.out.println(MATRIX[i].mult(MATRIX[j]));
					}
				} catch (IllegalMatrixDimensionException imde) {
					System.out.println(imde.getMessage()+"\n");
				}
			}
			try {
				System.out.println(MATRIX[i].getName() + " / " + i + " =");
				System.out.println(MATRIX[i].div(i));
			} catch (DivisionByZeroException dbze) {
				System.out.println(dbze.getMessage()+"\n");
			}
		}

	}
	
}

