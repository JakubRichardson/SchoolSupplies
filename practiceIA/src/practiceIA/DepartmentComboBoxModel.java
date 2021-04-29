package practiceIA;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

public class DepartmentComboBoxModel implements ComboBoxModel<String>{
	private String[] presetDepartments = new String[] {"Art", "Biology","Buisness", "Chemistry", "Computer Science", "Economics", "Physics", "Maths"};
//	"Chemistry", "Biology", "Computer Science", "Physics"
	
	private ArrayList<String> departments = new ArrayList<>();
	private ArrayList<ListDataListener> listeners = new ArrayList<>();
	private Object selectedItem;
	
	
	DepartmentComboBoxModel() {
		departments.addAll(Arrays.asList(presetDepartments));
	}
	
	public void addDepartment(String department) {
		if(!departments.contains(department)) {
			departments.add(department);
		}
	}
	
	@Override
	public void addListDataListener(ListDataListener arg0) {
		listeners.add(arg0);
	}

	@Override
	public String getElementAt(int arg0) {
		return departments.get(arg0);
	}

	@Override
	public int getSize() {
		return departments.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		listeners.remove(arg0);
	}

	@Override
	public Object getSelectedItem() {
		return selectedItem;
	}

	@Override
	public void setSelectedItem(Object arg0) {
		selectedItem = arg0;
	}
	
}
