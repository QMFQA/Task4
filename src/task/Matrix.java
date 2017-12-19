package task;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import exceptions.IllegalMatrixDimensionException;
import exceptions.DivisionByZeroException;

public class Matrix {
	
	public final String name;
	public int rows = 0;
	public int cols = 0;
	public int[][] matrix;
	
	public Matrix(File file) {
		this.name = file.getName().substring(0, 1);
		
		try (Scanner scanner = new Scanner(file)) {
			rows = scanner.nextInt();
			cols = scanner.nextInt();
			
			this.matrix = new int[rows][cols];
			
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					matrix[i][j] = scanner.nextInt();
				}
			}
			
		} catch (IOException e) {
			System.out.println("Couldn't load matrix from file.");
			e.printStackTrace();
		}
	}

	public String add(Matrix other) throws IllegalMatrixDimensionException {
		
		if (this.rows != other.rows || this.cols != other.cols) {
			throw new IllegalMatrixDimensionException();
		}
		
		int sumResult = 0;
		String result = "";
		
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				sumResult = this.matrix[i][j] + other.matrix[i][j];
				result += sumResult + " ";
			}
			
			result += "\n";
		}
		
		return result;
	}

	public String mult(Matrix other) throws IllegalMatrixDimensionException {
		
		if (this.cols != other.rows) {
			throw new IllegalMatrixDimensionException();
		}
		
		int[][] multResult = new int[this.rows][other.cols];
		String result = "";
				
		
        for (int i = 0; i < this.rows; i++) { 
            for (int j = 0; j < other.cols; j++) { 
                for (int k = 0; k < this.cols; k++) { 
                    multResult[i][j] += this.matrix[i][k] * other.matrix[k][j];
                }
            }            
		}
        
        for (int i = 0; i < this.rows; i++) { 
            for (int j = 0; j < other.cols; j++) { 
            	result += multResult[i][j] + " ";
            }
            
            result += "\n";
        }
		
		return result;
	}

	public String div(int divider) throws DivisionByZeroException {
		
		if (divider == 0) {
			throw new DivisionByZeroException();
		}
		
		String result = "";
		int divResult = 0;
		
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				divResult = this.matrix[i][j] / divider;
				result += divResult + " ";
			}
			
			result += "\n";
		}		
		
		return result;
	}
	
	public String getName() {
		return this.name;
	}


}
