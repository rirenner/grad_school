//Music track objects

public class MusicTrack 
{
	String itemCode = new String();
	String description = new String();
	String artist = new String();
	String album = new String();
	Double price = 0.00;
	String priceString = new String();
	
	
	/**
	 * @constructor instantiates a new MusicTrack object with no parameters provided 
	 * 
	 */
	MusicTrack()
	{
		
	}
	
	/**
	 * @constructor instantiates a new MusicTrack object with all parameters provided
	 * @param String itemCode : the music track's item code (example: BT012)
	 * @param String description : the title of the music track object (ex. "Yellow Submarine")
	 * @param String artist : the artist's name
	 * @param String album: the album name; "None" if the music track object is a single
	 * @param Double price : the album's price, in the format ##.## (example : 0.99)
	 * 
	 */
	MusicTrack(String itemCodeNew, String descriptionNew, String artistNew, String albumNew, Double priceNew)
	{
		itemCode = itemCodeNew;
		description = descriptionNew;
		artist = artistNew;
		album = albumNew;
		price = priceNew;
	}
	
	/**
	 * @constructor instantiates a new MusicTrack object with all parameters provided and priceNew as a STRING
	 * @param String itemCode : the music track's item code (example: BT012)
	 * @param String description : the title of the music track object (ex. "Yellow Submarine")
	 * @param String artist : the artist's name
	 * @param String album: the album name; "None" if the music track object is a single
	 * @param String price : the album's price, in the format ##.## (example : 0.99); taken in as a string due to formatting
	 * 
	 */
	MusicTrack(String itemCodeNew, String descriptionNew, String artistNew, String albumNew, String priceNew)
	{
		itemCode = itemCodeNew;
		description = descriptionNew;
		artist = artistNew;
		album = albumNew;
		priceString = priceNew;
		
		if(priceNew.trim().matches("^[0-9]*\\.?[0-9]*$"))
		{
			price = Double.parseDouble(priceNew.replace('.','.'));
		}
		else if (!priceNew.trim().matches("^[0-9]*\\.?[0-9]*$"))
		{
			price = 0.00;
		}
	}


	/**
	 * @method getDescription() returns the description of a MusicTrack object as a string
	 * @returns String
	 * 
	 */
	
	public String getDescription()
	{
		return this.description;
	}
	
	@Override
	/**
	 * @method toString() returns a formatted string containing the fields of a MusicTrack object
	 * @returns String
	 * 
	 */
	public String toString() {
		return itemCode + " " + description  + " " + artist + "  " + album + " " +  price;
	}
}	//close MusicTrack class 
