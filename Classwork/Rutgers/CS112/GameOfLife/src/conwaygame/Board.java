package conwaygame;
/*
 * Class holding various methods of the grid for when the board is being interacted within the driver.
 */

import java.awt.*;

import static conwaygame.StdDraw.*;
import static java.lang.Math.round;
import static java.util.stream.DoubleStream.iterate;

public class Board extends Rectangle {
	public int rows;
	public int cols;
	public boolean[][] board;
	public double incX;
	public double incY;
	public double lowerX;
	public double upperX;
	public double lowerY;
	public double upperY;

	public Board(int x, int y, int halfWidth, int halfHeight, int rows, int cols, boolean filled, boolean[][] board) {
		super(x, y, halfWidth, halfHeight, filled);
		this.rows = rows;
		this.cols = cols;
		this.board = board;
		calculateBounds();
	}

	public void calculateBounds() {
		incX = (double) 2 * halfWidth / cols;
		incY = (double) 2 * halfHeight / rows;
		lowerX = (x - halfWidth) + (incX / 2);
		upperX = (x + halfWidth) - (incX / 2);
		lowerY = (y - halfHeight) + (incY / 2);
		upperY = (y + halfHeight) - (incY / 2);
	}

	public double[] getCellCM(double pX, double pY) {
		double curCol = lowerX - (incX / 2);
		while (curCol < pX) curCol += incX;
		double curRow = upperY + (incY / 2);
		while (curRow > pY) curRow -= incY;
		return new double[]{curRow + (incY / 2), curCol - (incX / 2)};
	}

	public void fillCell(double pX, double pY, Color c) {
		Color prev = getPenColor();
		setPenColor(c);
		filledRectangle(pX, pY, incX / 2, incY / 2);
		setPenColor(prev);
		drawAxes();
	}

	public void drawGrid() {
		Color c = getPenColor();
		setPenColor(GRAY);
		// + incX/2 bc floating point imprecision
		for (double cellCol = lowerX; cellCol <= upperX + incX / 2; cellCol += incX)
			for (double cellRow = upperY; cellRow >= lowerY - incY / 2; cellRow -= incY)
				if (board[(int) round((upperY - cellRow) / incY)][(int) round((cellCol - lowerX) / incX)])
					filledRectangle(cellCol, cellRow, incX / 2, incY / 2);
		setPenColor(c);
	}

	public void drawAxes() {
		iterate(lowerX - incX / 2, horiz -> horiz <= upperX + incX / 2, horiz -> horiz + incX).forEach(horiz -> line(horiz, lowerY - (incY / 2), horiz, upperY + (incY / 2)));
		iterate(lowerY - incY / 2, vert -> vert <= upperY + incY, vert -> vert + incY).forEach(vert -> line(lowerX - incX / 2, vert, upperX + incX / 2, vert));
	}

	public void draw() {
		calculateBounds();
		drawGrid();
		super.draw();
		drawAxes();
	}
}
