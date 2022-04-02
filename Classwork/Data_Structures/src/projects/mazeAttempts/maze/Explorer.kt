package projects.mazeAttempts.maze

import java.awt.Color
import java.awt.Rectangle


class Explorer(var color: Color, var location: Location, var size: Int, var dir: Int) {
	fun move(keyCode: Int, maze: Array<Array<String?>>, draw3D: Boolean): Int {
		if (draw3D == false) {
			if (keyCode == 87 && maze[location.y - 1][location.x] != "#") location.y =
				location.y - 1 else if (keyCode == 65 && maze[location.y][location.x - 1] != "#") {
				location.x = location.x - 1
				dir--
				if (dir == -4) dir = 0
			} else if (keyCode == 83 && maze[location.y + 1][location.x] != "#") location.y =
				(location.y + 1) else if (keyCode == 68 && maze[location.y][location.x + 1] != "#") {
				location.x = (location.x + 1)
				dir++
				if (dir == 4) dir = 0
			}
		} else if (keyCode == 87) when (dir) {
			0 -> location.x = location.x + 1
			1 -> location.y = location.y + 1
			2 -> location.x = location.x - 1
			3 -> location.y = location.y - 1
			-1 -> location.y = location.y - 1
			-2 -> location.x = location.x - 1
			-3 -> location.y = location.y + 1
		} else if (keyCode == 65) {
			dir--
			if (dir == -4) dir = 0
		} else if (keyCode == 68) {
			dir++
			if (dir == 4) dir = 0
		}
		return dir
	}

	val rectangle: Rectangle get() = Rectangle(location.x * size, location.y * size, size, size)
}
/*
import mazeAttempts.maze.Explorer.Direction.*
import java.awt.Color
import java.awt.Rectangle

class Explorer(
	*/
/**
 * The `Explorer`'s location
 *//*

	var location: Location,
	*/
/**
 * The `Explorer`'s direction, represented by an `int`
 *//*

	var direction: Direction,
	*/
/**
 * The `Explorer`'s size
 *//*

	val size: Int,
	*/
/**
 * The `Explorer`'s color
 *//*

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
*/
