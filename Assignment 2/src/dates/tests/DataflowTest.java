package dates.tests;

import static dates.Dates.dayOfWeek;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dates.Dates.Day;

/**
 * SOFTENG254 Assignment 2 Part 2
 * @author Denise Jarry
 * ID: 199937702
 * 
 * NEW DU-PATH EXPLANATION
 * 
 * DU-PATH FROM NEW TEST
 * 
 * Variable Considered: isLeapYear
 * DEF: Line 49 (isLeapYear = true)
 * USE: Line 65 (isLeapYear && month == 2)
 * 
 * NEW DU-PATH: 49, 58, 59, 61, 63, 65
 * 
 * Explanation why the test (dates.tests.DataflowTest#isLeapYearVariableDUPath) follows this path (i.e. why each line of the path is reached):
 * 		49) isLeapYear is DEFINED. 2400 is divisible by 400, so the if statement on line 48 is evaluated to true
 * 		58) the if/else block is exited after line 49, so this line is reached
 * 		59) follows from 58
 * 		61) the if statement on line 59 is evaluated to false (year is greater than 1753)
 * 		63) the if statement on line 61 is evaluated to false (both month and day are greater than 0)
 * 		65) the if statement on line 63 is evaluated to false (month is less than 12). isLeapYear is USED
 * 
 * JUSTIFICATION WHY THIS IS UNIQUE
 * 
 * There is only one test from dates.tests.BranchTests that reaches line 49: Test 1, a.k.a. yearDiv400_yearSmallInvalid().
 * This is because this is the only test from that class where the year input is divisible by 400.
 * The path followed by this test DOES NOT reach line 65 because the if statement on line 59 is evaluated to true (because
 * the year is less than 1753), so the if/else block is exited before line 65.
 * 
 * The test from class dates.tests.EquivalenceTest also doesn't reach line 49 because the year is not divisible by 400.
 * 
 * This means that the du-path followed from line 49 to 65 by the test isLeapYearVariableDUPath() is definitely unique.
 * 
 * JUSTIFICATION WHY THIS IMPROVES TEST SUITE QUALITY
 * 
 * This test improves test suite quality because, before introducing this test, there is no test that follows the path from
 * isLeapYear being defined on line 49 to it being used on line 65. It is possible for a fault to lie on line 49 or line 65, so we want
 * to test this particular combination of definition followed by use to increase the chance of a failure propagating to the output.
 */
public class DataflowTest {
	
	/**
	 * Year is divisible by 400.
	 * Day and month values are valid.
	 * Introduces a DU-Path wrt. variable isLeapYear between lines 49 and 65 that is not followed by any other test
	 */
	@Test
	public void isLeapYearVariableDUPath() {
		Day day = dayOfWeek(2400, 3, 30);
		assertEquals(day, Day.Thursday);
	}
	

}
