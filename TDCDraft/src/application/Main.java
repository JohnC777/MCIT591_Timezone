package application;

import java.util.HashMap;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
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
	private static final String FILTER_URL = "FilterImage.png";
	private HashMap<Pin, Group> allCityPins;
	private HashMap<String, Pin> citiesOnMap;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		try {

			// Setting main stage
			window = primaryStage;
			window.setTitle("Time Zone Planner");
			Group root = new Group();
			Scene scene = new Scene(root, 1150, 750);

			// parentBorderPane is the parent pane.
			BorderPane parentBorderPane = new BorderPane();
			parentBorderPane.setPrefSize(1150.0, 750.0);

			// Below is code for the left section of the parentBorderPane
			BorderPane leftBorderPane = new BorderPane();
			leftBorderPane.setPrefSize(271.0, 900);
			leftBorderPane.setStyle("-fx-background-color: #1e2e4a;");
			// Creating pane for the title of the program.
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

			// Creating pane for the lower left content.
			AnchorPane leftPane = new AnchorPane();
			Label infoHeader = new Label("APPLICATION INFORMATION");
			infoHeader.setTextFill(Color.web("#d3d3d3"));
			infoHeader.setFont(new Font(16.0));
			infoHeader.setLayoutX(28.0);
			infoHeader.setLayoutY(15.0);
			infoHeader.setPrefSize(238.0, 57.0);
			leftPane.getChildren().add(infoHeader);
			// Adding text with tips on how to use the program.
			Text howToUse = new Text();
			howToUse.setText(" This tool will  show time zone conversions \n for  multiple  cities"
					+ " to  aid  in  international \n event and meeting planning.\n" + "\n"
					+ " Search for and add cities from the location\n drop-down  menu.  "
					+ "This  will  add  location\n pins on the map. \n" + "\n"
					+ " Enter  dates  and  times.  When  a  desired\n date  and time is selected  "
					+ "in  one city, the\n others will  automatically update  with  the\n "
					+ "corresponding dates and times.\n" + "\n"
					+ " Use the optional filter below to color times\n green  if  they  fall"
					+ " within  a  checked  time\n range, and red if not.");
			howToUse.setTextAlignment(TextAlignment.LEFT);
			howToUse.setLayoutX(25);
			howToUse.setLayoutY(75);
			howToUse.setFont(new Font(11));
			howToUse.setFill(Color.web("d3d3d3"));
			leftPane.getChildren().add(howToUse);

			ImageView filterViewer = new ImageView();
			Image filter = new Image(FILTER_URL);
			filterViewer.setImage(filter);
			filterViewer.setPreserveRatio(true);
			filterViewer.setFitWidth(240);
			filterViewer.setLayoutX(17);
			filterViewer.setLayoutY(300);
			leftPane.getChildren().add(filterViewer);
			leftBorderPane.setCenter(leftPane);

			// Below is code for the right/central section of the parentBorderPane
			BorderPane centerBorderPane = new BorderPane();
			parentBorderPane.setCenter(centerBorderPane);
			centerBorderPane.setPrefSize(1129.0, 79.0);
			// Below is the AnchorPane for the white top section and its contents.
			AnchorPane searchPane = new AnchorPane();
			searchPane.setPrefSize(1129.0, 53.0);
			centerBorderPane.setTop(searchPane);
			// Adding "search location" label.
			Label searchPrompt = new Label("SEARCH LOCATION");
			searchPrompt.setTextFill(Color.web("#656565"));
			searchPrompt.setLayoutX(21.0);
			searchPrompt.setLayoutY(19.0);
			searchPrompt.setFont(new Font(16.0));
			AnchorPane.setLeftAnchor(searchPrompt, 21.0);
			AnchorPane.setTopAnchor(searchPrompt, 19.0);
			searchPane.getChildren().add(searchPrompt);


			// Adding the "ADD" button.
			Button addCity = new Button("ADD");
			addCity.setLayoutX(386.0);
			addCity.setLayoutY(17.0);
			addCity.setPrefSize(52.0, 25.0);
			addCity.setTextFill(Color.web("#e8e8e8"));
			addCity.setFont(new Font(14.0));
			addCity.setPadding(new Insets(0, 0, 0, 0));
			addCity.setStyle("-fx-background-color: rgba(0, 204, 190, 1);");
			searchPane.getChildren().add(addCity);
			Button reset = new Button("RESET");
			reset.setLayoutX(463);
			reset.setLayoutY(16.5);
			reset.setPrefSize(52.25, 26.5);
			reset.setPadding(new Insets(0, 0, 0, 0));
			reset.setFont(new Font(12.0));
			reset.setTextFill(Color.web("#656565"));
			searchPane.getChildren().add(reset);

			// Adding ImageView pane to hold map image.
			ImageView mapImage = new ImageView();
			mapImage.setFitHeight(745.0);
			mapImage.setFitWidth(1330);

			// Adding map image to ImageView.
			Image map = new Image(IMAGE_URL);
			mapImage.setImage(map);
			mapImage.setPickOnBounds(true);
			mapImage.setPreserveRatio(true);

			// Constructing the Pin classes to create pins.
			// Otherwise will not be able to differentiate them.
//			Pin addisAbaba = new Pin("ADDIS ABABA", 667, 280.5, "Africa/Addis_Ababa", "Addis Ababa (UTC+03:00)");
//			Pin adelaide = new Pin("ADELAIDE", 1036, 447.75, "Australia/Adelaide", "Adelaide (UTC+09:30)");
			Pin anchorage = new Pin("ANCHORAGE", -28.5, 53.5, "America/Anchorage", "Anchorage (UTC-08:00)");
//			Pin auckland = new Pin("AUCKLAND", 1168.75, 455.75, "Pacific/Auckland", "Auckland  (UTC+12:00)");
//			Pin beijing = new Pin("BEIJING", 954.75, 160, "Asia/Shanghai", "Beijing (UTC+08:00)");
//			Pin cairo = new Pin("CAIRO", 639, 200, "Africa/Cairo", "Cairo (UTC+02:00)");
//			Pin capeTown = new Pin("CAPE TOWN", 592.25, 444.25, "Africa/Johannesburg", "Cape Town (UTC+02:00");
//			Pin caracas = new Pin("CARACAS", 277, 275.25, "America/Caracas", "Caracas (UTC-04:30)");
//			Pin dhaka = new Pin("DHAKA", 857.75, 224.75, "Asia/Dhaka", "Dhaka (UTC+06:00)");
//			Pin dubai = new Pin("DUBAI", 729, 219, "Asia/Dubai", "Dubai (UTC+04:00)");
//			Pin freetown = new Pin("FREETOWN", 475.75, 282.5, "Africa/Freetown", "Freetown (UTC+00:00)");
//			Pin hongKong = new Pin ("HONG KONG", 945.5, 230, "Hongkong", "Hong Kong (UTC+08:00)");
//			Pin honolulu = new Pin ("HONOLULU", -58, 233.5, "Pacific/Honolulu", "Honolulu (UTC-10:00)");
			Pin houston = new Pin ("HOUSTON", 172.5, 201.5, "US/Central", "Houston (UTC-05:00)");
//			Pin jakarta = new Pin ("JAKARTA", 918, 337, "Asia/Jakarta", "Jakarta (UTC+07:00)");
//			Pin kathmandu = new Pin ("KATHMANDU", 838.5, 208.5, "Asia/Kathmandu", "Kathmandu (UTC+05:45)");
//			Pin kinshasa = new Pin ("KINSHASA", 580.5, 330.5, "Africa/Kinshasa", "Kinshasa (UTC+01:00)");
//			Pin lahore = new Pin ("LAHORE", 797.75, 194.5, "Asia/Karachi", "Lahore (UTC+05:00)"); 
//			Pin lima = new Pin ("LIMA", 240.5, 359, "America/Lima", "Lima (UTC-05:00)");
//			Pin london = new Pin ("LONDON", 522.5, 105, "Europe/London", "London (UTC+01:00)"); 
//			Pin losAngeles = new Pin ("LOS ANGELES", 87.5, 183.5, "America/Los_Angeles", "Los Angeles (UTC-07:00)"); 
//			Pin magadan = new Pin ("MAGADAN", 1080.5, 63, "Asia/Magadan", "Magadan (UTC+10:00)"); 
//			Pin mexicoCity = new Pin ("MEXICO CITY", 158.5, 243.5, "America/Mexico_City", "Mexico City (UTC-05:00)"); 
//			Pin mumbai = new Pin ("MUMBAI", 794, 244, "Asia/Kolkata", "Mumbai (UTC+05:30)"); 
			Pin newYork = new Pin ("NEW YORK", 251.5, 153, "America/New_York", "New York (UTC-04:00)"); 
//			Pin nuuk = new Pin ("NUUK", 333, 36, "America/Godthab", "Nuuk (UTC-02:00)"); 
//			Pin perth = new Pin ("PERTH", 951.5, 435, "Australia/Perth", "Perth (UTC+08:00)"); 
//			Pin reykjavik = new Pin ("REYKJAVÍK", 444, 36.25, "Atlantic/Reykjavik", "Reykjavik (UTC+00:00)");
//			Pin rome = new Pin("ROME", 570.25, 149.75, "Europe/Rome", "Rome (UTC+02:00)"); 
//			Pin saltLakeCity = new Pin("SALTLAKECITY", 122, 149, "US/Mountain", "Salt Lake City (UTC-06:00)"); 
//			Pin santiago = new Pin("SANTIAGO", 263, 441, "America/Santiago", "Santiago (UTC-03:00)"); 
//			Pin saoPaulo = new Pin ("SÃO PAULO", 351.75, 403.5, "America/Sao_Paulo", "Sao Paulo (UTC-03:00)"); 
//			Pin sydney = new Pin ("SYDNEY", 1082.5, 443.5, "Australia/Sydney", "Sydney (UTC+10:00)"); 
//			Pin tehran = new Pin("TEHRAN", 713, 175, "Asia/Tehran", "Tehran (UTC+04:30)"); 
//			Pin tokyo = new Pin("TOKYO", 1039.75, 176.5, "Asia/Tokyo", "Tokyo (UTC+09:00)"); 
//			Pin toronto = new Pin("TORONTO", 231, 141, "America/Toronto", "Toronto (UTC-04:00)"); 
//			Pin vancouver = new Pin("VANCOUVER", 70.25, 115.75, "America/Vancouver", "Vancouver (UTC-07:00)"); 
//			Pin yangon = new Pin ("YANGON", 879, 251, "Asia/Rangoon", "Yangon (UTC+06:30)");
			allCityPins = new HashMap<Pin, Group>();
//			allCityPins.put("Addis Ababa", addisAbaba);
//			allCityPins.put("Adelaide", adelaide);
			allCityPins.put(anchorage, anchorage.pinMaker("ANCHORAGE", -28.5, 53.5, "America/Anchorage"));
//			allCityPins.put("Auckland", auckland);
//			allCityPins.put("Beijing", beijing);
//			allCityPins.put("Cairo", cairo);
//			allCityPins.put("Cape Town", capeTown);
//			allCityPins.put("Caracas", caracas);
//			allCityPins.put("Dhaka", dhaka);
//			allCityPins.put("Dubai", dubai);
//			allCityPins.put("Freetown", freetown);
			allCityPins.put(houston, houston.pinMaker("HOUSTON", 172.5, 201.5, "US/Central"));			
//			allCityPins.put("London", london);
//			allCityPins.put("Los Angeles", losAngeles);
//			allCityPins.put("Magadan", magadan);
//			allCityPins.put("Mexico City", mexicoCity);
//			allCityPins.put("Mumbai", mumbai);
			allCityPins.put(newYork, newYork.pinMaker("NEW YORK", 251.5, 153, "America/New_York"));
//			allCityPins.put("Nuuk", nuuk);
//			allCityPins.put("Perth", perth);
//			allCityPins.put("Reykjavik", reykjavik);
//			allCityPins.put("Rome", rome);
//			allCityPins.put("Salt Lake City", saltLakeCity);
//			allCityPins.put("Santiago", santiago);
//			allCityPins.put("Sao Paulo", saoPaulo);
//			allCityPins.put("Sydney", sydney);
//			allCityPins.put("Tehran", tehran);
//			allCityPins.put("Tokyo", tokyo);
//			allCityPins.put("Toronto", toronto);
//			allCityPins.put("Vancouver", vancouver);
//			allCityPins.put("Yangon", yangon);

			/*
			 * TODO: JOHN PUT CODE FOR SEARCHBAR FUNCTION HERE!!!!!!!!!!
			 */
			//Search Bar Code
			DropDownMenu ddm = new DropDownMenu();
			ComboBox comboBox = new ComboBox();
			comboBox = ddm.populateComboBox();
			new AutoCompleteComboBoxListener<>(comboBox);
			comboBox.setLayoutX(191.0);
			comboBox.setLayoutY(16.0);
			searchPane.getChildren().add(comboBox);
			// TODO:Taking search bar output, need method to put cities onto the citiesOnMap
			// HashMap
		
			/*
			 * TODO: Given new strategy of using 2 HashMaps, probably need to edit the Pin
			 * contructor to also take+set below passed arguments for all the cities. Then
			 * can update the pins in allCityPins above. Then see if can use a button
			 * ActionEvent to add the selected Pin to the citiesOnMap HashMap and to add the
			 * Pin.pinMaker the PinGroup above.
			 */
//
//			// Creating a new group to hold the map and the group of pins.
//			Group mapPinOverlay = new Group(mapImage, pinGroup);
//
//			// Start of zoom function code.
//			ZoomFunctions zf = new ZoomFunctions();
//			ScrollPane zoomPane = new ScrollPane(zf.createZoomPane(mapPinOverlay));
//			zoomPane.setFitToHeight(true);
//			zoomPane.setFitToWidth(true);
//
//			// Creating new pane to hold the window navigation buttons.
//			StackPane buttonPane = new StackPane();
//			buttonPane.setBlendMode(BlendMode.SRC_OVER);
//			buttonPane.getChildren().add(zf.createZoomButtonGroup(mapPinOverlay));
//
//			// Create new group to hold the panes and setting it in the border pane that
//			// holds it.
//			Group scrollPaneGroup = new Group(zoomPane, buttonPane);
//			centerBorderPane.setCenter(scrollPaneGroup);
//			
//		
			
			citiesOnMap = new HashMap<String, Pin>();
			// Creating a group to hold all of the pins and making pins.
			Group pinGroup = new Group();
			
			addCity.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
//				HashMap<String, Pin> citiesOnMapLoop;
//				citiesOnMapLoop = citiesOnMap;
				String menuItem = ddm.getComboBoxValue();
				for (HashMap.Entry<Pin, Group> entry : allCityPins.entrySet()) {
					Pin key = entry.getKey();
					Group value = entry.getValue();
					if (key.getUserChoice().equals(menuItem)) {
						System.out.println(key.getUserChoice());
						pinGroup.getChildren().add(value);
//						citiesOnMap.put(key.getCity(), value);
					} 
				}
				}
			});
			
