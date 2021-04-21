package practiceIA;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ErrorPanel extends JPanel {
	private JLabel errorLabel;
	
	ErrorPanel() {
		setLayout(null);
		
		errorLabel = new JLabel("error");
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setBounds(10, 43, 661, 192);
		add(errorLabel);
	}
	
	public void setError(String error) {
		errorLabel.setText(error);
	}
	
	public void setErrorColor(Color color) {
		errorLabel.setForeground(color);
	}
}
