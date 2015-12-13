import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


/*
 * This class is used to generate the test cases (i.e., ordered and unordered int arrays)
 * that are used as input for each sorting algorithm in the main method.
 * CLASS: GenerateTestCase
 */

public class GenerateTestCase
{

	/**
	 * @method genTestCases generates test cases (i.e. txt files that consist
	 * 			of the contents of int [], for use as input for each sorting
	 * 			algorithm.
	 * <dt><b>Precondition:</b><dd>
	 * Input parameter filesize [] contains recognized array types,including:
	 * 			ascending, reversed, random, and random with approx. 20% dups.
	 * @param int [] filesizes: size(s) of array(s) to generate;
	 * 			String [] fileTypes: type(s) of array(s) to generate.
	 * @returns void
	 * <dt><b>Postconditions:</b><dd>
	 * The specified test cases are generated.
	 * @author Christine Herlihy
	 */
	public static File genTestCases(int [] filesizes, String [] fileTypes, String master, String location)
	{
		try
		{
			File masterFile = new File (master);
			BufferedWriter outputFileMaster = new BufferedWriter(new FileWriter(masterFile.getAbsoluteFile()));

			for(int i = 0; i < filesizes.length; i++)
			{
				for(int j = 0; j < fileTypes.length; j++)
				{
					String loc = location;					
					File fileName = new File (loc + filesizes[i] + "_" + fileTypes[j] + ".txt");
					//System.out.println(fileName);	//TESTING

					switch(fileTypes[j])
					{
						case "ran":
									//System.out.println("random");	//TESTING
									try
									{
										//Make a new file for each test case
										BufferedWriter outputFile = new BufferedWriter(new FileWriter(fileName.getAbsoluteFile()));

										//Create an ascending ordered array of size k to be shuffled
										int [] tempInit = new int [filesizes[i]];

										for(int k = 0; k < filesizes[i]; k++)
										{
											tempInit[k] = k+1;
										}

										//Shuffle the ascending array
										shuffleArray(tempInit);

										for(int k = 0; k < tempInit.length; k++)
										{
											outputFile.write(tempInit[k] + "\n");
										}

										outputFile.close();
										outputFileMaster.write(fileName + "\n");
									}	//close try

									catch (FileNotFoundException e)
									{
										System.out.println("Database file not found.");

									}

									catch (IOException e)
									{
										System.out.println("An I/O Error occured.");

									}
									break;	//end case "ran"

						case "rev":
									//System.out.println("reverse");	//TESTING
									try
									{
										BufferedWriter outputFile = new BufferedWriter(new FileWriter(fileName.getAbsoluteFile()));

										int [] tempRev = new int[filesizes[i]];

										for(int l = 0; l < filesizes[i]; l++)
										{

											tempRev[l] = filesizes[i]- l;
											outputFile.write(tempRev[l]+ "\n");
										}

										outputFile.close();
										outputFileMaster.write(fileName + "\n");
									}	//close try

									catch (FileNotFoundException e)
									{
										System.out.println("Database file not found.");
									}

									catch (IOException e)
									{
										System.out.println("An I/O Error occured.");
									}
									break;	//close case "rev"

						case "asc":
									//System.out.println("asc");	//TESTING
									try
									{
										BufferedWriter outputFile = new BufferedWriter(new FileWriter(fileName.getAbsoluteFile()));

										int [] tempAsc = new int[filesizes[i]];

										for(int m = 0; m < filesizes[i]; m++)
										{
											tempAsc[m] = m+1;
											outputFile.write(tempAsc[m] + "\n");
										}

										outputFile.close();
										outputFileMaster.write(fileName + "\n");
										}	//close try

										catch (FileNotFoundException e)
										{
											System.out.println("Database file not found.");
										}

										catch (IOException e)
										{
											System.out.println("An I/O Error occured.");
										}

									break;	//end case "asc"

						case "dup":
									//System.out.println("dupes");	//TESTING
									try
									{
										BufferedWriter outputFile = new BufferedWriter(new FileWriter(fileName.getAbsoluteFile()));
										double percentDupes= 0.2;

										//Create an ascending ordered array of size k to be shuffled
										int [] tempSorted = new int [filesizes[i]];

										for(int k = 0; k < filesizes[i]; k++)
										{
											tempSorted[k] = k+1;
										}

										//Shuffle the ascending array
										shuffleArray(tempSorted);

										//Figure out the proportion of the array to replace with duplicate values of elements already in the array
										Double proportion =  tempSorted.length*percentDupes;
										int ratio = proportion.intValue();

										//Instantiate a new array of size "ratio"
										int [] tempShuffled = new int[ratio];

										//Fill the new, smaller array with randomly selected elements from the original, sorted array
										for(int b = 0; b< tempShuffled.length; b++)
										{
											int randNum = i + (int) (Math.random() * (tempSorted.length - i));	//Select a random element of the array to have as a duplicate
											tempShuffled[b] = tempSorted[randNum]; 	//tempSorted was previously shuffled
										}

										//Shuffle this sub-array
										shuffleArray(tempShuffled);

										//Loop through (shuffled) original array and, if the values don't match, replace a non-dup with a dup value
										for(int c= 0; c<tempShuffled.length; c++ )
										{
											if(tempSorted[c] != tempShuffled[c])
											{
												tempSorted[c] = tempShuffled[c];
											}
										}

										//Final output to print to file
										for(int k = 0; k < tempSorted.length; k++)
										{
											outputFile.write(tempSorted[k] + "\n");
										}

										outputFile.close();
										outputFileMaster.write(fileName + "\n");
										}	//close try

										catch (FileNotFoundException e)
										{
											System.out.println("Database file not found.");
										}

										catch (IOException e)
										{
											System.out.println("An I/O Error occured.");
										}

										break;	//end case "dups"
								}	//close switch
							}	//close inner for loop
						}	//close outer for loop
						outputFileMaster.close();	//Close file storing the path names of ALL test cases generated
						return masterFile;
					}	//close try

					catch (FileNotFoundException e)
					{
						System.out.println("Database file not found.");
						return null;
					}

					catch (IOException e)
					{
						System.out.println("An I/O Error occured.");
						return null;
					}
				}	//close method genTestCases

	/**
	 * @method shuffle takes a sorted array and returns a shuffled array,
	 * 			in which the index of each element is randomly chosen within
	 * 			the range of possible index values (i.e. w/in array bounds)
	 * <dt><b>Precondition:</b><dd>
	 * The input array contains elements and is sorted in asc. order
	 * @param inputArray
	 * @returns void
	 * <dt><b>Postconditions:</b><dd>
	 * The array is shuffled in-place.
	 * @author Christine Herlihy
	 */
		public static void shuffleArray(int[] array)
	    {
	    	int size = array.length;
	    	for (int i = 0; i < array.length; i++)
	    	{
	    		// Select a random index of the array past i.
	    		int rand = i + (int) (Math.random() * (size - i));

	    		// Swap the random element with current element
	    		swap(array, rand, i);
	    	}
	    }	//close method shuffleArray

	/**
	 * @method swap changes the position of two elements in the input array
	 * 			This method is useful when implementing heap sort
	 * <dt><b>Precondition:</b><dd>
	 * The input array contains elements
	 * @param inputArray; int lower bound; int upper bound
	 * @returns void
	 * <dt><b>Postconditions:</b><dd>
	 * The two elements input as parameters are swapped within the input array
	 * @author Christine Herlihy
	 */
	public static void swap(int[] input, int val1, int val2)
	{
		int tempInt = input[val1];
		input[val1] = input[val2];
		input[val2] = tempInt;
	}



}	//close class GenerateTestCase

