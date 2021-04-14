package practiceIA;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

public class SearchTableModel extends AbstractTableModel {
	private ArrayList<Item> found = new ArrayList<>();
	
	public void setFound(ArrayList<Item> found) {
		this.found = found;
		fireTableDataChanged();
	}
	
	public void clearFound() {
		found.clear();
		fireTableDataChanged();
	}
	
	public String getColumnName(int column) {
		if(column == 0) {
			return "Name";
		}else if(column == 1) {
			return "Department";
		}
		return null;
	}
	
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return found.size();
	}
	
	Item getItem(int index) {
		return found.get(index);
	}

	@Override
	public Object getValueAt(int row, int column) {
		Item item = found.get(row);
		if(column == 0) {
			return item.getName();
		} else if (column == 1) {
			return item.getDepartment();
		}
		return null;
	}
}