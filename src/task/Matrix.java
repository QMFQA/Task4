package task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import exceptions.IllegalMatrixDimensionException;
import exceptions.DivisionByZeroException;

public class Matrix {
	
	private final String name;
	private int[][] data;

	public Matrix(String name, int[][] data) throws IllegalMatrixDimensionException {
		this.name = name;
		
		if (data != null) {
			this.data = data;
		} else {
			throw new IllegalMatrixDimensionException();
		}
	}	
	
	public Matrix(File file)  {
		this.name = file.getName().substring(0, 1);
		
		int[][] data = null;
		
		try {
			data = readMatrixFromFile(file);
		} catch (IllegalMatrixDimensionException e) {
			System.out.println("Matrix '" + this.name + "' cannot be created: " + 
					e.getLocalizedMessage());
		}
		
		if (data != null) {
			this.data = data;
		} else {
			System.out.println("Exiting...");
			System.exit(-1);
		}		
	}

	private int[][] readMatrixFromFile(File file) throws IllegalMatrixDimensionException {
		int[][] data = null;
		int rows = 0;
		int cols = 0;
		
		try (BufferedReader buffer = new BufferedReader(new FileReader(file))) {		
			
			String line = null;
			
			//Reading and setting Matrix dimensions
			if ((line = buffer.readLine()) != null) {
				try (Scanner scanner = new Scanner(line)) {
					rows = scanner.nextInt();
					cols = scanner.nextInt();
				} 								
			}
			
			if (rows == 0 || cols == 0) {
				throw new IllegalMatrixDimensionException("The number of rows and columns must be greater than 0");
			}
			
			data = new int[rows][cols];
			
			for (int i = 0; i < rows; i++) {
				line = null;
						
				if ((line = buffer.readLine()) != null) {				
					try (Scanner scanner = new Scanner(line)) {
						for (int j = 0; j < cols; j++) {
							data[i][j] = scanner.nextInt();
						}						
					}
				}						
			}				
			
		} catch (IOException e) {
			System.out.println("Matrix cannot be created: Cannot read file: " + 
					e.getLocalizedMessage());		
		}
		
		return data; 
	}


	public Matrix add(Matrix other) throws IllegalMatrixDimensionException {
		
		if (this.getRowsCount() != other.getRowsCount() || 
				this.getColsCount() != other.getColsCount()) 
		{
			throw new IllegalMatrixDimensionException();
		}
		
		int[][] result = new int[this.getRowsCount()][this.getColsCount()];
		
		for (int i = 0; i < this.getRowsCount(); i++) {
			for (int j = 0; j < this.getColsCount(); j++) {
				result[i][j] = this.data[i][j] + other.data[i][j];
			}
		}
		
		return new Matrix("Sum Result", result);
	}

	public Matrix mult(Matrix other) throws IllegalMatrixDimensionException {
		
		if (this.getColsCount() != other.getRowsCount()) {
			throw new IllegalMatrixDimensionException();
		}
		
		int[][] result = new int[this.getRowsCount()][other.getColsCount()];				
		
        for (int i = 0; i < this.getRowsCount(); i++) { 
            for (int j = 0; j < other.getColsCount(); j++) { 
                for (int k = 0; k < this.getColsCount(); k++) { 
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
		
		int[][] result = new int[this.getRowsCount()][this.getColsCount()];
		
		for (int i = 0; i < this.getRowsCount(); i++) {
			for (int j = 0; j < this.getColsCount(); j++) {
				result[i][j] = this.data[i][j] / divider;
			}
		}		
		
		return new Matrix("Division Result", result);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getRowsCount() {
		return this.data.length;
	}
	
	public int getColsCount() {
		return this.data[0].length;
	}

	@Override
	public String toString() {
		
		String result = "";
		
		for (int i = 0; i < this.getRowsCount(); i++) {
			for (int j = 0; j < this.getColsCount(); j++) {
				result += this.data[i][j] + " ";
			}
			result += "\n";
		}
		
		return result;
	}
	
}
