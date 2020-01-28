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
 * @author Chris Nevison
 * @author Cay Horstmann
 */

package objectOriented.gridWorld.framework.info.gridworld.gui;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.*;

import static java.awt.BorderLayout.*;
import static java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager;
import static java.awt.event.KeyEvent.CHAR_UNDEFINED;
import static java.lang.Character.toUpperCase;
import static java.lang.Class.forName;
import static java.lang.System.*;
import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.toMap;
import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.JOptionPane.*;
import static javax.swing.KeyStroke.getKeyStroke;
import static javax.swing.KeyStroke.getKeyStrokeForEvent;
import static javax.swing.event.HyperlinkEvent.EventType.ACTIVATED;

/**
 * The WorldFrame displays a World and allows manipulation of its occupants.
 * <br />
 * This code is not tested on the AP CS A and AB exams. It contains GUI
 * implementation details that are not intended to be understood by AP CS
 * students.
 */
public class WorldFrame<T> extends JFrame {
	private static int count = 0;
	private GUIController<T> control;
	private GridPanel display;
	private JTextArea messageArea;
	private ArrayList<JMenuItem> menuItemsDisabledDuringRun;
	private World<T> world;
	private ResourceBundle resources;
	private DisplayMap displayMap;
	private Set<Class> gridClasses;
	private JMenu newGridMenu;

