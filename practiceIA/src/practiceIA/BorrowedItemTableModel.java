package practiceIA;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class BorrowedItemTableModel extends	AbstractTableModel{
	private ArrayList<BorrowObject> borrowed  = new ArrayList<>();
	
	public void setBorrowed(ArrayList<BorrowObject> borrowed) {
		this.borrowed = borrowed;
		fireTableDataChanged();
	}
	
	public String getColumnName(int column) {
		if(column == 0) {
			return "User";
		}else if(column == 1) {
			return "Quantity";
		} else if(column == 2) {
			return "Return date";
		}
		return null;
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return borrowed.size();
	}
	
	BorrowObject getItem(int index) {
		return borrowed.get(index);
	}

	@Override
	public Object getValueAt(int row, int column) {
		BorrowObject object = borrowed.get(row);
		if(column == 0) {
			return object.getUser();
		} else if (column == 1) {
			return object.getQuantity();
		} else if(column == 2) {
			return object.getDate();
		}
		return null;
	}
}
