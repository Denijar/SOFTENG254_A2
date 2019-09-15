package dates.tests;

import static dates.Dates.dayOfWeek;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dates.Dates.Day;

/**
 * SOFTENG254 Assignment 2 Part 3
 * @author Denise Jarry
 * ID: 199937702
 * 
 * NEW EQUIVALENCE CLASS EXPLANATION
 * 
 * Test input: February 29th, 2020
 * 
 * There are multiple ways to justify this as being part of a previously un-tested equivalence class:
 * 
 * 
 * Justification 1)
 * 
 * February 29th on a leap-year is clearly a unique input because this day doesn't exist in non-leap years.
 * This means that the input of Feburary 29th 2020 belongs to its own equivalence class. Other members of this class would include February 29th
 * for different leap years (Feb 29th 2000, Feb 29th 2008 etc.) In general, this class is "Feburary 29th for a leap-year".
 * One would expect values from this class to have the same behaviour in the IUT, and this behaviour would be different to other equivalence class inputs.
 *    
 * This test improves the quality of the test suite because, due to the unqiueness of this input, it is possible for a developer to forget to deal with it.
 * The implementation could incorrectly throw an IllegalArgumentException because it thinks February 29th doesn't exist, or could throw a NullPointerException
 * if there is some iteration that doesn't account for this input value.
 *    
 * There is no other test that has: Year is leap year, Month = 2 and Day = 29. So, this class is not represented by other tests.
 *  
 *  
 * Justification 2) 
 * 
 * The input variables Day and Month could be split into the following 3 equivalence classes:
 * 		input day is BEFORE the doomsday of that month
 * 		input day is ON the doomsday of that month
 * 		input day is AFTER the dommsday of that month
 *    
 * This input belongs to the equivalence class "input day is ON the doomsday of that month." When it is a leap-year, the doomsday for February is the 29th.
 *    
 * This test improves the quality of the test suite because there is a specific variable "days2dd" that represents how many days our input day is away from
 * the month's doomsday. If the calculation or usage of this variable is faulty, then having tests from all 3 of these equivalence classes would increase the 
 * chance of a failure propagating to the output.
 *    
 * If one considers the 2D array doomsdaysByMonth (and remembers that the day value should be incremented by one if the input year is a leap-year), then clearly
 * there is no other test where the input belongs to the class "input day is ON the doomsday of that month"
 * 
 *
 */
public class EquivalenceTest {
	
	/**
	 * Year is a leap year.
	 * Month is February
	 * Day is 29th
	 * Includes an input value belonging to an equivalence class that has not yet been tested
	 */
	@Test
	public void leapYear_february() {
		Day day = dayOfWeek(2020, 2, 29);
		assertEquals(day, Day.Saturday);
	}

}
