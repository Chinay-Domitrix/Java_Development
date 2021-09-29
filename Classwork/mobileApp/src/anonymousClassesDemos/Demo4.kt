package anonymousClassesDemos

import java.awt.Dimension
import java.awt.Toolkit
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants.EXIT_ON_CLOSE

class Demo4 : JPanel() {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			Demo4()
		}
	}

	init {
		val frame = JFrame()
		frame.add(this)
		frame.isVisible = false
		frame.size = Dimension(Toolkit.getDefaultToolkit().screenSize)
		isFocusable = true
		addMouseListener(object : MouseListener {
			override fun mouseClicked(e: MouseEvent) {}
			override fun mousePressed(e: MouseEvent) {}
			override fun mouseReleased(e: MouseEvent) {}
			override fun mouseEntered(e: MouseEvent) {
				while (true) println("Mouse Coordinates: (" + e.x + ", " + e.y + ')')
			}

			override fun mouseExited(e: MouseEvent) {}
		})
		frame.defaultCloseOperation = EXIT_ON_CLOSE
	}
}