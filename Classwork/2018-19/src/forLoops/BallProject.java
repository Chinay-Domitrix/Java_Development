package forLoops;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

import static java.lang.System.*;

public class BallProject {
	public static void main(final String[] args) {
		final Scanner in = new Scanner(System.in);
		final BallCanvas ballCanvas = new BallCanvas();
		int xpos = 0, ypos = 0;
		out.print("Which task do you want to run? ");
		final int task = in.nextInt();
		switch (task) {
			default:
				out.println("Error");
				exit(0);
				break;
			case 1:
//	    Task 1
				for (ypos = 0; ypos <= 550; ypos += 5) {
					ballCanvas.moveBall(xpos, ypos);
					ballCanvas.repaint();
					ballCanvas.delay(ypos);
				}
				break;
			case 2:
				out.print("Which sub-program do you want to run, one or two? ");
				final int t2c = in.nextInt();
				switch (t2c) {
					default:
						out.println("Error");
						exit(0);
					case 1:
//		Task 2, Sub-program 1
//		I set a fixed delay time, rather than the increasing one in Task 1.
						for (ypos = 0; ypos <= 550; ypos += 5) {
							ballCanvas.moveBall(xpos, ypos);
							ballCanvas.repaint();
							ballCanvas.delay(15.625);
						}
						break;
					case 2:
//		Task 2, Sub-program 2
//		I increased the interval of change in the y-position and halved the delay, relative to Task 2, Sub-program 1
						xpos = 0;
						for (ypos = 0; ypos <= 550; ypos += 10) {
							ballCanvas.moveBall(xpos, ypos);
							ballCanvas.repaint();
							ballCanvas.delay(15.625);
						}
						break;
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
				// noinspection InfiniteLoopStatement
				while (true) {
					for (int i = 0; i <= 550; i += change) {
						ypos += change;
						ballCanvas.moveBall(xpos, ypos);
						ballCanvas.repaint();
						ballCanvas.delay(15.625);
						if (i == 550) {
							change *= -1;
						}
					}
				}
			case 6:
				int xchange = 10;
				int ychange = 10;
				final long starttime = currentTimeMillis();
				while (currentTimeMillis() - starttime <= 30000) {
					for (int i = 0; i <= 550 && xpos >= 0 && ypos >= 0; i += 10) {
						xpos += xchange;
						ypos += ychange;
						ballCanvas.moveBall(xpos, ypos);
						ballCanvas.repaint();
						ballCanvas.delay(15.625);
						if (xpos == 500)
							xchange *= -1;
						if (ypos == 550)
							ychange *= -1;
						if (xpos == 0 && ypos != 0)
							xchange *= -1;
						if (xpos != 0 && ypos == 0)
							ychange *= -1;
						if (xpos == 0 && ypos == 0) {
							xchange *= -1;
							ychange *= -1;
						}
					}
				}
				break;
			case 7:
				int y;
				for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
					for (xpos = 0; xpos <= 550; xpos += 5) {
						y = -(2 * (int) Math.pow(xpos, 2)) / 275 + 4 * xpos;
						ypos = 550 - y;
						ballCanvas.moveBall(xpos, ypos);
						ballCanvas.repaint();
						ballCanvas.delay(15.625);
					}
					for (xpos = 550; xpos >= 0; xpos -= 5) {
						y = -(2 * (int) Math.pow(xpos, 2)) / 275 + 4 * xpos;
						ypos = 550 - y;
						ballCanvas.moveBall(xpos, ypos);
						ballCanvas.repaint();
						ballCanvas.delay(15.625);
					}
				}
		}
		in.close();
	}

	static class BallCanvas {
		private final Ball ball1;
		private final JFrame frame;

		BallCanvas() {
			frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(600, 600);
			ball1 = new Ball();
			frame.add(ball1);
			frame.setVisible(true);
		}

		void delay(final double x) {
			try {
				Thread.sleep((long) x);
			} catch (final Exception ignored) {
			}
		}

		void moveBall(final int x, final int y) {
			ball1.changePosition(x, y);
		}

		void repaint() {
			frame.repaint();

		}

		static class Ball extends JPanel {
			private static final long serialVersionUID = 8051694504739059413L;
			final Color c;
			int xPos, yPos;

			Ball() {
				xPos = 0;
				yPos = 0;
				c = Color.RED;
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
				g.setColor(Color.BLUE);
				g.drawLine(300, 1, 300, 600);
			}
		}
	}
}