package practiceIA;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import dates.Date;
import dates.InvalidDateException;

public class BorrowPanel extends JPanel {
	private JLabel returnDateLabel;
	private JLabel titleLabel;
	private JTextField usernameTextField;
	private JLabel usernameLabel;
	private JButton btnBack;
	private JButton btnSubmit;
	private JTextField dayTextField;
	private JTextField monthTextField;
	private JTextField yearTextField;
	private JLabel lblDay;
	private JLabel lblMonth;
	private JLabel lblYear;
	private JLabel lblDateToday;
	private JLabel msgLabel;
	private ColorScheme colorScheme;
	private Item item;
	private JLabel lblQuantityAvailable;
	private JLabel lblBorrowQuantity;
	private JSpinner quantityBorrowSpinner;
	private JLabel lblQuantityAvailableValue;
	private UiController uiController;

	BorrowPanel(ColorScheme colorScheme, UiController uiController) {
		this.colorScheme = colorScheme;
		this.uiController = uiController;

		setLayout(null);

		titleLabel = new JLabel("Borrow");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titleLabel.setBounds(200, 21, 266, 28);
		add(titleLabel);
		
		returnDateLabel = new JLabel("Return Date");
		returnDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		returnDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		returnDateLabel.setBounds(62, 245, 151, 43);
		add(returnDateLabel);
		
		usernameLabel = new JLabel("Username");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameLabel.setBounds(10, 84, 151, 43);
		add(usernameLabel);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uiController.back();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(539, 377, 132, 49);
		add(btnBack);
		
		btnSubmit = new JButton("Borrow");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(item == null) {
					System.out.println("Select an item first");
					return;
				}
				boolean isValid = validateData();
				if(isValid) {
					String username = usernameTextField.getText();
					int quantity = (Integer)quantityBorrowSpinner.getValue();
					String day = dayTextField.getText();
					String month = monthTextField.getText();
					String year = yearTextField.getText();
					item.setQuantityAvailable(item.getQuantityAvailable() - quantity);
					item.addBorrowed(new BorrowObject(username,quantity, day, month, year));
					System.out.println(item);
					Timer timer = new Timer(500, event -> {
						clear();
						uiController.popStack(2);
						uiController.goToView(item);
						setItem(null);
					});
					timer.setRepeats(false);
					timer.start();
				}
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSubmit.setBounds(397, 377, 132, 49);
		add(btnSubmit);
		
		usernameTextField = new JTextField();
		usernameTextField.setColumns(10);
		usernameTextField.setBounds(171, 84, 475, 43);
		add(usernameTextField);
		
		dayTextField = new JTextField();
		dayTextField.setColumns(10);
		dayTextField.setBounds(408, 194, 238, 43);
		add(dayTextField);
		
		monthTextField = new JTextField();
		monthTextField.setColumns(10);
		monthTextField.setBounds(408, 247, 238, 43);
		add(monthTextField);
		
		yearTextField = new JTextField();
		yearTextField.setColumns(10);
		yearTextField.setBounds(408, 300, 238, 43);
		add(yearTextField);
		
		lblDay = new JLabel("Day");
		lblDay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDay.setBounds(247, 192, 151, 43);
		add(lblDay);
		
		lblMonth = new JLabel("Month");
		lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMonth.setBounds(247, 245, 151, 43);
		add(lblMonth);
		
		lblYear = new JLabel("Year");
		lblYear.setHorizontalAlignment(SwingConstants.CENTER);
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYear.setBounds(247, 300, 151, 43);
		add(lblYear);
		
		lblDateToday = new JLabel("Date Today:");
		lblDateToday.setHorizontalAlignment(SwingConstants.LEFT);
		lblDateToday.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDateToday.setBounds(20, 383, 367, 43);
		add(lblDateToday);
		
		msgLabel = new JLabel("");
		msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		msgLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		msgLabel.setBounds(30, 50, 615, 31);
		add(msgLabel);
		
		lblQuantityAvailable = new JLabel("Quantity Available");
		lblQuantityAvailable.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantityAvailable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantityAvailable.setBounds(10, 137, 151, 43);
		add(lblQuantityAvailable);
		
		lblBorrowQuantity = new JLabel("Borrow Quantity");
		lblBorrowQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lblBorrowQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBorrowQuantity.setBounds(332, 137, 151, 43);
		add(lblBorrowQuantity);
		
		quantityBorrowSpinner = new JSpinner();
		quantityBorrowSpinner.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
		quantityBorrowSpinner.setBounds(493, 137, 153, 43);
		add(quantityBorrowSpinner);
		
		lblQuantityAvailableValue = new JLabel("");
		lblQuantityAvailableValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantityAvailableValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantityAvailableValue.setBounds(171, 137, 151, 43);
		add(lblQuantityAvailableValue);
	}
	
