package mazeAttempts.maze

import java.awt.Polygon

class Wall(var rows: IntArray, var columns: IntArray, var type: String) {
	val polygon: Polygon
		get() = Polygon(columns, rows, columns.size)

	companion object {
		const val shrink = 50
	}
}