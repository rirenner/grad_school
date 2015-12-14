
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class VendingMachineProduct 
{
	String itemName = new String(); //example: chips
	String packageType = new String();	//example: bag
	Double productPrice = 0.00;	//example 0.75
	int inventoryQuantity = 0;
	

	String sourceFilePath = new String();
	String line = new String();
	int numRowsInFile = 9;
	int numFieldsInFile = 4;
	int row = 0;
	int counter = 0;
	String outputArray [][] =  new String[numRowsInFile][numFieldsInFile];
	
	/**
	*
	 @method VendingMachineProduct : Constructor to instantiate a new VendingMachineProduct object without any parameters
	 @author Christine Herlihy
	*/	
	public  VendingMachineProduct()
	{
		
	}
	
	/**
	*
	 @method VendingMachineProduct : Constructor to instantiate a new VendingMachineProduct object
	 @param String itemName : the item's name (i.e., "chips")
	 @param String packageType : the item's package/container (i.e. "bag")
	 @param Double price : the item's price, in the format ##.##
	 @param String displayCode : a one character string for how the object should be "displayed" in the vending machine (i.e. "X")
	 @author Christine Herlihy
	*/	
	public VendingMachineProduct(String itemName, String packageKind, Double productPrice, int quantity)
	{
		this.itemName = itemName;
		this.packageType = packageKind;
		this.productPrice = productPrice;
		this.inventoryQuantity = quantity;

	}
	
	/**
	*
	 @method loadVendingMachineInventory(String filePath) : Loads original vending machine inventory from a comma-delimited txt file
	 			This text file should be formatted as follows: String productName, String packageType, Double productPrice, Int productQuantity
	 @param String filePath : the txt file path in String format
	 @return String[][] : Prints and returns the original inventory array as a 2D string array
	 @author Christine Herlihy
	*/	
	public static String[][] loadVendingMachineInventory(String filePath)
	{
		String sourceFilePath = filePath;
		String line = new String();
		int numRowsInFile = 9;
		int numFieldsInFile = 4;
		int row = 0;
		String outputArray [][] =  new String[numRowsInFile][numFieldsInFile];

	     try(BufferedReader buffer = new BufferedReader(new FileReader(sourceFilePath)))
			{
					
				while (( line = buffer.readLine()) != null) 
				{
					String [] lineSplit = line.split("\\,");
					
					for (int j=0; j < numRowsInFile; j++) 
		            	{
							outputArray[row][0]=(lineSplit[0].trim());	//Vending machine product item name
							outputArray[row][1]=(lineSplit[1].trim());	////Vending machine product package type
							outputArray[row][2]=(lineSplit[2].trim());	//Vending machine product price
							outputArray[row][3]=lineSplit[3].trim();	//Vending machine product quantity

		            	}	//close for loop
			        	row ++;	//Iterate through the rows
			        	
			        	//System.out.println(); //comment out so that the inventory output will be displayed at normal location on screen
				} //close while loop 
				
				
				for (int rowN = 0; rowN < numRowsInFile; rowN ++)
				{
					//lines that are commented out will print a normal array; toString() is used instead
					//for(int colN = 0; colN <numFieldsInFile; colN++)
					//{
						//System.out.print(Arrays.asList(outputArray[rowN][colN])+ " ");
					VendingMachineProduct temp = new VendingMachineProduct((outputArray[rowN][0]).toString(),(outputArray[rowN][1]).toString(),Double.parseDouble(outputArray[rowN][2]),Integer.parseInt(outputArray[rowN][3]));
					System.out.println(rowN + ": " + temp.toString());
					//}
					//System.out.println();
					//System.out.println(outputArray[1][2]);	//FOR TESTING
				}	//close for 

				
			}	 //close try 

			catch (FileNotFoundException e)
			{
				System.out.println("File not found.");
			}
			catch (IOException e) 
			{
				System.out.println("An I/O Error occured.");
			}

	     	return outputArray;
	}	//close loadVendingMachineInventory method
	
	
	/**
	*
	 @method loadVendingMachineInventoryNoPrint(String filePath) : Loads original vending machine inventory from a comma-delimited txt file
	 			This text file should be formatted as follows: String productName, String packageType, Double productPrice, Int productQuantity
	 @param String filePath : the txt file path in String format
	 @return String[][] : Returns the original inventory array as a 2D string array, but DOES NOT print this array to the console 
	 @author Christine Herlihy
	*/	
	public static String[][] loadVendingMachineInventoryNoPrint(String filePath)
	{
		String sourceFilePath = filePath;
		String line = new String();
		int numRowsInFile = 9;
		int numFieldsInFile = 4;
		int row = 0;
		String outputArray [][] =  new String[numRowsInFile][numFieldsInFile];

	     try(BufferedReader buffer = new BufferedReader(new FileReader(sourceFilePath)))
			{
					
				while (( line = buffer.readLine()) != null) 
				{
					String [] lineSplit = line.split("\\,");
					
					for (int j=0; j < numRowsInFile; j++) 
		            	{
							outputArray[row][0]=(lineSplit[0].trim());	//Vending machine product item name
							outputArray[row][1]=(lineSplit[1].trim());	////Vending machine product package type
							outputArray[row][2]=(lineSplit[2].trim());	//Vending machine product price
							outputArray[row][3]=lineSplit[3].trim();	//Vending machine product quantity

		            	}	//close for loop
			        	row ++;	//Iterate through the rows
			        	
			        	//System.out.println(); //comment out so that the inventory output will be displayed at normal location on screen
				} //close while loop 				
			}	 //close try 

			catch (FileNotFoundException e)
			{
				System.out.println("File not found.");
			}
			catch (IOException e) 
			{
				System.out.println("An I/O Error occured.");
			}

	     	return outputArray;
	}	//close loadVendingMachineInventory method
	
	
	/**
	*
	 @method dispenseItemAfterDeltaNoPrint(int itemInventoryRowIndex, String inventoryFilePath) : Loads original vending machine inventory file; "subtracts" item dispensed
	 			This text file should be formatted as follows: String productName, String packageType, Double productPrice, Int productQuantity
	 @param int itemInventoryRowIndex : the row index of the item to dispense in the original inventory array
	 @param String filePath : the txt file path in String format
	 @return String[][] : Returns the resulting inventory array as a 2D string array, but DOES NOT print this array to the console 
	 @author Christine Herlihy
	*/	
	public static String [][] dispenseItemAfterDeltaNoPrint(int itemInventoryRowIndex, String inventoryFilePath)
	{
		int row = itemInventoryRowIndex;
		String file = inventoryFilePath;
		
		String [][] outputArrayTemp = loadVendingMachineInventoryNoPrint(file);

		int tempItemQuantity = Integer.parseInt(outputArrayTemp[itemInventoryRowIndex][3]);
		int newQuantity = tempItemQuantity -1;
		outputArrayTemp[itemInventoryRowIndex][3] = String.valueOf(newQuantity);
		//System.out.println("Original quantity: " + tempItemQuantity + "Current quantity: " + newQuantity);	//FOR TESTING 
		//System.out.println("There are " + newQuantity + outputArrayTemp[itemInventoryRowIndex][0] + "left.");	//FOR TESTING
		return outputArrayTemp;
	}
	
	
	/**
	*
	 @method displayItemInventoryAfterDeltaPrintArray : Loads original vending machine inventory file; "subtracts" item(s) dispensed; prints resulting inventory array
	 			This text file should be formatted as follows: String productName, String packageType, Double productPrice, Int productQuantity
 	 @param boolean [] booleanArray : this is a boolean array that is the same length as inventory array; if item i's quantity has changed, booleanArray[i] = true
 	 @param int [] counterArray : this is an integer array that is the same length as inventory array; each time item i's quantity decreases, counter[i] -- 
	 @param String inventoryFilePath : the original inventory txt file path in String format
	 @return String[][] : Returns the resulting inventory array as a 2D string array, AND print this array to the console 
	 @author Christine Herlihy
	*/	
	public static String [][] displayItemInventoryAfterDeltaPrintArray(boolean [] booleanArray, int [] counterArray, String inventoryFilePath)
	{
		boolean [] tempBoolArray = booleanArray;
		int [] counter = counterArray;
		//int row = itemInventoryRowIndex;
		String file = inventoryFilePath;
		int numRows = 9;
		
		//for i in string array [i][] if array [][i] == true, then subtract from this
		String [][] outputArrayTemp = loadVendingMachineInventoryNoPrint(file);
		
		for (int i = 0; i < tempBoolArray.length; i ++)
		{
			if (tempBoolArray[i] = true)
			{
				int tempItemQuantity = Integer.parseInt(outputArrayTemp[i][3]);
				int newQuantity = tempItemQuantity - counter[i];
				outputArrayTemp[i][3] = String.valueOf(newQuantity);
			}
			
			else if (tempBoolArray[i] = true)
			{
				outputArrayTemp[i][3] = outputArrayTemp[i][3];
			}
			
		}	//close for loop 

		for (int rowN = 0; rowN < numRows; rowN ++)
		{
			VendingMachineProduct temp = new VendingMachineProduct((outputArrayTemp[rowN][0]).toString(),(outputArrayTemp[rowN][1]).toString(),Double.parseDouble(outputArrayTemp[rowN][2]),Integer.parseInt(outputArrayTemp[rowN][3]));
			System.out.println(rowN + ": " + temp.toString());
		}	//close for 
		return outputArrayTemp;
	}
	

	
	/**
	*
	 @method getItemName(): returns a VendingMachineProduct object's name 
	 @returns String : this.itemName
	 @author Christine Herlihy
	*/	
	public String getItemName()
	{
		return this.itemName;
	}
	
	/**
	*
	 @method getPackageType(): returns a VendingMachineProduct object's package type
	 @returns String : this.packageType
	 @author Christine Herlihy
	*/	
	public String getPackageType()
	{
		return this.packageType;
	}

	
	/**
	*
	 @method getPackagePrice(): returns a VendingMachineProduct object's price
	 @returns Double : this.productPrice
	 @author Christine Herlihy
	*/	
	public double getPrice()
	{
		return this.productPrice;
	}
	


	


	/**
	*
	 @method toString(): overrides the object's toString method to provide info about the vending machine product object
	 @returns String 
	 @author Christine Herlihy
	*/	
	public String toString() 
	{
		return "InventoryItem: " + "Name: " + itemName + "; Package: " + packageType + "; Price: " + productPrice + "; Quantity: "
				+ inventoryQuantity + " IN STOCK";
	}


}	//close VendingMachineProduct class