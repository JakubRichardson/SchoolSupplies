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
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ViewPanel extends JPanel {
	private Item selectedItem;
	private JLabel titleNameLabel;
	private JLabel nameLabel;
	private JLabel descriptionLabel;
	private JLabel departmentLabel;
//	private JLabel damagedLabel;
	private JLabel nameItemViewLabel;
	private JLabel descriptionItemViewLabel;
	private JLabel departmentItemViewLabel;
	private JLabel damagedItemViewLabel;
	private JButton editItemViewButton;
	private JButton borrowButton;
	private JButton btnBackView;
	private JScrollPane scrollPaneView;
	private JTextPane textPaneView;
	private JButton editReturnViewButton;
	private JButton returnItemButton;
	private JScrollPane scrollPaneBorrowed;
	private JTable borrowedItemTable;
	private BorrowedObjectTableModel borrowedByTableModel = new BorrowedObjectTableModel();
	private JLabel quantityAvailableLabel;
	private JLabel quantityAvailableValueLabel;
	private BorrowObject selectedReturn;
	private ColorScheme colorScheme;
	private UiController uiController;
	
	ViewPanel(ColorScheme colorScheme, UiController uiController) {
		this.colorScheme = colorScheme;
		this.uiController = uiController;
		
		setLayout(null);
		
		titleNameLabel = new JLabel("");
		titleNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titleNameLabel.setBounds(243, 16, 162, 28);
		add(titleNameLabel);
		
		nameLabel = new JLabel("Name");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel.setBounds(39, 54, 151, 43);
		add(nameLabel);
		
		descriptionLabel = new JLabel("Description");
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		descriptionLabel.setBounds(39, 107, 151, 74);
		add(descriptionLabel);
		
		departmentLabel = new JLabel("Department");
		departmentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		departmentLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		departmentLabel.setBounds(39, 191, 151, 43);
		add(departmentLabel);
		
		departmentItemViewLabel = new JLabel("");
		departmentItemViewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		departmentItemViewLabel.setBounds(200, 191, 471, 43);
		add(departmentItemViewLabel);
		
//		damagedLabel = new JLabel("Damaged");
//		damagedLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		damagedLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		damagedLabel.setBounds(39, 191, 151, 43);
//		add(damagedLabel);
		
//		damagedItemViewLabel = new JLabel("");
//		damagedItemViewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		damagedItemViewLabel.setBounds(200, 191, 471, 43);
//		add(damagedItemViewLabel);
		
		nameItemViewLabel = new JLabel("");
		nameItemViewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameItemViewLabel.setBounds(200, 54, 471, 43);
		add(nameItemViewLabel);
		
		scrollPaneView = new JScrollPane();
		scrollPaneView.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneView.setBounds(200, 107, 471, 74);
		add(scrollPaneView);
		
		textPaneView = new JTextPane();
		textPaneView.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textPaneView.setBackground(colorScheme.getBackground());
		textPaneView.setEditable(false);
		scrollPaneView.setViewportView(textPaneView);

//		descriptionItemViewLabel = new JLabel("");
//		descriptionItemViewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		descriptionItemViewLabel.setBounds(200, 107, 471, 74);
//		add(descriptionItemViewLabel);
		
		borrowButton = new JButton("Borrow Item");
		borrowButton.setEnabled(false);
		borrowButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		borrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selectedItem == null) {
					System.out.println("Select a product first");
					return;
				}
				
				System.out.println("Borrowing: " + selectedItem);
				uiController.goToBorrow(selectedItem);
			}
		});
		borrowButton.setBounds(10, 377, 124, 49);
		add(borrowButton);
		
		btnBackView = new JButton("Back");
		btnBackView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uiController.back();
			}
		});
		btnBackView.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBackView.setBounds(546, 377, 124, 49);
		add(btnBackView);
		
		editItemViewButton = new JButton("Edit Item");
		editItemViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selectedItem == null) {
					System.out.println("Select a product first");
					return;
				}
				
				System.out.println("Editing: " + selectedItem);
				uiController.goToEdit(selectedItem);
			}
		});
		editItemViewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editItemViewButton.setBounds(144, 377, 124, 49);
		add(editItemViewButton);
		
		JLabel borrowedLabel = new JLabel("Borrowed");
		borrowedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		borrowedLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		borrowedLabel.setBounds(39, 310, 151, 43);
		add(borrowedLabel);
		
		editReturnViewButton = new JButton("Edit Return");
		editReturnViewButton.setEnabled(false);
		editReturnViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = borrowedItemTable.getSelectedRow();
				selectedReturn = borrowedByTableModel.getItem(row);
				uiController.goToEditReturn(selectedItem, selectedReturn);
				System.out.println(selectedItem);
			}
		});
		editReturnViewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editReturnViewButton.setBounds(278, 377, 124, 49);
		add(editReturnViewButton);
		
		returnItemButton = new JButton("Return Item");
		returnItemButton.setEnabled(false);
		returnItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = borrowedItemTable.getSelectedRow();
				selectedReturn = borrowedByTableModel.getItem(row);
				returnItem(row);
				uiController.popStack();
				uiController.refreshBorrowedItems();
				uiController.goToView(selectedItem);
				System.out.println(selectedItem);
			}
		});
		returnItemButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		returnItemButton.setBounds(412, 377, 124, 49);
		add(returnItemButton);
		
		scrollPaneBorrowed = new JScrollPane();
		scrollPaneBorrowed.setBounds(200, 293, 471, 74);
		add(scrollPaneBorrowed);
		
		borrowedItemTable = new JTable();
		borrowedItemTable.setBackground(colorScheme.getBackground());
		borrowedItemTable.setModel(borrowedByTableModel);
		scrollPaneBorrowed.setViewportView(borrowedItemTable);
		
		quantityAvailableLabel = new JLabel("Quantity Available");
		quantityAvailableLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quantityAvailableLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		quantityAvailableLabel.setBounds(39, 240, 151, 43);
		add(quantityAvailableLabel);
		
		quantityAvailableValueLabel = new JLabel("");
		quantityAvailableValueLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		quantityAvailableValueLabel.setBounds(200, 240, 471, 43);
		add(quantityAvailableValueLabel);
		
