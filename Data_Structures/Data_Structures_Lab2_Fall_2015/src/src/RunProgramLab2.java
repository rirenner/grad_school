import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Instant;
import java.util.Scanner;

/* Christine Herlihy
 * EN 605.202 Data Structures
 * Lab #2: Recursive algorithm to compute the determinant of a matrix 
 * CLASS: RunProgLab2 (Main driver) 
 */

/*
 * This program accepts a txt file containing a set of matrices of specified
 * dimensions. It calculates the determinant of each matrix, and outputs
 * the original matrix, along with its determinant, to the indicated output
 * txt file. 
 */
public class RunProgramLab2
{
	public static void main(String [] args)
	{
		
		Boolean runtimeParamCorrect = false;
	
		//Check to make sure user has correctly entered the 3 required runtime parameters 
		while (runtimeParamCorrect != true)
		{
			if (args != null && args.length == 2)
			{
			
				runtimeParamCorrect = true;
				//Runtime parameters:
				String inputFilePath = args[0];
				String outputFilePath = args[1];
				
			
				//String inputFilePath = "/Users/christine/Documents/workspace2/Data_Structures_Lab2_Fall_2015/src/DS_Lab2_InputTxt.txt";
				//String outputFilePath = "/Users/christine/Documents/workspace2/Data_Structures_Lab2_Fall_2015/src/DS_Lab2_Output.txt";
				
		
				Instant start = Instant.now();
				
				//For testing; later change to args[0] b/c it will be runtime param		
				//final long startTime = System.currentTimeMillis();
				final long startTime = System.nanoTime();
				//String [] outputArrayTemp = readInput(directory + "/src/DataStructures_Lab1_inputFile.txt");	//TESTING
				readInput(inputFilePath, outputFilePath);
				final long duration = System.nanoTime() - startTime;
				//System.out.println(duration);

			}
		
		
		//If runtime parameters are entered incorrectly, give error message and break so user can re-enter from terminal 
			else if (args == null || args.length < 2 ||args.length > 2)
		{
			{
				System.out.println("\n"+ "2 runtime parameters are required." + "\n" + "To run this program, use the following command line prompt: " + "\n" + "java RunProgramLab2 /Users/christine/Documents/workspace2/Data_Structures_Lab2_Fall_2015/src/DS_Lab2_InputTxt.txt /Users/christine/Documents/workspace2/Data_Structures_Lab2_Fall_2015/src/DS_Lab2_Output.txt");
				break;
			}

		}	//close else if	
		}	//close while
	}
	

