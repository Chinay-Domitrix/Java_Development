package mazeAttempts.maze4

import java.awt.Color
import java.awt.Rectangle
import java.awt.event.KeyEvent.*

class Explorer(var location: Location, var direction: Int, var size: Int, var color: Color) {
	fun move(key: Int, list: ArrayList<String>) {
		if (key == VK_LEFT) if (direction == 0) direction = 3 else direction -= 1
		if (key == VK_RIGHT) if (direction == 3) direction = 0 else direction += 1
		if (key == VK_UP) when (direction) {
			0 -> if (list[location.r - 1][location.c] != '#') location.r = location.r - 1
			1 -> if (list[location.r][location.c + 1] != '#') location.c = location.c + 1
			2 -> if (list[location.r + 1][location.c] != '#') location.r = location.r + 1
			3 -> if (list[location.r][location.c - 1] != '#') location.c = location.c - 1
		}
	}

	val rect = Rectangle(13, 13, location.c, location.r)
}