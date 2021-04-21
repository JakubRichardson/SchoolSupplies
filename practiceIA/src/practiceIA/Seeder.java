package practiceIA;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

public class Seeder {
	private Item[] presetItems = new Item[] {
			new Item("Dell laptop", "Laptop", "Computer Science", 10,  new BorrowObject[] {new BorrowObject("John Montey", 2, "12", "12", "2020"), new BorrowObject("Bob the builder", 2, "12", "12", "2020")}),
			new Item("Flathead Screwdriver", "Flathead Screwdriver", "Art", 15, new BorrowObject[] {new BorrowObject("Jill Jackson", 3, "12", "12", "2020"), new BorrowObject("Corey Taylor", 2, "12", "12", "2020")}),
			new Item("Green Paint", "Green acrylic paint", "Art", 2,  new BorrowObject[] {new BorrowObject("Carl Tompson", 1, "12", "12", "2020"), new BorrowObject("Candy man", 2, "12", "12", "2020")}),
			new Item("Micrometer", "Micrometer", "Physics", 0,  new BorrowObject[] {new BorrowObject("Johnny english", 20,  "12", "12", "2020"), new BorrowObject("Tom scott", 2, "12", "12", "2020")})
	};

	private ArrayList<Item> inventory = new ArrayList<>();

	Seeder() {
		inventory.addAll(Arrays.asList(presetItems));
	}

	public static void main(String[] args) {
		
		Seeder seeder = new Seeder();
		
		Path savePath = JsonWorker.getSavePath();
	
		try {
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(); 
			for(Item item: seeder.inventory) {
				arrayBuilder.add(item.toJson());
			}
			JsonArray array = arrayBuilder.build();
			FileOutputStream oStream = new FileOutputStream(savePath.toFile());
			Json.createWriter(oStream).writeArray(array);
			
		} catch(FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
