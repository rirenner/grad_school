/* 
 * CLASS: LinkedStack (implements MakeStack interface) 
 */

public class LinkedStack implements MakeStack
{
	
/*
 * The LinkedStack class implements the MakeStack interface to instantiate a
 * stack object without using library functions. This class implements all of 
 * the abstract methods that it inherits from the MakeStack interface, and is
 * used by the class PostfixEval. 
 */

	/*
	 * Data values:
	 */
	
	private StackNode Top;	//The top (last inserted) item of a LinkedStack
	private int size;	//How many elements a LinkedStack contains 
	
	
	/**
	 * @constructor: 
	 * <dt><b>Precondition:</b><dd>
	 * None 
	 * @param none 
	 * @returns none
	 * <dt><b>Postconditions:</b><dd>
	 * A new LinkedStack object is instantiated
	 * 
	 */
	public void LinkedStack()
	{
		Top = null;	//Initialize Top to null because new list is empty
		size = 0;	//Initialize size to 0 because new list is empty
	}
	
	

	/**
	 * @method getSize() returns the size of the current stack 
	 * <dt><b>Precondition:</b><dd>
	 * None (if the stack is empty, 0 will be returned)
	 * @param none 
	 * @returns int size (size of the current stack)
	 * <dt><b>Postconditions:</b><dd>
	 * None (stack is unchanged) 
	 * 
	 */
	public int getSize()
	{		
		return size;
	}	//end method getSize
	
	/**
	 * @method setSize() sets the size of the current stack 
	 * <dt><b>Precondition:</b><dd>
	 * None
	 * @param int : new size
	 * @returns None
	 * <dt><b>Postconditions:</b><dd>
	 * Stack size parameter is changed  (actual contents unchanged) 
	 * 
	 */	
	public void setSize(int newSize)
	{
		size = newSize;
	}
	
	
	/**
	 * @method isEmpty() returns a boolean of TRUE if the stack does not 
	 * 			contain any elements 
	 * <dt><b>Precondition:</b><dd>
	 * None
	 * @param none 
	 * @returns boolean TRUE if empty; FALSE if stack contains elements 
	 * <dt><b>Postconditions:</b><dd>
	 * None (stack is unchanged) 
	 * 
	 */
	public boolean isEmpty()
	{
		if(Top == null)
		{
			return true;	//If list IS empty, return TRUE
		}
		else
		{
			return false; //If list is NOT empty, return FALSE 
		}
	}	//end method isEmpty()
	
	/**
	 * @method peek() returns the value of the item on top of the stack 
	 * <dt><b>Precondition:</b><dd>
	 * The stack is not empty 
	 * @param none 
	 * @returns the value of the item on top of the stack 
	 * <dt><b>Postconditions:</b><dd>
	 * None (stack is unchanged) 
	 * 
	 */	
	public  int peek()
	{
		try
		{			
			if(isEmpty() == false)
			{
				return Top.value;
			}
			else
			{
				throw new StackEmptyException("The stack is empty");				
			}
		}
		
		catch(StackEmptyException e)
		{
			System.out.println(e.getMessage());
			
		}
		return -1;
	}	//end method peek()
	
	
	
	/**
	 * @method push(Object element): 
	 * <dt><b>Precondition:</b><dd>
	 * The stack must have been initialized 
	 * @param Object element: the object you want to add to the stack
	 * @returns None
	 * <dt><b>Postconditions:</b><dd>
	 * The item is added to the top of the stack; stack size increases by 1
	 * 
	 */		
	//public void push (String elementToPush)
	public void push (int elementToPush)
	{
		StackNode temp = new StackNode();
		temp.value = elementToPush;	//set the string value
		temp.Next = Top;	//attach new element to top of stack
		Top = temp;	//reset the pointer so Top = new element (LIFO) 
		size ++; 	//increment size by one

	}	//end method push (Object element)
	
	
	/**
	 * @method pop() 
	 * <dt><b>Precondition:</b><dd>
	 * The stack is not empty.
	 * @param None
	 * @returns Returns the string value of the element at the top of the 
	 * 			stack that has been removed.
	 * <dt><b>Postconditions:</b><dd>
	 * The element at the top of the stack is removed; stack size decreases
	 * 	by 1.
	 * 
	 */		
	public int pop()
	{
		int val;	//Instantiate temporary data value holder
		StackNode tempNode = Top;	//Instantiate node holder for Top
		
		try
		{			
			if(isEmpty() == false)	//make sure stack is not empty 
			{
				val = Top.value;	//get data value of Top
				Top = Top.Next;	//Reset top pointer
				tempNode.Next = null;	//top is removed, so no next
				tempNode.value = -1;	//Null value flag
				size--;
				return val;	//Return value of deleted element 				
			}
			else
			{
				throw new StackEmptyException("The stack is empty");				
			}
		}
		
		catch(StackEmptyException e)
		{
			System.out.println(e.getMessage());
			
		}
			return -1;

	}	//end method pop()
	
	
	
	/**
	 * @method stackToString() returns the stack as a string, from R to L
	 * <dt><b>Precondition:</b><dd>
	 * The stack must not be empty 
	 * @param None
	 * @returns int [] : the stack contents in an integer array
	 * <dt><b>Postconditions:</b><dd>
	 * None (stack is unchanged)
	 * 
	 */		
	public int [] stackToString()
	{
		int [] tempArray = new int [500];
		int tempSize = this.size;
		LinkedStack tempStack = new LinkedStack();
		
		for(int i = tempSize; i > 0; i--)
		{
			StackNode tempElement  = new StackNode(this.pop());	//pop each element off stack
			tempStack.push(tempElement.value);	//push onto temp stack
			tempArray[i]= tempStack.pop();	//pop temp stack and store in 
											//array
		}	
		return tempArray;
	}
	



}	//end class LinkedStack 

