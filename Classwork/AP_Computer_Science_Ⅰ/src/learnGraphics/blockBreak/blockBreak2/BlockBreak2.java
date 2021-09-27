package learnGraphics.blockBreak.blockBreak2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import static java.awt.Color.*;
import static java.awt.Font.BOLD;
import static java.lang.Math.random;
import static java.util.Objects.requireNonNull;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class BlockBreak2 extends JPanel implements KeyListener, Runnable {
	private static final int frameWidth = 1000; // Set Dimension of full frame
	private static final int frameHeight = 800;
	private final ArrayList<WiggleBlock> blocks;
	private final Font f;
	private final Thread t;
	private final int y1; // coordinates of first animated object
	private boolean gameOn, win, left, right, up, down;
	private String msg;
	private int x1;
	private int x2, y2; // coordinates of second animated object
	private Color color;
	private Shape ball1, ball2; // ball1 has been created. Use it as an example to create ball2

	private BlockBreak2() {
		x1 = 0;
		y1 = frameHeight - 75;
		// Set x2, y2 as a starting point for ball2 here
		win = false;
		// Default not moving
		right = false; // Key Listener Related declarations
		// set other directions
		var frame = new JFrame();
		f = new Font("Comic Sans MS", BOLD, 32); // Set Font
													// https://docs.oracle.com/javase/7/docs/api/java/awt/Font.html
		frame.add(this);
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
		frame.addKeyListener(this); // Needed to use KeyListener
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Create a pattern of WiggleBlocks (you could do the same with regular static
		// blocks)
		blocks = new ArrayList<>();
		for (int i = 40; i < frameWidth; i += 75)
			for (int j = 40; j < (frameHeight - 75); j += 75)
				blocks.add(new WiggleBlock(i, j, 20, 20, false, 100, 45));
		int randIndex = (int) (random() * blocks.size());
		var tempBlock = blocks.get(randIndex);
		tempBlock.setGoal(true);
		blocks.set(randIndex, tempBlock); // Randomly set one block to be the Goal block and you win if you select it
		msg = "USE THE ARROW KEYS TO FIND THE PINK BLOCK"; // Initial message show briefly at start
		t = new Thread(this); // The thread
		t.start();
	}

	public static void main(String[] args) {
		new BlockBreak2();
	}

	public void paintComponent(Graphics g) { // Need to have this method to complete the painting
		super.paintComponent(g); // Need to call this first to initialize in parent class, do not change
		var g2d = (Graphics2D) g; // Cast to Graphics2D which is a subclass of Graphics with additional properties
		/* Fill background with a gray rectangle the size of the entire frame */
		g2d.setPaint(GRAY);
		g2d.fillRect(0, 0, frameWidth, frameHeight);
		g2d.setColor(RED);
		// Get the Blocks (or Wiggle Blocks) and paint one at a time. Paint the goal
		// pink and everything else red
		blocks.forEach(b -> {
			g2d.setColor(RED);
			if (b.isGoal())
				g2d.setColor(PINK);
			g2d.fill(b.getR());
		});
		g2d.setColor(BLUE);
		g2d.setFont(f); // NOTE: Font initialized in the constructor
		g2d.drawString(msg, 100, 30); // Write text from msg String
		g2d.setColor(new Color(0, 255, 130)); // Another way to set color. Parameters are red, green, blu on a scale of
												// 0-255
		ball1 = new Ellipse2D.Double(x1, y1, 25, 25); // Set the current position for ball1.
		g2d.fill(ball1);
	}

	/* This method does the math and creates the pause to create animation */
	public void run() {
		try {
			Thread.sleep(2000); // Delay a bit to allow user to read initial message
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		repaint();
		while (!win) { // Keep going until win or window is closed
			/* MUCH OF THE CODE BELOW HERE SHOULD BE COPIED AND ADAPTED FOR BALL2 */
			if (right && (x1 < (frameWidth - 25)))
				x1++;
			// If ball1 collides with the goal block you win. Otherwise,
			// the block is removed (inside the method)
			// and in this method you reverse its x and y direction
			if (blockInteraction(ball1)) {
				win = true;
				msg = "YOU WIN!";
			} else
				msg = (blocks.size() > 0) ? (blocks.size() + " BLOCKS") : "NO MORE BLOCKS!";
			try {
				Thread.sleep(5); // play with this number
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

	// This method was the previous checkcollision method, but now the check if you
	// ran into the
	// Goal block has been added.
	private boolean blockInteraction(Shape s) {
		for (int i = blocks.size() - 1; i >= 0; i--) {
			// Block temp = blocks.get(i);
			var temp = blocks.get(i);
			temp.move();
			blocks.set(i, temp);
			if ((s != null) && s.intersects(temp.getR())) {
				if (temp.isGoal())
					return true;
				blocks.remove(i);
			}
		}
		return false;
	}

	// KeyListener Methods Below
	// https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyListener.html
	//
	public void keyPressed(KeyEvent ke) {
		requireNonNull(ke);
		if (ke.getKeyCode() == 39)
			right = true;
		else if (ke.getKeyCode() == 37)
			left = true;
		else if (ke.getKeyCode() == 38)
			up = true;
		else if (ke.getKeyCode() == 40)
			down = true;
	}

	public void keyReleased(KeyEvent ke) {
		requireNonNull(ke);
		if (ke.getKeyCode() == 39)
			right = false;
		else if (ke.getKeyCode() == 37)
			left = false;
		else if (ke.getKeyCode() == 38)
			up = false;
		else if (ke.getKeyCode() == 40)
			down = false;
	}

	public void keyTyped(KeyEvent ke) {
		// This method is included because it is required by the KeyListener Interface
		// it is not used for basic movement but can be used to get actual input
		// show the keyboard such as typing name.
	}
}
