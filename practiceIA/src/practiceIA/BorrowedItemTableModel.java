package practiceIA;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class BorrowedItemTableModel extends	AbstractTableModel{
	private ArrayList<BorrowedItem> borrowed  = new ArrayList<>();
	
	public void setBorrowed(ArrayList<BorrowedItem> borrowed) {
		this.borrowed = borrowed;
		fireTableDataChanged();
	}
	
	public String getColumnName(int column) {
		if(column == 0) {
			return "Item Name";
		} else if(column == 1) {
			return "User";
		}else if(column == 2) {
			return "Quantity";
		} else if(column == 3) {
			return "Return date";
		}
		return null;
	}
	
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return borrowed.size();
	}
	
	BorrowedItem getBorrowed(int index) {
		return borrowed.get(index);
	}

	@Override
	public Object getValueAt(int row, int column) {
		BorrowedItem item = borrowed.get(row);
		if(column == 0) {
			return item.getItem().getName();
		} else if(column == 1) {
			return item.getBorrowedItem().getUser();
		} else if (column == 2) {
			return item.getBorrowedItem().getQuantity();
		} else if(column == 3) {
			return item.getBorrowedItem().getDate();
		}
		return null;
	}
}
