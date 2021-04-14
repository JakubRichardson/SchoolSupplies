package practiceIA;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class HomePanel extends JPanel {
	private Item selectedItem;
	private JTable table;
	private ItemTableModel tableModel;
	private JScrollPane scrollPane;
	private JButton btnView;
	private JButton btnNew;
	private JButton btnSearch;
	private JButton btnBorrow;
	private JButton btnViewBorrowed;
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
		
		btnView = new JButton("View more");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				autoSetSelectedItem();
				System.out.println("Viewing: " + selectedItem);
				uiController.goToView(selectedItem);
			}
		});
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnView.setBounds(526, 370, 145, 56);
		btnView.setEnabled(false);
		add(btnView);
		
		btnNew = new JButton("New Item");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uiController.goToNew();
			}
		});
		btnNew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNew.setBounds(526, 13, 145, 56);
		add(btnNew);
		
		btnViewBorrowed = new JButton("View Borrowed");
//		btnViewBorrowed.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				uiController.goToNew();
//			}
//		});
		btnViewBorrowed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnViewBorrowed.setBounds(526, 145, 145, 56);
		add(btnViewBorrowed);
		
		
		
//		JButton btnRefresh = new JButton("Refresh");
//		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		btnRefresh.setBounds(526, 13, 145, 56);
//		add(btnRefresh);
		
		btnBorrow = new JButton("Borrow");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				autoSetSelectedItem();
				uiController.goToView(selectedItem);
				uiController.goToBorrow(selectedItem);
			}
		});
		btnBorrow.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBorrow.setBounds(527, 304, 145, 56);
		btnBorrow.setEnabled(false);
		add(btnBorrow);
		
		btnSearch = new JButton("Search Item");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uiController.goToSearch();
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(527, 79, 145, 56);
		add(btnSearch);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = table.getSelectedRow();
				
				if(row >= 0 && !e.getValueIsAdjusting()) {
					btnView.setEnabled(true);
					if(tableModel.getItem(row).isAvailable()) {
						btnBorrow.setEnabled(true);
					} else {
						btnBorrow.setEnabled(false);
					}
				} else if (row == -1) {
					btnView.setEnabled(false);
					btnBorrow.setEnabled(false);
				}
			}
		});
	}
	
	private void autoSetSelectedItem() {
		int row = table.getSelectedRow();
		selectedItem = tableModel.getItem(row);
	}
}
