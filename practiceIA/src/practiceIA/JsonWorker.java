package practiceIA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonException;

public class JsonWorker {
	private ItemTableModel tableModel;
	private DepartmentComboBoxModel departmentModel;
	private static String saveLocation = "user.home"; //development path
//	private static String saveLocation = "user.dir";
	private static String itemSaveFileName = "items.json";
	
	JsonWorker(ItemTableModel tableModel, DepartmentComboBoxModel departmentModel) {
		this.tableModel = tableModel;
		this.departmentModel = departmentModel;
		loadItems();
	}
	
	public void loadItems() {
		Path savePath = getSavePath();
		
		try {
			FileInputStream iStream = new FileInputStream(savePath.toFile());
			JsonArray jsonArray = Json.createReader(iStream).readArray();
			for(int i = 0; i < jsonArray.size(); i++) {
				Item item = Item.fromJson(jsonArray.getJsonObject(i));
				tableModel.addItem(item);
				departmentModel.addDepartment(item.getDepartment());
			}
		} catch(FileNotFoundException e) {
			System.out.println("No file found, file may have been deleted or moved");
		} catch(JsonException e) {
			System.out.println("Empty file, no previous data");
		}
	}
	
	public boolean updateData() {
		Path savePath = getSavePath();
		
		boolean success = true;
		try {
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(); 
			for(Item item: tableModel.getInventory()) {
				arrayBuilder.add(item.toJson());
			}
			JsonArray array = arrayBuilder.build();
			FileOutputStream oStream = new FileOutputStream(savePath.toFile());
			Json.createWriter(oStream).writeArray(array);
		} catch (FileNotFoundException e) {
			success = false;
			e.printStackTrace();
		}
		return success;
	}
	
	public static Path getSavePath() {
		String home = System.getProperty(saveLocation);
		System.out.println("Save path: " + home);
		System.out.println("Saving to file: " + itemSaveFileName);
		Path homePath = Paths.get(home);
		Path savePath = homePath.resolve(itemSaveFileName);
		return savePath;
	}
}
