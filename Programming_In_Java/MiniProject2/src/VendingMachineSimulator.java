import java.util.Scanner;

/*
 * Create a new multi-class Java program which implements a vending machine simulator which
contains the following functionality:

	A) At program startup, the vending machine is loaded with a variety of products in a variety of
	packaging for example soda/tonic/Coke in bottles, peanuts in bags, juice in cartons, etc. Also
	included is the cost of each item. The program should be designed to easily load a different set
	of products easily (for example, from a file).

Also at program startup, money should be loaded into the vending machine. Money should
consist of different monetary objects for the specified currency for example $1 bills, $5 bills,
quarters, dimes, etc. Your program should be designed to use different national currencies
easily (for example the Euro). Money should be maintained as paper bills and coins, not just
amounts.


A menu of commands must be provided. At a minimum the menu should consists of the
following commands:
1. Display the list of commands
2. Display the vending machine inventory. For each item, this command should result in
displaying a description and current quantity.
3. Display the money currently held in the vending machine.
4. Purchase an item. The result of this selection should be the following actions:
	1. Prompt the user to indicate what item to purchase
	2. Prompt the user to specify what monetary items are being used for payment (the actual
	items for example quarters, dimes, etc.), not a money amount
	3. If the user specified enough money to purchase the selected item, the item is purchased
	(deducted from inventory), supplied money is added to the vending machine, and any
	change is returned in the form of monetary items (quarters, dimes, etc.).
	4. If the user did not specify enough money for the selected item, the transaction is aborted
	with the supplied money not added to the machine (not accepted) and the product not
	purchased (i.e. the state of the vending machine is unchanged).
5. Exit – exits the program displaying a departing message.
 * 
 * 
 * 
 */


