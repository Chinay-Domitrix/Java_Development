package anonymousClassesDemos

import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants.EXIT_ON_CLOSE

class Demo3 private constructor() : JPanel() {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			Demo3()
		}
	}

	init {
		val frame = JFrame()
		frame.add(this)
		frame.isVisible = true
		frame.setSize(800, 400)
		isFocusable = true
		addKeyListener(object : KeyListener {
			override fun keyTyped(e: KeyEvent) {
				println("3 " + e.keyChar)
			}

			override fun keyPressed(e: KeyEvent) {
				println("2 " + e.keyChar)
			}

			override fun keyReleased(e: KeyEvent) {
				println("1 " + e.keyChar)
			}
		})
		frame.defaultCloseOperation = EXIT_ON_CLOSE
	}
}