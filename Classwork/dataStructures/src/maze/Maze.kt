package maze

import maze.Explorer.Direction.*
import java.awt.Color.*
import java.awt.Graphics
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.*
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
	private lateinit var mazeArray: Array<CharArray>
	lateinit var explorer: Explorer
	override fun paintComponent(g: Graphics) {
		super.paintComponent(g)
		g.color = black
		g.fillRect(0, 0, 1600, 1600)
		val xpos = 0
		val ypos = 0
		try {
			Scanner(File("Classwork/dataStructures/src/maze/Maze.txt")).use { scanner ->
				val temp = ArrayList<CharArray>()
				while (scanner.hasNextLine()) temp += "#${scanner.nextLine()}#".toCharArray()
				mazeArray = temp.toArray(arrayOf())
				mazeArray.indices.forEach {
					(0 until mazeArray[it].size).forEach { j ->
						if (mazeArray[it][j] == '#') g.color = gray else if (mazeArray[it][j] == '1') {
							g.color = red
							explorer = Explorer(Location(it, j), EAST, 1, red)
						} else if (mazeArray[it][j] == 'E') g.color = green else g.color = black
						g.fillRect(j * 15, it * 15, 15, 15)
					}
				}
			}
		} catch (e: FileNotFoundException) {
			e.printStackTrace()
		} catch (e: NullPointerException) {
			e.printStackTrace()
		}
	}

	override fun keyPressed(e: KeyEvent) {
		with(explorer.location) {
			e.keyCode.run {
				if (isValidMove(this)) when (this) {
					VK_RIGHT -> {
						mazeArray[x][y] = ' '
						mazeArray[x + 1][y] = 'E'
						explorer.move(EAST)
					}
					VK_LEFT -> {
						mazeArray[x][y] = ' '
						mazeArray[x - 1][y] = 'E'
						explorer.move(WEST)
					}
					VK_UP -> {
						mazeArray[x][y] = ' '
						mazeArray[x][y - 1] = 'E'
						explorer.move(NORTH)
					}
					VK_DOWN -> {
						mazeArray[x][y] = ' '
						mazeArray[x][y + 1] = 'E'
						explorer.move(SOUTH)
					}
				}
			}
		}
	}

	override fun keyTyped(e: KeyEvent) {}
	override fun keyReleased(e: KeyEvent) {}
	override fun mouseClicked(e: MouseEvent) {}
	override fun mousePressed(e: MouseEvent) {}
	override fun mouseReleased(e: MouseEvent) {}
	override fun mouseEntered(e: MouseEvent) {}
	override fun mouseExited(e: MouseEvent) {}

	private final fun isValidMove(keyCode: Int) = with(explorer.location) {
		when (keyCode) {
			VK_RIGHT, VK_D -> mazeArray[x + 1][y]
			VK_LEFT, VK_A -> mazeArray[x - 1][y]
			VK_UP, VK_W -> mazeArray[x][y - 1]
			VK_DOWN, VK_S -> mazeArray[x][y + 1]
			else -> null
		}
	} != '#'

	companion object {
		@JvmStatic
		fun main(vararg args: String) {
			Maze()
		}
	}

	init {
		JFrame().apply {
			add(this@Maze)
			defaultCloseOperation = EXIT_ON_CLOSE
			setSize(1600, 1000)
			isVisible = true
			addKeyListener(this@Maze)
			addMouseListener(this@Maze)
		}
	}
}