package conwaygame;

import java.util.ArrayList;

/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many iterations/generations.
 * <p>
 * Rules
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.
 *
 * @author Seth Kelley
 * @author Maxwell Goldberg
 */
public class GameOfLife {
	// Instance variables
	private static final boolean ALIVE = true, DEAD = false;
	private boolean[][] grid;    // The board has the current generation of cells
	private int totalAliveCells; // Total number of alive cells in the grid (board)

	/**
	 * Default Constructor which creates a small 5x5 grid with five alive cells.
	 * This variation does not exceed bounds and dies off after four iterations.
	 */
	public GameOfLife() {
		grid = new boolean[5][5];
		totalAliveCells = 5;
		grid[1][1] = ALIVE;
		grid[1][3] = ALIVE;
		grid[2][2] = ALIVE;
		grid[3][2] = ALIVE;
		grid[3][3] = ALIVE;
	}

	/**
	 * Constructor used that will take in values to create a grid with a given number
	 * of alive cells
	 *
	 * @param file the input file with the initial game pattern formatted as follows: <ul><li>An integer representing the number of grid rows, say r</li><li>An integer representing the number of grid columns, say c</li><li>Number of r lines, each containing c true or false values (true denotes an ALIVE cell)</li></ul>
	 */
	public GameOfLife(String file) {
		StdIn.setFile(file);
		grid = new boolean[StdIn.readInt()][StdIn.readInt()];
		var caught = false;
		var nextToken = StdIn.readString();
		try {
			Integer.parseInt(nextToken);
		} catch (NumberFormatException ignored) {
			caught = true;
			grid[0][0] = Boolean.parseBoolean(nextToken);
		}
		for (int i = 0; i < grid.length; i++)
			for (int j = (caught && (i == 0)) ? 1 : 0; j < grid[i].length; j++) {
				grid[i][j] = StdIn.readBoolean();
				if (grid[i][j]) totalAliveCells++;
			}
	}

	/**
	 * Returns grid
	 *
	 * @return a two-dimensional boolean array for current grid
	 */
	public boolean[][] getGrid() {
		return grid;
	}

	/**
	 * Returns totalAliveCells
	 *
	 * @return int for total number of alive cells in grid
	 */
	public int getTotalAliveCells() {
		return totalAliveCells;
	}

	/**
	 * Returns the status of the cell at (row,col): ALIVE or DEAD
	 *
	 * @param row row position of the cell
	 * @param col column position of the cell
	 * @return true or false value "ALIVE" or "DEAD" (state of the cell)
	 */
	public boolean getCellState(int row, int col) {
		return grid[row][col];
	}

	/**
	 * Returns true if there are any alive cells in the grid
	 *
	 * @return true if there is at least one cell alive, otherwise returns false
	 */
	public boolean isAlive() {
		return totalAliveCells > 0;
	}

	/**
	 * Determines the number of alive cells around a given cell.
	 * Each cell has 8 neighbor cells which are the cells that are
	 * horizontally, vertically, or diagonally adjacent.
	 *
	 * @param col column position of the cell
	 * @param row row position of the cell
	 * @return neighboringCells, the number of alive cells (at most 8).
	 */
	public int numOfAliveNeighbors(int row, int col) {
		if (!isAlive()) return 0;
		var neighboringCells = 0;
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++)
				if (grid[((row + i) + grid.length) % grid.length][((col + j) + grid[row].length) % grid[row].length])
					neighboringCells++;
		if (grid[row][col]) neighboringCells--;
		return neighboringCells;
	}

	/**
	 * Creates a new grid with the next generation of the current grid using the rules for Conway's Game of Life.
	 *
	 * @return boolean[][] of new grid (this is a new 2D array)
	 */
	public boolean[][] computeNewGrid() {
		var newGrid = new boolean[grid.length][grid[0].length];
		for (var i = 0; i < grid.length; i++)
			for (var j = 0; j < grid[i].length; j++) {
				var neighbors = numOfAliveNeighbors(i, j);
				newGrid[i][j] = grid[i][j] ? (((neighbors < 2) || (neighbors > 3)) ? DEAD : ALIVE) : ((neighbors == 3) ? ALIVE : DEAD);
			}
		return newGrid;
	}

	/**
	 * Updates the current grid (the grid instance variable) with the grid denoting
	 * the next generation of cells computed by computeNewGrid().
	 * <p>
	 * Updates totalAliveCells instance variable
	 */
	public void nextGeneration() {
		grid = computeNewGrid();
		totalAliveCells = 0;
		for (var row : grid) for (var cell : row) if (cell) totalAliveCells++;
	}

	/**
	 * Updates the current grid with the grid computed after multiple (n) generations.
	 *
	 * @param n number of iterations that the grid will go through to compute a new grid
	 */
	public void nextGeneration(int n) {
		for (var i = 0; i < n; i++) nextGeneration();
	}

	/**
	 * Determines the number of separate cell communities in the grid
	 *
	 * @return the number of communities in the grid, communities can be formed from edges
	 */
	public int numOfCommunities() {
		var uf = new WeightedQuickUnionUF(grid.length, grid[0].length);
		for (var i = 0; i < grid.length; i++)
			for (var j = 0; j < grid[i].length; j++)
				if (grid[i][j]) {
					if (grid[((i + 1) + grid.length) % grid.length][j])
						uf.union(i, j, ((i + 1) + grid.length) % grid.length, j);
					if (grid[((i - 1) + grid.length) % grid.length][j])
						uf.union(i, j, ((i - 1) + grid.length) % grid.length, j);
					if (grid[i][((j + 1) + grid[i].length) % grid[i].length])
						uf.union(i, j, i, ((j + 1) + grid[i].length) % grid[i].length);
					if (grid[i][((j - 1) + grid[i].length) % grid[i].length])
						uf.union(i, j, i, ((j - 1) + grid[i].length) % grid[i].length);
					if (grid[((i + 1) + grid.length) % grid.length][((j + 1) + grid[i].length) % grid[i].length])
						uf.union(i, j, ((i + 1) + grid.length) % grid.length, ((j + 1) + grid[i].length) % grid[i].length);
					if (grid[((i + 1) + grid.length) % grid.length][((j - 1) + grid[i].length) % grid[i].length])
						uf.union(i, j, ((i + 1) + grid.length) % grid.length, ((j - 1) + grid[i].length) % grid[i].length);
					if (grid[((i - 1) + grid.length) % grid.length][((j + 1) + grid[i].length) % grid[i].length])
						uf.union(i, j, ((i - 1) + grid.length) % grid.length, ((j + 1) + grid[i].length) % grid[i].length);
					if (grid[((i - 1) + grid.length) % grid.length][((j - 1) + grid[i].length) % grid[i].length])
						uf.union(i, j, ((i - 1) + grid.length) % grid.length, ((j - 1) + grid[i].length) % grid[i].length);
				}
		var roots = new ArrayList<Integer>();
		for (var i = 0; i < grid.length; i++)
			for (var j = 0; j < grid[i].length; j++)
				if (grid[i][j] && !roots.contains(uf.find(i, j))) roots.add(uf.find(i, j));
		return roots.size();
	}
}
