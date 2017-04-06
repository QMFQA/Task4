package task;

import java.io.*;
import exceptions.*;
import java.util.regex.*;

public class Matrix {
	private String name;
	private int [][] elements;
	private int numRows;
	private int numColumns;

	
	private Matrix(int[][] inputElements) {
		name = "Unnamed matrix";
		numRows = inputElements.length;
		numColumns = inputElements[0].length;
		elements = new int [numRows][numColumns];
		for (int i=0; i<numRows; i++) 
			for (int j=0; j<numColumns; j++) 
				elements[i][j]=inputElements[i][j];
	}
	
	public Matrix(File input) {
		String fileName = input.getName();
		name = fileName.substring(0, fileName.length() - 4);
		try (FileReader reader = new FileReader(input); BufferedReader inputStringBuffer = new BufferedReader(reader)) {	
			String matrixDim=inputStringBuffer.readLine();
			String[] nums = getSubStrings (matrixDim);
			numRows = Integer.parseInt(nums[0]);
			numColumns = Integer.parseInt(nums[1]);
			elements = new int [numRows][numColumns];
			String tempS;
			int j=0;
			while ((tempS = inputStringBuffer.readLine()) != null) {
				nums = getSubStrings (tempS);
				for (int i=0; i<numColumns; i++) elements [j][i]= Integer.parseInt(nums[i]);
				j++;
			}
		} catch (FileNotFoundException fnf) {
			System.out.println("Cannot find the file: "+input);
			System.exit(2);
		} catch (NumberFormatException nfe) {
			System.out.println("Wrong data from file: " + nfe.getMessage());
			System.exit(13);
		}
		catch (IOException io) {
			System.out.println("Input/output error appears: " + io.getMessage());
			System.exit(12);
		}
		catch (Exception ex) {
			System.out.println("Some one else problem: " + ex.getMessage());
			System.exit(1);
		}
	}
	
	private String[] getSubStrings (String inputString) {
		Pattern pattern = Pattern.compile(" ");
		String[] numbers = pattern.split(inputString);
		return numbers;
	}
	
	
	public String getName() {
		return name;
	}

	public Matrix add(Matrix matrix) throws IllegalMatrixDimensionException {
		if ((this.numColumns != matrix.numColumns) || (this.numRows != matrix.numRows)) throw new IllegalMatrixDimensionException();
		int[][] tempElements = new int [numRows][numColumns];
		for (int i=0; i<numRows; i++)
			for (int j=0; j<numColumns; j++) tempElements[i][j] = this.elements [i][j]+matrix.elements [i][j];
		return new Matrix (tempElements);
	}

	public Matrix mult(Matrix matrix) throws IllegalMatrixDimensionException {
		if (this.numColumns != matrix.numRows) throw new IllegalMatrixDimensionException();
		int[][] tempElements = new int [this.numRows][matrix.numColumns];
		for (int i=0; i<this.numRows; i++)
			for (int j=0; j<matrix.numColumns; j++) 
				for (int r=0; r<this.numColumns; r++)
				{
					int temp = this.elements[i][r]*matrix.elements[r][j];
					tempElements[i][j]+= temp;
				}
		return new Matrix (tempElements);
	}

	public Matrix div(int divider) throws DivisionByZeroException {
		if (divider == 0) throw new DivisionByZeroException();
		int[][] tempElements = new int [numRows][numColumns];
		for (int i=0; i<numRows; i++)
			for (int j=0; j<numColumns; j++) tempElements[i][j] = this.elements [i][j]/divider;
		return new Matrix (tempElements);
	}
	
	@Override
	public String toString() {
		String tempS="";
		if (!this.name.equals("NullMatrix"))
			for (int i=0; i<numRows; i++) {
				for (int j=0; j<numColumns; j++) tempS += elements [i][j]+" "; 
				tempS += "\n";
			}
		return tempS;
	}

}
