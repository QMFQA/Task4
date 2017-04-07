package task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;

import exceptions.DivisionByZeroException;
import exceptions.IllegalMatrixDimensionException;

public class Matrix {

	private int matrix[][];
	private String name;
	
	public Matrix(File file)  {
		this.name = FilenameUtils.removeExtension(file.getName());
		int cols, rows;
		try (BufferedReader buf = new BufferedReader(new FileReader(file.getPath()))){			
			String[] dimension = buf.readLine().split(" ");
			rows = Integer.parseInt(dimension[0]);
			cols = Integer.parseInt(dimension[1]);
			matrix = new int[rows][cols];
			for (int i = 0; i < rows; i++)
			{
				String line = buf.readLine();
				for (int j = 0; j < cols; j++)													
					matrix[i][j] = Integer.parseInt(line.split(" ")[j]);		
			}
			buf.close();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}			
	}
	
	private Matrix(int matrix[][])
	{
		int cols = matrix[0].length;
		int rows = matrix.length;
		this.matrix = new int[rows][cols];
		for(int i = 0; i < rows; i++){
	        for(int j = 0; j < cols; j++)
		       	this.matrix[i][j] = matrix[i][j];
		}
	}
	
	private int getRowsNumber(){
		return matrix.length;
	}

	private int getColsNumber(){
		return matrix[0].length;
	}
	
	private int[][] getArray(){
		return matrix;
	}
	
	public String getName() {		
		return name;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();	
		for (int i = 0; i < getRowsNumber(); i++)
		{
			for (int value : matrix[i]) {
				builder.append(value);
				builder.append(" ");
			}
			builder.append("\n");
		}
		return builder.toString();
	}
	
	public Matrix add(Matrix matrix) throws IllegalMatrixDimensionException {
		if ((getRowsNumber() != matrix.getRowsNumber()) || (getColsNumber() != matrix.getColsNumber())){
			throw new IllegalMatrixDimensionException();
		}
		int matrixSum[][] = new int[getRowsNumber()][getColsNumber()];
		for(int i = 0; i < this.matrix.length; i++){
	        for(int j = 0; j < this.matrix[0].length; j++)
	        	matrixSum[i][j] = this.matrix[i][j] + matrix.getArray()[i][j];
		}   	
	    return new Matrix(matrixSum);
	}

	public Matrix mult(Matrix matrix) throws IllegalMatrixDimensionException {
		if (getColsNumber()!= matrix.getRowsNumber())
		{
			throw new IllegalMatrixDimensionException();
		}
		int matrixMult[][] = new int[getRowsNumber()][matrix.getColsNumber()];
		for(int i = 0; i < getRowsNumber(); i++){
	        for(int j = 0; j < matrix.getColsNumber(); j++)
	        {
	        	for(int k = 0; k < getColsNumber(); k++){
	               	matrixMult[i][j] += this.matrix[i][k] * matrix.getArray()[k][j];
	        	}
	        }  
		}
	    return new Matrix(matrixMult);
	}

	public Matrix div(int denominator) throws DivisionByZeroException {
		if (denominator == 0)
		{
			throw new DivisionByZeroException();
		}
		int matrixDivided[][] = new int[getRowsNumber()][getColsNumber()];
		for (int k = 0; k < getRowsNumber(); k++)
		{
			for (int j = 0; j < getColsNumber(); j++) {
				matrixDivided[k][j] = matrix[k][j]/denominator;
			}
		}
		return new Matrix(matrixDivided);
	}

}
