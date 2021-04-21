package practiceIA;

import java.awt.Color;

public class ColorScheme {
	private Color failColor = new Color(0xff0000);
	private Color successColor = new Color(0x00ef00);
	private Color defaultColor = new Color(0xffffff);
	private Color backgroundColor = new Color(0xf0f0f0);
	
	public Color getFail() {
		return failColor;
	}
	
	public Color getSuccess() {
		return successColor;
	}
	
	public Color getDefault() {
		return defaultColor;
	}
	
	public Color getBackground() {
		return backgroundColor;
	}
}
