package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 * Class responsible for creating the drop down menu with all of the city
 * options.
 * 
 * @author saimerriam and johncaton
 *
 */
public class DropDownMenu {
	ComboBox menuComboBox;

	/**
	 * Method to create a ComboBox with all the city/time zone options to display to
	 * the user.
	 * 
	 * @return a ComboBox with menu options.
	 */
	public ComboBox<String> populateComboBox() {

		/*
		 * Creating a list of all the different options to pass into the combo box.
		 */
		ObservableList<String> menuOptions = FXCollections.observableArrayList("Addis Ababa (UTC+03:00)",
				"Adelaide (UTC+09:30)", "Anchorage (UTC-08:00)", "Auckland  (UTC+12:00)", "Beijing (UTC+08:00)",
				"Cairo (UTC+02:00)", "Cape Town (UTC+02:00)", "Caracas (UTC-04:30)", "Dhaka (UTC+06:00)",
				"Dubai (UTC+04:00)", "Freetown (UTC+00:00)", "Hong Kong (UTC+08:00)", "Honolulu (UTC-10:00)",
				"Houston (UTC-05:00)", "Jakarta (UTC+07:00)", "Kathmandu (UTC+05:45)", "Kinshasa (UTC+01:00)",
				"Lahore (UTC+05:00)", "Lima (UTC-05:00)", "London (UTC+01:00)", "Los Angeles (UTC-07:00)",
				"Magadan (UTC+10:00)", "Mexico City (UTC-05:00)", "Mumbai (UTC+05:30)", "New York (UTC-04:00)",
				"Nuuk (UTC-02:00)", "Perth (UTC+08:00)", "Reykjavik (UTC+00:00)", "Rome (UTC+02:00)",
				"Salt Lake City (UTC-06:00)", "Santiago (UTC-03:00)", "Sao Paulo (UTC-03:00)", "Sydney (UTC+10:00)",
				"Tehran (UTC+04:30)", "Tokyo (UTC+09:00)", "Toronto (UTC-04:00)", "Vancouver (UTC-07:00)",
				"Yangon (UTC+06:30)");

		// Constructing combo box.
		menuComboBox = new ComboBox(menuOptions);
		menuComboBox.setItems(menuOptions);

		// Restricts viewing up to 7 options at a time.
		menuComboBox.setVisibleRowCount(7);
		return menuComboBox;
	}

	public String getComboBoxValue() {
		String value = String.valueOf(menuComboBox.getValue());
		return value;
	}

}
