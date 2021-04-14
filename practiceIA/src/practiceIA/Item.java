package practiceIA;

import java.util.ArrayList;
import java.util.Arrays;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Item {
	private String name;
	private String description;
	private String department;
	private int quantityAvailable;
	private ArrayList<BorrowObject> borrowed = new ArrayList<>();
	
	Item(String name, String description, String department, int quantityAvailable){
		this.name = name;
		this.description = description;
		this.department = department;
		this.quantityAvailable = quantityAvailable;
	}
	
	Item(String name, String description, String department, int quantityAvailable, BorrowObject[] borrowed){
		this.name = name;
		this.description = description;
		this.department = department;
		this.quantityAvailable = quantityAvailable;
		this.borrowed.addAll(Arrays.asList(borrowed));
	}
	
	Item(String name, String description, String department, int quantityAvailable, BorrowObject borrowed){
		this.name = name;
		this.description = description;
		this.department = department;
		this.quantityAvailable = quantityAvailable;
		this.borrowed.add(borrowed);
	}
	
	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public ArrayList<BorrowObject> getBorrowed() {
		return borrowed;
	}
	
	public boolean isAvailable() {
		if(quantityAvailable > 0) {
			return true;
		}
		return false;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public void addBorrowed(BorrowObject borrow) {
		this.borrowed.add(borrow);
	}
	
	public JsonObject toJson() {
		JsonObjectBuilder b = Json.createObjectBuilder();
		b.add("name", name);
		b.add("description", description);
		b.add("department", department);
		b.add("quantityAvailable", quantityAvailable);
		JsonArrayBuilder c = Json.createArrayBuilder();
		for(int i = 0; i < borrowed.size(); i++) {
			BorrowObject object = borrowed.get(i);
			c.add(Json.createObjectBuilder()
					.add("user", object.getUser())
					.add("quantity", object.getQuantity())
					.add("day", object.getDay())
					.add("month", object.getMonth())
					.add("year", object.getYear())
				);
		}
		b.add("borrowed", c);
		return b.build();
	}
	
	public static Item fromJson(JsonObject o) {
		String name = o.getString("name");
		String description = o.getString("description");
		String department = o.getString("department");
		int quantityAvailable = o.getInt("quantityAvailable");
		JsonArray borrowed = o.getJsonArray("borrowed");
		BorrowObject[] borrowValues = new BorrowObject[borrowed.size()];
		for(int i = 0; i < borrowed.size(); i++) {
			JsonObject object = borrowed.getJsonObject(i);
			String user = object.getString("user");
			int quantity = object.getInt("quantity");
			String day = object.getString("day");
			String month = object.getString("month");
			String year = object.getString("year");
			borrowValues[i] = new BorrowObject(user, quantity, day, month, year);
		}
		Item item = new Item(name, description, department, quantityAvailable, borrowValues);
		return item;
	}
	
	public String toString() {
		return "Item{ name: " + name + ", description: " + description + ", department: " + department + ", quantityAvailable: " + quantityAvailable + ", borrowed: " + borrowed + " }";
	}
	
}
