import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


	/**
	 * @method getSize() returns the size of the current stack 
	 * <dt><b>Precondition:</b><dd>
	 * None (if the stack is empty, 0 will be returned)
	 * @param none 
	 * @returns int size (size of the current stack)
	 * <dt><b>Postconditions:</b><dd>
	 * None (stack is unchanged) 
	 * @author Christine Herlihy
	 */
public class Matrix {
	
	private int matrixDim;
	int [][]newMatrix = new int [0][0];
	
	/**
	 * @constructor without any parameters: 
	 * <dt><b>Precondition:</b><dd>
	 * None 
	 * @param none 
	 * @returns none
	 * <dt><b>Postconditions:</b><dd>
	 * A new Matrix object is instantiated
	 * @author Christine Herlihy
	 */
	public  Matrix()
	{
		matrixDim = 0;
		
	}
	
	/**
	 * @constructor with a dimension parameter: 
	 * <dt><b>Precondition:</b><dd>
	 * None 
	 * @param none 
	 * @returns none
	 * <dt><b>Postconditions:</b><dd>
	 * A new Matrix object is instantiated
	 * @author Christine Herlihy
	 */
	public Matrix(int matrixOrder)
	{
		matrixDim = matrixOrder;
		newMatrix = new int[matrixDim][matrixDim];
						
	}
	
	
	/**
	 * @method getSize() returns matrixDim of current matrix
	 * <dt><b>Precondition:</b><dd>
	 * None (if the matrix is empty, 0 will be returned)
	 * @param none 
	 * @returns int matrixDim 
	 * <dt><b>Postconditions:</b><dd>
	 * None (stack is unchanged) 
	 * @author Christine Herlihy
	 */
	public int getSize()
	{
		return this.matrixDim;
		
		
	}
	

	
	/**
	 * @method matrixToPrint prepares the contents of a matrix for printing
	 * <dt><b>Precondition:</b><dd>
	 * Matrix dimensions must match (i.e. must be a valid square matrix); can be 1
	 * @param none 
	 * @returns String [][] A 2d array holding matrix elements as strings 
	 * <dt><b>Postconditions:</b><dd>
	 * None Matrix object is unchanged 
	 * @author Christine Herlihy
	 */
	public String[][] matrixToPrint()
	{
		String[][] tempArray = new String[this.matrixDim][this.matrixDim];
		
		for(int i=0; i<this.matrixDim; i++)
		{
			for(int j=0; j<this.matrixDim; j++)
			{				
				//System.out.print(this.newMatrix[i][j]);
				tempArray[i][j] = Integer.toString(this.newMatrix[i][j]);
			}
		}
		return tempArray;
	}
	
	
	/**
	 * @method matrixOut prints the contents of a matrix to output file
	 * <dt><b>Precondition:</b><dd>
	 * Matrix dimensions must match (i.e. must be a valid square matrix); can be 1
	 * @param Matrix tempMat: the matrix object to print
	 * @param StringBuffer output: the file to print to 
	 * @returns none
	 * <dt><b>Postconditions:</b><dd>
	 * None Matrix object is unchanged 
	 * @author Christine Herlihy
	 */
	public static void matrixOut(BufferedWriter outputFile, Matrix tempMat)
	{
		StringBuffer tempOut = new StringBuffer(300);
		
		for(int a=0; a < tempMat.matrixDim; a++)
		{
			for(int b=0; b<tempMat.matrixDim; b++)
			{
				tempOut.append(" " + tempMat.newMatrix[a][b]);
			}
			tempOut.append("\n");	//Go to next line when a new row starts 
		}
		
		System.out.println(tempOut.toString()); //FOR TESTING 
		
		//Add this matrix to the output file
		try
		{
			outputFile.write(tempOut.toString());
		}
		
		catch (FileNotFoundException e)
		{
			System.out.println("Database file not found.");
						
		}
	
		catch (IOException e) 
		{
			System.out.println("An I/O Error occured.");
		}
		
	}	//close method matrixOut 
	
	
	
	/**
	 * @method determinantCalc recursively calculates the determinant of a square matrix 
	 * <dt><b>Precondition:</b><dd>
	 * Matrix dimensions must match (i.e. must be a valid square matrix); can be 1
	 * @returns int: value of the determinant of the Matrix object 
	 * <dt><b>Postconditions:</b><dd>
	 * None Matrix object is unchanged 
	 * @author Christine Herlihy
	 */
	
	public double determinantCalc(int [][] tempMatrix)
	{

		int [][] temp = tempMatrix;
		int order = temp.length;
		double determinant = 0;
		
		
		//The stopping case is order == 1; determinant = matrix[0][0]
		if(order == 1)
		{
			determinant = temp[0][0];
		}
		
		/*If order is > 1, det= sum(power -1, i+j)* temp[i,j]*det(minor(temp[i,j]) for each minor
		 * The minor of an element x is the submatrix formed by deleting the 
		 * row and column containing x. 
		 */
		else
		{
			for(int a = 0; a < order; a++)
			{
				int [][] tempMinor = new int[order-1][order-1];
				
				
				//Fill in the minorMatrix with corresponding values from temp; skip row 0, so ignore c==a
				for(int b= 1; b<order; b++)
				{
					for(int c= 0; c< order; c++)
					{
						if(c < a)
						{
							tempMinor[b-1][c] = temp[b][c];
						}
						
						else if(c > a)
						{
							tempMinor[b-1][c-1] = temp[b][c];
						}

					}	//close for c		
				}	//close for b
			determinant+= temp[0][a] * Math.pow((-1), a)* determinantCalc(tempMinor);
	
			}	//close for a
		}	//close if/else if
			return determinant;
		
	}	//close method determinantCalc
		

/*	//FOR TESTING 
	public static void main(String [] args)
	{
		
		Matrix temp = new Matrix(2);
		
		for(int i=0; i<2;i++)
		{
			for(int j=0; j<2; j++)
			{
				temp.newMatrix[i][j] = i+j;
			}
		}
		
		for(int i=0; i<2;i++)
		{
			for(int j=0; j<2; j++)
			{
				System.out.print(temp.matrixToPrint()[i][j]);
			}
			System.out.println();
		}
		
		//System.out.println(temp.printMatrix());
		double out = temp.determinantCalc(temp.newMatrix);
		System.out.println(out);
	}*/
}	//close class Matrix