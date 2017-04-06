package task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import exceptions.DivisionByZeroException;
import exceptions.IllegalMatrixDimensionException;

public class Matrix {
	private int[][] matr;
	private File file;
	private int row_count, column_count;

	public Matrix(File file) {
		this.file = file;
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			row_count = scanner.nextInt();
			column_count = scanner.nextInt();
			matr = new int[row_count][column_count];
			for (int i = 0; i < row_count; i++)
				for (int j = 0; j < column_count; j++) {
					matr[i][j] = scanner.nextInt();
				}
		} catch (FileNotFoundException e) {
			System.out.println("File with matrix data not found.");
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	private Matrix(int[][] matr) {
		this.matr = matr;
		row_count = matr.length;
		column_count = matr[0].length;
	}

	public String getName() {
		return file.getName().substring(0, file.getName().length() - 4);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < row_count; i++) {
			for (int j = 0; j < column_count; j++) {
				buffer.append(matr[i][j]);
				buffer.append(' ');
			}
			buffer.append('\n');
		}

		return buffer.toString();
	}

	public Matrix add(Matrix matrix) throws IllegalMatrixDimensionException{
		int[][] sum = new int[row_count][column_count];
		if(row_count!=matrix.row_count || column_count!=matrix.column_count){
			throw new IllegalMatrixDimensionException();
		}
		for (int i = 0; i < row_count; i++) {
			for (int j = 0; j < column_count; j++) {
				sum[i][j] = matr[i][j] + matrix.matr[i][j];
			}
		}
		return new Matrix(sum);
	}

	public Matrix mult(Matrix matrix) throws IllegalMatrixDimensionException {
		int[][] result = new int[row_count][matrix.column_count];
		if(column_count!=matrix.row_count){
			throw new IllegalMatrixDimensionException();
		}
		for (int i = 0; i < row_count; i++) {
			for (int j = 0; j < matrix.column_count; j++) {
				for (int k = 0; k < matrix.row_count; k++) {
					result[i][j] += matr[i][k] * matrix.matr[k][j];
				}
			}
		}
		return new Matrix(result);
	}

	public Matrix div(int num) throws DivisionByZeroException {
		int[][] result = new int[row_count][column_count];
		if(num==0){
			throw new DivisionByZeroException();
		}
		for (int i = 0; i < row_count; i++) {
			for (int j = 0; j < column_count; j++) {
				result[i][j] = matr[i][j] / num;
			}
		}
		return new Matrix(result);
	}

}
