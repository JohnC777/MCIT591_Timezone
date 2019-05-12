package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.controlsfx.control.ToggleSwitch;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
	private static String originalSelectionState = "default";
//	private HashMap<String, Pin> citiesOnMap;
	ToggleSwitch wakingHours;
	Button update;
	ComboBox comboBox;

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
			leftBorderPane.setCenter(leftPane);
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
					+ "corresponding dates and times. \n" + "\n Use the switch above to color pins green if \n" 
					+ " local times fall within general waking hours \n (7:00 - 21:59), red if not (22:00 - 6:59).\n");
			howToUse.setTextAlignment(TextAlignment.LEFT);
			howToUse.setLayoutX(25);
			howToUse.setLayoutY(75);
			howToUse.setFont(new Font(11));
			howToUse.setFill(Color.web("d3d3d3"));
			leftPane.getChildren().add(howToUse);

			
//			ImageView filterViewer = new ImageView();
//			Image filter = new Image(FILTER_URL);
//			filterViewer.setImage(filter);
//			filterViewer.setPreserveRatio(true);
//			filterViewer.setFitWidth(240);
//			filterViewer.setLayoutX(17);
//			filterViewer.setLayoutY(300);
//			leftPane.getChildren().add(filterViewer);


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
			Pin addisAbaba = new Pin("ADDIS ABABA", 667, 280.5, "Africa/Addis_Ababa", "Addis Ababa (UTC+03:00)");
			Pin adelaide = new Pin("ADELAIDE", 1036, 447.75, "Australia/Adelaide", "Adelaide (UTC+09:30)");
			Pin anchorage = new Pin("ANCHORAGE", -28.5, 53.5, "America/Anchorage", "Anchorage (UTC-08:00)");
			Pin auckland = new Pin("AUCKLAND", 1168.75, 455.75, "Pacific/Auckland", "Auckland  (UTC+12:00)");
			Pin beijing = new Pin("BEIJING", 954.75, 160, "Asia/Shanghai", "Beijing (UTC+08:00)");
			Pin cairo = new Pin("CAIRO", 639, 200, "Africa/Cairo", "Cairo (UTC+02:00)");
			Pin capeTown = new Pin("CAPE TOWN", 592.25, 444.25, "Africa/Johannesburg", "Cape Town (UTC+02:00");
			Pin caracas = new Pin("CARACAS", 277, 275.25, "America/Caracas", "Caracas (UTC-04:30)");
			Pin dhaka = new Pin("DHAKA", 857.75, 224.75, "Asia/Dhaka", "Dhaka (UTC+06:00)");
			Pin dubai = new Pin("DUBAI", 729, 219, "Asia/Dubai", "Dubai (UTC+04:00)");
			Pin freetown = new Pin("FREETOWN", 475.75, 282.5, "Africa/Freetown", "Freetown (UTC+00:00)");
			Pin hongKong = new Pin ("HONG KONG", 945.5, 230, "Hongkong", "Hong Kong (UTC+08:00)");
			Pin honolulu = new Pin ("HONOLULU", -58, 233.5, "Pacific/Honolulu", "Honolulu (UTC-10:00)");
			Pin houston = new Pin ("HOUSTON", 172.5, 201.5, "US/Central", "Houston (UTC-05:00)");
			Pin jakarta = new Pin ("JAKARTA", 918, 337, "Asia/Jakarta", "Jakarta (UTC+07:00)");
			Pin kathmandu = new Pin ("KATHMANDU", 838.5, 208.5, "Asia/Kathmandu", "Kathmandu (UTC+05:45)");
			Pin kinshasa = new Pin ("KINSHASA", 580.5, 330.5, "Africa/Kinshasa", "Kinshasa (UTC+01:00)");
			Pin lahore = new Pin ("LAHORE", 797.75, 194.5, "Asia/Karachi", "Lahore (UTC+05:00)"); 
			Pin lima = new Pin ("LIMA", 240.5, 359, "America/Lima", "Lima (UTC-05:00)");
			Pin london = new Pin ("LONDON", 522.5, 105, "Europe/London", "London (UTC+01:00)"); 
			Pin losAngeles = new Pin ("LOS ANGELES", 87.5, 183.5, "America/Los_Angeles", "Los Angeles (UTC-07:00)"); 
			Pin magadan = new Pin ("MAGADAN", 1080.5, 63, "Asia/Magadan", "Magadan (UTC+10:00)"); 
			Pin mexicoCity = new Pin ("MEXICO CITY", 158.5, 243.5, "America/Mexico_City", "Mexico City (UTC-05:00)"); 
			Pin mumbai = new Pin ("MUMBAI", 794, 244, "Asia/Kolkata", "Mumbai (UTC+05:30)"); 
			Pin newYork = new Pin ("NEW YORK", 251.5, 153, "America/New_York", "New York (UTC-04:00)"); 
			Pin nuuk = new Pin ("NUUK", 333, 36, "America/Godthab", "Nuuk (UTC-02:00)"); 
			Pin perth = new Pin ("PERTH", 951.5, 435, "Australia/Perth", "Perth (UTC+08:00)"); 
			Pin reykjavik = new Pin ("REYKJAVÍK", 444, 36.25, "Atlantic/Reykjavik", "Reykjavik (UTC+00:00)");
			Pin rome = new Pin("ROME", 570.25, 149.75, "Europe/Rome", "Rome (UTC+02:00)"); 
			Pin saltLakeCity = new Pin("SALTLAKECITY", 122, 149, "US/Mountain", "Salt Lake City (UTC-06:00)"); 
			Pin santiago = new Pin("SANTIAGO", 263, 441, "America/Santiago", "Santiago (UTC-03:00)"); 
			Pin saoPaulo = new Pin ("SÃO PAULO", 351.75, 403.5, "America/Sao_Paulo", "Sao Paulo (UTC-03:00)"); 
			Pin sydney = new Pin ("SYDNEY", 1082.5, 443.5, "Australia/Sydney", "Sydney (UTC+10:00)"); 
			Pin tehran = new Pin("TEHRAN", 713, 175, "Asia/Tehran", "Tehran (UTC+04:30)"); 
			Pin tokyo = new Pin("TOKYO", 1039.75, 176.5, "Asia/Tokyo", "Tokyo (UTC+09:00)"); 
			Pin toronto = new Pin("TORONTO", 231, 141, "America/Toronto", "Toronto (UTC-04:00)"); 
			Pin vancouver = new Pin("VANCOUVER", 70.25, 115.75, "America/Vancouver", "Vancouver (UTC-07:00)"); 
			Pin yangon = new Pin ("YANGON", 879, 251, "Asia/Rangoon", "Yangon (UTC+06:30)");

			allCityPins = new HashMap<Pin, Group>();
			allCityPins.put(addisAbaba, addisAbaba.pinMaker("ADDIS ABABA", 667, 280.5, "Africa/Addis_Ababa"));
			allCityPins.put(adelaide, adelaide.pinMaker("ADELAIDE", 1036, 447.75, "Australia/Adelaide"));
			allCityPins.put(anchorage, anchorage.pinMaker("ANCHORAGE", -28.5, 53.5, "America/Anchorage"));
			allCityPins.put(auckland, auckland.pinMaker("AUCKLAND", 1168.75, 455.75, "Pacific/Auckland"));
			allCityPins.put(beijing, beijing.pinMaker("BEIJING", 954.75, 160, "Asia/Shanghai"));
			allCityPins.put(cairo, cairo.pinMaker("CAIRO", 639, 200, "Africa/Cairo"));
			allCityPins.put(capeTown, capeTown.pinMaker("CAPE TOWN", 592.25, 444.25, "Africa/Johannesburg"));
			allCityPins.put(caracas, caracas.pinMaker("CARACAS", 277, 275.25, "America/Caracas"));
			allCityPins.put(dhaka, dhaka.pinMaker("DHAKA", 857.75, 224.75, "Asia/Dhaka"));
			allCityPins.put(dubai, dubai.pinMaker("DUBAI", 729, 219, "Asia/Dubai"));
			allCityPins.put(freetown, freetown.pinMaker("FREETOWN", 475.75, 282.5, "Africa/Freetown"));
			allCityPins.put(hongKong, hongKong.pinMaker("HONG KONG", 945.5, 230, "Hongkong"));	
			allCityPins.put(honolulu, honolulu.pinMaker("HONOLULU", -58, 233.5, "Pacific/Honolulu"));
			allCityPins.put(houston, houston.pinMaker("HOUSTON", 172.5, 201.5, "US/Central"));
			allCityPins.put(jakarta, jakarta.pinMaker("JAKARTA", 918, 337, "Asia/Jakarta"));
			allCityPins.put(kathmandu, kathmandu.pinMaker("KATHMANDU", 838.5, 208.5, "Asia/Kathmandu"));
			allCityPins.put(kinshasa, kinshasa.pinMaker("KINSHASA", 580.5, 330.5, "Africa/Kinshasa"));
			allCityPins.put(lahore, lahore.pinMaker("LAHORE", 797.75, 194.5, "Asia/Karachi")); 
			allCityPins.put(lima, lima.pinMaker("LIMA", 240.5, 359, "America/Lima"));
			allCityPins.put(london, london.pinMaker("LONDON", 522.5, 105, "Europe/London"));
			allCityPins.put(losAngeles, losAngeles.pinMaker("LOS ANGELES", 87.5, 183.5, "America/Los_Angeles"));
			allCityPins.put(magadan, magadan.pinMaker("MAGADAN", 1080.5, 63, "Asia/Magadan"));
			allCityPins.put(mexicoCity, mexicoCity.pinMaker("MEXICO CITY", 158.5, 243.5, "America/Mexico_City"));
			allCityPins.put(mumbai, mumbai.pinMaker("MUMBAI", 794, 244, "Asia/Kolkata"));
			allCityPins.put(newYork, newYork.pinMaker("NEW YORK", 251.5, 153, "America/New_York"));
			allCityPins.put(nuuk, nuuk.pinMaker("NUUK", 333, 36, "America/Godthab"));
			allCityPins.put(perth, perth.pinMaker("PERTH", 951.5, 435, "Australia/Perth"));
			allCityPins.put(reykjavik, reykjavik.pinMaker("REYKJAVÍK", 444, 36.25, "Atlantic/Reykjavik"));
			allCityPins.put(rome, rome.pinMaker("ROME", 570.25, 149.75, "Europe/Rome"));
			allCityPins.put(saltLakeCity, saltLakeCity.pinMaker("SALTLAKECITY", 122, 149, "US/Mountain"));
			allCityPins.put(santiago, santiago.pinMaker("SANTIAGO", 263, 441, "America/Santiago"));
			allCityPins.put(saoPaulo, saoPaulo.pinMaker("SÃO PAULO", 351.75, 403.5, "America/Sao_Paulo"));
			allCityPins.put(sydney, sydney.pinMaker("SYDNEY", 1082.5, 443.5, "Australia/Sydney"));
			allCityPins.put(tehran, tehran.pinMaker("TEHRAN", 713, 175, "Asia/Tehran"));
			allCityPins.put(tokyo, tokyo.pinMaker("TOKYO", 1039.75, 176.5, "Asia/Tokyo"));
			allCityPins.put(toronto, toronto.pinMaker("TORONTO", 231, 141, "America/Toronto"));
			allCityPins.put(vancouver, vancouver.pinMaker("VANCOUVER", 70.25, 115.75, "America/Vancouver"));
			allCityPins.put(yangon, yangon.pinMaker("YANGON", 879, 251, "Asia/Rangoon"));

			//Search Bar Code
			DropDownMenu ddm = new DropDownMenu();
			comboBox = new ComboBox();
			comboBox = ddm.populateComboBox();
			new AutoCompleteComboBoxListener<>(comboBox);
			comboBox.setLayoutX(184.75);
			comboBox.setLayoutY(16.0);
			searchPane.getChildren().add(comboBox);
			
			
			// TODO:Taking search bar output, need method to put cities onto the citiesOnMap
			// HashMap	
