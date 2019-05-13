package application;

import java.time.LocalDate;

import java.time.LocalTime;
import javafx.scene.Group;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This class is responsible for creating pins and holding variables to pass to
 * the TimeConverter class.
 * 
 * @author saimerriam
 *
 */
public class Pin {
	private static final String WHITEPINIMAGE_URL = "WhitePinImage100.png";
	private static final String GREENPINIMAGE_URL = "GreenPinImage100.png";
	private static final String PINKPINIMAGE_URL = "PinkPinImage100.png";
	private String city;
	private String timeZoneID;
	private DatePicker dp;
	private TimeSpinner ts;
	private ImageView pinImage;
	private String userChoice;
	private String cityName;
	private String timeZone;
	private double cityCoordinateX;
	private double cityCoordinateY;

	/**
	 * 
	 * Constructs a Pin with the below variables.
	 * 
	 * @param cityName
	 * @param cityCoordinateX
	 * @param cityCoordinateY
	 * @param timeZone
	 * @param userChoice
	 */
	public Pin(String userChoice) {
		this.userChoice = userChoice;
	}

	/**
	 * Method creates a new pin group that can be added to the map.
	 * 
	 * @param cityName
	 * @param cityCoordinateX
	 * @param cityCoordinateY
	 * @param timeZone
	 * @return pin Group
	 */
	public Group pinMaker(String cityName, double cityCoordinateX, double cityCoordinateY, String timeZone) {
		city = cityName;
		timeZoneID = timeZone;
		this.cityCoordinateX = cityCoordinateX;
		this.cityCoordinateY = cityCoordinateY;
		
		// Adds image for the pin background and sizes it.
		pinImage = new ImageView();
		Image pin = new Image(WHITEPINIMAGE_URL);
		pinImage.setFitHeight(180.0);
		pinImage.setFitWidth(200);
		pinImage.setImage(pin);

		// Adds name of city and formats it.
		Label name = new Label(cityName.toUpperCase());
		name.setBlendMode(BlendMode.SRC_OVER);
		name.setTextFill(Color.web("726f6f"));
		name.setFont(new Font(18.0));
		name.setLayoutX(25);
		name.setLayoutY(25);

		// Adds date picker to the pin and positions it.
		dp = new DatePicker();
		dp.setBlendMode(BlendMode.SRC_OVER);
		dp.setScaleX(0.83);
		dp.setScaleY(0.83);
		dp.setPrefWidth(155.5);
		dp.setLayoutX(13);
		dp.setLayoutY(48);
		dp.setValue(LocalDate.now());

		// Adds time spinner to the pin and positions it.
		ts = new TimeSpinner();
		ts.setBlendMode(BlendMode.SRC_OVER);
		ts.setScaleX(0.85);
		ts.setScaleY(0.85);
		ts.setPrefWidth(125);
		ts.setLayoutX(15);
		ts.setLayoutY(76);

		// Creates a group to hold all elements
		Group pinGroup = new Group(pinImage, name, ts, dp);

		// Changes blend mode so that it sits on top of the map.
		pinGroup.setBlendMode(BlendMode.SRC_OVER);
		pinGroup.setScaleX(0.4);
		pinGroup.setScaleY(0.4);

		// Set coordinates of the whole pin group for use on the map.
		// e.g. Map coordinates for NYC are X = 255, Y = 108.
		pinGroup.setLayoutX(cityCoordinateX);
		pinGroup.setLayoutY(cityCoordinateY);

		return pinGroup;
	}

	/*
	 * This method sets pin date and time method to update values displayed on Pins.
	 */
	public void setPinDateAndTime(int year, int month, int dayOfMonth, LocalTime time) {
		dp.setValue(LocalDate.of(year, month, dayOfMonth));
		ts.getValueFactory().setValue(time);
	}

	/*
	 * When given a color, this method will change Pin color accordingly.
	 */
	public void setPinColor(String c) {
		String color = c;
		if (color.equals("green")) {
			Image greenPin = new Image(GREENPINIMAGE_URL);
			pinImage.setImage(greenPin);
		}
		if (color.equals("pink")) {
			Image pinkPin = new Image(PINKPINIMAGE_URL);
			pinImage.setImage(pinkPin);
		}
		if (color.equals("white")) {
			Image whitePin = new Image(WHITEPINIMAGE_URL);
			pinImage.setImage(whitePin);
		}
	}

	/*
	 * Method returns the current date from the Date Picker.
	 */
	public LocalDate getDate() {
		LocalDate currentDate = dp.getValue();
		return currentDate;
	}

	/*
	 * Method returns the current time from the Time Spinner.
	 */
	public LocalTime getTime() {
		LocalTime currentTime = ts.getValue();
		return currentTime;
	}

	/*
	 * Sets a new date to display on the Date Picker.
	 */
	public void setDate(LocalDate date) {
		dp.setValue(date);
	}

	public String getTimeZoneID() {
		return timeZoneID;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public String getCity() {
		return city;
	}

	public DatePicker getDp() {
		return dp;
	}

	public TimeSpinner getTs() {
		return ts;
	}

	public String getUserChoice() {
		return userChoice;
	}

	public String getCityName() {
		return cityName;
	}

	public double getCityCoordinateX() {
		return cityCoordinateX;
	}

	public double getCityCoordinateY() {
		return cityCoordinateY;
	}

}
