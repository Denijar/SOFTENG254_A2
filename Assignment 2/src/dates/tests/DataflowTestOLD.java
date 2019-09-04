package dates.tests;

import static dates.Dates.dayOfWeek;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dates.Dates.Day;

/**
 * SOFTENG254 Assignment 2 Part 2
 * @author Denise Jarry
 * 
 * NEW DU-PATH JUSTIFICATION
 * 
 * It should be noted that only Tests 8, 9 and 10 from dates.tests.BranchTests DON'T terminate on line 75 by throwing an exception.
 * So, when justifying that my du-path is new, I only need to compare with these 3 tests. The other tests never even reach line 79.
 * 
 * DU-PATH FROM NEW TEST
 * 
 * Variable Considered: year
 * DEF: Line 46 (parameter definition)
 * USE: Line 79 (int centuryNumber = year/100 + 1)
 * 
 * NEW DU-PATH: 46(entry), 47, 48, 49, 58, 59, 61, 63, 65, 69, 70, 74, 79
 * 
 * Explanation why the test follows this path (why is each line reached):
 * 		46) YEAR IS DEFINED. dayOfWeek is invoked in the Test.
 * 		47) follows from 46
 * 		48) follows from 47
 * 		49) 2400 is divisible by 400, so the if statement on 48 is evaluated to true
 * 		58) the if/else block is exited so this line is reached
 * 		59) follows from 58
 * 		61) the if statement on line 59 is evaluated to false
 * 		63) the if statement on line 61 is evaluated to false
 * 		65) the if statement on line 63 is evaluated to false
 * 		69) the if statement on line 65 is evaluated to false
 * 		70) follows from line 69
 * 		74) the if statement on line 69 is evaluated to false, the if/else block is exited
 * 		79) the if statement on line 74 is evaluated to false. YEAR IS USED.
 * 
 * DU-PATHS FROM EXISTING TESTS
 * 
 * Compare this to the du-paths followed between these two points in the three tests from dates.tests.BranchTests
 * 
 * Test 8 du-path: 46(entry), 47, 48, 50, 52, 53, 58, 59, 61, 63, 65, 66, 74, 79
 * 
 * Explanation why the test follows this path:
 * 		46) YEAR IS DEFINED. dayOfWeek is invoked in the Test.
 * 		47) follows from 46
 * 		48) follows from 47
 * 		50) the if statement on line 48 is evaluated to false
 * 		52) the if statement on line 50 is evaluated to false
 * 		53) 2020 is divisible by 4, so the if statement on 52 is evaluated to true
 * 		58) the if/else block is exited so this line is reached
 * 		59) follows from 58
 * 		61) the if statement on line 59 is evaluated to false
 * 		63) the if statement on line 61 is evaluated to false
 * 		65) the if statement on line 63 is evaluated to false
 * 		66) year is 2020, so isLeapYear was set true on line 53 and month is 2, so line 65 if statement is evaluated to true
 * 		74) if/else block is exited
 * 		79) the if statement on line 74 is evaluated to false. YEAR IS USED
 * 		
 * Test 9 du-path: 46(entry), 47, 48, 50, 52, 54, 55, 58, 59, 61, 63, 65, 69, 70, 74, 79
 * 
 * Explanation why the test follows this path:
 * 		46) YEAR IS DEFINED. dayOfWeek is invoked in the Test.
 * 		47) follows from 46
 * 		48) follows from 47
 * 		50) the if statement on line 48 is evaluated to false
 * 		52) the if statement on line 50 is evaluated to false
 * 		54) the if statement on line 52 is evaluated to false
 * 		55) follows from line 54
 * 		58) the if/else blocke is exited so this line is reached
 * 		59) follows from 58
 * 		61) the if statement on line 59 is evaluated to false
 * 		63) the if statement on line 61 is evaluated to false
 * 		65) the if statement on line 63 is evaluated to false
 * 		69) the if statement on line 65 is evaluated to false 
 * 		70) follows from line 69
 * 		74) the if statement on line 69 is evaluated to false, the if/else block is exited
 * 		79) the if statement on line 74 is evaluated to false. YEAR IS USED.
 * 
 * Test 10 du-path: 46(entry), 47, 48, 50, 52, 53, 58, 59, 61, 63, 65, 69, 70, 74, 79
 * 
 * Explanation why the test follows this path:
 * 		46) YEAR IS DEFINED. dayOfWeek is invoked in the Test.
 * 		47) follows from 46
 * 		48) follows from 47
 * 		50) the if statement on line 48 is evaluated to false
 * 		52) the if statement on line 50 is evaluated to false
 * 		53) 2020 is divisible by 4, so the if statement on 52 is evaluated to true
 * 		58) if/else block is exited
 * 		59) follows from line 58
 * 		61) the if statement on line 59 is evaluated to false
 * 		63) the if statement on line 61 is evaluated to false
 * 		65) the if statement on line 63 is evaluated to false
 * 		69) the if statement on line 65 is evaluated to false 
 * 		70) follows from line 69
 * 		74) the if statement on line 69 is evaluated to false, the if/else block is exited
 * 		79) the if statement on line 74 is evaluated to false. YEAR IS USED.
 * 
 * JUSTIFICATION
 * 
 * Clearly the path followed by this test is unique as the same du-path is not followed by any test in dates.tests.BranchTests
 * 
 * To explain in words why it is unique: there is no existing test where 'year' is divisible by 400 and execution reaches line 79.
 * 
 * This improves the quality of the test suite because, before introducing this test, there is no test that observes
 * the flow from year being defined on line 46, line 49 being reached, and year being used on line 79. It is possible for a fault
 * to lie on line 49 that could make the use of 'year' on line 79 be incorrect.
 * 
 *
 */
public class DataflowTestOLD {
	
	/**
	 * Year is divisible by 400.
	 * Day and month values are valid.
	 * Introduces a DU-Path wrt. variable year between lines 46 and 79 that is not followed by any test in dates.tests.BranchTests
	 */
	@Test
	public void yearVariableDUPath() {
		Day day = dayOfWeek(2400, 3, 30);
		assertEquals(day, Day.Thursday);
	}
	

}
