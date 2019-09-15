package dates.test;

import static dates.Dates.dayOfWeek;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import dates.Dates.Day;

/**
 * SOFTENG254 Assignment 2 Part 1
 * @author Denise Jarry 
 * ID: 199937702
 * 
 * BRANCH COVERAGE EXPLANATION
 * 
 * NOTE: There is 1 branch that is not covered: the branch that goes from line 92 directly to 102 is never followed. This means line 92 is still highlighted yellow with EMMA.
 * This is due to predicate number 11. See below for justification.
 * 
 * There are 15 predicate statements. The following list shows which Test exercises the TRUE and FALSE evaluation of each.
 * By showing that TRUE and FALSE for each predicate is covered, I have shown that each branch coming out of a predicate is followed.
 * This justification is supported by (almost, see predicate 11) full green coverage in EMMA.
 * 
 * I have referred to the tests as Test 1, Test 2 etc. for ease of presentation.
 * 
 * (1) Line 48) year % 400 == 0
 *		T: Test 1 (year is 400)
 *		F: Test 2 (year is 2100)
 *
 * (2) Line 50) year % 100 == 0
 * 		T: Test 2 (year is 2100)
 * 		F: Test 3 (year is 2144)
 * 
 * (3) Line 52) year % 4 == 0
 * 		T: Test 3 (year is 2144)
 * 		F: Test 4 (year is 2019)
 * 
 * (4) Line 59) year < 1753
 * 		T: Test 1 (year is 400)
 * 		F: Test 2 (year is 2100)
 * 
 * (5) Line 61) month <= 0 || day <= 0
 * 		T: Test 3 (month is -1)
 * 		F: Test 5 (month is 13 and day is 10)
 * 
 * (6) Line 63) month > 12
 * 		T: Test 5 (month is 13)
 * 		F: Test 6 (month is 2)
 * 
 * (7) Line 65) isLeapYear && month == 2
 * 		T: Test 6 (year is 2020 so 'isLeapYear' is set true on line 53 and month is 2)
 * 		F: Test 7 (month is 3)
 * 
 * (8) Line 66) day > (months[month-1]+1)
 * 		T: Test 6 (day is 30 and months[month-1]+1 evaluates to 29)
 * 		F: Test 8 (day is 28 and months[month-1]+1 evaluates to 29)
 * 
 * (9) Line 70) day > (months[month-1])
 * 		T: Test 7 (day is 40 and months[month-1] evaluates to 31)
 * 		F: Test 9 (day is 30 and months[month-1] evaluates to 31)
 * 
 * (10) Line 74) !valid
 * 		T: Test 1 (year is 400 so 'valid' is set false on line 60)
 * 		F: Test 8 (year/month/day are valid, so 'valid' is set true on line 58 and never changed)
 * 
 * (11) Line 92) (under the hood) i < doomsdaysByMonth.length
 * 		T: Test 8 (i initializes to 0 and the for loop is entered)
 * 		F: NEVER EVALUATED. The value of month at this point is always between 1 and 12 due to the checks done on lines 61 and 63. 
 * 							This means line 93 always evaluates TRUE eventually so i will never exceed doomsdaysByMonth.length. 
 * 							Thus the for loop only ever exits via the break statement on line 99.
 * 
 * (12) Line 93) month == monthDoomsday[1]
 * 		T: Test 8 (month is 2, so on the 3rd iteration of the for loop this is true)
 * 		F: Test 8 (month is 2, so on the 1st iteration of the for loop this is false)
 * 
 * (13) Line 96) isLeapYear && month <= 2
 * 		T: Test 8 (year is 2020 so 'isLeapYear' is set true on line 53 and month is 2)
 * 		F: Test 9 (year is 2019 so 'isLeapYear' is set false on line 47 and never changed)
 * 
 * (14) Line 103) position > 0
 * 		T: Test 8 (position is calculated as 5 on line 102)
 * 		F: Test 10 (position is calculated as -2 on line 102)
 * 
 * (15) Line 107) position < 0
 * 		T: Test 10 (position is calculated as -2 on line 102, this statement is reached)
 * 		F: Test 10 (position is incremented to 5 after the while loop is entered)
 * 
 * For further clarity: there are 3 else statements that need to be reached to ensure their branch is followed.
 * 
 * (1) Line 54)
 * 		Reached: Test 4 (year is 2019, so all previous if statements are evaluated false)
 * 
 * (2) Line 69)
 * 		Reached: Test 7 (all previous if statements are evaluated false)
 * 
 * (3) Line 105)
 * 		Reached: Test 10 (position is calculated as -2 so the previous if statement is evaluated false)
 * 
 * CONDITION/COMBINATORIAL COVERAGE EXPLANATION
 * 
 * There are 3 compound expressions where the different combinations of T and F for the clauses need to be exercised.
 * 
 * (1) Line 61) month <= 0 || day <= 0
 * 		TT: Test 2 (month is -1 and day is -1)
 * 		TF: Test 3 (month is -1 and day is 10) NOTE: this combination is not necessary as, due to short-circuit boolean evaluation, it is the same as TT
 * 		FT: Test 4 (month is 10 and day is -1)
 * 		FF: Test 5 (month is 13 and day is 10)
 * 
 * (2) Line 65) isLeapYear && month == 2
 * 		TT: Test 6 (year is 2020 so 'isLeapYear' is set true on line 53 and month is 2)
 * 		TF: Test 7 (year is 2020 so 'isLeapYear' is set true on line 53 and month is 3)
 * 		FT: This combination does not need to be exercised because, due to short-circuit boolean evaluation, it is the same as FF
 * 		FF: Test 9 (year is 2019 so 'isLeapYear' is set false on line 47 and never changed and month is 3)
 * 
 * (3) Line 96) isLeapYear && month <= 2
 * 		TT: Test 8 (year is 2020 so 'isLeapYear' is set true on line 53 and month is 2)
 * 		TF: Test 10 (year is 2020 so 'isLeapYear' is set true on line 53 and month is 10)
 * 		FT: This combination does not need to be exercised because, due to short-circuit boolean evaluation, it is the same as FF
 * 		FF: Test 9 (year is 2019 so 'isLeapYear' is set false on line 47 and never changed and month is 3)
 *
 */
