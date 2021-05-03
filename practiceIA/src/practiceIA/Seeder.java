package practiceIA;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
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
			new Item("Micrometer", "Micrometer", "Physics", 0,  new BorrowObject[] {new BorrowObject("Johnny english", 20,  "12", "12", "2020"), new BorrowObject("Tom scott", 2, "12", "12", "2020")}),
			new Item("Gibson Les Paul electric guitar", "Gibson 1959 sunburst les paul standard electric guitar", "Music", 0,  new BorrowObject[] {new BorrowObject("Musician", 1, "12", "12", "2022")}),
			new Item("Music Man", "John Petrucci Music Man Electric Guitar", "Music", 2,  new BorrowObject[] {new BorrowObject("Mr Gordon", 2, "20", "11", "2021")}),
			new Item("Yellow Paint", "Yellow acrylic paint", "Art", 12, new BorrowObject[] {new BorrowObject("James Colton", 3, "12", "12", "2023"), new BorrowObject("Mrs Greggs", 2, "10", "4", "2022")}),
			new Item("Football", "White asics football", "PE", 10,  new BorrowObject[] {new BorrowObject("Carl Tompson", 1, "10", "1", "2022"), new BorrowObject("Candy man", 12, "12", "12", "2020")}),
			new Item("IB Maths HL AA textbook", "IB Maths HL AA textbook", "Maths", 0,  new BorrowObject[] {new BorrowObject("Dr Wilson", 20,  "12", "12", "2020"), new BorrowObject("Bill Nye", 2, "12", "12", "2022")}),
			new Item("200ml Beaker", "A 200 ml clear pyrex beaker", "Chemistry", 10,  new BorrowObject[] {new BorrowObject("Bob the builder", 2, "12", "12", "2020"), new BorrowObject("Thomas the tank engine", 2, "12", "12", "2020"), new BorrowObject("Nemo", 2, "12", "12", "2020"), new BorrowObject("Dory", 2, "12", "12", "2020"), new BorrowObject("Elmo", 2, "12", "12", "2020")}),
			new Item("Mercury Thermometer", "Glass Mercury Thermometer", "Physics", 15,  new BorrowObject("Cookie monster", 3, "12", "12", "2020")),
			new Item("Oscilloscope", "Large black oscilloscope", "Physics", 2,  new BorrowObject("Curious George", 1, "12", "12", "2020")),
			new Item("Network switch", "4 port 10Gbit switch", "Computer Science", 1,  new BorrowObject("Curious George", 1, "12", "12", "2020")),
			new Item("Sony Headphones", "Sony headphones", "Computer Science", 5,  new BorrowObject("Mr Potato", 1, "12", "12", "2020")),
			new Item("Raspberry Pi 4 8GB", "Raspberry Pi 4 8GB", "Computer Science", 20,  new BorrowObject("Curious George", 1, "12", "12", "2020")),
			new Item("Black BIC Pen", "Black BIC pen", "Art", 2,  new BorrowObject("Arthur", 1, "12", "12", "2020")),
			new Item("Tuning fork", "Tuning fork", "Physics", 2,  new BorrowObject("DW", 1, "12", "12", "2020")),
			new Item("Orange 20W valve Amplifier", "Orange 20W valve Amplifie", "Music", 2,  new BorrowObject("Emma", 1, "12", "12", "2020")),
			new Item("Double kick pedal", "Double kick pedal", "Music", 5,  new BorrowObject("Metal head", 1, "12", "12", "2020")),
			new Item("Whiteboard", "Small whiteboard", "Art", 3,  new BorrowObject("Mrs fancy", 1, "12", "12", "2020")),
			new Item("1000ohm resistor", "1000ohm resistor", "Physics", 50,  new BorrowObject("Arthur", 1, "12", "12", "2020")),
			new Item("MOSFET Transistor", "MOSFET Transistor", "Design and Technology", 2,  new BorrowObject("Arthur", 1, "12", "12", "2020")),
			new Item("PLA Grey roll", "Grey PLA plastic", "Design and Technology", 10),
			new Item("Ammeter", "Ammeter", "Physics", 2),
			new Item("PS2", "PS2", "Computer Science", 1,  new BorrowObject("Nerd", 1, "12", "12", "2020"))
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
