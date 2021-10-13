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
	var direction: Direction,
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

	enum class Direction {
		NORTH, EAST, SOUTH, WEST
	}
}