//			for (Pin p : citiesOnMap.values()) {
//				pinGroup.getChildren().add(p.pinMaker(p.getCityName(), p.getCityCoordinateX(), p.getCityCoordinateY(), p.getTimeZone()));
//			}
			
			// Creating a new group to hold the map and the group of pins.
			Group mapPinOverlay = new Group(mapImage, pinGroup);

			// Start of zoom function code.
			ZoomFunctions zf = new ZoomFunctions();
			ScrollPane zoomPane = new ScrollPane(zf.createZoomPane(mapPinOverlay));
			zoomPane.setFitToHeight(true);
			zoomPane.setFitToWidth(true);

			// Creating new pane to hold the window navigation buttons.
			StackPane buttonPane = new StackPane();
			buttonPane.setBlendMode(BlendMode.SRC_OVER);
			buttonPane.getChildren().add(zf.createZoomButtonGroup(mapPinOverlay));

			// Create new group to hold the panes and setting it in the border pane that
			// holds it.
			Group scrollPaneGroup = new Group(zoomPane, buttonPane);
			centerBorderPane.setCenter(scrollPaneGroup);
			
			// Start of conversion code:
			TimeConverter tc = new TimeConverter();

			// Using nested loops to check all pins.
			// Adding listener for the TimeSpinner to reset times (and dates if applicable):

			for (HashMap.Entry<Pin, Group> entry : allCityPins.entrySet()) {
				Pin key = entry.getKey();
				Group value = entry.getValue();
				key.getTs().valueProperty().addListener((ov, OldValue, newValue) -> {
					for (HashMap.Entry<Pin, Group> e : allCityPins.entrySet()) {
						Pin k = e.getKey();
//						Group v = e.getValue();
						String newDateTime = tc.Convert(key.getDate(), newValue, key.getTimeZoneID(),
								k.getTimeZoneID());
						tc.outputFormatterDatePicker(newDateTime);
						tc.outputFormatterTimeSpinner(newDateTime);
						k.setPinDateAndTime(tc.getNewYear(), tc.getNewMonth(), tc.getNewDay(), tc.getNewTime());
					}
				});
			}
			
			// Timeline Code
