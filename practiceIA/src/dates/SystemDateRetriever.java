package dates;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SystemDateRetriever {
	int day;
	int month;
	int year;
	
	SystemDateRetriever() {
		this.updateDate();
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
	
	public void updateDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.now();
		String[] dateParts = date.format(formatter).split("-");
		day = Integer.parseInt(dateParts[0]);
		month = Integer.parseInt(dateParts[1]);
		year = Integer.parseInt(dateParts[2]);
	}
}
