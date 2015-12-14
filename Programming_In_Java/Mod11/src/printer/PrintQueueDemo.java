package printer;

import java.util.*;

/*
 A print queue is a list of documents, called jobs, waiting to be printed. Each job is
assigned an integer job number and an estimated print time, in seconds. If a job arrives
and the printer is currently printing another job, the job waits in the print queue. This
type of waiting line is called a first-in-first-out (FIFO) or first-come-first-serve (FCFS)
waiting line model or queue. The Java LinkedList class is a pretty good class for
implementing a waiting line model like this in code.

*This program uses a LinkedList object to store a list of print jobs in a FIFO
queue. Assume that each job has a unique job number that is assigned
sequentially, and that the print time is randomly distributed between 10 seconds and
1000 seconds. These two data elements are defined as part of a Job class. The
program prompts the user to either add a job to the queue or to quit. When a
user adds a job, the program assigns a job number and randomly generates a print time
for the job. When the user quits, the program displays the jobs (i.e., the job id and
print time) in the queue, in the order in which they were added.
 */

public class PrintQueueDemo 
{
	
	public static void main(String[] args)
	{
		
		Scanner input = new Scanner(System.in);
		boolean chooseAgain = true;
		int switchOperator = -1;
		int counter = 0; 
		
		LinkedList<Job> printQueue = new LinkedList<Job>();
		
		
		while (chooseAgain != false)
		{
			
			printMainMenu();
			switchOperator = processUserChoice();
			
			inner: switch(switchOperator)
			{
				case 0:		// Show commands
				{
					switchOperator = -1;
					break inner;
					
				}	//close case 0
				case 1:	//add a new print job to the queue
				{
					int originalSize = printQueue.size();
					
					System.out.println("You chose: " + switchOperator);	//FOR TESTING
					
					addJobToQueue(counter, printQueue);
					counter ++;
					
					if (printQueue.size() > originalSize)
					{
						System.out.println("Job successfully added to queue");		
					}
					else
					{
						System.out.println("Could not add new job now. Try again later.");
					}
		
					switchOperator = -1;
					break;
					
				}
				case 2:	//exit and display the jobs (i.e.,job id and print time) in the queue, in the order in which they were added.
				{
					//System.out.println("You chose: " + switchOperator);	//FOR TESTING
					System.out.println("Print Queue: ");
					System.out.println("------------");
					displayPrintQueue(printQueue);
					System.out.println("Program will terminate; good bye!");
					System.exit(0);			
				}
			} 	//close inner: switch(switchOperator) 
		}	//close while (chooseAgain != false)
	}	//close main method 
	
	/**
	*
	 @method printMainMenu() : displays the print queue demo program's main menu for the user
	 @return void
	 
	*/		
	public static void printMainMenu()
	{
		System.out.println("\n"+ "Available Commands:");
		System.out.println("-------------------");
		System.out.println("Show Commands:                      0" );
		System.out.println("Add a new print job to the queue:   1" );
		System.out.println("Quit:                               2" );
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
				
				if (choice >= 0 & choice <= 2)
				{
					validSelection = true;
				}
				
				else if (choice < 0 || choice > 2)
				{
					throw new NumberFormatException();
				}
			}	//close try

			catch (NumberFormatException e)
			{
				System.out.println("Entry invalid. Please enter a valid choice between 0-2.");
			}
			catch (Exception e) 
			{
				System.out.println("Entry invalid. Please enter a valid choice between 0-2.");
			}
			
		}	//close while(validSelection != true)
		return choice;
	}	//close processUserChoice method 
	
	/**
	*
	 @method addJobToQueue : allows the user to add a job to the print queue
	 @param int counterNumber : the counter, which corresponds to the job's place in the queue
	 @returns LinkedList<Job> : returns a linked list of job objects, with new object at the end of the queue
	 
	*/		
	public static LinkedList<Job> addJobToQueue(int counterNumber, LinkedList<Job> linkedListJobObj )
	{
		Job jobToAdd = new Job(counterNumber);
		linkedListJobObj.addLast(jobToAdd);
		return linkedListJobObj;
		
	}	//close addJobToQueue(int counterNumber, LinkedList<Job> linkedListJobObj ) method 
	
	/**
	*
	 @method displayPrintQueue : prints the current queue, with jobs in order of when they were added
	 @param LinkedList<Job> : requires a LinkedList of Job objects as input
	 @returns void 
	 
	*/		
	public static void displayPrintQueue(LinkedList<Job> linkedListJobObj)
	{
		for (Job e : linkedListJobObj)
		{
			System.out.println(e.toStringClean());
		}
	}
	
}	//close PrintQueueDemo class
