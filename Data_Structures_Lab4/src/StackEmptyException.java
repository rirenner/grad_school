/*
 * EXCEPTION: Empty stack exception
 */

/*
 * The StackEmptyException extends the Exception class and is thrown by 
 * methods that require a non-empty stack (i.e. pop, peek)
 */

public class StackEmptyException extends Exception
{

	/**
	 * @constructor: empty constructor
	 * <dt><b>Precondition:</b><dd>
	 * The stack is empty and a method requiring a non-empty stack has been
	 * 	called
	 * @param none 
	 * @returns None
	 * <dt><b>Postconditions:</b><dd>
	 * Exception is handled by calling method
	 * @author Christine Herlihy
	 */	
	public StackEmptyException () 
	{

    }

	
	/**
	 * @constructor that takes a message as a parameter
	 * <dt><b>Precondition:</b><dd>
	 * The stack is empty and a method requiring a non-empty stack has been
	 * 	called
	 * @param String message 
	 * @returns None
	 * <dt><b>Postconditions:</b><dd>
	 * Exception is handled by calling method
	 * @author Christine Herlihy
	 */	
    public StackEmptyException (String message) {
        super (message);
    }

    
	/**
	 * @constructor that takes a cause as the parameter
	 * <dt><b>Precondition:</b><dd>
	 * The stack is empty and a method requiring a non-empty stack has been
	 * 	called
	 * @param Throwable cause
	 * @returns None
	 * <dt><b>Postconditions:</b><dd>
	 * Exception is handled by calling method
	 * @author Christine Herlihy
	 */	
    public StackEmptyException (Throwable cause) {
        super (cause);
    }

    
	/**
	 * @constructor that takes a message and a cause as parameters
	 * <dt><b>Precondition:</b><dd>
	 * The stack is empty and a method requiring a non-empty stack has been
	 * 	called
	 * @param String message; throwable cause
	 * @returns None
	 * <dt><b>Postconditions:</b><dd>
	 * Exception is handled by calling method
	 * @author Christine Herlihy
	 */	
    public StackEmptyException (String message, Throwable cause) {
        super (message, cause);
    }
}	//end class StackEmptyException extends Exception
