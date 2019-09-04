package dates.tests;

import static dates.Dates.dayOfWeek;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dates.Dates;
import dates.Dates.Day;

/**
 * SOFTENG254 Assignment 2 Part 3
 * @author Denise Jarry
 * 
 * NEW EQUIVALENCE CLASS JUSTIFICATION
 * 
 * The input variable 'month' can be split into the following 5 equivalence classes:
 * 
 * (1) month <= 0
 * 
 * 	Why? The code include a specific test for month <= 0 (line 61). Months <= 0 are also invalid
 * 
 * (2) 0 < month < 2
 * (3) month = 2
 * (4) 2 < month <= 12
 * 
 * 	Why? The code treats month == 2 in a specific way (line 65), so this should be its own 
 * 		 class. Because equivalence classes need to be disjoint, the months thus need to be 
 * 		 split around month = 2.
 * 	
 * (5) 12 < month
 * 
 * 	Why? The code includes a specific test for month > 12 (line 63). Months > 12 are also invalid.
 * 
 * Currently, there is no test that includes a month input that belongs to equivalence class (2).
 * Every other equivalence class is covered except for this one.
 * 
 *
 */
public class EquivalenceTest {
	
	/**
	 * Year is not a leap year.
	 * Day and month values are valid
	 * Includes a month input value that belongs to the equivalence class 0 < month < 2 that has not
	 * been covered by any other test.
	 */
	@Test
	public void monthEquivalenceClass() {
		Day day = dayOfWeek(2019, 1, 10);
		assertEquals(day, Day.Thursday);
	}

}
