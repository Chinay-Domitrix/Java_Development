package objectOriented.gridExamples;

import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.random;
import static java.lang.System.out;

class Grid {
	/**
	 * the actual grid
	 */
	private static String[][] grid;
	/**
	 * the dimensional fields for the grid
	 */
	private static int numRows, numCols;

	Grid() {
		this(GridDriver.numRows, GridDriver.numCols);
	}

	Grid(int bothDimensions) {
		this(bothDimensions, bothDimensions);
	}

	/**
	 * sets the grid to its dimensions
	 *
	 * @param numRows the vertical dimension
	 * @param numCols the horizontal dimension
	 */
	Grid(int numRows, int numCols) {
		Grid.numRows = numRows;
		Grid.numCols = numCols;
		grid = new String[numRows][numCols];
	}

	/**
	 * fills the grid
	 *
	 * @param s the given {@code String}
	 */
	void fill(String s) {
		for (var i = 0; i < numRows; i++) Arrays.fill(grid[i], s);
	}

	/**
	 * fills the grid
	 *
	 * @param s the given {@code char}
	 */
	void fill(char s) {
		fill(Character.toString(s));
	}

	/**
	 * prints the whole array
	 */
	void print() {
		for (String[] i : grid) {
			for (var j : i) out.print(j);
			out.println();
		}
	}

	/**
	 * prints a diagonal from the top left to the bottom right
	 *
	 * @param s the {@code String} to be printed
	 */
	void paintDiagonalToRight(String s) {
		if (numRows != numCols) return;
		for (var i = 0; i < numRows; i++) grid[i][i] = s;
	}

	/**
	 * prints a diagonal from the top left to the bottom right
	 *
	 * @param s the {@code char} to be printed
	 */
	void paintDiagonalToRight(char s) {
		paintDiagonalToRight(Character.toString(s));
	}

	/**
	 * prints a diagonal from the top right to the bottom left
	 *
	 * @param s the {@code String} to be printed
	 */
	private void paintDiagonalToLeft(String s) {
		if (numRows != numCols) return;
		var counter = 0;
		for (var i = numCols - 1; i >= 0; i--) {
			grid[counter][i] = s;
			counter++;
		}
	}

	/**
	 * prints a diagonal from the top right to the bottom left
	 *
	 * @param s the {@code char} to be printed
	 */
	void paintDiagonalToLeft(char s) {
		paintDiagonalToLeft(Character.toString(s));
	}

	/**
	 * replaces the borders of the grid with another value
	 *
	 * @param s the {@code String} to replace the border
	 */
	private void paintBorders(String s) {
		Arrays.fill(grid[0], s);
		Arrays.fill(grid[grid.length - 1], s);
		for (var i = 0; i < grid.length; i++) grid[i][0] = s;
		for (var i = 0; i < grid.length; i++) grid[i][grid[i].length - 1] = s;
	}

	/**
	 * replaces the borders of the grid with another value
	 *
	 * @param s the {@code char} to replace the border
	 */
	void paintBorders(char s) {
		paintBorders(Character.toString(s));
	}

	@Override
	public String toString() {
		var x = new StringBuilder();
		for (String[] i : grid) {
			for (var j : i) x.append(j);
			x.append('\n');
		}
		return x.toString().trim();
	}

	void remove() {
		if (numRows != numCols) return;
		String breaker = " ".repeat(max(0, numCols)).repeat(max(0, numRows));
		var counter = 0;
		do {
			grid[(int) (random() * numRows)][(int) (random() * numCols)] = " ";
			var x = new StringBuilder();
			for (String[] i : grid) {
				for (var j : i) x.append(j);
				x.append('\n');
			}
			var y = new StringBuilder();
			for (String[] i : grid) for (var j : i) y.append(j);
			x.deleteCharAt(x.length() - 1);
			String[] printed = x.toString().split("\n");
			if (!y.toString().equals(breaker))
				out.println(Arrays.toString(printed).replace("[", "").replace("]", "").replace(", ", "\n"));
			if (y.toString().equals(breaker)) break;
			counter++;
		} while (true);
		out.println("It took " + counter + " tries to clear the grid.");
	}
}