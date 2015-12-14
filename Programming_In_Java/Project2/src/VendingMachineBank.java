import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class VendingMachineBank extends CurrencySystem
{
	String filePath = "/Users/christine/Documents/workspace2/MiniProject2/src/VendingMachineBankInventory";
	
	/**
	*
	 @method VendingMachineProduct : Constructor to instantiate a new VendingMachineProduct object without any parameters
	 @author Christine Herlihy
	*/	
	VendingMachineBank()
	{
		
	}
	
	
	/**
	*
	 @method loadVendingMachineBank(String filePath) : Loads original vending machine bank from a comma-delimited txt file
	 			This text file should be formatted as follows: String currencyName, Double currencyValue, String currencyMaterial, Int currencyQuantity
	 @param String filePath : the txt file path in String format
	 @return String[][] : Prints and returns the original inventory bank array as a 2D string array
	 @author Christine Herlihy
	*/	
	public static String[][] loadVendingMachineBank(String filePath)	
	{
		
		String line = new String();
		int numRowsInFile = 10;
		int numFieldsInFile = 4;
		int row = 0;
		String outputArray [][] =  new String[numRowsInFile][numFieldsInFile];
		
		try(BufferedReader buffer = new BufferedReader(new FileReader(filePath)))
		{
				
			while (( line = buffer.readLine()) != null) 
			{
				String [] lineSplit = line.split("\\,");
				
				for (int j=0; j < numRowsInFile; j++) 
	            	{
						outputArray[row][0]=(lineSplit[0].trim());	//Currency object item name
						outputArray[row][1]=(lineSplit[1].trim());	////Currency object item value
						outputArray[row][2]=(lineSplit[2].trim());	//Currency object item material type
						outputArray[row][3]=lineSplit[3].trim();	//Currency object quantity in vending machine

	            	}	//close for loop
		        	row ++;	//Iterate through the rows
		        	
		        	//System.out.println(); //comment out so that the inventory output will be displayed at normal location on screen
			} //close while loop 
			
			VendingMachineBank tempBank = new VendingMachineBank();
			System.out.println("National currency accepted: " + tempBank.getCurrencyTicker() + ". Accepts bills? " + tempBank.acceptsPaperCurrency() + " Accepts coins? " + tempBank.acceptsMetalCurrency() + " Returns bills? " + tempBank.returnsPaperCurrency() + " Returns coins? " + tempBank.returnsMetalCurrency());
			System.out.println();
			
			for (int rowN = 0; rowN < 5; rowN ++)
			{
				//lines that are commented out will print a normal array; toString() is used instead
				//for(int colN = 0; colN <numFieldsInFile; colN++)
				//{
					//System.out.print(Arrays.asList(outputArray[rowN][colN])+ " ");
				PaperCurrency temp = new PaperCurrency((outputArray[rowN][0]).toString(),Double.parseDouble(outputArray[rowN][1]),(outputArray[rowN][2]).toString(),Integer.parseInt(outputArray[rowN][3]));
				System.out.println(rowN + ": " + temp.toString());
				//}
				//System.out.println();
				//System.out.println(outputArray[1][2]);	//FOR TESTING
			}	//close for 

			for (int rowN = 5; rowN < 10; rowN ++)
			{

				MetalCurrency temp = new MetalCurrency((outputArray[rowN][0]).toString(),Double.parseDouble(outputArray[rowN][1]),(outputArray[rowN][2]).toString(),Integer.parseInt(outputArray[rowN][3]));
				System.out.println(rowN + ": " + temp.toString());

			}	//close for 
			
		
		}	//close try 
		catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
		}
		catch (IOException e) 
		{
			System.out.println("An I/O Error occured.");
		}
		
		return outputArray;
	}	//close loadVendingMachineBank method
	

	/**
	*
	 @method loadVendingMachineBankClean(String filePath) : Loads original vending machine bank from a comma-delimited txt file
	 			This text file should be formatted as follows: String currencyName, Double currencyValue, String currencyMaterial, Int currencyQuantity
	 @param String filePath : the txt file path in String format
	 @return String[][] : Prints and returns the original bank array as a 2D string array, and prints with a shortened version of the toString() output
	 @author Christine Herlihy
	*/	
	public static String[][] loadVendingMachineBankClean(String filePath)	
	{
		
		String line = new String();
		int numRowsInFile = 10;
		int numFieldsInFile = 4;
		int row = 0;
		String outputArray [][] =  new String[numRowsInFile][numFieldsInFile];
		
		try(BufferedReader buffer = new BufferedReader(new FileReader(filePath)))
		{
				
			while (( line = buffer.readLine()) != null) 
			{
				String [] lineSplit = line.split("\\,");
				
				for (int j=0; j < numRowsInFile; j++) 
	            	{
						outputArray[row][0]=(lineSplit[0].trim());	//Currency object item name
						outputArray[row][1]=(lineSplit[1].trim());	////Currency object item value
						outputArray[row][2]=(lineSplit[2].trim());	//Currency object item material type
						outputArray[row][3]=lineSplit[3].trim();	//Currency object quantity in vending machine

	            	}	//close for loop
		        	row ++;	//Iterate through the rows

			} //close while loop 
			
			
			for (int rowN = 0; rowN < 5; rowN ++)
			{

				PaperCurrency temp = new PaperCurrency((outputArray[rowN][0]).toString(),Double.parseDouble(outputArray[rowN][1]),(outputArray[rowN][2]).toString(),Integer.parseInt(outputArray[rowN][3]));
				System.out.println(rowN + ": " + temp.toStringShort());
			}	//close for 

			for (int rowN = 5; rowN < 10; rowN ++)
			{

				MetalCurrency temp = new MetalCurrency((outputArray[rowN][0]).toString(),Double.parseDouble(outputArray[rowN][1]),(outputArray[rowN][2]).toString(),Integer.parseInt(outputArray[rowN][3]));
				System.out.println(rowN + ": " + temp.toStringShort());

			}	//close for 
	
		}	//close try 
		catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
		}
		catch (IOException e) 
		{
			System.out.println("An I/O Error occured.");
		}
			return outputArray;
	}	//close loadVendingMachineBank method
	
	/**
	*
	 @method loadVendingMachineBankNoPrint(String filePath) : Loads original vending machine bank from a comma-delimited txt file
	 			This text file should be formatted as follows: String currencyName, Double currencyValue, String currencyMaterial, Int currencyQuantity
	 @param String filePath : the txt file path in String format
	 @return String[][] : Returns the original bank array as a 2D string array, but DOES NOT print this array to the console 
	 @author Christine Herlihy
	*/	
	public static String[][] loadVendingMachineBankNoPrint(String filePath)	
	{
		
		String line = new String();
		int numRowsInFile = 10;
		int numFieldsInFile = 4;
		int row = 0;
		String outputArray [][] =  new String[numRowsInFile][numFieldsInFile];
		
		
		try(BufferedReader buffer = new BufferedReader(new FileReader(filePath)))
		{
				
			while (( line = buffer.readLine()) != null) 
			{
				String [] lineSplit = line.split("\\,");
				
				for (int j=0; j < numRowsInFile; j++) 
	            	{
						outputArray[row][0]=(lineSplit[0].trim());	//Currency object item name
						outputArray[row][1]=(lineSplit[1].trim());	////Currency object item value
						outputArray[row][2]=(lineSplit[2].trim());	//Currency object item material type
						outputArray[row][3]=lineSplit[3].trim();	//Currency object quantity in vending machine

	            	}	//close for loop
		        	row ++;	//Iterate through the rows
		        	
		        	//System.out.println(); //comment out so that the inventory output will be displayed at normal location on screen
			} //close while loop 
		}	//close try 
		catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
		}
		catch (IOException e) 
		{
			System.out.println("An I/O Error occured.");
		}
		return outputArray;
	}	//close loadVendingMachineBankNoPrint method
	

	/**
	*
	 @method addCurrencyAfterDeltaNoPrint(int itemInventoryRowIndex, String inventoryFilePath) : Loads original vending machine  bank file; "adds" currency received from sale
	 			This text file should be formatted as follows: String currencyName, Double currencyValue, String currencyMaterial, Int currencyQuantity
	 @param int itemInventoryRowIndex : the row index of the currency item to increase in the original inventory array
	 @param String filePath : the txt file path in String format
	 @return String[][] : Returns the resulting inventory bank array as a 2D string array, but DOES NOT print this array to the console 
	 @author Christine Herlihy
	*/	
	public static String [][] addCurrencyAfterDeltaNoPrint(int currencyItemRowIndex, String bankFilePath)
	{
		int row = currencyItemRowIndex;
		String file = bankFilePath;
		
		String [][] outputArrayTemp = loadVendingMachineBankNoPrint(file);

		int tempCurrencyItemQuantity = Integer.parseInt(outputArrayTemp[currencyItemRowIndex][3]);
		int newCurrencyQuantity = tempCurrencyItemQuantity +1;
		outputArrayTemp[currencyItemRowIndex][3] = String.valueOf(newCurrencyQuantity);
		//System.out.println("Original quantity: " + tempItemQuantity + "Current quantity: " + newQuantity);	//FOR TESTING 
		//System.out.println("There are " + newQuantity + outputArrayTemp[itemInventoryRowIndex][0] + "left.");	//FOR TESTING
		return outputArrayTemp;
	}
	
	
	/**
	*
	 @method generateCurrencyInventoryAfterDeltaNoPrint : Loads original vending machine bank file; "adds" item(s) received from sale; prints resulting bank array
	 			This text file should be formatted as follows: String currencyName, Double currencyValue, String currencyMaterial, Int currencyQuantity
 	 @param boolean [] booleanArrayBank : this is a boolean array that is the same length as bank array; if item i's quantity has changed, booleanArray[i] = true
 	 @param int [] counterArray : this is an integer array that is the same length as inventory array; each time item i's quantity decreases, counter[i] -- 
	 @param String inventoryFilePath : the original inventory txt file path in String format
	 @return String[][] : Returns the resulting inventory array as a 2D string array, but DOES NOT print this array to the console 
	 @author Christine Herlihy
	*/	
	public static String [][] generateCurrencyInventoryAfterDeltaNoPrint(boolean [] booleanArrayBank, int [] counterArray, String bankFilePath)
	{
		boolean [] tempBoolArray = booleanArrayBank;
		int [] counter = counterArray;
		//int row = itemInventoryRowIndex;
		String file = bankFilePath;
		int numRows = 9;
		
		
		//for i in string array [i][] if array [][i] == true, then subtract from this
		String [][] outputArrayTemp = loadVendingMachineBankNoPrint(file);
		
		for (int i = 0; i < tempBoolArray.length; i ++)
		{
			if (tempBoolArray[i] = true)
			{
				int tempCurrencyItemQuantity = Integer.parseInt(outputArrayTemp[i][3]);
				int newCurrencyQuantity = tempCurrencyItemQuantity + counter[i];
				outputArrayTemp[i][3] = String.valueOf(newCurrencyQuantity);
		
			}
			
			else if (tempBoolArray[i] = true)
			{
				outputArrayTemp[i][3] = outputArrayTemp[i][3];
			}
			
		}	//close for loop 
		return outputArrayTemp;
		}	//close displayCurrencyInventoryAfterDeltaNoPrint

	
	
	
	
	
	/**
	*
	 @method displayCurrencyInventoryAfterDeltaPrintArray : Loads original vending machine bank file; "adds" item(s) received from sale; prints resulting bank array
	 			This text file should be formatted as follows: String currencyName, Double currencyValue, String currencyMaterial, Int currencyQuantity
 	 @param boolean [] booleanArrayBank : this is a boolean array that is the same length as bank array; if item i's quantity has changed, booleanArray[i] = true
 	 @param int [] counterArray : this is an integer array that is the same length as inventory array; each time item i's quantity decreases, counter[i] -- 
	 @param String inventoryFilePath : the original inventory txt file path in String format
	 @return String[][] : Returns the resulting inventory array as a 2D string array, AND print this array to the console 
	 @author Christine Herlihy
	*/	
	public static String [][] displayCurrencyInventoryAfterDeltaPrintArray(boolean [] booleanArrayBank, int [] counterArray, String bankFilePath)
	{
		boolean [] tempBoolArray = booleanArrayBank;
		int [] counter = counterArray;
		//int row = itemInventoryRowIndex;
		String file = bankFilePath;
		int numRows = 9;
		
		
		//for i in string array [i][] if array [][i] == true, then subtract from this
		String [][] outputArrayTemp = loadVendingMachineBankNoPrint(file);
		
		for (int i = 0; i < tempBoolArray.length; i ++)
		{
			if (tempBoolArray[i] = true)
			{
				int tempCurrencyItemQuantity = Integer.parseInt(outputArrayTemp[i][3]);
				int newCurrencyQuantity = tempCurrencyItemQuantity + counter[i];
				outputArrayTemp[i][3] = String.valueOf(newCurrencyQuantity);
		
			}
			
			else if (tempBoolArray[i] = true)
			{
				outputArrayTemp[i][3] = outputArrayTemp[i][3];
			}
			
		}	//close for loop 

			for (int rowN = 0; rowN < 5; rowN ++)
			{

				PaperCurrency temp = new PaperCurrency((outputArrayTemp[rowN][0]).toString(),Double.parseDouble(outputArrayTemp[rowN][1]),(outputArrayTemp[rowN][2]).toString(),Integer.parseInt(outputArrayTemp[rowN][3]));
				System.out.println(rowN + ": " + temp.toStringShort());
			}	//close for 

			for (int rowN = 5; rowN < 10; rowN ++)
			{

				MetalCurrency temp = new MetalCurrency((outputArrayTemp[rowN][0]).toString(),Double.parseDouble(outputArrayTemp[rowN][1]),(outputArrayTemp[rowN][2]).toString(),Integer.parseInt(outputArrayTemp[rowN][3]));
				System.out.println(rowN + ": " + temp.toStringShort());

		}	//close for 
		return outputArrayTemp;
	}	//close displayCurrencyInventoryAfterDeltaPrintArray(boolean [] booleanArrayBank, int [] counterArray, String bankFilePath)
	
	
	/**
	*
	 @method subtractChangeFromInventoryPrintBankArray : Loads a bank inventory array after currency is added from sale; deducts change; returns resulting array
 	 @param String [][] bankArrayAfterSaleBeforeChange : The bank inventory array after a sale has been made and currency added, but before change is deducted
	 @param double changeToGive: The amount the user has deposited minus the item's purchase price 
	 @return String[][] : Returns the resulting inventory array as a 2D string array, AND print this array to the console 
	 @author Christine Herlihy
	*/	
	public static String [][] subtractChangeFromInventoryPrintBankArray(String [][] bankArrayAfterSaleBeforeChange, Double changeToGive)
	{
		double changeOwedToUser = changeToGive;
		
		
		String [][] outputArrayTemp = bankArrayAfterSaleBeforeChange;
		
		if (changeToGive%0.25 == 0 & changeToGive <= (Double.parseDouble(outputArrayTemp[6][3]))*(Double.parseDouble(outputArrayTemp[6][1])))	//quantity of quarters
		{
			//System.out.println("comes here");	//FOR TESTING 
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[6][3]);
			double change = (changeToGive/(Double.parseDouble(outputArrayTemp[6][1])));
			double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/(Double.parseDouble(outputArrayTemp[6][1])));
			outputArrayTemp[6][3] = String.valueOf(newCurrencyQuantity);

		}
					
		else if (changeToGive%1 == 0.25 & changeToGive <= (Double.parseDouble(outputArrayTemp[6][3]))*(Double.parseDouble(outputArrayTemp[6][1])))	//quantity of quarters
		{
			//System.out.println("comes here");	//FOR TESTING 
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[6][3]);
			double change = (changeToGive/(Double.parseDouble(outputArrayTemp[6][1])));
			double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/(Double.parseDouble(outputArrayTemp[6][1])));
			outputArrayTemp[6][3] = String.valueOf(newCurrencyQuantity);
			
			
		}
					
		else if (changeToGive%0.1 == 0 & changeToGive <=Double.parseDouble(outputArrayTemp[7][3])*(Double.parseDouble(outputArrayTemp[7][1])))	//quantity of dimes
		{
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[7][3]);
			double change = (changeToGive/(Double.parseDouble(outputArrayTemp[7][1])));
			double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/(Double.parseDouble(outputArrayTemp[7][1])));
			outputArrayTemp[7][3] = String.valueOf(newCurrencyQuantity);
			
		}
		
		else if (changeToGive%1 == 0.10 & changeToGive <=Double.parseDouble(outputArrayTemp[7][3])*(Double.parseDouble(outputArrayTemp[7][1])))	//quantity of dimes
		{
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[7][3]);
			double change = (changeToGive/(Double.parseDouble(outputArrayTemp[7][1])));
			double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/(Double.parseDouble(outputArrayTemp[7][1])));
			outputArrayTemp[7][3] = String.valueOf(newCurrencyQuantity);
			
		}
		
		else if (changeToGive%1 == 0.10 & changeToGive > Double.parseDouble(outputArrayTemp[7][3])*(Double.parseDouble(outputArrayTemp[7][1])) & changeToGive <= Double.parseDouble(outputArrayTemp[8][3])*(Double.parseDouble(outputArrayTemp[8][1])))	//quantity of dimes; nickels
		{
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[8][3]);
			double change = (changeToGive/Double.parseDouble(outputArrayTemp[8][1]));
			double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/Double.parseDouble(outputArrayTemp[8][1]));
			outputArrayTemp[8][3] = String.valueOf(newCurrencyQuantity);
			
		}
		
		
		else if (changeToGive%1 == 0 & changeToGive <= Double.parseDouble(outputArrayTemp[4][3])*1)	//quantity of one dollar bills 
		{
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[4][3]);
			double newCurrencyQuantity = tempCurrencyItemQuantity - changeToGive;
			outputArrayTemp[4][3] = String.valueOf(newCurrencyQuantity);
	
		}
		
			
		else if (changeToGive == 5 & changeToGive <= Double.parseDouble(outputArrayTemp[3][3]))	//quantity of five dollar bills
		{
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[3][3]);
			double newCurrencyQuantity = tempCurrencyItemQuantity - 1;
			outputArrayTemp[3][3] = String.valueOf(newCurrencyQuantity);
			
		}
		
		else if (changeToGive%10==0 & changeToGive <= Double.parseDouble(outputArrayTemp[2][3])*10)	//quantity of ten dollar bills
		{
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[2][3]);
			double change = (changeToGive/10);
			double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/10);
			outputArrayTemp[2][3] = String.valueOf(newCurrencyQuantity);
			
		}
		
		else if (changeToGive%20 ==0 & changeToGive <= Double.parseDouble(outputArrayTemp[1][3])*20)	//quantity of twenty dollar bills
		{
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[1][3]);
			double change = (changeToGive/20);
			double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/20);
			outputArrayTemp[1][3] = String.valueOf(newCurrencyQuantity);
		
		}
		
		else if (changeToGive == 100 & changeToGive <= Double.parseDouble(outputArrayTemp[0][3]))	//quantity of twenty dollar bills
		{
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[0][3]);
			double change = 1;
			double newCurrencyQuantity = tempCurrencyItemQuantity - 1;
			outputArrayTemp[0][3] = String.valueOf(newCurrencyQuantity);
			
		}
		
		
		
		for (int rowN = 0; rowN < 5; rowN ++)
		{

			PaperCurrency temp = new PaperCurrency((outputArrayTemp[rowN][0]).toString(),Double.parseDouble(outputArrayTemp[rowN][1]),(outputArrayTemp[rowN][2]).toString(),Double.parseDouble(outputArrayTemp[rowN][3]));
			System.out.println(rowN + ": " + temp.toStringShort2());
		}	//close for 

		for (int rowN = 5; rowN < 10; rowN ++)
		{

			MetalCurrency temp = new MetalCurrency((outputArrayTemp[rowN][0]).toString(),Double.parseDouble(outputArrayTemp[rowN][1]),(outputArrayTemp[rowN][2]).toString(),Double.parseDouble(outputArrayTemp[rowN][3]));
			System.out.println(rowN + ": " + temp.toStringShort2());

		}
		
		return outputArrayTemp;
		}
		
	
	
	/**
	*
	 @method subtractChangeFromInventoryNoPrint : Loads a bank inventory array after currency is added from sale; deducts change; returns resulting array
 	 @param String [][] bankArrayAfterSaleBeforeChange : The bank inventory array after a sale has been made and currency added, but before change is deducted
	 @param double changeToGive: The amount the user has deposited minus the item's purchase price 
	 @return String[][] : Returns the resulting inventory array as a 2D string array, but DOES NOT print this array to the console 
	 @author Christine Herlihy
	*/	
	public static String [][] subtractChangeFromInventoryNoPrint(String [][] bankArrayAfterSaleBeforeChange, Double changeToGive)
	{
		double changeOwedToUser = changeToGive;
		
		
		String [][] outputArrayTemp = bankArrayAfterSaleBeforeChange;
		
		if (changeToGive%0.25 == 0 & changeToGive <= (Double.parseDouble(outputArrayTemp[6][3]))*(Double.parseDouble(outputArrayTemp[6][1])))	//quantity of quarters
		{
			//System.out.println("comes here");	//FOR TESTING
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[6][3]);
			double change = (changeToGive/(Double.parseDouble(outputArrayTemp[6][1])));
			double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/(Double.parseDouble(outputArrayTemp[6][1])));
			outputArrayTemp[6][3] = String.valueOf(newCurrencyQuantity);
			

		}
					
		else if (changeToGive%1 == 0.25 & changeToGive <= (Double.parseDouble(outputArrayTemp[6][3]))*(Double.parseDouble(outputArrayTemp[6][1])))	//quantity of quarters
		{
			//System.out.println("comes here");	//FOR TESTING 
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[6][3]);
			double change = (changeToGive/(Double.parseDouble(outputArrayTemp[6][1])));
			double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/(Double.parseDouble(outputArrayTemp[6][1])));
			outputArrayTemp[6][3] = String.valueOf(newCurrencyQuantity);
			
			
		}
					
		else if (changeToGive%0.1 == 0 & changeToGive <=Double.parseDouble(outputArrayTemp[7][3])*(Double.parseDouble(outputArrayTemp[7][1])))	//quantity of dimes
		{
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[7][3]);
			double change = (changeToGive/(Double.parseDouble(outputArrayTemp[7][1])));
			double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/(Double.parseDouble(outputArrayTemp[7][1])));
			outputArrayTemp[7][3] = String.valueOf(newCurrencyQuantity);
			
		}
		
		else if (changeToGive%1 == 0.10 & changeToGive <=Double.parseDouble(outputArrayTemp[7][3])*(Double.parseDouble(outputArrayTemp[7][1])))	//quantity of dimes
		{
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[7][3]);
			double change = (changeToGive/(Double.parseDouble(outputArrayTemp[7][1])));
			double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/(Double.parseDouble(outputArrayTemp[7][1])));
			outputArrayTemp[7][3] = String.valueOf(newCurrencyQuantity);
			
		}
		
		else if (changeToGive%1 == 0.10 & changeToGive > Double.parseDouble(outputArrayTemp[7][3])*(Double.parseDouble(outputArrayTemp[7][1])) & changeToGive <= Double.parseDouble(outputArrayTemp[8][3])*(Double.parseDouble(outputArrayTemp[8][1])))	//quantity of dimes; nickels
		{
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[8][3]);
			double change = (changeToGive/Double.parseDouble(outputArrayTemp[8][1]));
			double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/Double.parseDouble(outputArrayTemp[8][1]));
			outputArrayTemp[8][3] = String.valueOf(newCurrencyQuantity);
			
		}
		
		
		else if (changeToGive%1 == 0 & changeToGive <= Double.parseDouble(outputArrayTemp[4][3])*1)	//quantity of one dollar bills 
		{
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[4][3]);
			double newCurrencyQuantity = tempCurrencyItemQuantity - changeToGive;
			outputArrayTemp[4][3] = String.valueOf(newCurrencyQuantity);
	
		}
		
			
		else if (changeToGive == 5 & changeToGive <= Double.parseDouble(outputArrayTemp[3][3]))	//quantity of five dollar bills
		{
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[3][3]);
			double newCurrencyQuantity = tempCurrencyItemQuantity - 1;
			outputArrayTemp[3][3] = String.valueOf(newCurrencyQuantity);
			
		}
		
		else if (changeToGive%10==0 & changeToGive <= Double.parseDouble(outputArrayTemp[2][3])*10)	//quantity of ten dollar bills
		{
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[2][3]);
			double change = (changeToGive/10);
			double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/10);
			outputArrayTemp[2][3] = String.valueOf(newCurrencyQuantity);
			
		}
		
		else if (changeToGive%20 ==0 & changeToGive <= Double.parseDouble(outputArrayTemp[1][3])*20)	//quantity of twenty dollar bills
		{
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[1][3]);
			double change = (changeToGive/20);
			double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/20);
			outputArrayTemp[1][3] = String.valueOf(newCurrencyQuantity);
		
		}
		
		else if (changeToGive == 100 & changeToGive <= Double.parseDouble(outputArrayTemp[0][3]))	//quantity of twenty dollar bills
		{
			double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[0][3]);
			double change = 1;
			double newCurrencyQuantity = tempCurrencyItemQuantity - 1;
			outputArrayTemp[0][3] = String.valueOf(newCurrencyQuantity);
			
		}
	
		return outputArrayTemp;
		}	// close subtractChangeFromInventoryNoPrint method
	
	/**
	*
	 @method subtractChangeFromInventoryPrintChange : Loads a bank inventory array after currency is added from sale; deducts change; returns resulting change
 	 @param String [][] bankArrayAfterSaleBeforeChange : The bank inventory array after a sale has been made and currency added, but before change is deducted
	 @param double changeToGive: The amount the user has deposited minus the item's purchase price 
	 @return String changeOut: Returns number and name of currency objects owed to the user as change 
	 @author Christine Herlihy
	*/	
		public static String [][]  subtractChangeFromInventoryPrintChange(String [][] bankArrayAfterSaleBeforeChange, Double changeOwedToUser)
		{
			double changeToGive = changeOwedToUser;
			
			
			String [][] outputArrayTemp = bankArrayAfterSaleBeforeChange;
			String changeOut = new String();
			
			if (changeToGive%0.25 == 0 & changeToGive <= (Double.parseDouble(outputArrayTemp[6][3]))*(Double.parseDouble(outputArrayTemp[6][1])))	//quantity of quarters
			{
				//System.out.println("comes here");	//FOR TESTING 
				double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[6][3]);
				double change = (changeToGive/(Double.parseDouble(outputArrayTemp[6][1])));
				double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/(Double.parseDouble(outputArrayTemp[6][1])));
				outputArrayTemp[6][3] = String.valueOf(newCurrencyQuantity);
				//System.out.println(change);	//FOR TESTING
				//System.out.println(changeToGive);	//FOR TESTING 
				System.out.println(Double.parseDouble(outputArrayTemp[6][1]));
				changeOut = "Your change is: " + String.valueOf(change) + " " + outputArrayTemp[6][0] + "s";

			}
						
			else if (changeToGive%1 == 0.25 & changeToGive <= (Double.parseDouble(outputArrayTemp[6][3]))*(Double.parseDouble(outputArrayTemp[6][1])))	//quantity of quarters
			{
				//System.out.println("comes here");	//FOR TESTING
				double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[6][3]);
				double change = (changeToGive/(Double.parseDouble(outputArrayTemp[6][1])));
				double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/(Double.parseDouble(outputArrayTemp[6][1])));
				outputArrayTemp[6][3] = String.valueOf(newCurrencyQuantity);
				changeOut = "Your change is: " + String.valueOf(change) + " " + outputArrayTemp[6][0] + "s";
				
			}
						
			else if (changeToGive%0.1 == 0 & changeToGive <=Double.parseDouble(outputArrayTemp[7][3])*(Double.parseDouble(outputArrayTemp[7][1])))	//quantity of dimes
			{
				double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[7][3]);
				double change = (changeToGive/(Double.parseDouble(outputArrayTemp[7][1])));
				double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/(Double.parseDouble(outputArrayTemp[7][1])));
				outputArrayTemp[7][3] = String.valueOf(newCurrencyQuantity);
				changeOut = "Your change is: " + String.valueOf(change) + " " + outputArrayTemp[7][0] + "s";
			}
			
			else if (changeToGive%1 == 0.10 & changeToGive <=Double.parseDouble(outputArrayTemp[7][3])*(Double.parseDouble(outputArrayTemp[7][1])))	//quantity of dimes
			{
				double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[7][3]);
				double change = (changeToGive/(Double.parseDouble(outputArrayTemp[7][1])));
				double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/(Double.parseDouble(outputArrayTemp[7][1])));
				outputArrayTemp[7][3] = String.valueOf(newCurrencyQuantity);
				changeOut = "Your change is: " + String.valueOf(change) + " " + outputArrayTemp[7][0] + "s";
			}
			
			else if (changeToGive%1 == 0.10 & changeToGive > Double.parseDouble(outputArrayTemp[7][3])*(Double.parseDouble(outputArrayTemp[7][1])) & changeToGive <= Double.parseDouble(outputArrayTemp[8][3])*(Double.parseDouble(outputArrayTemp[8][1])))	//quantity of dimes; nickels
			{
				double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[8][3]);
				double change = (changeToGive/Double.parseDouble(outputArrayTemp[8][1]));
				double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/Double.parseDouble(outputArrayTemp[8][1]));
				outputArrayTemp[8][3] = String.valueOf(newCurrencyQuantity);
				changeOut = "Your change is: " + String.valueOf(change) + " " + outputArrayTemp[8][0] + "s";
			}
			
			
			else if (changeToGive%1 == 0 & changeToGive <= Double.parseDouble(outputArrayTemp[4][3])*1)	//quantity of one dollar bills 
			{
				double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[4][3]);
				double newCurrencyQuantity = tempCurrencyItemQuantity - changeToGive;
				outputArrayTemp[4][3] = String.valueOf(newCurrencyQuantity);
				changeOut = "Your change is: " + String.valueOf(changeToGive) + " " + outputArrayTemp[4][0] + "s";
			}
			
				
			else if (changeToGive == 5 & changeToGive <= Double.parseDouble(outputArrayTemp[3][3]))	//quantity of five dollar bills
			{
				double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[3][3]);
				double newCurrencyQuantity = tempCurrencyItemQuantity - 1;
				outputArrayTemp[3][3] = String.valueOf(newCurrencyQuantity);
				changeOut = "Your change is: " + 1 + " " + outputArrayTemp[3][0];
			}
			
			else if (changeToGive%10==0 & changeToGive <= Double.parseDouble(outputArrayTemp[2][3])*10)	//quantity of ten dollar bills
			{
				double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[2][3]);
				double change = (changeToGive/10);
				double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/10);
				outputArrayTemp[2][3] = String.valueOf(newCurrencyQuantity);
				changeOut = "Your change is: " + String.valueOf(change) + " " + outputArrayTemp[2][0] + "s";
			}
			
			else if (changeToGive%20 ==0 & changeToGive <= Double.parseDouble(outputArrayTemp[1][3])*20)	//quantity of twenty dollar bills
			{
				double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[1][3]);
				double change = (changeToGive/20);
				double newCurrencyQuantity = tempCurrencyItemQuantity - (changeToGive/20);
				outputArrayTemp[1][3] = String.valueOf(newCurrencyQuantity);
				changeOut = "Your change is: " + String.valueOf(change) + " " + outputArrayTemp[1][0] + "s";
			}
			
			else if (changeToGive == 100 & changeToGive <= Double.parseDouble(outputArrayTemp[0][3]))	//quantity of twenty dollar bills
			{
				double tempCurrencyItemQuantity = Double.parseDouble(outputArrayTemp[0][3]);
				double change = 1;
				double newCurrencyQuantity = tempCurrencyItemQuantity - 1;
				outputArrayTemp[0][3] = String.valueOf(newCurrencyQuantity);
				changeOut = "Your change is: " + String.valueOf(change) + " " + outputArrayTemp[0][0] + "s";
			}
			
			//System.out.println(changeOut);	//FOR TESTING
			return outputArrayTemp;

	}//close subtractChangeFromInventoryPrintChange method
		
		
		
	
	
	
	
	
}	//close VendingMachineBank class
