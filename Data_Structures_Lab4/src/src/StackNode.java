/*
 * CLASS: StackNode (used by LinkedStack) 
 */

/*
 * The StackNode class allows us to instantiate individual elements that 
 * can be pushed onto a LinkedStack object. 
 */
public class StackNode {

	int value;	//data value of the StackNode element 
	StackNode Next;	//The next element in a LinkedStack 
	
	/**
	 * @constructor: empty constructor for a new StackNode object
	 */
	StackNode()
	{
	
	}
	
	/**
	 * @constructor: constructor with a string parameter
	 * @param newNode
	 */
	StackNode(int newNode)
	{
		value = newNode;	//an individual character element of the stack	
	}
		
}	//end class StackNode
