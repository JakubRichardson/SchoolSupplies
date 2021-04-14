package dates;

// assume minimum date 1753

//add getting system date
//add update date from system

public class Date {
	private static final int[] MONTHS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; 
	int day;
	int month;
	int year;
	public Date(int day, int month, int year) throws InvalidDateException {
		if(!checkValid(day, month, year)) {
			throw new InvalidDateException();
		}
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public Date() throws InvalidDateException{
		this.update(); // sets date form system
	}

	public void next() {
		day++;

		if(month == 2 && day == 29) {
			if(isLeap(year)) {
				return;
			}
		}

		if(day > MONTHS[month -1]) {
			if(month >= 12) {
				month = 1;
				year++;
			} else {
				month++;
			}
			day = 1;
		}
	}

	public void prev() throws InvalidDateException {
		day--;

		if(day > 0) return;
		
		if(month == 1) {
			day = 31;
			month = 12;
			year--;
			if(year <= 1752) {
				throw new InvalidDateException("Going into gregorian calendar");
			}
			return;
		} else if (month == 3) {
			if(isLeap(year)) {
				day = 29;
			} else {
				day = 28;
			}
		} else {
			day = MONTHS[month - 2];
		}
		month--;
	}

	public void update() throws InvalidDateException {
		SystemDateRetriever date = new SystemDateRetriever();
		int day = date.getDay();
		int month = date.getMonth();
		int year = date.getYear();
		if(!checkValid(day, month, year)) {
			throw new InvalidDateException("Invalid system date");
			//			System.out.println("Invalid system date");
			//			System.out.println("Setting default date: " + );
		}
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public boolean isLeap(int year) {
		if(year % 4 == 0) {
			if(year % 100 == 0) {
				if(year % 400 == 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	public String getMinDate() {
		return "01/01/1753";
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public String getDate() {
		return this.toString();
	}

	public String dayStringHelper() {
		if(day < 10) {
			return "0" + Integer.toString(day);
		} else {
			return Integer.toString(day);
		}
	}

	public String monthStringHelper() {
		if(month < 10) {
			return "0" + Integer.toString(month);
		} else {
			return Integer.toString(month);
		}
	}

	public String yearStringHelper() {
		if(year < 10) {
			return "000" + Integer.toString(year);
		} else if (year < 100) {
			return "00" + Integer.toString(year);
		} else if (year < 1000) {
			return "0" + Integer.toString(year);
		} else {
			return Integer.toString(year);
		}
	}

	public boolean checkValid(int day, int month, int year) {
		if(year >= 1753) {
			if(month <= 12 && month > 0) {
				if(month <= 12 && month > 0) {
					if(day <= MONTHS[month - 1] && day > 0) {
						return true;
					} else if (month == 2 && day == 29) {
						if(isLeap(year)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public String toString() {
		String str = dayStringHelper() + "/" + monthStringHelper() + "/" + yearStringHelper();
		return str;
	}

	public String dayOfWeek() {
		String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}; 
		return days[dayOfWeekInt()];
	}

	public int dayOfWeekInt() {
		int[] t = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
		int y = year;
		y -= (month < 3) ? 1 : 0; 
		return (y + y/4 - y/100 + y/400 + t[month - 1] + day) % 7;
	}
}