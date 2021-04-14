package practiceIA;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SearchPanel extends JPanel {
	private ItemTableModel tableModel;
	private JLabel titleSearchLabel;
	private JTextField searchTextField;
	private JButton searchButton;
	private JScrollPane searchScrollPane;
	private JTable searchResultTable;
	private SearchTableModel searchModel;
	private JButton btnViewSearch;
	private JButton btnBackSearch;
	private JLabel msgSearchLabel;
	private Item selectedItem;
	private UiController uiController;

	SearchPanel(ItemTableModel tableModel, UiController uiController) {
		this.tableModel = tableModel;
		this.uiController = uiController;
		setLayout(null);

		titleSearchLabel = new JLabel("Search for an item");
		titleSearchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleSearchLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titleSearchLabel.setBounds(202, 10, 266, 31);
		add(titleSearchLabel);

		searchTextField = new JTextField();
		searchTextField.setBounds(10, 52, 528, 31);
		searchTextField.setColumns(10);
		add(searchTextField);

		searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String searchTerm = searchTextField.getText();
				if(searchTerm.isEmpty()) {
					searchModel.clearFound();
					setMsgSearch(0);
					return;
				}
				ArrayList<Item> found = tableModel.search(searchTerm);
				System.out.println(found);
				searchModel.setFound(found);
				setMsgSearch(found.size());
			}
		});
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchButton.setBounds(545, 52, 126, 31);
		add(searchButton);

		searchScrollPane = new JScrollPane();
		searchScrollPane.setBounds(10, 93, 661, 267);
		add(searchScrollPane);

		searchResultTable = new JTable();
		searchModel = new SearchTableModel();
		searchResultTable.setModel(searchModel);
		searchScrollPane.setViewportView(searchResultTable);

		btnViewSearch = new JButton("View more");
		btnViewSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = searchResultTable.getSelectedRow();
				selectedItem = searchModel.getItem(row);
				System.out.println("Viewing: " + selectedItem);
				uiController.goToView(selectedItem);
			}
		});
		btnViewSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnViewSearch.setEnabled(false);
		btnViewSearch.setBounds(371, 370, 145, 56);
		add(btnViewSearch);

		btnBackSearch = new JButton("Back");
		btnBackSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uiController.back();
			}
		});
		btnBackSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBackSearch.setBounds(526, 370, 145, 56);
		add(btnBackSearch);

		msgSearchLabel = new JLabel("");
		msgSearchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		msgSearchLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		msgSearchLabel.setBounds(10, 370, 351, 55);
		add(msgSearchLabel);
		
		searchResultTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = searchResultTable.getSelectedRow();
				
				if(row >= 0 && !e.getValueIsAdjusting()) {
					btnViewSearch.setEnabled(true);
				} else if (row == -1) {
					btnViewSearch.setEnabled(false);
				}
			}
		});
	}
	
	
	private void setMsgSearch(int numFound) {
		if(numFound <= 0) {
			msgSearchLabel.setText("No results found");
		} else {
			msgSearchLabel.setText("Number of results found: " + Integer.toString(numFound));
		}	
	}
	
}
