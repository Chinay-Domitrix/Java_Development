/*
package arrays.twoDimensionalArrays.ticTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;
import static java.lang.Math.random;
import static java.lang.System.*;
import static java.lang.Thread.sleep;
import static javax.imageio.ImageIO.read;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

class TicTacToeProject {
	public static void main(String[] args) {
		var board = new TicTacToeBoard();
		board.displayGame(false);
		board.defineBoard(new int[][]{{0, 200, 600, 200}, {0, 400, 600, 400}, {200, 0, 200, 600}, {400, 0, 400, 600}});
//		The following line changes from device to device.
		var fPs = new String[]{"C:\\Users\\china.DESKTOP-ISAVF5I\\Programming\\Java_Development\\Classwork\\Computer_Science_in_the_21st_Century\\resources\\arrays.twoDimensionalArrays\\ticTacToe\\TicTacToeX.png",
				"C:\\Users\\china.DESKTOP-ISAVF5I\\Programming\\Java_Development\\Classwork\\Computer_Science_in_the_21st_Century\\resources\\arrays.twoDimensionalArrays\\ticTacToe\\TicTacToeO.png"};
		board.setFiles(fPs[0], fPs[1]);
		var play = new char[3][3];
		for (var i = 0; i < 3; i++) for (var j = 0; j < 3; j++) play[i][j] = ' ';
		board.setBoard(play);
		out.print("How many players? ");
		board.displayGame(true);
		int counter = 0;
		int[] pieces = new int[2];
		int placementIn;
		switch (new Scanner(in).nextInt()) {
			case 0:
				do {
					for (var i = 0; i < 2; i++)
						do {
							counter++;
							placementIn = (int) (random() * 9) + 1;
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
						placementIn = new Scanner(in).nextInt();
						placementDetector(placementIn, pieces);
						placePiece(placementIn, play, counter, pieces);
						board.repaint();
						board.delay();
						if (play[pieces[0]][pieces[1]] != ' ') break;
					} while (play[pieces[0]][pieces[1]] == ' ');
					do {
						counter++;
						out.print("Computer's turn: ");
						placementIn = (int) (random() * 9) + 1;
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
						placementIn = new Scanner(in).nextInt();
						placementDetector(placementIn, pieces);
						placePiece(placementIn, play, counter, pieces);
						board.repaint();
						board.delay();
						if (play[pieces[0]][pieces[1]] != ' ') break;
					} while (play[pieces[0]][pieces[1]] == ' ');
					do {
						counter++;
						out.print("Player 2's turn: ");
						placementIn = new Scanner(in).nextInt();
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
				throw new IllegalArgumentException("The placement location value must be between one and nine, inclusive.");
		}
	}

	private static void placePiece(int a, char[][] b, int c, int[] d) {
		*/
/*assert (a >= 1) && (a <= 9) : "Location value must be between one and nine, inclusive.";
		requireNonNull(b);
		requireNonNull(d);
		b[d[0]][d[1]] = ((c % 2) == 0) ? 'o' : 'x';*//*

		if ((a == 1) || (a == 2) || (a == 3) || (a == 4) || (a == 5) || (a == 6) || (a == 7) || (a == 8) || (a == 9))
			b[d[0]][d[1]] = ((c % 2) == 0) ? 'o' : 'x';
	}

	static class TicTacToeBoard {
		private final JFrame f = new JFrame("Tic-Tac-Toe");
		private int[][] b;
		private char[][] p;

		private TicTacToeBoard() {
//			The following line changes from device to device.
			f.setIconImage(new ImageIcon("C:/Users/china.DESKTOP-ISAVF5I/Programming/Java_Development/Classwork/Computer_Science_in_the_21st_Century/resources/arrays.twoDimensionalArrays/ticTacToe/Tic-tac-toe.png").getImage());
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
				setBackground(RED);
				try {
					for (var i = 0; i < 2; i++) images[i] = read(new File(new String[]{a, b}[i]));
				} catch (IOException ignored) {
				}
			}

			@Override
			public void paintComponent(Graphics a) {
				super.paintComponent(a);
				for (int i = 0; i < 3; i++)
					for (int j = 0; j < 3; j++)
						if (p[i][j] == 'x') a.drawImage(images[0], j * 200 + 25, i * 200 + 25, null, null);
						else if (p[i][j] == 'o') a.drawImage(images[1], j * 200 + 25, i * 200 + 25, null, null);
//						else throw new IllegalStateException("Unexpected value: " + p[i][j]);
				a.setColor(BLUE);
				for (int i = 0; i < 6; i++) {
					a.drawLine(b[0][0], b[0][1] + i, b[0][2], b[0][3] + i);
					a.drawLine(b[1][0], b[1][1] + i, b[1][2], b[1][3] + i);
					a.drawLine(b[2][0] + i, b[2][1], b[2][2] + i, b[2][3]);
					a.drawLine(b[3][0] + i, b[3][1], b[3][2] + i, b[3][3]);
				}
			}
		}
	}
}*/
package Classwork.Computer_Science_in_the_21st_Century.src.arrays.twoDimensionalArrays.ticTacToe;

