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

package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.gui;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static java.lang.Math.max;
import static java.util.stream.IntStream.iterate;

/**
 * A layout manager that lays out components along a central axis <br />
 * This code is not tested on the AP CS A and AB exams. It contains GUI
 * implementation details that are not intended to be understood by AP CS
 * students.
 */
public class FormLayout implements LayoutManager {
	private static final int GAP = 6;
	private int left;
	private int right;
	private int height;

	public Dimension preferredLayoutSize(@NotNull Container parent) {
		Component[] components = parent.getComponents();
		left = 0;
		right = 0;
		height = 0;
		iterate(0, i -> i < components.length, i -> i + 2).forEachOrdered(i -> {
			Component cleft = components[i];
			Component cright = components[i + 1];
			Dimension dleft = cleft.getPreferredSize();
			Dimension dright = cright.getPreferredSize();
			left = max(left, dleft.width);
			right = max(right, dright.width);
			height = height + max(dleft.height, dright.height);
		});
		return new Dimension(left + GAP + right, height);
	}

	public Dimension minimumLayoutSize(Container parent) {
		return preferredLayoutSize(parent);
	}

	public void layoutContainer(Container parent) {
		preferredLayoutSize(parent); // sets left, right
		Component[] components = parent.getComponents();
		Insets insets = parent.getInsets();
		int xcenter = insets.left + left;
		int y = insets.top;
		for (int i = 0; i < components.length; i += 2) {
			Component cleft = components[i];
			Component cright = components[i + 1];
			Dimension dleft = cleft.getPreferredSize();
			Dimension dright = cright.getPreferredSize();
			int height = max(dleft.height, dright.height);
			cleft.setBounds(xcenter - dleft.width, y + (height - dleft.height) / 2, dleft.width, dleft.height);
			cright.setBounds(xcenter + GAP, y + (height - dright.height) / 2, dright.width, dright.height);
			y += height;
		}
	}

	public void addLayoutComponent(String name, Component comp) {
	}

	public void removeLayoutComponent(Component comp) {
	}
}
