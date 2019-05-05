import static org.junit.jupiter.api.Assertions.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class TimeConverterTest {

	@Test
	void testConvert() {
		TimeConverter tc1 = new TimeConverter(); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		assertEquals(tc1.Convert("2018-05-05 16:00:00", "US/Eastern", "Australia/Adelaide").format(formatter), "2018-05-06 05:30:00"); 
	}

}
