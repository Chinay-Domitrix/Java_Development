package twoDimensionalArrays.ticTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.awt.Color.RED;
import static java.lang.Math.*;
import static java.lang.System.*;
import static java.lang.Thread.sleep;
import static javax.imageio.ImageIO.read;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

class TicTacToeProject {
	public static void main(String[] args) {
		var board = new TicTacToeBoard();
		board.displayGame(false);
		var grid = new int[][]{{0, 200, 600, 200}, {0, 400, 600, 400}, {200, 0, 200, 600}, {400, 0, 400, 600}};
		board.defineBoard(grid);
//		This line changes from device to device.
		var ifp = "C:\\Users\\china.DESKTOP-ISAVF5I\\Programming\\Java_Development\\Classwork\\2018-19\\resources\\twoDimensionalArrays\\ticTacToe\\";
		var ifn = "TicTacToe";
		var fi = ifp + ifn;
		var ft = ".png";
		var fPs = new String[]{fi + 'X' + ft, fi + 'O' + ft};
		board.setFiles(fPs[0], fPs[1]);
		var play = new char[3][3];
		for (var i = 0; i < 3; i++) for (var j = 0; j < 3; j++) play[i][j] = ' ';
		board.setBoard(play);
		out.print("How many players? ");
		board.displayGame(true);
		int counter = 0;
		int[] pieces = new int[2];
		int placementIn;
		switch (new Scanner(System.in).nextInt()) {
			case 0:
				do {
					for (var i = 0; i < 2; i++) {
						do {
							counter++;
							placementIn = toIntExact(round((random() * 10) + 1));
							out.println(placementIn);
							placementDetector(placementIn, pieces);
							placePiece(placementIn, play, counter, pieces);
							board.repaint();
							board.delay();
							if (play[pieces[0]][pieces[1]] != ' ') break;
						} while (play[pieces[0]][pieces[1]] == ' ');
					}
				} while (play[pieces[0]][pieces[1]] == ' ');
				break;
			case 1:
				do {
					do {
						counter++;
						out.print("Player's turn: ");
						placementIn = new Scanner(System.in).nextInt();
						placementDetector(placementIn, pieces);
						placePiece(placementIn, play, counter, pieces);
						board.repaint();
						board.delay();
						if (play[pieces[0]][pieces[1]] != ' ') break;
					} while (play[pieces[0]][pieces[1]] == ' ');
					do {
						counter++;
						out.print("Computer's turn: ");
						placementIn = toIntExact(round((random() * 10) + 1));
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
						placementIn = new Scanner(System.in).nextInt();
						placementDetector(placementIn, pieces);
						placePiece(placementIn, play, counter, pieces);
						board.repaint();
						board.delay();
						if (play[pieces[0]][pieces[1]] != ' ') break;
					} while (play[pieces[0]][pieces[1]] == ' ');
					do {
						counter++;
						out.print("Player 2's turn: ");
						placementIn = new Scanner(System.in).nextInt();
						placementDetector(placementIn, pieces);
						placePiece(placementIn, play, counter, pieces);
						board.repaint();
						board.delay();
						if (play[pieces[0]][pieces[1]] != ' ') break;
					} while (play[pieces[0]][pieces[1]] == ' ');
				} while (play[pieces[0]][pieces[1]] == ' ');
				break;
			default:
				err.println("Error.");
				exit(0);
				break;
		}
	}

	private static void placementDetector(int placementIn, int[] pieces) {
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

	private static void placePiece(int a, char[][] b, int c, int[] d) {
		if ((a == 1) || (a == 2) || (a == 3) || (a == 4) || (a == 5) || (a == 6) || (a == 7) || (a == 8) || (a == 9))
			b[d[0]][d[1]] = ((c % 2) == 0) ? 'o' : 'x';
	}

	static class TicTacToeBoard {
		private final JFrame f;
		private int[][] b;
		private char[][] p;

		private TicTacToeBoard() {
			f = new JFrame("Tic-Tac-Toe");
			f.setIconImage(new ImageIcon("C:\\Users\\china.DESKTOP-ISAVF5I\\Programming\\Java_Development\\Classwork\\2018-19\\resources\\twoDimensionalArrays\\ticTacToe\\Tic-tac-toe.png").getImage());
			f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			f.setSize(620, 700);
		}

		private void defineBoard(int[][] a) {
			b = a;
		}

		private void delay() {
			try {
				sleep(1000);
			} catch (InterruptedException ignored) {
			}
		}

		private void displayGame(boolean a) {
			f.setVisible(a);
		}

		private void repaint() {
			f.repaint();
		}

		private void setBoard(char[][] a) {
			p = a;
		}

		private void setFiles(String a, String b) {
			f.add(new DrawTicTacToeBoard(a, b));
		}

		class DrawTicTacToeBoard extends JPanel {
			final BufferedImage[] images = new BufferedImage[2];

			private DrawTicTacToeBoard(String a, String b) {
				String[] y = {a, b};
				setBackground(RED);
				try {
					for (int i = 0; i < 2; i++) images[i] = read(new File(y[i]));
				} catch (IOException ignored) {
				}
			}

			@Override
			public void paintComponent(Graphics a) {
				super.paintComponent(a);
				for (int i = 0; i < 3; i++)
					for (int j = 0; j < 3; j++)
						switch (p[i][j]) {
							case 'x':
								a.drawImage(images[0], j * 200 + 25, i * 200 + 25, null, null);
								break;
							case 'o':
								a.drawImage(images[1], j * 200 + 25, i * 200 + 25, null, null);
								break;
							default:
								throw new IllegalStateException("Unexpected value: " + p[i][j]);
						}
				a.setColor(Color.BLUE);
				for (int i = 0; i < 6; i++) {
					a.drawLine(b[0][0], b[0][1] + i, b[0][2], b[0][3] + i);
					a.drawLine(b[1][0], b[1][1] + i, b[1][2], b[1][3] + i);
					a.drawLine(b[2][0] + i, b[2][1], b[2][2] + i, b[2][3]);
					a.drawLine(b[3][0] + i, b[3][1], b[3][2] + i, b[3][3]);
				}
			}
		}
	}
}