//			final Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler<ActionEvent>() {
//                
//				@Override
//                public void handle(ActionEvent actionEvent) {
//					// Creating a new group to hold the map and the group of pins.
//					Group mapPinOverlay = new Group(mapImage, pinGroup);
//
//					// Start of zoom function code.
//					ZoomFunctions zf = new ZoomFunctions();
//					ScrollPane zoomPane = new ScrollPane(zf.createZoomPane(mapPinOverlay));
//					zoomPane.setFitToHeight(true);
//					zoomPane.setFitToWidth(true);
//
//					// Creating new pane to hold the window navigation buttons.
//					StackPane buttonPane = new StackPane();
//					buttonPane.setBlendMode(BlendMode.SRC_OVER);
//					buttonPane.getChildren().add(zf.createZoomButtonGroup(mapPinOverlay));
//
//					// Create new group to hold the panes and setting it in the border pane that
//					// holds it.
//					Group scrollPaneGroup = new Group(zoomPane, buttonPane);
//					centerBorderPane.setCenter(scrollPaneGroup);
//                }
//            }) , new KeyFrame(Duration.seconds(2)));
//			timeline.setCycleCount(Animation.INDEFINITE);
//			timeline.play();
//			
			
			// TODO: replace below placeholder for filter code.
//			CheckBox c = new CheckBox();
//			c.setLayoutX(30);
//			c.setLayoutY(330);
//			c.setPrefSize(7, 7);
//			c.setPadding(new Insets(0,0,0,0));
//			c.setScaleX(.7);
//			c.setScaleY(.7);
//			c.setOnAction(new EventHandler<ActionEvent>() {
//				@Override
//				public void handle(ActionEvent event) {
//					//loop through HashMap
//					//Color code for green: "#ddffdf"
//				}
//			});
//			leftPane.getChildren().add(c);

			root.getChildren().add(parentBorderPane);
			window.setScene(scene);
			window.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void DisplayMap() {
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