	/**
	 * Constructs a WorldFrame that displays the occupants of a world
	 *
	 * @param world the world to display
	 */
	public WorldFrame(World<T> world) {
		this.world = world;
		count++;
		resources = ResourceBundle.getBundle(getClass().getName() + "Resources");
		try {
			setProperty("sun.awt.exception.handler", GUIExceptionHandler.class.getName());
		} catch (SecurityException ex) {
			// will fail in an applet
		}
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				count--;
				if (count == 0) exit(0);
			}
		});
		displayMap = new DisplayMap();
		String title = getProperty("info.gridworld.gui.frametitle");
		if (title == null) title = resources.getString("frame.title");
		setTitle(title);
		setLocation(25, 15);
		URL appIconUrl = getClass().getResource("GridWorld.gif");
		ImageIcon appIcon = new ImageIcon(appIconUrl);
		setIconImage(appIcon.getImage());
		makeMenus();
		JPanel content = new JPanel();
		content.setBorder(createEmptyBorder(15, 15, 15, 15));
		content.setLayout(new BorderLayout());
		setContentPane(content);
		display = new GridPanel(displayMap, resources);
		getCurrentKeyboardFocusManager().addKeyEventDispatcher(event -> {
			if (getFocusOwner() == null) return false;
			String text = getKeyStrokeForEvent(event).toString();
			final String PRESSED = "pressed ";
			int n = text.indexOf(PRESSED);
			if (n < 0) return false;
			// filter out modifier keys; they are neither characters or actions
			if ((event.getKeyChar() == CHAR_UNDEFINED) && !event.isActionKey()) return false;
			text = text.substring(0, n) + text.substring(n + PRESSED.length());
			boolean consumed = getWorld().keyPressed(text, display.getCurrentLocation());
			if (consumed) repaint();
			return consumed;
		});
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewport(new PseudoInfiniteViewport(scrollPane));
		scrollPane.setViewportView(display);
		content.add(scrollPane, CENTER);
		gridClasses = new TreeSet<>(Comparator.comparing(Class::getName));
		world.getGridClasses().forEach(name -> {
			try {
				gridClasses.add(forName(name));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		Grid<T> gr = world.getGrid();
		gridClasses.add(gr.getClass());
		makeNewGridMenu();
		control = new GUIController<>(this, display, displayMap, resources);
		content.add(control.controlPanel(), SOUTH);
		messageArea = new JTextArea(2, 35);
		messageArea.setEditable(false);
		messageArea.setFocusable(false);
		messageArea.setBackground(new Color(16448210));
		content.add(new JScrollPane(messageArea), NORTH);
		pack();
		repaint(); // to show message
		display.setGrid(gr);
	}

	public void repaint() {
		String message = getWorld().getMessage();
		if (message == null) message = resources.getString("message.default");
		messageArea.setText(message);
		messageArea.repaint();
		display.repaint(); // for applet
		super.repaint();
	}

	/**
	 * Gets the world that this frame displays
	 *
	 * @return the world
	 */
	public World<T> getWorld() {
		return world;
	}

	/**
	 * Sets a new grid for this world. Occupants are transferred from
	 * the old world to the new.
	 *
	 * @param newGrid the new grid
	 */
	public void setGrid(Grid<T> newGrid) {
		Grid<T> oldGrid = world.getGrid();
		Map<Location, T> occupants = oldGrid.getOccupiedLocations().stream().collect(toMap(loc -> loc, loc -> world.remove(loc), (a, b) -> b));
		world.setGrid(newGrid);
		occupants.keySet().stream().filter(newGrid::isValid).forEachOrdered(loc -> world.add(loc, occupants.get(loc)));
		display.setGrid(newGrid);
		repaint();
	}

	/**
	 * Displays an error message
	 *
	 * @param t        the throwable that describes the error
	 * @param resource the resource whose .text/.title strings
	 *                 should be used in the dialog
	 */
	public void showError(Throwable t, String resource) {
		String text;
		try {
			text = resources.getString(resource + ".text");
		} catch (MissingResourceException e) {
			text = resources.getString("error.text");
		}
		String title;
		try {
			title = resources.getString(resource + ".title");
		} catch (MissingResourceException e) {
			title = resources.getString("error.title");
		}
		String reason = resources.getString("error.reason");
		String message = text + "\n" + format(reason, t);
		showMessageDialog(this, message, title, ERROR_MESSAGE);
	}

	// Creates the drop-down menus on the frame.

	@NotNull
	private JMenu makeMenu(String resource) {
		JMenu menu = new JMenu();
		configureAbstractButton(menu, resource);
		return menu;
	}

	@NotNull
	private JMenuItem makeMenuItem(String resource, ActionListener listener) {
		JMenuItem item = new JMenuItem();
		configureMenuItem(item, resource, listener);
		return item;
	}

	private void configureMenuItem(JMenuItem item, String resource, ActionListener listener) {
		configureAbstractButton(item, resource);
		item.addActionListener(listener);
		try {
			String accel = resources.getString(resource + ".accel");
			String metaPrefix = "@";
			if (accel.startsWith(metaPrefix)) {
				int menuMask = getToolkit().getMenuShortcutKeyMaskEx();
				KeyStroke key = getKeyStroke(getKeyStroke(accel.substring(metaPrefix.length())).getKeyCode(), menuMask);
				item.setAccelerator(key);
			} else {
				item.setAccelerator(getKeyStroke(accel));
			}
		} catch (MissingResourceException ex) {
			// no accelerator
		}
	}

	private void configureAbstractButton(AbstractButton button, String resource) {
		String title = resources.getString(resource);
		int i = title.indexOf('&');
		int mnemonic = 0;
		if (i >= 0) {
			mnemonic = title.charAt(i + 1);
			title = title.substring(0, i) + title.substring(i + 1);
			button.setText(title);
			button.setMnemonic(toUpperCase(mnemonic));
			button.setDisplayedMnemonicIndex(i);
		} else button.setText(title);
	}

	private void makeMenus() {
		JMenuBar mbar = new JMenuBar();
		JMenu menu;
		menuItemsDisabledDuringRun = new ArrayList<>();
		mbar.add(menu = makeMenu("menu.file"));
		newGridMenu = makeMenu("menu.file.new");
		menu.add(newGridMenu);
		menuItemsDisabledDuringRun.add(newGridMenu);
		menu.add(makeMenuItem("menu.file.quit", e -> exit(0)));
		mbar.add(menu = makeMenu("menu.view"));
		menu.add(makeMenuItem("menu.view.up", e -> display.moveLocation(-1, 0)));
		menu.add(makeMenuItem("menu.view.down", e -> display.moveLocation(1, 0)));
		menu.add(makeMenuItem("menu.view.left", e -> display.moveLocation(0, -1)));
		menu.add(makeMenuItem("menu.view.right", e -> display.moveLocation(0, 1)));
		JMenuItem viewEditMenu;
		menu.add(viewEditMenu = makeMenuItem("menu.view.edit", e -> control.editLocation()));
		menuItemsDisabledDuringRun.add(viewEditMenu);
		JMenuItem viewDeleteMenu;
		menu.add(viewDeleteMenu = makeMenuItem("menu.view.delete", e -> control.deleteLocation()));
		menuItemsDisabledDuringRun.add(viewDeleteMenu);
		menu.add(makeMenuItem("menu.view.zoomin", e -> display.zoomIn()));
		menu.add(makeMenuItem("menu.view.zoomout", e -> display.zoomOut()));
		mbar.add(menu = makeMenu("menu.help"));
		menu.add(makeMenuItem("menu.help.about", e -> showAboutPanel()));
		menu.add(makeMenuItem("menu.help.help", e -> showHelp()));
		menu.add(makeMenuItem("menu.help.license", e -> showLicense()));
		setRunMenuItemsEnabled(true);
		setJMenuBar(mbar);
	}

	private void makeNewGridMenu() {
		newGridMenu.removeAll();
		MenuMaker<T> maker = new MenuMaker<>(this, resources, displayMap);
		maker.addConstructors(newGridMenu, gridClasses);
	}

	/**
	 * Sets the enabled status of those menu items that are disabled when
	 * running.
	 *
	 * @param enable true to enable the menus
	 */
	public void setRunMenuItemsEnabled(boolean enable) {
		menuItemsDisabledDuringRun.forEach(item -> item.setEnabled(enable));
	}

	/**
	 * Brings up a simple dialog with some general information.
	 */
	private void showAboutPanel() {
		StringBuilder html = new StringBuilder(format(resources.getString("dialog.about.text"), resources.getString("version.id")));
		String[] props = {"java.version", "java.vendor", "java.home", "os.name", "os.arch", "os.version", "user.name", "user.home", "user.dir"};
		html.append("<table border='1'>");
		for (String prop : props)
			try {
				String value = getProperty(prop);
				html.append("<tr><td>").append(prop).append("</td><td>").append(value).append("</td></tr>");
			} catch (SecurityException ex) {
				// oh well...
			}
		html.append("</table>");
		html = new StringBuilder("<html>" + html + "</html>");
		showMessageDialog(this, new JLabel(html.toString()), resources.getString("dialog.about.title"), INFORMATION_MESSAGE);
	}

	/**
	 * Brings up a window with a scrolling text pane that display the help
	 * information.
	 */
	private void showHelp() {
		JDialog dialog = new JDialog(this, resources.getString("dialog.help.title"));
		final JEditorPane helpText = new JEditorPane();
		try {
			URL url = getClass().getResource("GridWorldHelp.html");
			helpText.setPage(url);
		} catch (Exception e) {
			helpText.setText(resources.getString("dialog.help.error"));
		}
		helpText.setEditable(false);
		helpText.addHyperlinkListener(ev -> {
			if (ev.getEventType() == ACTIVATED)
				try {
					helpText.setPage(ev.getURL());
				} catch (Exception ignored) {
				}
		});
		JScrollPane sp = new JScrollPane(helpText);
		sp.setPreferredSize(new Dimension(650, 500));
		dialog.getContentPane().add(sp);
		dialog.setLocation(getX() + getWidth() - 200, getY() + 50);
		dialog.pack();
		dialog.setVisible(true);
	}

	/**
	 * Brings up a dialog that displays the license.
	 */
	private void showLicense() {
		JDialog dialog = new JDialog(this, resources.getString("dialog.license.title"));
		final JEditorPane text = new JEditorPane();
		try {
			URL url = getClass().getResource("GNULicense.txt");
			text.setPage(url);
		} catch (Exception e) {
			text.setText(resources.getString("dialog.license.error"));
		}
		text.setEditable(false);
		JScrollPane sp = new JScrollPane(text);
		sp.setPreferredSize(new Dimension(650, 500));
		dialog.getContentPane().add(sp);
		dialog.setLocation(getX() + getWidth() - 200, getY() + 50);
		dialog.pack();
		dialog.setVisible(true);
	}

	/**
	 * Nested class that is registered as the handler for exceptions on the
	 * Swing event thread. The handler will put up an alert panel and dump the
	 * stack trace to the console.
	 */
	public class GUIExceptionHandler {
		public void handle(@NotNull Throwable e) {
			e.printStackTrace();
			JTextArea area = new JTextArea(10, 40);
			StringWriter writer = new StringWriter();
			e.printStackTrace(new PrintWriter(writer));
			area.setText(writer.toString());
			area.setCaretPosition(0);
			String copyOption = resources.getString("dialog.error.copy");
			JOptionPane pane = new JOptionPane(new JScrollPane(area), ERROR_MESSAGE, YES_NO_OPTION, null, new String[]{copyOption, resources.getString("cancel")});
			pane.createDialog(WorldFrame.this, e.toString()).setVisible(true);
			if (copyOption.equals(pane.getValue())) {
				area.setSelectionStart(0);
				area.setSelectionEnd(area.getText().length());
				area.copy(); // copy to clipboard
			}
		}
	}
}