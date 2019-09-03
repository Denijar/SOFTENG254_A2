package dates.tests;

import static dates.Dates.dayOfWeek;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dates.Dates.Day;

public class DataflowTest {
	
	@Test
	public void dataFlowTest() {
		Day day = dayOfWeek(2400, 3, 30);
		assertEquals(day, Day.Thursday);
	}
	

}
