/*
 * 
 * Module 11 Assignment- Part 1 (Contact List)
 * 8/1/15
 */

package contacts;

/*
 * This program uses the TreeMap class to implement a contact list. 
 * Contact information includes first name, last name, a phone number and an email address. 
 * The list is stored on your computer’s file system and entries sorted alphabetically 
 * according to a person’s last name. The program prompts the user for the name of the 
 * file where the information is stored, and allow the user to add a contact from the list, 
 * delete a contact from the list, and display the entire contact list.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ContactList 
{

	/**
	 *
	  @method main : This is a main method to display the main menu and allow user to view the list and/or add/delete contacts 
	  @param String args []
	  @return void
	  @throws FileNotFoundException
	  @throws IOException 
	  @author  Christine Herlihy
	 */
	public static void main(String args[]) throws FileNotFoundException, IOException
	{

		Scanner input = new Scanner(System.in);
		boolean validFile = false; 
		boolean chooseAgain = true;
		String filePathMain = new String();
		String line =new String();
		int switchOperator = -1;


		while (validFile != true)
		{
			//Assign the string the user enters to the file path
			filePathMain = assignPath(input);

			//Initialize a TreeMap that takes a String as its key and ContactInfo objects as its values, and orders based on last names
			TreeMap<String, ContactInfo> contactsTreeMap = new TreeMap<String, ContactInfo> (new CompareLastNames());
			
			try(BufferedReader buffer = new BufferedReader(new FileReader(filePathMain)))
				{
					//Read in existing contacts and put them in the tree map
					while (( line = buffer.readLine()) != null) 
						{
				         //Split lines from text file on comma
				         String[] fields = line.split(";");
				         
				         //Create new ContactInfo objects out of elements 1 & 2 of the fields array
				         ContactInfo tempContact = new ContactInfo(fields[1], fields[2]);	//store # and email as new contact

				         contactsTreeMap.put(fields[0], tempContact);
				         
						} //close while (( line = buffer.readLine()) != null) 

						Set<Map.Entry<String, ContactInfo>> set = contactsTreeMap.entrySet();

						{
							System.out.println("\n"+ "Your contact list has been successfully imported.");
							
							while (chooseAgain!= false)
							{
								printMainMenuPlain();
								switchOperator = processUserChoice();
								
								inner: switch(switchOperator)
								{
									case 0:		// Show commands
									{
										switchOperator = -1;
										break inner;
										
									}	//close case 0
									case 1:		//Display contact list 
									{
										System.out.println("\n"+ "Contacts: " );
										System.out.println("---------" );
										
										//Print each key-value pair in the tree map 
										
										for (Map.Entry<String, ContactInfo> mapEntry : set)  
								         {
								        	 
								           System.out.print(mapEntry.getKey() + ": ");
								           System.out.println(" " + mapEntry.getValue().toStringRawContent());
								         }
										
										switchOperator = -1;
										break;
									}	//close case 1
									case 2:		//Add a new contact to the list
									{
										//Get the size of the original tree map
										int sizeOriginal = contactsTreeMap.size();
										
										//Add the new contact
										addNewContact(input, contactsTreeMap);
										
										//Check to make sure the size has increased; tell user contact has been added
										if(contactsTreeMap.size() > sizeOriginal)
										{
											System.out.println("Contact successfully added.");
										}
										else 
										{
											System.out.println("Could not add contact. Try again later.");
										}
										switchOperator = -1;
										break;
									}	//close case 2
									case 3:	//Delete a contact from the list 
									{
										//Get the size of the original tree map
										int sizeOriginal2 = contactsTreeMap.size();
										
										//Delete contact from the list 
										deleteContact(input, contactsTreeMap);
										
										//Compare to make sure size has decreased
										if(contactsTreeMap.size() < sizeOriginal2)
										{
											System.out.println("Contact successfully deleted.");
										}
										else 
										{
											System.out.println("Could not delete contact. Try again later.");
										}

										switchOperator = -1;
										break;
									}	//close case 3
									case 4:	//Exit 
									{
										System.out.println("You chose " + switchOperator);
										System.out.println("Program will terminate; good bye!");
										System.exit(0);
									}	//close case 4

								}	//close switch(switchOperator)
							}	//close while (chooseAgain != false)
						}

				validFile = true;
				
			}	//close try 
			catch (FileNotFoundException e)
			{
				System.out.println("File not found.");
			}
			catch (IOException e) 
			{
				System.out.println("An I/O Error occured.");
			}
		}	// close while (validFile != true)

	}	//close main method 
	
	/**
	*
	 @method assignPath() : Allows the user to enter the path where his/her contact list is stored
	 @return void
	 
	*/		
	public static String assignPath(Scanner scanObj) throws FileNotFoundException, IOException
	{
		String filePath = new String();
		
		System.out.println("Please enter the path to the file where your contact list is stored (ex: /Users/christine/Documents/Contacts_file ");
		System.out.println("Test: /Users/christine/Documents/workspace2/Module11_Assignment/src/contacts/Contacts_txt_file"); // FOR TESTING
		filePath = scanObj.next();
		
		return filePath; 
	}	//close readFile method 	
	

	
	/**
	*
	 @method printMainMenu() : displays the contact list program's main menu for the user
	 @return void
	 
	*/		
	public static void printMainMenu()
	{
		System.out.println("\n"+ "Your contact list has been successfully imported." + "\n");
		System.out.println("\n"+ "Available Commands:");
		System.out.println("-------------------");
		System.out.println("Show Commands:          0" );
		System.out.println("Display Contact List:   1" );
		System.out.println("Add a contact:          2" );
		System.out.println("Delete a contact        3" );
		System.out.println("Exit:                   4" );
		System.out.println();
	}	//close printMainMenu method
	
	
	/**
	*
	 @method printMainMenu() : Displays the main menu with no message about successful importation of contacts
	 @return void
	 
	*/		
	public static void printMainMenuPlain()
	{
		System.out.println("\n"+ "Available Commands:");
		System.out.println("-------------------");
		System.out.println("Show Commands:          0" );
		System.out.println("Display Contact List:   1" );
		System.out.println("Add a contact:          2" );
		System.out.println("Delete a contact        3" );
		System.out.println("Exit:                   4" );
		System.out.println();
	}	//close printMainMenu method
	
	/**
	*
	 @method processUserChoice : takes the user's choice as a string, parses for int to operate switch
	 @param String commandEntry : the user is prompted to enter his/her choice
	 @returns int choice : returns valid choices as integers
	 @throws NumberFormatException if the choice is out of range or not an integer 
	 
	*/		
	public static int processUserChoice() throws NumberFormatException
	{
		boolean validSelection = false;
		int choice = -1;
		
		while(validSelection != true)
		{
			Scanner input2 = new Scanner(System.in);
			System.out.println("Please enter a valid command: ");
			String choiceString = input2.next();
			
			try
			{
				choice = Integer.parseInt(choiceString.trim());	
				
				if (choice >= 0 & choice <= 4)
				{
					validSelection = true;
				}
				
				else if (choice < 0 || choice > 4)
				{
					throw new NumberFormatException();
				}
			}	//close try

			catch (NumberFormatException e)
			{
				System.out.println("Entry invalid. Please enter a valid choice between 0-4.");
			}
			catch (Exception e) 
			{
				System.out.println("Entry invalid. Please enter a valid choice between 0-4.");
			}
			
		}	//close while(validSelection != true)
		//System.out.println("You chose " + choice );	//FOR TESTING 
		return choice;
	}	//close processUserChoice method 
			
	
	/**
	*
	 @method addNewContact: a method to add a new contact to a tree map
	 @param Scanner : scanner object
	 @param TreeMap<String, ContactInfo> treeMapObj: a Tree Map that takes a string as a key and ContactInfo objects as its values
	 @returns void
	 @throws IOException
	 
	*/		
	public static void addNewContact(Scanner scannerObj, TreeMap<String, ContactInfo> treeMapObj) throws IOException 
	{
		String tempFirstName = new String();
		String tempLastName = new String();
		String tempPhoneNumber = new String();
		String tempEmailAddress = new String();
		String tempKey = new String();
		
		
		System.out.println("Please enter the new contact's first name: ");
		tempFirstName = scannerObj.next().trim();
		
		while(containsOnlyLetters(tempFirstName) != true)
		
		{
	        System.out.println("Entry invalid. Please enter the contact's first name.");
	        tempFirstName= scannerObj.next().trim();
		}
			
		System.out.println("Please enter the new contact's last name: ");
		tempLastName = scannerObj.next().trim();
		
		while(containsOnlyLetters(tempLastName) != true)
		{
	       System.out.println("Entry invalid. Please enter the contact's last name.");
	       tempLastName = scannerObj.next().trim();
		}

		System.out.println("Please enter the new contact's phone number in the format: ###-###-#### : ");
		tempPhoneNumber = scannerObj.next().trim();
		
		while(tempPhoneNumber.trim().length()!=12) 
		{
	        System.out.println("Entry invalid. Please enter the new contact's phone number in the format: ###-###-#### : ");
	       tempPhoneNumber = scannerObj.next().trim();
		}
		
		System.out.println("Please enter the new contact's email address in the format example@exampleemail.com : ");
		tempEmailAddress = scannerObj.next().trim();
		
		while(tempEmailAddress.contains("@")!= true) 
		{
	        System.out.println("Entry invalid. Please enter the new contact's email address in the format example@exampleemail.com : ");
	        tempEmailAddress= scannerObj.next().trim();
		}
		
		tempKey = tempFirstName + " " + tempLastName;
		ContactInfo tempContactInfoObj = new ContactInfo(tempPhoneNumber, tempEmailAddress);
		treeMapObj.put(tempKey, tempContactInfoObj);

	}	//close addNewContact(Scanner scannerObj, TreeMap<String, ContactInfo> treeMapObj) throws IOException 
	
	
	/**
	*
	 @method deleteContact : a method to delete a contact from a Tree Map
	 @param Scanner : scanner object
	 @param TreeMap<String, ContactInfo> treeMapObj: a Tree Map that takes a string as a key and ContactInfo objects as its values
	 @returns void
	 
	*/		
	public static void deleteContact(Scanner scannerObj, TreeMap<String, ContactInfo> treeMapObj)
	{
		String tempFirstNameDel = new String();
		String tempLastNameDel = new String ();
		String tempKeyDel = new String();

		System.out.println("Please enter the first name of the contact you would like to delete");
		tempFirstNameDel= scannerObj.next().trim();
		
		while(containsOnlyLetters(tempFirstNameDel) != true)
			
		{
	        System.out.println("Entry invalid. Please enter the contact's first name.");
	        tempFirstNameDel= scannerObj.next().trim();
		}

		System.out.println("Please enter the last name of the contact you would like to delete");
		tempLastNameDel = scannerObj.next().trim();
		while(containsOnlyLetters(tempLastNameDel) != true)
			
		{
	        System.out.println("Entry invalid. Please enter the contact's last name name.");
	        tempLastNameDel= scannerObj.next().trim();
		}
		
		tempKeyDel = tempFirstNameDel + " " + tempLastNameDel;
		
		if (treeMapObj.containsKey(tempKeyDel))
		{
			treeMapObj.remove(tempKeyDel);
		}
		
		else
		{
			System.out.println("The contact you wanted to delete could not be found in your list.");
		}

	}	//close public static void deleteContact(Scanner scannerObj, TreeMap<String, ContactInfo> treeMapObj)


	/**
	*
	 @method containsOnlyLetters: a method to check if a string contains only alphabetic characters
	 @param String : string to check
	 @returns boolean : returns true if only letters; false otherwise
	 
	*/		
	 public static boolean containsOnlyLetters(String str) {        
	        //First, check to make sure the string is not null or empty
	        if (str == null || str.length() == 0)
	            return false;

	        for (int i = 0; i < str.length(); i++) {

	            //If a non-alphabetic character is found, return false 
	            if (Character.isDigit(str.charAt(i)))
	                return false;
	        }

	        return true;
	    }


}	//close ContactList class
