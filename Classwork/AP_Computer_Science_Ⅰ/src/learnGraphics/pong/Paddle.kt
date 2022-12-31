package learnGraphics.pong

import java.awt.Graphics
import java.awt.event.InputEvent.CTRL_DOWN_MASK
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.*
import kotlin.system.exitProcess

internal class Paddle(private val game: Pong, y: Int) : Sprite((game.frame.width - 60) / 2, y, 0, 0, 60, 10) {
	fun pressed(e: KeyEvent) {
		if (e.keyCode == VK_LEFT) xA = -2 else if (e.keyCode == VK_RIGHT) xA =
			2 else if (e.keyCode == VK_W && e.modifiersEx == CTRL_DOWN_MASK) exitProcess(0)
	}

	fun released(e: KeyEvent) {
		if (e.keyCode == VK_LEFT || e.keyCode == VK_RIGHT) xA = 0
	}

	fun updatePosition() {
		if (((x + xA) >= 0) && ((x + xA) < (game.frame.width - width))) x += xA
	}

	fun paint(g: Graphics) = g.fillRect(x, y, width, height)
}
