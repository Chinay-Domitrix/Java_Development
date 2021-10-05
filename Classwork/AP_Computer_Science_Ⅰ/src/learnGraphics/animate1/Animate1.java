package learnGraphics.animate1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

import static java.awt.Color.*;
import static java.awt.Font.PLAIN;
import static java.lang.Math.random;
import static java.lang.Math.round;
import static java.lang.Thread.sleep;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Requirements for today's experiment:
 * <p>
 * Total of two moving elements:
 * <ol>
 * <li>Must Print your name somewhere on canvas</li>
 * <li>Must have one element with a predictable movement pattern</li>
 * <li>Must have one element with a movement pattern that involves an element of
 * randomness</li>
 * </ol>
 * <p>
 * <p>
 * You will need to change two basic things to meet these minimum requirements:
 * <ol>
 * <li>Change the bottom of the paintComponent method to add a second animated
 * object</li>
 * <li>Change the run method to perform the math to find coordinates of animated
 * object</li>
 * </ol>
 */
public class Animate1 extends JPanel implements Runnable {
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 800;
	private final Font f;
	private int x1, y1;
	private int x2, y2;

	private Animate1() {
		x1 = 100;
		y1 = 100;
		JFrame frame = new JFrame();
		f = new Font("Comic Sans MS", PLAIN, 50);
		frame.add(this);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Thread t = new Thread(this);
		t.start();
	}

	public static void main(String[] args) {
		new Animate1();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		var g2d = (Graphics2D) g;
		g2d.setPaint(BLACK);
		g2d.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		g2d.setColor(RED);
		g2d.setFont(f);
		g2d.drawString("Animation Example", 50, 50);
		g2d.setPaint(YELLOW);
		int[] xPoints = {510, 570, 620, 700, 380};
		int[] yPoints = {220, 400, 370, 580, 230};
		g2d.fillPolygon(xPoints, yPoints, xPoints.length);
		for (int i = 80; i < 600; i += 40)
			for (int j = i + 220; j < 700; j += 40) {
				g2d.setPaint((random() < 0.05) ? magenta : pink);
				g2d.fillOval(i, j, 40, 40);
			}
		for (int i = 80; i < 600; i += 20)
			g2d.drawRect(i, i, 50 + (i / 10), 50 + (i / 10));
		g2d.setPaint(CYAN);
		int numPoints = (int) (random() * 50) + 20;
		int[] xArr = new int[numPoints], yArr = new int[numPoints];
		for (int i = 0; i < numPoints; i++) {
			xArr[i] = (int) (random() * 300) + 250;
			yArr[i] = (int) (random() * 120) + 80;
		}
		g2d.drawPolygon(xArr, yArr, numPoints);
		g2d.setPaint(RED);
		var oval = new Ellipse2D.Double(x1, y1, 40, 40);
		g2d.fill(oval);
		var oval2 = new Ellipse2D.Double(x2, y2, 40, 40);
		g2d.fill(oval2);
	}

	public void run() {
		int incX = 1;
		int incY = 1;
		int incX2 = (int) (random() * 5) + 1;
		int incY2 = (int) (random() * 5) + 1;
		while (true) {
			x1 += incX;
			y1 += incY;
			if (random() > 0.99)
				incY *= -1;
			if (x1 > (FRAME_WIDTH - 50))
				incX *= -1;
			if (x1 < 0)
				incX *= -1;
			if (y1 > (FRAME_HEIGHT - 50))
				incY *= -1;
			if (y1 < 0)
				incY *= -1;
			x2 += incX2;
			y2 += incY2;
			if (random() > 0.99)
				incY2 *= -1;
			if (x2 > (FRAME_WIDTH - 50))
				incX2 *= -1;
			if (x2 < 0)
				incX2 *= -1;
			if (y2 > (FRAME_HEIGHT - 50))
				incY2 *= -1;
			if (y2 < 0)
				incY2 *= -1;
			try {
				sleep(round((random() * 10) + 1));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
}
