package dates;

public class Dates {
	public enum Day { Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday };

	/** 
	 * Doomsday dates (as day,month pairs). Each of these dates falls on the same day of 
	 * the week (the "doomsday" in any given year. If you know what that day is, then the 
	 * day of the week any other date falls on can be computed by counting days forwards 
	 * or backwards to one of these days.
	 * For example, the doomsday for 2001 is Wednesday. The date 2001-07-03 is
	 * 8 days before the doomsday date for July, so 2001-07-03 must fall on
	 * a Tuesday (count back 8 days from Wednesday).   Taken from the 
	 * <a href="http://en.wikipedia.org/wiki/Doomsday_rule">Wikipedia Doomsday Rule article</a>
	 * as of 2012-08-07.
	 */
	private static final int[][] doomsdaysByMonth = { // Yes I know (month,day) would have made things easier!
		{ 10,  1 }, // 11,1 for leap year
		{ 28,  2 }, // 29,2 for leap year
		{  7,  3 },
		{  4,  4 },
		{  9,  5 },
		{  6,  6 },
		{ 11,  7 },
		{  8,  8 },
		{  5,  9 },
		{ 10, 10 },
		{  7, 11 },
		{ 12, 12 }
	};
	/**
	 * Number of days in a the month (non leap year)
	 */
	private static final int[] months = {31,28,31,30,31,30,31,31,30,31,30,31}; // L = 30 + { [ M + floor(M/8) ] MOD 2 },
	
	/**
	 * Determine the day of the week the date specified by the parameters falls
	 * on for any date from 1 January 1753 onwards (chosen to reduce issues dealing
	 * with the conversion from Julian to Gregorian calendars).
	 * @param year The year of the date.
	 * @param month The month of the date.
	 * @param day The day of the date.
	 * @return The day of the week the date falls on.
	 * @throws IllegalArgumentException if the date is invalid or before 1753-01-01
	 */
	public static Day dayOfWeek(int year, int month, int day) {
		boolean isLeapYear = false;
		if (year % 400 == 0) {
			isLeapYear = true;
		} else if (year % 100 == 0) {
			isLeapYear = false;
		} else if (year % 4 == 0) {
			isLeapYear = true;
		} else {
			isLeapYear = false;
		}
		// Check whether date is valid - depends on whether leap year or not
		boolean valid = true;
		if (year < 1753) {
			valid = false;
		} else if (month <= 0 || day <= 0) {
			valid = false;
		} else if (month > 12) {
			valid = false;
		} else if (isLeapYear && month == 2) {
			if (day > (months[month-1]+1)) {
				valid = false;
			}
		} else {
			if (day > (months[month-1])) {
				valid = false;
			}
		}
		if (!valid) {
			throw new IllegalArgumentException("Invalid date: year=" + year + ", month=" + month + ", day=" + day);
		}
		
		// Determine anchor day for century
		int centuryNumber = year/100 + 1; // First two digits + 1
		int pCN = (Day.Thursday.ordinal() + (((centuryNumber*5) + (centuryNumber - 1)/4) % 7)) % 7; // Day of the week
		Day anchor = Day.values()[pCN];   // Actual day (could leave it as day of week)
		
		// Determine doomsday for this year.
		int y = year % 100; // last two digits of year
		int dDD = ((y/12) /*a*/ + (y%12) /*b*/ + ((y%12)/4)/*c*/) % 7;
		int pDD = (anchor.ordinal() + dDD) % 7; // Day of the week
		Day doomsDay = Day.values()[pDD];       // Actual day
		
		// Compute the number of days from the date in question to the nearest doomsday date
		// First find the doomsday date for the month in question
		int days2dd = 0; // The number of days difference between the date in question and the doomsday date day of the month
		for (int[] monthDoomsday: doomsdaysByMonth) {
			if (month == monthDoomsday[1]) {
				days2dd = day - monthDoomsday[0];
				// Deal with the doomsday dates for leap years
				if (isLeapYear && month <= 2) {
					days2dd--;
				}
				break; // Found the month
			}
		}
		int position = doomsDay.ordinal() + days2dd; // Count forward or backward from doomsday day
		if (position > 0) {
			position = position % 7;
		} else {
			// Need to deal with when day is before doomsday date
			while (position < 0) {
				position += 7;
			}
		}
		return Day.values()[position];
	}
}
