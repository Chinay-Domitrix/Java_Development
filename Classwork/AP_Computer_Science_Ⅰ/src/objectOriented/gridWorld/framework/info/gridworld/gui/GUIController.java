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

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.Class.forName;
import static java.lang.reflect.Modifier.ABSTRACT;
import static javax.swing.BorderFactory.createEtchedBorder;
import static javax.swing.Box.createRigidArea;
import static javax.swing.KeyStroke.getKeyStroke;

/**
 * The GUIController controls the behavior in a WorldFrame. <br />
 * This code is not tested on the AP CS A and AB exams. It contains GUI
 * implementation details that are not intended to be understood by AP CS
 * students.
 */

public class GUIController<T> {
	public static final int INDEFINITE = 0, FIXED_STEPS = 1, PROMPT_STEPS = 2;
	private static final int MIN_DELAY_MSECS = 10, MAX_DELAY_MSECS = 1000;
	private static final int INITIAL_DELAY = 505;
	private final Timer timer;
	private JButton stepButton, runButton, stopButton;
	private JComponent controlPanel;
	private final GridPanel display;
	private final WorldFrame<T> parentFrame;
	private int numStepsToRun, numStepsSoFar;
	private final ResourceBundle resources;
	private final DisplayMap displayMap;
	private boolean running;
	private final Set<Class<?>> occupantClasses;

	/**
	 * Creates a new controller tied to the specified display and gui
	 * frame.
	 *
	 * @param parent     the frame for the world window
	 * @param disp       the panel that displays the grid
	 * @param displayMap the map for occupant displays
	 * @param res        the resource bundle for message display
	 */
	public GUIController(WorldFrame<T> parent, GridPanel disp, DisplayMap displayMap, ResourceBundle res) {
		resources = res;
		display = disp;
		parentFrame = parent;
		this.displayMap = displayMap;
		makeControls();
		occupantClasses = new TreeSet<>(Comparator.comparing(Class::getName));
		World<T> world = parentFrame.getWorld();
		Grid<T> gr = world.getGrid();
		for (Location loc : gr.getOccupiedLocations()) addOccupant(gr.get(loc));
		world.getOccupantClasses().forEach(name -> {
			try {
				occupantClasses.add(forName(name));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		timer = new Timer(INITIAL_DELAY, evt -> step());
		display.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				Grid<T> gr = parentFrame.getWorld().getGrid();
				Location loc = display.locationForPoint(evt.getPoint());
				if (loc != null && gr.isValid(loc) && !isRunning()) {
					display.setCurrentLocation(loc);
					locationClicked();
				}
			}
		});
		stop();
	}

	/**
	 * Advances the world one step.
	 */
	public void step() {
		parentFrame.getWorld().step();
		parentFrame.repaint();
		if (++numStepsSoFar == numStepsToRun) stop();
		Grid<T> gr = parentFrame.getWorld().getGrid();
		gr.getOccupiedLocations().stream().map(gr::get).forEach(this::addOccupant);
	}

	private void addOccupant(T occupant) {
		var cl = occupant.getClass();
		do {
			if ((cl.getModifiers() & ABSTRACT) == 0) occupantClasses.add(cl);
			cl = cl.getSuperclass();
		}
		while (cl != Object.class);
	}

	/**
	 * Starts a timer to repeatedly carry out steps at the speed currently
	 * indicated by the speed slider up Depending on the run option, it will
	 * either carry out steps for some fixed number or indefinitely
	 * until stopped.
	 */
	public void run() {
		display.setToolTipsEnabled(false); // hide tool tips while running
		parentFrame.setRunMenuItemsEnabled(false);
		stopButton.setEnabled(true);
		stepButton.setEnabled(false);
		runButton.setEnabled(false);
		numStepsSoFar = 0;
		timer.start();
		running = true;
	}

	/**
	 * Stops any existing timer currently carrying out steps.
	 */
	public void stop() {
		display.setToolTipsEnabled(true);
		parentFrame.setRunMenuItemsEnabled(true);
		timer.stop();
		stopButton.setEnabled(false);
		runButton.setEnabled(true);
		stepButton.setEnabled(true);
		running = false;
	}

