package practiceIA;

public enum Damaged {
	FALSE("Not Damaged"),
	TRUE("Damaged"),
	UNKNOWN("Unknown");
	
	private String text;
	
	Damaged(String text) {
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
}
