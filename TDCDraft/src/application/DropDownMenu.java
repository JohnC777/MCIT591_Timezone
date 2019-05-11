package application;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class DropDownMenu {
	HashMap<String, Pin> menuAllCityPins;
	String menuName;
	SplitMenuButton splitMenuButton;
	ChoiceBox<String> menuChoiceBox;
	ComboBox menuComboBox;
//	@Override
//    public void start(Stage primaryStage) throws Exception {
//	}

	public SplitMenuButton populateMenu(HashMap<String, Pin> allCityPins) {
    	menuAllCityPins = allCityPins;
    	splitMenuButton = new SplitMenuButton();
    	
    	for (Pin p : menuAllCityPins.values()) {
    		String menuName = "menuItem" + p.getCity();
    		MenuItem menuItem  = new MenuItem(p.getUserChoice());
    		menuItem.setId(menuName);
    		splitMenuButton.getItems().add(menuItem);
    	}
    	splitMenuButton.setText("Choose your city");
//    	splitMenuButton.set
    	return splitMenuButton;

    	
    }

	public ComboBox<String> populateComboBox() {

		ObservableList<String> menuOptions = 
			    FXCollections.observableArrayList(
			"Addis Ababa (UTC+03:00)",
			"Adelaide (UTC+09:30)",
			"Anchorage (UTC-08:00)",
			"Auckland  (UTC+12:00)",
			"Beijing (UTC+08:00)",
			"Cairo (UTC+02:00)",
			"Cape Town (UTC+02:00)",
			"Caracas (UTC-04:30)",
			"Dhaka (UTC+06:00)",
			"Dubai (UTC+04:00)",
			"Freetown (UTC+00:00)",
			"Hong Kong (UTC+08:00)",
			"Honolulu (UTC-10:00)",
			"Houston (UTC-05:00)",
			"Jakarta (UTC+07:00)",
			"Kathmandu (UTC+05:45)",
			"Kinshasa (UTC+01:00)",
			"Lahore (UTC+05:00)",
			"Lima (UTC-05:00)",
			"London (UTC+01:00)",
			"Los Angeles (UTC-07:00)",
			"Magadan (UTC+10:00)",
			"Mexico City (UTC-05:00)",
			"Mumbai (UTC+05:30)",
			"New York (UTC-04:00)",
			"Nuuk (UTC-02:00)",
			"Perth (UTC+08:00)",
			"Reykjavik (UTC+00:00)",
			"Rome (UTC+02:00)",
			"Salt Lake City (UTC-06:00)",
			"Santiago (UTC-03:00)",
			"Sao Paulo (UTC-03:00)",
			"Sydney (UTC+10:00)",
			"Tehran (UTC+04:30)",
			"Tokyo (UTC+09:00)",
			"Toronto (UTC-04:00)",
			"Vancouver (UTC-07:00)",
			"Yangon (UTC+06:30)"
			);
		
		menuComboBox = new ComboBox(menuOptions);
		menuComboBox.setItems(menuOptions);
		menuComboBox.setVisibleRowCount(7); 
		return menuComboBox;
	}
	
	public String getComboBoxValue() {
		String value = String.valueOf(menuComboBox.getValue());
		return value;
	}
	
//	public ChoiceBox<String> populateChoiceBox(HashMap<String, Pin> allCityPins) {
//		menuChoiceBox = new ChoiceBox<String>();
//    	for (Pin p : menuAllCityPins.values()) {
//    		menuChoiceBox.getItems().add(p.getUserChoice());
//    	}
//    	menuChoiceBox.setValue("Choose a city to add");
//		return menuChoiceBox;
//	}
	
//        // Create MenuItems
//        MenuItem menuItemAddis = new MenuItem("Addis Ababa (UTC+03:00)");
//        MenuItem menuItemAdelaide = new MenuItem("Adelaide (UTC+09:30)");
//        MenuItem menuItemAnchorage = new MenuItem("Anchorage (UTC-08:00)");
//        MenuItem menuItemAukland = new MenuItem("Auckland  (UTC+12:00)");
//        MenuItem menuItemBeijing = new MenuItem("Beijing (UTC+08:00)");
//        MenuItem menuItemCairo = new MenuItem("Cairo (UTC+02:00)");
//        MenuItem menuItemCapeTown = new MenuItem("Cape Town (UTC+02:00)" );
//        MenuItem menuItemCaracas = new MenuItem("Caracas (UTC-04:30)");
//        MenuItem menuItemDhaka = new MenuItem("Dhaka (UTC+06:00)");
//        MenuItem menuItemDubai = new MenuItem("Dubai (UTC+04:00)");
//        MenuItem menuItemFreetown = new MenuItem("Freetown (UTC+00:00)");
//        MenuItem menuItemHongKong = new MenuItem("Hong Kong (UTC+08:00)");
//        MenuItem menuItemHonolulu = new MenuItem("Honolulu (UTC-10:00)");
//        MenuItem menuItemHouston = new MenuItem("Houston (UTC-05:00)");
//        MenuItem menuItemJakarta = new MenuItem("Jakarta (UTC+07:00)");
//        MenuItem menuItemKathmandu = new MenuItem("Kathmandu (UTC+05:45)");
//        MenuItem menuItemKinshasa = new MenuItem("Kinshasa (UTC+01:00)");
//        MenuItem menuItemLahore = new MenuItem("Lahore (UTC+05:00)");
//        MenuItem menuItemLima = new MenuItem("Lima (UTC-05:00)");
//        MenuItem menuItemLondon = new MenuItem("London (UTC+01:00)");
//        MenuItem menuItemLosAngeles = new MenuItem("Los Angeles (UTC-07:00)");
//        MenuItem menuItemMagadan = new MenuItem("Magadan (UTC+10:00)");
//        MenuItem menuItemMexicoCity = new MenuItem("Mexico City (UTC-05:00)");
//        MenuItem menuItemMumbai = new MenuItem("Mumbai (UTC+05:30)");
//        MenuItem menuItemNewYork = new MenuItem("New York (UTC-04:00)");
//        MenuItem menuItemNuuk = new MenuItem("Nuuk (UTC-02:00)");
//        MenuItem menuItemPerth = new MenuItem("Perth (UTC+08:00)");
//        MenuItem menuItemReykjavik = new MenuItem("Reykjavik (UTC+00:00)");
//        MenuItem menuItemRome = new MenuItem("Rome (UTC+02:00)");
//        MenuItem menuItemSaltLakeCity = new MenuItem("Salt Lake City (UTC-06:00)");
//        MenuItem menuItemSantiago = new MenuItem("Santiago (UTC-03:00)");
//        MenuItem menuItemSaoPaulo = new MenuItem("Sao Paulo (UTC-03:00)");
//        MenuItem menuItemSydney = new MenuItem("Sydney (UTC+10:00)");
//        MenuItem menuItemTehran = new MenuItem("Tehran (UTC+04:30)");
//        MenuItem menuItemTokyo = new MenuItem("Tokyo (UTC+09:00)");
//        MenuItem menuItemToronto = new MenuItem("Toronto (UTC-04:00)");
//        MenuItem menuItemVancouver = new MenuItem("Vancouver (UTC-07:00)");
//        MenuItem menuItemYangon = new MenuItem("Yangon (UTC+06:30)");
//
// 
//        // Create a MenuButton with all cities
//        SplitMenuButton splitMenuButton = new SplitMenuButton(menuItemAddis, menuItemAdelaide, menuItemAnchorage, menuItemAukland, menuItemBeijing, menuItemCairo, menuItemCapeTown, menuItemCaracas, menuItemDhaka, menuItemDubai, menuItemFreetown, menuItemHongKong, menuItemHonolulu, menuItemHouston, menuItemJakarta, menuItemKathmandu, menuItemKinshasa, menuItemLahore, menuItemLima, menuItemLondon, menuItemLosAngeles, menuItemMagadan, menuItemMexicoCity, menuItemMumbai, menuItemNewYork, menuItemNuuk, menuItemPerth, menuItemReykjavik, menuItemRome, menuItemSaltLakeCity, menuItemSantiago, menuItemSaoPaulo, menuItemSydney, menuItemTehran, menuItemTokyo, menuItemToronto, menuItemVancouver, menuItemYangon);
// 
//        splitMenuButton.setText("Choose your city");
//
// 
//        // Handling when the user selects city
//        splitMenuButton.setOnAction(new EventHandler<ActionEvent>() {
//        	
// 
//            @Override
//            public void handle(ActionEvent event) {
//            	if (menuItemAddis) {
//	        		citiesOnMap.put("Addis Ababa", addisAbaba)
//            	}else if (menuItemAdelaide) {
//	        		citiesOnMap.put("Adelaide", adelaide)
//            	}else if (menuItemAnchorage) {
//	        		citiesOnMap.put(Anchorage", anchorage)
//            	}else if (menuItemAukland) {
//	        		citiesOnMap.put("Auckland", auckland)
//            	}else if (menuItemBeijing) {
//	        		citiesOnMap.put("Beijing", beijing)
//            	}else if (menuItemCairo) {
//	        		citiesOnMap.put("Cairo", cairo)
//            	}else if (menuItemCapeTown) {
//	        		citiesOnMap.put("Cape Town", capeTown)
//            	}else if (menuItemCaracas) {
//	        		citiesOnMap.put("Caracas", caracas)
//            	}else if (menuItemDhaka) {
//	        		citiesOnMap.put("Dhaka", dhaka)
//            	}else if (menuItemDubai) {
//	        		citiesOnMap.put("Dubai", dubai)
//            	}else if (menuItemFreetown) {
//	        		citiesOnMap.put("Freetown", freetown)
//            	}else if (menuItemHongKong) {
//	        		citiesOnMap.put("Hong Kong", hongKong)
//            	}else if (menuItemHonolulu) {
//	        		citiesOnMap.put("Honolulu", honolulu)
//            	}else if (menuItemHouston) {
//	        		citiesOnMap.put("Houston", houston)
//            	}else if (menuItemJakarta) {
//	        		citiesOnMap.put("Jakarta", jakarta)
//            	}else if (menuItemKathmandu) {
//	        		citiesOnMap.put("Kathmandu", kathmandu)
//            	}else if (menuItemKinshasa) {
//	        		citiesOnMap.put("Kinshasa", kinshasa)
//            	}else if (menuItemLahore) {
//	        		citiesOnMap.put("Lahore", lahore)
//            	}else if (menuItemLima) {
//	        		citiesOnMap.put("Lima", lima)
//            	}else if (menuItemLondon) {
//	        		citiesOnMap.put("London", london)
//            	}else if (menuItemLosAngeles) {
//	        		citiesOnMap.put("Los Angeles", losAngeles)
//            	}else if (menuItemMagadan) {
//	        		citiesOnMap.put("Magadan", magadan)
//            	}else if (menuItemMexicoCity) {
//	        		citiesOnMap.put("Mexico City", mexicoCity)
//            	}else if (menuItemMumbai) {
//	        		citiesOnMap.put("Mumbai", mumbai)
//            	}else if (menuItemNewYork) {
//	        		citiesOnMap.put("New York", newYork)
//            	}else if (menuItemNuuk) {
//	        		citiesOnMap.put("Nuuk", nuuk))
//            	}else if (menuItemPerth) {
//	        		citiesOnMap.put("Perth", perth)
//            	}else if (menuItemReykjavik) {
//	        		citiesOnMap.put("Reykjavik", reykjavik)
//            	}else if (menuItemRome) {
//	        		citiesOnMap.put("Rome", rome)
//            	}else if (menuItemSaltLakeCity) {
//	        		citiesOnMap.put("Salt Lake City", saltLakeCity)
//            	}else if (menuItemSantiago) {
//	        		citiesOnMap.put("Santiago", santiago)
//            	}else if (menuItemSaoPaulo) {
//	        		citiesOnMap.put("Sao Paulo", saoPaulo)
//            	}else if (menuItemSydney) {
//	        		citiesOnMap.put("Sydney", sydney)
//            	}else if (menuItemTehran) {
//	        		citiesOnMap.put("Tehran", tehran)
//            	}else if (menuItemTokyo) {
//	        		citiesOnMap.put("Tokyo", tokyo)
//            	}else if (menuItemToronto) {
//	        		citiesOnMap.put("Toronto", toronto)
//            	}else if (menuItemVancouver) {
//	        		citiesOnMap.put("Vancouver", vancouver)
//            	}else if (menuItemYangon) {
//	        		citiesOnMap.put("Yangon", yangon)
//            	}
//	        			
//	        		});
// //Need help formatting this
//        
//        FlowPane root = new FlowPane();
//        root.setPadding(new Insets(10));
//        root.setVgap(5);
//        root.setHgap(5);
// 
//        root.getChildren().addAll(splitMenuButton, label);
// 
//        Scene scene = new Scene(root, 320, 150);
// 
//        primaryStage.setTitle("JavaFX SplitMenuButton");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
 
    public static void main(String[] args) {
        Application.launch(args);
    }
 
    
    public String getMenuName() {
		return menuName;
	}

}

