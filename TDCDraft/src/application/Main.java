package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class Main extends Application {
	Stage window;
	private static final String IMAGE_URL = "Map_Export500.png";
	private static final int MIN_PIXELS = 10;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//Setting main stage
			window = primaryStage;
			window.setTitle("Time Zone Planner");
			Group root = new Group();
			Scene scene = new Scene(root, 1150, 750);
			
			//parentBorderPane is the parent pane.
			BorderPane parentBorderPane = new BorderPane();
			parentBorderPane.setPrefSize(1150.0, 750.0);
			
			//Below is code for the left section of the parentBorderPane
			BorderPane leftBorderPane = new BorderPane();
			leftBorderPane.setPrefSize(271.0, 900);
			leftBorderPane.setStyle("-fx-background-color: #1e2e4a;");
			//Creating pane for the title of the program.
			AnchorPane titlePane = new AnchorPane();
			titlePane.setPrefSize(271.0, 53.0);
			titlePane.setStyle("-fx-background-color: #1f3559;");
			leftBorderPane.setTop(titlePane);	
			Label title = new Label("TIME ZONE PLANNER");
			title.setTextFill(Color.web("#d3d3d3"));
			title.setFont(new Font(20.0));
			title.setLayoutX(28.0);
			title.setLayoutY(-2.0);
			title.setPrefSize(238.0, 57.0);
			titlePane.getChildren().add(title);
			parentBorderPane.setLeft(leftBorderPane);
			
			//Creating pane for the lower left content.
			AnchorPane leftPane = new AnchorPane();
			Label infoHeader = new Label("APPLICATION INFORMATION");
			infoHeader.setTextFill(Color.web("#d3d3d3"));
			infoHeader.setFont(new Font(14.0));
			infoHeader.setLayoutX(28.0);
			infoHeader.setLayoutY(15.0);
			infoHeader.setPrefSize(238.0, 57.0);
			leftPane.getChildren().add(infoHeader);
			//Adding text with tips on how to use the program.
			Text howToUse = new Text();
			howToUse.setText(
					" This tool will show time zone conversions \n for  multiple  cities"
					+ " to  aid  in  international \n event and meeting planning.\n" + 
					"\n" + 
					" Search for and add cities from the location\n drop-down  menu.  "
					+ "This  will  add  location\n pins on the map. \n" + 
					"\n" + 
					" Enter  dates  and  times.  When  a  desired\n date  and  time  is  selected "
					+ "in  one city, the\n others will automatically update  with  the\n "
					+ "corresponding dates and times.\n" + 
					"\n" + 
					" Use the optional filter below to color times\n green  if  they  fall"
					+ " within  a  checked  time\n range, and red if not.");
			howToUse.setTextAlignment(TextAlignment.LEFT);
			howToUse.setLayoutX(25);
			howToUse.setLayoutY(75);
			howToUse.setFont(new Font(11));
			howToUse.setFill(Color.web("d3d3d3"));
			leftPane.getChildren().add(howToUse);
			leftBorderPane.setCenter(leftPane);
			
			//Below is code for the right/central section of the parentBorderPane
			BorderPane centerBorderPane = new BorderPane();
			parentBorderPane.setCenter(centerBorderPane);
			centerBorderPane.setPrefSize(1129.0, 79.0);
			//Below is the AnchorPane for the white top section and its contents. 
			AnchorPane searchPane = new AnchorPane();
			searchPane.setPrefSize(1129.0, 53.0);
			centerBorderPane.setTop(searchPane);
			//Adding "search location" label.
			Label searchPrompt = new Label("SEARCH LOCATION");
			searchPrompt.setTextFill(Color.web("#656565"));
			searchPrompt.setLayoutX(21.0);
			searchPrompt.setLayoutY(19.0);
			searchPrompt.setFont(new Font(16.0));
			AnchorPane.setLeftAnchor(searchPrompt, 21.0);
			AnchorPane.setTopAnchor(searchPrompt, 19.0);
			searchPane.getChildren().add(searchPrompt);
			
			//Below Text field for future search bar integration.
			//Construct the city class here.
			TextField citySearch = new TextField();
			citySearch.setLayoutX(191.0);
			citySearch.setLayoutY(16.0);
			AnchorPane.setLeftAnchor(citySearch, 191.0);
			AnchorPane.setTopAnchor(citySearch, 16.0);
			citySearch.setPadding(new Insets(5.0, 20.0, 5.0, 0));
			searchPane.getChildren().add(citySearch);
			//Adding the "ADD" and "RESET" buttons.
			Button addCity = new Button("ADD");
			addCity.setLayoutX(386.0);
			addCity.setLayoutY(17.0);
			addCity.setMnemonicParsing(false);
			addCity.setPrefSize(52.0, 25.0);
			addCity.setTextFill(Color.web("#e8e8e8"));
			addCity.setFont(new Font(12.0));
			addCity.setStyle("-fx-background-color: rgba(0, 204, 190, 1);");
			searchPane.getChildren().add(addCity);
			Button reset = new Button("RESET");
			reset.setLayoutX(463);
			reset.setLayoutY(16.5);
			reset.setPrefSize(52.25, 26.5);
			reset.setPadding(new Insets(0,0,0,0));
			reset.setFont(new Font(12.0));
			reset.setTextFill(Color.web("#656565"));
			searchPane.getChildren().add(reset);
			
			//Adding ImageView pane to hold map image.
			ImageView mapImage = new ImageView();
			mapImage.setFitHeight(745.0);
			mapImage.setFitWidth(1330);
			
			//Adding map image to ImageView.
			Image map = new Image(IMAGE_URL);
			mapImage.setImage(map);
			mapImage.setPickOnBounds(true);
			mapImage.setPreserveRatio(true);
			
			//Constructing the Pin class to create pins.
			Pin p = new Pin();
			
			//Creating a group to hold all of the pins.
			Group pinGroup = new Group(
					p.pinMaker("TORONTO", 231, 141, ""),
					p.pinMaker("NEW YORK", 251.5, 153, ""),
					p.pinMaker("VANCOUVER", 70.25, 115.75, ""),
					p.pinMaker("SALT LAKE CITY", 122, 149, ""),
					p.pinMaker("SÃO PAULO", 351.75, 403.5, ""),
					p.pinMaker("HOUSTON", 172.5, 201.5, ""),
					p.pinMaker("LOS ANGELES", 87.5, 183.5, ""),
					p.pinMaker("ANCHORAGE", -28.5, 53.5, ""),
					p.pinMaker("HONOLULU", -58, 233.5, ""),
					p.pinMaker("BEIJING", 954.75, 160, ""),
					p.pinMaker("HONG KONG",	945.5, 230, ""),
					p.pinMaker("PERTH", 951.5, 435, ""),
					p.pinMaker("ADELAIDE", 1036, 447.75, ""),
					p.pinMaker("SYDNEY", 1082.5, 443.5, ""),
					p.pinMaker("LONDON", 522.5, 105, ""),
					p.pinMaker("MEXICO CITY", 158.5, 243.5, ""),
					p.pinMaker("CAPE TOWN", 592.25, 444.25, ""),
					p.pinMaker("ADDIS ABABA", 667, 280.5, ""),
					p.pinMaker("ROME", 570.25, 149.75, ""),
					p.pinMaker("SANTIAGO", 263, 441, ""),
					p.pinMaker("TOKYO", 1039.75, 176.5, ""),
					p.pinMaker("JAKARTA", 918, 337, ""),
					p.pinMaker("TEHRAN", 713, 175, ""),
					p.pinMaker("LAHORE", 797.75, 194.5, ""),
					p.pinMaker("KATHMANDU", 838.5, 208.5, ""),
					p.pinMaker("MUMBAI", 794, 244, ""),
					p.pinMaker("CARACAS", 277, 275.25, ""),
					p.pinMaker("FREETOWN", 475.75, 282.5, ""),
					p.pinMaker("DUBAI", 729, 219, ""),
					p.pinMaker("AUCKLAND", 1168.75, 455.75, ""),
					p.pinMaker("MAGADAN", 1080.5, 63, ""),
					p.pinMaker("DHAKA", 857.75, 224.75, ""),
					p.pinMaker("YANGON", 879, 251, ""),
					p.pinMaker("NUUK", 333, 36, ""),
					p.pinMaker("CAIRO", 639, 200, ""),
					p.pinMaker("KINSHASA", 580.5, 330.5, ""),
					p.pinMaker("LIMA", 240.5, 359, ""),
					p.pinMaker("REYKJAVÍK", 444, 36.25, "")
					);
			
			//Creating a new group to hold the map and the group of pins.
			Group mapPinOverlay = new Group(
					mapImage,
					pinGroup
					);
			
			//Start of zoom function code. 
			ZoomFunctions zf = new ZoomFunctions();
			ScrollPane zoomPane = new ScrollPane(zf.createZoomPane(mapPinOverlay));
			
			//Creating new pane to hold the window navigation buttons.
			StackPane buttonPane = new StackPane();
			buttonPane.setBlendMode(BlendMode.SRC_OVER);
			buttonPane.getChildren().add(zf.createZoomButtonGroup(mapPinOverlay));

			//Create new group to hold the panes and setting it in the border pane that holds it.
			Group scrollPaneGroup = new Group(
					zoomPane,
					buttonPane
					); 
			centerBorderPane.setCenter(scrollPaneGroup);

			root.getChildren().add(parentBorderPane);
			window.setScene(scene);
			window.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//SvgImageLoaderFactory.install();
		//SvgImageLoaderFactory.install(new PrimitiveDimensionProvider());
		launch(args);
	}
}
