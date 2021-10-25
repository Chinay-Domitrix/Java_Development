package maze

import maze.Explorer.Direction.*
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
	constructor(location: Location, direction: Int, size: Int, color: Color) : this(
		location,
		(if (direction == 0) NORTH else if (direction == 1) WEST else if (direction == 2) EAST else if (direction == 3) SOUTH else null) as Direction,
		size,
		color
	)

	val rectangle: Rectangle
		get() = Rectangle(location.x, location.y, size, size)

	fun move(direction: Direction) {
		with(location) {
			when (direction) {
				NORTH -> y -= 1
				EAST -> x += 1
				SOUTH -> y += 1
				WEST -> x -= 1
			}
		}
	}

	enum class Direction {
		NORTH, EAST, SOUTH, WEST;
	}

	fun move(i: Int) {
		move((if (i == 0) NORTH else if (i == 1) WEST else if (i == 2) EAST else if (i == 3) SOUTH else null) as Direction)
	}
}