import org.jetbrains.annotations.Contract;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;
import static java.lang.Math.random;
import static java.lang.System.*;
import static java.lang.Thread.sleep;
import static java.util.Arrays.deepToString;
import static java.util.Objects.requireNonNull;
import static javax.imageio.ImageIO.read;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

class TicTacToeProject {
	public static void main(String[] args) {
		var board = new TicTacToeBoard(620, 700);
		board.displayGame(false);
		var scanner = new Scanner(in);
		board.defineBoard(new int[][]{{0, 200, 600, 200}, {0, 400, 600, 400}, {200, 0, 200, 600}, {400, 0, 400, 600}});
		var fPs = new String[] {
				"Classwork/Computer_Science_in_the_21st_Century/images/arrays/twoDimensionalArrays/ticTacToe/TicTacToeX.png",
				"Classwork/Computer_Science_in_the_21st_Century/images/arrays/twoDimensionalArrays/ticTacToe/TicTacToeO.png" };
		board.setFiles(fPs[0], fPs[1]);
		var play = new char[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				play[i][j] = ' ';
		board.setBoard(play);
		out.print("How many players? ");
		board.displayGame(true);
		int counter = 0;
		var pieces = new int[2];
		int placementIn;
		switch (scanner.nextInt()) {
			case 0:
				do {
					both:
					for (int i = 0; i < 2; i++)
						do {
							if (!deepToString(play).contains(" ")) break both;
							do {
								placementIn = (int) (random() * 9) + 1;
							} while (isNotValid(play, placementIn));
							out.println(placementIn);
							placementDetector(placementIn, pieces);
							placePiece(placementIn, play, counter, pieces);
							board.repaint();
							board.delay();
							if (play[pieces[0]][pieces[1]] != ' ') {
								counter++;
								break;
							}
						} while ((play[pieces[0]][pieces[1]] == ' ') & (counter < 9) & isNotVictory(play));
				} while (deepToString(play).contains(" "));
				break;
			case 1:
				do {
					do {
						if (!deepToString(play).contains(" ")) break;
						out.print("Player's turn: ");
						do {
							placementIn = scanner.nextInt();
							if (isNotValid(play, placementIn))
								out.println("Please enter a valid position.");
						} while (isNotValid(play, placementIn));
						placementDetector(placementIn, pieces);
						placePiece(placementIn, play, counter, pieces);
						board.repaint();
						board.delay();
						if (play[pieces[0]][pieces[1]] != ' ') {
							counter++;
							break;
						}
					} while ((play[pieces[0]][pieces[1]] == ' ') & (counter < 9) & isNotVictory(play));
					do {
						if (!deepToString(play).contains(" ")) break;
						counter++;
						out.print("Computer's turn: ");
						do {
							placementIn = (int) (random() * 9) + 1;
						} while (isNotValid(play, placementIn));
						out.println(placementIn);
						placementDetector(placementIn, pieces);
						placePiece(placementIn, play, counter, pieces);
						board.repaint();
						board.delay();
						if (play[pieces[0]][pieces[1]] != ' ') {
							counter++;
							break;
						}
					} while ((play[pieces[0]][pieces[1]] == ' ') & (counter < 9) & isNotVictory(play));
				} while (deepToString(play).contains(" "));
				scanner.close();
				break;
			case 2:
				do {
					do {
						if (!deepToString(play).contains(" ")) break;
						out.print("Player 1's turn: ");
						do {
							placementIn = scanner.nextInt();
							if (isNotValid(play, placementIn))
								out.println("Please enter a valid position.");
						} while (isNotValid(play, placementIn));
						placementDetector(placementIn, pieces);
						placePiece(placementIn, play, counter, pieces);
						board.repaint();
						board.delay();
						if (play[pieces[0]][pieces[1]] != ' ') {
							counter++;
							break;
						}
					} while ((play[pieces[0]][pieces[1]] == ' ') && (counter < 9) && isNotVictory(play));
					do {
						if (!deepToString(play).contains(" ")) break;
						out.print("Player 2's turn: ");
						do {
							placementIn = scanner.nextInt();
							if (isNotValid(play, placementIn))
								out.println("Please enter a valid position.");
						} while (isNotValid(play, placementIn));
						placementDetector(placementIn, pieces);
						placePiece(placementIn, play, counter, pieces);
						board.repaint();
						board.delay();
						if (play[pieces[0]][pieces[1]] != ' ') {
							counter++;
							break;
						}
					} while ((play[pieces[0]][pieces[1]] == ' ') && (counter < 9) && isNotVictory(play));
				} while (deepToString(play).contains(" "));
				scanner.close();
				break;
			default:
				scanner.close();
				err.println("Error.");
				exit(0);
				break;
		}
		victory(play);
	}

	private static void placementDetector(int placementIn, int[] pieces) {
		if (placementIn == 1) {
			pieces[0] = 0;
			pieces[1] = 0;
		} else if (placementIn == 2) {
			pieces[0] = 0;
			pieces[1] = 1;
		} else if (placementIn == 3) {
			pieces[0] = 0;
			pieces[1] = 2;
		} else if (placementIn == 4) {
			pieces[0] = 1;
			pieces[1] = 0;
		} else if (placementIn == 5) {
			pieces[0] = 1;
			pieces[1] = 1;
		} else if (placementIn == 6) {
			pieces[0] = 1;
			pieces[1] = 2;
		} else if (placementIn == 7) {
			pieces[0] = 2;
			pieces[1] = 0;
		} else if (placementIn == 8) {
			pieces[0] = 2;
			pieces[1] = 1;
		} else if (placementIn == 9) {
			pieces[0] = 2;
			pieces[1] = 2;
		}
	}

	private static void placePiece(int a, char[][] b, int c, int[] d) {
		if ((a == 1) || (a == 2) || (a == 3) || (a == 4) || (a == 5) || (a == 6) || (a == 7) || (a == 8) || (a == 9))
			b[d[0]][d[1]] = ((c % 2) == 0) ? 'o' : 'x';
	}

	@Contract(pure = true)
	private static boolean isNotVictory(char[][] board) {
		requireNonNull(board);
		return (((board[0][0] != 'x') || (board[0][1] != 'x') || (board[0][2] != 'x')) && ((board[1][0] != 'x') || (board[1][1] != 'x') || (board[1][2] != 'x')) && ((board[2][0] != 'x') || (board[2][1] != 'x') || (board[2][2] != 'x'))) && (((board[0][0] != 'x') || (board[1][0] != 'x') || (board[2][0] != 'x')) && ((board[0][1] != 'x') || (board[1][1] != 'x') || (board[2][1] != 'x')) && ((board[0][2] != 'x') || (board[1][2] != 'x') || (board[2][2] != 'x'))) && (((board[0][0] != 'x') || (board[1][1] != 'x') || (board[2][2] != 'x')) && ((board[2][0] != 'x') || (board[1][1] != 'x') || (board[0][2] != 'x'))) && (((board[0][0] != 'o') || (board[0][1] != 'o') || (board[0][2] != 'o')) && ((board[1][0] != 'o') || (board[1][1] != 'o') || (board[1][2] != 'o')) && ((board[2][0] != 'o') || (board[2][1] != 'o') || (board[2][2] != 'o'))) && (((board[0][0] != 'o') || (board[1][0] != 'o') || (board[2][0] != 'o')) && ((board[0][1] != 'o') || (board[1][1] != 'o') || (board[2][1] != 'o')) && ((board[0][2] != 'o') || (board[1][2] != 'o') || (board[2][2] != 'o'))) && (((board[0][0] != 'o') || (board[1][1] != 'o') || (board[2][2] != 'o')) && ((board[2][0] != 'o') || (board[1][1] != 'o') || (board[0][2] != 'o')));
	}

	private static void victory(char[][] board) {
		requireNonNull(board);
		out.println(((((board[0][0] == 'x') && (board[0][1] == 'x') && (board[0][2] == 'x')) || ((board[1][0] == 'x') && (board[1][1] == 'x') && (board[1][2] == 'x')) || ((board[2][0] == 'x') && (board[2][1] == 'x') && (board[2][2] == 'x'))) || (((board[0][0] == 'x') && (board[1][0] == 'x') && (board[2][0] == 'x')) || ((board[0][1] == 'x') && (board[1][1] == 'x') && (board[2][1] == 'x')) || ((board[0][2] == 'x') && (board[1][2] == 'x') && (board[2][2] == 'x'))) || (((board[0][0] == 'x') && (board[1][1] == 'x') && (board[2][2] == 'x')) || ((board[2][0] == 'x') && (board[1][1] == 'x') && (board[0][2] == 'x')))) ? "The winner is Player One" : (((((board[0][0] == 'o') && (board[0][1] == 'o') && (board[0][2] == 'o')) || ((board[1][0] == 'o') && (board[1][1] == 'o') && (board[1][2] == 'o')) || ((board[2][0] == 'o') && (board[2][1] == 'o') && (board[2][2] == 'o'))) || (((board[0][0] == 'o') && (board[1][0] == 'o') && (board[2][0] == 'o')) || ((board[0][1] == 'o') && (board[1][1] == 'o') && (board[2][1] == 'o')) || ((board[0][2] == 'o') && (board[1][2] == 'o') && (board[2][2] == 'o'))) || (((board[0][0] == 'o') && (board[1][1] == 'o') && (board[2][2] == 'o')) || ((board[2][0] == 'o') && (board[1][1] == 'o') && (board[0][2] == 'o')))) ? "The winner is Player Two" : "There is no winner"));
	}

	@Contract(pure = true)
	private static boolean isNotValid(char[][] board, int placement) {
		if (placement == 1) return board[0][0] != ' ';
		else if (placement == 2) return board[0][1] != ' ';
		else if (placement == 3) return board[0][2] != ' ';
		else if (placement == 4) return board[1][0] != ' ';
		else if (placement == 5) return board[1][1] != ' ';
		else if (placement == 6) return board[1][2] != ' ';
		else if (placement == 7) return board[2][0] != ' ';
		else if (placement == 8) return board[2][1] != ' ';
		else return (placement != 9) || (board[2][2] != ' ');
	}

	static class TicTacToeBoard {
		private final JFrame f;
		private int[][] b;
		private char[][] p;

		@SuppressWarnings("SameParameterValue")
		TicTacToeBoard(int width, int height) {
			f = new JFrame("Tic-Tac-Toe");
			f.setIconImage(new ImageIcon("C:\\Users\\china.DESKTOP-ISAVF5I\\Programming\\Java_Development\\Classwork\\Computer_Science_in_the_21st_Century\\images\\arrays.twoDimensionalArrays\\ticTacToe\\Tic-tac-toe.png").getImage());
			f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			f.setSize(width, height);
		}

		void defineBoard(int[][] a) {
			b = a;
		}

		void delay() {
			try {
				sleep(1000);
			} catch (InterruptedException ignored) {
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
			final BufferedImage[] images = new BufferedImage[2];

			private DrawTicTacToeBoard(String a, String b) {
				setBackground(RED);
				try {
					for (int i = 0; i < 2; i++) images[i] = read(new File(new String[]{a, b}[i]));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			public void paintComponent(Graphics a) {
				super.paintComponent(a);
				for (int i = 0; i < 3; i++)
					for (int j = 0; j < 3; j++)
						if (p[i][j] == 'x') a.drawImage(images[0], j * 200 + 25, i * 200 + 25, null, null);
						else if (p[i][j] == 'o') a.drawImage(images[1], j * 200 + 25, i * 200 + 25, null, null);
				a.setColor(BLUE);
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
