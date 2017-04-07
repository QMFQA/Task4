package task;

import java.io.*;

import exceptions.DivisionByZeroException;
import exceptions.IllegalMatrixDimensionException;

public class Matrix {
	
	public int sourceMatrix [][];
	private String name;
	private int countRows;
	private int countColumns;
	public int resultMatrix [][];
	public Matrix(File file) {
		
		try {
			FileReader file1 = new FileReader(file);
			name = file.getName().split("\\.")[0];
			
			BufferedReader reader = new BufferedReader(file1);
			String line = reader.readLine();
			countRows = Integer.parseInt(line.split(" ")[0]);
			countColumns = Integer.parseInt(line.split(" ")[1]);
			sourceMatrix = new int[countRows][countColumns];
			int i=0;
			while ((line = reader.readLine()) != null) 
			{
	         String [] str = line.split(" ");
	         
	         for (int j=0; j<str.length; j++)
	         {
	        	 sourceMatrix[i][j]=Integer.parseInt(str[j]);
	         }
	         
	         i++;
	        }
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			
		} catch (IOException e) {
			System.out.println("String is empty");
		}
		
	}
	
	public String getName()
	{
		return name;
	}

	public String mult(Matrix secondMatrix) throws IllegalMatrixDimensionException
	{
		if (countRows!=secondMatrix.countColumns)
			throw new IllegalMatrixDimensionException();
		resultMatrix = new int[countRows][secondMatrix.countColumns];
		String str = new String();
		for (int i = 0; i < countRows; i++) {
            for (int j = 0; j < secondMatrix.countColumns; j++) {
            	for (int k = 0; k<countColumns; k++)
            		resultMatrix[i][j] = resultMatrix[i][j] + sourceMatrix[i][k] * secondMatrix.sourceMatrix[k][j];
                
                str += resultMatrix [i][j] + " ";
                
            }
           str += "\n";
        }
		return str;
	}
	
	public String add(Matrix secondMatrix) throws IllegalMatrixDimensionException
	{
		if (countRows!=secondMatrix.countRows)
			throw new IllegalMatrixDimensionException();
		resultMatrix = new int[countRows][countColumns];
		String str = new String();
		for (int i = 0; i < countRows; i++) {
            for (int j = 0; j < countColumns; j++) {
            	resultMatrix[i][j] = sourceMatrix[i][j] + secondMatrix.sourceMatrix[i][j];
                
                str += resultMatrix [i][j] + " ";
                
            }
            str += "\n";
        }
		return str;
	}
	
	public String div(int divisor) throws DivisionByZeroException
	{
		if (divisor == 0)
			throw new DivisionByZeroException();
		String str = new String();
		resultMatrix = new int[countRows][countColumns];
		for (int i = 0; i < countRows; i++) {
            for (int j = 0; j < countColumns; j++) {
            	resultMatrix[i][j] = sourceMatrix[i][j]/divisor;
            	str += resultMatrix [i][j] + " ";
            }
            str += "\n";
		}
		return str;
	}

//	public String print() {
//		String str = new String();
//		for (int i = 0; i < countRows; i++) {
//            for (int j = 0; j < resultMatrix.length/countRows; j++) {
//            	str += resultMatrix [i][j] + " ";
//            }
//            str += "\n";
//		}
//		return str;
//	}


	
	
	
	
	

}