	public boolean isRunning() {
		return running;
	}

	/**
	 * Builds the panel with the various controls (buttons and
	 * slider).
	 */
	private void makeControls() {
		controlPanel = new JPanel();
		stepButton = new JButton(resources.getString("button.gui.step"));
		runButton = new JButton(resources.getString("button.gui.run"));
		stopButton = new JButton(resources.getString("button.gui.stop"));
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		controlPanel.setBorder(createEtchedBorder());
		Dimension spacer = new Dimension(5, stepButton.getPreferredSize().height + 10);
		controlPanel.add(createRigidArea(spacer));
		controlPanel.add(stepButton);
		controlPanel.add(createRigidArea(spacer));
		controlPanel.add(runButton);
		controlPanel.add(createRigidArea(spacer));
		controlPanel.add(stopButton);
		runButton.setEnabled(false);
		stepButton.setEnabled(false);
		stopButton.setEnabled(false);
		controlPanel.add(createRigidArea(spacer));
		controlPanel.add(new JLabel(resources.getString("slider.gui.slow")));
		JSlider speedSlider = new JSlider(MIN_DELAY_MSECS, MAX_DELAY_MSECS, INITIAL_DELAY);
		speedSlider.setInverted(true);
		speedSlider.setPreferredSize(new Dimension(100, speedSlider.getPreferredSize().height));
		speedSlider.setMaximumSize(speedSlider.getPreferredSize());
		// remove control PAGE_UP, PAGE_DOWN from slider--they should be used
		// for zoom
		InputMap map = speedSlider.getInputMap();
		while (map != null) {
			map.remove(getKeyStroke("control PAGE_UP"));
			map.remove(getKeyStroke("control PAGE_DOWN"));
			map = map.getParent();
		}
		controlPanel.add(speedSlider);
		controlPanel.add(new JLabel(resources.getString("slider.gui.fast")));
		controlPanel.add(createRigidArea(new Dimension(5, 0)));
		stepButton.addActionListener(e -> step());
		runButton.addActionListener(e -> run());
		stopButton.addActionListener(e -> stop());
		speedSlider.addChangeListener(evt -> timer.setDelay(((JSlider) evt.getSource()).getValue()));
	}

	/**
	 * Returns the panel containing the controls.
	 *
	 * @return the control panel
	 */
	public JComponent controlPanel() {
		return controlPanel;
	}

	/**
	 * Callback on mousePressed when editing a grid.
	 */
	private void locationClicked() {
		World<T> world = parentFrame.getWorld();
		Location loc = display.getCurrentLocation();
		if ((loc != null) && !world.locationClicked(loc)) editLocation();
		parentFrame.repaint();
	}

	/**
	 * Edits the contents of the current location, by displaying the constructor
	 * or method menu.
	 */
	public void editLocation() {
		World<T> world = parentFrame.getWorld();
		Location loc = display.getCurrentLocation();
		if (loc != null) if (world.getGrid().get(loc) == null) {
			MenuMaker<T> maker = new MenuMaker<>(parentFrame, resources, displayMap);
			JPopupMenu popup = maker.makeConstructorMenu(occupantClasses, loc);
			Point p = display.pointForLocation(loc);
			popup.show(display, p.x, p.y);
		} else {
			MenuMaker<T> maker = new MenuMaker<>(parentFrame, resources, displayMap);
			JPopupMenu popup = maker.makeMethodMenu(world.getGrid().get(loc), loc);
			Point p = display.pointForLocation(loc);
			popup.show(display, p.x, p.y);
		}
		parentFrame.repaint();
	}

	/**
	 * Edits the contents of the current location, by displaying the constructor
	 * or method menu.
	 */
	public void deleteLocation() {
		World<T> world = parentFrame.getWorld();
		Location loc = display.getCurrentLocation();
		if (loc != null) {
			world.remove(loc);
			parentFrame.repaint();
		}
	}
}