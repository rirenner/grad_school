/* Christine Herlihy
 * EN 605.202 Data Structures
 * Lab #1: Use of Stacks
 * CLASS: StackNode (used by LinkedStack) 
 */

/*
 * The StackNode class inherits from StackNodeElement and allows us to 
 * instantiate individual elements that can be pushed onto a LinkedStack object. 
 */
public class StackNode extends StackNodeElement
{
	
	String Element;	//an individual character element of the stack	
	double DataValue = super.DataValue;//included to demonstrate exception handling
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
	StackNode(String newNode)
	{
		Element = newNode;	//an individual character element of the stack	
	}
		
}	//end class StackNode
