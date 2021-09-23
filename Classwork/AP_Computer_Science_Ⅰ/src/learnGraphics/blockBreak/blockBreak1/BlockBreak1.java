package Classwork.AP_Computer_Science_Ⅰ.src.learnGraphics.blockBreak.blockBreak1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

import static java.awt.Color.*;
import static java.awt.Font.BOLD;
import static java.awt.Toolkit.getDefaultToolkit;
import static java.lang.Math.random;
import static java.lang.Math.round;
import static java.lang.Thread.sleep;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.JFrame.MAXIMIZED_BOTH;


/**
 * Requirements for today's task.  Total of two moving elements:
 * <ol>
 *     <li>Create another ball to move around and run into blocks called ball2</li>
 *     <li>Animate ball2 to move around in a different pattern from ball1 (some random behavior would be great) and knock out blocks</li>
 *     <li>Detect if ball2 hits a block and change its trajectory if it does</li>
 * </ol>
 * <p>
 * <p>
 * <b>NOTE:</b> You do not need to do anything special if ball1 hits ball2 at this point
 * <p>
 * <p>
 * You will need to change four basic things to meet these minimum requirements:
 * <ol>
 *     <li>Set x2 and y2 in the constructor</li>
 *     <li>Add code to the bottom of paintComponent to paint ball2</li>
 *     <li>In the run method, increment x2 and y2</li>
 *     <li>Detect and handle collisions of ball2</li>
 * </ol>
 */
public class BlockBreak1 extends JPanel implements Runnable {
	private static final Dimension dimension = new Dimension(1000, 800);
	private final ArrayList<Rectangle> blocks = new ArrayList<>();
	private final Font f = new Font("Comic Sans MS", BOLD, 32);
	private String msg;
	private int x1, y1;
	private int x2, y2;
	private Shape ball1, ball2;


	private BlockBreak1() {
		x1 = 5;
		y1 = 5;
		var frame = new JFrame(this.getClass().getTypeName());
		frame.add(this);
		frame.setSize(1070, 865);
		frame.setExtendedState(MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		for (int i = 20; i < dimension.width; i += 75)
			for (int j = 40; j < dimension.height; j += 75)
				blocks.add(new Rectangle(i, j, 20, 20));
		msg = blocks.size() + " BLOCKS";
		var t = new Thread(this);
		t.start();
	}

	public static void main(String[] args) {
		new BlockBreak1();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		var g2d = (Graphics2D) g;
		g2d.setPaint(GRAY);
		g2d.fillRect(0, 0, getDefaultToolkit().getScreenSize().width, getDefaultToolkit().getScreenSize().height);
		g2d.setColor(RED);
		for (var r : blocks) g2d.fill(r);
		g2d.setColor(BLUE);
		g2d.setFont(f);
		g2d.drawString(msg, 400, 30);
		g2d.setColor(new Color(0, 255, 130));
		ball1 = new Ellipse2D.Double(x1, y1, 20, 20);
		g2d.fill(ball1);
		g2d.setColor(new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
		ball2 = new Ellipse2D.Double(x2, y2, 20, 20);
		g2d.fill(ball2);
	}

	public void run() {
		int incX1 = 1;
		int incY1 = 2;
		int incX2 = (int) (random() * 5) + 1;
		int incY2 = (int) (random() * 9) + 2;
		while (true) {
			x1 += incX1;
			if (random() > 0.995) incY1 *= -1;
			y1 += incY1;
			if (checkCollision(ball1)) {
				incX1 *= -1;
				incY1 *= -1;
			}
			if (x1 > (dimension.width - 30)) incX1 *= -1;
			if (x1 <= 0) incX1 *= -1;
			if (y1 > (dimension.height - 30)) incY1 *= -1;
			if (y1 <= 0) incY1 *= -1;
			x2 += incX2;
			if (random() > 0.995) incY2 *= -1;
			y2 += incY2;
			if (checkCollision(ball2)) {
				incX2 *= -1;
				incY2 *= -1;
			}
			if (x2 > (dimension.width - 30)) incX2 *= -1;
			if (x2 <= 0) incX2 *= -1;
			if (y2 > (dimension.height - 30)) incY2 *= -1;
			if (y2 <= 0) incY2 *= -1;
			msg = (blocks.size() > 0) ? (blocks.size() + " BLOCKS") : "NO MORE BLOCKS!";
			try {
				sleep(round((random() * 10) + 1));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

	private boolean checkCollision(Shape s) {
		for (int i = blocks.size() - 1; i >= 0; i--)
			if ((s != null) && s.intersects(blocks.get(i))) {
				blocks.remove(i);
				return true;
			}
		return false;
	}
}
