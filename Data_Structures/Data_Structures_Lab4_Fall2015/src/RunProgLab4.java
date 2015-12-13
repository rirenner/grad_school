import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.Scanner;

public class RunProgLab4 {


	public static void main(String [] args)

	{
		//Check to make sure runtime parameters are correct:
		Boolean runtimeParamCorrect = false;
		
			//Check to make sure user has correctly entered the 4 required runtime parameters 
			while (runtimeParamCorrect != true)
			{
				if (args != null && args.length == 4)
				{
					runtimeParamCorrect = true;
					//Runtime parameters:
					File output = new File (args[0]);
					File outputStats = new File(args[1]);	
					String masterFile = args[2];
					String loc = args[3];
					
					//File types
					String [] fileTypes = new String [] {"ran", "rev", "asc", "dup"};
					//File sizes
					int [] fileSizes = new int [] {50, 500, 1000, 2000, 5000, 7000, 10000, 13000};
					//int [] fileSizes = new int [] {5,10};	//TESTING
					String line = new String();
					String in = new String();
					int counter=0;
			
					//Storage array for input file names
					String [] cases = new String[fileTypes.length*fileSizes.length];
			
					//Storage array for file/algorithm run times
					double [] times1 = new double [cases.length*5];
					double [] times2 = new double [cases.length*5];
					int timesCounter1 = 0;
					int timesCounter2= 0;
			
					//Output files
				//	File output= new File ("/Users/christine/Documents/workspace2/Data_Structures_Lab4/src/ProgOutput_Lab4.txt");
				//	File outputStats = new File ("/Users/christine/Documents/workspace2/Data_Structures_Lab4/src/ProgOutput_Lab4_STATS.txt");
				//	String masterFile= "/Users/christine/Documents/workspace2/Data_Structures_Lab4/src/ListOfInputCases_Lab4.txt";
				//	String loc = "/Users/christine/Documents/workspace2/Data_Structures_Lab4/src/testCases/";
			
					//Generate the test cases and create a file containing the names of each test case txt file
					//File listOfTestCases = GenerateTestCase.genTestCases(fileSizes, fileTypes);
					File listOfTestCases = GenerateTestCase.genTestCases(fileSizes, fileTypes, masterFile, loc);
			
					//Read each input file name into an array 
					try(BufferedReader buffer = new BufferedReader(new FileReader(listOfTestCases)))
					{
						//Instantiate master output files for entire program
						BufferedWriter outputFile = new BufferedWriter(new FileWriter(output.getAbsoluteFile()));
						BufferedWriter outputStatsFile = new BufferedWriter(new FileWriter(outputStats.getAbsoluteFile()));
			
						//Read in each test case file path from the master input case file
						while((line=buffer.readLine()) != null)
						{
			
							cases[counter] = line;
							counter++;
							//System.out.println("counter" + counter);
						}
			
						//For each test file, read each line into a stack; then pop the stack into an array; make a copy, and run each algorithm
						//For each algorithm in the loop:
							//	Make a copy of original array to sort
							//	Run the algorithm on each array
							//	Print the output of each, as well as the duration of the run
							//	Generate summary data on timing of run
							//	Store timing data in a separate output file for later analysis in R
						for(int i =0; i<cases.length; i++)
						{
							BufferedReader readCase = new BufferedReader(new FileReader(cases[i]));
							LinkedStack stack = new LinkedStack();
							outputStatsFile.write(cases[i] + "\n");
			
			
							//In is each line (i.e. row, containing an integer) in the test case file
							while((in = readCase.readLine()) !=null)
							{
								int temp = Integer.valueOf(in);
								//System.out.println(temp);	//TESTING
								stack.push(temp);	//push temp onto the stack
							}
			
							//System.out.println("stack size " + stack.getSize());	//TESTING
							//System.out.println("Stack top  " + stack.peek());	//TESTING
			
							//Instantiate an array of same size of the stack; pop into the array
							int [] arrayToSort = new int[stack.getSize()];
							int temp = i +1;
							
							//*************************************************************************//
							//Beginning of output file writing: 
							
							outputFile.write("\n" + "Input File #" + (temp) + " " + "\n");
			
							String fileSpecs = cases[i].substring(73);
							fileSpecs = fileSpecs.replaceAll("[.txt//_]", " ");
			
							outputFile.write("Input file size and type: " + fileSpecs + "\n");
							outputFile.write("Original array to sort: " + "\n");
			
							for(int j = stack.getSize()-1; j > -1; j--)
							{
								arrayToSort[j] = stack.pop();	//This is the array to copy & sort
							}
			
							for(int k=0; k<arrayToSort.length; k++)
							{
								outputFile.write("[" + arrayToSort[k] + "] "); //Print original, unsorted array to output file
							}
			
							outputFile.write("\n\n");
			
							//*************************************************************************//
							//ALGORITHM 1: Quicksort; first item of partition as pivot
			
							int [] temp1 = copyArray(arrayToSort);
			
							outputFile.write("Quicksort: first item of partition as pivot"+ "\n");
			
							final long startTime1 = System.nanoTime();
							Quicksort.quicksortFirst(temp1);
							final long duration1 = System.nanoTime() - startTime1;
							final long durSeconds1 = (long) (duration1 / 1000000000.0);
							times1[timesCounter1] = duration1;
							timesCounter1++;
							times2[timesCounter2] = durSeconds1;
							timesCounter2++;
			
							outputFile.write("This sort took " + duration1 + " nanoseconds."+ "\n");
							outputFile.write("This sort took " + durSeconds1 + " seconds."+ "\n\n");
			
							outputFile.write("Sorted array: " + "\n");
			
							for(int z = 0; z<temp1.length; z++)
							{
								outputFile.write("["+ temp1[z] + "] ");
							}
			
							outputFile.write("\n");
			
							//*************************************************************************//
							
							//ALGORITHM 2: Quicksort; median of 3 as pivot
			
							int [] temp2 = copyArray(arrayToSort);
			
							outputFile.write("\n" + "Quicksort: median of 3 as pivot"+ "\n");
			
							final long startTime2 = System.nanoTime();
							Quicksort.quicksortMedianOf3(temp2, 0, temp2.length-1);
							final long duration2 = System.nanoTime() - startTime2;
							final long durSeconds2 = (long) (duration2 / 1000000000.0);
							times1[timesCounter1] = duration2;
							timesCounter1++;
							times2[timesCounter2] = durSeconds2;
							timesCounter2++;
			
							outputFile.write("This sort took " + duration2 + " nanoseconds."+ "\n");
							outputFile.write("This sort took " + durSeconds2 + " seconds."+ "\n\n");
			
							outputFile.write("Sorted array: " + "\n");
			
							for(int z = 0; z<temp2.length; z++)
							{
								outputFile.write("["+ temp2[z] + "] ");
							}
			
							outputFile.write("\n");
			
							//*************************************************************************//
							//ALGORITHM 3: Quicksort recursive; stopping case of k=50
			
							int [] temp3 = copyArray(arrayToSort);
			
							outputFile.write("\n" + "Quicksort: recursive with stopping case of k = 50 "+ "\n");
			
							final long startTime3 = System.nanoTime();
							Quicksort.quicksortRecur50(temp3, 0, temp3.length-1);
							final long duration3 = System.nanoTime() - startTime3;
							final long durSeconds3 = (long) (duration3 / 1000000000.0);
			
							outputFile.write("This sort took " + duration3 + " nanoseconds."+ "\n");
							outputFile.write("This sort took " + durSeconds3 + " seconds."+ "\n\n");
							times1[timesCounter1] = duration3;
							timesCounter1++;
							times2[timesCounter2] = durSeconds3;
							timesCounter2++;
			
							outputFile.write("Sorted array: " + "\n");
			
							for(int z = 0; z<temp3.length; z++)
							{
								outputFile.write("["+ temp3[z] + "] ");
							}
			
							outputFile.write("\n");
			
							//*************************************************************************//
							//ALGORITHM 4: Quicksort recursive; stopping case of k=100
			
							int [] temp4 = copyArray(arrayToSort);
			
							outputFile.write("\n" + "Quicksort: recursive with stopping case of k = 100 "+ "\n");
			
							final long startTime4 = System.nanoTime();
							Quicksort.quicksortRecur100(temp4, 0, temp4.length-1);
							final long duration4 = System.nanoTime() - startTime4;
							final long durSeconds4 = (long) (duration4 / 1000000000.0);
							times1[timesCounter1] = duration4;
							timesCounter1++;
							times2[timesCounter2] = durSeconds4;
							timesCounter2++;
			
							outputFile.write("This sort took " + duration4 + " nanoseconds."+ "\n");
							outputFile.write("This sort took " + durSeconds4 + " seconds."+ "\n\n");
			
							outputFile.write("Sorted array: " + "\n");
			
							for(int z = 0; z<temp4.length; z++)
							{
								outputFile.write("["+ temp4[z] + "] ");
							}
			
							outputFile.write("\n");

							//*************************************************************************//
							//ALGORITHM 5: Iterative heap sort
			
							int [] temp5 = copyArray(arrayToSort);
			
							outputFile.write("\n" + "Heap Sort"+ "\n");
			
							final long startTime5 = System.nanoTime();
							HeapSort.heapSort(temp5);
							final long duration5 = System.nanoTime() - startTime5;
							final long durSeconds5 = (long) (duration5 / 1000000000.0);
							times1[timesCounter1] = duration5;
							timesCounter1++;
							times2[timesCounter2] = durSeconds5;
							timesCounter2++;
			
							outputFile.write("This sort took " + duration5 + " nanoseconds."+ "\n");
							outputFile.write("This sort took " + durSeconds5 + " seconds."+ "\n\n");
			
							outputFile.write("Sorted array: " + "\n");
			
							for(int z = 0; z<temp5.length; z++)
							{
								outputFile.write("["+ temp5[z] + "] ");
							}
			
							outputFile.write("\n");
							outputFile.write("//*************************************************************************//" + "\n\n");
			
							//*************************************************************************//
						}
			
						outputFile.close();
			
						//Print the summary stats (in nanoseconds and seconds) as column vectors for later analysis 
						
						//Nanoseconds
						for(int i = 0; i<times1.length; i++)
						{
							outputStatsFile.write(Double.toString(times1[i]) + "\n");
						}
			
						outputStatsFile.write("\n");
						
						//Seconds
						for(int i = 0; i<times2.length; i++)
						{
							outputStatsFile.write(Double.toString(times2[i])+ "\n");
						}
			
			
						outputStatsFile.close();
						buffer.close();
						
						System.out.println("Input files generated and sorted; results written to output files.");
			
					}	//close try
			
					catch (FileNotFoundException e)
					{
						System.out.println("Database file not found.");
			
					}
			
					catch (IOException e)
					{
						System.out.println("An I/O Error occured.");
					}
					
					catch (OutOfMemoryError e)
					{
						System.out.println("Out of memory.");
					}
				}
					
			else if (args == null || args.length < 4 ||args.length > 4)
			{
				{
					System.out.println("\n"+ "4 runtime parameters are required." + "\n" + "To run this program, use the following command line prompt: " + "\n" + "java RunProgLab4 /Users/christine/Documents/workspace2/Data_Structures_Lab4/src/ProgOutput_Lab4.txt /Users/christine/Documents/workspace2/Data_Structures_Lab4/src/ProgOutput_Lab4_STATS.txt /Users/christine/Documents/workspace2/Data_Structures_Lab4/src/ListOfInputCases_Lab4.txt /Users/christine/Documents/workspace2/Data_Structures_Lab4/src/testCases/");
					break;
				}

			}	//close else if	
			}	//close while
		

	}	//close main method





	/**
	 * @method copyArray creates a copy of an input int array
	 * <dt><b>Precondition:</b><dd>
	 * The input array contains elements
	 * @param int [] input array to be copied
	 * @returns temp []: a copy of the input array
	 * <dt><b>Postconditions:</b><dd>
	 * Temp contains the contents of the input array
	 * 
	 */
	public static int[] copyArray(int[] input)
	{
		int [] temp = new int[input.length];

		for(int copy = 0; copy<input.length; copy++)
		{
			temp[copy] = input[copy];
		}
		return temp;
	}



}	//close class RunProgLab4

