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

import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.grid.Location;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

/**
 * A property editor for the Location type. <br />
 * This code is not tested on the AP CS A and AB exams. It contains GUI
 * implementation details that are not intended to be understood by AP CS
 * students.
 */
public class LocationEditor extends PropertyEditorSupport {
	private final JFormattedTextField rowField = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private final JFormattedTextField colField = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private final JPanel panel = new JPanel();

	public LocationEditor() {
		rowField.setColumns(5);
		colField.setColumns(5);
		panel.add(rowField);
		panel.add(colField);
	}

	public Object getValue() {
		int row = ((Number) rowField.getValue()).intValue();
		int col = ((Number) colField.getValue()).intValue();
		return new Location(row, col);
	}

	public void setValue(Object newValue) {
		Location loc = (Location) newValue;
		rowField.setValue(loc.getRow());
		colField.setValue(loc.getCol());
	}

	public boolean supportsCustomEditor() {
		return true;
	}

	public Component getCustomEditor() {
		return panel;
	}
}
