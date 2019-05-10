package application;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

class TimeConverterTest {

	/**
	 * Testing Convert Method: various functionalities 
	 */
	TimeConverter tc1 = new TimeConverter();
	
	@Test
	void testConvertAdelaide() {
		LocalDate date = LocalDate.of(2019, 5, 8);
		LocalTime time = LocalTime.of(18, 30);
		assertEquals(tc1.Convert(date, time, "US/Eastern", "Australia/Adelaide"), "2019-05-09 08:00"); 
	}
	// Cross International Date Line (+1 day)
	
	@Test
	void testConvertSanFrancisco() {
		LocalDate date = LocalDate.of(2019, 1, 1);
		LocalTime time = LocalTime.of(0, 0);
		assertEquals(tc1.Convert(date, time, "US/Eastern", "US/Pacific"), "2018-12-31 21:00");
	}
	// working with midnight 
	
	@Test
	void testConvertLondon() {
		LocalDate date = LocalDate.of(2019, 10, 31);
		LocalTime time = LocalTime.of(23, 59);
		assertEquals(tc1.Convert(date, time, "Europe/London", "US/Eastern"), "2019-10-31 19:59");
	}
	// same day 
	
	@Test
	void testConvertTokyo() {
		LocalDate date = LocalDate.of(2019, 4, 1);
		LocalTime time = LocalTime.of(6, 45);
		assertEquals(tc1.Convert(date, time, "US/Eastern", "Asia/Tokyo"), "2019-04-01 19:45");
	}
	
	@Test
	void testConvertTehran() {
		LocalDate date = LocalDate.of(2019, 11, 11);
		LocalTime time = LocalTime.of(3, 0);
		assertEquals(tc1.Convert(date, time, "Asia/Tehran", "America/Santiago"), "2019-11-10 20:30");
	}
	// Cross International Date Line (-1 day)
	
	/**
	 * Test OutputFormatterDatePicker Method
	 */
	@Test
	void testOutputFormatterDatePicker1() {
		String input = "2019-05-31 18:30";
		tc1.outputFormatterDatePicker(input);
		assertEquals(tc1.getNewYear(), 2019);
		assertEquals(tc1.getNewMonth(), 5);
		assertEquals(tc1.getNewDay(), 31);
	}
	
	@Test
	void testOutputFormatterDatePicker2() {
		String input = "2019-12-01 00:00";
		tc1.outputFormatterDatePicker(input);
		assertEquals(tc1.getNewYear(), 2019);
		assertEquals(tc1.getNewMonth(), 12);
		assertEquals(tc1.getNewDay(), 1);
	}
	
	
	/**
	 * Test OutputFormatterTimeSpinner Method
	 */
	
	@Test
	void testOutputFormatterTimeSpinner1() {
		String input = "2019-01-01 09:12";
		LocalTime time = LocalTime.of(9, 12);
		assertEquals(tc1.outputFormatterTimeSpinner(input), time);
	}
	
	void testOutputFormatterTimeSpinner2() {
		String input = "2019-12-01 11:03";
		LocalTime time = LocalTime.of(11, 3);
		assertEquals(tc1.outputFormatterTimeSpinner(input), time);
	}

	
}
