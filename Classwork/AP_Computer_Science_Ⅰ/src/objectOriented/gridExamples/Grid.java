package objectOriented.gridExamples;

import org.jetbrains.annotations.Contract;

import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Math.max;
import static java.lang.Math.random;
import static java.lang.System.out;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

class Grid {
	/**
	 * The grid to be printed
	 */
	private static String[][] grid;
	/**
	 * The width field for the grid
	 */
	private static int numRows;
	/**
	 * The height field for the grid
	 */
	private static int numCols;

	Grid() {
		this(GridDriver.numRows, GridDriver.numCols);
	}

	/**
	 * The constructor for generating a square grid
	 */
	Grid(int bothDimensions) {
		this(bothDimensions, bothDimensions);
	}

	/**
	 * Sets the grid to its dimensions
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
	 * Fills the grid
	 *
	 * @param s the given {@code String}
	 */
	void fill(String s) {
		for (var i = 0; i < numRows; i++)
			Arrays.fill(grid[i], s);
	}

	/**
	 * Fills the grid
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
		stream(grid).forEachOrdered(i -> {
			stream(i).forEachOrdered(out::print);
			out.println();
		});
	}

	/**
	 * prints a diagonal from the top left to the bottom right
	 *
	 * @param s the {@code String} to be printed
	 */
	void paintDiagonalToRight(String s) {
		if (numRows != numCols)
			return;
		range(0, numRows).forEachOrdered(i -> grid[i][i] = s);
	}

	/**
	 * prints a diagonal from the top left to the bottom right
	 *
	 * @param s the {@code char} to be printed
	 */
	void paintDiagonalToRight(@SuppressWarnings("SameParameterValue") char s) {
		paintDiagonalToRight(Character.toString(s));
	}

	/**
	 * prints a diagonal from the top right to the bottom left
	 *
	 * @param s the {@code String} to be printed
	 */
	private void paintDiagonalToLeft(String s) {
		if (numRows != numCols)
			return;
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
	void paintDiagonalToLeft(@SuppressWarnings("SameParameterValue") char s) {
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
		range(0, grid.length).forEachOrdered(i -> grid[i][0] = s);
		range(0, grid.length).forEachOrdered(i -> grid[i][grid[i].length - 1] = s);
	}

	/**
	 * replaces the borders of the grid with another value
	 *
	 * @param s the {@code char} to replace the border
	 */
	void paintBorders(char s) {
		paintBorders(Character.toString(s));
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 * <p>
	 * The {@code equals} method implements an equivalence relation on non-null
	 * object references:
	 * <ul>
	 * <li>It is <i>reflexive</i>: for any non-null reference value {@code x},
	 * {@code x.equals(x)} should return {@code true}.
	 * <li>It is <i>symmetric</i>: for any non-null reference values {@code x} and
	 * {@code y}, {@code x.equals(y)} should return {@code true} if and only if
	 * {@code y.equals(x)} returns {@code true}.
	 * <li>It is <i>transitive</i>: for any non-null reference values {@code x},
	 * {@code y}, and {@code z}, if {@code x.equals(y)} returns {@code true} and
	 * {@code y.equals(z)} returns {@code true}, then {@code x.equals(z)} should
	 * return {@code true}.
	 * <li>It is <i>consistent</i>: for any non-null reference values {@code x} and
	 * {@code y}, multiple invocations of {@code x.equals(y)} consistently return
	 * {@code true} or consistently return {@code false}, provided no information
	 * used in {@code equals} comparisons on the objects is modified.
	 * <li>For any non-null reference value {@code x}, {@code x.equals(null)} should
	 * return {@code false}.
	 * </ul>
	 * <p>
	 * The {@code equals} method for class {@code Object} implements the most
	 * discriminating possible equivalence relation on objects; that is, for any
	 * non-null reference values {@code x} and {@code y}, this method returns
	 * {@code true} if and only if {@code x} and {@code y} refer to the same object
	 * ({@code x == y} has the value {@code true}).
	 * <p>
	 * Note that it is generally necessary to override the {@code hashCode} method
	 * whenever this method is overridden, so as to maintain the general contract
	 * for the {@code hashCode} method, which states that equal objects must have
	 * equal hash codes.
	 *
	 * @param obj the reference object with which to compare.
	 * @return {@code true} if this object is the same as the obj argument;
	 * {@code false} otherwise.
	 * @see #hashCode()
	 * @see HashMap
	 */
	@Contract(value = "null -> false", pure = true)
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	void remove() {
		if (numRows != numCols)
			return;
		var breaker = " ".repeat(max(0, numCols)).repeat(max(0, numRows));
		var counter = 0;
		do {
			grid[(int) (random() * numRows)][(int) (random() * numCols)] = " ";
			var x = new StringBuilder();
			stream(grid).forEachOrdered(i -> {
				stream(i).forEachOrdered(x::append);
				x.append('\n');
			});
			String y = stream(grid).flatMap(Arrays::stream).collect(joining());
			x.deleteCharAt(x.length() - 1);
			var printed = x.toString().split("\n");
			if (!y.equals(breaker))
				out.println(Arrays.toString(printed).replace("[", "").replace("]", "").replace(", ", "\n"));
			if (y.equals(breaker))
				break;
			counter++;
		} while (true);
		out.println("It took " + counter + " tries to clear the grid.");
	}
}
