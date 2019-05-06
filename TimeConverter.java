import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;

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
	private String FromYYYY;
	private String FromMM;
	private String FromDD;
	private String ToYYYY;
	private String ToM;
	private String ToD;
	
	// Output variables: feed back to JavaFx
	private String ToDatePicker; 
	private String ToTimeSpinner; 

	
	/**
	 * Constructor
	 */
	public TimeConverter() {
		
		//Set Formatter
		FromFormat = "yyyy-MM-dd HH:mm:ss"; 
		ToFormat = "yyyy-MM-dd HH:mm:ss"; 
				// Alternative e.g.: "dd-MMM-yyyy hh:mm:ss a z"; 
		Formatter = DateTimeFormatter.ofPattern(ToFormat);
//		DateFormatterToString = new SimpleDateFormat(ToFormat);
		ToDateTime = null; 
	}
	
	/**
	 * Input Formatter 
	 * (1) Take in 2 Strings from JavaFx DatePicker and TimeSpinner 
	 * (2) formats and combine them, and 
	 * (3) return a formatted string recognized by Time Converter 
	 * @param fromDatePicker
	 * @param fromTimeSpinner
	 * @return
	 */
	public String InputFormatter(String fromDatePicker, String fromTimeSpinner) {
		FromDatePicker = fromDatePicker;
		FromTimeSpinner = fromTimeSpinner;
		
		//Step 1: separate out D/M/YYYY from FromDatePicker 
		
		int indexSlash1 = FromDatePicker.indexOf("/");
		int indexSlash2 = FromDatePicker.indexOf("/", FromDatePicker.indexOf("/") + 1);
				
		FromMM = FromDatePicker.substring(0, indexSlash1);
		FromDD = FromDatePicker.substring(indexSlash1 + 1, indexSlash2);
		FromYYYY = FromDatePicker.substring(indexSlash2 + 1);
				
		//Step 2: Adjust number of digit for Month and Day
		if (FromMM.length() == 1) {
			FromMM = "0" + FromMM;
		}
		if (FromDD.length() == 1) {
			FromDD = "0" + FromDD;
		}
		
		//Step 3: concatenate Date + Time strings 
		
		FormattedInputDateTimeString = FromYYYY + "-" + FromMM + "-" + FromDD + " " + fromTimeSpinner;
		// FromHH + ":" + FromMM + ":" + FromSS
				
		System.out.println(FormattedInputDateTimeString);
		return FormattedInputDateTimeString; 
	}
	
	/**
	 * Converter: Converts time from FromZone to ToZone
	 * Pass in information from user input (map | date/time selection) through InputFormatter 
	 * @param inputDateTime
	 * @param fromZone
	 * @param toZone
	 */
	public ZonedDateTime Convert(String inputDateTime, String zone1, String zone2) {
		
		FromTimeZone = ZoneId.of(zone1);
		ToTimeZone = ZoneId.of(zone2);
		
		// Convert String to LocalDateTime
		UserInputDateTime = LocalDateTime.parse(inputDateTime, DateTimeFormatter.ofPattern(FromFormat));

		// Assign Zone to LocalDateTime to create ZonedDateTime
		ZonedDateTime FromDateTime = UserInputDateTime.atZone(FromTimeZone);
		
		// Convert from zone1 to zone2 
		ZonedDateTime ToDateTime = FromDateTime.withZoneSameInstant(ToTimeZone);
		
		// Output and Print
		System.out.println("Time/Date at " + zone1 + ": " + Formatter.format(FromDateTime));
		System.out.println("Time/Date at " + zone2 + ": " + Formatter.format(ToDateTime));
		
		return ToDateTime;
	}
	
	/**
	 * Output Formatter String
	 * (1) takes in a ZonedDateTime (output from Convert() method)
	 * (2) formats it into a string
	 * (3) returns the formatted string (=> can be passed into Output Separator)
	 * @param toDateTime
	 * @return
	 */
	public String OutputFormatterString(ZonedDateTime toDateTime) {
		
		ToDateTimeString = toDateTime.format(Formatter);
		return ToDateTimeString;
		
	}
	

	/**
	 * Output Formatter JavaFx Date Picker
	 * (1) takes in Java Date Time converted String (output from OutputFormatterString() method)
	 * (2) returns string in the format required by DatePicker (M/D/YYYY) 
	 * @param DateTimeString
	 * @return
	 */
	public String OutputFormatterDatePicker(String DateTimeString) {	
		
		//Step 1: separate D/M/YYYY 
		
		int indexDash1 = DateTimeString.indexOf("-");
		int indexDash2 = DateTimeString.indexOf("-", DateTimeString.indexOf("-") + 1);
		int indexSpace =  DateTimeString.indexOf(" ");
				
		ToYYYY = DateTimeString.substring(0, indexDash1);
		ToM = DateTimeString.substring(indexDash1 + 1, indexDash2);
		ToD = DateTimeString.substring(indexDash2 + 1, indexSpace);
				
		//Step 2: Adjust number of digit for Month and Day (-1) 
		
		if (ToM.substring(0,1).equals("0")) {
			ToM = ToM.substring(1);
		}
		if (ToD.substring(0,1).equals("0")) {
			ToD = ToD.substring(1);
		}
		
		//Step 3: concatenate Date + Time strings 
		ToDatePicker = ToD + "/" + ToM + "/" + ToYYYY;
		
		return ToDatePicker;
	}
	
	/**
	 * Output Formatter for JavaFx Time Spinner
	 * (1) takes in Java Date Time converted String (output from OutputFormatterString() method)
	 * (2) returns string in the format required by TimeSpinner
	 * @param DateTimeString
	 * @return
	 */
	public String OutputFormatterTimeSpinner(String DateTimeString) {
		
		// Step 1: separate Time from Date
		int indexSpace =  DateTimeString.indexOf(" ");
		ToTimeSpinner = DateTimeString.substring(indexSpace + 1);
				
		return ToTimeSpinner;
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


	public static void main(String[] args) {
		
		TimeConverter tc = new TimeConverter(); 
		
		// Test 1: Convert() method 
		tc.Convert("2019-08-08 16:00:00", "US/Eastern", "Australia/Adelaide");

		// Test 2: InputFormatter() method 
		System.out.println(tc.InputFormatter("8/8/2019", "16:00:00"));
		
		// Test 3: OutputFormatterString() method 
		System.out.println(tc.OutputFormatterString(tc.Convert("2019-08-08 16:00:00", "US/Eastern", "Australia/Adelaide")));		

		// Test 4: OutputFormatterDatePicker() method 
		System.out.println(tc.OutputFormatterDatePicker(tc.OutputFormatterString(tc.Convert("2019-08-08 16:00:00", "US/Eastern", "Australia/Adelaide"))));		
		
		// Test 5: OutputFormatterTimeSpinner() method 
		System.out.println(tc.OutputFormatterTimeSpinner(tc.OutputFormatterString(tc.Convert("2019-08-08 16:00:00", "US/Eastern", "Australia/Adelaide"))));		
				
		
		// Improvements and features: 
		// (a) User inputTimeDate selection => should be ready to be linked up :-)
		// (b) Timezone Selection: link to selectable Map => getting there! 
		// (c) Output linked to JavaFX Display  => getting there! 
		
	}
	
	
}
