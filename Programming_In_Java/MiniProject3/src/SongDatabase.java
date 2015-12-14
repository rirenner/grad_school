
import java.io.*;
import java.util.*;
import javafx.application.*; 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
 * Programming in Java:
 * Mini Project #3
 * 8/14/15
 */

/*
 * Upon start-up, the application will read the database file. The path of the database file will be supplied by a run-time parameter. 
 * If the database file does not exist, the user will be told the database does not exist and prompted (non-GUI text prompt) asking 
 * if they want to create a new one. If the user answers positive, the application will continue with an initially empty song database. 
 * If the user answers negatively, the application will exit. An example of an appropriate start-up command is as follows:
 *		java SongDB mySongDB.data
 */

//SongDatabase loads a song database and allows the user to view, add, edit, delete and export this database via a GUI
public class SongDatabase extends Application 
{

	//Initialize global variables 
	static Boolean runtimeParamCorrect = false;
	static Scanner input = new Scanner(System.in);
	static String newDatabaseChoice = new String();
	static String newDatabaseName = new String();
	static String directory=System.getProperty("user.dir");
	static int newDatabaseChoiceInt = 0;
	static Boolean newDatabaseCreated = false;
	static ArrayList<MusicTrack> songDatabase = new ArrayList<MusicTrack> ();
	static TreeMap<String, String> titleItemCodeTreeMap = new TreeMap<String , String> (); //Key = title; Value = item code 
	static TreeMap<String, MusicTrack> musicTracksTreeMap = new TreeMap<String, MusicTrack> (); //String = ItemCode
	static String [] songTitles = new String [200];
	Boolean newSongAdded = false;
	Boolean triedToAddNewSong = false;
	Boolean songEdited = false;
	Boolean triedToEditSong = false;
	Boolean songDeleted = false;
	Boolean cancelPressed = false;
	static int trackCounter = 0;
	static int uneditedSongIndexValue = 0;
	static MusicTrack originalSongBeforeEdit = new MusicTrack();
	static MusicTrack originalSongBeforeDelete = new MusicTrack();
	static String sourceFilePath = new String();
	static String newDatabaseFilePath = new String();
	Boolean newItemCodeCorrect = false;
	Boolean newDescriptionCorrect = false;
	Boolean newArtistCorrect = false;
	Boolean newAlbumCorrect = false;
	Boolean newPriceCorrect = false;
	Boolean editedDescriptionCorrect = false;
	Boolean editedArtistCorrect = false;
	Boolean editedAlbumCorrect = false;
	Boolean editedPriceCorrect = false;
	public static final Color DARKTURQUOISE = Color.web("#00CED1");
    private final String LABEL_STYLE = "-fx-text-fill: black; -fx-font-size: 12;";
    static int buttonPressed = -1;
	
	/**
	 * @method main reads 
	 * @param String [] args : 1 runtime parameter is required; should be the file path of the existing MusicTrack database (; delimited)
	 * @returns void
	 * 
	 */
	public static void main(String[] args) throws IOException, FileNotFoundException
	{

		//This program has one runtime parameter: the data source file path 
		Boolean runtimeParamCorrect = false;
		String directory=System.getProperty("user.dir");

		
		//Check to make sure user has correctly entered the required runtime parameter
		while (runtimeParamCorrect != true)
		{
			if (args != null && args.length == 1)
			{
			
			//FOR TESTING:
			//String sourceFilePath = "/Users/christine/Documents/workspace2/MiniProject3/src/MusicTracks_TXT";	//FOR TESTING
			//System.out.println("enter path: /Users/christine/Documents/workspace2/MiniProject3/src/MusicTracks_TXT");	//FOR TESTING
			//sourceFilePath = input.next();	//FOR TESTING
		
			runtimeParamCorrect = true;
			

			//Runtime parameters:
			String sourceFilePath = args[0];	//make active once testing is done 
			
			//Initialize variables
			String line = new String();
			
			
			//This will import every track from the original music track file  
			try(BufferedReader buffer = new BufferedReader(new FileReader(sourceFilePath)))
			{
				while (( line = buffer.readLine()) != null) 
				{
			         //Split lines from text file on comma
			        String [] fields = line.split(";");
			         
			         //Instantiate a new MusicTrack object for each line in the music track file
			         MusicTrack newTrackTemp = new MusicTrack(fields[0], fields[1], fields[2], fields[3], fields[4]);
			         
			         
			         //Add each new MusicTrack object to the songDatabase ArrayList 
			        songDatabase.add(newTrackTemp);
			      	titleItemCodeTreeMap.put(newTrackTemp.description, newTrackTemp.itemCode);
			        musicTracksTreeMap.put(newTrackTemp.itemCode, newTrackTemp); //with ItemCode as key
			         
				}	//close while
				
				//FOR TESTING: Print each MusicTrack object in the songDatabase:
				for(int i = 0; i<songDatabase.size(); i++)
				{
					songTitles[i]= songDatabase.get(i).getDescription();
					//System.out.println(songDatabase.get(i).toString());	//FOR TESTING
					trackCounter++;
				}
				
				buttonPressed = 0;
				System.out.println("Launching application window...");
				//Launch JavaFX window for user to view/edit database 
				launch();
				
			}	//close try(BufferedReader buffer = new BufferedReader(new FileReader(sourceFilePath)))
			
			
/*				 * If the database file does not exist, the user will be told the database does not exist and prompted (non-GUI text prompt) asking 
				 * if they want to create a new one. If the user answers positive, the application will continue with an initially empty song database. 
				 * If the user answers negatively, the application will exit.*/
			
			//If the database file does not exist; ask user if they want to create a new one. If yes, continue w/empty DB; if no, exit
			catch (FileNotFoundException e)
			{
				System.out.println("Database file not found.");

				int switchOperator = processUserChoice();
				
				switch(switchOperator)
				{
				
					case 1:	//create a new song database with the file path name supplied by the user
					{
						System.out.println("You chose " + switchOperator);	//FOR TESTING
						//newDatabaseFilePath = directory+File.separator+newFileName;
						newDatabaseFilePath = sourceFilePath;
						//Stream output array to destination file and format for printing
						try (PrintStream output = new PrintStream(new File(newDatabaseFilePath)))
						{
							
						//FOR TESTING: Print each MusicTrack object in the songDatabase:
						if(songDatabase.size() > 0)
						{
							for(int i = 0; i<songDatabase.size(); i++)
							{
								//System.out.println(songDatabase.get(i).toString());
								trackCounter++;
							}	
						}	
						
						else if (songDatabase.size() <= 0)
						{
							MusicTrack dummyMusicTrack = new MusicTrack("Null", "Null", "Null", "Null", "Null");
							songDatabase.add(dummyMusicTrack);

					      	titleItemCodeTreeMap.put(dummyMusicTrack.description, dummyMusicTrack.itemCode);
					        musicTracksTreeMap.put(dummyMusicTrack.itemCode, dummyMusicTrack); //with ItemCode as key
							System.out.println("Database successfully created; database currently contains one null track");
							
							for(int i = 0; i<songDatabase.size(); i++)
							{
								songTitles[i]= songDatabase.get(i).getDescription();
								//System.out.println(songDatabase.get(i).toString());	//FOR TESTING
								trackCounter++;
							}
							
						}

						newDatabaseCreated= true;
						buttonPressed = 1;
			            System.out.println("Launching application window...");
			            launch();			            
			            output.close();
			            
						}	//close try (PrintStream output = new PrintStream(new File(newDatabaseFilePath)))
						catch (FileNotFoundException e2) 
						{
							System.out.println("File path not found.");
						}
						break;
					}	//close case1
						
					case 2:	//if user enters 2, the application will exit 
					{
						System.out.println("You chose " + switchOperator);
						System.out.println("Program will terminate; good bye!");
						System.exit(0);
					}	//close case2 
				}	//close switch(switchOperator)
			}	//close catch (FileNotFoundException e)
			catch (IOException e) 
			{
				System.out.println("An I/O Error occured.");
			}

		}	//close if (args != null && args.length == 1)
			//If runtime parameters are entered incorrectly, give error message and break so user can re-enter from terminal 
			else if (args == null || args.length < 1 ||args.length > 1)
			{
				{
					System.out.println("\n"+ "1 runtime parameter is required." + "\n" + "To run this program, save the database text file in the current directory, and then use the following command line prompt: " + "\n" + "java SongDatabase MusicTracks_TXT");
					break;
				}	
			}	//close else if
		}	//close while loop 
	}	//close main
	
