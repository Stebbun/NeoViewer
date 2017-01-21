/**Homework 7.4 - NeoViewer Class
 * NeoViewer is a GUI application that allows the user to manage and store records of NearEarthObject instances in 
 * the database.
 * 
 * @author Steven Li
 * E-mail: steven.li@stonybrook.edu
 * SBU id: 110296296
 * course: CSE 214
 * recitation: 05
 */
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class NeoViewer extends Application{
	
	//NeoDatabase
	NeoDatabase database = new NeoDatabase();
	
	//All scenes
	Scene menuScene;
	Scene addPageScene;
	Scene sortPageScene;
	Scene printPageScene;
	
	//Buttons for menu scene
	final Button addPageButton = new Button("Add a page to the Database");
	final Button sortPageButton = new Button("Sort the Database");
	final Button printPageButton = new Button("Print the Database as a table");
	final Button quitButton = new Button("Quit");
	
	//Nodes for addPageScene
	final Button submitButton1 = new Button("Submit");
	final Button backButton1 = new Button("Back");
	final Label errorLabel1 = new Label();
	TextField pageNumField = new TextField();
	Boolean[] isPageDupe = new Boolean[NeoDatabase.MAX_PAGE_NUM];
	
	//Nodes for sortPageScene
	String sortMethod = "Reference ID";
	final Button submitButton2 = new Button("Submit");
	final Button backButton2 = new Button("Back");
	final Label errorLabel2 = new Label();
	ComboBox<String> sortCombo = new ComboBox<String>();
	
	//Nodes for printPageScene
	final Button backButton3 = new Button("Back");
	TableView<NearEarthObject> neoTable = new TableView<NearEarthObject>();
	
	/**Main method that launches the GUI.
	 * 
	 * @param args
	 * 	arguments from command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		createMenu();
		createAddPage();
		createSortPage();
		createPrintPage();
		
		handleButtonClicks(window);
		
		window.setTitle("NeoViewer");
		window.setScene(menuScene);
		window.show();
	}
	
	/**Creates the menu scene for the NeoViewer.
	 * 
	 */
	public void createMenu(){
		Text neoViewer = new Text("NEOVIEWER");
		neoViewer.setFont(Font.font("Rockwell Extra Bold", 40));
		neoViewer.setFill(Color.WHEAT);
		
		VBox menu = new VBox(10);
		menu.setPadding(new Insets(10,10,10,10));
		menu.setAlignment(Pos.CENTER);
		menu.getChildren().addAll(neoViewer, addPageButton, sortPageButton, printPageButton, quitButton);
		
		BorderPane border = new BorderPane();
		border.setCenter(menu);
		try{
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("NeoViewerBackground.jpg");
			Image background = new Image(is);
			BackgroundImage bgImg = new BackgroundImage(background, null, null, null, null);
			Background bg = new Background(bgImg);
			border.setBackground(bg);
		}
		catch(Exception e){
			
		}
		
		menuScene = new Scene(border, 400, 400);
	}
	
	/**Creates the add page scene for the NeoViewer.
	 * 
	 */
	public void createAddPage(){
		GridPane addPageGrid = new GridPane();
		addPageGrid.setPadding(new Insets(10, 10, 10, 10));
		addPageGrid.setVgap(5);
		addPageGrid.setHgap(5);
		
		Label pageNumLabel = new Label("Enter a page number (1-715)");
		pageNumLabel.setTextFill(Color.AZURE);
		pageNumField.setPromptText("Choose from 1-715");
		errorLabel1.setTextFill(Color.AZURE);
		
		GridPane.setConstraints(pageNumLabel, 0, 0);
		GridPane.setConstraints(pageNumField, 0, 1);
		GridPane.setConstraints(submitButton1, 1, 1);
		GridPane.setConstraints(backButton1, 1, 2);
		GridPane.setConstraints(errorLabel1, 0, 2);
		addPageGrid.getChildren().addAll(pageNumLabel, pageNumField, submitButton1, backButton1, errorLabel1);
		
		try{
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("NeoViewerSmall.jpg");
			Image background = new Image(is);
			BackgroundImage bgImg = new BackgroundImage(background, null, null, null, null);
			Background bg = new Background(bgImg);
			addPageGrid.setBackground(bg);
		}
		catch(Exception e){
			
		}
		
		addPageScene = new Scene(addPageGrid, 300, 200);
	}
	
	/**Creates the sort page for the NeoViewer.
	 * 
	 */
	public void createSortPage(){
		GridPane sortPageGrid = new GridPane();
		sortPageGrid.setPadding(new Insets(10, 10, 10, 10));
		sortPageGrid.setVgap(5);
		sortPageGrid.setHgap(5);
		
		Label sortOptionLabel = new Label("Select a sorting option:");
		sortOptionLabel.setTextFill(Color.AZURE);
		sortCombo.getItems().addAll(
				"Reference ID",
				"Diameter",
				"Approach Date",
				"Miss Distance"
		);
		GridPane.setConstraints(sortOptionLabel, 0, 0);
		GridPane.setConstraints(sortCombo, 0, 1);
		GridPane.setConstraints(submitButton2, 1, 1);
		GridPane.setConstraints(backButton2, 1, 2);
		GridPane.setConstraints(errorLabel2, 0, 2);
		sortPageGrid.getChildren().addAll(sortOptionLabel, sortCombo, submitButton2, backButton2, errorLabel2);
		
		try{
			File spaceJpg = new File("NeoViewerSmall.jpg");
			InputStream is = new BufferedInputStream(new FileInputStream(spaceJpg.getAbsolutePath()));
			Image background = new Image(is);
			BackgroundImage bgImg = new BackgroundImage(background, null, null, null, null);
			Background bg = new Background(bgImg);
			sortPageGrid.setBackground(bg);
		}
		catch(Exception e){
			
		}
		
		sortPageScene = new Scene(sortPageGrid, 300, 200);
	}
	
	/**Creates the print page scene for the NeoViewer.
	 * 
	 */
	public void createPrintPage(){
		VBox printPagePane = new VBox();
		
		TableColumn<NearEarthObject, Integer> refIDColumn = new TableColumn<>("ID");
		refIDColumn.setMinWidth(100);
		refIDColumn.setCellValueFactory(new PropertyValueFactory<>("referenceID"));
		
		TableColumn<NearEarthObject, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(100);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<NearEarthObject, Double> magColumn = new TableColumn<>("Magnitude");
		magColumn.setMinWidth(100);
		magColumn.setCellValueFactory(new PropertyValueFactory<>("absoluteMagnitude"));
		
		TableColumn<NearEarthObject, Double> diamColumn = new TableColumn<>("Diameter");
		diamColumn.setMinWidth(100);
		diamColumn.setCellValueFactory(new PropertyValueFactory<>("averageDiameter"));
		
		TableColumn<NearEarthObject, String> dangColumn = new TableColumn<>("Danger");
		dangColumn.setMinWidth(100);
		dangColumn.setCellValueFactory(new PropertyValueFactory<>("isDangerousStr"));
		
		TableColumn<NearEarthObject, String> dateColumn = new TableColumn<>("Close Date");
		dateColumn.setMinWidth(100);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateStr"));
		
		TableColumn<NearEarthObject, Double> disColumn = new TableColumn<>("Miss Distance");
		disColumn.setMinWidth(100);
		disColumn.setCellValueFactory(new PropertyValueFactory<>("missDistance"));
		
		TableColumn<NearEarthObject, String> orbitColumn = new TableColumn<>("Orbits");
		orbitColumn.setMinWidth(100);
		orbitColumn.setCellValueFactory(new PropertyValueFactory<>("orbitingBody"));
		
		neoTable.getColumns().addAll(refIDColumn, nameColumn, magColumn, diamColumn, dangColumn, dateColumn, disColumn, orbitColumn);
		
		printPagePane.getChildren().addAll(backButton3, neoTable);
		printPageScene = new Scene(printPagePane);
	}
	
	/**Receives an ObservableList of NearEarthObject instances.
	 * 
	 * @return
	 * 	ObservableList of NearEarthObject instances.
	 */
	public ObservableList<NearEarthObject> getNeo(){	
		ObservableList<NearEarthObject> neoList = FXCollections.observableArrayList();
		for(NearEarthObject e : database)
			neoList.add(e);
		
		return neoList;
	}
	
	/**Handles button clicks from the window Stage.
	 * 
	 * @param window
	 * 	The stage that contains the menu scenes.
	 */
	public void handleButtonClicks(Stage window){
		
		addPageButton.setOnAction(e ->{
			window.setScene(addPageScene);
		});
		sortPageButton.setOnAction(e ->{
			window.setScene(sortPageScene);
		});
		printPageButton.setOnAction(e ->{
			
			//In the case that sort is selected before adding pages
			switch(sortMethod){
				case "Reference ID":
					database.sortData(new ReferenceIDComparator());
					break;
				case "Diameter":
					database.sortData(new DiameterComparator());
					break;
				case "Approach Date":
					database.sortData(new ApproachDateComparator());
					break;
				case "Miss Distance":
					database.sortData(new MissDistanceComparator());
					break;
				default:
					break;
			}
			
			neoTable.setItems(getNeo());
			window.setScene(printPageScene);
		});
		quitButton.setOnAction(e ->{
			window.close();
		});
		submitButton1.setOnAction(e ->{
			try{	
				if(isPageDupe[Integer.parseInt(pageNumField.getText()) - 1] == null){
					isPageDupe[Integer.parseInt(pageNumField.getText()) - 1] = true;
					String queryURL = database.buildQueryURL(Integer.parseInt(pageNumField.getText()));
					database.addAll(queryURL);
					pageNumField.clear();
					window.setScene(menuScene);
				}
				else
					throw new IllegalArgumentException("Page has been entered before.");
			}
			catch(NumberFormatException ex){
				errorLabel1.setText("Input is not an integer.");
			}
			catch(IllegalArgumentException ex){
				errorLabel1.setText(ex.getMessage());
			}
			catch(NullPointerException ex){
				errorLabel1.setText("The page failed to load.");
			}
			catch(Exception ex){
				errorLabel1.setText("The page failed to load.");
			}

		});
		backButton1.setOnAction(e ->{
			pageNumField.clear();
			errorLabel1.setText("");
			window.setScene(menuScene);
		});
		
		submitButton2.setOnAction(e ->{
			try{
				switch(sortCombo.getValue()){
					case "Reference ID":
						database.sortData(new ReferenceIDComparator());
						sortMethod = sortCombo.getValue();
						break;
					case "Diameter":
						database.sortData(new DiameterComparator());
						sortMethod = sortCombo.getValue();
						break;
					case "Approach Date":
						database.sortData(new ApproachDateComparator());
						sortMethod = sortCombo.getValue();
						break;
					case "Miss Distance":
						database.sortData(new MissDistanceComparator());
						sortMethod = sortCombo.getValue();
						break;
					default:
						break;
				}
				window.setScene(menuScene);
			}
			catch(NullPointerException ex){
				errorLabel2.setText("Please select a sort.");
			}
		});
		backButton2.setOnAction(e ->{
			errorLabel2.setText("");
			window.setScene(menuScene);
		});
		backButton3.setOnAction(e ->{
			window.setScene(menuScene);
		});
		
	}
}
