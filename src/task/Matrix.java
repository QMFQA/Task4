package task;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import exceptions.DivisionByZeroException;
import exceptions.IllegalMatrixDimensionException;

public class Matrix {
	
	private int rows;
	private int columns;
	private int [][] matrix ;
	private String name;
	
	public Matrix(int rows, int columns){
		this.columns = columns;
		this.rows = rows;
		matrix = new int[rows][columns];
	}
	
	public Matrix(File f){
		
		name = f.getName().substring(0, f.getName().indexOf("."));
		List<String> lines = null;
		
		try {
			lines = Files.readAllLines(Paths.get(f.getPath()), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int i=0;
		for(String line:lines){
			
			String[] parts = line.split(" ");
			if (i==0) {
				rows = Integer.parseInt(parts[0]);
				columns = Integer.parseInt(parts[1]);
				matrix = new int[rows][columns];
			}
			else {
				for(int j=0; j<this.columns; j++)
				{
					matrix[i-1][j] = Integer.parseInt(parts[j]);
				}
			}
			i++;
		}
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		
		String s = "";
		for(int i=0; i<rows; i++){
			for(int j=0; j<columns; j++){
				s += matrix[i][j]+" ";
			}
			s+="\n";
		}
		return s;	
	}
	
	public Matrix add(Matrix m) throws IllegalMatrixDimensionException {

		if (rows != m.rows && columns != m.columns) throw new IllegalMatrixDimensionException();
		
		Matrix newMatrix = new Matrix(rows, columns);
		
		for(int i=0; i<rows; i++)
			for(int j=0; j<columns; j++)
				newMatrix.matrix[i][j] = matrix[i][j]+m.matrix[i][j];
		return newMatrix;
		
	}
	
	
	public Matrix mult(Matrix m) throws IllegalMatrixDimensionException {
		
		if (columns != m.rows) throw new IllegalMatrixDimensionException();
		
		Matrix newMatrix = new Matrix(rows, m.columns);
		
		for(int i=0; i<rows; i++)
			for(int j=0; j<m.columns; j++)
				for(int r=0; r<m.rows; r++)
					newMatrix.matrix[i][j] += matrix[i][r]*m.matrix[r][j];
		return newMatrix;
		
	}
	
	public Matrix div(int num) throws DivisionByZeroException {
		
		if (num == 0) throw new DivisionByZeroException();
		
		Matrix newMatrix = new Matrix(rows,columns);
		
		for(int i=0; i<rows; i++)
			for(int j=0; j<columns; j++)
				newMatrix.matrix[i][j] = matrix[i][j]/num;
		return newMatrix;
		
	}

}
