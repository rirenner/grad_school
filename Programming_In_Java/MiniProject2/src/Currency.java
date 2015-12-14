
public class Currency extends CurrencySystem
{
	String name = new String();
	Double value= 0.00;
	String material = new String();
	int quantity = 0;
	double quantity2 = 0.00;
	
	
	/**
	*
	 @method Currency : Constructor to instantiate a new Currency object
	 @param String currencyName : the currency item's name (i.e. quarter, dime, etc.)
	 @param Double currencyValue : the currency item's value, in the format #.##
	 @author Christine Herlihy
	*/	
	Currency(String currencyName, Double currencyValue, int quantityOfCurrencyInMachine)
	{
		name = currencyName;
		value = currencyValue;
		quantity = quantityOfCurrencyInMachine;
	}
	
	/**
	*
	 @method Currency : Constructor to instantiate a new Currency object
	 @param String currencyName : the currency item's name (i.e. quarter, dime, etc.)
	 @param Double currencyValue : the currency item's value, in the format #.##
	 @author Christine Herlihy
	*/	
	Currency(String currencyName, Double currencyValue, double quantityOfCurrencyInMachine)
	{
		name = currencyName;
		value = currencyValue;
		quantity2 = quantityOfCurrencyInMachine;
	}
	
	//For testing
	public static void main(String [] args)
	{
	
		Currency quarter = new Currency("Quarter", 0.25, 5);
		System.out.println(quarter.toString());
	}
	
	
	
	/**
	*
	 @method getName(): returns a Currency object's name
	 @returns String : this.name
	 @author Christine Herlihy
	*/	
	public String getName()
	{
		return this.name;
	}
	
	/**
	*
	 @method getValue(): returns a Currency object's monetary value
	 @returns double : this.value
	 @author Christine Herlihy
	*/	
	public double getValue()
	{
		return this.value;
	}
	

	/**
	*
	 @method getCurrencyQuantity(): returns a Currency object's quantity within the vending machine's bank
	 @returns int: this.quantity
	 @author Christine Herlihy
	*/	
	public int getCurrencyQuantity()
	{
		return this.quantity;
	}

	/**
	*
	 @method toString(): overrides the object's toString method to provide info about currency object
	 @returns String 
	 @author Christine Herlihy
	*/	
	public String toString() 
	{
		return "Name: " + name + "; Value: " + value + "; Quantity: " + quantity;
		
	}
	
	
	
	
	
	//denomination (i.e. USD), euro, etc.
	//bool handles paper currency
	//bool handles coins 
	//provides coins paper
	//provides coins bills
	//100, 50, 20, 10, 5, 1, .25, .10, .5, .1

	
	
}	//close Currency class
