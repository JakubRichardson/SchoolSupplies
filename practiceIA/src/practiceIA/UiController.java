package practiceIA;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class UiController {
	private JTabbedPane tabbedPane;
	private ViewPanel viewPanel;
	private HomePanel homePanel;
	private BorrowPanel borrowPanel;
	private NewPanel newPanel;
	private EditPanel editPanel;
	private EditReturnPanel editReturnPanel;
	private SearchPanel searchPanel;
	private BorrowedItemPanel borrowedItemPanel;
	private AddDepartmentPanel addDepartmentPanel;
	private ArrayList<Integer> panelSelectStack = new ArrayList<>(); 
	
	UiController(JTabbedPane tabbedPane) {
		panelSelectStack.add(0);
		this.tabbedPane = tabbedPane;
	}
	
	public void setPanels(HomePanel homePanel, ViewPanel viewPanel, BorrowPanel borrowPanel, NewPanel newPanel, EditPanel editPanel, EditReturnPanel editReturnPanel, SearchPanel searchPanel, BorrowedItemPanel borrowedItemPanel,  AddDepartmentPanel addDepartmentPanel) {
		this.homePanel = homePanel;
		this.viewPanel = viewPanel;
		this.borrowPanel = borrowPanel;
		this.newPanel = newPanel;
		this.editPanel = editPanel;
		this.editReturnPanel = editReturnPanel;
		this.searchPanel = searchPanel;
		this.borrowedItemPanel = borrowedItemPanel;
		this.addDepartmentPanel = addDepartmentPanel;
	}
	
	public void setSelected(int index) {
		panelSelectStack.add(index);
		tabbedPane.setSelectedIndex(index);
	}
	
	public void popStack() {
		if(panelSelectStack.size() == 0) {
			System.out.println("Stack is empty");
			return;
		}
		panelSelectStack.remove(panelSelectStack.size() - 1);
	}
	
	public void popStack(int repeats) {
		for(int i = 0; i < repeats; i++) {
			this.popStack();
		}
	}
	
	public void back() {
		this.popStack();
		int index = panelSelectStack.get(panelSelectStack.size() - 1);
		tabbedPane.setSelectedIndex(index);
		if(index == 0) homePanel.refreshBorrowButton();
	}
	
	public void goToHome() {
		setSelected(0);
	}
	
//	public void goToHome(String msg, Color color) {
//		homePanel.setMsg(msg);
//		homePanel.setMsgColor(color);
//		setSelected(0);
//	}
	
	
	public void goToBorrow(Item item) {
		borrowPanel.setItem(item);
		borrowPanel.populateBorrow();
		this.setSelected(2);
	}
	
	public void goToEdit(Item item) {
		editPanel.populateEdit(item);
		this.setSelected(4);
	}
	
	public void goToNew() {
		setSelected(3);
	}
	
	public void goToSearch() {
		setSelected(6);
	}
	
	public void goToEditReturn(Item item, BorrowObject selectedReturn) {
		editReturnPanel.setItem(item);
		editReturnPanel.setReturn(selectedReturn);
		editReturnPanel.populatePanel();
		this.setSelected(5);
	}
	
	public void goToView(Item item) {
		viewPanel.populateView(item);
		this.setSelected(1);
	}
	
	public void refreshBorrowedItems() {
		borrowedItemPanel.refresh();
	}
	
	public void goToBorrowedItems() {
		borrowedItemPanel.refresh();
		this.setSelected(7);
	}
	
	public void goToAddDepartment() {
		this.setSelected(8);
	}
	
}
