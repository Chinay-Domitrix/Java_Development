package maze

import maze.Explorer.Direction.EAST
import java.awt.Color.*
import java.awt.Graphics
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.io.File
import java.io.FileNotFoundException
import java.util.*
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants.EXIT_ON_CLOSE

class Maze private constructor() : JPanel(), KeyListener, MouseListener {
	private lateinit var maze: Array<CharArray>
	var explorer: Explorer? = null
	override fun paintComponent(g: Graphics) {
		super.paintComponent(g)
		g.color = black
		g.fillRect(0, 0, 1600, 1600)
		val xpos = 0
		val ypos = 0
		try {
			Scanner(File("Classwork/dataStructures/src/maze/Maze.txt")).use { scanner ->
				val temp = ArrayList<CharArray>()
				while (scanner.hasNextLine()) temp.add(scanner.nextLine().toCharArray())
				maze = temp.toArray(arrayOf())
				maze.indices.forEach { i ->
					(0 until maze[i].size).forEach { j ->
						if (maze[i][j] == '#') g.color = gray else if (maze[i][j] == '1') {
							g.color = red
							explorer = Explorer(Location(i, j), EAST, 1, red)
						} else if (maze[i][j] == 'E') g.color = green else g.color = black
						g.fillRect(j * 15, i * 15, 15, 15)
					}
				}
			}
		} catch (e: FileNotFoundException) {
			e.printStackTrace()
		} catch (e: NullPointerException) {
			e.printStackTrace()
		}
	}

	override fun keyTyped(e: KeyEvent) {}
	override fun keyPressed(e: KeyEvent) {}
	override fun keyReleased(e: KeyEvent) {}
	override fun mouseClicked(e: MouseEvent) {}
	override fun mousePressed(e: MouseEvent) {}
	override fun mouseReleased(e: MouseEvent) {}
	override fun mouseEntered(e: MouseEvent) {}
	override fun mouseExited(e: MouseEvent) {}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			Maze()
		}
	}

	init {
		val frame = JFrame()
		frame.add(this)
		frame.defaultCloseOperation = EXIT_ON_CLOSE
		frame.setSize(1600, 1000)
		frame.isVisible = true
		frame.addKeyListener(this)
		frame.addMouseListener(this)
	}
}