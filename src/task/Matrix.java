package task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import exceptions.IllegalMatrixDimensionException;
import exceptions.DivisionByZeroException;

public class Matrix {
	
	private final String name;
	private int rows = 0;
	private int cols = 0;
	private int[][] data;
	
	public Matrix(File file)  {
		this.name = file.getName().substring(0, 1);
			
		try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)))) {
				
			String line[] = null;
				
			//Reading and setting Matrix dimensions
			if (scanner.hasNextLine()) {
				line = scanner.nextLine().split(" ");
					
				if (line.length == 2) {
					try {
						this.rows = Integer.parseInt(line[0]);
						this.cols = Integer.parseInt(line[1]);
					} catch (NumberFormatException e) {
						throw new IOException("Matrix '" + this.name + "' cannot be created: " +
								"source file contains incorrect data");
					}
				}
					
				if (this.rows == 0 || this.cols == 0) {
					throw new IllegalMatrixDimensionException("Matrix '" + this.name + "' cannot be created: " +
							"the number of rows and columns must be greater than 0");
				}
			}
				
			//Initializing Matrix array
			this.data = new int[rows][cols];
				
			//Populating Matrix with data
			for (int i = 0; i < this.rows; i++) {
				line = null;
						
				if (scanner.hasNextLine()) {
					line = scanner.nextLine().split(" ");
				}
						
				if (line.length != this.cols) {
					throw new IOException("Matrix '" + this.name + "' cannot be created: " +
							"source file contains incorrect data in line " + (i+2));
				}
							
				for (int j = 0; j < this.cols; j++) {
					try {
						this.data[i][j] = Integer.parseInt(line[j]);
					} catch (NumberFormatException e) {
						throw new IOException("Matrix '" + this.name + "' cannot be created: " +
								"source file contains incorrect data");
					}													
				}						
			}						
				
		} catch (IOException | IllegalMatrixDimensionException e) {
			System.out.println(e.getMessage());
			System.out.println("Exiting...");
			System.exit(-1);
		}
	}
	
	public Matrix(String name, int[][] data) throws IllegalMatrixDimensionException {
		this.name = name;
		
		if (data != null) {
			this.rows = data.length;
			this.cols = data[0].length;
			this.data = data;
		} else {
			throw new IllegalMatrixDimensionException();
		}
	}


	public Matrix add(Matrix other) throws IllegalMatrixDimensionException {
		
		if (this.rows != other.rows || this.cols != other.cols) {
			throw new IllegalMatrixDimensionException();
		}
		
		int[][] result = new int[this.rows][this.cols];
		
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				result[i][j] = this.data[i][j] + other.data[i][j];
			}
		}
		
		return new Matrix("Sum Result", result);
	}

	public Matrix mult(Matrix other) throws IllegalMatrixDimensionException {
		
		if (this.cols != other.rows) {
			throw new IllegalMatrixDimensionException();
		}
		
		int[][] result = new int[this.rows][other.cols];				
		
        for (int i = 0; i < this.rows; i++) { 
            for (int j = 0; j < other.cols; j++) { 
                for (int k = 0; k < this.cols; k++) { 
                    result[i][j] += this.data[i][k] * other.data[k][j];
                }
            }            
		}
		
		return new Matrix("Multiplication Result", result);
	}

	public Matrix div(int divider) throws DivisionByZeroException, IllegalMatrixDimensionException {
		
		if (divider == 0) {
			throw new DivisionByZeroException();
		}
		
		int[][] result = new int[this.rows][this.cols];
		
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				result[i][j] = this.data[i][j] / divider;
			}
		}		
		
		return new Matrix("Division Result", result);
	}
	
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		
		String result = "";
		
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				result += this.data[i][j] + " ";
			}
			result += "\n";
		}
		
		return result;
	}
	
}
