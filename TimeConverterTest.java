import static org.junit.jupiter.api.Assertions.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class TimeConverterTest {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	// Test Convert Method 
	@Test
	void ConvertAdelaide() {
		TimeConverter tc1 = new TimeConverter(); 
		assertEquals(tc1.Convert("2018-05-05 16:00:00", "US/Eastern", "Australia/Adelaide").format(formatter), "2018-05-06 05:30:00"); 
	}
	
	@Test
	void ConvertHonolulu() {
		TimeConverter tc1 = new TimeConverter(); 
		assertEquals(tc1.Convert("2018-05-07 08:00:00", "US/Eastern", "US/Hawaii").format(formatter), "2018-05-07 02:00:00"); 
	}
	
	@Test
	void ConvertChicago() {
		TimeConverter tc1 = new TimeConverter(); 
		assertEquals(tc1.Convert("2018-05-07 00:00:00", "US/Eastern", "US/Central").format(formatter), "2018-05-06 23:00:00"); 
	}
	
	// Test Print Method 

}
