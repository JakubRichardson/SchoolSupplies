package practiceIA;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BorrowedItemPanel extends JPanel {
	private ItemTableModel tableModel;
	private BorrowedItemTableModel borrowedTableModel = new BorrowedItemTableModel();
	private JTable table;
	private JButton btnBack;
	private JScrollPane scrollPane;
	private JButton editReturnButton;
	private JButton returnItemButton;
	private UiController uiController;
	private BorrowObject selectedReturn;
	private Map<Integer, Item> items = new HashMap<Integer, Item>();
	
	BorrowedItemPanel(ItemTableModel tableModel, UiController uiController) {
		this.tableModel = tableModel;
		this.uiController = uiController;
		
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 507, 416);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(borrowedTableModel);
		scrollPane.setViewportView(table);
		
		editReturnButton = new JButton("Edit Return");
		editReturnButton.setEnabled(false);
		editReturnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				selectedReturn = borrowedTableModel.getItem(row);
				uiController.goToEditReturn(items.get(row), selectedReturn);
				System.out.println(items.get(row));
			}
		});
		editReturnButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editReturnButton.setBounds(526, 238, 145, 56);
		add(editReturnButton);
		
		returnItemButton = new JButton("Return Item");
		returnItemButton.setEnabled(false);
		returnItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				selectedReturn = borrowedTableModel.getItem(row);
				Item item = items.get(row);
				returnItem(item, selectedReturn);
				uiController.popStack();
				uiController.goToView(item);
				System.out.println(item);
			}
		});
		returnItemButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		returnItemButton.setBounds(526, 304, 145, 56);
		add(returnItemButton);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uiController.back();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(526, 370, 145, 56);
		add(btnBack);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = table.getSelectedRow();
				
				if(row >= 0 && !e.getValueIsAdjusting()) {
					editReturnButton.setEnabled(true);
					returnItemButton.setEnabled(true);
				} else if (row == -1) {
					editReturnButton.setEnabled(false);
					returnItemButton.setEnabled(false);
				}
			}
		});
	}
	
	public void refresh() {
		items = new HashMap<Integer, Item>();
		ArrayList<Item> inventory = tableModel.getInventory();
		ArrayList<BorrowObject> borrowed = new ArrayList<>();
		int index = 0;
		for(Item item: inventory) {
			ArrayList<BorrowObject> borrowedList = item.getBorrowed();
			for(BorrowObject borrowedObject: borrowedList) {
				borrowed.add(borrowedObject);
				items.put(index, item);
				index++;
			}
		}
		borrowedTableModel.setBorrowed(borrowed);
	}
	
	public void returnItem(Item item, BorrowObject itemToReturn) {
		ArrayList<BorrowObject> borrowed = item.getBorrowed();
		int index = borrowed.indexOf(itemToReturn);
		if(index == -1) {
			System.out.println("Item not borrowed");
			return;
		}
		BorrowObject borrowedItem = borrowed.get(index);
		if(borrowedItem == itemToReturn) {
			int quantity = itemToReturn.getQuantity();
			item.setQuantityAvailable(item.getQuantityAvailable() + quantity);
			borrowed.remove(index);
		} else {
			System.out.println("Error when trying to return item");
		}
	}
}
