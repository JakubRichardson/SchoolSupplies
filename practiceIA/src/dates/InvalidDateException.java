package dates;

public class InvalidDateException extends Exception {
	
	InvalidDateException() {
		System.out.println("Invalid date");
	}
	
	InvalidDateException(String msg) {
		System.out.println(msg);
	}
	
	
	public String toString() {
		return "Attempted to created date with invalid arguments";
	}
}
