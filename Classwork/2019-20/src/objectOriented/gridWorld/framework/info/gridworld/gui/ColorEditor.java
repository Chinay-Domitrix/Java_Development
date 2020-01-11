/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * @author Cay Horstmann
 */

package objectOriented.gridWorld.framework.info.gridworld.gui;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyEditorSupport;

/**
 * A property editor for the Color type. <br />
 * This code is not tested on the AP CS A and AB exams. It contains GUI
 * implementation details that are not intended to be understood by AP CS
 * students.
 */
public class ColorEditor extends PropertyEditorSupport {
	private static Color[] colorValues =
			{Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY,
					Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
					Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};
	private static ColorIcon[] colorIcons;

	static {
		colorIcons = new ColorIcon[colorValues.length + 1];
		colorIcons[0] = new RandomColorIcon();
		for (int i = 0; i < colorValues.length; i++)
			colorIcons[i + 1] = new SolidColorIcon(colorValues[i]);
	}

	private JComboBox combo;

	public ColorEditor() {
		combo = new JComboBox(colorIcons);
	}

	public Object getValue() {
		ColorIcon value = (ColorIcon) combo.getSelectedItem();
		assert value != null;
		return value.getColor();
	}

	public boolean supportsCustomEditor() {
		return true;
	}

	public Component getCustomEditor() {
		combo.setSelectedItem(0);
		return combo;
	}

	private interface ColorIcon extends Icon {
		int WIDTH = 120;
		int HEIGHT = 20;

		Color getColor();
	}

	private static class SolidColorIcon implements ColorIcon {
		private Color color;

		public SolidColorIcon(Color c) {
			color = c;
		}

		public Color getColor() {
			return color;
		}

		public int getIconWidth() {
			return WIDTH;
		}

		public int getIconHeight() {
			return HEIGHT;
		}

		public void paintIcon(Component c, Graphics g, int x, int y) {
			Rectangle r = new Rectangle(x, y, WIDTH - 1, HEIGHT - 1);
			Graphics2D g2 = (Graphics2D) g;
			Color oldColor = g2.getColor();
			g2.setColor(color);
			g2.fill(r);
			g2.setColor(Color.BLACK);
			g2.draw(r);
			g2.setColor(oldColor);
		}
	}

	private static class RandomColorIcon implements ColorIcon {
		public Color getColor() {
			return new Color((int) (Math.random() * 256 * 256 * 256));
		}

		public int getIconWidth() {
			return WIDTH;
		}

		public int getIconHeight() {
			return HEIGHT;
		}

		public void paintIcon(Component c, Graphics g, int x, int y) {
			Rectangle r = new Rectangle(x, y, WIDTH - 1, HEIGHT - 1);
			Graphics2D g2 = (Graphics2D) g;
			Color oldColor = g2.getColor();
			Rectangle r1 = new Rectangle(x, y, WIDTH / 4, HEIGHT - 1);
			for (int i = 0; i < 4; i++) {
				g2.setColor(getColor());
				g2.fill(r1);
				r1.translate(WIDTH / 4, 0);
			}
			g2.setColor(Color.BLACK);
			g2.draw(r);
			g2.setColor(oldColor);
		}
	}
}
