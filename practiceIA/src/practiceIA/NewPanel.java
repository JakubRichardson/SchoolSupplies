package practiceIA;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NewPanel extends JPanel {
	private JLabel msgLabel;
	private JLabel titleLabel;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JLabel descriptionLabel;
	private JScrollPane scrollPane;
	private JTextArea descriptionTextArea;
	private JLabel departmentLabel;
	private JComboBox departmentComboBox;
//	private JComboBox damagedComboBox;
//	private JLabel damagedLabel;
	private JButton btnBack;
	private JButton btnAddNew;
	private JButton btnAddDepartment;
	private JLabel quantityLabel;
	private JSpinner quantitySpinner;
	private DepartmentComboBoxModel departmentModel;
	private ItemTableModel tableModel;
	private ColorScheme colorScheme;
	private UiController uiController;
	ArrayList<DocumentListener> listeners = new ArrayList<>();


	NewPanel(DepartmentComboBoxModel departmentModel, ItemTableModel tableModel, ColorScheme colorScheme, UiController uiController) {
		this.departmentModel = departmentModel;
		this.tableModel = tableModel;
		this.colorScheme = colorScheme;
		this.uiController = uiController;

		setLayout(null);

		titleLabel = new JLabel("Add new item");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titleLabel.setBounds(205, 10, 266, 31);
		add(titleLabel);

		nameLabel = new JLabel("Name");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel.setBounds(10, 80, 150, 43);
		add(nameLabel);

		descriptionLabel = new JLabel("Description");
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		descriptionLabel.setBounds(10, 133, 151, 74);
		add(descriptionLabel);

//		damagedLabel = new JLabel("Damaged");
//		damagedLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		damagedLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		damagedLabel.setBounds(10, 217, 151, 43);
//		add(damagedLabel);
//		
//		damagedComboBox = new JComboBox();
//		damagedComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		damagedComboBox.setModel(new DefaultComboBoxModel(Damaged.values()));
//		damagedComboBox.setBounds(170, 217, 475, 43);
//		add(damagedComboBox);

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uiController.back();
				clear();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(539, 377, 132, 49);
		add(btnBack);

		nameTextField = new JTextField();
		nameTextField.setBounds(170, 80, 475, 43);
		add(nameTextField);
		nameTextField.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(170, 133, 475, 74);
		add(scrollPane);

		descriptionTextArea = new JTextArea();
		descriptionTextArea.setLineWrap(true);
		scrollPane.setViewportView(descriptionTextArea);

		departmentLabel = new JLabel("Department");
		departmentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		departmentLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		departmentLabel.setBounds(10, 217, 151, 43);
		add(departmentLabel);
		
		departmentComboBox = new JComboBox();
		departmentComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		departmentComboBox.setBounds(170, 217, 475, 43);
		departmentComboBox.setModel(departmentModel);
		departmentComboBox.setSelectedIndex(departmentModel.getSize() > 0 ? 0 : -1);
		add(departmentComboBox);
		
		quantityLabel = new JLabel("Quantity");
		quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quantityLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		quantityLabel.setBounds(10, 269, 151, 43);
		add(quantityLabel);
		
		quantitySpinner = new JSpinner();
//		quantitySpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(5)));
		quantitySpinner.setBounds(170, 269, 475, 43);
		add(quantitySpinner);
		
		btnAddDepartment = new JButton("Add Department");
		btnAddDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uiController.goToAddDepartment();
			}
		});
		btnAddDepartment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddDepartment.setBounds(10, 377, 140, 49);
		add(btnAddDepartment);

		btnAddNew = new JButton("Add");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean valid = validateModel();
				if(valid) {
					String name = nameTextField.getText();
					String description = descriptionTextArea.getText();
//					Damaged damaged = (Damaged)damagedComboBox.getSelectedItem();
					String department = (String)departmentComboBox.getSelectedItem();
					int quantity = (Integer)quantitySpinner.getValue();
					tableModel.addItem(new Item(name, description, department, quantity));
					Timer timer = new Timer(500, event -> {
						clear();
						uiController.popStack(2);
						uiController.goToHome();
					});
					timer.setRepeats(false);
					timer.start();
				}
			}
		});
		btnAddNew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddNew.setBounds(397, 377, 132, 49);
		add(btnAddNew);

		msgLabel = new JLabel("");
		msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		msgLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		msgLabel.setBounds(30, 40, 615, 31);
		add(msgLabel);
	}

	private boolean validateModel() {
		ArrayList<String> messages = new ArrayList<>();
		
		messages.add(validateDepartment());
		messages.add(validateName());
		messages.add(validateDescription());
//		messages.add(validateDamaged());
		messages.add(validateQuantity());
		return showAndValidateMessages(messages);
	}
	
	private String validateName() {
		if(nameTextField.getText().isEmpty()) {
			nameTextField.setBackground(colorScheme.getFail());
			return "Enter item name";
		}
		nameTextField.setBackground(colorScheme.getSuccess());
		return null;
	}
	
	private String validateDescription() {
		if(descriptionTextArea.getText().isEmpty()) {
			descriptionTextArea.setBackground(colorScheme.getFail());
			return "Enter a description";
		}
		descriptionTextArea.setBackground(colorScheme.getSuccess());
		return null;
	}
	
	private String validateDepartment() {
		if(departmentComboBox.getSelectedItem() == null) {
			departmentComboBox.setBackground(colorScheme.getFail());
			return "Select a department";
		}
		departmentComboBox.setBackground(colorScheme.getSuccess());
		return null;
	}
	
	private String validateQuantity() {
		if((Integer)quantitySpinner.getValue() < 1) {
			quantitySpinner.setBackground(colorScheme.getFail());
			return "The item quantity must be at least 1 to add it";
		}
		quantitySpinner.setBackground(colorScheme.getSuccess());
		return null;
	}
	
