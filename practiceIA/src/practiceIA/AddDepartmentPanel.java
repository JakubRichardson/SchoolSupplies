package practiceIA;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class AddDepartmentPanel extends JPanel {
	private ColorScheme colorScheme;
	private DepartmentComboBoxModel departmentModel;
	private UiController uiController;
	private JLabel titleLabel;
	private JLabel msgLabel;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JButton btnBack;
	private JButton btnAddNew;
	
	AddDepartmentPanel(ColorScheme colorScheme, DepartmentComboBoxModel departmentModel, UiController uiController) {
		this.colorScheme = colorScheme;
		this.departmentModel = departmentModel;
		this.uiController = uiController;
		
		setLayout(null);

		titleLabel = new JLabel("Add Department");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		titleLabel.setBounds(205, 20, 266, 40);
		add(titleLabel);

		msgLabel = new JLabel("");
		msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		msgLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		msgLabel.setBounds(30, 80, 615, 41);
		add(msgLabel);

		nameLabel = new JLabel("Department Name");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel.setBounds(10, 150, 150, 43);
		add(nameLabel);

		nameTextField = new JTextField();
		nameTextField.setBounds(170, 150, 475, 43);
		add(nameTextField);
		nameTextField.setColumns(10);

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uiController.back();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(539, 377, 132, 49);
		add(btnBack);

		btnAddNew = new JButton("Add");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nameTextField.getText().isEmpty()) {
					nameTextField.setBackground(colorScheme.getFail());
					setMsgColor(colorScheme.getFail());
					setMsg("Fill in the Department name");
					return;
				}
				nameTextField.setBackground(colorScheme.getSuccess());
				setMsgColor(colorScheme.getSuccess());
				setMsg("Looks Good!");
				departmentModel.addDepartment(nameTextField.getText());
				Timer timer = new Timer(500, event -> {
					uiController.back();
				});
				timer.setRepeats(false);
				timer.start();
			}
		});
		btnAddNew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddNew.setBounds(397, 377, 132, 49);
		add(btnAddNew);
	}
	
	
	private void setMsg(String msg) {
		msgLabel.setText(msg);
	}

	private void setMsgColor(Color color) {
		msgLabel.setForeground(color);
	}
}
