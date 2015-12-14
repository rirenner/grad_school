package contacts;


import java.util.*;
public class CompareLastNames implements Comparator<String>
{

	/**
	*
	 @method compare : allows us to compare two strings to figure out which one should come first alphabetically
	 @param String a : first string to compare
	 @param String b: second string to compare
	 @return int : the int value returned is = 0 if strings are lex. equal; int > 0 if a > b; int < 0 if a < b
	
	*/
	public int compare(String a, String b) 
	{
		 int i, j, k;
		    String aStr, bStr;

		    aStr = a;
		    bStr = b;

		    //We will use this class to build a comparator that compares contact list entries based on last name
		    i = aStr.lastIndexOf(' ');	//contact list entry keys will be formatted "firstName lastName"
		    j = bStr.lastIndexOf(' ');	//contact list entry keys will be formatted "firstName lastName"

		    //Compare the first sub-string to the second sub-string 
		    
		    /*
		     * The value 0 = if the argument is a string lexicographically equal to this string; 
		     * A value less than 0 if j is a string lexicographically greater than i; 
		     * and a value greater than 0 if j is a string lexicographically less than i.
		     */
		    
		    k = aStr.substring(i).compareTo(bStr.substring(j));
		    if (k == 0) // if last names match, check entire name
		      return aStr.compareTo(bStr);
		    else
		      return k;
	}	//close compare method
}	// close CompareLastNames
