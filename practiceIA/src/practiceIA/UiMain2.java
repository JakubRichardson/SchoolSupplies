package practiceIA;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class UiMain2 {

	private JFrame frame;
	private ItemTableModel tableModel = new ItemTableModel();
	private HomePanel homePanel;
//	private JTable table;
//	private JButton btnView;
//	private JButton btnNew;
	private ViewPanel viewPanel;
	
	// edit panel variables
//	private JLabel titleNameLabel;
//	private JLabel nameLabel;
//	private JLabel descriptionLabel;
//	private JLabel departmentLabel;
////	private JLabel damagedLabel;
//	private JLabel nameItemViewLabel;
//	private JLabel descriptionItemViewLabel;
//	private JLabel departmentItemViewLabel;
//	private JLabel damagedItemViewLabel;
//	private JButton editItemViewButton;
//	private JButton borrowButton; 
//	private JButton btnBackView;
//	private JScrollPane scrollPaneView;
//	private JTextPane textPaneView;
//	private JButton editReturnViewButton;
//	private JScrollPane scrollPaneBorrowed;
//	private JTable borrowedItemTable;
//	private BorrowedItemTableModel borrowedByTableModel = new BorrowedItemTableModel();
//	private JLabel quantityAvailableLabel;
//	private JLabel quantityAvailableValueLabel;
//	private BorrowObject selectedReturn;
	
//	end edit panel variables
	
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
	private BorrowPanel borrowPanel;
//	private JLabel titleBorrowLabel;
//	private JLabel returnDateBorrowLabel;
	private NewPanel newPanel;
//	private JLabel titleNewLabel;
//	private JLabel nameNewLabel;
//	private JLabel descriptionNewLabel;
//	private JLabel damagedNewLabel;
//	private JLabel departmentNewLabel;
//	private JButton btnBackNew;
//	private JTextField nameNewTextField;
//	private JTextField descriptionNewTextField;
	private EditPanel editPanel;
//	private JLabel nameEditLabel;
//	private JLabel titleEditLabel;
//	private JLabel descriptionEditLabel;
//	private JLabel damagedEditLabel;
//	private JLabel departmentEditLabel;
//	private JTextField itemNameEditTextField;
//	private JComboBox damagedEditComboBox;
////	private JTextField descriptionEditTextField;
//	private JButton btnBackEdit;
//	private JComboBox departmentEditComboBox;
////	private JComboBox departmentNewComboBox;
//	private JButton btnSubmitEdit;
//	private JLabel msgEditLabel;
	private DepartmentComboBoxModel departmentModel = new DepartmentComboBoxModel();
//	private JScrollPane scrollPaneEdit;
//	private JTextArea descriptionEditTextArea;
//	private JSpinner quantityAvailableEditSpinner;
//	private JLabel quantityAvailableEditLabel;
//	private JTextField usernameBorrowTextField;
//	private JTextField returnDateDayTextField;
//	private JTextField returnDateMonthTextField;
//	private JTextField returnDateYearTextField;
//	private JLabel msgNewLabel;
	private ColorScheme colorScheme = new ColorScheme();
	private JScrollPane scrollPaneView;
	private JTextPane textPaneView;
	private JButton editReturnViewButton;
	private EditReturnPanel editReturnPanel;
	private JScrollPane scrollPaneBorrowed;
	private JTable borrowedItemTable;
	private BorrowedItemTableModel borrowedByTableModel = new BorrowedItemTableModel();
	private JLabel quantityAvailableLabel;
	private JLabel quantityAvailableValueLabel;
	private BorrowObject selectedReturn;
	private SearchPanel searchPanel;
	private UiController uiController;
	private JPanel borrowedPanel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UiMain2 window = new UiMain2();
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
	public UiMain2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		uiController = new UiController(tabbedPane);
		
		homePanel = new HomePanel(tableModel, uiController);
		tabbedPane.addTab("Home", null, homePanel, null);
		
		
		viewPanel = new ViewPanel(colorScheme, uiController);
		tabbedPane.addTab("View", null, viewPanel, null);
		
		//borrow panel
		
		borrowPanel = new BorrowPanel(colorScheme, uiController);
		tabbedPane.addTab("Borrow", null, borrowPanel, null);
		
		//new
		
		newPanel = new NewPanel(departmentModel, tableModel, colorScheme, uiController);
		tabbedPane.addTab("New", null, newPanel, null);
		
		// edit panel
		
		editPanel = new EditPanel(colorScheme, uiController);
		tabbedPane.addTab("Edit", null, editPanel, null);
		
		//edit return panel
		
		editReturnPanel = new EditReturnPanel(colorScheme, uiController);
		tabbedPane.addTab("Edit Return", null, editReturnPanel, null);
		
		//end edit return panel
		
		//search panel
		
		searchPanel = new SearchPanel(tableModel, uiController);
		tabbedPane.addTab("Search", null, searchPanel, null);
		
		// end search panel
		
		uiController.setPanels(homePanel, viewPanel, borrowPanel, newPanel, editPanel, editReturnPanel, searchPanel);
		
		borrowedPanel = new JPanel();
		tabbedPane.addTab("Borrowed", null, borrowedPanel, null);
		borrowedPanel.setLayout(null);
	}
}
