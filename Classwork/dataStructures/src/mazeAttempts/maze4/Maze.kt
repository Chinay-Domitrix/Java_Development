package mazeAttempts.maze4

import java.awt.Color.*
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.VK_SPACE
import java.awt.event.KeyListener
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.lang.Integer.valueOf
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants.EXIT_ON_CLOSE

class Maze : JPanel(), KeyListener {
	var frame: JFrame
	var list = ArrayList<String>()
	var walls2D = ArrayList<Location>()
	var walls = ArrayList<Wall>()
	var maze = Array(33) { CharArray(74) }
	var draw3D = false
	var explorerLocationIndex = 0
	var shrink = 50
	var counterM = 0
	var location = Location(2, 0)
	var explorer = Explorer(location, 0, 13, RED)
	fun setBoard() {
		try {
			val input = BufferedReader(FileReader(File("Classwork/dataStructures/src/mazeAttempts/Maze.txt")))
			var txt: String
			while (input.readLine().also { txt = it } != null) {
				list.add(txt)
				(0 until txt.length).forEach { maze[counterM][it] = txt[it] }
				counterM++
				(0 until txt.length).forEach { if (txt[it] == '1') explorerLocationIndex = valueOf(txt.indexOf('1')) }
			}
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	public override fun paintComponent(g: Graphics) {
		super.paintComponent(g)
		val g2 = g as Graphics2D
		if (!draw3D) {
			for (i in list.indices) for (j in 0 until list[i].length) {
				val store = list[i].split("").toTypedArray()
				if (store[j].equals("#")) {
					g.setColor(DARK_GRAY)
					g.fillRect(j * 13, i * 13, 13, 13)
					walls2D.add(explorer.location)
				} else if (store[j].equals(" ")) {
					g.setColor(YELLOW)
					g.fillRect(j * 13, i * 13, 13, 13)
				} else if (store[j].equals("1")) {
					g.setColor(YELLOW)
					g.fillRect(j * 13, i * 13, 13, 13)
				} else if (store[j].equals("E")) {
					g.setColor(BLUE)
					g.fillRect(j * 13, i * 13, 13, 13)
				}
			}
			g.setColor(explorer.color)
			g.fillRect(explorer.location.c * 13, explorer.location.r * 13, 13, 13)
		} else {
			for (w in walls) if (!w.type.equals("Front")) {
				g2.paint = w.paint
				g2.fillPolygon(w.polygon)
				g2.color = w.color
				g2.drawPolygon(w.polygon)
			}
			for (w in walls) if (w.type.equals("Front")) {
				g2.paint = w.color
				g2.fillPolygon(w.polygon)
				g2.color = w.color
				g2.drawPolygon(w.polygon)
			}
		}
	}

	fun createWalls() {
		walls = ArrayList()
		walls.add(getFront(5))
		for (n in 0..4) with(walls) {
			add(getCeiling(n))
			add(getFloor(n))
			add(getLeftPath(n + 1))
			add(getLeftCeil(n))
			add(getLeftFloor(n))
			add(getRightPath(n + 1))
			add(getRightCeil(n))
			add(getRightFloor(n))
		}
		when (explorer.direction) {
			0 -> {
				var n = 0
				while (n < 5) {
					try {
						if (maze[explorer.location.r - (5 - n)][explorer.location.c] == '#') walls.add(getFront(5 - n))
						if (maze[explorer.location.r - n][explorer.location.c + 1] == '#') walls.add(getRight(n))
						if (maze[explorer.location.r - n][explorer.location.c - 1] == '#') walls.add(getLeft(n))
					} catch (e: ArrayIndexOutOfBoundsException) {
						e.printStackTrace()
					}
					n++
				}
			}
			1 -> {
				var n = 0
				while (n < 5) {
					try {
						if (maze[explorer.location.r][explorer.location.c + (5 - n)] == '#') walls.add(getFront(5 - n))
						if (maze[explorer.location.r + 1][explorer.location.c + n] == '#') walls.add(getRight(n))
						if (maze[explorer.location.r - 1][explorer.location.c + n] == '#') walls.add(getLeft(n))
					} catch (e: ArrayIndexOutOfBoundsException) {
						e.printStackTrace()
					}
					n++
				}
			}
			2 -> {
				var n = 0
				while (n < 5) {
					try {
						if (maze[explorer.location.r + (5 - n)][explorer.location.c] == '#') walls.add(getFront(5 - n))
						if (maze[explorer.location.r + n][explorer.location.c - 1] == '#') walls.add(getRight(n))
						if (maze[explorer.location.r + n][explorer.location.c + 1] == '#') walls.add(getLeft(n))
					} catch (e: ArrayIndexOutOfBoundsException) {
						e.printStackTrace()
					}
					n++
				}
			}
			3 -> {
				var n = 0
				while (n < 5) {
					try {
						if (maze[explorer.location.r][explorer.location.c - (5 - n)] == '#') walls.add(getFront(5 - n))
						if (maze[explorer.location.r - 1][explorer.location.c - n] == '#') walls.add(getRight(n))
						if (maze[explorer.location.r + 1][explorer.location.c + n] == '#') walls.add(getLeft(n))
					} catch (e: ArrayIndexOutOfBoundsException) {
						e.printStackTrace()
					}
					n++
				}
			}
		}
	}

	fun getCeiling(n: Int) = Wall(
		intArrayOf(100 + n * shrink, 150 + n * shrink, 150 + n * shrink, 100 + n * shrink),
		intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink),
		"Ceil",
		255 - n * shrink,
		255 - n * shrink,
		255 - n * shrink
	)

	fun getFloor(n: Int) = Wall(
		intArrayOf(700 - n * shrink, 650 - n * shrink, 650 - n * shrink, 700 - n * shrink),
		intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink),
		"Floor",
		255 - n * shrink,
		255 - n * shrink,
		255 - n * shrink
	)