	/**
	*
	 @method processUserChoice : takes the user's choice as a string, parses for int to operate switch
	 @param String commandEntry : the user is prompted to enter his/her choice
	 @returns int choice : returns valid choices as integers
	 @throws NumberFormatException if the choice is out of range or not an integer 
	 
	*/		
	public static int processUserChoice() throws NumberFormatException
	{
		boolean validSelection = false;
		int choice = -1;
		
		while(validSelection != true)
		{
			Scanner input2 = new Scanner(System.in);
			System.out.println("Would you like to create this song database? Enter 1 for YES and 2 for NO: "); 
			String choiceString = input2.next();
			
			try
			{
				choice = Integer.parseInt(choiceString.trim());	
				
				if (choice >= 1 & choice <= 2)
				{
					validSelection = true;
				}
				
				else if (choice < 1 || choice > 2)
				{
					throw new NumberFormatException();
				}
			}	//close try
			catch (NumberFormatException e)
			{
				System.out.println("Entry invalid. Please enter a valid choice between 1-2.");
			}
			catch (Exception e) 
			{
				System.out.println("Entry invalid. Please enter a valid choice between 1-2.");
			}			
		}	//close while(validSelection != true)		
		return choice;
	}	//close processUserChoice method 
	
	
	
	/*	The original state of the frame shall display the combo box with the first song in the database selected. 
		The Item Code, Description, Artist, Album, and Price fields for this item shall be displayed with the fields 
		disabled (non-editable). The Add, Edit, Delete, and Exit buttons shall be enabled, and the Accept and Cancel buttons 
		shall be disabled.*/

