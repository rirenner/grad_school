/* 
 * INTERFACE: MakeStack (implemented by LinkedStack) 
 */

/*
 * The MakeStack interface is used to implement a stack without using library
 * functions. This interface includes all of the methods that 
 * the stack should contain, and can be implemented using an array or a 
 * linked list. 
 */

public interface MakeStack {

	/**
	 * @method getSize() returns the size of the current stack 
	 * <dt><b>Precondition:</b><dd>
	 * None (if the stack is empty, 0 will be returned)
	 * @param none 
	 * @returns int size (size of the current stack)
	 * <dt><b>Postconditions:</b><dd>
	 * None (stack is unchanged) 
	 * @author Christine Herlihy
	 */
	public int getSize();
	
	
	/**
	 * @method isEmpty() returns a boolean of TRUE if the stack does not 
	 * 			contain any elements 
	 * <dt><b>Precondition:</b><dd>
	 * None
	 * @param none 
	 * @returns boolean TRUE if empty; FALSE if stack contains elements 
	 * <dt><b>Postconditions:</b><dd>
	 * None (stack is unchanged) 
	 * @author Christine Herlihy
	 */
	public boolean isEmpty();
	
	/**
	 * @method peek() returns the String value of the item on top of the stack 
	 * <dt><b>Precondition:</b><dd>
	 * The stack is not empty 
	 * @param none 
	 * @returns the value of the item on top of the stack 
	 * <dt><b>Postconditions:</b><dd>
	 * None (stack is unchanged) 
	 * @author Christine Herlihy
	 */	
	public int peek();
	
	
	/**
	 * @method push(Object element): 
	 * <dt><b>Precondition:</b><dd>
	 * The stack must have been initialized 
	 * @param Object element: the object you want to add to the stack
	 * @returns None
	 * <dt><b>Postconditions:</b><dd>
	 * The item is added to the top of the stack; stack size increases by 1
	 * @author Christine Herlihy
	 */		
	public void push (int newNode);
	
	
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
	 * @author Christine Herlihy
	 */		
	public int pop();

}	//end interface MakeStack 
