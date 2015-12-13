/*
 * Lab 4
 * Class: QUICKSORT 
 */


/*
 * This class contains the methods needed to implement several variations
 * of the quicksort algorithm, including:
 * 		1) Iterative quicksort; first item of the partition is the pivot
 * 		2) Iterative quicksort; median-of-three as the pivot
 * 		3) Recursive quicksort down to k=50; followed by insertion sort
 * 		4) Recursive quicksort down to k=100; followed by insertion sort
 * 		
 *
 * 	In implementations (1) and (2), partitions of size 1 and 2 are treated
 * 	as special cases. In (3) and (4), partitions of size <=50 and <=100,
 * 	respectively, are treated as special cases. 
 */

public class Quicksort
{


	/**
	 * @method partition allows a specific element to find its proper position
	 * 			with respect to other elements within the sub-array
	 * <dt><b>Precondition:</b><dd>
	 * The input array contains elements
	 * @param inputArray; int lower bound; int upper bound
	 * @returns int array index of element's correct position, such that
	 * 			all elements to the left of this index value in the array
	 * 			are <= the element, and all elements to the right are >= the
	 * 			element's value
	 * <dt><b>Postconditions:</b><dd>
	 * All elements to the left of the element in question are <= the element,
	 * 	 	and all elements to the right are >= the element's value
	 * @author Christine Herlihy
	 */
	public static int partition(int inputArray[], int lb, int ub)
	{
		int down, up, a, temp;
		a = inputArray[lb];	//a is the pivot that is selected; i.e., the element whose final position we seek to find

		up = ub; //upper or right-most index value of the input array
		down = lb;	//down, or left-most index value of the input array

		while(down < up)
		{
			while( inputArray[down] <= a && down < ub)
				down ++;	//move the left pointer to the right, up the array
			while(inputArray[up] > a)
				up--; //move the right pointer to the left, down the array
			if(down < up)
			{
				//switch the left and right pointers
				temp = inputArray[down];
				inputArray[down] = inputArray[up];
				inputArray[up] = temp;
			}
		} //close while(down < up)

		inputArray[lb] = inputArray[up];
		inputArray[up]= a;
		return up;
	}	//close method partition


	/**
	 * @method swap changes the position of two elements in the input array
	 * 			This method is useful when implementing quicksort methods
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


	/**
	 * @method medianOfThree returns the median of 3 array values
	 * 			(i.e. compares array[right], array[center], and array[left]
	 * <dt><b>Precondition:</b><dd>
	 * The input array contains elements
	 * @param inputArray; int lower bound; int upper bound
	 * @returns int median value among the 3 numbers
	 * <dt><b>Postconditions:</b><dd>
	 * None
	 * @author Christine Herlihy
	 */
	 public static int medianOfThree(int[] in, int left, int right)
	 {
		    /*	left is the left-most index val of the (sub) array;
		     * 	right is the right-most index val of the (sub) array;
		     * 	To find the middle value; we sum them and divide by 2.
		     * 	Since this is integer division, we are essentially taking the
		     * 		floor of the sum/2; this value = midpoint
		     */
		 	int midpoint = (left + right) / 2;

		 	//If left > mid, swap them
		    if (in[left] > in[midpoint])
		    	swap(in, left, midpoint);

		    //Then, if left > right, swap left and right
		    if (in[left] > in[right])
			    swap(in, left, right);

		    //Finally, if the center > right, swap them
		    if (in[midpoint] > in[right])
		    	swap(in, midpoint, right);

		    //Here, put the pivot to the right
		    swap(in, midpoint, right -1);
		    return in[right - 1];
	 }	//close method medianOfThree

	 
		/**
		 * @method partitionMedian partitions an input array using the
		 * 			median value of the first, middle, and last elements of 
		 * 			the array as the pivot
		 * <dt><b>Precondition:</b><dd>
		 * The input array contains elements
		 * @param inputArray; int lower bound; int upper bound; int median
		 * 			(median is the result of the MedianOfThree method above.)
		 * @returns int array index of the left pointer element's correct 
		 * 			position
		 * <dt><b>Postconditions:</b><dd>
		 * None
		 * @author Christine Herlihy
		 */
		  public static int partitionMedian(int[] intArray, int left, int right, int pivot) {
			    int leftPointer = left;
			    int rightPointer = right - 1;

			    while (true)
			    {
			      while (intArray[++ leftPointer] < pivot)
			      {
			    	  ; //keep incrementing leftPointer
			      }
			        
			      while (intArray[--rightPointer ] > pivot)
			      {
			    	 ;  //keep decrementing rightPointer
			      }
			      
			      if ( leftPointer >= rightPointer ) //pivot was the middle of the 3 values
			      {
			    	  break;
			      }
			   
			      else
			      {
			    	  swap(intArray,  leftPointer, rightPointer ); //if input[rightPointer] > input[leftPointer], swap them
			      }
			       
			    }	//close while (true) 
			    swap(intArray,  leftPointer, right - 1);
			    return  leftPointer;
			  }


