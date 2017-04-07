package main;

import java.io.File;

import exceptions.DivisionByZeroException;
import exceptions.IllegalMatrixDimensionException;
import task.Matrix;

public class Main {

	private static final Matrix[] MATRIX = { 
			new Matrix(new File("A.txt")), 
			new Matrix(new File("B.txt")), 
			new Matrix(new File("C.txt"))
			};

	public static void main(String[] args) {

		for (int i = 0; i < MATRIX.length; i++) {
			for (int j = 0; j < MATRIX.length; j++) {
				if (i + j % 2 == 0) {
					System.out.println(MATRIX[i].getName() + " + " + MATRIX[j].getName() + " =");
					try {
						System.out.println(MATRIX[i].add(MATRIX[j]));
					} catch (IllegalMatrixDimensionException e) {
						System.out.println(e.getMessage());
						
					}
				} else {
					System.out.println(MATRIX[i].getName() + " * " + MATRIX[j].getName() + " =");
					try {
						System.out.println(MATRIX[i].mult(MATRIX[j]));
					} catch (IllegalMatrixDimensionException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			System.out.println(MATRIX[i].getName() + " / " + i + " =");
			try {
				System.out.println(MATRIX[i].div(i));
			} catch (DivisionByZeroException e) {
				System.out.println(e.getMessage());
			}
		}

	}

}