//	private String validateDamaged() {
//		if(damagedComboBox.getSelectedItem() == null) {
//			departmentComboBox.setBackground(colorScheme.getFail());
//			return "Choose if item is damaged";
//		}
//		damagedComboBox.setBackground(colorScheme.getSuccess());
//		return null;
//	}
	
	private boolean showAndValidateMessages(ArrayList<String> msgs) {
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
		nameTextField.setText("");
		nameTextField.setBackground(colorScheme.getDefault());
		descriptionTextArea.setText("");
		descriptionTextArea.setBackground(colorScheme.getDefault());
//		damagedComboBox.setBackground(colorScheme.getDefault());
		departmentComboBox.setBackground(colorScheme.getDefault());
		quantitySpinner.setBackground(colorScheme.getDefault());
		quantitySpinner.setValue(0);
		setMsg("");
	}


	private void setMsg(String msg) {
		msgLabel.setText(msg);
	}
	
	private void setMsgColor(Color color) {
		msgLabel.setForeground(color);
	}

	//	private void addNameListener() {
	//		DocumentListener listener = new DocumentListener() {
	//			public void changedUpdate(DocumentEvent e) {
	//				changed();
	//			}
	//			public void removeUpdate(DocumentEvent e) {
	//				changed();
	//			}
	//			public void insertUpdate(DocumentEvent e) {
	//				changed();
	//			}
	//
	//			public void changed() {
	//				if (nameTextField.getText().equals("")){
	//					nameTextField.setBackground(new Color(0xff0000));
	//				}
	//				else {
	//					System.out.println("Something");
	//				}
	//
	//			}
	//		}
	//		nameTextField.getDocument().addDocumentListener(listener);
	//		listeners.add(listener);
	//	}
	//
	//	private void removeNameListener() {
	//		for( DocumentListener listen : listeners) {
	//			nameTextField.getDocument().removeDocumentListener( listen );
	//		}
	//	}


}
