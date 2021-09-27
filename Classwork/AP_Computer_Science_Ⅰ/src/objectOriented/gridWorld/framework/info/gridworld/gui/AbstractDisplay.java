/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board
 * (http://www.collegeboard.com).
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
 * @author Julie Zelenski
 * @author Cay Horstmann
 */

package objectOriented.gridWorld.framework.info.gridworld.gui;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import static java.lang.Math.min;
import static java.lang.Math.toRadians;
import static java.lang.System.out;
import static java.util.Objects.requireNonNullElse;

/**
 * This class provides common implementation code for drawing objects. It will
 * translate, scale, and rotate the graphics system as needed and then invoke
 * its abstract <code>draw</code> method. Subclasses of this abstract class
 * define <code>draw</code> to display an object in a fixed size and
 * orientation. <br />
 * This code is not tested on the AP CS A and AB exams. It contains GUI
 * implementation details that are not intended to be understood by AP CS
 * students.
 */

public abstract class AbstractDisplay implements Display {
	@Contract("null, _ -> null")
	public static Object getProperty(Object obj, String propertyName) {
		if (obj == null)
			return null;
		try {
			BeanInfo info = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] descriptors = info.getPropertyDescriptors();
			for (PropertyDescriptor descriptor : descriptors)
				if (descriptor.getName().equals(propertyName)) {
					Method getter = descriptor.getReadMethod();
					if (getter == null)
						return null;
					try {
						return getter.invoke(obj);
					} catch (Exception ex) {
						out.println(descriptor.getName());
						return null;
					}
				}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Draw the given object. Subclasses should implement this method to draw the
	 * occupant facing North in a cell of size (1,1) centered around (0,0) on the
	 * drawing surface. (All scaling/rotating has been done beforehand).
	 *
	 * @param obj  the occupant we want to draw
	 * @param comp the component on which to draw
	 * @param g2   the graphics context
	 */
	abstract public void draw(Object obj, Component comp, Graphics2D g2);

	/**
	 * Draw the given object. Scales and rotates the coordinate appropriately then
	 * invokes the simple draw method above that is only responsible for drawing a
	 * unit-length occupant facing North.
	 *
	 * @param obj  the occupant we want to draw
	 * @param comp the component on which to draw
	 * @param g2   the graphics context
	 * @param rect rectangle in which to draw
	 */
	public void draw(Object obj, Component comp, Graphics2D g2, @NotNull Rectangle rect) {
		float scaleFactor = min(rect.width, rect.height);
		g2 = (Graphics2D) g2.create();
		// Translate to center of the object
		g2.translate(rect.x + (rect.width / 2.0), rect.y + (rect.height / 2.0));
		// Rotate drawing surface before drawing to capture object's
		// orientation (direction).
		if (obj != null) {
			int rotationInDegrees = requireNonNullElse((Integer) getProperty(obj, "direction"), 0);
			g2.rotate(toRadians(rotationInDegrees));
		}
		// Scale to size of rectangle, adjust stroke back to 1-pixel wide
		g2.scale(scaleFactor, scaleFactor);
		g2.setStroke(new BasicStroke(1.0f / scaleFactor));
		draw(obj, comp, g2);
	}
}
