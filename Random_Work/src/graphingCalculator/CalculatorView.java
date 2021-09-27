package graphingCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.WEST;
import static java.awt.Color.gray;
import static java.awt.Color.red;
import static java.awt.Font.PLAIN;
import static java.lang.Double.valueOf;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.JTabbedPane.TOP;
import static javax.swing.JTabbedPane.WRAP_TAB_LAYOUT;

class CalculatorView implements ActionListener {
	private JFrame frame;
	private JPanel displayPanel;
	private JPanel buttonPanel;
	private JPanel graphPanel;
	private JPanel graphDisplayPanel;
	private JTextArea inputEquation;
	private JTextArea equationDisplay;
	private JTextArea graphEquation;
	private final CalculatorController calcControl;
	private final Graphics2D g;
	private final Font displayFont;

	/**
	 * Sets up the overall frame for the Calculator, the two
	 * panels where everything is displayed.
	 */
	public CalculatorView() {
//		Creates a new font to be used within the frame
		displayFont = new Font("Dialogue", PLAIN, 18);
//		Creates the frame and correct panels and tabs so it displays properly
		createFrame();
		createGraphPanel();
		createDisplayPanel();
		createButtonPanel();
		createTabs();
//		Creates a new Calculator Controller for the Calculator
		calcControl = new CalculatorController();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
//		Creates the graphics to draw the grid on the graph panel
		g = (Graphics2D) graphDisplayPanel.getGraphics();
	}