public class VendingMachineSimulator 
{
	public static void main(String [] args) throws NumberFormatException
	{
		Scanner input = new Scanner(System.in);
		boolean chooseAgain = true;
		String commandString = new String();
		int command= 0;
		int itemToPurchaseId = -1;
		int currencyItemsToUseInt = -1;
		int currencyTypeInt = -1;
		boolean choiceValid = false;
		boolean choiceToBuy = false;
		boolean currencyChoiceValid = false;
		boolean enoughMoneyToBuy = false;
		boolean inventoryDelta = false;
		boolean bankDelta = false;
		int numProductTypesInInventory = 9; 	//make this dyanmic from inventory txt file?
		int [] counter = new int [numProductTypesInInventory];
		boolean [] inventoryDeltaBoolArray= new boolean [numProductTypesInInventory];
		int numUniqueCurrencyItems = 10;	//make this dynamic from inventory txt file?
		boolean [] bankDeltaBoolArray = new boolean [numUniqueCurrencyItems];
		int [] counterBank = new int [numUniqueCurrencyItems];
		int counterCurMoreThanOne = 0;
		int [] curMoreThanOneTypeArray = new int [200];
		double totalCurrencyDeposited = 0.00;
		int i = 0;
		int productQuantity = 0;
		double purchasePrice = 0.00;
		double currencyDeposited = 0.00;
		double changeOwed = 0.00;
		String [][] outputStepA = new String [200][200];
		String filePath = "/Users/christine/Documents/workspace2/MiniProject2/src/VendingMachineInventoryFile";
		String filePath2 = "/Users/christine/Documents/workspace2/MiniProject2/src/VendingMachineBankInventory";
		
		printMainMenu();	//print the main menu for the user 
		
		outer: while (chooseAgain != false)	//allows the user to keep selecting from main menu until they choose to exit
		{

			inner1: while (choiceValid != true)
			{
				try
				{
					System.out.println("Command: ");
					commandString = input.next().trim();	//allow the user to enter his/her choice
					command = Integer.parseInt(commandString);	//cast the string command to an integer
					
					//Make sure command entered is valid (i.e. an integer within range)
					if (command <= 4 & command >=0)
					{
						//System.out.println("A.You chose" + command);	//FOR TESTING 
						choiceValid = true;
						break inner1;
					}
	
					else if (command > 4 || command <0)
					{
						System.out.println("Invalid command. Please enter a number 0-4.");
					}
				}	//close try
				catch (NumberFormatException e)
				{
					System.out.println("Invalid command. Please enter a number 0-4.");
				}
				catch (Exception e)
				{
					System.out.println("Invalid command. Please enter a number 0-4.");
				}
			}	//close while (choiceValid != true) loop 

			inner2: switch(command)
			{
			case 0: //Display main menu 
				{
					printMainMenu();
					command = -1;
					choiceValid = false;
					break;
				}
			case 1: //Display vending machine inventory
				{
					System.out.println("B. You chose 1.");	//FOR TESTING
					System.out.println("Vending Machine Inventory:");
					System.out.println("---------------------------");
				
				//Original inventory file; print this one up until an item is dispensed from the vending machine 
				if (inventoryDelta !=true)
				{

					VendingMachineProduct.loadVendingMachineInventory(filePath);
					System.out.println();
				}
				else if (inventoryDelta = true)
				{
					VendingMachineProduct.displayItemInventoryAfterDeltaPrintArray(inventoryDeltaBoolArray, counter, filePath);
					System.out.println();
				}	
					
					
					
					command = -1;
					choiceValid = false;
					break;
				}
			case 2: //Display currency currently in the vending machine
				{
					System.out.println("B. You chose 2");	//FOR TESTING
					System.out.println("Vending Machine Currency Inventory:");
					System.out.println("---------------------------");
					
					if (bankDelta !=true)
					{
						
						VendingMachineBank.loadVendingMachineBank(filePath2);
						System.out.println();
					}
					else if (bankDelta = true)
					{
						String [][] outputStepB =VendingMachineBank.generateCurrencyInventoryAfterDeltaNoPrint(bankDeltaBoolArray, counterBank, filePath2);
						VendingMachineBank.subtractChangeFromInventoryPrintBankArray(outputStepB, changeOwed);
						System.out.println();
					}	

					command = -1;
					choiceValid = false;
					break;
				}
			case 3: //Purchase an item from the vending machine 
				{
					//System.out.println("C. You chose 3");	//FOR TESTING
					inner3: while (choiceToBuy != true)
					{
						try
						{
							System.out.println("Enter the number of the item you'd like to purchase: ");
							String itemToPurchase = input.next().trim();
							itemToPurchaseId = Integer.parseInt(itemToPurchase);
							VendingMachineProduct temp = new VendingMachineProduct();
							
							//Make sure command entered is valid (i.e. an integer within range)
							if (itemToPurchaseId <= numProductTypesInInventory & itemToPurchaseId >=0)
							{
								//Display for user the name of the item they have selected, along with this item's price 
								System.out.println("You chose: " + VendingMachineProduct.loadVendingMachineInventoryNoPrint(filePath)[itemToPurchaseId][0]);	 
								System.out.println("This item costs: $" + Double.parseDouble(VendingMachineProduct.loadVendingMachineInventoryNoPrint(filePath)[itemToPurchaseId][2]) + "\n");
								//Break out of choice to buy try/catch loop 
								choiceToBuy = true;
								break inner3;
							}
							else if (itemToPurchaseId > numProductTypesInInventory || itemToPurchaseId <0)
							{
								System.out.println("Invalid command. Please enter a number 0- " + numProductTypesInInventory);
							}
						}	//close try
						catch (NumberFormatException e)
						{
							System.out.println("Invalid command. Please enter a number 0- " + numProductTypesInInventory);
						}
						catch (Exception e)
						{
							System.out.println("Invalid command. Please enter a number 0- " + numProductTypesInInventory);
						}
					}	//close inner3: while (choiceToBuy != true)
					
					//Allow user to input how many currency items they want to use to buy the item 
					inner4: while ( currencyChoiceValid != true)
					{
						try
						{
							System.out.println("How many different currency items would you like to use?");
							System.out.println();
							System.out.println("Available currency denominations include: " );
							System.out.println("------------------------------------------" );
							VendingMachineBank.loadVendingMachineBankClean(filePath2);
							String currencyItemsToUse = input.next().trim();
							currencyItemsToUseInt  = Integer.parseInt(currencyItemsToUse);
							
							//Make sure command entered is valid (i.e. an integer within range)
							if (currencyItemsToUseInt <= numUniqueCurrencyItems & currencyItemsToUseInt >=1)
							{
								//System.out.println("CURRENCY ITEM!" + currencyItemsToUseInt);	//FOR TESTING 
								//Break out of choice to buy try/catch loop 
								currencyChoiceValid = true;
								break inner4;
							}
							else if (currencyItemsToUseInt > numUniqueCurrencyItems || currencyItemsToUseInt <1)
							{
								System.out.println("Invalid command. Please enter a number 1- " + numUniqueCurrencyItems);
							}
						}	//close try
						catch (NumberFormatException e)
						{
							System.out.println("Invalid command. Please enter a number 1- " + numUniqueCurrencyItems);
						}
						catch (Exception e)
						{
							System.out.println("Invalid command. Please enter a number 1- " + numUniqueCurrencyItems);
						}

					}	//close inner4: (while enoughMoneyToBuy != true)
					
					//Allow user to input how many currency items they want to use to buy the item 
					inner5: while ( enoughMoneyToBuy != true)
					{
						if (currencyItemsToUseInt == 1)
						{
							try
								{
									System.out.println("Enter the number corresponding to the currency type would you like to use:");
									System.out.println();
									String currencyType = input.next().trim();
									currencyTypeInt  = Integer.parseInt(currencyType);
								}
								catch (NumberFormatException e)
								{
									System.out.println("Invalid command. Please enter a number 0- " + numUniqueCurrencyItems);
								}
								catch (Exception e)
								{
									System.out.println("Invalid command. Please enter a number 0- " + numUniqueCurrencyItems);
								}

							//Get the price of the item the user has selected
							purchasePrice = Double.parseDouble(VendingMachineProduct.loadVendingMachineInventoryNoPrint(filePath)[itemToPurchaseId][2]);
							productQuantity = Integer.parseInt(VendingMachineProduct.loadVendingMachineInventoryNoPrint(filePath)[itemToPurchaseId][3]);
							//Calculate the amount of money the user has deposited, given choice for # of currency items and currency type 
							currencyDeposited = Double.parseDouble(VendingMachineBank.loadVendingMachineBankNoPrint(filePath2)[currencyTypeInt][1])*currencyItemsToUseInt;
							
							String currencyItemName = VendingMachineBank.loadVendingMachineBankNoPrint(filePath2)[currencyTypeInt][0];
							System.out.println("The item you chose to purchase costs: $" + purchasePrice);
							System.out.println("You have put in 1 " + currencyItemName + ", which equals $" + currencyDeposited);
							
							if (currencyDeposited == purchasePrice & productQuantity >=1) //item is dispensed and subtracted from inventory; currency added to bank; no change
							{
								System.out.println("Dispensing your " + VendingMachineProduct.loadVendingMachineInventoryNoPrint(filePath)[itemToPurchaseId][0] + "; enjoy!");
								VendingMachineProduct.dispenseItemAfterDeltaNoPrint(itemToPurchaseId, filePath);	//change product inventory
								VendingMachineBank.addCurrencyAfterDeltaNoPrint(currencyTypeInt, filePath2);	// change bank currency inventory
								counter[itemToPurchaseId] ++;
								inventoryDeltaBoolArray[itemToPurchaseId]=true;
								inventoryDelta = true;
								counterBank[currencyTypeInt] ++;
								bankDeltaBoolArray[currencyTypeInt]=true;
								bankDelta = true;
								currencyDeposited = 0.00;
								enoughMoneyToBuy = true;
								break;
							}	//close if (currencyDeposited == purchasePrice)
							
							else if  (currencyDeposited > purchasePrice  & productQuantity >=1) //item is dispensed and subtracted from inventory; currency added to bank; change given & subtracted from bank
							{
			
								System.out.println("Dispensing your " + VendingMachineProduct.loadVendingMachineInventoryNoPrint(filePath)[itemToPurchaseId][0] + " now; enjoy!");
								VendingMachineProduct.dispenseItemAfterDeltaNoPrint(itemToPurchaseId, filePath);
								outputStepA = VendingMachineBank.addCurrencyAfterDeltaNoPrint(currencyTypeInt, filePath2);
								changeOwed = currencyDeposited - purchasePrice;
								//VendingMachineBank.subtractChangeFromInventoryNoPrint(outputStepA, changeOwed);
								VendingMachineBank.subtractChangeFromInventoryPrintChange(outputStepA, changeOwed);
								
								//inventoryDelta = true;
								counter[itemToPurchaseId] ++;
								inventoryDeltaBoolArray[itemToPurchaseId]=true;
								inventoryDelta = true;
								counterBank[currencyTypeInt] ++;
								bankDeltaBoolArray[currencyTypeInt]=true;
								bankDelta = true;
								currencyDeposited = 0.00;
								enoughMoneyToBuy = true;
								break;

							}	//close else if  (currencyDeposited >= purchasePrice)
							
							else if (currencyDeposited < purchasePrice & productQuantity >=1)
							{
								System.out.println("Insufficient funds. You deposited "+ currencyDeposited + " , but this item costs " + purchasePrice);
								System.out.println("Purchase failed. Returning to the main menu.");
								currencyDeposited = 0.00;
								enoughMoneyToBuy = true;
								break;
								
							} // close else if (currencyDeposited < purchasePrice & productQuantity >=1)
							
							else if (productQuantity < 1)
							{
								System.out.println("Sorry, this item is temporarily out of stock. Returning to the main menu.");
								currencyDeposited = 0.00;
								enoughMoneyToBuy = true;
								break;
								
							}	//close else if (productQuantity < 1)
						}	//close if (currencyItemsToUseInt == 1)
						
						else if (currencyItemsToUseInt > 1)
						{	
							while (i <currencyItemsToUseInt)
							{
								try
								{
									i ++;
									int j = 0;
									System.out.println("Currency item selection # " + (i)+ " of "+ currencyItemsToUseInt+ ".\n" + "Enter the number corresponding to the currency type would you like to use:");
									System.out.println();
									String currencyType = input.next().trim();
									currencyTypeInt  = Integer.parseInt(currencyType);
									curMoreThanOneTypeArray[j]= currencyTypeInt;
									
									//Get the price of the item to purchase
									purchasePrice = Double.parseDouble(VendingMachineProduct.loadVendingMachineInventoryNoPrint(filePath)[itemToPurchaseId][2]);
									productQuantity = Integer.parseInt(VendingMachineProduct.loadVendingMachineInventoryNoPrint(filePath)[itemToPurchaseId][3]);
									//Calculate the amount of money the user has deposited, given choice for # of currency items and currency type 
									currencyDeposited = (Double.parseDouble(VendingMachineBank.loadVendingMachineBankNoPrint(filePath2)[currencyTypeInt][1]));
									//Get running total of currency deposited so far 
									totalCurrencyDeposited += currencyDeposited; 

									String currencyItemName = VendingMachineBank.loadVendingMachineBankNoPrint(filePath2)[currencyTypeInt][0];
									System.out.println("The item you chose to purchase costs: $" + purchasePrice);
									System.out.println("You just put in 1 " + currencyItemName + ". You have deposited a total of $" + totalCurrencyDeposited);
									
									counterCurMoreThanOne ++;
								
									
							}	//close try
							catch (NumberFormatException e)
							{
								System.out.println("Invalid command. Please enter a number 1- " + numUniqueCurrencyItems);
							}
							catch (Exception e)
							{
								System.out.println("Invalid command. Please enter a number 1- " + numUniqueCurrencyItems);
							}


							}
							//System.out.println(totalCurrencyDeposited);	//FOR TESTING
							
							if (totalCurrencyDeposited == purchasePrice & productQuantity >=1) //item is dispensed and subtracted from inventory; currency added to bank; no change
							{
								System.out.println("Dispensing your " + VendingMachineProduct.loadVendingMachineInventoryNoPrint(filePath)[itemToPurchaseId][0] + "; enjoy!");
								VendingMachineProduct.dispenseItemAfterDeltaNoPrint(itemToPurchaseId, filePath);
								VendingMachineBank.addCurrencyAfterDeltaNoPrint(currencyTypeInt, filePath2);
																
								//inventoryDelta = true;
								counter[itemToPurchaseId] ++;
								inventoryDeltaBoolArray[itemToPurchaseId]=true;
								inventoryDelta = true;
								counterBank[currencyTypeInt] ++;
								bankDeltaBoolArray[currencyTypeInt]=true;
								bankDelta = true;
								totalCurrencyDeposited = 0.00;
								enoughMoneyToBuy = true;
								break;
							}	//close if (currencyDeposited == purchasePrice)
							
							else if  (totalCurrencyDeposited > purchasePrice  & productQuantity >=1) //item is dispensed and subtracted from inventory; currency added to bank; change given & subtracted from bank
							{
								System.out.println("Dispensing your " + VendingMachineProduct.loadVendingMachineInventoryNoPrint(filePath)[itemToPurchaseId][0] + "; enjoy!");
								VendingMachineProduct.dispenseItemAfterDeltaNoPrint(itemToPurchaseId, filePath);
								outputStepA = VendingMachineBank.addCurrencyAfterDeltaNoPrint(currencyTypeInt, filePath2);
								changeOwed = totalCurrencyDeposited - purchasePrice;
								//VendingMachineBank.subtractChangeFromInventoryNoPrint(outputStepA, changeOwed);
								VendingMachineBank.subtractChangeFromInventoryPrintChange(outputStepA, changeOwed);
	
								//Reset key variables
								counter[itemToPurchaseId] ++;
								inventoryDeltaBoolArray[itemToPurchaseId]=true;
								inventoryDelta = true;
								counterBank[currencyTypeInt] ++;
								bankDeltaBoolArray[currencyTypeInt]=true;
								bankDelta = true;
								totalCurrencyDeposited = 0.00;
								enoughMoneyToBuy = true;
								break;

							}	//close else if  (currencyDeposited >= purchasePrice)
							
							else if (totalCurrencyDeposited < purchasePrice & productQuantity >=1)
							{
								System.out.println("Insufficient funds. You deposited "+ totalCurrencyDeposited + " , but this item costs " + purchasePrice);
								System.out.println("Purchase failed. Returning to the main menu.");
								
								//Reset key variables 
								totalCurrencyDeposited = 0.00;
								enoughMoneyToBuy = true;
								break;
								
							} // close else if (currencyDeposited < purchasePrice & productQuantity >=1)
							
							else if (productQuantity < 1)
							{
								System.out.println("Sorry, this item is temporarily out of stock. Returning to the main menu.");
								
								//Reset key variables
								totalCurrencyDeposited = 0.00;
								enoughMoneyToBuy = true;
								break;
								
							}	//close else if (productQuantity < 1)
						}	//close else if (currencyItemsToUseInt > 1)
					}	//close if/else if block 

					//From here, program will go back to main menu 
					itemToPurchaseId = -1;
					command = -1;
					totalCurrencyDeposited = 0;
					choiceToBuy = false;
					currencyChoiceValid= false;
					enoughMoneyToBuy = false;
					choiceValid = false;
					break;
			}	//close switch case 3

			case 4: //Terminate the program 
				{
					System.out.println("Program will terminate; good bye!");
					chooseAgain = false;
					System.exit(0);
				}	
			}	//close switch(command)
		}	//close while (chooseAgain != false) loop 
	}	//close main

/**
*
 @method printMainMenu() : displays the vending machine simulator's main menu for the user 
 @return void
 
*/	
	
	
public static void printMainMenu()
{
	System.out.println("\n"+ "Available Commands:");
	System.out.println("-------------------");
	System.out.println("Show Commands:       0" );
	System.out.println("Display Inventory:   1" );
	System.out.println("Display Currency:    2" );
	System.out.println("Purchase Item:       3" );
	System.out.println("Exit:                4" );
	System.out.println();
	

}	//close printMainMenu method
	
}	//close VendingMachineSimulator
