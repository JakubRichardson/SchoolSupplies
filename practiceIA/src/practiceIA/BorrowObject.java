package practiceIA;

public class BorrowObject {
	private String user;
	private int quantity;
	private String day;
	private String month;
	private String year;
	
	BorrowObject(String user, int quantity, String day, String month, String year) {
		this.user = user;
		this.quantity = quantity;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public String getDate() {
		return day + "/" + month + "/" + year;
	}
	
	public String toString() {
		return "{ user: " + user + ", quantity: " + quantity + ", day: " + day + ", month: " + month + ", year: " + year + " }";
	}
}
