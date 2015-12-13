import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.Instant;

/* Christine Herlihy
 * EN 605.202 Data Structures
 * Lab #1: Use of Stacks
 * CLASS: RunProgramLab1 (Main driver) 
 */


/*
 * This program accepts a txt file containing postfix expressions that contain
 * single letter operands and the operators +, -, *, /, %, and $, and prints 
 * a sequence of instructions to evaluate the expression and leaves the result
 * in the register. This program uses temporary variables of the form TEMPn.
 */

public class RunProgramLab1 {

	/*
	 * The main method acts as a driver for the program. It requires 2 
	 * runtime parameters: the file name of the input file, and the file name
	 * of the output file. The main method will parse the input file,
	 * generate machine code to evaluate each postfix expression, and will 
	 * output these machine language instructions to the specified txt file. 
	 */
	
	/**
	 * @method main: main driver of program
	 * <dt><b>Precondition:</b><dd>
	 * Input path is correctly specified as the first runtime parameter; 
	 * 	the input file is not empty, and contains valid input; output path 
	 * 	is correctly specified as the second runtime parameter 
	 * @param: String [] args: args[0] = input file; args[1] =output file
	 * @returns none
	 * <dt><b>Postconditions:</b><dd>
	 * Output file is available.
	 * @author Christine Herlihy
	 */
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
		
				Instant start = Instant.now();
				