	private void setDate(String date) {
		lblDateToday.setText("Date today: " + date);
	}
	
	public void populateBorrow() {
		titleLabel.setText("Borrow " + item.getName());
		lblQuantityAvailableValue.setText(Integer.toString(item.getQuantityAvailable()));
		try {
			Date date = new Date();
			setDate(date.getDate());
		} catch(InvalidDateException e) {}
	}
	
	private boolean validateData() {
		ArrayList<String> messages = new ArrayList<>();
		
		messages.add(validateUsername());
		messages.add(validateQuantity());
		messages.add(validateDate());
		return showAndValidateMessages(messages);
	}
	
	private String validateUsername() {
		if(usernameTextField.getText().isEmpty()) {
			usernameTextField.setBackground(colorScheme.getFail());
			return "Enter Username";
		}
		usernameTextField.setBackground(colorScheme.getSuccess());
		return null;
	}
	
	private String validateQuantity() {
		if((Integer)quantityBorrowSpinner.getValue() < 1) {
			quantityBorrowSpinner.setBackground(colorScheme.getFail());
			return "Borrow Quantity must be positive";
		}
		if((Integer)quantityBorrowSpinner.getValue() > item.getQuantityAvailable()) {
			quantityBorrowSpinner.setBackground(colorScheme.getFail());
			return "Borrow Quantity must be less than or equal to available quantity";
		}
		quantityBorrowSpinner.setBackground(colorScheme.getSuccess());
		return null;
	}
	
	
	private String validateDate() {
		ArrayList<String> messages = new ArrayList<>();
		boolean filledIn = true;
		String day = validateDay();
		if(day == null) {
			messages.add("Enter a day");
			filledIn = false;
		}
		String month = validateMonth();
		if(month == null) {
			messages.add("Enter a month");
			filledIn = false;
		}
		String year = validateYear();
		if(year == null) {
			messages.add("Enter a year");
			filledIn = false;
		}
		
		if(filledIn) {
			int intDay;
			int intMonth;
			int intYear;
			try {
				intDay = Integer.parseInt(day);
			} catch (NumberFormatException e) {
				return "Invalid day, try an integer";
			}
			try {
				intMonth = Integer.parseInt(month);
			} catch (NumberFormatException e) {
				return "Invalid month, try an integer";
			}
			try {
				intYear = Integer.parseInt(year);
			} catch (NumberFormatException e) {
				return "Invalid year, try an integer";
			}
			try {
				Date date = new Date(intDay, intMonth, intYear);
				return null;
			} catch(InvalidDateException e) {
				return "Invalid date";
			}
		} else {
			return messages.get(0);
		}
	}
	
	private String validateDay() {
		String day = dayTextField.getText();
		if(day.isEmpty()) { 
			dayTextField.setBackground(colorScheme.getFail());
			return null;
		}
		dayTextField.setBackground(colorScheme.getSuccess());
		return day;
	}
	
	private String validateMonth() {
		String month = monthTextField.getText();
		if(month.isEmpty()) { 
			monthTextField.setBackground(colorScheme.getFail());
			return null;
		}
		monthTextField.setBackground(colorScheme.getSuccess());
		return month;
	}
	
	private String validateYear() {
		String year = yearTextField.getText();
		if(year.isEmpty()) { 
			yearTextField.setBackground(colorScheme.getFail());
			return null;
		}
		yearTextField.setBackground(colorScheme.getSuccess());
		return year;
	}
	
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
		titleLabel.setText("Borrow");
		setMsg("");
		usernameTextField.setText("");
		usernameTextField.setBackground(colorScheme.getDefault());
		lblQuantityAvailableValue.setText("");
		quantityBorrowSpinner.setValue(1);
		quantityBorrowSpinner.setBackground(colorScheme.getDefault());
		dayTextField.setText("");
		dayTextField.setBackground(colorScheme.getDefault());
		monthTextField.setText("");
		monthTextField.setBackground(colorScheme.getDefault());
		yearTextField.setText("");
		yearTextField.setBackground(colorScheme.getDefault());
	}
	
	private void setMsg(String msg) {
		msgLabel.setText(msg);
	}
	
	private void setMsgColor(Color color) {
		msgLabel.setForeground(color);
	}
	
	public void setItem(Item item) {
		this.item = item;
	}

	public Item getItem() {
		return item;
	}
}
