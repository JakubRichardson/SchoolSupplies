package practiceIA;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class EditPanel extends JPanel {
	private Item selectedItem; 
	private ColorScheme colorScheme;
	private JLabel titleLabel;
	private JLabel nameLabel;
	private JLabel msgLabel;
	private JTextField nameTextField;
	private JLabel descriptionLabel;
	private JScrollPane descriptionScrollPane;
	private JTextArea descriptionTextArea;
	private JLabel departmentLabel;
	private JComboBox departmentComboBox;
	private DepartmentComboBoxModel departmentModel;
//	private JLabel damagedEditLabel;
//	private JComboBox damagedEditComboBox;
	private JLabel quantityAvailableLabel;
	private JSpinner quantityAvailableSpinner;
	private JButton btnSubmitEdit;
	private JButton btnBackEdit;
	private JButton btnAddDepartment;
	private UiController uiController;
	
	EditPanel(ColorScheme colorScheme, DepartmentComboBoxModel departmentModel, UiController uiController) {
		this.colorScheme = colorScheme;
		this.departmentModel = departmentModel;
		this.uiController = uiController;
		
		setLayout(null);
		
		nameLabel = new JLabel("Name");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel.setBounds(10, 80, 150, 43);
		add(nameLabel);
		
		titleLabel = new JLabel("Edit item");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titleLabel.setBounds(205, 10, 266, 31);
		add(titleLabel);
		
		descriptionLabel = new JLabel("Description");
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		descriptionLabel.setBounds(10, 133, 151, 74);
		add(descriptionLabel);
		
//		damagedEditLabel = new JLabel("Damaged");
//		damagedEditLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		damagedEditLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		damagedEditLabel.setBounds(10, 217, 151, 43);
//		add(damagedEditLabel);
		
//		damagedEditComboBox = new JComboBox();
//		damagedEditComboBox.setModel(new DefaultComboBoxModel(Damaged.values()));
//		damagedEditComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		damagedEditComboBox.setBounds(170, 217, 475, 43);
//		add(damagedEditComboBox);
		
		departmentLabel = new JLabel("Department");
		departmentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		departmentLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		departmentLabel.setBounds(10, 217, 151, 43);
		add(departmentLabel);
		
		departmentComboBox = new JComboBox();
		departmentComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		departmentComboBox.setBounds(170, 217, 475, 43);
		departmentComboBox.setModel(departmentModel);
		add(departmentComboBox);
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(170, 80, 475, 43);
		add(nameTextField);
		
		descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(170, 133, 475, 74);
		add(descriptionScrollPane);
		
		descriptionTextArea = new JTextArea();
		descriptionTextArea.setLineWrap(true);
	    descriptionScrollPane.setViewportView(descriptionTextArea);
	    
	    quantityAvailableLabel = new JLabel("Quantity Available");
	    quantityAvailableLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    quantityAvailableLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    quantityAvailableLabel.setBounds(10, 269, 151, 43);
	    add(quantityAvailableLabel);
		
		quantityAvailableSpinner = new JSpinner();
//		quantityAvailableSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(5)));
		quantityAvailableSpinner.setBounds(170, 269, 475, 43);
		add(quantityAvailableSpinner);
		
		btnAddDepartment = new JButton("Add Department");
		btnAddDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uiController.goToAddDepartment();
			}
		});
		btnAddDepartment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddDepartment.setBounds(10, 377, 140, 49);
		add(btnAddDepartment);
		
		btnBackEdit = new JButton("Back");
		btnBackEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uiController.back();
				clear();
				setItem(null);
			}
		});
		btnBackEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBackEdit.setBounds(539, 377, 132, 49);
		add(btnBackEdit);
		
		btnSubmitEdit = new JButton("Save");
		btnSubmitEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selectedItem == null) {
					System.out.println("Select item first");
					return;
				}
				boolean valid = validateEdit();
				if(valid) {
					selectedItem.setName(nameTextField.getText());
					selectedItem.setDescription(descriptionTextArea.getText());
//					selectedItem.setDamaged((Damaged)damagedEditComboBox.getSelectedItem());
					selectedItem.setDepartment((String)departmentComboBox.getSelectedItem());
					selectedItem.setQuantityAvailable((Integer)quantityAvailableSpinner.getValue());
					System.out.println("Edited item: " + selectedItem);
					Timer timer = new Timer(500, event -> {
						clear();
						uiController.popStack(2);
						uiController.goToView(selectedItem);
						setItem(null);
					});
					timer.setRepeats(false);
					timer.start();
				}
			}
		});
		btnSubmitEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSubmitEdit.setBounds(397, 377, 132, 49);
		add(btnSubmitEdit);
		
		msgLabel = new JLabel("");
		msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		msgLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		msgLabel.setBounds(30, 40, 615, 31);
		add(msgLabel);
	}
	
	public void populateEdit(Item item) {
		this.setItem(item);
		titleLabel.setText("Edit " + selectedItem.getName());
		nameTextField.setText(selectedItem.getName());
		descriptionTextArea.setText(selectedItem.getDescription());
//		damagedEditComboBox.setSelectedItem(item.getDamaged());
		departmentComboBox.setSelectedItem(selectedItem.getDepartment());
		quantityAvailableSpinner.setValue(selectedItem.getQuantityAvailable());
	}
	
	public boolean validateEdit() {
		ArrayList<String> messages = new ArrayList<>();
		
		messages.add(validateNameEdit());
		messages.add(validateDescriptionEdit());
		messages.add(validateDepartmentEdit());
		messages.add(validateQuantityEdit());
		return showAndValidateMessagesEdit(messages);
	}
	
	private String validateNameEdit() {
		if(nameTextField.getText().isEmpty()) {
			nameTextField.setBackground(colorScheme.getFail());
			return "Enter item name";
		}
		nameTextField.setBackground(colorScheme.getSuccess());
		return null;
	}
	
	private String validateDescriptionEdit() {
		if(descriptionTextArea.getText().isEmpty()) {
			descriptionTextArea.setBackground(colorScheme.getFail());
			return "Enter a description";
		}
		descriptionTextArea.setBackground(colorScheme.getSuccess());
		return null;
	}
	
	private String validateDepartmentEdit() {
		if(departmentComboBox.getSelectedItem() == null) {
			departmentComboBox.setBackground(colorScheme.getFail());
			return "Select a department";
		}
		departmentComboBox.setBackground(colorScheme.getSuccess());
		return null;
	}
	
	private String validateQuantityEdit() {
		if((Integer)quantityAvailableSpinner.getValue() < 0) {
			quantityAvailableSpinner.setBackground(colorScheme.getFail());
			return "Quantity available can't be negative";
		}
		quantityAvailableSpinner.setBackground(colorScheme.getSuccess());
		return null;
	}
	
	private boolean showAndValidateMessagesEdit(ArrayList<String> msgs) {
		for (String msg : msgs) {
			if(msg != null) {
				setMsgColor(colorScheme.getFail());
				setMsg(msg);
				return false;
			}
    	}
		setMsgColor(colorScheme.getSuccess());
		setMsg("Looks good!");
		return true;
	}
	
	private void clear() {
		titleLabel.setText("Borrow");
		setMsg("");
		nameTextField.setText("");
		nameTextField.setBackground(colorScheme.getDefault());
		descriptionTextArea.setText("");
		descriptionTextArea.setBackground(colorScheme.getDefault());
//		departmentComboBox.reset()
		departmentComboBox.setBackground(colorScheme.getDefault());
		quantityAvailableSpinner.setValue(0);
		quantityAvailableSpinner.setBackground(colorScheme.getDefault());
	}
	
	public void setMsg(String msg) {
		msgLabel.setText(msg);
	}
	
	public void setMsgColor(Color color) {
		msgLabel.setForeground(color);
	}
	
	public void setItem(Item selectedItem) {
		this.selectedItem = selectedItem;
	}

	public Item getItem() {
		return selectedItem;
	}
}
