package mazeAttempts.maze3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.awt.Color.BLUE;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/*
List of objects that must be coded:
Explorer – This is the playable character that will move through the mazeAttempts.maze. Explorer must have a move() method (which will be called in the keyPressed method in your runner) and store the current position as a Location.
	- Required fields:
		- Location location;
		- int direction;
		- int size;
		- Color color;
	- Required methods:
		- Color getColor();
		- Location getLocation();
		- int getDirection();
		- void move();
		- Rectangle getRectangle();
Location – This will be used to keep track of Locations (Explorer and Maze will both use this object)
	- Required fields:
		- int x;
		- int y;
	- Required methods:
		- int getX();
		- int getY();
		- void setX(int x);
		- void setY(int y);
*/
class Location {
	int x;
	int y;


	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Location{x=" + x + ", y=" + y + '}';
	}
}

class Explorer {
	Location location;
	int direction;
	int size;
	Color color;

	public Explorer(Location location, int direction, int size, Color color) {
		this.location = location;
		this.direction = direction;
		this.size = size;
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public Location getLocation() {
		return location;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void move() {
		switch (direction) {
			case 0 -> location.setY(location.getY() - 1);
			case 1 -> location.setX(location.getX() + 1);
			case 2 -> location.setY(location.getY() + 1);
			case 3 -> location.setX(location.getX() - 1);
		}
	}

	public Rectangle getRectangle() {
		return new Rectangle(location.getX() * size, location.getY() * size, size, size);
	}
}

/*
 * The Maze class must have the following:
 * - Required fields
 * - A JFrame frame
 * - A char[][] mazeAttempts.maze; or ArrayList<char[]> mazeAttempts.maze; or ArrayList<ArrayList<Character>> mazeAttempts.maze;
 * - An Explorer explorer;
 * - An ArrayList<Wall> walls;
 * - A boolean draw3D; initialized as false
 * - An int shrink; initialized as 15
 */
public class Maze extends JPanel implements KeyListener {
	/*
	 * - Declare a two-dimensional array (or an ArrayList of ArrayList) of characters that will be used to store all the wall locations in the mazeAttempts.maze, so you can read from Classwork/dataStructures/src/mazeAttempts.maze/Maze.txt
	 * - Walls are represented by the character ‘#’, and the Explorer should not be able to move onto those locations.
	 * - Empty spaces are represented by the character ‘ ’, and the Explorer should only be able to move onto those locations.
	 * - The Explorer should be initialized to the starting position, represented by the character ‘1'.
	 * - The exit location for the Explorer is the ‘E’ character in the mazeAttempts.maze.
	 * - You may use either Scanner(File) or BufferedReader(FileReader(File)) to read the mazeAttempts.maze file.
	 */
	char[][] maze;
	int direction;
	JFrame frame;
	Explorer explorer;
	ArrayList<Wall> walls;
	boolean draw3D;
	int shrink;

	/*
	 * - The constructor must do the following:
	 * - Initialize the JFrame field frame, and then do the following:
	 * - Run JFrame's add and addKeyListener methods, passing the current Maze object as an argument.
	 * - Set the JFrame's default close operation to EXIT_ON_CLOSE.
	 * - Set the JFrame's size to 1600 by 1600.
	 * - Make the JFrame visible.
	 * - Read the text file at Classwork/dataStructures/src/mazeAttempts.maze/Maze.txt using a Scanner and a while loop, adding each character to the mazeAttempts.maze array, in the setBoard method, and then call it to draw the mazeAttempts.maze.
	 * - The setBoard method must also note the Explorer's inital location, indicated by the character 1.
	 */
	public Maze() {
		frame = new JFrame();
		frame.add(this);
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(1600, 1600);
		frame.setVisible(true);
		setBoard();
	}

	/*
	 * The main method must only call the constructor for the Maze class; all other tasks must be completed by their respective methods.
	 */
	public static void main(String[] args) {
		new Maze();
	}

	public void setBoard() {
		try {
			Scanner scanner = new Scanner(new File("Classwork/dataStructures/src/mazeAttempts/Maze.txt"));
			maze = new char[scanner.nextInt()][scanner.nextInt()];
			for (int i = 0; i < maze.length; i++)
				for (int j = 0; j < maze[i].length; j++)
					maze[i][j] = scanner.next().charAt(0);
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		setExplorer();
		repaint();
	}

	public void setExplorer() {
		for (int i = 0; i < maze.length; i++)
			for (int j = 0; j < maze[i].length; j++)
				if (maze[i][j] == '1')
					explorer = new Explorer(new Location(i, j), 0, 50, BLUE);
	}

	/*
	 * The keyPressed method must:
	 * - Determine which key was pressed, and set the direction of the Explorer accordingly.
	 * - Call the move() method on the Explorer.
	 * - Call the repaint() method on the Maze.
	 * - If the Explorer has reached the exit location, the Maze should display a congratulatory message in a popup.
	 * The keyTyped and keyReleased methods are unnecessary, but should still be declared as necessary overrides of the KeyListener implementation.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP -> direction = 0;
			case KeyEvent.VK_RIGHT -> direction = 1;
			case KeyEvent.VK_DOWN -> direction = 2;
			case KeyEvent.VK_LEFT -> direction = 3;
		}
		explorer.move();
		repaint();
		if (explorer.getLocation().equals(new Location(0, 0))) {
			JOptionPane.showMessageDialog(frame, "You Win!");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}

/*
 * Part 2:
 * Redraw your mazeAttempts.maze in 3D. You must always draw private char[][] board; at least 3 spaces ahead of your movable character.
 * You need to create a new object:
 * - Wall – This is an object used to store a Wall. All wall positions will be stored using the Location class. Each wall will be stored in an ArrayList of Wall objects.
 * - Required fields:
 * - int[] rows;
 * - int[] columns;
 * - String type;
 * - Required methods:
 * - Wall(int[] rows, int[] columns, String type);
 * - String getType();
 * - Polygon getPolygon();
 * - The polygons must use GradientPaint to create shadows to give a 3D feeling.
 * Make use of a wall object (walls, floor, ceiling) that would retain the coordinates of the shape as a Polygon to make it easy to draw the polygons that represent the walls, floors, or ceilings. Because you set movement based on the up arrow always being the key to move forward, you should have an easier time of it when it comes to drawing your mazeAttempts.maze.
 */
class Wall {
	int[] rows;
	int[] columns;
	String type;

	public Wall(int[] rows, int[] columns, String type) {
		this.rows = rows;
		this.columns = columns;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public Polygon getPolygon() {
		Polygon polygon = new Polygon();
		for (int i = 0; i < rows.length; i++) {
			polygon.addPoint(columns[i], rows[i]);
		}
		return polygon;
	}
}