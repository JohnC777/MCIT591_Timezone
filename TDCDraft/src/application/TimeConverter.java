package application;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;
//import java.util.TimeZone; 
//import java.util.Date.ZonedDateTime; 

public class TimeConverter {
	
	//private String InputDateTime; 
	private ZoneId FromTimeZone; 
	private ZoneId ToTimeZone;
	private LocalDateTime UserInputDateTime;
	private ZonedDateTime FromDateTime;
	private ZonedDateTime ToDateTime; 
	private String ToFormat; 
	private String FromFormat; 
	private DateTimeFormatter Formatter;
	
	/**
	 * Constructor
	 */
	public TimeConverter() {
		
		//Set Formatter
		FromFormat = "yyyy-MM-dd HH:mm:ss"; 
		ToFormat = "yyyy-MM-dd HH:mm:ss"; 
				// Alternative e.g.: "dd-MMM-yyyy hh:mm:ss a z"; 
		Formatter = DateTimeFormatter.ofPattern(ToFormat);
		
		ToDateTime = null; 
	}
	
	
	/**
	 * Converter: Converts time from FromZone to ToZone
	 * Parse in information from user input (e.g. map | date/time selection)
	 * @param inputDateTime
	 * @param fromZone
	 * @param toZone
	 */
	public void Convert(String inputDateTime, String zone1, String zone2) {
		
		FromTimeZone = ZoneId.of(zone1);
		ToTimeZone = ZoneId.of(zone2);
		
		// Convert String to LocalDateTime
		UserInputDateTime = LocalDateTime.parse(inputDateTime, DateTimeFormatter.ofPattern(FromFormat));

		// Assign Zone to LocalDateTime to create ZonedDateTime
		ZonedDateTime FromDateTime = UserInputDateTime.atZone(FromTimeZone);
		
		// Convert from zone1 to zone2 
		ZonedDateTime ToDateTime = FromDateTime.withZoneSameInstant(ToTimeZone);
		
		// Output and Print
		System.out.println("Time/Date at: " + "zone1: " + Formatter.format(FromDateTime));
		System.out.println("Time/Date at: " + "zone2: " + Formatter.format(ToDateTime));
		
	}
	
	public static void main(String[] args) {
		
		TimeConverter tc = new TimeConverter(); 
		tc.Convert("2019-04-17 08:00:00", "Australia/Adelaide", "US/Eastern");
		// Improvements and features: 
		// (a) User inputTimeDate selection: possibly some sort of Calendar pop-up? I need to learn more about this. 
		// (b) Timezone Selection: link to selectable Map; 
		// (c) Output linked to JavaFX Display 
		
	}
	
	
}