//			citiesOnMap = new HashMap<String, Pin>();
			// Creating a group to hold all of the pins and making pins.
			Group pinGroup = new Group();
			
			// Adding the "ADD" button.
			Button addCity = new Button("ADD");
			addCity.setLayoutX(417.0);
			addCity.setLayoutY(17.0);
			addCity.setPrefSize(52.0, 25.0);
			addCity.setTextFill(Color.web("#e8e8e8"));
			addCity.setFont(new Font(14.0));
			addCity.setPadding(new Insets(0, 0, 0, 0));
			addCity.setStyle("-fx-background-color: rgba(0, 204, 190, 1);");
			searchPane.getChildren().add(addCity);
			
			//Setting action for "ADD" button.
			addCity.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
				String menuItem = ddm.getComboBoxValue();
				for (HashMap.Entry<Pin, Group> entry : allCityPins.entrySet()) {
					Pin key = entry.getKey();
					Group value = entry.getValue();
					if (key.getUserChoice().equals(menuItem)) {
						pinGroup.getChildren().add(value);
					} 
				}
				comboBox.getSelectionModel().clearSelection();
				}
			});
			
			Button reset = new Button("RESET MAP");
			reset.setLayoutX(486);
			reset.setLayoutY(16.5);
			reset.setPrefSize(90.25, 26.5);
			reset.setPadding(new Insets(0, 0, 0, 0));
			reset.setFont(new Font(12.0));
			reset.setTextFill(Color.web("#656565"));
			searchPane.getChildren().add(reset);
			reset.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					pinGroup.getChildren().removeAll(pinGroup.getChildren());
				}
			});
			
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
				key.getTs().valueProperty().addListener((ov, OldValue, newValue) -> {
					for (HashMap.Entry<Pin, Group> e : allCityPins.entrySet()) {
						Pin k = e.getKey();
						String newDateTime = tc.Convert(key.getDate(), newValue, key.getTimeZoneID(),
								k.getTimeZoneID());
						tc.outputFormatterDatePicker(newDateTime);
						tc.outputFormatterTimeSpinner(newDateTime);
						k.setPinDateAndTime(tc.getNewYear(), tc.getNewMonth(), tc.getNewDay(), tc.getNewTime());
////						working.checkBoxFilter(allCityPins, "newValue");
//						for (HashMap.Entry<Pin, Group> ent : allCityPins.entrySet()) {
//							Pin p = ent.getKey();
//							if (getW().isSelected()) {
//								TimeConverter timeCon = new TimeConverter();
//								LocalTime pinT = p.getTime();
//								LocalTime minimumTime = timeCon.outputFormatterTimeSpinner("yyyy-MM-dd 08:00");
//								LocalTime maxTime = timeCon.outputFormatterTimeSpinner("yyyy-MM-dd 17:59");
//								if (minimumTime.isBefore(pinT) && maxTime.isAfter(pinT)) {
//									p.setPinColor("green");
//								}
//								if (!(minimumTime.isBefore(pinT) && maxTime.isAfter(pinT))) {
//									k.setPinColor("pink");
//								}
//								}
//						}
//					} else {
//						k.setPinColor("white");
//					}
					}
					if(wakingHours.isSelected()) {
						wakingHours.setSelected(false);
//						working.setSelected(true);
					
					}

				});
				if (originalSelectionState.equals("true")) {
					update.fire();
					originalSelectionState = "default";
				}
			}
			
	
			
			// TODO: replace below placeholder for filter code.
			wakingHours = new ToggleSwitch();
