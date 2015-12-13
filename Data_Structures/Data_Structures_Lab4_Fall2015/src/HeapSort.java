/*
 * Lab 4
 * Class: HEAPSORT 
 */

/*
 * This class contains the methods needed to implement an iterative version of
 * a heap sort. The heap sort first builds an array-based representation
 * of a max heap, and then extracts the elements in sorted order to produce
 * a sorted version of the input array in ascending order.
 */
public class HeapSort
{
	/**
	 * @method HeapSort
	 * <dt><b>Precondition:</b><dd>
	 * The input array contains elements
	 * @param inputArray; array to be sorted
	 * @returns int []; the sorted input array
	 * <dt><b>Postconditions:</b><dd>
	 * The input array is sorted in ascending order
	 * 
	 */
	public static int[] heapSort(int[] input)
	{
		int a;
		int b;
		int c;

		if(input.length==0)
		{
			return null;	//The input array is empty
		}

		//Create the initial heap
		for(a= 1; a<input.length; a++)
		{
			int temp = input[a];

			//Insert input[a] into the heap
			c = a;
			b = (c-1)/2; // don't forget, this is integer division; b is the father of c AND c+1  (i.e., c=1; (1-1)/2 = 0; c=2; (2-1)/2 also = 0

			while(c > 0 && input[b] < temp)
			{
				//When building the max heap, each node in the heap should be <= the content of its father 
				//Thus, input[c] should be <= input[(c-1)/2] for 0<= ((c-1)/2) < c <= array length -1 
				input[c] = input[b]; 
				c = b;
				b = (c-1)/2; //Move up the tree; reset b so it can continue to represent the parent of c  
			}
			input[c] = temp;	//insert temp into the heap; values will be inserted in descending order
			//System.out.println("buildheap " + input[c]); //TESTING 
		}
		
/*		//TESTING 
		for(int k = 0; k < input.length; k++)
		{
			System.out.println(input[k]); 	//Root should appear at position input[0]
		}
*/
		/*Extract the elements in sorted order from the heap:
		 * Iterate through and delete input[0] (i.e. root of sorted heap) each time;
		 * insert it into the correct position in the sorted array, and then adjust heap
		 */
		for(a = input.length-1; a > 0; a--)
		{
			//Delete input[a] from the heap (it is the current root/ max value of the heap)
			int dummyVar = input[a];	//placeholder for the contents of input[a]
			//System.out.println("DV "  + dummyVar);	//TESTING 
			input[a] = input[0];	//root of sorted heap 
			b= 0;

			//
			if (a == 1) //last element to be removed has no children
			{
				c = -1; //prevents it from going to the while loop; the first element of the sorted array is dummyVar
			}

			else //
			{
				c = 1;	//ensures control will pass to while loop 
			}

			if (a > 2 && input[2] > input[1])
			{
				c = 2;
			}
			
			//Rearrange the sub-trees so that the node on top each time at the end is the (next) max
			while(c >= 0 && dummyVar < input[c])
			{
				input[b] = input[c];
				b = c;
				c =  2*b + 1;	//left subtree

				if(c + 1 <= a -1 && input[c] < input[c+1])
				{
					c +=1;	//Right subtree
				}
				if(c > a -1)
				{
					c = -1;
				}
			}	//close while

			 input[b] = dummyVar;
			// System.out.println("Extracting " + input[b]);
		}

		return input;
	}	//end method heapSort


	/**
	 * @method swap changes the position of two elements in the input array
	 * 			This method is useful when implementing heap sort
	 * <dt><b>Precondition:</b><dd>
	 * The input array contains elements
	 * @param inputArray; int lower bound; int upper bound
	 * @returns void
	 * <dt><b>Postconditions:</b><dd>
	 * The two elements input as parameters are swapped within the input array
	 * 
	 */
	public static void swap(int[] input, int val1, int val2)
	{
		int tempInt = input[val1];
		input[val1] = input[val2];
		input[val2] = tempInt;
	}


/*
	//FOR TESTING
	public static void main(String [] args)
	{
		int [] temp = new int [] {2,7,4,3,3};
		int [] temp2 = new int [] {100,7,99,1,3,0,50, 999, 2, 0, 59, 44};
		int [] temp3 = new int[temp.length];




		temp= heapSort(temp);
		for(int i =0; i<temp.length; i++)
		{
			System.out.print(temp[i] + " ");
		}
	}*/
}