	/**
	 * @method quicksortFirst iteratively sorts an input array
	 * <dt><b>Precondition:</b><dd>
	 * The input array contains elements
	 * @param int [] inputArray
	 * @returns int []; the sorted input array
	 * <dt><b>Postconditions:</b><dd>
	 * The input array is sorted in ascending order
	 * @author Christine Herlihy
	 */
	public static int [] quicksortFirst(int inputArray[])
	{
		int i, j;
		LinkedStack bounds = new LinkedStack(); 	//use stack to implement quicksort iteratively
		int low= 0;						//original lower bound is first element in the array
		int up = inputArray.length-1;	//original upper bound is final element in the array
		bounds.push(low);	//push the lower bound onto the stack
		bounds.push(up);	//push the upper bound onto the stack

		while(bounds.isEmpty()!=true) 	//while the stack is not empty
		{
			//This happens for the last time when the stack size == 2; at this point, up == low and the while loop that follows is skipped

			up= bounds.pop();
			//System.out.println("up is " + up);	//TESTING
			low = bounds.pop();
			//System.out.println("low is " + low);	//TESTING

			while(up > low)
			{

				j = partition(inputArray, low, up);

				//If the index value of the pivot minus the lower bound > upper bound-index val. of pivot...
				if(j-low > up-j)
				{
					int up2 = j-1;	//the index to the left of the pivot is the upper bound of the new sub-array
					int low2= low;	//the original lower bound is still the lower bound of the sub-array
					bounds.push(low2);	//push sub-array's lower bound onto the stack
					bounds.push(up2);	//push sub-array's upper bound onto the stack
					low= j+1; //the other sub-array's lower bound is to the right of the pivot
				}

				else	//If the index value of the pivot minus the lower bound is <= upper bound index-val of pivot
				{
					int up3 = up;	//the original upper bound is still the upper bound of the sub-array
					int low3= j+1;	//the sub-array's lower bound is the index to the right of the pivot
					bounds.push(low3);	//push sub-array's lower bound onto the stack
					bounds.push(up3);	//push sub-array's upper bound onto the stack
					up = j-1;	//the other sub-array's upper bound is to the left of the pivot
				}
			} //close while(up > low)
		}	//close while(bounds.isEmpty()!=true)

		return inputArray;
	}	//close method quicksortFirst()


	/**
	 * @method quicksortMedianOf3 implements the quicksort algorithm to sort the
	 * 			input array; this method looks at the first, middle, and last
	 * 			values of the array, and picks the middle one of the 3 to be
	 * 			the median. This value serves as the pivot; the array is then
	 * 			partitioned in half and the sub-arrays are sorted recursively.
	 * 			Partition sizes of < 3 are treated as special cases and sorted
	 * 			with the manualSort function detailed below. 
	 * <dt><b>Precondition:</b><dd>
	 * The input array contains elements
	 * @param inputArray; int lower bound; int upper bound
	 * @returns void (in-place sort) 
	 * <dt><b>Postconditions:</b><dd>
	 * The input array is sorted in ascending order
	 * @author Christine Herlihy
	 */
	 public static void quicksortMedianOf3(int[] intArray, int left, int right)
	 {
		    int arraySize = right - left + 1;
		    
		    //Treat partition sizes < 3 as special cases 
		    //A partition of size 1 is trivially sorted
		    //A partition of size 2 is either in order or ordered via swap method
		    if (arraySize <= 2)
		    {
		      manualSort(intArray, left, right);
		    }
		    else
		    {
		    	
		    	//Find the median of the set {array[first], array[middle], array[last]}
		      int median = medianOfThree(intArray, left, right);
		      //Partition this array using the median as the pivot
		      int partition = partitionMedian(intArray, left, right, median);

		      //Sort the resulting sub-arrays recursively 
		      quicksortMedianOf3(intArray, left, partition - 1);
		      quicksortMedianOf3(intArray, partition + 1, right);
		    }
	}	//close method quicksortMedianOf3
	 
	 
	/**
	 * @method quicksortRecur50 implements the quicksort algorithm to sort the
	 * 			input array; this method treats an array size of <= 50 as a 
	 * 			special case to be sorted via insertion sort; otherwise, for
	 * 			array size > 50, the array is partitioned and the resulting
	 * 			sub-arrays are sorted via recursive function calls 
	 * <dt><b>Precondition:</b><dd>
	 * The input array contains elements
	 * @param inputArray; int lower bound; int upper bound
	 * @returns void (in-place sort) 
	 * <dt><b>Postconditions:</b><dd>
	 * The input array is sorted in ascending order
	 * @author Christine Herlihy
	 */
	 public static void quicksortRecur50(int[] input, int left, int right)
	 {
		    int arraySize = right - left + 1;
		    
		    //Treat arrays of size <=50 as special cases to sort via insertion sort 
		    if (arraySize <= 50)
		    {
		    	//System.out.println("goes here");	//TESTING
		    	insertionSort(input);
		    }
		    
		    //Otherwise, if the arraySize > 50:
		    if(left < right)
		    {
		    	//Partition the array, and sort the resulting sub-arrays
		      int partition = partition(input, left, right);

		      if(left < partition-1)
		      {
		    	  quicksortRecur50(input, left, partition - 1);
		      }

		      if(right > partition)
		      {
		    	  quicksortRecur50(input, partition+1, right);
		      }
		    }
	}	//close method quicksortRecur50
	 
	 
		/**
		 * @method quicksortRecur100 implements the quicksort algorithm to sort the
		 * 			input array; this method treats an array size of <= 100 as a 
		 * 			special case to be sorted via insertion sort; otherwise, for
		 * 			array size > 100, the array is partitioned and the resulting
		 * 			sub-arrays are sorted via recursive function calls 
		 * <dt><b>Precondition:</b><dd>
		 * The input array contains elements
		 * @param inputArray; int lower bound; int upper bound
		 * @returns void (in-place sort) 
		 * <dt><b>Postconditions:</b><dd>
		 * The input array is sorted in ascending order
		 * @author Christine Herlihy
		 */
	 public static void quicksortRecur100(int[] input, int left, int right)
	 {
		    int arraySize = right - left + 1;
		    
		  //Treat arrays of size <=100 as special cases to sort via insertion sort 
		    if (arraySize <= 100)
		    {
		    	//System.out.println("goes here");	//TESTING
		    	insertionSort(input);
		    }
		    
		  //Otherwise, if the arraySize > 100:
		    if(left < right)
		    {
		      
		     //Partition the array, and sort the resulting sub-arrays
		      int partition = partition(input, left, right);

		      if(left < partition-1)
		      {
		    	  quicksortRecur50(input, left, partition - 1);
		      }

		      if(right > partition)
		      {
		    	  quicksortRecur50(input, partition+1, right);
		      }
		    }
	}	//close method quicksortRecur50


