package mazeAttempts.maze2

import java.awt.Color
import java.awt.Rectangle

class Explorer(var color: Color, var location: Location, var size: Int, var dir: Int) {
	fun move(keyCode: Int, maze: Array<Array<String>>, draw3D: Boolean): Int {
		//87 = W, 83 = S, 65 = A, 68 = D
		if (draw3D == false) {
			if (keyCode == 87 && !maze[location.y - 1][location.x].equals("#")) {
				location.y = location.y - 1
			} else if (keyCode == 65 && !maze[location.y][location.x - 1].equals("#")) {
				location.x = location.x - 1
			} else if (keyCode == 83 && !maze[location.y + 1][location.x].equals("#")) {
				location.y = location.y + 1
			} else if (keyCode == 68 && !maze[location.y][location.x + 1].equals("#")) {
				location.x = location.x + 1
			}
		} else {
			if (keyCode == 87) {
				try {
					location.run {
						when (dir) {
							0 -> if (!maze[y][x].equals("#")) x = x + 1
							1, -3 -> if (!maze[y + 1][x].equals("#")) y = y + 1
							2, -2 -> if (!maze[y][x - 1].equals("#")) x = x - 1
							3, -1 -> if (!maze[y - 1][x].equals("#")) y = y - 1
						}
					}
				} catch (e: ArrayIndexOutOfBoundsException) {
					e.printStackTrace()
				}
				/*when (dir) {
					0 -> location.x = location.x + 1
					1 -> location.y = location.y + 1
					2 -> location.x = location.x - 1
					3 -> location.y = location.y - 1
					-1 -> location.y = location.y - 1
					-2 -> location.x = location.x - 1
					-3 -> location.y = location.y + 1
				}*/
			} else if (keyCode == 65) {
				dir--
				if (dir == -4) {
					dir = 0
				}
			} else if (keyCode == 68) {
				dir++
				if (dir == 4) {
					dir = 0
				}
			}
		}
		return dir
	}

	val rectangle: Rectangle
		get() = Rectangle(location.x * size, location.y * size, size, size)

}