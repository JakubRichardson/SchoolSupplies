package practiceIA;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicTabbedPaneUI;



public class EnhancedTabbedPane extends JTabbedPane {
	private static final long serialVersionUID = -4093130253460783895L;
	private boolean showTabs = true;

	private class MyUI extends BasicTabbedPaneUI {
		
		@Override
		protected int calculateTabAreaHeight(int tabPlacement, int horizontalRunCount, int maxTabHeight) {
			return showTabs ? super.calculateTabAreaHeight(tabPlacement, horizontalRunCount, maxTabHeight) : 0;
		}

		@Override
		protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect) {
			if (showTabs) super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);
		}
	}

	public EnhancedTabbedPane() {
		this(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
	}

	public EnhancedTabbedPane(int tabPlacement) {
		this(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
	}

	public EnhancedTabbedPane(int tabPlacement, int tabLayoutPolicy) {
		super(tabPlacement, tabLayoutPolicy);
		setUI(new MyUI());
	}

	public void setTabsVisible(boolean isVisible) {
		this.showTabs = isVisible;
		repaint();
	}
	
}