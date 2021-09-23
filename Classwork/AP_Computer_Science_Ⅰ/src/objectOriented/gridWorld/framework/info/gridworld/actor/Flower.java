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

package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor;

import java.awt.*;

import static java.awt.Color.PINK;

/**
 * A <code>Flower</code> is an actor that darkens over time. Some actors drop
 * flowers as they move. <br />
 * The API of this class is testable on the AP CS A and AB exams.
 */

public class Flower extends Actor {
	private static final Color DEFAULT_COLOR = PINK;

	// lose 5% of color value in each step

	/**
	 * Constructs a pink flower.
	 */
	public Flower() {
		setColor(DEFAULT_COLOR);
	}

	/**
	 * Constructs a flower of a given color.
	 *
	 * @param initialColor the initial color of this flower
	 */
	public Flower(Color initialColor) {
		setColor(initialColor);
	}

	/**
	 * Causes the color of this flower to darken.
	 */
	public void act() {
		Color c = getColor();
		int red = (int) (c.getRed() * 0.95), green = (int) (c.getGreen() * 0.95), blue = (int) (c.getBlue() * 0.95);
		setColor(new Color(red, green, blue));
	}
}
