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


    public String getMenuName() {
		return menuName;
	}

}