	fun getLeft(n: Int) = Wall(
		intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink),
		intArrayOf(100 + n * shrink, 150 + n * shrink, 150 + n * shrink, 100 + n * shrink),
		"Left",
		255 - n * shrink,
		255 - n * shrink,
		255 - n * shrink
	)

	fun getRight(n: Int) = Wall(
		intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink),
		intArrayOf(700 - n * shrink, 650 - n * shrink, 650 - n * shrink, 700 - n * shrink),
		"Right",
		255 - n * shrink,
		255 - n * shrink,
		255 - n * shrink
	)

	fun getFront(n: Int) = Wall(
		intArrayOf(100 + n * shrink, 100 + n * shrink, 700 - n * shrink, 700 - n * shrink),
		intArrayOf(100 + n * shrink, 700 - n * shrink, 700 - n * shrink, 100 + n * shrink),
		"Front",
		255 - n * shrink,
		255 - n * shrink,
		255 - n * shrink
	)

	fun getLeftPath(n: Int) = Wall(
		intArrayOf(100 + n * shrink, 100 + n * shrink, 700 - n * shrink, 700 - n * shrink),
		intArrayOf(50 + n * shrink, 100 + n * shrink, 100 + n * shrink, 50 + n * shrink),
		"LeftPath",
		255 - n * shrink,
		255 - n * shrink,
		255 - n * shrink
	)

	fun getLeftFloor(n: Int) = Wall(
		intArrayOf(700 - n * shrink, 650 - n * shrink, 650 - n * shrink),
		intArrayOf(100 + n * shrink, 100 + n * shrink, 150 + n * shrink),
		"LeftFloor",
		255 - n * shrink,
		255 - n * shrink,
		255 - n * shrink
	)

	fun getLeftCeil(n: Int) = Wall(
		intArrayOf(100 + n * shrink, 150 + n * shrink, 150 + n * shrink),
		intArrayOf(100 + n * shrink, 150 + n * shrink, 100 + n * shrink),
		"LeftCeil",
		255 - n * shrink,
		255 - n * shrink,
		255 - n * shrink
	)

	fun getRightPath(n: Int) = Wall(
		intArrayOf(100 + n * shrink, 100 + n * shrink, 700 - n * shrink, 700 - n * shrink),
		intArrayOf(700 - n * shrink, 750 - n * shrink, 750 - n * shrink, 700 - n * shrink),
		"RightPath",
		255 - n * shrink,
		255 - n * shrink,
		255 - n * shrink
	)

	fun getRightFloor(n: Int) = Wall(
		intArrayOf(700 - n * shrink, 650 - n * shrink, 650 - n * shrink),
		intArrayOf(700 - n * shrink, 700 - n * shrink, 650 - n * shrink),
		"RightFloor",
		255 - n * shrink,
		255 - n * shrink,
		255 - n * shrink
	)

	fun getRightCeil(n: Int) = Wall(
		intArrayOf(100 + n * shrink, 150 + n * shrink, 150 + n * shrink),
		intArrayOf(700 - n * shrink, 700 - n * shrink, 650 - n * shrink),
		"RightCeil",
		255 - n * shrink,
		255 - n * shrink,
		255 - n * shrink
	)

	override fun keyPressed(e: KeyEvent) {
		if (e.keyCode == VK_SPACE) draw3D = !draw3D
		if (draw3D) createWalls()
		if (e.keyCode.equals(37) || e.keyCode.equals(38) || e.keyCode.equals(39) || e.keyCode.equals(40)) {
			explorer.move(e.keyCode, list)
			if (explorer.direction == 0) println("Direction: north")
			if (explorer.direction == 1) println("Direction: east")
			if (explorer.direction == 2) println("Direction: south")
			if (explorer.direction == 3) println("Direction: west")
		}
		if (explorer.location.equals(Location(30, 72))) println("You win!")
		repaint()
	}

	override fun keyReleased(e: KeyEvent) {}
	override fun keyTyped(e: KeyEvent) {}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			Maze()
		}
	}

	init {
		frame = JFrame().also {
			it.add(this)
			it.defaultCloseOperation = EXIT_ON_CLOSE
			it.setSize(1500, 800)
			it.isVisible = true
			it.addKeyListener(this)
		}
		setBoard()
	}
}