	/**
	 * @method insertionSort
	 * <dt><b>Precondition:</b><dd>
	 * The input array contains elements
	 * @param inputArray; int lower bound; int upper bound
	 * @returns int []; the sorted input array
	 * <dt><b>Postconditions:</b><dd>
	 * The input array is sorted in ascending order
	 * @author Christine Herlihy
	 */
    public static int[] insertionSort(int[] input){

      int temp;

      //Move through the input array from left to right
        for (int x = 1; x< input.length; x++)
        {
            //Move through the array from right to left in inner loop, comparing each value to x
        	for(int y = x ; y > 0 ; y--)
            {
            	//Compare each pair of values; swap if a < b
            	if(input[y] < input[y-1])
                {
                   swap(input, y, y-1);
                }
            }
        }
        return input;	//return sorted array
    }


	/**
	 * @method manual sort puts array sizes <= 3 in ascending order
	 * <dt><b>Precondition:</b><dd>
	 * The input array contains elements
	 * @param inputArray; int lower bound; int upper bound
	 * @returns int []; the sorted input array
	 * <dt><b>Postconditions:</b><dd>
	 * The input array is sorted in ascending order
	 * @author Christine Herlihy
	 */
	public static int[] manualSort(int[] input,  int left, int right)
	{
		int arraySize = right-left + 1;

		//An array of size one is trivially sorted
		if(arraySize ==1)
		{
			return input;
		}

		//Check to see if array of size 2 is in order already; if not, swap elements
		else if(arraySize == 2 && input[left] > input[right])
		{
			swap(input, left, right);
			return input;
		}
		//If the array size is 3, (may) need to rearrange one or more elements 
		else if(arraySize == 3)
		{
			
			// Imagine [a] [b] [c]
			//If a > b; swap a and b
			if(input[left] > input[right-1])
			{
				swap(input, left, right-1);
			}

			//Then, if a > c; swap a and c
			if(input[left] > input[right])
			{
				swap(input, left, right);
			}

			//Finally, if b > c, swap b and c 
			if(input[right-1] > input[right])
			{
				swap(input, right-1, right);
			}
		}
		
		else
		{
			//values >3 are not valid 
		}
		return input;
	}


/*
	//FOR TESTING 
	public static void main(String [] args)
	{
		int [] temp = new int [] {2,7,4,3,3};
		int [] temp2 = new int [] {100,7,99,1,3,0,50, 999, 2, 0, 59, 44};
		int [] temp3 = new int[temp.length];
		int [] three = new int [] {9, 1, 7};

		int a= partition(temp, 0, temp.length-1);
		//System.out.println(a);

		int b= medianOfThree(temp, 0, temp.length-1);
		//System.out.println(b);

		three = manualSort(three, 0, 2);


		//quicksortMedianOf3(temp, 0, 4);
		for(int i =0; i<temp.length; i++)
		{
			//System.out.print(temp[i] + " ");
		}

		quicksortRecur50(temp2, 0, 9);
		for(int i =0; i<temp2.length; i++)
		{
			System.out.print(temp2[i] + " ");
		}

	}*/

}	//close class Quicksort