	/**
	 * Sets up the whole frame to be used in the Calculator, sets it
	 * to be visible and of a set size with a grid layout of two
	 * columns and one row.
	 */
	private void createFrame() {
		frame = new JFrame("Graphing Calculator");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(1, 2));
		frame.setSize(1250, 720);
	}

	/**
	 * Sets up the display panel, which is half the size of the frame,
	 * and has a null layout so we can place the text areas appropriately.
	 * <p>
	 * The display panel is where equations are entered in, evaluated, and graphed.
	 */
	private void createDisplayPanel() {
		displayPanel = new JPanel();
		displayPanel.setLayout(null);
		frame.add(displayPanel, WEST);
		addToDisplayPanel();
	}

	/**
	 * Sets up the button panel, which is half the size of the frame,
	 * and has a grid layout to place all the buttons in a uniform way.
	 * <p>
	 * The button panel contains the keyboard for the Calculator.
	 */
	private void createButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(6, 6));
		frame.add(buttonPanel, EAST);
		addButtonsToButtonPanel();
	}

	/**
	 * Sets up the graph panel, which is half the size of the frame,
	 * and has a null layout so we can add the graph to it in a
	 * specified location.
	 * <p>
	 * The graph panel contains equation to be graphed and the graph itself.
	 */
	private void createGraphPanel() {
		graphPanel = new JPanel();
		graphPanel.setLayout(null);
		graphPanel.setVisible(true);
		frame.add(graphPanel, WEST);
		addToGraphPanel();

	}

	/**
	 * First creates the display panel tab, which allows the user to switch
	 * between the display and graph panels, then creates the button panel
	 * tab, which informs the user of the location of the keyboard.
	 */
	private void createTabs() {
		JTabbedPane tabs = new JTabbedPane(TOP, WRAP_TAB_LAYOUT);
		tabs.addTab("Equations", displayPanel);
		tabs.addTab("Graph", graphPanel);
		tabs.setVisible(true);
		frame.add(tabs);

		tabs = new JTabbedPane(TOP, WRAP_TAB_LAYOUT);
		tabs.addTab("Keyboard", buttonPanel);
		tabs.setVisible(true);
		frame.add(tabs);
	}

	/**
	 * Adds everything necessary for the display panel, including two
	 * text areas to display the current equation and previous equations
	 */
	private void addToDisplayPanel() {
//		Creates the text area for the input equation and adds it to the displayPanel
		inputEquation = new JTextArea("Enter equation here: ", 3, 5);
		inputEquation.setLineWrap(true);
		inputEquation.setEditable(false);
		inputEquation.setFont(displayFont);
		inputEquation.setBounds(0, 0, 600, 50);
		displayPanel.add(inputEquation);
//		Creates the text area for the previous equations and adds it to the displayPanel
		equationDisplay = new JTextArea("Previous Equations: \n", 3, 5);
		equationDisplay.setLineWrap(true);
		equationDisplay.setEditable(false);
		equationDisplay.setFont(displayFont);
		equationDisplay.setBounds(0, 60, 600, 600);
		displayPanel.add(equationDisplay);
	}

	/**
	 * Adds all necessary buttons to the keyboard by creating each
	 * button and adding it to an ArrayList to store them all,
	 * then looping through that list and adding them to the panel
	 * while also setting the font to a correct size and creating
	 * action listeners for all buttons.
	 */
	private void addButtonsToButtonPanel() {
//		Creates the new ArrayList to store the buttons
		ArrayList<JButton> buttonList = new ArrayList<>();
//		Row one of buttons on the keyboard
		JButton plus = new JButton("+");
		buttonList.add(plus);
		JButton minus = new JButton("-");
		buttonList.add(minus);
		JButton multiply = new JButton("*");
		buttonList.add(multiply);
		JButton divide = new JButton("/");
		buttonList.add(divide);
		JButton openParen = new JButton("(");
		buttonList.add(openParen);
		JButton closeParen = new JButton(")");
		buttonList.add(closeParen);
//		Row two of buttons on the keyboard
		JButton seven = new JButton(Integer.toString(7));
		buttonList.add(seven);
		JButton eight = new JButton(Integer.toString(8));
		buttonList.add(eight);
		JButton nine = new JButton(Integer.toString(9));
		buttonList.add(nine);
		JButton power = new JButton("^");
		buttonList.add(power);
		JButton squared = new JButton("x^2");
		buttonList.add(squared);
		var sqrt = new JButton("sqrt");
		buttonList.add(sqrt);
//		Row three of buttons on the keyboard
		var four = new JButton(Integer.toString(4));
		buttonList.add(four);
		var five = new JButton(Integer.toString(5));
		buttonList.add(five);
		var six = new JButton(Integer.toString(6));
		buttonList.add(six);
		var sine = new JButton("sin()");
		buttonList.add(sine);
		var cosine = new JButton("cos()");
		buttonList.add(cosine);
		var tan = new JButton("tan()");
		buttonList.add(tan);
//		Row four of buttons on the keyboard
		var one = new JButton(Integer.toString(1));
		buttonList.add(one);
		var two = new JButton(Integer.toString(2));
		buttonList.add(two);
		var three = new JButton(Integer.toString(3));
		buttonList.add(three);
		var pi = new JButton("pi");
		buttonList.add(pi);
		var ln = new JButton("ln()");
		buttonList.add(ln);
		var e = new JButton("e");
		buttonList.add(e);
//		Row five of buttons on the keyboard
		var period = new JButton(".");
		buttonList.add(period);
		var zero = new JButton(Integer.toString(0));
		buttonList.add(zero);
		var negative = new JButton("(-)");
		buttonList.add(negative);
		var x = new JButton("x");
		buttonList.add(x);
		var enter = new JButton("Enter");
		buttonList.add(enter);
		var graph = new JButton("Graph");
		buttonList.add(graph);
//		Row six of buttons on the keyboard
		var delete = new JButton("Delete");
		buttonList.add(delete);
		var clear = new JButton("Clear");
		buttonList.add(clear);
		var clearAll = new JButton("<html>" + "Clear" + "<br>" + "All" + "<html>");
		buttonList.add(clearAll);
		var clearGraph = new JButton("<html>" + "Clear" + "<br>" + "Graph" + "<html>");
		buttonList.add(clearGraph);
//		Creates a new font so the buttons have larger text than everything else
		Font f = new Font("Dialogue", PLAIN, 22);
//		Loops through the button list, sets the font, adds listeners, then adds them to the panel
		buttonList.forEach(temp -> {
			temp.setFont(f);
			temp.setActionCommand(temp.getText());
			temp.addActionListener(this);
			buttonPanel.add(temp);
		});
	}

	/**
	 * Adds everything necessary to the graphPanel, including a text area
	 * to display the current equation, and a panel to draw the graph in.
	 */
	private void addToGraphPanel() {
//		Creates a text area for the input equations and adds it to the graphPanel
		graphEquation = new JTextArea("Graph: Y = ", 600, 50);
		graphEquation.setEditable(false);
		graphEquation.setFont(displayFont);
		graphEquation.setBounds(0, 0, 600, 50);
		graphPanel.add(graphEquation);
//		Creates a new panel to draw the graph in and adds it to the graphPanel
		graphDisplayPanel = new JPanel();
		graphDisplayPanel.setVisible(true);
		graphDisplayPanel.setLayout(null);
		graphDisplayPanel.setBounds(0, 50, 650, 650);
		graphPanel.add(graphDisplayPanel);
	}


	/**
	 * Determine which button was pressed and evaluates it once Enter is pushed, Graphs
	 * it once graph is pushed, Clears everything when Clear All is pushed, and clears
	 * the graph when Clear Graph is pushed, otherwise it just adds the text from the
	 * button to each text area.
	 */
	public void actionPerformed(ActionEvent arg0) {
//		Gets the button text and instantiates an array of Strings to store the answer
		String result = arg0.getActionCommand();
		String[] fullEquation;
		String[] newText;
//		If the user pushes "Enter", evaluate the equation and display it in previous equations
		switch (result) {
			case "Enter":
				fullEquation = calcControl.update("Enter");
				String eq = fullEquation[0];
				String sol = fullEquation[1];
//			    Inserts the equation and solution to the text area and adds newlines for readability
				equationDisplay.insert("\n", 22);
				equationDisplay.insert(sol, 22);
				equationDisplay.insert(" = ", 22);
				equationDisplay.insert(eq, 22);
				equationDisplay.insert("\n", 22);
				equationDisplay.insert("\n", 22);
				inputEquation.setText("");
				/*
				 * If the list of equations gets longer than the given screen size clear the
				 * screen of previous equations and start over
				 */
				if (equationDisplay.getLineCount() > 24) {
					equationDisplay.setText(eq + " = " + sol);
					equationDisplay.append("\n");
				}
				break;
//			If the user pushes the "Graph" button graph the equation if they are on the graphPanel
			case "Graph":
				if (graphDisplayPanel.isShowing()) drawPoints(calcControl.update("Graph"));
				break;
//			If the user pushes "Clear All" reset all text areas to their original state
			case "<html>Clear<br>All<html>":
				newText = calcControl.update(result);
				inputEquation.setText(newText[0]);
				graphEquation.setText(newText[0]);
				equationDisplay.setText("Previous equations: ");
				break;
//			If the user pushes "Clear Graph" reset the graph if it is showing
			case "<html>Clear<br>Graph<html>":
				if (graphDisplayPanel.isShowing()) {
					clearGraph();
					drawGrid();
				}
				break;
//			Otherwise add the input to the equation stored in the model
			default:
				newText = calcControl.update(result);
				inputEquation.setText(newText[0]);
				graphEquation.setText(newText[0]);
				break;
		}
	}

	/**
	 * Draws the grid of the graph using a set size that we
	 * determined based on the size of the panel.
	 */
	private void drawGrid() {
		g.setColor(gray);
		int boxSize = 30;
//		Loops and draws horizontal and vertical lines for every 30 units
		rangeClosed(0, 20).forEachOrdered(i -> {
			if ((i % 10) == 0) g.setStroke(new BasicStroke(3));
			g.drawLine(boxSize * i, 0, boxSize * i, 600);
			g.drawLine(0, boxSize * i, 600, boxSize * i);
			g.setStroke(new BasicStroke(1));
		});
	}

	/**
	 * Draws the grid for the graph and then, given the array of coordinates, plots
	 * the points in the correct place in the graph after setting the color to red
	 * so it shows up on the graph better.
	 */
	private void drawPoints(String[] coordinates) {
		drawGrid();
		range(0, coordinates.length - 1).forEachOrdered(j -> {
			g.setColor(red);
			g.drawLine(j, 300 - valueOf(coordinates[j]).intValue(), j + 1, 300 - valueOf(coordinates[j + 1]).intValue());
		});
	}

	/**
	 * Removes all equations that have been graphed
	 */
	private void clearGraph() {
		g.clearRect(0, 0, 600, 600);
	}
}
