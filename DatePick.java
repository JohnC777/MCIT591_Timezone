import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DatePick extends Application {
	private Stage stage;
	private DatePicker locationDatePicker;
	private TimeSpinner spinner;
	private String date;
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		
		
		stage.setTitle("Date and Time Picker ");
		initDatePickerUI();
		stage.show();
		
	}
	
	private void initDatePickerUI() {
		VBox vbox = new VBox(20);
		vbox.setStyle("-fx-padding: 10;");
		Scene scene = new Scene(vbox, 400, 400);
		stage.setScene(scene);
		
		locationDatePicker = new DatePicker();
		
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		Label checkInLabel = new Label("Location 1 Date:");
		gridPane.add(checkInLabel, 0, 0);
		
		spinner = new TimeSpinner();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
		spinner.valueProperty().addListener((obs, oldTime, newTime) ->
				System.out.println(formatter.format(newTime)));
		
		GridPane.setHalignment(checkInLabel, HPos.LEFT);
		gridPane.add(locationDatePicker, 0, 1);
		gridPane.add(spinner, 1, 1);
		vbox.getChildren().add(gridPane);
		
	}
	
	public String getDate() {
		return date;
	}
	
	
	
}
