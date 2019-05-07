package application;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.util.StringConverter;

public class TimeConverter {

	// Input variables: set by JavaFx User Selection
	private ZoneId FromTimeZone;
	private ZoneId ToTimeZone;
	private String FromDatePicker;
	private String FromTimeSpinner;

	// "In-Process" Variables
	private String ToFormat;
	private String FromFormat;
	private DateTimeFormatter Formatter;
	private LocalDateTime UserInputDateTime;
	private ZonedDateTime FromDateTime;
	private ZonedDateTime ToDateTime;
	private String ToDateTimeString;
	private String FormattedInputDateTimeString;
	private String ToYYYY;
	private String ToM;
	private String ToD;

	// Output variables: feed back to JavaFx
	private String ToDatePicker;
	private String ToTimeSpinner;
	private int newYear;
	private int newMonth;
	private int newDay;
	private LocalTime newTime;

	/**
	 * Constructor
	 */
	public TimeConverter() {

		// Set Formatter
		FromFormat = "yyyy-MM-dd HH:mm";
		ToFormat = "yyyy-MM-dd HH:mm";
		Formatter = DateTimeFormatter.ofPattern(ToFormat);
		ToDateTime = null;
	}

	/**
	 * Converter: Converts time from FromZone to ToZone Pass in information from
	 * user input (map | date/time selection) through InputFormatter
	 * 
	 * @param inputDateTime
	 * @param fromZone
	 * @param toZone
	 */
	public String Convert(LocalDate fromDatePicker, LocalTime fromTimeSpinner, String zone1, String zone2) {
		// Take date and time sent from Pin and reformat
		FromDatePicker = fromDatePicker.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		FromTimeSpinner = fromTimeSpinner.format(DateTimeFormatter.ofPattern("HH:mm"));
		FormattedInputDateTimeString = FromDatePicker + " " + FromTimeSpinner;

		// Assign proper ZoneIds from zone names
		FromTimeZone = ZoneId.of(zone1);
		ToTimeZone = ZoneId.of(zone2);

		// Convert String to LocalDateTime
		UserInputDateTime = LocalDateTime.parse(FormattedInputDateTimeString, DateTimeFormatter.ofPattern(FromFormat));

		// Assign Zone to LocalDateTime to create ZonedDateTime
		ZonedDateTime FromDateTime = UserInputDateTime.atZone(FromTimeZone);

		// Convert from zone1 to zone2
		ZonedDateTime ToDateTime = FromDateTime.withZoneSameInstant(ToTimeZone);

		// Return formatted String
		ToDateTimeString = ToDateTime.format(Formatter);

		return ToDateTimeString;
	}

	/**
	 * Output Formatter for DatePicker (1) takes in a DateTimeString (output from
	 * Convert() method) (2) parses Integers from the String (3) sets integers to
	 * variables to be passed to set new DatePicker values
	 * 
	 * @param DateTimeString
	 */
	public void outputFormatterDatePicker(String DateTimeString) {

		// Step 1: separate D/M/YYYY and parse ints:
		int indexDash1 = DateTimeString.indexOf("-");
		int indexDash2 = DateTimeString.indexOf("-", DateTimeString.indexOf("-") + 1);
		int indexSpace = DateTimeString.indexOf(" ");

		ToYYYY = DateTimeString.substring(0, indexDash1);
		ToM = DateTimeString.substring(indexDash1 + 1, indexDash2);
		ToD = DateTimeString.substring(indexDash2 + 1, indexSpace);

		// Step 2: Adjust number of digit for Month and Day (-1)

		if (ToM.substring(0, 1).equals("0")) {
			ToM = ToM.substring(1);
		}
		if (ToD.substring(0, 1).equals("0")) {
			ToD = ToD.substring(1);
		}

		// Step 3: Cast as integers and set values.
		newYear = Integer.parseInt(ToYYYY);
		newMonth = Integer.parseInt(ToM);
		newDay = Integer.parseInt(ToD);

	}

