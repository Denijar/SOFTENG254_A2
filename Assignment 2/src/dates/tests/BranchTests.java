package dates.tests;

import static dates.Dates.dayOfWeek;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import dates.Dates;
import dates.Dates.Day;

public class BranchTests {
	
	//===============================================================================
    //  			    Test that exercises object construction
    //===============================================================================
	/**
	 * Creates 2 date objects and verifies they are not considered the same
	 * New evaluations: reaches line 3
	 */
	@Test
	public void testDatesObjectConstruction() {
		Dates dates1 = new Dates();
		Dates dates2 = new Dates();
		assertNotEquals(dates1, dates2);
	}
	
	//===============================================================================
    //  			Tests that exercise leap-year and validity checks
    //===============================================================================
	/**
	 * Tests a year that is divisible by 400 and is too small to be valid.
	 * New evaluations: T line 48, T line 59.
	 */
	@Test
	public void testYearDiv400_YearSmallInvalid() {
		try {
			dayOfWeek(400, 10, 10);
			fail("IllegalArgumentException should have been thrown");
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid date: year=400, month=10, day=10");
		}
	}
	
	/**
	 * Tests a year that is only divisible by 100 and both month/day are negative
	 * New evaluations: F line 48, T line 50, F line 59, TT line 61
	 */
	@Test
	public void testYearDiv100_MonthDayNegativeInvalid() {
		try {
			dayOfWeek(2100, -1, -1);
			fail("IllegalArgumentException should have been thrown");
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid date: year=2100, month=-1, day=-1");
		}
	}
	
	/**
	 * Tests a year that is only divisible by 4 and only month is negative
	 * New evaluations: F line 50, T line 52, TF line 61
	 */
	@Test
	public void testYearDiv4_MonthNegativeInvalid() {
		try {
			dayOfWeek(2144, -1, 10);
			fail("IllegalArgumentException should have been thrown");
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid date: year=2144, month=-1, day=10");
		}
	}
	
	/**
	 * Tests a non-leap year and only day is negative
	 * New evaluations: F line 52, reaches 54, FT line 61
	 */
	@Test
	public void testLeapYear_DayNegativeInvalid() {
		try {
			dayOfWeek(2019, 10, -1);
			fail("IllegalArgumentException should have been thrown");
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid date: year=2019, month=10, day=-1");
		}
	}
	
	/**
	 * Tests month and day are positive, month is larger than 12
	 * New evaluations: FF line 61, T line 63
	 */
	@Test
	public void testMonthLargerThan12Invalid() {
		try {
			dayOfWeek(2019, 13, 10);
			fail("IllegalArgumentException should have been thrown");
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid date: year=2019, month=13, day=10");
		}
	}
	
	/**
	 * Tests leap year, month is February and day is too large to be valid
	 * New evaluations: F line 63, TT line 65, T line 66
	 */
	@Test
	public void testLeapYearFebruaryLargeDayInvalid() {
		try {
			dayOfWeek(2020, 2, 30);
			fail("IllegalArgumentException should have been thrown");
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid date: year=2020, month=2, day=30");
		}
	}
	
	/**
	 * Tests leap year, month is not February and day is invalid
	 * New evaluations: TF line 65, T line 70
	 */
	@Test
	public void testLeapYearNotFebruaryInvalidDay() {
		try {
			dayOfWeek(2020, 3, 40);
			fail("IllegalArgumentException should have been thrown");
		}
		catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid date: year=2020, month=3, day=40");
		}
	}
	
	/**
	 * Tests leap year, month is February and day is valid
	 * New evaluations: F line 66, TT line 96, T line 103
	 */
	@Test
	public void testLeapYearFebruary() {
		Day day = dayOfWeek(2020, 2, 28);
		assertEquals(day, Day.Friday);
	}
	
	
	/**
	 * Tests not leap year, month is not February and day is valid
	 * New evaluations: FF line 65, F line 70, FF line 96
	 */
	 
	@Test
	public void testNonLeapYearNotFebruary() {
		Day day = dayOfWeek(2019, 3, 30);
		assertEquals(day, Day.Saturday);
	}
	
	//===============================================================================
    //  							Last test needed
    //===============================================================================
	/**
	 * Tests leap year where the day is before Dooms Day
	 * New evaluations: TF line 96, F line 103
	 */
	@Test
	public void testLeapYearDateBeforeDoomsDay() {
		Day day = dayOfWeek(2020, 10, 2);
		assertEquals(day, Day.Friday);
	}
	
	
	
	
	
	
	


	

}
