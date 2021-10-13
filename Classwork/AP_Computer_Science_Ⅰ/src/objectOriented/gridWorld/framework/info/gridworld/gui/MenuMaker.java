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

import objectOriented.gridWorld.framework.info.gridworld.grid.Grid;
import objectOriented.gridWorld.framework.info.gridworld.grid.Location;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static java.awt.Color.BLACK;
import static java.awt.event.ItemEvent.SELECTED;
import static java.beans.PropertyEditorManager.findEditor;
import static java.lang.Boolean.FALSE;
import static java.lang.Math.min;
import static java.util.Arrays.sort;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;
import static javax.swing.JOptionPane.*;

/**
 * Makes the menus for constructing new occupants and grids, and for invoking
 * methods on existing occupants. <br />
 * This code is not tested on the AP CS A and AB exams. It contains GUI
 * implementation details that are not intended to be understood by AP CS
 * students.
 */
public class MenuMaker<T> {
	private final WorldFrame<T> parent;
	private final DisplayMap displayMap;
	private final ResourceBundle resources;
	private T occupant;
	private Grid<?> currentGrid;
	private Location currentLocation;

	/**
	 * Constructs a menu maker for a given world.
	 *
	 * @param parent     the frame in which the world is displayed
	 * @param resources  the resource bundle
	 * @param displayMap the display map
	 */
	@Contract(pure = true)
	public MenuMaker(WorldFrame<T> parent, ResourceBundle resources, DisplayMap dispMap) {
		this.parent = parent;
		this.resources = resources;
		displayMap = dispMap;
	}

	/**
	 * Makes a menu that displays all public methods of an object
	 *
	 * @param occupant the object whose methods should be displayed
	 * @param loc      the location of the occupant
	 * @return the menu to pop up
	 */
	public JPopupMenu makeMethodMenu(T occupant, Location loc) {
		this.occupant = occupant;
		this.currentLocation = loc;
		JPopupMenu menu = new JPopupMenu();
		Method[] methods = getMethods();
		var oldDcl = (Class<?>) null;
		for (int i = 0; i < methods.length; i++) {
			var dcl = methods[i].getDeclaringClass();
			if (dcl != Object.class) {
				if (i > 0 && dcl != oldDcl)
					menu.addSeparator();
				menu.add(new MethodItem(methods[i]));
			}
			oldDcl = dcl;
		}
		return menu;
	}

	/**
	 * Makes a menu that displays all public constructors of a collection of
	 * classes.
	 *
	 * @param classes the classes whose constructors should be displayed
	 * @param loc     the location of the occupant to be constructed
	 * @return the menu to pop up
	 */
	public JPopupMenu makeConstructorMenu(Set<Class<?>> classes, Location loc) {
		this.currentLocation = loc;
		JPopupMenu menu = new JPopupMenu();
		boolean first = true;
		for (Class<?> aClass : classes) {
			if (first)
				first = false;
			else
				menu.addSeparator();
			var cons = aClass.getConstructors();
			stream(cons).map(OccupantConstructorItem::new).forEachOrdered(menu::add);
		}
		return menu;
	}

	/**
	 * Adds menu items that call all public constructors of a collection of classes
	 * to a menu
	 *
	 * @param menu    the menu to which the items should be added
	 * @param classes the collection of classes
	 */
	public void addConstructors(JMenu menu, @NotNull Collection<Class<?>> classes) {
		boolean first = true;
		for (var aClass : classes) {
			if (first)
				first = false;
			else
				menu.addSeparator();
			var cons = aClass.getConstructors();
			stream(cons).map(GridConstructorItem::new).forEachOrdered(menu::add);
		}
	}

	private Method[] getMethods() {
		var cl = occupant.getClass();
		Method[] methods = cl.getMethods();
		sort(methods, new Comparator<>() {
			public int compare(Method m1, Method m2) {
				int d1 = depth(m1.getDeclaringClass());
				int d2 = depth(m2.getDeclaringClass());
				if (d1 != d2)
					return d2 - d1;
				int d = m1.getName().compareTo(m2.getName());
				if (d != 0)
					return d;
				d1 = m1.getParameterTypes().length;
				d2 = m2.getParameterTypes().length;
				return d1 - d2;
			}

			private int depth(Class<?> cl) {
				if (cl == null)
					return 0;
				else
					return 1 + depth(cl.getSuperclass());
			}
		});
		return methods;
	}

