package objectOriented.gridExamples;

import java.util.Scanner;

import static java.lang.Math.*;
import static java.lang.System.in;
import static java.lang.System.out;

class GridDriver extends Thread {
	/**
	 * char value to be inputted into paintDiagonalToRight and paintDiagonalToLeft
	 */
	private static final char diagonalInput = (char) toIntExact(round(((random() * 94) + 33)));

	/**
	 * Scanner for inputting dimensions
	 */
	private static final Scanner input = new Scanner(in);
	/**
	 * {@link Grid}s to be used by the iteration sets
	 */
	private static final Grid[] x = new Grid[3];
	/**
	 * grid dimension values
	 */
	protected static int numRows, numCols;

	/**
	 * main data-flow processor for GridDriver
	 */
	public static void main(String[] args) throws InterruptedException {
		out.println("Note: if you want the removal, you need both dimensions to be the same.");
		sleep(1000);
		out.println();
		out.print("Day 1 or 2? ");
		var switchCondition = input.nextInt();
		out.println();
		out.print("Please enter the number of rows you want: ");
		numRows = input.nextInt();
		out.print("Please enter the number of columns you want: ");
		numCols = input.nextInt();
		out.println('\n');
		switch (switchCondition) {
			case 1:
				iteration1();
				out.println('\n');
				iteration2();
				break;
			case 2:
				if (numRows == numCols) {
					out.println('\n');
					clearGrid();
				}
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + switchCondition);
		}
	}

	/**
	 * First iteration; original code, no modifications
	 */
	private static void iteration1() {
		x[0] = new Grid();
		x[0].fill("Hello");
		x[0].print();
		out.println();
		x[0].paintDiagonalToRight(diagonalInput);
		x[0].print();
	}

	/**
	 * Second iteration; modified code, runs on a {@code char} array and runs the
	 * additional two methods
	 */
	private static void iteration2() {
		x[1] = new Grid(numRows, numCols);
		x[1].fill((char) toIntExact(round(((random() * 94) + 33))));
		x[1].print();
		out.println();
		x[1].paintDiagonalToRight(Character.toString(diagonalInput));
		x[1].print();
		out.println();
		x[1].paintDiagonalToLeft(diagonalInput);
		x[1].print();
		out.println();
		x[1].paintBorders((char) toIntExact(round(((random() * 94) + 33))));
		x[1].print();
	}

	private static void clearGrid() {
		x[2] = new Grid(numRows);
		x[2].fill((char) toIntExact(round(((random() * 94) + 33))));
		x[2].print();
		out.println();
		x[2].remove();
		x[2].remove();
	}
}
