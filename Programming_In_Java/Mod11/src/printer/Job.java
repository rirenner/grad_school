/*
 * 
 * Module 11 Assignment- Part 2 (Printer)
 * 8/1/15
 */

/*
 * . A print queue is a list of documents, called jobs, waiting to be printed. Each job is
assigned an integer job number and an estimated print time, in seconds. If a job arrives
and the printer is currently printing another job, the job waits in the print queue. This
type of waiting line is called a first-in-first-out (FIFO) or first-come-first-serve (FCFS)
waiting line model or queue. The Java LinkedList class is a pretty good class for
implementing a waiting line model like this in code.
*
*Write a program that uses a LinkedList object to store a list of print jobs in a FIFO
queue. We will assume that each job has a unique job number that is assigned
sequentially, and that the print time is randomly distributed between 10 seconds and
1000 seconds. These two data elements should be defined as part of a Job class. The
program should prompt the user to either add a job to the queue or to quit. When a
user adds a job, the program assigns a job number and randomly generates a print time
for the job. When the user quits, the program will display the jobs (i.e., the job id and
print time) in the queue, in the order in which they were added.
For this exercise, you can use the java.util.Random class to generate random
numbers, as follows:

Random rnGenerator = new Random( rnSeed ); // creates a Random object
printTime = rnGenerator.nextInt( 1000 ) + 10; // next int in range 10-1000
*
*
*The first statement above instantiates a Random object called rnGenerator. The
constructor argument rnSeed is called a random number seed. It is of type long, and
is used to help ensure that the same sequence of random numbers is generated each 
time the program is run…which is helpful for test purposes. The nextInt() method
returns a random integer in the range 0 to 999.

*

 */


package printer;


import java.util.*;


public class Job 
{
	int jobNumber = 0;
	long rnSeed = 0;
	Random rnGenerator = new Random();
	int printTime = 0;

	
	
	/**
	 * @constructor instantiates a new Job object with no parameters
	 * @author Christine Herlihy
	 */
	Job()
	{
		
	}
	
	/**
	 * @constructor instantiates a new Job object with only the job # as the parameter
	 * @param int jobNum : the job's job number (i.e. place in the queue)
	 * @author Christine Herlihy
	 */
	Job(int jobNum)
	{
		jobNumber = jobNum;
		Random rnGenerator = new Random();
		printTime = rnGenerator.nextInt( 1000 ) + 10;

		
	}
	
	/**
	 * @constructor instantiates a new Job object with job # and printTime as parameters
	 * @param int jobNum : the job's job number (i.e. place in the queue)
	 * @param int printT: the job's randomly generated print time 
	 * @author Christine Herlihy
	 */
	Job(int jobNum, int printT)
	{
		jobNumber = jobNum;
		Random rnGenerator = new Random();
		printTime = rnGenerator.nextInt( 1000 ) + 10;

	}

	/**
	 * @method toStringClean: returns the contact info as a formatted String  
	 * @return String
	 * @author Christine Herlihy
	 */
	public String toStringClean() 
	{
		return "Job ID Number: " + jobNumber + "; Print time: " + printTime + " seconds";
	}
	
	
	
	
}	//close Job class 
