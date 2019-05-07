package application;

import java.util.HashMap;
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
	private static final String FILTER_URL = "FilterImage.png";
	private HashMap<String, Pin> allCityPins;
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
			// TODO: replace below placeholder for filter code.
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

			// Below Text field for future search bar integration.
			// Construct the city class here.
			TextField citySearch = new TextField();
			citySearch.setLayoutX(191.0);
			citySearch.setLayoutY(16.0);
			AnchorPane.setLeftAnchor(citySearch, 191.0);
			AnchorPane.setTopAnchor(citySearch, 16.0);
			citySearch.setPadding(new Insets(5.0, 20.0, 5.0, 0));
			searchPane.getChildren().add(citySearch);
			// Adding the "ADD" button.
			Button addCity = new Button("ADD");
			addCity.setLayoutX(386.0);
			addCity.setLayoutY(17.0);
			addCity.setMnemonicParsing(false);
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
			allCityPins = new HashMap<String, Pin>();
			Pin toronto = new Pin();
			allCityPins.put("Toronto", toronto);
			Pin newYork = new Pin();
			allCityPins.put("New York", newYork);
			Pin vancouver = new Pin();
			allCityPins.put("Vancouver", vancouver);
			Pin saltLakeCity = new Pin();
			allCityPins.put("Salt Lake City", saltLakeCity);
			Pin saoPaulo = new Pin();
			allCityPins.put("Sao Paulo", saoPaulo);
			Pin houston = new Pin();
			allCityPins.put("Houston", houston);
			Pin losAngeles = new Pin();
			allCityPins.put("Los Angeles", losAngeles);
			Pin anchorage = new Pin();
			allCityPins.put("Anchorage", anchorage);
			Pin honolulu = new Pin();
			allCityPins.put("Honolulu", honolulu);
			Pin beijing = new Pin();
			allCityPins.put("Beijing", beijing);
			Pin hongKong = new Pin();
			allCityPins.put("Hong Kong", hongKong);
			Pin perth = new Pin();
			allCityPins.put("Perth", perth);
			Pin adelaide = new Pin();
			allCityPins.put("Adelaide", adelaide);
			Pin sydney = new Pin();
			allCityPins.put("Sydney", sydney);
			Pin london = new Pin();
			allCityPins.put("London", london);
			Pin mexicoCity = new Pin();
			allCityPins.put("Mexico City", mexicoCity);
			Pin capeTown = new Pin();
			allCityPins.put("Cape Town", capeTown);
			Pin addisAbaba = new Pin();
			allCityPins.put("Addis Ababa", addisAbaba);
			Pin rome = new Pin();
			allCityPins.put("Rome", rome);
			Pin santiago = new Pin();
			allCityPins.put("Santiago", santiago);
			Pin tokyo = new Pin();
			allCityPins.put("Tokyo", tokyo);
			Pin jakarta = new Pin();
			allCityPins.put("Jakarta", jakarta);
			Pin tehran = new Pin();
			allCityPins.put("Tehran", tehran);
			Pin lahore = new Pin();
			allCityPins.put("Lahore", lahore);
			Pin kathmandu = new Pin();
			allCityPins.put("Kathmandu", kathmandu);
			Pin mumbai = new Pin();
			allCityPins.put("Mumbai", mumbai);
			Pin caracas = new Pin();
			allCityPins.put("Caracas", caracas);
			Pin freetown = new Pin();
			allCityPins.put("Freetown", freetown);
			Pin dubai = new Pin();
			allCityPins.put("Dubai", dubai);
			Pin auckland = new Pin();
			allCityPins.put("Auckland", auckland);
			Pin magadan = new Pin();
			allCityPins.put("Magadan", magadan);
			Pin dhaka = new Pin();
			allCityPins.put("Dhaka", dhaka);
			Pin yangon = new Pin();
			allCityPins.put("Yangon", yangon);
			Pin nuuk = new Pin();
			allCityPins.put("Nuuk", nuuk);
			Pin cairo = new Pin();
			allCityPins.put("Cairo", cairo);
			Pin kinshasa = new Pin();
			allCityPins.put("Kinshasa", kinshasa);
			Pin lima = new Pin();
			allCityPins.put("Lima", lima);
			Pin reykjavik = new Pin();
			allCityPins.put("Reykjavik", reykjavik);

			/*
			 * TODO: JOHN PUT CODE FOR SEARCHBAR FUNCTION HERE!!
			 */

			// TODO:Taking search bar output, need method to put cities onto the citiesOnMap
			// HashMap
			// For example:
			citiesOnMap = new HashMap<String, Pin>();
			citiesOnMap.put("Sao Paulo", saoPaulo);
			citiesOnMap.put("Santiago", santiago);
			citiesOnMap.put("Lima", lima);
			citiesOnMap.put("Mumbai", mumbai);
			citiesOnMap.put("New York", newYork);
			citiesOnMap.put("Vancouver", vancouver);
			citiesOnMap.put("London", london);
			citiesOnMap.put("Tokyo", tokyo);
			citiesOnMap.put("Adelaide", adelaide);
			citiesOnMap.put("Mexico City", mexicoCity);
			citiesOnMap.put("Addis Ababa", addisAbaba);
			citiesOnMap.put("Jakarta", jakarta);
			citiesOnMap.put("Hong Kong", hongKong);
			citiesOnMap.put("Freetown", freetown);
			citiesOnMap.put("Tehran", tehran);

			// Creating a group to hold all of the pins and making pins.
			Group pinGroup = new Group();
			pinGroup.getChildren().add(saoPaulo.pinMaker("SÃO PAULO", 351.75, 403.5, "America/Sao_Paulo"));
			pinGroup.getChildren().add(santiago.pinMaker("SANTIAGO", 263, 441, "America/Santiago"));
			pinGroup.getChildren().add(lima.pinMaker("LIMA", 240.5, 359, "America/Lima"));
			pinGroup.getChildren().add(mumbai.pinMaker("MUMBAI", 794, 244, "Asia/Kolkata"));
			pinGroup.getChildren().add(newYork.pinMaker("NEW YORK", 251.5, 153, "America/New_York"));
			pinGroup.getChildren().add(vancouver.pinMaker("VANCOUVER", 70.25, 115.75, "America/Vancouver"));
			pinGroup.getChildren().add(london.pinMaker("LONDON", 522.5, 105, "Europe/London"));
			pinGroup.getChildren().add(tokyo.pinMaker("TOKYO", 1039.75, 176.5, "Asia/Tokyo"));
			pinGroup.getChildren().add(adelaide.pinMaker("ADELAIDE", 1036, 447.75, "Australia/Adelaide"));
			pinGroup.getChildren().add(mexicoCity.pinMaker("MEXICO CITY", 158.5, 243.5, "America/Mexico_City"));
			pinGroup.getChildren().add(addisAbaba.pinMaker("ADDIS ABABA", 667, 280.5, "Africa/Addis_Ababa"));
			pinGroup.getChildren().add(jakarta.pinMaker("JAKARTA", 918, 337, "Asia/Jakarta"));
			pinGroup.getChildren().add(hongKong.pinMaker("HONG KONG", 945.5, 230, "Hongkong"));
			pinGroup.getChildren().add(freetown.pinMaker("FREETOWN", 475.75, 282.5, "Africa/Freetown"));
			pinGroup.getChildren().add(tehran.pinMaker("TEHRAN", 713, 175, "Asia/Tehran"));
			/*
			 * TODO: Given new strategy of using 2 HashMaps, probably need to edit the Pin
			 * contructor to also take+set below passed arguments for all the cities. Then
			 * can update the pins in allCityPins above. Then see if can use a button
			 * ActionEvent to add the selected Pin to the citiesOnMap HashMap and to add the
			 * Pin.pinMaker the PinGRoup above.
			 */
