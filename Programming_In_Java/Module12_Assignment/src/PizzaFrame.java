
import javax.swing.*;

public class PizzaFrame extends JFrame
{
	/**
	 * @constructor instantiates a new PizzaFrame; displays the panel in a frame
	 * 
	 */
	public PizzaFrame()
	{
		setTitle( "Pizza Options" );
		setSize( 300, 220 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		JPanel panel = new PizzaSelector();
		this.add( panel );
	}
	
	/**
	 * @main instantiates a new Pizza frame and renders it visible to the user so he/she can make selections
	 * @return void
	 * 
	 */
	public static void main( String [] args )
	{
		JFrame frame = new PizzaFrame();
		frame.setVisible( true );
	}
}	//close PizzaFrame class
	
	
	
	