public class BranchTests {
	
	//===============================================================================
    //  			Tests that exercise leap-year and validity checks
    //===============================================================================
	/**
	 * Test 1
	 * Year is divisible by 400
	 * Year is too small to be valid
	 * New evaluations: T line 48, T line 59, T line 74
	 */
	@Test
	public void yearDiv400_yearSmallInvalid() {
		try {
			dayOfWeek(400, 10, 5);
			fail("IllegalArgumentException should have been thrown");
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid date: year=400, month=10, day=5");
		}
	}
	
	/**
	 * Test 2
	 * Year is divisible by 100 (not 400)
	 * Month and day are negative and hence invalid
	 * New evaluations: F line 48, T line 50, F line 59, TT line 61
	 */
	@Test
	public void yearDiv100_monthDayNegativeInvalid() {
		try {
			dayOfWeek(2100, -1, -1);
			fail("IllegalArgumentException should have been thrown");
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid date: year=2100, month=-1, day=-1");
		}
	}
	
	/**
	 * Test 3
	 * Year is divisible by 4 (not 400 or 100)
	 * Month is negative and hence invalid
	 * New evaluations: F line 50, T line 52, TF line 61
	 */
	@Test
	public void yearDiv4_monthNegativeInvalid() {
		try {
			dayOfWeek(2144, -1, 10);
			fail("IllegalArgumentException should have been thrown");
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid date: year=2144, month=-1, day=10");
		}
	}
	
	/**
	 * Test 4
	 * Year is not a leap-year
	 * Day is negative and hence invalid
	 * New evaluations: F line 52, reaches 54, FT line 61
	 */
	@Test
	public void nonLeapYear_dayNegativeInvalid() {
		try {
			dayOfWeek(2019, 10, -1);
			fail("IllegalArgumentException should have been thrown");
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid date: year=2019, month=10, day=-1");
		}
	}
	
	/**
	 * Test 5
	 * Month is larger than 12 and hence invalid
	 * New evaluations: FF line 61, T line 63
	 */
	@Test
	public void monthLargerThan12Invalid() {
		try {
			dayOfWeek(2019, 13, 10);
			fail("IllegalArgumentException should have been thrown");
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid date: year=2019, month=13, day=10");
		}
	}
	
	/**
	 * Test 6
	 * Year is a leap-year
	 * Month is February
	 * Day is too large to be valid
	 * New evaluations: F line 63, TT line 65, T line 66
	 */
	@Test
	public void leapYear_february_dayLargeInvalid() {
		try {
			dayOfWeek(2020, 2, 30);
			fail("IllegalArgumentException should have been thrown");
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid date: year=2020, month=2, day=30");
		}
	}
	
	/**
	 * Test 7
	 * Year is a leap-year
	 * Month is not February
	 * Day is too large to be valid
	 * New evaluations: TF line 65, T line 70
	 */
	@Test
	public void leapYear_notFebruary_dayLargeInvalid() {
		try {
			dayOfWeek(2020, 3, 40);
			fail("IllegalArgumentException should have been thrown");
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid date: year=2020, month=3, day=40");
		}
	}
	
	//===============================================================================
    //  			     Tests that exercise method functionality
    //===============================================================================
	
	/**
	 * Test 8
	 * Year is a leap-year
	 * Month is February
	 * New evaluations: F line 66, T line 92, T line 93, F line 93, TT line 96, T line 103, F line 74
	 */
	@Test
	public void leapYear_february() {
		Day day = dayOfWeek(2020, 2, 28);
		assertEquals(day, Day.Friday);
	}
	
	
	/**
	 * Test 9
	 * Year is not a leap-year
	 * Month is not February
	 * Day is valid
	 * New evaluations: FF line 65, F line 70, FF line 96
	 */
	@Test
	public void nonLeapYear_notFebruary() {
		Day day = dayOfWeek(2019, 3, 30);
		assertEquals(day, Day.Saturday);
	}
	
	/**
	 * Test 10
	 * Year is a leap-year
	 * Day/Month combo is such that the day is before the doomsday date
	 * New evaluations: TF line 96, F line 103
	 */
	@Test
	public void leapYear_dayBeforeDoomsdayDate() {
		Day day = dayOfWeek(2020, 10, 2);
		assertEquals(day, Day.Friday);
	}

}
