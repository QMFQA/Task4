package task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import exceptions.DivisionByZeroException;
import exceptions.IllegalMatrixDimensionException;

public class Matrix {
	private int body[][];
	String name;
	
	public Matrix( int rows, int cols, String name ) throws IllegalMatrixDimensionException {
		if( rows <= 0 || cols <= 0 ) {
			throw( new IllegalMatrixDimensionException("Dimensions must be greater than zero: [" + rows + "][" + cols + "]") );
		}
		body = new int[rows][cols];
		this.name = name;
	}
	
	public Matrix( File inFile ) {
		int nRows, nCols;
		
			// Set the name of the matrix
		name = inFile.getName().substring(0, 1);
		
		try (BufferedReader reader = new BufferedReader(
		                new FileReader(inFile))){
			String line;
					// Read the header
		    if ((line = reader.readLine()) != null) {
		       	try( Scanner sc = new Scanner( line ) ) {
		       		nRows = sc.nextInt();
		       		nCols = sc.nextInt();
		       	}
		    } else {
		    	return;
		    }
		    	
			if( nRows <= 0 || nCols <= 0 ) {
				throw( new IllegalMatrixDimensionException( "Matrix " + name + ": Dimensions must be greater than zero [" + nRows + "][" + nCols + "]" ) );
			}

					//Read the body of matrix
			body = new int[nRows][nCols];
			for (int i=0; i < nRows; i++ ) {
				if ((line = reader.readLine()) != null) {
					try (Scanner sc = new Scanner( line )) {
						for (int j=0; j < nCols; j++ ) {
							int f = sc.nextInt();
							body[i][j] = f;
						}	
					}
				}
			} 
		} catch (IOException | IllegalMatrixDimensionException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
			System.exit(-1);
		} 
	}

	public String getName() {
		return name;
	}

	public void setItem( int row, int col, int value ) {
		body[row][col] = value;
	}

	public int getItem( int row, int col ) {
		return body[row][col];
	}

	public int getnRows() {
		return body.length;
	}

	public int getnCols() {
		return body[0].length;
	}

	public Matrix add( Matrix m ) throws IllegalMatrixDimensionException {
		Matrix result;
				// Check sizes and initialize the result matrix
		if( (this.getnCols() != m.getnCols()) || (this.getnRows() != m.getnRows()) ) {
			throw( new IllegalMatrixDimensionException() );
		}
		result = new Matrix( this.getnRows(), this.getnCols(), "R" );
				// do addition
		for (int i=0; i < this.getnRows(); i++ ) {
			for (int j=0; j < this.getnCols(); j++ ) {
				result.body[i][j] = this.body[i][j] + m.body[i][j];  
			}
		}	
		
		return result;
	}
	
	public Matrix mult( Matrix m ) throws IllegalMatrixDimensionException {
		Matrix result;
		// Check sizes and initialize the result matrix
		if( (this.getnCols() != m.getnRows()) ) {
			throw( new IllegalMatrixDimensionException() );
		}
		result = new Matrix( this.getnRows(), m.getnCols(), "R" );
				// Do multiplication
		for (int i=0; i < this.getnRows(); i++ ) {
			for (int j=0; j < m.getnCols(); j++ ) {
				for( int k=0; k < this.getnCols(); k++ ) {
					result.body[i][j] += this.body[i][k] * m.body[k][j];
				}
			}
		}	
		
		return result;
	}

	public Matrix div( int divider ) throws DivisionByZeroException, IllegalMatrixDimensionException {
		Matrix result;
		
		if( divider == 0 ) {
			throw( new DivisionByZeroException() ); 
		}
		result = new Matrix( this.getnRows(), this.getnCols(), "R" );
		// divide
		for (int i=0; i < this.getnRows(); i++ ) {
			for (int j=0; j < this.getnCols(); j++ ) {
				result.body[i][j] = this.body[i][j] / divider;
			}
		}	
		
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i < this.getnRows(); i++ ) {
			for (int j=0; j < this.getnCols(); j++ ) {
				sb.append(getItem( i, j ) + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