	/**
	 * A menu item that shows a method or constructor.
	 */
	private class MCItem extends JMenuItem {
		public String getDisplayString(Class<?> retType, String name, Class<?>[] paramTypes) {
			StringBuffer b = new StringBuffer();
			b.append("<html>");
			if (retType != null)
				appendTypeName(b, retType.getName());
			b.append(" <font color='blue'>");
			appendTypeName(b, name);
			b.append("</font>( ");
			range(0, paramTypes.length).forEachOrdered(i -> {
				if (i > 0)
					b.append(", ");
				appendTypeName(b, paramTypes[i].getName());
			});
			b.append(" )</html>");
			return b.toString();
		}

		public void appendTypeName(StringBuffer b, @NotNull String name) {
			int i = name.lastIndexOf('.');
			if (i >= 0) {
				String prefix = name.substring(0, i + 1);
				if (!prefix.equals("java.lang")) {
					b.append("<font color='gray'>").append(prefix).append("</font>");
				}
				b.append(name.substring(i + 1));
			} else
				b.append(name);
		}

		public Object makeDefaultValue(Class<?> type) {
			if (type == int.class)
				return 0;
			else if (type == boolean.class)
				return FALSE;
			else if (type == double.class)
				return (double) 0;
			else if (type == String.class)
				return "";
			else if (type == Color.class)
				return BLACK;
			else if (type == Location.class)
				return currentLocation;
			else if (Grid.class.isAssignableFrom(type))
				return currentGrid;
			else {
				try {
					return type.getEnclosingConstructor().newInstance();
				} catch (Exception ex) {
					return null;
				}
			}
		}
	}

	private abstract class ConstructorItem extends MCItem {
		private final Constructor<?> c;

		public ConstructorItem(@NotNull Constructor<?> c) {
			setText(getDisplayString(null, c.getDeclaringClass().getName(), c.getParameterTypes()));
			this.c = c;
		}

		public Object invokeConstructor() {
			var types = c.getParameterTypes();
			Object[] values = new Object[types.length];
			for (int i = 0; i < types.length; i++)
				values[i] = makeDefaultValue(types[i]);
			if (types.length > 0) {
				PropertySheet sheet = new PropertySheet(types, values);
				showMessageDialog(this, sheet, resources.getString("dialog.method.params"), QUESTION_MESSAGE);
				values = sheet.getValues();
			}
			try {
				return c.newInstance(values);
			} catch (InvocationTargetException ex) {
				parent.new GUIExceptionHandler().handle(ex.getCause());
				return null;
			} catch (Exception ex) {
				parent.new GUIExceptionHandler().handle(ex);
				return null;
			}
		}
	}

	private class OccupantConstructorItem extends ConstructorItem implements ActionListener {
		public OccupantConstructorItem(Constructor<?> c) {
			super(c);
			addActionListener(this);
			setIcon(displayMap.getIcon(c.getDeclaringClass(), 16, 16));
		}

		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent event) {
			T result = (T) invokeConstructor();
			parent.getWorld().add(currentLocation, result);
			parent.repaint();
		}
	}

	private class GridConstructorItem extends ConstructorItem implements ActionListener {
		public GridConstructorItem(Constructor<?> c) {
			super(c);
			addActionListener(this);
			setIcon(displayMap.getIcon(c.getDeclaringClass(), 16, 16));
		}

		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent event) {
			Grid<T> newGrid = (Grid<T>) invokeConstructor();
			parent.setGrid(newGrid);
		}
	}

	private class MethodItem extends MCItem implements ActionListener {
		private final Method m;

		public MethodItem(Method m) {
			setText(getDisplayString(m.getReturnType(), m.getName(), m.getParameterTypes()));
			this.m = m;
			addActionListener(this);
			setIcon(displayMap.getIcon(m.getDeclaringClass(), 16, 16));
		}

		public void actionPerformed(ActionEvent event) {
			var types = m.getParameterTypes();
			Object[] values = new Object[types.length];
			for (int i = 0; i < types.length; i++)
				values[i] = makeDefaultValue(types[i]);
			if (types.length > 0) {
				PropertySheet sheet = new PropertySheet(types, values);
				showMessageDialog(this, sheet, resources.getString("dialog.method.params"), QUESTION_MESSAGE);
				values = sheet.getValues();
			}
			try {
				Object result = m.invoke(occupant, values);
				parent.repaint();
				if (m.getReturnType() != void.class) {
					String resultString = result.toString();
					java.io.Serializable resultObject;
					final int MAX_LENGTH = 50;
					final int MAX_HEIGHT = 10;
					if (resultString.length() < MAX_LENGTH)
						resultObject = resultString;
					else {
						int rows = min(MAX_HEIGHT, 1 + resultString.length() / MAX_LENGTH);
						JTextArea pane = new JTextArea(rows, MAX_LENGTH);
						pane.setText(resultString);
						pane.setLineWrap(true);
						resultObject = new JScrollPane(pane);
					}
					showMessageDialog(parent, resultObject, resources.getString("dialog.method.return"),
							INFORMATION_MESSAGE);
				}
			} catch (InvocationTargetException ex) {
				parent.new GUIExceptionHandler().handle(ex.getCause());
			} catch (Exception ex) {
				parent.new GUIExceptionHandler().handle(ex);
			}
		}
	}
}

