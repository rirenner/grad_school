
public class MetalCurrency extends Currency
{
	String material= new String();
	
	/**
	*
	 @method MetalCurrency : Constructor to instantiate a new MetalCurrency object
	 @param String currencyName : the currency item's name (i.e. One dollar bill, etc.)
	 @param Double currencyValue : the currency item's value, in the format #.##
	 @param String materialType : the currency object's material type (i.e. metal coin)
	 @author Christine Herlihy
	*/	
	public MetalCurrency(String currencyName, Double currencyValue, String materialType, int quantityOfCurrencyInMachine) 

	{
		super(currencyName, currencyValue, quantityOfCurrencyInMachine);
		material = materialType;
		
	}
	
	/**
	*
	 @method MetalCurrency : Constructor to instantiate a new MetalCurrency object 
	 @param String currencyName : the currency item's name (i.e. One dollar bill, etc.)
	 @param Double currencyValue : the currency item's value, in the format #.##
	 @param String materialType : the currency object's material type (i.e. paper bill)
	 @param double : Quantity of currency in the machine
	 @author Christine Herlihy
	*/	
		public MetalCurrency(String currencyName, Double currencyValue, String materialType, double quantityOfCurrencyInMachine) 
	{
		super(currencyName, currencyValue, quantityOfCurrencyInMachine);
		material = materialType;
	}		
		
	/**
	*
	 @method getMaterialType(): returns a MetalCurrency object's materialType
	 @returns String : this.name
	 @author Christine Herlihy
	*/	
	public String getMaterialType()
	{
		return this.material;
	}

	/**
	*
	 @method toString(): overrides the object's toString method to provide info about currency object
	 @returns String 
	 @author Christine Herlihy
	*/	
	public String toString() 

	{
		return "Currency Inventory: MetalCurrency: CurrencyUnit: Name: " + name + "; Value: " + value + "; Material: " + material + "; Quantity: " + quantity;
		
	}
	
	/**
	*
	 @method toStringShort(): overrides the object's toString method to provide info about currency object
	 @returns String 
	 @author Christine Herlihy
	*/	
	public String toStringShort() 
	{
		return name + "; Value: " + value  + "; Quantity: " + quantity;
		
	}
	
	/**
	*
	 @method toStringShort(): overrides the object's toString method to provide info about currency object
	 @returns String 
	 @author Christine Herlihy
	*/	
	public String toStringShort2() 
	{
		return name + "; Value: " + value  + "; Quantity: " + quantity2;
		
	}
}	//close MetalCurrency class
