package application;

import javafx.scene.Group;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Pin {
	private static final String PINIMAGE_URL = "PinImage100.png";
	private String city;
	/*
	 * Method creates a new pin group to add to the map.
	 */
	public Group pinMaker(String cityName, double cityCoordinateX, double cityCoordinateY) {

		//Adds image for the pin background and sizes it.
		ImageView pinImage = new ImageView();
		Image pin = new Image(PINIMAGE_URL);
		pinImage.setFitHeight(180.0);
		pinImage.setFitWidth(200);
		pinImage.setImage(pin);
		
		//Adds name of city and formats it.
		Label name = new Label(cityName.toUpperCase());
		name.setBlendMode(BlendMode.SRC_OVER);
		name.setTextFill(Color.web("726f6f"));
		name.setFont(new Font(18.0));
		name.setLayoutX(25);
		name.setLayoutY(25);
		
		//Adds date picker to the pin and positions it.
		DatePicker dp = new DatePicker();
		dp.setBlendMode(BlendMode.SRC_OVER);
		dp.setScaleX(0.83);
		dp.setScaleY(0.83);
		dp.setPrefWidth(155.5);
		dp.setLayoutX(13);
		dp.setLayoutY(48);
		
		//Adds time spinner to the pin and positions it.
		TimeSpinner ts = new TimeSpinner();
		ts.setBlendMode(BlendMode.SRC_OVER);
		ts.setScaleX(0.85);
		ts.setScaleY(0.85);
		ts.setPrefWidth(125);
		ts.setLayoutX(15);
		ts.setLayoutY(76);

		
		//Creates a group to hold all elements
		Group pinGroup = new Group(pinImage, name, ts, dp);
		
		//Changes blend mode so that it sits on top of the map.
		pinGroup.setBlendMode(BlendMode.SRC_OVER);
		pinGroup.setScaleX(0.65);
		pinGroup.setScaleY(0.65);
		/*
		 * JOHN LOOK HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 */
		// Set coordinates of the whole pin group for use on the map.
		// e.g. Map coordinates for NYC are X = 255, Y = 108.
		pinGroup.setLayoutX(cityCoordinateX);
		pinGroup.setLayoutY(cityCoordinateY);

		return pinGroup;
	}

//	public String getCityName() {
//		pinMaker.
//		
//	}
}