class PropertySheet extends JPanel {
	private static final Map<Class<?>, PropertyEditor> defaultEditors;

	static {
		defaultEditors = new HashMap<>();
		defaultEditors.put(String.class, new StringEditor());
		defaultEditors.put(Location.class, new LocationEditor());
		defaultEditors.put(Color.class, new ColorEditor());
	}

	private final PropertyEditor[] editors;
	private final Object[] values;

	/**
	 * Constructs a property sheet that shows the editable properties of a given
	 * object.
	 *
	 * @param values the object whose properties are being edited
	 */
	public PropertySheet(Class<?>[] types, Object[] values) {
		this.values = values;
		editors = new PropertyEditor[types.length];
		setLayout(new FormLayout());
		range(0, values.length).forEachOrdered(i -> {
			JLabel label = new JLabel(types[i].getName());
			add(label);
			if (Grid.class.isAssignableFrom(types[i])) {
				label.setEnabled(false);
				add(new JPanel());
			} else {
				editors[i] = getEditor(types[i]);
				if (editors[i] != null) {
					editors[i].setValue(values[i]);
					add(getEditorComponent(editors[i]));
				} else
					add(new JLabel("?"));
			}
		});
	}

	/**
	 * Gets the property editor for a given property, and wires it so that it
	 * updates the given object.
	 *
	 * @return a property editor that edits the property with the given descriptor
	 * and updates the given object
	 */
	public PropertyEditor getEditor(Class<?> type) {
		PropertyEditor editor;
		editor = defaultEditors.get(type);
		if (editor != null)
			return editor;
		editor = findEditor(type);
		return editor;
	}

	/**
	 * Wraps a property editor into a component.
	 *
	 * @param editor the editor to wrap
	 * @return a button (if there is a custom editor), combo box (if the editor has
	 * tags), or text field (otherwise)
	 */
	public Component getEditorComponent(@NotNull final PropertyEditor editor) {
		String[] tags = editor.getTags();
		String text = editor.getAsText();
		if (editor.supportsCustomEditor()) {
			return editor.getCustomEditor();
		} else if (tags != null) {
			// make a combo box that shows all tags
			final JComboBox<? extends String> comboBox = new JComboBox<>(tags);
			comboBox.setSelectedItem(text);
			comboBox.addItemListener(event -> {
				if (event.getStateChange() == SELECTED)
					editor.setAsText((String) comboBox.getSelectedItem());
			});
			return comboBox;
		} else {
			final JTextField textField = new JTextField(text, 10);
			textField.getDocument().addDocumentListener(new DocumentListener() {
				public void insertUpdate(DocumentEvent e) {
					try {
						editor.setAsText(textField.getText());
					} catch (IllegalArgumentException ignored) {
					}
				}

				public void removeUpdate(DocumentEvent e) {
					try {
						editor.setAsText(textField.getText());
					} catch (IllegalArgumentException ignored) {
					}
				}

				public void changedUpdate(DocumentEvent e) {
				}
			});
			return textField;
		}
	}

	public Object[] getValues() {
		range(0, editors.length).filter(i -> editors[i] != null).forEachOrdered(i -> values[i] = editors[i].getValue());
		return values;
	}

	// workaround for Web Start bug
	public static class StringEditor extends PropertyEditorSupport {
		public String getAsText() {
			return (String) getValue();
		}

		public void setAsText(String s) {
			setValue(s);
		}
	}
}
