package projects.mazeAttempts.maze2

import java.awt.Color
import java.awt.GradientPaint
import java.awt.Polygon

class Wall(
	var rows: IntArray,
	var cols: IntArray,
	var type: String,
	private var r: Int,
	private var g: Int,
	private var b: Int
) {
	private val dist = 50
	val paint: GradientPaint
		get() {
			var endR = r - dist
			var endG = g - dist
			var endB = b - dist
			if (r < 0) r = 0
			if (g < 0) g = 0
			if (b < 0) b = 0
			if (endR < 0) endR = 0
			if (endG < 0) endG = 0
			if (endB < 0) endB = 0
			return GradientPaint(
				cols[0].toFloat(),
				rows[0].toFloat(),
				Color(r, g, b),
				cols[0].toFloat(),
				rows[1].toFloat(),
				Color(endR, endG, endB)
			)
		}
	val polygon: Polygon get() = Polygon(cols, rows, 4)
}