//			wakingHours.setFont(Color.web("d3d3d3"));
			wakingHours.setLayoutX(197);
			wakingHours.setLayoutY(308);
			leftPane.getChildren().add(wakingHours);
			
			Label toggle = new Label("WAKING HOURS FILTER");
			toggle.setTextFill(Color.web("#d3d3d3"));
			toggle.setFont(new Font(14.0));
			toggle.setLayoutX(28.0);
			toggle.setLayoutY(290.0);
			toggle.setPrefSize(238.0, 57.0);
//			Text toggleText = new Text();
//			toggleText.setText(" Use  the switch above to color pins green if \n "
//					+ "local times fall within general waking hours\n (7:00 - 21:59), red if not (22:00 - 6:59).");
//			toggleText.setTextAlignment(TextAlignment.LEFT);
//			toggleText.setLayoutX(25);
//			toggleText.setLayoutY(350);
//			toggleText.setFont(new Font(11));
//			toggleText.setFill(Color.web("d3d3d3"));
//			leftPane.getChildren().add(toggleText);
			leftPane.getChildren().add(toggle);

			
//			update = new Button("UPDATE");
//			leftPane.getChildren().add(update);
//			update.setOnAction(new EventHandler<ActionEvent>() {
//				@Override
//				public void handle(ActionEvent event) {
//					for (HashMap.Entry<Pin, Group> entry : allCityPins.entrySet()) {
//						Pin key = entry.getKey();
//						TimeConverter timeCon = new TimeConverter();
//						LocalTime pinT = key.getTime();
//					if (working.isSelected() == true) {
//							LocalTime minWorking = timeCon.outputFormatterTimeSpinner("yyyy-MM-dd 07:00");
//							LocalTime maxWorking = timeCon.outputFormatterTimeSpinner("yyyy-MM-dd 17:59");
//							if (minWorking.isBefore(pinT) && maxW.isAfter(pinT)) {
//								key.setPinColor("green");
//							}
//							if (!(minimumTime.isBefore(pinT) && maxTime.isAfter(pinT))) {
//								key.setPinColor("pink");
//							}
//					if(working.isSelected()==true && leisure.isSelected() == true) {
//						LocalTime minWorkLeisure = timeCon.outputFormatterTimeSpinner("yyyy-MM-dd 07:00");
//						LocalTime maxWorkLeisure = timeCon.outputFormatterTimeSpinner("yyyy-MM-dd 17:59");
//						if (minimumTime.isBefore(pinT) && maxTime.isAfter(pinT)) {
//							key.setPinColor("green");
//						}
//						if (!(minimumTime.isBefore(pinT) && maxTime.isAfter(pinT))) {
//							key.setPinColor("pink");
//						}
//					}
//					
//					} else {
//							key.setPinColor("white");
//						}
//					}
//				}
//			});
			
			
			wakingHours.selectedProperty().addListener((o, oldValue, newValue) -> {
				for (HashMap.Entry<Pin, Group> entry : allCityPins.entrySet()) {
					Pin key = entry.getKey();
				if (newValue) {
						TimeConverter timeCon = new TimeConverter();
						LocalTime pinT = key.getTime();
						LocalTime minimumTime = timeCon.outputFormatterTimeSpinner("yyyy-MM-dd 08:00");
						LocalTime maxTime = timeCon.outputFormatterTimeSpinner("yyyy-MM-dd 22:00");
						if (minimumTime.isBefore(pinT) && maxTime.isAfter(pinT)) {
							key.setPinColor("green");
						}
						if (!(minimumTime.isBefore(pinT) && maxTime.isAfter(pinT))) {
							key.setPinColor("pink");
						}
					} 
				else {
						key.setPinColor("white");
					}
				}
				
			});
			
//			
//			w.setOnAction(new EventHandler<ActionEvent>() {
//				@Override
//				public void handle(ActionEvent event) {
//					System.out.println("handing color!");
//					for (HashMap.Entry<Pin, Group> entry : allCityPins.entrySet()) {
//						TimeConverter timeCon = new TimeConverter();
//						Pin key = entry.getKey();
//						LocalTime pinT = key.getTime();
//						LocalTime minimumTime = timeCon.outputFormatterTimeSpinner("yyyy-MM-dd 08:00");
//						LocalTime maxTime = timeCon.outputFormatterTimeSpinner("yyyy-MM-dd 17:59");
//						if (minimumTime.isBefore(pinT) && maxTime.isAfter(pinT)) {
//							key.setPinColor("green");
//						}
//						if (!(minimumTime.isBefore(pinT) && maxTime.isAfter(pinT))) {
//							key.setPinColor("pink");
//						}
//					}
//				}
//			});
			
			root.getChildren().add(parentBorderPane);
			window.setScene(scene);
			window.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public CheckBox getWakingHours() {
//		return wakingHours;
//	}
//	
	public HashMap<Pin, Group> getAllCityPins() {
		return allCityPins;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
