/* Christine Herlihy
 * EN 605.202 Data Structures
 * Lab #4: Sorting Algorithms Comparison
 * README file
 */
 
 The source code in this assignment can be compiled with JDK 8 from the 
 command line. This program generates test cases internally, but does 
 require three runtime parameters. 

To run this program, use the following command line prompts: 

// (1) On OSX, first, allow the JVM to use 2 GB (2048 MB) of memory, to prevent stack overflow issues for larger input arrays:

java -Xmx2048m

// (2) Then, run:

java RunProgLab4 /Users/christine/Documents/workspace2/Data_Structures_Lab4/src/ProgOutput_Lab4.txt /Users/christine/Documents/workspace2/Data_Structures_Lab4/src/ProgOutput_Lab4_STATS.txt /Users/christine/Documents/workspace2/Data_Structures_Lab4/src/ListOfInputCases_Lab4.txt /Users/christine/Documents/workspace2/Data_Structures_Lab4/src/testCases/

/****************************************************************************/

The naming convention for input files (generated internally) is as follows:

	typeSize.txt 
 
Size is an integer in the set {50, 500, 1000, 2000, 5000, 10000, 20000}

Set is defined as:
 
asc  - for ascending ordered file, 
dup  - for a randomly ordered file containing a higher percentage of duplicates, 
ran  - for a randomly ordered file containing very few, if any, duplicates,
rev  - for a reverse ordered file.




	