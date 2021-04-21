package practiceIA;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class HomePanel extends JPanel {
	private Item selectedItem;
	private JTable table;
	private ItemTableModel tableModel;
	private JScrollPane scrollPane;
	private JLabel msgLabel;
	private JButton btnView;
	private JButton btnNew;
	private JButton btnSearch;
	private JButton btnBorrow;
	private JButton btnViewBorrowed;
	private JButton btnDelete;
	private UiController uiController;
	
	HomePanel(ItemTableModel tableModel, UiController uiController) {
		this.tableModel = tableModel;
		this.uiController = uiController;
		
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 507, 416);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		
		msgLabel = new JLabel("Welcome!");
		msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		msgLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		msgLabel.setBounds(526, 10, 145, 32);
		add(msgLabel);
		
		btnNew = new JButton("New Item");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uiController.goToNew();
			}
		});
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNew.setBounds(526, 52, 145, 54);
		add(btnNew);
		
		btnSearch = new JButton("Search Item");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uiController.goToSearch();
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(526, 116, 145, 54);
		add(btnSearch);
		
		btnViewBorrowed = new JButton("View Borrowed");
		btnViewBorrowed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uiController.goToBorrowedItems();
			}
		});
		btnViewBorrowed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnViewBorrowed.setBounds(526, 180, 145, 54);
		add(btnViewBorrowed);
		
		btnDelete = new JButton("Delete Item");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				autoSetSelectedItem();
				tableModel.deleteItem(selectedItem);
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(526, 244, 145, 54);
		btnDelete.setEnabled(false);
		add(btnDelete);
		
		btnBorrow = new JButton("Borrow");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				autoSetSelectedItem();
				uiController.goToView(selectedItem);
				uiController.goToBorrow(selectedItem);
			}
		});
		btnBorrow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBorrow.setBounds(526, 308, 145, 54);
		btnBorrow.setEnabled(false);
		add(btnBorrow);
		
		btnView = new JButton("View more");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				autoSetSelectedItem();
				System.out.println("Viewing: " + selectedItem);
				uiController.goToView(selectedItem);
			}
		});
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnView.setBounds(526, 372, 145, 54);
		btnView.setEnabled(false);
		add(btnView);
		
//		JButton btnRefresh = new JButton("Refresh");
//		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		btnRefresh.setBounds(526, 13, 145, 56);
//		add(btnRefresh);
		
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = table.getSelectedRow();
				
				if(row >= 0 && !e.getValueIsAdjusting()) {
					btnView.setEnabled(true);
					btnDelete.setEnabled(true);
					if(tableModel.getItem(row).isAvailable()) {
						btnBorrow.setEnabled(true);
					} else {
						btnBorrow.setEnabled(false);
					}
				} else if (row == -1) {
					btnView.setEnabled(false);
					btnDelete.setEnabled(false);
					btnBorrow.setEnabled(false);
				}
			}
		});
	}
	
	private void autoSetSelectedItem() {
		int row = table.getSelectedRow();
		selectedItem = tableModel.getItem(row);
	}
	
	
	public void refreshBorrowButton() {
		int row = table.getSelectedRow();
		
		if(row >= 0) {
			if(tableModel.getItem(row).isAvailable()) {
				btnBorrow.setEnabled(true);
				return;
			}
		}
		btnBorrow.setEnabled(false);
	}
	
//	public void setMsg(String msg) {
//		msgLabel.setText(msg);
//	}
//	
//	public void setMsgColor(Color color) {
//		msgLabel.setForeground(color);
//	}
//	
//	public void resetMsg() {
//		setMsg("Welcome!");
//		setMsgColor(colorScheme.getDefault());
//	}
}