	/**
	 * @method readInput reads in an input txt file containing n*n matrices. 
	 * Each matrix is preceeded by an integer, n, which indicates its 
	 * dimensions
	 * <dt><b>Precondition:</b><dd>
	 * Input path is correctly specified as the first runtime parameter; 
	 * 	the input file is not empty, and contains valid input 
	 * @param String inputFilePath: the path of the input txt file 
	 * @returns String [] the output array 
	 * <dt><b>Postconditions:</b><dd>
	 * The input file is read in so that other operations can be performed
	 * @author Christine Herlihy
	 */
	public static void  readInput(String inputFilePath, String outputFilePath)
	{
		try(BufferedReader buffer = new BufferedReader(new FileReader(inputFilePath)))
		{
			//For input
			String line= new String();
			Scanner input = new Scanner(System.in);
			int matrixCounter = 0;
			boolean makeNewMatrix = true;
			int [] readNumbersIn = new int[500];
			Matrix tempMatrix= new Matrix();
			Matrix [] matrixOutArray = new Matrix [100];
			int inputRowCounter = 0;
			int matrixDimSetter =0;
			int arrayParser = 0;
			
			//For output
			BufferedWriter outputFile = new BufferedWriter(new FileWriter(outputFilePath));
			
			//Read each line of the input file in; store each integer
			while((line=buffer.readLine()) != null)
			{
				String tempFirst = new String(line);	//the first line in the input file will = the order of the first matrix 
				input = new Scanner(tempFirst).useDelimiter("\\s+");	//after that, for each line, want to parse the line, using white space as a delimiter, and grabbing all of the integer values 

				//Read each integer in the input file into an int array; delimiter=whitespace
				while(input.hasNextInt())
				{

					readNumbersIn[inputRowCounter] = input.nextInt();
					inputRowCounter ++;		//Keep track of how many rows are in the array
					//System.out.println("counter " + inputRowCounter);	//TESTING 
				}
			}
				
				//FOR TESTING 
/*				for(int i=0; i<readNumbersIn.length;i++)
				{
					System.out.println(i + " " + readNumbersIn[i]);
				}*/
				
				//For each row in the input int array, if it's a dimension marker, make a new matrix; else, fill created matrix
				for(int z = 0; z<inputRowCounter; z+= arrayParser)
				{
					if(makeNewMatrix != false)
					{
						
						matrixDimSetter = (readNumbersIn[z]);
						if( matrixDimSetter > 0)
						{
							tempMatrix = new Matrix(readNumbersIn[z]);
							//System.out.println("dimsetter" + readNumbersIn[i]);	//TESTING 
							matrixCounter ++;	//A new matrix has been created; increase the count by 1
							//matrixElements= matrixDimSetter*matrixDimSetter;
							arrayParser=1;
							makeNewMatrix = false;
						}
						
						else
						{
							continue; //Ignore invalid input; matrix must be n*n; if a negative # is put it, it's an error
						}
			
					}
					
					/*The previous int was an array dimension marker "n"; fill the n*n array created.
					 * Because a Matrix object is instantiated, it is by default n*n. This means if there
					 * is a dimension mis-match issue, it will show up when the last matrix in the input array is input 
					 * and there are too few or too many integers 
					 */
					
					else if(makeNewMatrix== false){
						int tempCounter = z;
						for(int m = 0; m< matrixDimSetter; m++)
							{
								for(int n=0; n<matrixDimSetter; n++)
								{
									
									int temp = readNumbersIn[tempCounter];
									//System.out.println("temp int " + temp);
									tempMatrix.newMatrix[m][n] = temp;							
									tempCounter++;
									//System.out.print(tempMatrix.newMatrix[m][n] + " ");	//TESTING 
								}
									//System.out.println();	//TESTING 
								
							}	
						//System.out.println("\n");
						matrixOutArray[matrixCounter-1] = tempMatrix;
						//matrixCounter ++;
						makeNewMatrix = true;
						arrayParser = (matrixDimSetter*matrixDimSetter);

					}	//close if else if loop 
				}	//close for(int z = 0; z<inputRowCounter; z+= arrayParser)
				
				
				for(int g=0; g<matrixCounter; g++)
				{
					Matrix dummyMatrix = matrixOutArray[g];
					Instant start = Instant.now();	
					final long startTime = System.currentTimeMillis();
					double det = dummyMatrix.determinantCalc(dummyMatrix.newMatrix);
					final long duration = System.currentTimeMillis() - startTime;
					
					outputFile.write("Input Matrix #" + (g+1) +  ":"+ "\n\n");
					tempMatrix.matrixOut(outputFile, dummyMatrix);
					outputFile.write("\n");
					outputFile.write("Order: " + dummyMatrix.getSize()+ "\n");
					outputFile.write("The determinant is: " + det+ "\n"); 
					outputFile.write("It took "+ duration + " nanoseconds to calculate this determinant");
					outputFile.write("\n" + "#----------------------------------------------------#");
					outputFile.write("\n\n");
				}
				outputFile.close();
			}	//close try 

		catch (FileNotFoundException e)
		{
			System.out.println("Database file not found.");
		
		}
	
		catch (IOException e) 
		{
			System.out.println("An I/O Error occured.");
			
		}
	}

}	//close class RunProgramLab2