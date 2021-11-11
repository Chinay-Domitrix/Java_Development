package mazeAttempts.maze2;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import static java.awt.Color.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Maze extends JPanel implements KeyListener, MouseListener {
	JFrame frame;
	String[][] maze;
	Explorer exp;
	boolean alreadyPainted = false;
	boolean alreadyRead = false;
	String[] lineArr = new String[33];
	int k = 0;
	String line;
	int lineCount = 0;
	boolean draw3D = false;
	int shrink = 50;
	ArrayList<Wall> walls;

	public Maze() {
		frame = new JFrame();
		frame.add(this);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(1600, 1000);
		frame.setVisible(true);
		frame.addKeyListener(this);
		frame.addMouseListener(this);
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Classwork/dataStructures/src/mazeAttempts/Maze.txt"));
			if ((line = reader.readLine()) != null) {
				do {
					lineArr[k] = line;
					lineCount++;
					k++;
				} while ((line = reader.readLine()) != null);
			}
		} catch (Exception e) {
			System.out.println("Exception");
		}

	}

	public static void main(String[] args) {
		new Maze();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) (g);
		g.setColor(BLACK);
		g.fillRect(0, 0, 1600, 1000);
		k = 0;
		if (draw3D == false) {
			maze = new String[lineArr.length][lineArr[0].split("").length];
			for (int i = 0; i < lineArr.length; i++) {
				for (int j = 0; j < lineArr[0].split("").length; j++) {
					maze[i][j] = lineArr[i].split("")[j];
					if (maze[i][j].equals("#"))
						g.setColor(WHITE);
					else if (maze[i][j].equals("1") && alreadyPainted == false) {
						exp = new Explorer(RED, new Location(j, i), 15, 0);
						g.setColor(exp.getColor());
						alreadyPainted = true;
					} else if (maze[i][j].equals("E"))
						g.setColor(GREEN);
					else
						g.setColor(BLACK);
					g.fillRect(j * 15, i * 15, 15, 15);
				}
			}


			if (alreadyPainted == true) {
				g.setColor(exp.getColor());
				g2.fill(exp.getRectangle());
			}
		} else if (draw3D) {
			for (int i = 0; i < walls.size(); i++) {
				g2.setColor(WHITE);
				g2.fillPolygon(walls.get(i).getPolygon());
				g2.setColor(RED);
				g2.drawPolygon(walls.get(i).getPolygon());
			}
		}
	}

	public void createWalls() {
		walls = new ArrayList<Wall>();
		for (int n = 0; n < 5; n++) {
			walls.add(getCeil(n));
			walls.add(getFloor(n));
			//walls.add(getLeft(n));
			//walls.add(getRight(n));
		}
		int expR = exp.getLocation().getX();
		int expC = exp.getLocation().getY();
		int expDir = exp.getDir();
		/*
		 * code methods getRight and getLeft and also use a switch statement with directions to print walls on right, left, and front
		 * code the enhanced hallways: getRightPath, getRightFloor, getRightCeil
		 */
		switch (expDir) {
			case 0: //east
				for (int n = 0; n < 5; n++) {
					try {
						/*if (mazeAttempts.maze[expR - 1][expC - (5 - n)] == "#") {
							walls.add(getFront(5 - n));
						}*/
						if (maze[expC + 1][expR + n].equals("#")) {
							walls.add(getRight(n));
						}
						if (maze[expC - 1][expR + n].equals("#")) {
							walls.add(getLeft(n));
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						e.printStackTrace();
					}
				}
				break;
			case 1: //south
				for (int n = 0; n < 5; n++) {
					try {
						//if(mazeAttempts.maze[expR-1][expC-(5-n)] == "#"){
						//	walls.add(getFront(5-n));
						//}
						if (maze[expC + n][expR - 1].equals("#")) {
							walls.add(getRight(n));
						}
						if (maze[expC + n][expR + 1].equals("#")) {
							walls.add(getLeft(n));
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						e.printStackTrace();
					}
				}
				break;
			case 2: //west
				for (int n = 0; n < 5; n++) {
					try {
						//if(mazeAttempts.maze[expR-1][expC-(5-n)] == "#"){
						//	walls.add(getFront(5-n));
						//}
						if (maze[expC + 1][expR - n].equals("#")) {
							walls.add(getRight(n));
						}
						if (maze[expC - 1][expR - n].equals("#")) {
							walls.add(getLeft(n));
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						e.printStackTrace();
					}
				}
				break;
			case 3: //north
				for (int n = 0; n < 5; n++) {
					try {
						//if(mazeAttempts.maze[expR-1][expC-(5-n)] == "#"){
						//	walls.add(getFront(5-n));
						//}
						if (maze[expC - n][expR + 1].equals("#")) {
							walls.add(getRight(n));
						}
						if (maze[expC - n][expR - 1].equals("#")) {
							walls.add(getLeft(n));
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						e.printStackTrace();
					}
				}
				break;
			case -1: //north
				for (int n = 0; n < 5; n++) {
					try {
						//if(mazeAttempts.maze[expR-1][expC-(5-n)] == "#"){
						//	walls.add(getFront(5-n));
						//}
						if (maze[expC - n][expR + 1].equals("#")) {
							walls.add(getRight(n));
						}
						if (maze[expC - n][expR - 1].equals("#")) {
							walls.add(getLeft(n));
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						e.printStackTrace();
					}
				}
				break;
			case -2: //west
				for (int n = 0; n < 5; n++) {
					try {
						//if(mazeAttempts.maze[expR-1][expC-(5-n)] == "#"){
						//	walls.add(getFront(5-n));
						//}
						if (maze[expC + 1][expR - n].equals("#")) {
							walls.add(getRight(n));
						}
						if (maze[expC - 1][expR - n].equals("#")) {
							walls.add(getLeft(n));
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						e.printStackTrace();
					}
				}
				break;
			case -3: //south
				for (int n = 0; n < 5; n++) {
					try {
						//if(mazeAttempts.maze[expR-1][expC-(5-n)] == "#"){
						//	walls.add(getFront(5-n));
						//}
						if (maze[expC + n][expR - 1].equals("#")) {
							walls.add(getRight(n));
						}
						if (maze[expC + n][expR + 1].equals("#")) {
							walls.add(getLeft(n));
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						e.printStackTrace();
					}
				}
				break;
		}
	}

	public Wall getFloor(int n) {
		int[] rLocs = {700 - n * shrink, 650 - n * shrink, 650 - n * shrink, 700 - n * shrink},
				cLocs = {100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink};
		return new Wall(rLocs, cLocs, "Floor", 255 - n * shrink, 255 - n * shrink, 255 - n * shrink);
	}

	public Wall getCeil(int n) {
		int[] rLocs = {100 + n * shrink, 150 + n * shrink, 150 + n * shrink, 100 + n * shrink},
				cLocs = {100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink};
		return new Wall(rLocs, cLocs, "Ceil", 255 - n * shrink, 255 - n * shrink, 255 - n * shrink);
	}

	public Wall getLeft(int n) {
		int[] rLocs = {100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink},
				cLocs = {100 + n * shrink, 150 + n * shrink, 150 + n * shrink, 100 + n * shrink};
		return new Wall(rLocs, cLocs, "Left", 255 - n * shrink, 255 - n * shrink, 255 - n * shrink);
	}

	public Wall getRight(int n) {
		int[] rLocs = {100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink},
				cLocs = {700 - n * shrink, 650 - n * shrink, 650 - n * shrink, 700 - n * shrink};
		return new Wall(rLocs, cLocs, "Right", 255 - n * shrink, 255 - n * shrink, 255 - n * shrink);
	}

	public Wall getFront(int n) {
		int[] rLocs = {100 + n * shrink, 100 + n * shrink, 700 - n * shrink, 700 - n * shrink},
				cLocs = {100 + n * shrink, 650 - n * shrink, 650 - n * shrink, 700 - n * shrink};
		return new Wall(rLocs, cLocs, "Ceil", 255 - n * shrink, 255 - n * shrink, 255 - n * shrink);
	}

	public void keyPressed(@NotNull KeyEvent e) {
		if (e.getKeyCode() == 32) draw3D = !draw3D;
		if (draw3D) createWalls();
		exp.move(e.getKeyCode(), maze, draw3D);
		repaint();
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}
}
