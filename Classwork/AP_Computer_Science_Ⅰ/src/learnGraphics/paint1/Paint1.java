package learnGraphics.paint1;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static java.awt.Font.PLAIN;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/*
 * Requirements
 * 1) Must Print your name somewhere on canvas
 * 2) Must show at least one filled shape
 * 3) Must show at least one outline of a shape
 * 4) Must include at least on pattern generated by a loop
 */
public class Paint1 extends JPanel {
	private static final int frameWidth = 1000; // Set Dimension of full frame
	private static final int frameHeight = 800;
	private final Font f;

	private Paint1() {
		JFrame frame = new JFrame("Paint1");
		f = new Font("Comic Sans MS", PLAIN, 50); // Set Font: https://docs.oracle.com/javase/7/docs/api/java/awt/Font.html
		frame.add(this); // Add Object to Frame, this will invoke the paintComponent method
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Paint1();
	}

	public void paintComponent(Graphics g) { // Need to have this method to complete the painting
		var rand = new Random(new Random().nextLong());
		super.paintComponent(g);
		var g2d = (Graphics2D) g;
		g2d.setPaint(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
		g2d.fillRect(0, 0, frameWidth, frameHeight);
		g2d.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
		g2d.setFont(f);
		g2d.drawString("Chirag Baviskar", 50, 50);
		g2d.setPaint(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
		g2d.fillOval(700, 200, 40, 40);
		g2d.fill3DRect(700, 300, 100, 150, false);
		g2d.fillOval(700, 500, 40, 80);
		g2d.fillRect(700, 600, 140, 10);
		g2d.setPaint(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
		g2d.drawOval(850, 200, 40, 40);
		g2d.draw3DRect(850, 300, 100, 150, false);
		g2d.drawOval(850, 500, 40, 80);
		g2d.drawRect(850, 600, 140, 10);
		g2d.setPaint(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
		int[] xPoints = {510, 570, 620, 700, 380}, yPoints = {220, 400, 370, 580, 230};
		g2d.fillPolygon(xPoints, yPoints, xPoints.length);
		g2d.setPaint(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
		for (int i = 80; i < 600; i += 40)
			for (int j = i + 220; j < 700; j += 40)
				g2d.fillOval(i, j, 40, 40);
		for (int i = 80; i < 600; i += 20)
			g2d.drawRect(i, i, 50 + (i / 10), 50 + (i / 10));
		g2d.setPaint(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
		var numPoints = rand.nextInt(50) + 20;
		int[] xArr = new int[numPoints], yArr = new int[numPoints];
		for (int i = 0; i < numPoints; i++) {
			xArr[i] = rand.nextInt(300) + 250;
			yArr[i] = rand.nextInt(120) + 80;
		}
		g2d.drawPolygon(xArr, yArr, numPoints);
	}
}
