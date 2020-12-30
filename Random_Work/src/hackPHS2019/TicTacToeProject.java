package hackPHS2019;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;
import static java.lang.Math.random;
import static java.lang.System.in;
import static java.lang.System.out;
import static java.lang.Thread.sleep;
import static java.util.Arrays.fill;
import static java.util.Arrays.stream;
import static javax.imageio.ImageIO.read;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

final class TicTacToeProject {
	TicTacToeProject() {
		var board = new TicTacToeBoard(620, 700);
		board.displayGame(false);
		try (var scanner = new Scanner(in)) {
			final int[][] grid = new int[][]{new int[]{0, 200, 600, 200}, new int[]{0, 400, 600, 400}, new int[]{200, 0, 200, 600}, new int[]{400, 0, 400, 600}};
			board.defineBoard(grid);
			var ifn = "TicTacToe";
			var ft = ".png";
			var fPs = new String[]{ifn + 'X' + ft, ifn + 'O' + ft};
			board.setFiles(fPs[0], fPs[1]);
			var play = new char[3][3];
			stream(play).forEachOrdered(chars -> fill(chars, ' '));
			board.setBoard(play);
			out.print("How many players? ");
			board.displayGame(true);
			var counter = 0;
			var pieces = new int[2];
			switch (scanner.nextInt()) {
				case 0:
					do {
						do {
							counter++;
							out.print("Computer's turn: ");
							var placementIn = (int) (random() * 9) + 1;
							out.println(placementIn);
							placementDetector(placementIn, pieces);
							placePiece(placementIn, play, counter, pieces);
							board.repaint();
							board.delay();
							if (play[pieces[0]][pieces[1]] != ' ') break;
						} while (play[pieces[0]][pieces[1]] == ' ');
						do {
							counter++;
							out.print("Computer's turn: ");
							var placementIn = (int) (random() * 9) + 1;
							out.println(placementIn);
							placementDetector(placementIn, pieces);
							placePiece(placementIn, play, counter, pieces);
							board.repaint();
							board.delay();
							if (play[pieces[0]][pieces[1]] != ' ') break;
						} while (play[pieces[0]][pieces[1]] == ' ');
					} while (play[pieces[0]][pieces[1]] == ' ');
					break;
				case 1:
					do {
						do {
							counter++;
							out.print("Player's turn: ");
							var placementIn = scanner.nextInt();
							placementDetector(placementIn, pieces);
							placePiece(placementIn, play, counter, pieces);
							board.repaint();
							board.delay();
							if (play[pieces[0]][pieces[1]] != ' ') break;
						} while (play[pieces[0]][pieces[1]] == ' ');
						do {
							counter++;
							out.print("Computer's turn: ");
							var placementIn = (int) (random() * 9) + 1;
							out.println(placementIn);
							placementDetector(placementIn, pieces);
							placePiece(placementIn, play, counter, pieces);
							board.repaint();
							board.delay();
							if (play[pieces[0]][pieces[1]] != ' ') break;
						} while (play[pieces[0]][pieces[1]] == ' ');
					} while (play[pieces[0]][pieces[1]] == ' ');
					break;
				case 2:
					do {
						do {
							counter++;
							out.print("Player 1's turn: ");
							var placementIn = scanner.nextInt();
							placementDetector(placementIn, pieces);
							placePiece(placementIn, play, counter, pieces);
							board.repaint();
							board.delay();
							if (play[pieces[0]][pieces[1]] != ' ') break;
						} while (play[pieces[0]][pieces[1]] == ' ');
						do {
							counter++;
							out.print("Player 2's turn: ");
							var placementIn = scanner.nextInt();
							placementDetector(placementIn, pieces);
							placePiece(placementIn, play, counter, pieces);
							board.repaint();
							board.delay();
							if (play[pieces[0]][pieces[1]] != ' ') break;
						} while (play[pieces[0]][pieces[1]] == ' ');
					} while (play[pieces[0]][pieces[1]] == ' ');
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + scanner.nextInt());
			}
		}
	}

	private void placementDetector(int placementIn, int[] pieces) {
		switch (placementIn) {
			case 1:
				pieces[0] = 0;
				pieces[1] = 0;
				break;
			case 2:
				pieces[0] = 0;
				pieces[1] = 1;
				break;
			case 3:
				pieces[0] = 0;
				pieces[1] = 2;
				break;
			case 4:
				pieces[0] = 1;
				pieces[1] = 0;
				break;
			case 5:
				pieces[0] = 1;
				pieces[1] = 1;
				break;
			case 6:
				pieces[0] = 1;
				pieces[1] = 2;
				break;
			case 7:
				pieces[0] = 2;
				pieces[1] = 0;
				break;
			case 8:
				pieces[0] = 2;
				pieces[1] = 1;
				break;
			case 9:
				pieces[0] = 2;
				pieces[1] = 2;
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + placementIn);
		}
	}

	private void placePiece(int a, char[][] b, int c, int[] d) {
		if ((a == 1) | (a == 2) | (a == 3) | (a == 4) | (a == 5) | (a == 6) | (a == 7) | (a == 8) | (a == 9))
			b[d[0]][d[1]] = ((c % 2) == 0) ? 'o' : 'x';
	}
}

class TicTacToeBoard {
	private final JFrame f;
	private int[][] b;
	private char[][] p;

	TicTacToeBoard(int width, int height) {
		f = new JFrame("Tic-Tac-Toe");
		f.setIconImage(new ImageIcon("Tic-tac-toe.png").getImage());
		f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		f.setSize(width, height);
	}

	void defineBoard(int[][] a) {
		b = a;
	}

	void delay() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			new Throwable().addSuppressed(e);
		}
	}

	void displayGame(boolean a) {
		f.setVisible(a);
	}

	void repaint() {
		f.repaint();
	}

	void setBoard(char[][] a) {
		p = a;
	}

	void setFiles(String a, String b) {
		f.add(new DrawTicTacToeBoard(a, b));
	}

	class DrawTicTacToeBoard extends JPanel {
		private final BufferedImage[] images = new BufferedImage[2];

		private DrawTicTacToeBoard(String... a) {
			setBackground(RED);
			for (var i = 0; i < 2; i++)
				try {
					images[i] = read(new File(a[i]));
				} catch (IOException e) {
					new Throwable().addSuppressed(e);
				}
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (var i = 0; i < 3; i++)
				for (var j = 0; j < 3; j++)
					if (p[i][j] == 'x')
						g.drawImage(images[0], (j * 200) + 25, (i * 200) + 25, null);
					else if (p[i][j] == 'o')
						g.drawImage(images[1], (j * 200) + 25, (i * 200) + 25, null);
			g.setColor(BLUE);
			for (int i = 0; i < 6; i++) {
				g.drawLine(b[0][0], b[0][1] + i, b[0][2], b[0][3] + i);
				g.drawLine(b[1][0], b[1][1] + i, b[1][2], b[1][3] + i);
				g.drawLine(b[2][0] + i, b[2][1], b[2][2] + i, b[2][3]);
				g.drawLine(b[3][0] + i, b[3][1], b[3][2] + i, b[3][3]);
			}
		}
	}
}