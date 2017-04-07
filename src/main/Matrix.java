package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Matrix {
	
	private int[][] matrix;
	private int rowsCount, columnsCount;
	private String name;
	
	public Matrix(File f){
		readFile(f);
		name = f.getName().replace(".txt", "");
	}

	public Matrix(int rows, int columns){
		matrix = new int[rows][columns];
		rowsCount = rows;
		columnsCount = columns;
	}

	/*
	private void setValue(int row, int column, int value){
		matrix[row][column] = value;
	}
	*/
	
	private int getElement(int row, int column){
		return matrix[row][column];
	}
	
	private int getRowsCount(){
		return rowsCount;
	}
	
	private int getColumnsCount(){
		return columnsCount;
	}
	
	public String getName() {
		return name;
	}
	
	public Matrix add(Matrix m2) throws IllegalMatrixDimensionException{
		if (m2.getRowsCount() != this.rowsCount || m2.getColumnsCount() != this.columnsCount)
			throw new IllegalMatrixDimensionException();
		Matrix result_matrix = new Matrix(this.rowsCount,this.columnsCount);
		for (int i=0; i < this.rowsCount; i++)
			for (int j=0; j<this.columnsCount; j++)
				result_matrix.matrix[i][j] = m2.getElement(i, j) + matrix[i][j];
		return result_matrix;
	}
	
	public Matrix mult(Matrix m2) throws IllegalMatrixDimensionException{
		Matrix matrix_mult;
		if (m2.getRowsCount() == matrix[0].length) {
			matrix_mult = new Matrix(rowsCount, m2.getColumnsCount());
			int matrix_mult_element;
			for (int m1_rows = 0; m1_rows < matrix.length; m1_rows++ )
			{						
				for (int matrix_mult_col = 0; matrix_mult_col < matrix_mult.getColumnsCount(); matrix_mult_col++)
				{
						matrix_mult_element = 0;
						for (int m2_rows = 0; m2_rows < m2.getRowsCount(); m2_rows++)
							matrix_mult_element += matrix[m1_rows][m2_rows]*m2.getElement(m2_rows, matrix_mult_col);
						matrix_mult.matrix[m1_rows][matrix_mult_col] = matrix_mult_element;
				}
			}
			return matrix_mult;
		}
		else 
			throw new IllegalMatrixDimensionException();
		
	}
	
	public Matrix div(int divider) throws DivisionByZeroException{
		if (divider != 0) {
			Matrix matrix_div = new Matrix(rowsCount, columnsCount);
			for (int i=0; i<matrix_div.getRowsCount(); i++)
				for (int j=0; j<matrix_div.getColumnsCount(); j++)
						matrix_div.matrix[i][j] = matrix[i][j]/divider;
			return matrix_div;
		}
		else throw new DivisionByZeroException();
	}

	private void readFile(File f) 
	{
		ArrayList<String> fileContent = new ArrayList<>();
		try(BufferedReader reader = new BufferedReader(new FileReader(f))) 
        {
            String line;          		
            while ((line = reader.readLine()) != null){
                fileContent.add(line);
            }
            
            //matrix size
            line = fileContent.get(0);
            int space_index = line.indexOf(" ");
            rowsCount = Integer.parseInt(line.substring(0, space_index));
            columnsCount = Integer.parseInt(line.substring(space_index+1));
            
            //matrix content
            matrix = new int[rowsCount][columnsCount];
            String temp;
            for (int row_ind = 0; row_ind < rowsCount; row_ind++)
            {
            	line = fileContent.get(row_ind+1);
            	for (int column_ind = 0; column_ind < columnsCount; column_ind++){
            		space_index = line.indexOf(" ");
            		if (space_index != -1){
            			temp = line.substring(0, space_index);
            			matrix[row_ind][column_ind] = Integer.parseInt(temp);
            			line = line.replace(temp + " ", "");
            		}
            		else
            			matrix[row_ind][column_ind] = Integer.parseInt(line);
            	}
            }	
            
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
	}
	
	@Override
	public String toString(){
		String outline = "";
		for (int row = 0; row < matrix.length; row++)
		{
			for (int column=0; column < matrix[row].length; column ++)
				outline += matrix[row][column] + " ";
			outline += "\n";
		}
		return outline;
	}

}
