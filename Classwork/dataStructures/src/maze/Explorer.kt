package maze

import java.awt.Color
import java.awt.Rectangle

class Explorer(
	/**
	 * The `Explorer`'s location
	 */
	var location: Location,
	/**
	 * The `Explorer`'s direction, represented by an `int`
	 */
	var direction: Int,
	/**
	 * The `Explorer`'s size
	 */
	val size: Int,
	/**
	 * The `Explorer`'s color
	 */
	val color: Color
) {

	val rectangle: Rectangle
		get() = Rectangle(location.x, location.y, size, size)

	companion object {
		const val DIRECTION_NORTH = 0
		const val DIRECTION_EAST = 1
		const val DIRECTION_SOUTH = 2
		const val DIRECTION_WEST = 3
	}
}