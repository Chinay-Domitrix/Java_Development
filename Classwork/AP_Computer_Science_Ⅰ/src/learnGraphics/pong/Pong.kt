package learnGraphics.pong

import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.WindowConstants.EXIT_ON_CLOSE

class Pong : Thread() {
	val frame = JFrame("Pong")

	override fun run() {
		frame.size = dimension
		frame.isResizable = false
		frame.defaultCloseOperation = EXIT_ON_CLOSE
		frame.add(PongPanel(this))
		frame.isVisible = true
	}

	companion object {
		private val dimension = Dimension(900, 500)
	}
}
fun main() {
	Pong().start()
}
