package contacts;

public class ContactInfo 
{
	String telephoneNumber = new String();
	String emailAddress = new String();

	/**
	 * @constructor instantiates a new ContactInfo object with no parameters
	 * 
	 */
	ContactInfo()
	{
		
	}
	
	/**
	 * @constructor instantiates a new ContactInfo object with 2 parameters 
	 * @param String number: the new contact's phone number, entered as a string 
	 * @param String email: the new contact's email address, entered as a string
	 * 
	 */
	ContactInfo(String number, String email)
	{
		telephoneNumber = number;
		emailAddress = email;
	}

	
	/**
	 * @method toStringClassic : returns the contact info as a formatted String w/field names
	 * @return String
	 * 
	 */
	public String toStringClassic() {
		return "ContactInfo [telephoneNumber=" + telephoneNumber
				+ ", emailAddress=" + emailAddress + "]";
	}
	
	/**
	 * @method toStringRawContent: returns the contact info as a formatted String without field names 
	 * @return String
	 * 
	 */
	public String toStringRawContent() {
		return " " + telephoneNumber + " " + emailAddress + " " ;
	}
	
	
}	//close ContactInfo class
