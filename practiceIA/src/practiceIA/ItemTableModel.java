package practiceIA;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ItemTableModel extends	AbstractTableModel {
	private ArrayList<Item> inventory = new ArrayList<>();
	
//	ItemTableModel() {
//		inventory.addAll(Arrays.asList(presetItems));
//	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public ArrayList<Item> search(String searchTerm) {
		ArrayList<Item> found = new ArrayList<>();
		for(Item item: inventory) {
			if(item.getName().equals(searchTerm)) {
				found.add(item);
			}
		}
		return found;
	}
	
	public void addItem(Item item) {
		inventory.add(item);
		fireTableDataChanged();
	}
	
	public void deleteItem(Item item) {
		inventory.remove(item);
		fireTableDataChanged();
	}
	
	public String getColumnName(int column) {
		if(column == 0) {
			return "Name";
		}else if(column == 1) {
			return "Department";
		} else if (column == 2) {
			return "Quantity Available";
		}
		return null;
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return inventory.size();
	}
	
	Item getItem(int index) {
		return inventory.get(index);
	}

	@Override
	public Object getValueAt(int row, int column) {
		Item item = inventory.get(row);
		if(column == 0) {
			return item.getName();
		} else if (column == 1) {
			return item.getDepartment();
		} else if (column == 2) {
			return item.getQuantityAvailable();
		}
		return null;
	}
}
