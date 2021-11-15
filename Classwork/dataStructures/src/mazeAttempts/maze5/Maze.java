package mazeAttempts.maze5;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.Color.*;
import static java.awt.Font.ITALIC;
import static java.awt.Font.PLAIN;
import static java.lang.System.err;
import static java.lang.System.out;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Maze extends JPanel implements KeyListener, MouseListener {

	JFrame frame;
	int x = 0, y = 1;
	ArrayList<String[]> board = new ArrayList<String[]>();
	ArrayList<Wall> walls = new ArrayList<Wall>();
	ArrayList<Ceiling> ceilings = new ArrayList<Ceiling>();
	ArrayList<Wall> sideWalls = new ArrayList<Wall>();
	ArrayList<Wall> rectangles = new ArrayList<Wall>();
	boolean play = true;
	int direction = 1;
	int count = 0;
	Clip clip;
	int numWalls = 3;
	boolean drop = false;
	int currentX = 500;
	int currentY = 500;
	int bcX, bcY = 0;
	int pressed = 0;
	int xLength = 350;
	int yLength = 30;
	int r1 = 255, b2 = 255;
	int g1 = 0, b1 = 0, r2 = 0, g2 = 0;
	boolean on = false;
	int moves = 0;

	public Maze() {
		setBoard();
		frame = new JFrame();
		frame.add(this);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Player player = null;
		try {
			player = Manager.createRealizedPlayer(new File("Classwork/dataStructures/src/mazeAttempts/Background-Music.mp3").toURI().toURL());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoPlayerException e) {
			e.printStackTrace();
		} catch (CannotRealizeException e) {
			e.printStackTrace();
		}
		player.start();
		frame.setSize(1500, 800);
		frame.setVisible(true);
		frame.addKeyListener(this);
		frame.addMouseListener(this);

		this.addMouseListener(this);
	}

	public static void main(String... args) {
		new Maze();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gpart2 = (Graphics2D) g;
		g.setColor(BLACK);    //this will set the background color
		g.fillRect(0, 0, 1500, 800);
		//drawBoard here!
		//setBoard();
		g.setColor(WHITE);

		//2D STUFF
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.get(i).length; j++) {
				if (board.get(i)[j].equals("#"))
					g.fillRect((j * 8) + 1, (i * 8) + 250, 5, 5);
			}
		}

		g.setColor(YELLOW);

		int lastY = board.get(0).length - 1;
		int lastX = board.size() - 1;

		g.fillRect((lastY * 10) + 180, (15 * 10) + 180, 5, 5);

		g.setColor(MAGENTA);
		g.fillOval((x * 8) + 1, (y * 8) + 249, 7, 7);

		//3D Stuff
		for (Ceiling c : ceilings) {
			//g.setColor(Color.GRAY);
			gpart2.setPaint(c.getPaint());
			g.fillPolygon(c.getPolygon());

		}
		for (Wall w : rectangles) {
			g.setColor(BLACK);
			g.fillPolygon(w.getPolygon());
		}
		for (Wall w : walls) {
			g.setColor(WHITE);
			//g2.setPaint(w.getPaint());
			g.fillPolygon(w.getPolygon());
			g.setColor(BLUE);
			g.drawPolygon(w.getPolygon());
		}
		//front
		for (Wall w : sideWalls) {
			gpart2.setPaint(w.getPaint());
			//g.setColor(new Color(67,70,76));
			g.fillPolygon(w.getPolygon());
		}
		g.setFont(new Font("ALGERIAN", ITALIC, 50));
		g.setColor(new Color(0, 80, 255));
		g.drawString("Happy Maze-ing!", 10, 100);
		g.drawString("Moves Used: " + count, 40, 600);
		gpart2.setPaint(new GradientPaint(35, 175, new Color(r1, b1, g1), 35 + xLength, 175 + yLength, new Color(r2, b2, g2)));
		g.fillRect(35, 175, xLength, yLength);
		g.setFont(new Font("Lucida Calligraphy", PLAIN, 20));
		g.setColor(YELLOW);
		if (xLength != 0)
			g.drawString("Hint: Press F to use the flashlight", 35, 150);
		else {
			g.drawString("Unfortunately the flashlight\'s", 50, 150);
			g.drawString("battery has died", 115, 180);
		}
		if (on)
			g.drawString("F Pressed", 150, 230);

		if (x == lastY && y == 15) {
			g.setFont(new Font("Stencil", PLAIN, 40));
			g.setColor(WHITE);
			g.fillRect(0, 0, 1500, 800);
			g.setColor(new Color(237, 187, 249));
			g.fillRect(185, 95, 975, 500);
			g.setColor(WHITE);
			g.fillRect(190, 100, 965, 490);
			g.setColor(new Color(49, 224, 177));
			g.drawString("Congratulations on completing the maze!", 225, 325);
			g.drawString("You used " + count + " moves", 500, 425);
		}
	}

	public void setBoard() {

		File name = new File("Classwork/dataStructures/src/mazeAttempts/Maze.txt");
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text;
			while ((text = input.readLine()) != null) {
				board.add(text.split(""));
			}
		} catch (IOException io) {
			err.println("File error");
		}
		for (int r = 0; r < board.size(); r++) {
			for (int c = 0; c < board.get(r).length; c++)
				out.print(board.get(r)[c]);
			out.println();
		}
		ceilingStuff();
		rightRectangle();
		leftRectangle();
		leftSide();
		rightSide();
	}

	public void leftSide() {
		for (int i = 0; i < numWalls; i++) {
			int[] xCoordinates = {0, 50, 650, 700};
			int[] yCoordinates = {450, 550, 550, 450};

			for (int j = 0; j < xCoordinates.length; j++) {
				if (j < 2)
					xCoordinates[j] += 50 * i;
				else
					xCoordinates[j] -= 50 * i;
				yCoordinates[j] += 100 * i;
			}

			if (direction == 1) { //right
				if ((x + i) < board.get(0).length && y - 1 >= 0 && board.get(y - 1)[x + i].equals("#"))
					walls.add(new Wall(xCoordinates, yCoordinates));
			} else if (direction == 2) { //down
				if ((y + i) < board.size() && x + 1 < board.get(y + i).length && board.get(y + i)[x + 1].equals("#"))
					walls.add(new Wall(xCoordinates, yCoordinates));
			} else if (direction == 3) { //left
				if ((x - i) >= 0 && y + 1 < board.size() && board.get(y + 1)[x - i].equals("#"))
					walls.add(new Wall(xCoordinates, yCoordinates));
			} else if (direction == 4) { //up
				if ((y - i) >= 0 && x - 1 >= 0 && board.get(y - i)[x - 1].equals("#"))
					walls.add(new Wall(xCoordinates, yCoordinates));
			}
		}
	}

	public void flashlightPower() {
		xLength -= 25;
		if (b2 >= 0 && r2 <= 255) {
			b2 -= 10;
			r2 += 15;
		}
	}

	public void rightSide() {
		for (int i = 0; i < numWalls; i++) {
			int[] xCoordinates = {0, 50, 650, 700};
			int[] yCoordinates = {1300, 1200, 1200, 1300};
			for (int j = 0; j < xCoordinates.length; j++) {
				if (j < 2)
					xCoordinates[j] += 50 * i;
				else
					xCoordinates[j] -= 50 * i;
				yCoordinates[j] -= 100 * i;
			}
			if (direction == 1) { //right
				if ((x + i) < board.get(0).length && (y + 1) < board.size() && board.get(y + 1)[x + i].equals("#"))
					walls.add(new Wall(xCoordinates, yCoordinates));
			} else if (direction == 2) { //down
				if ((y + i) < board.size() && x - 1 >= 0 && board.get(y + i)[x - 1].equals("#"))
					walls.add(new Wall(xCoordinates, yCoordinates));
			} else if (direction == 3) { //left
				if ((x - i) >= 0 && (y - 1) >= 0 && board.get(y - 1)[x - i].equals("#"))
					walls.add(new Wall(xCoordinates, yCoordinates));
			} else if (direction == 4) { //up
				if ((y - i) >= 0 && x + 1 < board.get(y - i).length && board.get(y - i)[x + 1].equals("#"))
					walls.add(new Wall(xCoordinates, yCoordinates));
			}
		}
	}

	public void leftRectangle() {

		for (int i = 0; i < numWalls; i++) {
			int[] xCoordinates = {50, 50, 650, 650};
			int[] yCoordinates = {450, 550, 550, 450};

			if ((x + 1) < board.get(0).length) {
				for (int j = 0; j < xCoordinates.length; j++) {
					if (j < 2)
						xCoordinates[j] += 50 * i;
					else
						xCoordinates[j] -= 50 * i;
					yCoordinates[j] += 100 * i;
				}
				rectangles.add(new Wall(xCoordinates, yCoordinates));
			}
		}
	}

	public void rightRectangle() {
		for (int i = 0; i < numWalls; i++) {
			int[] xCoordinates = {50, 50, 650, 650};
			int[] yCoordinates = {1300, 1200, 1200, 1300};

			if ((x + 1) < board.get(0).length) {
				for (int j = 0; j < xCoordinates.length; j++) {
					if (j < 2)
						xCoordinates[j] += 50 * i;
					else
						xCoordinates[j] -= 50 * i;
					yCoordinates[j] -= 100 * i;
				}
				rectangles.add(new Wall(xCoordinates, yCoordinates));
			}
		}
	}

	public void ceilingStuff() {
		for (int i = 0; i < 13; i++) {
			int[] clX = {450, 450, 1300, 1300};
			int[] clY = {0, 150, 150, 0};

			for (int j = 0; j < clX.length; j++) {
				clY[j] += 50 * i;
			}
			ceilings.add(new Ceiling(clX, clY));
		}
	}

	public void frontWall() {
		int[] xStuff = {0, 0, 0, 0};
		int[] yStuff = {0, 0, 0, 0};
		sideWalls = new ArrayList<Wall>();

		for (int i = numWalls; i >= 0; i--) {
			int[] vert = {0, 0, 700, 700};
			int[] horiz = {450, 1300, 1300, 450};
			for (int j = 0; j < 4; j++) {
				if (j < 2)
					vert[j] += 50 * i;
				else
					vert[j] -= 50 * i;
			}
			horiz[0] += 100 * i;
			horiz[1] -= 100 * i;
			horiz[2] -= 100 * i;
			horiz[3] += 100 * i;

			if (direction == 1) { //right
				if (x + i < board.get(0).length && board.get(y)[x + i].equals("#"))
					sideWalls.add(new Wall(vert, horiz));
			}
			if (direction == 2) { //down
				if (y + i < board.size() && board.get(y + i)[x].equals("#"))
					sideWalls.add(new Wall(vert, horiz));
			}
			if (direction == 3) { //left
				if (x - i >= 0 && board.get(y)[x - i].equals("#"))
					sideWalls.add(new Wall(vert, horiz));
			}
			if (direction == 4) { //up
				if (y - i >= 0 && board.get(y - i)[x].equals("#"))
					sideWalls.add(new Wall(vert, horiz));
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if (play) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (direction == 1) { //right
					if ((x + 1) < board.get(0).length && !(board.get(y)[x + 1].equals("#"))) {
						x++;
						count++;
					}
				}
				if (direction == 2) { //down
					if ((y + 1) < board.size() && !(board.get(y + 1)[x].equals("#"))) {
						y++;
						count++;
					}
				}
				if (direction == 3) { //left
					if ((x - 1) < board.get(0).length && !(board.get(y)[x - 1].equals("#"))) {
						x--;
						count++;
					}
				}
				if (direction == 4) { //up
					if ((y - 1) < board.size() && !(board.get(y - 1)[x].equals("#"))) {
						y--;
						count++;
					}
				}
				moves++;
			}
			if (xLength > 0) {
				if (e.getKeyCode() == KeyEvent.VK_F) {
					if (numWalls == 3) {
						numWalls = 4;
						on = true;
					} else {
						numWalls = 3;
						on = false;
					}
				}
			} else {
				numWalls = 3;
				on = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				direction++;
				if (direction == 5)
					direction = 1;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				direction--;
				if (direction == 0)
					direction = 4;
			}
			if (moves == 3) {
				moves = 0;
				if (on)
					flashlightPower();
			}
		}

		walls = new ArrayList<Wall>();
		rectangles = new ArrayList<Wall>();
		ceilingStuff();
		rightRectangle();
		leftRectangle();
		rightSide();
		leftSide();
		frontWall();

		repaint();
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		bcX = e.getX();
		bcY = e.getY();
		out.println(bcX + "," + bcY);
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	//INNER CLASS FOR CEILING PRINTING
	public class Ceiling {
		int xStuff[];
		int yStuff[];

		public Ceiling(int[] x, int[] y) {
			xStuff = x;
			yStuff = y;

		}

		public Polygon getPolygon() {
			return new Polygon(xStuff, yStuff, xStuff.length);
		}

		public GradientPaint getPaint() {
			return new GradientPaint(xStuff[0], yStuff[0], new Color(47, 79, 79), xStuff[0], yStuff[2], new Color(192, 192, 192));
		}
	}

	//INNER CLASS FOR WALL PRINTING
	public class Wall {
		int xStuff[];
		int yStuff[];

		public Wall(int[] x, int[] y) {
			xStuff = x;
			yStuff = y;

		}

		public Polygon getPolygon() {
			return new Polygon(yStuff, xStuff, xStuff.length);
		}

		public GradientPaint getPaint() {
			return new GradientPaint(xStuff[0], yStuff[0], new Color(187, 184, 193), xStuff[2], yStuff[2], new Color(27, 16, 48));
		}
	}
}
