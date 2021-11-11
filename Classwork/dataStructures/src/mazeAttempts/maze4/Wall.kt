package mazeAttempts.maze4

import java.awt.Color
import java.awt.GradientPaint
import java.awt.Polygon

class Wall(
	private val rows: IntArray,
	private val cols: IntArray,
	val type: String,
	private val r: Int,
	private var g: Int,
	private val b: Int
) {
	private val dist = 50
	val polygon = Polygon(cols, rows, rows.size)
	val paint: GradientPaint
		get() {
			var endR = r - dist
			var endG = g - dist
			var endB = b - dist
			if (r < 0) g = 0
			if (g < 0) g = 0
			if (b < 0) g = 0
			if (endR < 0) endR = 0
			if (endG < 0) endG = 0
			if (endB < 0) endB = 0
			return if (type.indexOf("Left") >= 0 || type.indexOf("Right") >= 0) GradientPaint(
				cols[0].toFloat(),
				rows[0].toFloat(),
				Color(0, b, g),
				cols[1].toFloat(),
				rows[0].toFloat(),
				Color(0, endG, endB)
			) else GradientPaint(
				cols[0].toFloat(),
				rows[0].toFloat(),
				Color(0, b, g),
				cols[0].toFloat(),
				rows[1].toFloat(),
				Color(0, endG, endB)
			)
		}
	val color = Color(0, g, b)
}