//					toronto.pinMaker("TORONTO", 231, 141, "America/Toronto"),
//					newYork.pinMaker("NEW YORK", 251.5, 153, "America/New_York"),
//					vancouver.pinMaker("VANCOUVER", 70.25, 115.75, "America/Vancouver"),
//					saltLakeCity.pinMaker("SALTLAKECITY", 122, 149, "US/Mountain"),
//					saoPaulo.pinMaker("SÃO PAULO", 351.75, 403.5, "America/Sao_Paulo"),
//					houston.pinMaker("HOUSTON", 172.5, 201.5, "US/Central"),
//					losAngeles.pinMaker("LOS ANGELES", 87.5, 183.5, "America/Los_Angeles"),
//					anchorage.pinMaker("ANCHORAGE", -28.5, 53.5, "America/Anchorage"),
//					honolulu.pinMaker("HONOLULU", -58, 233.5, "Pacific/Honolulu"),
//					beijing.pinMaker("BEIJING", 954.75, 160, "Asia/Shanghai"),
//					hongKong.pinMaker("HONG KONG",	945.5, 230, "Hongkong"),
//					perth.pinMaker("PERTH", 951.5, 435, "Australia/Perth"),
//					adelaide.pinMaker("ADELAIDE", 1036, 447.75, "Australia/Adelaide"),
//					sydney.pinMaker("SYDNEY", 1082.5, 443.5, "Australia/Sydney"),
//					london.pinMaker("LONDON", 522.5, 105, "Europe/London"),
//					mexicoCity.pinMaker("MEXICO CITY", 158.5, 243.5, "America/Mexico_City"),
//					capeTown.pinMaker("CAPE TOWN", 592.25, 444.25, "Africa/Johannesburg"),
//					addisAbaba.pinMaker("ADDIS ABABA", 667, 280.5, "Africa/Addis_Ababa"),
//					rome.pinMaker("ROME", 570.25, 149.75, "Europe/Rome"),
//					santiago.pinMaker("SANTIAGO", 263, 441, "America/Santiago"),
//					tokyo.pinMaker("TOKYO", 1039.75, 176.5, "Asia/Tokyo"),
//					jakarta.pinMaker("JAKARTA", 918, 337, "Asia/Jakarta"),
//					tehran.pinMaker("TEHRAN", 713, 175, "Asia/Tehran"),
//					lahore.pinMaker("LAHORE", 797.75, 194.5, "Asia/Karachi"),
//					kathmandu.pinMaker("KATHMANDU", 838.5, 208.5, "Asia/Kathmandu"),
//					mumbai.pinMaker("MUMBAI", 794, 244, "Asia/Kolkata"),
//					caracas.pinMaker("CARACAS", 277, 275.25, "America/Caracas"),
//					freetown.pinMaker("FREETOWN", 475.75, 282.5, "Africa/Freetown"),
//					dubai.pinMaker("DUBAI", 729, 219, "Asia/Dubai"),
//					auckland.pinMaker("AUCKLAND", 1168.75, 455.75, "Pacific/Auckland"),
//					magadan.pinMaker("MAGADAN", 1080.5, 63, "Asia/Magadan"),
//					dhaka.pinMaker("DHAKA", 857.75, 224.75, "Asia/Dhaka"),
//					yangon.pinMaker("YANGON", 879, 251, "Asia/Rangoon"),
//					nuuk.pinMaker("NUUK", 333, 36, "America/Godthab"),
//					cairo.pinMaker("CAIRO", 639, 200, "Africa/Cairo"),
//					kinshasa.pinMaker("KINSHASA", 580.5, 330.5, "Africa/Kinshasa"),
//					lima.pinMaker("LIMA", 240.5, 359, "America/Lima"),
//					reykjavik.pinMaker("REYKJAVÍK", 444, 36.25, "Atlantic/Reykjavik")

			// Creating a new group to hold the map and the group of pins.
			Group mapPinOverlay = new Group(mapImage, pinGroup);

			// Start of zoom function code.
			ZoomFunctions zf = new ZoomFunctions();
			ScrollPane zoomPane = new ScrollPane(zf.createZoomPane(mapPinOverlay));

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
			for (Pin timePin : citiesOnMap.values()) {
				timePin.getTs().valueProperty().addListener((ov, OldValue, newValue) -> {
					for (Pin pin : citiesOnMap.values()) {
						String newDateTime = tc.Convert(timePin.getDate(), newValue, timePin.getTimeZoneID(),
								pin.getTimeZoneID());
						tc.outputFormatterDatePicker(newDateTime);
						tc.outputFormatterTimeSpinner(newDateTime);
						pin.setPinDateAndTime(tc.getNewYear(), tc.getNewMonth(), tc.getNewDay(), tc.getNewTime());
					}
				});
			}

			root.getChildren().add(parentBorderPane);
			window.setScene(scene);
			window.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