				//For testing; later change to args[0] b/c it will be runtime param		
				final long startTime = System.currentTimeMillis();
				//String [] outputArrayTemp = readInput(directory + "/src/DataStructures_Lab1_inputFile.txt");	//TESTING
				String [] outputArrayTemp = readInput(inputFilePath);
				final long duration = System.currentTimeMillis() - startTime;
				//printOutput(directory + "/src/Output.txt", outputArrayTemp, duration);	//TESTING
				printOutput(outputFilePath, outputArrayTemp, duration);
			}
		
			//If runtime parameters are entered incorrectly, give error message and break so user can re-enter from terminal 
			else if (args == null || args.length < 2 ||args.length > 2)
			{
				{
					System.out.println("\n"+ "2 runtime parameters are required." + "\n" + "To run this program, use the following command line prompt: " + "\n" + "java RunProgramLab1 /Users/christine/Documents/workspace2/Data_Structures_Lab1_Fall_2015/src/DataStructures_Lab1_inputFile.txt /Users/christine/Documents/workspace2/Data_Structures_Lab1_Fall_2015/src/Output.txt");
					break;
				}
	
			}	//close else if	
		}	//close while
	}
	
	/**
	 * @method readInput reads in an input txt file containing postfix 
	 * 			expressions. 
	 * <dt><b>Precondition:</b><dd>
	 * Input path is correctly specified as the first runtime parameter; 
	 * 	the input file is not empty, and contains valid input 
	 * @param String inputFilePath: the path of the input txt file 
	 * @returns none
	 * <dt><b>Postconditions:</b><dd>
	 * The input file is read in so that other operations can be performed
	 * @author Christine Herlihy
	 */
	public static String [] readInput(String inputFilePath)
	{
		
		boolean inputParametersCorrect = false;
		String inputPath = inputFilePath;
		String line = new String();
		
		
		while (inputParametersCorrect != true)
		{
			if (inputFilePath.length() != 0)
			{		
				inputParametersCorrect = true;
			}
			
		}	//close while (inputParametersCorrect != true)
			
			try(BufferedReader buffer = new BufferedReader(new FileReader(inputPath)))
			{
				String [] fields = new String [200];
				int fieldsCounter = 0;
				int outputCounter = 0;
				int tempVarCounter = 1;
				LinkedStack register = new LinkedStack();
				String [] machineLangOut = new String [500];
				String stringToParse = new String();
								
				while (( line = buffer.readLine()) != null) 
				{
			         //Split lines from text file and store for char parsing
					fields[fieldsCounter] = line;
					fieldsCounter++;
					fields[fieldsCounter] = "#";
					fieldsCounter++;
					
				}	//close while
				
				//Loop through fields; parse each string; evaluate postfix
				for(int i = 0; i< fieldsCounter; i++)
				{
				    stringToParse = fields[i].trim();
				    register.setSize(0);
				    boolean operatorsExist = false;
				    
				    //Generate headings for each new postfix expression 
				    if(stringToParse.equals("#") == false)
				    {
						machineLangOut[outputCounter] = "Original prefix expression: " + stringToParse;
						outputCounter ++;
				    }
					
				    //Check to see if each element of string is valid, and if so, operator or operand
					for(int j = 0; j < stringToParse.length(); j++)
			        {
						String substringTemp = stringToParse.substring(j, j+1);						
						StackNode dummyChar = new StackNode(substringTemp);
						
						
						if(isValidElement(dummyChar.Element)[0] == true)
						{
							//If valid operand, push onto stack
							register.push(dummyChar);	
							
						}
						
						else if(isValidElement(dummyChar.Element)[1] == true & register.getSize() >=2)
						{
							/*
							 * If operator and at least 2 operands are in 
							 * the stack, pop A, pop B, perform operation 
							 * B op A, and push result on stack 
							 */
							operatorsExist = true;
							
							if (dummyChar.Element.equals("+"))
							{
								
								String popFirst = register.pop();		//pop A
								System.out.println("popfirst" + popFirst);
								String popSecond = register.pop();	//pop B
								machineLangOut[outputCounter] = "LD " +  popSecond;
								outputCounter ++;
								machineLangOut[outputCounter] = "AD " +  popFirst;
								outputCounter ++;
								machineLangOut[outputCounter] = "ST TEMP" + tempVarCounter;
								String tempToPush = machineLangOut[outputCounter].substring(3, 8);
								StackNode tempNode = new StackNode(tempToPush);
								register.push(tempNode);	//Push the result of B op A onto stack
								outputCounter++;
								tempVarCounter ++;							
							}

							else if (dummyChar.Element.equals("-"))
							{
								
								String popFirstB = register.pop();		//pop A
								String popSecondB = register.pop();		//pop B
								machineLangOut[outputCounter] = "LD " +  popSecondB;
								outputCounter ++;
								machineLangOut[outputCounter] = "SB " +  popFirstB;
								outputCounter ++;
								machineLangOut[outputCounter] = "ST TEMP" + tempVarCounter;
								String tempToPushB = machineLangOut[outputCounter].substring(3, 8);
								StackNode tempNodeB = new StackNode(tempToPushB);
								register.push(tempNodeB);	//Push the result of B op A onto stack
								outputCounter ++;
								tempVarCounter ++;	
							}
							
							else if(dummyChar.Element.equals("*"))
							{
								String popFirstC = register.pop();		//pop A
								String popSecondC = register.pop();		//pop B
								machineLangOut[outputCounter] = "LD " +  popSecondC;
								outputCounter ++;
								machineLangOut[outputCounter] = "ML " +  popFirstC;
								outputCounter ++;
								machineLangOut[outputCounter] = "ST TEMP" + tempVarCounter;
								String tempToPushC = machineLangOut[outputCounter].substring(3, 8);
								StackNode tempNodeC = new StackNode(tempToPushC);
								register.push(tempNodeC);	//Push the result of B op A onto stack
								outputCounter ++;
								tempVarCounter ++;
							}
							
							else if(dummyChar.Element.equals("/"))
							{
								String popFirstD = register.pop();		//pop A
								String popSecondD = register.pop();		//pop B
								machineLangOut[outputCounter] = "LD " +  popSecondD;
								outputCounter ++;
								machineLangOut[outputCounter] = "DV " +  popFirstD;
								outputCounter ++;
								
								StackNode divByZeroCheck = new StackNode(popFirstD);
								if(divByZeroCheck.DataValue != 0)
								{							
									machineLangOut[outputCounter] = "ST TEMP" + tempVarCounter;
									String tempToPushD = machineLangOut[outputCounter].substring(3, 8);
									StackNode tempNodeD = new StackNode(tempToPushD);
									register.push(tempNodeD);	//Push the result of B op A onto stack
									outputCounter ++;
									tempVarCounter ++;
								}
								else if(divByZeroCheck.DataValue == 0)
								{
									machineLangOut[outputCounter] = "Cannot divide by zero. Operation is undefined; this expression cannot be evaluated.";
									outputCounter ++;
								}
							}
							
							else if (dummyChar.Element.equals("%"))
							{
								String popFirstE = register.pop();		//pop A
								String popSecondE = register.pop();		//pop B
								machineLangOut[outputCounter] = "LD " +  popSecondE;
								outputCounter ++;
								machineLangOut[outputCounter] = "MOD " +  popFirstE;
								outputCounter ++;
								
								StackNode divByZeroCheckB = new StackNode(popFirstE);
								if(divByZeroCheckB.DataValue != 0)
								{
									machineLangOut[outputCounter] = "ST TEMP" + tempVarCounter;
									String tempToPushE = machineLangOut[outputCounter].substring(3, 8);
									StackNode tempNodeE = new StackNode(tempToPushE);
									register.push(tempNodeE);	//Push the result of B op A onto stack
									outputCounter ++;
									tempVarCounter ++;
								}
								else if(divByZeroCheckB.DataValue == 0)
								{
									machineLangOut[outputCounter] = "Cannot divide by zero. Operation is undefined; this expression cannot be evaluated.";
									outputCounter ++;
								}
								
								
							}
							
							else if (dummyChar.Element.equals("$"))
							{
								String popFirstF = register.pop();		//pop A
								String popSecondF = register.pop();		//pop B
								machineLangOut[outputCounter] = "LD " +  popSecondF;
								outputCounter ++;
								machineLangOut[outputCounter] = "EXP " +  popFirstF;
								outputCounter ++;
								machineLangOut[outputCounter] = "ST TEMP" + tempVarCounter;
								String tempToPushF = machineLangOut[outputCounter].substring(3, 8);
								StackNode tempNodeF = new StackNode(tempToPushF);
								register.push(tempNodeF);	//Push the result of B op A onto stack
								outputCounter ++;
								tempVarCounter ++;
							
							}	//end switch 
						
						}
						else if(isValidElement(dummyChar.Element)[1] == true & register.getSize() <2)
						{
							machineLangOut[outputCounter] = "Error: less than 2 operands on stack; cannot perform operation.";
							outputCounter ++;
							break;	
						}
						
						
						else if(dummyChar.Element.equals("#"))
						{
							machineLangOut[outputCounter] = " ";
							outputCounter ++;
							tempVarCounter =1;
						}	
						
						else	//If not operand or operator, element is invalid
						{
							machineLangOut[outputCounter] = "This postfix expression contains invalid element(s) and cannot be evaluated";
							outputCounter ++;
							break;	
						}	
	
			        }	//close for int j		
				}	//close for int i 
				
				//For testing 
/*				for (int k = 0; k<machineLangOut.length; k++)
				{					
					if(machineLangOut[k] != null)
					{
						System.out.println(machineLangOut[k]);
					}					
				}*/
				
				return machineLangOut;

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
	}	//end method readInput 
	
	
	/**
	 * @method printOutput 
	 * <dt><b>Precondition:</b><dd>
	 * Output path is correctly specified as the second runtime parameter
	 * @param String outputFilePath: the path of the output txt file;
	 * 			String [] outputArray: the output array to print 
	 * @returns none
	 * <dt><b>Postconditions:</b><dd>
	 * The output file is created if it does not exist, and contains the 
	 * 	machine language instructions for evaluating each of the postfix 
	 * 	expressions contained in the input file
	 * @author Christine Herlihy
	 */
	public static void printOutput(String outputFilePath, String [] outputArray, long endTimeMain)
	{
		boolean outputParametersCorrect = false;
		
		while (outputParametersCorrect != true)
		{
			if (outputFilePath.length() != 0)
			{
				outputParametersCorrect = true;
			}	//close if 

			try (PrintStream output = new PrintStream(new File(outputFilePath)))
			{
				int numExpressions = 0;
				
				for (int k = 0; k<outputArray.length; k++)
				{			
					if(outputArray[k] != null)
					{
						output.println(outputArray[k]);
						
						//Count the number of expressions pseudo evaluated:						
						if(outputArray[k].length() > 7 && outputArray[k].substring(0,8).equals("Original"))
						{
							numExpressions ++;
						}
					}				
				}				
				//Generate information to evaluate efficiency of program
				output.println();
				output.println("The postfix evaluator program took " + endTimeMain + " milliseconds to run." + "\n" + numExpressions + " postfix expressions were evaluated.");	
	            output.close();
	            
	        } 
			catch (FileNotFoundException e) 
	        {
				System.out.println("File not found.");
	        }
		}	//close while (outputParametersCorrect != true)
	}	//end method printOutput
	

	
	/**
	 * @method isValidElement 
	 * <dt><b>Precondition:</b><dd>
	 * A StackNode element has been read in
	 * @param char elementToTest: a StackNode element
	 * @returns boolean []: the first value is whether the element is a valid
	 * 			operand (i.e. capital letter); second value is whether the
	 * 			element is a valid operator (i.e. +, -, *, /, %, $)
	 * <dt><b>Postconditions:</b><dd>
	 * None: 2 true/false values are returned, but element is unchanged
	 * @author Christine Herlihy
	 */
	public static boolean[] isValidElement(String elementToTest)
	{
		boolean isValidOperand = false;
		boolean isValidOperator = false;
		char elementToTestChar = elementToTest.charAt(0);
				
		if(Character.isUpperCase(elementToTestChar) == true)
		{
			isValidOperand = true;
		}
		
		else if(elementToTestChar == '+' | elementToTestChar == '-' | elementToTestChar 
				== '*' | elementToTestChar == '/' | elementToTestChar == '%' | 
						elementToTestChar == '$')
		{
			isValidOperator = true;
		}
		
		else{}	//if not an upper case letter or an operator, element is not
				//valid; the method should return FALSE 
		
		boolean [] outputArray = new boolean [2];
		outputArray[0] = isValidOperand;
		outputArray[1] = isValidOperator;
		
		return outputArray;
	}


}	//end class RunProgramLab1
