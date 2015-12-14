/*
 * 
 * Module 12 Assignment
 * 8/6/15
 */

/*
 * 
During an earlier in-lecture exercise, you developed a GUI interface like the one below. This
assignment will involve modifying that application to include calculating the price of pizzas.
Assume that the price of a small pizza is $10.95, the price of a medium pizza is $13.95, and the
price of a large pizza is $16.95. The price of each topping (not including plain) is $1.00. Modify
the GUI so that it calculates and displays the total price for a pizza. You may choose how and
where the price will be displayed.
 */

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.border.Border;

public class PizzaSelector extends JPanel 
{
		private JRadioButton smallPizzaButton,
		mediumPizzaButton,
		largePizzaButton;
		private JCheckBox plainCheckBox,
		sausageCheckBox,
		mushroomCheckBox,
		pepperoniCheckBox;
		private double sumSize = 0.00;
		private double totalCost = 0.00;
		private double toppingsTotal = 0.00;
		int counter = 0;
		
		/**
		 * @constructor instantiates a new PizzaSelector panel
		 * @return void
		 * 
		 */
		public PizzaSelector()
		{
			//Create buttons
			smallPizzaButton = new JRadioButton( "Small" );
			mediumPizzaButton = new JRadioButton( "Medium" );
			largePizzaButton = new JRadioButton( "Large" );
			
			//Create a button group & add buttons
			ButtonGroup sizeGroup = new ButtonGroup();
			sizeGroup.add( smallPizzaButton );
			sizeGroup.add( mediumPizzaButton );
			sizeGroup.add( largePizzaButton );
			
			//Create a button border
			Border buttonBorder = BorderFactory.createEtchedBorder();
			buttonBorder = BorderFactory.createTitledBorder( buttonBorder,
			"Pizza Size" );
			
			//Create a button panel & add buttons
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout( new FlowLayout( FlowLayout.LEFT ) );
			buttonPanel.add( smallPizzaButton );
			buttonPanel.add( mediumPizzaButton );
			buttonPanel.add( largePizzaButton );
			buttonPanel.setBorder( buttonBorder );
			
			//Create toppings check boxes
			plainCheckBox = new JCheckBox( "Plain" );
			sausageCheckBox = new JCheckBox( "Sausage" );
			mushroomCheckBox = new JCheckBox( "Mushroom" );
			pepperoniCheckBox = new JCheckBox( "Pepperoni" );
			
			//Make an object array of the topping check boxes
			JCheckBox [] toppingsArray = new JCheckBox [] {plainCheckBox, sausageCheckBox, mushroomCheckBox, pepperoniCheckBox};
			
			for(int i=0; i<toppingsArray.length; i++)
			{
				toppingsArray[i].setSelected(false);
			}
			
			//Create a border for the toppings group
			Border toppingBorder = BorderFactory.createEtchedBorder();
			toppingBorder = BorderFactory.createTitledBorder( toppingBorder,
			"Toppings" );
			
			//Create a toppings panel & add toppings check boxes
			JPanel toppingPanel = new JPanel();
			toppingPanel.setLayout( new FlowLayout( FlowLayout.LEFT ) );
			toppingPanel.add( plainCheckBox );
			toppingPanel.add( sausageCheckBox );
			toppingPanel.add( mushroomCheckBox );
			toppingPanel.add( pepperoniCheckBox );
			toppingPanel.setBorder( toppingBorder );
			
			//Create a border for the "total" section
			Border totalBorder = BorderFactory.createEtchedBorder();
			totalBorder= BorderFactory.createTitledBorder(totalBorder, "Total price ");
			
			//Create a "total cost" panel and add "total" text box
			JPanel totalPanel = new JPanel();
			totalPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			JLabel totalLabel = new JLabel();
			JTextField totalField = new JTextField(10);
			totalField.setEditable(false);	//user should not be able to enter a value here 
			totalPanel.add(totalLabel);
			totalPanel.add(totalField);
			totalPanel.setBorder(totalBorder);
			
			//Create an inner class to listen to action events related to pizza "size" button selections
			class PizzaListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
				{
					Object source = e.getSource();
					
					if (source == smallPizzaButton)	//if user chooses small pizza
					{
						double costSmall = 10.95;
						sumSize = costSmall;
						String sumString = String.valueOf(sumSize + toppingsTotal);	//so that size can be changed even after toppings selected
						totalField.setText(sumString);
					}

					else if (source == mediumPizzaButton)	//if user chooses medium pizza
					{						
						double costMedium = 13.95;
						sumSize = costMedium;
						String sumString = String.valueOf(sumSize+ toppingsTotal);	//so that size can be changed even after toppings selected
						totalField.setText(sumString);
					}

					else if (source == largePizzaButton)	//if user chooses large pizza
					{
						double costLarge = 16.95;
						sumSize = costLarge;
						String sumString = String.valueOf(sumSize+ toppingsTotal);	//so that size can be changed even after toppings selected
						totalField.setText(sumString);					
					}
				}	//close actionPerformed method 

				}	//close PizzaListener inner class 
			
				//Create an inner class to listen to action events related to pizza "size" button selections	
				class ToppingsListener implements ItemListener
				{
					public void itemStateChanged(ItemEvent ie)
					{
						int stateChange = ie.getStateChange();
						
						if(ie.getStateChange() == ItemEvent.SELECTED)	//if a topping has been selected
						{
							if (ie.getSource() == toppingsArray[0])
							{
								//toppingsTotal should remain unchanged if plain is selected
							}
							else
							{
								toppingsTotal += 1.00;	//add $1.00 to the total for each topping selected
							}	
						}	
	
						else if(ie.getStateChange() == ItemEvent.DESELECTED)	//if a topping once selected has been deselected
						{
							if (ie.getSource() == toppingsArray[0])
							{
								//toppingsTotal should remain unchanged if plain is deselected
							}
							else
							{
								toppingsTotal --;	//subtract $1.00 from the total for each topping deselected
							}						
						}
							totalCost = sumSize + toppingsTotal;
							String sumString2 = String.valueOf(totalCost);
							totalField.setText(sumString2);
					}	//close actionPerformed method
			}	//close ToppingsListener inner class

			//Add panels to main panel
			this.setLayout( new BorderLayout() );
			this.add( buttonPanel, BorderLayout.NORTH );
			this.add( toppingPanel, BorderLayout.CENTER );
			this.add( totalPanel, BorderLayout.SOUTH);
			
			//Create a new action listener
			ActionListener sizeChoice = new PizzaListener();
			smallPizzaButton.addActionListener(sizeChoice);
			mediumPizzaButton.addActionListener(sizeChoice);
			largePizzaButton.addActionListener(sizeChoice);
			
			//Create a new item listener
			ItemListener toppingsChoice = new ToppingsListener();
			for(int i =0; i<toppingsArray.length; i ++)
			{
				toppingsArray[i].addItemListener(toppingsChoice);
			}

		}	//close PizzaSelector constructor
	}	//close PizzaSelector class 