//		borrowedByTextArea = new JTextArea();
//		borrowedByTextArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		borrowedByTextArea.setEditable(false);
//		borrowedByTextArea.setBackground(colorScheme.getBackground());
//		scrollPaneBorrowed.setViewportView(borrowedByTextArea);
		
		borrowedItemTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = borrowedItemTable.getSelectedRow();
				
				if(row >= 0 && !e.getValueIsAdjusting()) {
					editReturnViewButton.setEnabled(true);
					returnItemButton.setEnabled(true);
				} else if (row == -1) {
					editReturnViewButton.setEnabled(false);
					returnItemButton.setEnabled(false);
				}
			}
		});
	}
	
	public void populateView(Item item) {
		this.setItem(item);
		titleNameLabel.setText(selectedItem.getName());
		nameItemViewLabel.setText(selectedItem.getName());
		textPaneView.setText(selectedItem.getDescription());
//		damagedItemViewLabel.setText(selectedItem.getDamagedString());
		departmentItemViewLabel.setText(selectedItem.getDepartment());
//		borrowedByTextArea.setText(getBorrowedByText(item));
		borrowedByTableModel.setBorrowed(selectedItem.getBorrowed());
		int quantityAvailable = selectedItem.getQuantityAvailable();
		quantityAvailableValueLabel.setText(Integer.toString(quantityAvailable));
		if(quantityAvailable > 0) {
			borrowButton.setEnabled(true);
		}  else {
			borrowButton.setEnabled(false);
		}
	}
	
	public void setItem(Item selectedItem) {
		this.selectedItem = selectedItem;
	}

	public Item getItem() {
		return selectedItem;
	}
	
	public void returnItem(int index) {
		ArrayList<BorrowObject> borrowed = selectedItem.getBorrowed();
		BorrowObject borrowedItem = borrowed.get(index);
		if(borrowedItem == selectedReturn) {
			int quantity = borrowedItem.getQuantity();
			selectedItem.setQuantityAvailable(selectedItem.getQuantityAvailable() + quantity);
			borrowed.remove(index);
		} else {
			System.out.println("Error when trying to return item");
		}
	}
}