	/**
	 * @method start : displays the SongDatabase GUI and allows the user to view, edit, add, and delete songs 
	 * @param Stage myStage 
	 * @returns void
	 * 
	 */
	public void start(Stage myStage) throws Exception
	{
		myStage.setTitle( "Song Database" ); 
		myStage.setResizable(false);
		GridPane primaryGridPane = createGridPane();
		Scene myScene = new Scene(primaryGridPane, 1000, 300, DARKTURQUOISE);
		
        
        //Create a label, text field, and error text field for each MusicTrack attribute
        Label itemCodeLabel = new Label("Item Code:");
        itemCodeLabel.setStyle(LABEL_STYLE);
        GridPane.setHalignment(itemCodeLabel, HPos.RIGHT);
        TextField itemCodeTextField = new TextField();
        TextField itemCodeErrorMessage = new TextField();
        itemCodeErrorMessage.setPrefColumnCount(40);
        GridPane.setColumnSpan(itemCodeErrorMessage, 3);
        
        Label descriptionLabel = new Label("Description:");
        descriptionLabel.setStyle(LABEL_STYLE);
        GridPane.setHalignment(descriptionLabel, HPos.RIGHT);
        TextField descriptionTextField = new TextField();
        TextField descriptionErrorMessage = new TextField();
        descriptionErrorMessage.setPrefColumnCount(40);
        GridPane.setColumnSpan(descriptionErrorMessage, 3);
		
        Label artistLabel = new Label("Artist:");
        artistLabel.setStyle(LABEL_STYLE);
        GridPane.setHalignment(artistLabel, HPos.RIGHT);
        TextField artistTextField = new TextField();
        TextField artistErrorMessage = new TextField();
        artistErrorMessage.setPrefColumnCount(40);
        GridPane.setColumnSpan(artistErrorMessage, 3);
		
        Label albumLabel = new Label("Album:");
        albumLabel.setStyle(LABEL_STYLE);
        GridPane.setHalignment(albumLabel, HPos.RIGHT);
        TextField albumTextField = new TextField();
        TextField albumErrorMessage = new TextField();
        albumErrorMessage.setPrefColumnCount(40);
        GridPane.setColumnSpan(albumErrorMessage, 3);
		
        Label priceLabel = new Label("Price:");
        priceLabel.setStyle(LABEL_STYLE);
        GridPane.setHalignment(priceLabel, HPos.RIGHT);
        TextField priceTextField = new TextField();
        TextField priceErrorMessage = new TextField();
        priceErrorMessage.setPrefColumnCount(50);
        GridPane.setColumnSpan(priceErrorMessage, 3);
        
        ArrayList<TextField> attributeTextFields = new ArrayList<TextField> ();
        attributeTextFields.add(itemCodeTextField);
        attributeTextFields.add(descriptionTextField);
        attributeTextFields.add(artistTextField);
        attributeTextFields.add(albumTextField);
        attributeTextFields.add(priceTextField);
        
        ArrayList<TextField> errorMsgTxtFields = new ArrayList<TextField> ();
        errorMsgTxtFields.add(itemCodeErrorMessage);
        errorMsgTxtFields.add(descriptionErrorMessage);
        errorMsgTxtFields.add(artistErrorMessage);
        errorMsgTxtFields.add(albumErrorMessage);
        errorMsgTxtFields.add(priceErrorMessage);
        
        //Set error messages to be blank        
		itemCodeErrorMessage.setText("");
		descriptionErrorMessage.setText("");
		artistErrorMessage.setText("");
		albumErrorMessage.setText("");
		priceErrorMessage.setText("");
		
		//Disable error message fields
		itemCodeErrorMessage.setEditable(false);
		descriptionErrorMessage.setEditable(false);
		artistErrorMessage.setEditable(false);
		albumErrorMessage.setEditable(false);
		priceErrorMessage.setEditable(false);
                
		//Instantiate action buttons
		Button add = new Button("Add");
		Button edit = new Button("Edit");
		Button delete = new Button("Delete");
		Button accept = new Button("Accept");
		Button cancel = new Button("Cancel");
		Button exit = new Button("Exit");
		
		ArrayList<Button> buttonList= new ArrayList<Button> ();
		buttonList.add(add);
		buttonList.add(edit);
		buttonList.add(delete);
		buttonList.add(accept);
		buttonList.add(cancel);
		buttonList.add(exit);
		

		//Enable all buttons for the user's initial selection
		for(Button b : buttonList)
		{
			b.setDisable(false);
		}
		
		//Put the buttons in an Hbox
		HBox buttonHBox = new HBox(15);
		buttonHBox.getChildren().addAll(buttonList);
		GridPane.setColumnSpan(buttonHBox, 5);
		GridPane.setHalignment(buttonHBox, HPos.CENTER);
		GridPane.setValignment(buttonHBox, VPos.BOTTOM);
		
		//Create the song selection drop-down menu: 
		Label songChoiceLabel = new Label ("Select song: ");
		ObservableList<String> choices = FXCollections.observableArrayList(songTitles);	//songTitles is a String []
		ComboBox<String> songChoice2= new ComboBox<String>(choices);
		
	
		//This section controls what will happen if the drop down menu selection (songChoice2) is changed 
		//as a result of a button-related action event (or, a series of button-related action events)
		songChoice2.setOnAction(new EventHandler<ActionEvent> ()	//activated if the song selection is changed
		{
			public void handle(ActionEvent ae)
			{
				if (songChoice2.getValue() != "" & newSongAdded != true & songEdited != true & songDeleted != true & cancelPressed != true) //Default; songChoice2 = key to query TreeMap for rel. attributes
				{
				
				songChoice2.setDisable(false);
				songChoice2.setValue(songChoice2.getSelectionModel().getSelectedItem());			
				
				String selectedMusicTrack = songChoice2.getSelectionModel().getSelectedItem();
				String matchSongChoiceToItemCode = titleItemCodeTreeMap.get(selectedMusicTrack);
				
				//WITH ITEM CODE AS KEY
				itemCodeTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCode).itemCode);
				descriptionTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCode).description);
				artistTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCode).artist);
				albumTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCode).album);
				priceTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCode).priceString);
							
				for(Button b : buttonList)
				{
					b.setDisable(false);
				}
				
				itemCodeTextField.setEditable(false);
				descriptionTextField.setEditable(false);
				artistTextField.setEditable(false);
				albumTextField.setEditable(false);
				priceTextField.setEditable(false);
				
				//Make error message fields blank
				itemCodeErrorMessage.setText("");
				descriptionErrorMessage.setText("");
				artistErrorMessage.setText("");
				albumErrorMessage.setText("");
				priceErrorMessage.setText("");
				
				//Disable error message fields
				itemCodeErrorMessage.setEditable(false);
				descriptionErrorMessage.setEditable(false);
				artistErrorMessage.setEditable(false);
				albumErrorMessage.setEditable(false);
				priceErrorMessage.setEditable(false);
				
				}
				
				else if (songChoice2.getValue() == "" & newSongAdded != true & songEdited != true & cancelPressed != true)	//To have the drop down bar show up blank while adding NEW
				{
					songChoice2.setValue("");
				}
				else if(newSongAdded == true & descriptionTextField.getText() != null & songEdited != true & cancelPressed != true)	//After new song added; make songChoice2= user input 
				{
					
					//BOTH ARE NEW 
					String selectedMusicTrackB = songChoice2.getSelectionModel().getSelectedItem();
					String matchSongChoiceToItemCodeB = titleItemCodeTreeMap.get(selectedMusicTrackB);
										
					//WITH ITEM CODE AS KEY!
					itemCodeTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeB).itemCode);
					descriptionTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeB).description);
					artistTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeB).artist);
					albumTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeB).album);
					priceTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeB).priceString);
					
					//Disable all text fields once the new entry is displayed
					itemCodeTextField.setEditable(false);
					descriptionTextField.setEditable(false);
					artistTextField.setEditable(false);
					albumTextField.setEditable(false);
					priceTextField.setEditable(false);
					
					//Enable all option buttons 
					for(Button b : buttonList)
					{
						b.setDisable(false);
					}					
					songChoice2.setDisable(false);	
					triedToAddNewSong = false;
					newSongAdded = false;	//reset
					
				}
				else if (songEdited == true & descriptionTextField.getText() != null & cancelPressed != true)
				{
					
					System.out.println("songEdited == true & descriptionTextField.getText() != null"); //For testing
					
					//BOTH ARE NEW 
					String selectedMusicTrackC = songChoice2.getSelectionModel().getSelectedItem();
					String matchSongChoiceToItemCodeC = titleItemCodeTreeMap.get(selectedMusicTrackC);
					
					//ITEM CODE AS KEY
					itemCodeTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeC).itemCode);
					descriptionTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeC).description);
					artistTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeC).artist);
					albumTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeC).album);
					priceTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeC).priceString);
					
					//Disable all text fields once the new entry is displayed
					itemCodeTextField.setEditable(false);
					descriptionTextField.setEditable(false);
					artistTextField.setEditable(false);
					albumTextField.setEditable(false);
					priceTextField.setEditable(false);
					
					//Enable all option buttons 
					for(Button b : buttonList)
					{
						b.setDisable(false);
					}					
					
					songChoice2.setDisable(false);	
					triedToEditSong = false;
					songEdited = false;	//reset
				}
				
				else if (songDeleted == true & descriptionTextField.getText() != null & cancelPressed != true)
				{
					System.out.println("songDeleted == true & descriptionTextField.getText() != null"); //For testing
										
					//BOTH ARE NEW 
					String selectedMusicTrackD = songChoice2.getSelectionModel().getSelectedItem();
					String matchSongChoiceToItemCodeD = titleItemCodeTreeMap.get(selectedMusicTrackD);
					
					//ITEM CODE AS KEY
					itemCodeTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeD).itemCode);
					descriptionTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeD).description);
					artistTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeD).artist);
					albumTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeD).album);
					priceTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeD).priceString);
					
					//Disable all text fields once the new entry is displayed
					itemCodeTextField.setEditable(false);
					descriptionTextField.setEditable(false);
					artistTextField.setEditable(false);
					albumTextField.setEditable(false);
					priceTextField.setEditable(false);
					
					//Enable all option buttons 
					for(Button b : buttonList)
					{
						b.setDisable(false);
					}					
					
					songChoice2.setDisable(false);					
					songDeleted = false;	//reset
				}
				else if (cancelPressed == true)
				{
						
					System.out.println("cancel pressed == true & descriptionTextField.getText() != null"); //For testing
					
					//BOTH ARE NEW 
					String selectedMusicTrackE = songChoice2.getSelectionModel().getSelectedItem();
					String matchSongChoiceToItemCodeE = titleItemCodeTreeMap.get(selectedMusicTrackE);
					
					//ITEM CODE AS KEY
					itemCodeTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeE).itemCode);
					descriptionTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeE).description);
					artistTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeE).artist);
					albumTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeE).album);
					priceTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeE).priceString);
					
					//Disable all text fields once the new entry is displayed
					itemCodeTextField.setEditable(false);
					descriptionTextField.setEditable(false);
					artistTextField.setEditable(false);
					albumTextField.setEditable(false);
					priceTextField.setEditable(false);
					
					//Enable all option buttons 
					for(Button b : buttonList)
					{
						b.setDisable(false);
					}					
					
					songChoice2.setDisable(false);			
					cancelPressed = false;	//reset
				}
			}
		});	//close handle ActionEvent

		
		//The addButtonHandler class handles action events that occur to the "Add" button 
		class addButtonHandler implements EventHandler<ActionEvent> 
		{
			public void handle( ActionEvent addButtonEvent )
			{
				
				
				songChoice2.setValue("");
				songChoice2.setDisable(true); 	//will be filled from the description 
				
				//Make all text fields appear blank 
				itemCodeTextField.setText("");
				descriptionTextField.setText("");
				artistTextField.setText("");
				albumTextField.setText("");
				priceTextField.setText("");
				
				//Allow user to edit fields as warranted
				itemCodeTextField.setEditable(true);
				descriptionTextField.setEditable(true);
				artistTextField.setEditable(true);
				albumTextField.setEditable(true);
				priceTextField.setEditable(true);
				
				//Make error message fields blank
				itemCodeErrorMessage.setText("");
				descriptionErrorMessage.setText("");
				artistErrorMessage.setText("");
				albumErrorMessage.setText("");
				priceErrorMessage.setText("");
				
				//Disable error message fields
				itemCodeErrorMessage.setEditable(false);
				descriptionErrorMessage.setEditable(false);
				artistErrorMessage.setEditable(false);
				albumErrorMessage.setEditable(false);
				priceErrorMessage.setEditable(false);
										
				//Disable edit and delete buttons
				add.setDisable(true);	//can't add a new song while adding current new song 
				edit.setDisable(true);
				delete.setDisable(true);
				triedToAddNewSong = true;
				//newSongAdded = true;
				
				if(newDatabaseCreated = true)
				{
					accept.setDisable(false);
					cancel.setDisable(false);
				}

				//At this point, the user should accept or cancel, triggering a NEW action event 
			}	//close public void handle( ActionEvent addButtonEvent )
		}	//close addButtonHandler
		
		//The editButtonHandler class handles action events that occur to the "Edit" button 
		class editButtonHandler implements EventHandler<ActionEvent> 
		{
			public void handle( ActionEvent editButtonEvent )
			{
				
				songChoice2.setDisable(true); 
				
				String originalSongItemCode = titleItemCodeTreeMap.get(descriptionTextField.getText().toString());	//NEW
				
				////Get the MusicTrack object associated with the original (i.e. unedited) track
				originalSongBeforeEdit = musicTracksTreeMap.get(originalSongItemCode); //ITEM CODE AS KEY
						
				//Enable the Description, Artist, Album, and Price fields (the user may not change the Item Code)				
				itemCodeTextField.setEditable(false); 	//can't be edited
				descriptionTextField.setEditable(true);
				artistTextField.setEditable(true);
				albumTextField.setEditable(true);
				priceTextField.setEditable(true);
				
				//Disable the add, edit, delete, and exit buttons; enable accept and cancel
				add.setDisable(true); 
				edit.setDisable(true);
				delete.setDisable(true);
				accept.setDisable(false);
				cancel.setDisable(false);
				exit.setDisable(true);
				
				triedToEditSong = true;
				
				//At this point, the user should choose to accept or cancel changes, which triggers a NEW action event 
			}	//close handle
		}	//close editButtonHandler
		
		//The deleteButtonHandler class handles action events that occur to the "Delete" button 
		class deleteButtonHandler implements EventHandler<ActionEvent> 
		{
			public void handle( ActionEvent deleteButtonEvent )
			{
				
				String originalSongBeforeDeleteItemCode = titleItemCodeTreeMap.get(descriptionTextField.getText().toString());					
				originalSongBeforeDelete = musicTracksTreeMap.get(originalSongBeforeDeleteItemCode); //ITEM CODE AS KEY
				
				int originalIndexPositionValue = 0;
				
				for(int i =0; i<songTitles.length; i++)
				{
					if(songTitles[i]== originalSongBeforeDelete.description)
					{
						originalIndexPositionValue += i;
					}
				}
				
				//System.out.println("original index position is: " + originalIndexPositionValue);	//FOR TESTING 
				songTitles[originalIndexPositionValue] = "";
				
				//System.out.println("SONG DELETE A"); //FOR TESTING 				
				
				String originalSongKey = titleItemCodeTreeMap.get(originalSongBeforeDelete.description);
				
				//System.out.println("original song key is: " + originalSongKey);	//FOR TESTING
								
				titleItemCodeTreeMap.remove(originalSongBeforeDelete.description, originalSongKey);
				
				//System.out.println(titleItemCodeTreeMap.entrySet());	//FOR TESTING; prints in ascending key order
				
				songDatabase.remove(originalIndexPositionValue);
				
				//songDatabase.listIterator().toString();	//FOR TESTING 
				
				if(musicTracksTreeMap.containsKey(originalSongKey))
				{
					//System.out.println(musicTracksTreeMap.entrySet());	//FOR TESTING 					
					//System.out.println("BEFORE" + choices.toString());	//FOR TESTING 
					musicTracksTreeMap.remove(originalSongKey, originalSongBeforeDelete); 
					choices.remove(originalSongBeforeDelete.description);				
					//System.out.println("AFTER REMOVE:" + choices.toString());	//FOR TESTING 
				}
				else if (!musicTracksTreeMap.containsKey(originalSongKey))
				{
					
				}
		        
		        songChoice2.getSelectionModel().selectFirst();
		        String selectedSong = songChoice2.getSelectionModel().getSelectedItem().toString();
		        songChoice2.getSelectionModel().select(selectedSong);
		        songDeleted = true;

			}	//close handle 
		}	//close deleteButtonHandler
		
		//The acceptButtonHandler class handles action events that occur to the "Accept" button 
		class acceptButtonHandler implements EventHandler<ActionEvent> 
		{
			public void handle( ActionEvent acceptButtonEvent )
			{
				
				if (triedToAddNewSong == true)
				{

					//Validate itemCodeTextField input 
					if(itemCodeTextField.getText().length() > 0 &  !itemCodeTextField.getText().toString().trim().isEmpty())
					{
						//System.out.println("Goes here A"); 	//FOR TESTING 
						itemCodeErrorMessage.setText("");
						itemCodeTextField.setText(itemCodeTextField.getText());
						itemCodeTextField.setEditable(false);
						newItemCodeCorrect = true;
					}
					
					else if (itemCodeTextField.getText().length() == 0 | itemCodeTextField.getText().toString().trim().isEmpty())
					{
						newItemCodeCorrect = false;
					}			

					//Valid descriptionTextField input
					if(descriptionTextField.getText().length() > 0 & !descriptionTextField.getText().toString().trim().isEmpty())
					{
						descriptionErrorMessage.setText("");
						descriptionTextField.setText(descriptionTextField.getText());	//this value also passed to songChoice2
						descriptionTextField.setEditable(false);
						newDescriptionCorrect = true;							
					}
					
					else if (descriptionTextField.getText().length() == 0 | descriptionTextField.getText().toString().trim().isEmpty())
					{
						newDescriptionCorrect = false;
					}
					
					//Validate artistTextField input 
					if(artistTextField.getText().length()  > 0 & !artistTextField.getText().toString().trim().isEmpty())
					{
						artistErrorMessage.setText("");
						artistTextField.setText(artistTextField.getText());	//this value also passed to songChoice2
						artistTextField.setEditable(false);
						newArtistCorrect = true; 
					}
					
					else if (artistTextField.getText().length() == 0 | artistTextField.getText().toString().trim().isEmpty())
					{
						newArtistCorrect = false;
					}
										
					//Validate albumTextField input 
					if(albumTextField.getText().length()  > 0 & !albumTextField.getText().toString().trim().isEmpty())
					{
						albumErrorMessage.setText("");
						albumTextField.setText(albumTextField.getText());	//this value also passed to songChoice2
						albumTextField.setEditable(false);
						newAlbumCorrect = true; 							
					}
					
					else if (albumTextField.getText().length() == 0 | albumTextField.getText().toString().trim().isEmpty())
					{		
						albumErrorMessage.setVisible(true);
						albumErrorMessage.setEditable(false);
						albumErrorMessage.setText("'None' will be entered; this song is a single.");
						albumTextField.setText("None");	//indicate that the song is a single 
						newAlbumCorrect = true;
					}
					
					//Validate priceTextField input 
					if( priceTextField.getText().length() > 0 & !priceTextField.getText().toString().trim().isEmpty() & priceTextField.getText().matches("^[0-9]*\\.?[0-9]*$"))
					{
						priceErrorMessage.setText("");
						priceTextField.setText(priceTextField.getText());	//this value also passed to songChoice2
						priceTextField.setEditable(false);
						newPriceCorrect = true;					
					}
					
					else if ( priceTextField.getText().length() == 0 | priceTextField.getText().toString().trim().isEmpty() | !priceTextField.getText().matches("^[0-9]*\\.?[0-9]*$"))
					{
						newPriceCorrect = false;
					}
										
					if(newItemCodeCorrect == true & newDescriptionCorrect == true & newArtistCorrect == true & newAlbumCorrect == true & newPriceCorrect == true)
					{	
					//Add the new song to the drop down menu and database; display refreshed info 
					String newSongChoice = descriptionTextField.getText().toString();
					choices.add(trackCounter, newSongChoice);	//Add the new song to the next empty space in the track drop-down list 
					
					//Create a new MusicTrack object from the validated, user-entered text fields
					String tempItemCodeNew = new String(itemCodeTextField.getText().toString());
					String tempDescriptionNew = new String(newSongChoice.toString());
					String tempArtistNew = new String(artistTextField.getText().toString());
					String tempAlbumNew = new String(albumTextField.getText().toString());
					String tempPriceNew = new String(priceTextField.getText().toString());
					
					MusicTrack newTrackTemp2 = new MusicTrack(tempItemCodeNew, tempDescriptionNew, tempArtistNew, tempAlbumNew, tempPriceNew);
			         
			         //Add thisnew MusicTrack object to the songDatabase ArrayList and to the tree Map so the title can be used as a key
			        songDatabase.add(trackCounter, newTrackTemp2);
			        titleItemCodeTreeMap.put(newTrackTemp2.description, newTrackTemp2.itemCode); 
			        musicTracksTreeMap.put(newTrackTemp2.itemCode, newTrackTemp2); //ITEM CODE AS KEY                
			        newSongAdded = true;
					songChoice2.setValue(descriptionTextField.getText());	//make it so that the new song = selected by default 
					songChoice2.getSelectionModel().select(newSongChoice.toString());//this will trigger a songChoice2 Action Event 

				}	//close if loop 
					
					else if (newItemCodeCorrect != true | newDescriptionCorrect != true | newArtistCorrect != true | newAlbumCorrect != true | newPriceCorrect != true)
					{
						if(newItemCodeCorrect != true)
						{
							
							itemCodeErrorMessage.setVisible(true);
							itemCodeErrorMessage.setEditable(false);
							itemCodeErrorMessage.setText( "Please enter a value; Item Code cannot be blank.");
							
							itemCodeTextField.setEditable(true);
						}
						
						else if (newItemCodeCorrect == true)
						{
							
						}
						
						if (newDescriptionCorrect != true)
						{
							descriptionErrorMessage.setVisible(true);
							descriptionErrorMessage.setEditable(false);
							descriptionErrorMessage.setText("Please enter a valid value; Description cannot be blank.");
							descriptionTextField.setEditable(true);

						}
						
						else if (newDescriptionCorrect == true)
						{
							
						}
						
						if (newArtistCorrect != true)
						{
							artistErrorMessage.setVisible(true);
							artistErrorMessage.setEditable(false);
							artistErrorMessage.setText("Please enter a valid value; Artist cannot be blank.");
							artistTextField.setEditable(true);							
						}
						
						else if(newArtistCorrect == true)
						{
							
						}
						
						if (newAlbumCorrect != true)
						{

							
						}
						
						if (newPriceCorrect != true)
						{
							priceErrorMessage.setVisible(true);
							priceErrorMessage.setEditable(false);
							priceErrorMessage.setText("Please enter a valid value for price, in the format #.##");
							priceTextField.setEditable(true);
						}
						
						else if (newPriceCorrect == true)
						{
							
						}
					}	//close else if 
				}	//close if(newSongAdded =true)
					
				else if (triedToEditSong == true)
				{			
					System.out.println("SONG EDITEDA"); //FOR TESTING 
					itemCodeTextField.setText(itemCodeTextField.getText().toString());	//can't be edited for existing songs
					

					//Valid descriptionTextField input
					if(descriptionTextField.getText().length() > 0 & !descriptionTextField.getText().toString().trim().isEmpty())
					{
						descriptionErrorMessage.setText("");
						descriptionTextField.setText(descriptionTextField.getText());	//this value also passed to songChoice2
						descriptionTextField.setEditable(false);
						editedDescriptionCorrect = true;		
						
					}
					
					else if (descriptionTextField.getText().length() == 0 | descriptionTextField.getText().toString().trim().isEmpty())
					{
						editedDescriptionCorrect = false;
					}
					
					//Validate artistTextField input 
					if(artistTextField.getText().length()  > 0 & !artistTextField.getText().toString().trim().isEmpty())
					{
						artistErrorMessage.setText("");
						artistTextField.setText(artistTextField.getText());	//this value also passed to songChoice2
						artistTextField.setEditable(false);
						editedArtistCorrect = true; 
					}
					
					else if (artistTextField.getText().length() == 0 | artistTextField.getText().toString().trim().isEmpty())
					{
						editedArtistCorrect = false;
					}
										
					//Validate albumTextField input 
					if(albumTextField.getText().length()  > 0 & !albumTextField.getText().toString().trim().isEmpty())
					{
						albumErrorMessage.setText("");
						albumTextField.setText(albumTextField.getText());	//this value also passed to songChoice2
						albumTextField.setEditable(false);
						editedAlbumCorrect = true; 							
					}
					
					else if (albumTextField.getText().length() == 0 | albumTextField.getText().toString().trim().isEmpty())
					{		
						albumErrorMessage.setVisible(true);
						albumErrorMessage.setEditable(false);
						albumErrorMessage.setText("'None' will be entered; this song is a single.");
						albumTextField.setText("None");	//indicate that the song is a single 
						editedAlbumCorrect = true;
					}
					
					//Validate priceTextField input 
					if( priceTextField.getText().length() > 0 & !priceTextField.getText().toString().trim().isEmpty() & priceTextField.getText().matches("^[0-9]*\\.?[0-9]*$"))
					{
						priceErrorMessage.setText("");
						priceTextField.setText(priceTextField.getText());	//this value also passed to songChoice2
						priceTextField.setEditable(false);
						editedPriceCorrect = true;
						
					}
					else if ( priceTextField.getText().length() == 0 | priceTextField.getText().toString().trim().isEmpty() | !priceTextField.getText().matches("^[0-9]*\\.?[0-9]*$"))
					{
						editedPriceCorrect = false;
					}	
					
					//Validate all fields 
					if(editedDescriptionCorrect == true & editedArtistCorrect == true & editedAlbumCorrect == true & editedPriceCorrect == true)
					{	
		
						//System.out.println("SONG EDITEDB"); //FOR TESTING 
					
						//Edit the existing song in the drop down menu and database; display refreshed info 
						String editedSongChoice = descriptionTextField.getText().toString();
						String editedSongItemCodeMatch = titleItemCodeTreeMap.get(originalSongBeforeEdit.description);
						
						int originalIndexPositionValue = 0;
						
						for(int i =0; i<songTitles.length; i++)
						{
							if(songTitles[i]== originalSongBeforeEdit.description)
							{
								originalIndexPositionValue += i;
							}
						}
						
						//System.out.println("original index position is: " + originalIndexPositionValue);	//FOR TESTING 
						
						songTitles[originalIndexPositionValue] = editedSongChoice;//original
						
						//System.out.println("new song title is:" + songTitles[originalIndexPositionValue]);
						
						//System.out.println("SONG EDITEDC"); //FOR TESTING 
						
						//Create a new MusicTrack object from the validated, user-entered text fields
						String tempItemCodeEdit = new String(itemCodeTextField.getText().toString());
						String tempDescriptionEdit = new String(descriptionTextField.getText().toString());
						String tempArtistEdit = new String(artistTextField.getText().toString());
						String tempAlbumEdit = new String(albumTextField.getText().toString());
						String tempPriceEdit = new String(priceTextField.getText().toString());
						
						String originalSongKey = titleItemCodeTreeMap.get(originalSongBeforeEdit.description);
						
						//System.out.println("original song key is: " + originalSongKey);	//FOR TESTING
						
						if(titleItemCodeTreeMap.containsKey(originalSongBeforeEdit.description))
						{
							titleItemCodeTreeMap.remove(originalSongBeforeEdit.description, originalSongKey);
							titleItemCodeTreeMap.put(tempDescriptionEdit, tempItemCodeEdit);
						}
						
						//System.out.println(titleItemCodeTreeMap.entrySet());	//FOR TESTING; prints in ascending key order
						
						MusicTrack editedTrackTemp2 = new MusicTrack(tempItemCodeEdit, tempDescriptionEdit, tempArtistEdit, tempAlbumEdit, tempPriceEdit);
				        
						songDatabase.remove(originalIndexPositionValue);
						songDatabase.add(originalIndexPositionValue, editedTrackTemp2);
						
						//songDatabase.listIterator().toString();	//FOR TESTING 
						
						if(musicTracksTreeMap.containsKey(originalSongKey))
						{
							musicTracksTreeMap.remove(originalSongKey, originalSongBeforeEdit); 
							musicTracksTreeMap.put(tempItemCodeEdit, editedTrackTemp2);
							//System.out.println(musicTracksTreeMap.entrySet());	//FOR TESTING 
							
							//System.out.println("BEFORE" + choices.toString());	//FOR TESTING 
							
							choices.add(originalIndexPositionValue, editedSongChoice);	//Add the edited song in place of the original song in the drop down menu
							
							//System.out.println("AFTER ADD" + choices.toString());	//FOR TESTING 
	
							choices.remove(originalSongBeforeEdit.description);
							
							//System.out.println("AFTER REMOVE:" + choices.toString());	//FOR TESTING 
							
						}
						else if (!musicTracksTreeMap.containsKey(originalSongKey))
						{
							
						}
						
						//System.out.println(editedTrackTemp2.toString());	//FOR TESTING					
				        //System.out.println("goes to  songChoice2.getSelectionModel().select(editedTrackTemp2.description)"); //TESTING
				        songChoice2.setValue(editedTrackTemp2.description);
				        songChoice2.getSelectionModel().select(editedTrackTemp2.description);
				        songEdited = true;
				        songDeleted = false;
				        cancelPressed = false;
				        newSongAdded = false;
					}
					
					//Allow user to correct invalid entries 
					else if (editedDescriptionCorrect != true | editedArtistCorrect != true | editedAlbumCorrect != true | editedPriceCorrect != true)
					{
						if (editedDescriptionCorrect != true)
						{
							descriptionErrorMessage.setVisible(true);
							descriptionErrorMessage.setEditable(false);
							descriptionErrorMessage.setText("Please enter a valid value; Description cannot be blank.");
							descriptionTextField.setEditable(true);
						}
						
						else if (editedDescriptionCorrect == true)
						{
							
						}
						
						if (editedArtistCorrect != true)
						{
							artistErrorMessage.setVisible(true);
							artistErrorMessage.setEditable(false);
							artistErrorMessage.setText("Please enter a valid value; Artist cannot be left blank.");
							artistTextField.setEditable(true);							
						}
						
						else if(editedArtistCorrect == true)
						{
							
						}
						
						if (editedAlbumCorrect != true)
						{
					
						}
						
						if (editedPriceCorrect != true)
						{
							priceErrorMessage.setVisible(true);
							priceErrorMessage.setEditable(false);
							priceErrorMessage.setText("Please enter a valid value for price, in the format #.##");
							priceTextField.setEditable(true);
						}
						
						else if (editedPriceCorrect == true)
						{
							
						}
					}	//close else if 	
				}	//close if (triedToAddNewSong = true)
			}	//close handle
		}	//close acceptButtonHandler
		

		//If the user presses the Cancel button, the edit transaction is canceled and the frame reverts to its original state.
		//The cancelButtonHandler class handles action events that occur to the "Cancel" button 
		class cancelButtonHandler implements EventHandler<ActionEvent> 
		{
			public void handle( ActionEvent cancelButtonEvent )
			{

				if(triedToAddNewSong == true)
				{
					//System.out.println("Tried to add new song and changed mind"); //FOR TESTING 
					
					//Discard changes by returning to select the first (already existing) song 
					songChoice2.getSelectionModel().selectFirst();
			        String selectedSong = songChoice2.getSelectionModel().getSelectedItem().toString();
			        songChoice2.getSelectionModel().select(selectedSong);
			        newSongAdded = false;
			        songDeleted = false;
			        cancelPressed = true;
				}
				
				else if (triedToEditSong == true)
				{
					//System.out.println("Tried to edit song and changed mind"); //FOR TESTING 
					
					MusicTrack originalSongBeforeCancel = musicTracksTreeMap.get(itemCodeTextField.getText().toString()); 
					
					//Get original song's item code and select the song's original title in the combo box 
					String songToRestoreItemCode = originalSongBeforeCancel.itemCode;				
					String songTitleToRestore = musicTracksTreeMap.get(songToRestoreItemCode).description;

					songChoice2.setDisable(false);			
					songChoice2.setValue(songTitleToRestore);
					songChoice2.getSelectionModel().select(songTitleToRestore);

					
					//System.out.println("cancel pressed == true & descriptionTextField.getText() != null"); //For testing
					
					String selectedMusicTrackC = songChoice2.getSelectionModel().getSelectedItem();
					String matchSongChoiceToItemCodeC = titleItemCodeTreeMap.get(selectedMusicTrackC);
					
					//ITEM CODE AS KEY
					itemCodeTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeC).itemCode);
					descriptionTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeC).description);
					artistTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeC).artist);
					albumTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeC).album);
					priceTextField.setText(musicTracksTreeMap.get(matchSongChoiceToItemCodeC).priceString);
					
					//Disable all text fields once the new entry is displayed
					itemCodeTextField.setEditable(false);
					descriptionTextField.setEditable(false);
					artistTextField.setEditable(false);
					albumTextField.setEditable(false);
					priceTextField.setEditable(false);
					
					//Enable all option buttons 
					for(Button b : buttonList)
					{
						b.setDisable(false);
					}					
					
					songChoice2.setDisable(false);			
					cancelPressed = false;	//reset
				}	//close else if
			}	//close handle( ActionEvent cancelButtonEvent )
		}	//close cancelButtonHandler 
		
		//The exitButtonHandler class handles action events that occur to the "Exit" button 
		class exitButtonHandler implements EventHandler<ActionEvent> 
		{
			public void handle( ActionEvent exitButtonEvent )
			{
								
				if (newDatabaseCreated != true)
				{
					try (PrintStream output = new PrintStream(new File(sourceFilePath)))
					{
						for (int i = 0; i < songDatabase.size(); i++)
						{
							MusicTrack trackDummy = songDatabase.get(i);
							output.print(trackDummy.itemCode + ";" + trackDummy.description + ";" + trackDummy.artist + ";" + trackDummy.album + ";" + trackDummy.priceString);
							output.println();
						}				
			            output.close();
					}
					catch (FileNotFoundException e) 
			        {
						System.out.println("File not found.");
			        }
				}	//close if (newDatabaseCreated != true)
				
				else if (newDatabaseCreated == true)
				{
					try (PrintStream output = new PrintStream(new File(newDatabaseFilePath)))
					{
						for (int i = 0; i < songDatabase.size(); i++)
						{
							MusicTrack trackDummy = songDatabase.get(i);
							output.print(trackDummy.itemCode + ";" + trackDummy.description + ";" + trackDummy.artist + ";" + trackDummy.album + ";" + trackDummy.priceString);
							output.println();
						}
						output.close();
					}
					catch (FileNotFoundException e) 
			        {
						System.out.println("File not found.");
			        }				
				}	//close else if (newDatabaseCreated == true)
				
				System.out.println("File saved");
				Platform.exit();
			}
		}
		
		add.setOnAction(new addButtonHandler());
		edit.setOnAction(new editButtonHandler());
		delete.setOnAction(new deleteButtonHandler());
		accept.setOnAction(new acceptButtonHandler());
		cancel.setOnAction(new cancelButtonHandler());
		exit.setOnAction(new exitButtonHandler());
		
		//Operate the content to display via switch
		switch(buttonPressed)
		{
		case 0:	//DEFAULT; FIRST SONG IN DATABASE IS SELECTED AND FIELDS ARE NOT EDITABLE
		{
			//Set the first track as the default selection
			songChoice2.getSelectionModel().selectFirst();
			songChoice2.setDisable(false);
						
			String itemCodeMatch = titleItemCodeTreeMap.get(songChoice2.getValue());	//TEST
			itemCodeTextField.setText(musicTracksTreeMap.get(itemCodeMatch).itemCode);
			itemCodeTextField.setEditable(false);
			
			String descriptionMatch = titleItemCodeTreeMap.get(songChoice2.getValue());	//TEST
			descriptionTextField.setText(musicTracksTreeMap.get(itemCodeMatch).description);
			descriptionTextField.setEditable(false);
			
			String artistMatch = titleItemCodeTreeMap.get(songChoice2.getValue());	//TEST
			artistTextField.setText(musicTracksTreeMap.get(itemCodeMatch).artist);
			artistTextField.setEditable(false);
			
			String albumMatch = titleItemCodeTreeMap.get(songChoice2.getValue());	//TEST
			albumTextField.setText(musicTracksTreeMap.get(itemCodeMatch).album);
			albumTextField.setEditable(false);
			
			String priceMatch = titleItemCodeTreeMap.get(songChoice2.getValue());	//TEST
			priceTextField.setText(musicTracksTreeMap.get(itemCodeMatch).priceString);
			priceTextField.setEditable(false);
			break;
	
		}
	
		case 1: //New database was created; no songs currently exist; need to force the user to add first thing 
		{
			
			songChoice2.getSelectionModel().selectFirst();
			songChoice2.setDisable(true);
						
			itemCodeTextField.setText("");
			itemCodeTextField.setEditable(false);
			
			descriptionTextField.setText("");
			descriptionTextField.setEditable(false);
			
			artistTextField.setText("");
			artistTextField.setEditable(false);
			
			albumTextField.setText("");
			albumTextField.setEditable(false);
			
			priceTextField.setText("");
			priceTextField.setEditable(false);
			
			//Only choices the user has at first = to add or exit 
			edit.setDisable(true);
			delete.setDisable(true);
			accept.setDisable(true);
			cancel.setDisable(true);
			
			break;
			
			
		}

		
	}	//close switch(buttonPressed)
		
		
		//Make the GridPane lines invisible
        primaryGridPane.setGridLinesVisible(false);
        
        //Add children to the GridPane
        primaryGridPane.add(songChoiceLabel, 0, 0); primaryGridPane.add(songChoice2, 1, 0);
        primaryGridPane.add(itemCodeLabel, 0, 1); primaryGridPane.add(itemCodeTextField, 1, 1); primaryGridPane.add(itemCodeErrorMessage, 4, 1);
        primaryGridPane.add(descriptionLabel, 0, 2); primaryGridPane.add(descriptionTextField, 1, 2); primaryGridPane.add(descriptionErrorMessage, 4, 2);
        primaryGridPane.add(artistLabel, 0, 3); primaryGridPane.add(artistTextField, 1, 3); primaryGridPane.add(artistErrorMessage, 4, 3);
        primaryGridPane.add(albumLabel, 0, 4); primaryGridPane.add(albumTextField, 1, 4); primaryGridPane.add(albumErrorMessage, 4, 4);
        primaryGridPane.add(priceLabel, 0, 5); primaryGridPane.add(priceTextField, 1, 5); primaryGridPane.add(priceErrorMessage, 4, 5);
        primaryGridPane.add(buttonHBox, 1, 7);
        
        
        myStage.setTitle("Song Database");
        myStage.setScene(myScene);
        myStage.show();	
        myStage.setOnCloseRequest(exitButtonEvent -> Platform.exit());        
	}
	
	
	
	
	/**
	*
	 @method createGridPane() : creates the primary grid pane that will be displayed in the GUI
	 @returns gridPane
	 
	*/		
	public GridPane createGridPane()
	{
	
		//Instantiate the gridPane
		GridPane primaryGridPane = new GridPane();
		primaryGridPane.setPadding(new Insets(20, 0, 20, 20));
		primaryGridPane.setHgap(7); 
		primaryGridPane.setVgap(7);
        
        return primaryGridPane;
	}	//close createGridPane()

}	//close SongDatabase

