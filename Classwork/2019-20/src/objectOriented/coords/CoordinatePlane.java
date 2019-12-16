package objectOriented.coords;

import java.util.ArrayList;

import static java.lang.Math.*;
import static java.lang.System.out;

class CoordinatePlane {
	private static final int[] GRID_SIZES = {61, 41, 21};
	private final int numRows;
	private final int numCols;
	private final int xAxisIndex;
	private final int yAxisIndex;
	private final int xMin;
	private final int xMax;
	private final int yMin;
	private final int yMax;
	private final ArrayList<OrderedPair> points;
	private final String[][] displayGrid;

	/**
	 * Creates a {@code CoordinatePlane} object of size {@code size}
	 *
	 * @param size the size of both dimensions
	 */
	private CoordinatePlane(int size) {
		points = new ArrayList<>();
		numRows = size;
		numCols = size;
		xAxisIndex = numRows / 2;
		yAxisIndex = numCols / 2;
		xMin = (xAxisIndex - numRows) + 1;
		xMax = numCols - yAxisIndex - 1;
		yMin = (yAxisIndex - numRows) + 1;
		yMax = numCols - xAxisIndex - 1;
		displayGrid = new String[numRows][numRows];
		out.printf("Prepared grid with xMin = %d, xMax = %d, yMin = %d yMax = %d%n", xMin, xMax, yMin, yMax);

	}

	public static void main(String[] args) {
		var cp = new CoordinatePlane(CoordinatePlane.GRID_SIZES[2]);
		//cp.randomPoints(10, -10, 10);
//		Graph with 'x'
		//cp.graph("x");
//		Uncomment when ready
		//cp.linearGraph(1/2.0,-4);
		cp.linearGraph();
//		Graph with 'x'
		cp.graph("x");
	}

	/**
	 * The key method that displays a graph with x-axis, y-axis and ArrayList of points
	 *
	 * @param pointChar character used to graph points
	 */
	private void graph(String pointChar) {
		blankGrid();
		out.print("Graphing => ");
		for (var p : points) {
//			round to nearest int to graph
			var x = p.getXInt();
//			round to nearest int to graph
			var y = p.getYInt();
			out.printf("(%d,%d) ", x, y);
//			Only graph points in range of grid
			if ((x >= xMin) && (x <= xMax) && (y >= yMin) && (y <= yMax))
				displayGrid[yAxisIndex - y][xAxisIndex + x] = pointChar;
		}
		out.println();
//		prints new display grid
		print();
	}

	private void graph(char pointChar) {
		graph(Character.toString(pointChar));
	}

	/**
	 * Default graph uses * for points
	 */
	public void graph() {
		this.graph('*');
	}

	/**
	 * Fills with a number of random points in a specified range
	 *
	 * @param num the number of points
	 * @param min the minimum value for the x and y coordinates
	 * @param max the maximum value for the x and y coordinates
	 */
	public void randomPoints(int num, int min, int max) {
		for (int i = 0; i < num; i++) {
			var randX = toIntExact(round((random() * (max - min + 1)) + min));
			var randY = toIntExact(round((random() * (max - min + 1)) + min));
			points.add(new OrderedPair(randX, randY));
		}
	}

	/**
	 * Fills points in form of line with a given slope and y-intercept
	 */
	private void linearGraph() {
		//noinspection StatementWithEmptyBody
		for (var x = xMin; x <= xMax; x++) {
			/*  TASK 2 - Write the loop to get this working */

		}
	}

	/**
	 * Prints out all ordered pair points
	 */
	public String toString() {
		var s = new StringBuilder("[");
		for (var p : points) s.append(p.toString()).append(',');
		return s + "\b]";
	}

	/**
	 * Creates the x and y axis on a blank grid
	 */
	private void blankGrid() {
		fillAll();
		for (var i = 0; i < numCols; i++) displayGrid[xAxisIndex][i] = "-";
		for (var i = 0; i < numRows; i++) displayGrid[i][xAxisIndex] = "|";
	}

	/**
	 * Prints all contents of displayGrid
	 */
	private void print() {
		for (String[] i : displayGrid) {
			for (String j : i) out.printf("%s ", j);
			out.println();
		}
	}

	/**
	 * Fills all contents of displayGrid
	 */
	private void fillAll() {
		for (var i = 0; i < numRows; i++) for (var j = 0; j < numCols; j++) displayGrid[i][j] = " ";
	}
}