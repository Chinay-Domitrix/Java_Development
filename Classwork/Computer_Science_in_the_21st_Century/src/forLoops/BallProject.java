package forLoops;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.*;
import static java.lang.System.*;
import static java.lang.Thread.sleep;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class BallProject {
	public static void main(final String[] args) {
		final var input = new Scanner(in);
		final var ballCanvas = new BallCanvas();
		var xpos = 0;
		var ypos = 0;
		out.print("Which task do you want to run? ");
		final int task = input.nextInt();
		switch (task) {
			case 1:
				for (ypos = 0; ypos <= 550; ypos += 5) {
					ballCanvas.moveBall(xpos, ypos);
					ballCanvas.repaint();
					ballCanvas.delay(ypos);
				}
				break;
			case 2:
				out.print("Which sub-program do you want to run, one or two? ");
				final int t2c = input.nextInt();
				switch (t2c) {
					case 1:
						for (ypos = 0; ypos <= 550; ypos += 5) {
							ballCanvas.moveBall(xpos, ypos);
							ballCanvas.repaint();
							ballCanvas.delay(15.625);
						}
						break;
					case 2:
						xpos = 0;
						for (ypos = 0; ypos <= 550; ypos += 10) {
							ballCanvas.moveBall(xpos, ypos);
							ballCanvas.repaint();
							ballCanvas.delay(15.625);
						}
						break;
					default:
						throw new IllegalStateException("Unexpected value: " + t2c);
				}
				break;
			case 3:
				for (ypos = 0; ypos <= 550; ypos += 10) {
					ballCanvas.moveBall(xpos, ypos);
					ballCanvas.repaint();
					ballCanvas.delay(15.625);
				}
				for (ypos = 550; ypos >= 0; ypos -= 10) {
					ballCanvas.moveBall(xpos, ypos);
					ballCanvas.repaint();
					ballCanvas.delay(15.625);
				}
				break;
			case 4:
				xpos = 250;
				for (ypos = 0; ypos <= 550; ypos += 10) {
					ballCanvas.moveBall(xpos, ypos);
					ballCanvas.repaint();
					ballCanvas.delay(15.625);
				}
				for (xpos = 250; xpos <= 290; xpos += 10) {
					ballCanvas.moveBall(xpos, ypos);
					ballCanvas.repaint();
					ballCanvas.delay(15.625);
				}
				for (ypos = 550; ypos >= 0; ypos -= 10) {
					ballCanvas.moveBall(xpos, ypos);
					ballCanvas.repaint();
					ballCanvas.delay(15.625);
				}
				break;
			case 5:
				int change = 10;
//				noinspection InfiniteLoopStatement
				while (true) for (int i = 0; i <= 550; i += change) {
					ypos += change;
					ballCanvas.moveBall(xpos, ypos);
					ballCanvas.repaint();
					ballCanvas.delay(15.625);
					if (i == 550) change *= -1;
				}
			case 6:
				int xchange = 10;
				int ychange = 10;
				final long starttime = currentTimeMillis();
				while ((currentTimeMillis() - starttime) <= 30000)
					for (int i = 0; (i <= 550) && (xpos >= 0) && (ypos >= 0); i += 10) {
						xpos += xchange;
						ypos += ychange;
						ballCanvas.moveBall(xpos, ypos);
						ballCanvas.repaint();
						ballCanvas.delay(15.625);
						if (xpos == 500) xchange *= -1;
						if (ypos == 550) ychange *= -1;
						if (xpos == 0 && ypos != 0) xchange *= -1;
						if (xpos != 0 && ypos == 0) ychange *= -1;
						if (xpos == 0 && ypos == 0) {
							xchange *= -1;
							ychange *= -1;
						}
					}
				break;
			case 7: {
//				noinspection InfiniteLoopStatement
				while (true) {
					for (xpos = 0; xpos <= 550; xpos += 25) {
						ballCanvas.moveBall(xpos, 550 - ((-((int) pow(xpos, 2)) << 1) / 275) - (4 * xpos));
						ballCanvas.repaint();
						ballCanvas.delay(15.625);
					}
					for (xpos = 550; xpos >= 0; xpos -= 25) {
						ballCanvas.moveBall(xpos, 550 - ((-((int) pow(xpos, 2)) << 1) / 275) - (4 * xpos));
						ballCanvas.repaint();
						ballCanvas.delay(15.625);
					}
				}
			}
			case 8:
				for (int i = MIN_VALUE; i < MAX_VALUE; i++) {
					for (xpos = 0; xpos <= 550; xpos++) {
						ballCanvas.moveBall(xpos, toIntExact(round(225 - (10 * sin(xpos)))));
						ballCanvas.repaint();
						ballCanvas.delay(15.625);
					}
					for (xpos = 550; xpos >= 0; xpos--) {
						ballCanvas.moveBall(xpos, toIntExact(round(225 - (10 * sin(xpos)))));
						ballCanvas.repaint();
						ballCanvas.delay(15.625);
					}
				}
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + task);
		}
		input.close();
	}
}

final class BallCanvas {
	private final Ball ball;
	private final JFrame frame;

	BallCanvas() {
		frame = new JFrame("BallCanvas");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		ball = new Ball();
		frame.add(ball);
		frame.setVisible(true);
	}

	void delay(final double x) {
		try {
			sleep((long) x);
		} catch (InterruptedException e) {
			new InterruptedException().addSuppressed(e);
		}
	}

	void moveBall(final int x, final int y) {
		ball.changePosition(x, y);
	}

	void repaint() {
		frame.repaint();
	}

	static class Ball extends JPanel {
		final Color c;
		int xPos, yPos;

		Ball() {
			xPos = 0;
			yPos = 0;
			c = RED;
		}

		void changePosition(final int x, final int y) {
			xPos = x;
			yPos = y;
		}

		@Override
		public void paintComponent(final Graphics g) {
			super.paintComponent(g);
			g.setColor(c);
			g.fillOval(xPos, yPos, 50, 50);
			g.setColor(BLUE);
			g.drawLine(300, 1, 300, 600);
		}
	}
}