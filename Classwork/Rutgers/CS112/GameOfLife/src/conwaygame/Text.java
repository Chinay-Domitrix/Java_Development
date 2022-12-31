package conwaygame;
/*
 * Class used to create label text for various aspects of the driver.
 */

import java.awt.*;

import static conwaygame.StdDraw.*;

public class Text {
	public int x;
	public int y;
	public String text;
	public Color color;
	public String orientation;

	public Text(int x, int y, String text, String orientation) {
		this.x = x;
		this.y = y;
		this.text = text;
		this.orientation = orientation;
	}

	public Text(int x, int y, String text) {
		this(x, y, text, null);
	}

	public void draw() {
		color = getPenColor();
		if ("LEFT".equals(orientation)) textLeft(x, y, text);
		else if ("RIGHT".equals(orientation)) textRight(x, y, text);
		else text(x, y, text);
	}

	public void changeColor(Color c) {
		var tmp = getPenColor();
		setPenColor(c);
		draw();
		setPenColor(tmp);
	}
}
