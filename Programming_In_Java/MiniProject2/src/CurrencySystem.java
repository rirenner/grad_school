
public class CurrencySystem 
{
	/**
	*
	 @method  getCurrencyTicker() : this default method will be used to get a currency object's ticker (i.e. USD; EUR)
	 @return String; should be overriden by classes that implement this interface
	 @author Christine Herlihy
	*/	
	 public String getCurrencyTicker()
	 {
		 return ("USD");
	 }

	/**
	*
	 @method default acceptsPaperCurrency() : this default method will be used to get a boolean for if a vending machine takes bills
	 @return boolean
	 @author Christine Herlihy
	*/	
	public boolean acceptsPaperCurrency()
	{
		return true;
	}
	
	/**
	*
	 @method default acceptsMetalCurrency() : this default method will be used to get a boolean for if a vending machine takes coins
	 @return boolean
	 @author Christine Herlihy
	*/	
	public boolean acceptsMetalCurrency()
	{
		return true;
	}
	
	/**
	*
	 @method default returnsPaperCurrency() : this default method will be used to get a boolean for if a vending machine returns bills
	 @return boolean
	 @author Christine Herlihy
	*/	
	public boolean returnsPaperCurrency()
	{
		return true;
	}
	
	
	/**
	*
	 @method default returnsMetalCurrency() : this default method will be used to get a boolean for if a vending machine returns coins
	 @return boolean
	 @author Christine Herlihy
	*/	
	public boolean returnsMetalCurrency()
	{
		return true;
	}
}	//close CurrencySystem class


