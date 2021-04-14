package practiceIA;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UiMain {

	private JFrame frame;
	private ItemTableModel tableModel;
	private JTable table;
	private JButton btnView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UiMain window = new UiMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UiMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 508, 443);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		tableModel = new ItemTableModel();
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		
		btnView = new JButton("View More");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				Item item = tableModel.getItem(row);
				System.out.println(item);
			}
		});
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnView.setBounds(528, 402, 148, 51);
		btnView.setEnabled(false);
		frame.getContentPane().add(btnView);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = table.getSelectedRow();
				
				if(row >= 0 && !e.getValueIsAdjusting()) {
					btnView.setEnabled(true);
				}
			}
		});
	}
}