	/**
	 * Output Formatter for JavaFx Time Spinner (1) takes in Java Date Time
	 * converted String (output from OutputFormatterString() method) (2) returns
	 * string in the LocalTime format required by TimeSpinner
	 * 
	 * @param DateTimeString
	 * @return LocalTime newTime
	 */
	public LocalTime outputFormatterTimeSpinner(String DateTimeString) {

		// Step 1: separate Time from Date
		int indexSpace = DateTimeString.indexOf(" ");
		ToTimeSpinner = DateTimeString.substring(indexSpace + 1);

		// Step 2: set up formatter
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

		// Step 3: set up format converter
		StringConverter<LocalTime> localTimeConverter = new StringConverter<LocalTime>() {

			@Override
			public String toString(LocalTime time) {
				return formatter.format(time);
			}

			@Override
			public LocalTime fromString(String string) {
				String[] tokens = string.split(":");
				int hours = getIntField(tokens, 0);
				int minutes = getIntField(tokens, 1);
				int seconds = getIntField(tokens, 2);
				int totalSeconds = (hours * 60 + minutes) * 60 + seconds;
				return LocalTime.of((totalSeconds / 3600) % 24, (totalSeconds / 60) % 60);
			}

			private int getIntField(String[] tokens, int index) {
				if (tokens.length <= index || tokens[index].isEmpty()) {
					return 0;
				}
				return Integer.parseInt(tokens[index]);
			}

		};

		// Step 4: use format converter and return string.
		newTime = localTimeConverter.fromString(ToTimeSpinner);

		return newTime;
	}

	// Settings (for input variables: from JavaFx)

	public void setFromTimeZone(ZoneId fromTimeZone) {
		FromTimeZone = fromTimeZone;
	}

	public void setToTimeZone(ZoneId toTimeZone) {
		ToTimeZone = toTimeZone;
	}

	public void setFromDatePicker(String fromDatePicker) {
		FromDatePicker = fromDatePicker;
	}

	public void setFromTimeSpinner(String fromTimeSpinner) {
		FromTimeSpinner = fromTimeSpinner;
	}

	// Getters (for output variables: to JavaFx)

	public ZoneId getFromTimeZone() {
		return FromTimeZone;
	}

	public ZoneId getToTimeZone() {
		return ToTimeZone;
	}

	public ZonedDateTime getFromDateTime() {
		return FromDateTime;
	}

	public ZonedDateTime getToDateTime() {
		return ToDateTime;
	}

	public String getToDatePicker() {
		return ToDatePicker;
	}

	public String getToTimeSpinner() {
		return ToTimeSpinner;
	}

	public int getNewYear() {
		return newYear;
	}

	public int getNewMonth() {
		return newMonth;
	}

	public int getNewDay() {
		return newDay;
	}

	public LocalTime getNewTime() {
		return newTime;
	}

//	public static void main(String[] args) {
//		
//		TimeConverter tc = new TimeConverter(); 
//		
//		// Test 1: Convert() method 
//		tc.Convert("2019-08-08 16:00:00", "US/Eastern", "Australia/Adelaide");
//
//		// Test 2: InputFormatter() method 
//		System.out.println(tc.InputFormatter("8/8/2019", "16:00:00"));
//		
//		// Test 3: OutputFormatterString() method 
//		System.out.println(tc.OutputFormatterString(tc.Convert("2019-08-08 16:00:00", "US/Eastern", "Australia/Adelaide")));		
//
//		// Test 4: OutputFormatterDatePicker() method 
//		//System.out.println(tc.OutputFormatterDatePicker(tc.OutputFormatterString(tc.Convert("2019-08-08 16:00:00", "US/Eastern", "Australia/Adelaide"))));		
//		
//		// Test 5: OutputFormatterTimeSpinner() method 
//		System.out.println(tc.OutputFormatterTimeSpinner(tc.OutputFormatterString(tc.Convert("2019-08-08 16:00:00", "US/Eastern", "Australia/Adelaide"))));		
//				
//		
//		 Improvements and features: 
//		 //(a) User inputTimeDate selection => should be ready to be linked up :-)
//		 //(b) Timezone Selection: link to selectable Map => getting there! 
//		 //(c) Output linked to JavaFX Display  => getting there! 
//		
//	}

}
