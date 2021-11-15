package mazeAttempts.maze4

import javafx.application.Platform.startup
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import java.awt.*
import java.awt.Color.*
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.*
import java.awt.event.KeyListener
import java.io.File
import java.io.IOException
import java.lang.Integer.valueOf
import java.lang.Thread.currentThread
import java.util.*
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants.EXIT_ON_CLOSE

fun Maze.getCeiling(n: Int) = Wall(
	intArrayOf(100 + n * shrink, 150 + n * shrink, 150 + n * shrink, 100 + n * shrink),
	intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink),
	"Ceil",
	255 - n * shrink,
	255 - n * shrink,
	255 - n * shrink
)

fun Maze.getFloor(n: Int) = Wall(
	intArrayOf(700 - n * shrink, 650 - n * shrink, 650 - n * shrink, 700 - n * shrink),
	intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink),
	"Floor",
	255 - n * shrink,
	255 - n * shrink,
	255 - n * shrink
)

fun Maze.getLeft(n: Int) = Wall(
	intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink),
	intArrayOf(100 + n * shrink, 150 + n * shrink, 150 + n * shrink, 100 + n * shrink),
	"Left",
	255 - n * shrink,
	255 - n * shrink,
	255 - n * shrink
)

fun Maze.getRight(n: Int) = Wall(
	intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink),
	intArrayOf(700 - n * shrink, 650 - n * shrink, 650 - n * shrink, 700 - n * shrink),
	"Right",
	255 - n * shrink,
	255 - n * shrink,
	255 - n * shrink
)

fun Maze.getFront(n: Int) = Wall(
	intArrayOf(100 + n * shrink, 100 + n * shrink, 700 - n * shrink, 700 - n * shrink),
	intArrayOf(100 + n * shrink, 700 - n * shrink, 700 - n * shrink, 100 + n * shrink),
	"Front",
	255 - n * shrink,
	255 - n * shrink,
	255 - n * shrink
)

fun Maze.getLeftPath(n: Int) = Wall(
	intArrayOf(100 + n * shrink, 100 + n * shrink, 700 - n * shrink, 700 - n * shrink),
	intArrayOf(50 + n * shrink, 100 + n * shrink, 100 + n * shrink, 50 + n * shrink),
	"LeftPath",
	255 - n * shrink,
	255 - n * shrink,
	255 - n * shrink
)

fun Maze.getLeftFloor(n: Int) = Wall(
	intArrayOf(700 - n * shrink, 650 - n * shrink, 650 - n * shrink),
	intArrayOf(100 + n * shrink, 100 + n * shrink, 150 + n * shrink),
	"LeftFloor",
	255 - n * shrink,
	255 - n * shrink,
	255 - n * shrink
)

fun Maze.getLeftCeil(n: Int) = Wall(
	intArrayOf(100 + n * shrink, 150 + n * shrink, 150 + n * shrink),
	intArrayOf(100 + n * shrink, 150 + n * shrink, 100 + n * shrink),
	"LeftCeil",
	255 - n * shrink,
	255 - n * shrink,
	255 - n * shrink
)

fun Maze.getRightPath(n: Int) = Wall(
	intArrayOf(100 + n * shrink, 100 + n * shrink, 700 - n * shrink, 700 - n * shrink),
	intArrayOf(700 - n * shrink, 750 - n * shrink, 750 - n * shrink, 700 - n * shrink),
	"RightPath",
	255 - n * shrink,
	255 - n * shrink,
	255 - n * shrink
)

fun Maze.getRightFloor(n: Int) = Wall(
	intArrayOf(700 - n * shrink, 650 - n * shrink, 650 - n * shrink),
	intArrayOf(700 - n * shrink, 700 - n * shrink, 650 - n * shrink),
	"RightFloor",
	255 - n * shrink,
	255 - n * shrink,
	255 - n * shrink
)

fun Maze.getRightCeil(n: Int) = Wall(
	intArrayOf(100 + n * shrink, 150 + n * shrink, 150 + n * shrink),
	intArrayOf(700 - n * shrink, 700 - n * shrink, 650 - n * shrink),
	"RightCeil",
	255 - n * shrink,
	255 - n * shrink,
	255 - n * shrink
)

fun main() {
	Maze()
}

fun Maze.createWalls() {
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

fun Maze.setBoard() {
	try {
		val input = Scanner(File("Classwork/dataStructures/src/mazeAttempts/Maze.txt"))
		var txt: String
		while (input.hasNextLine()) {
			input.nextLine().also { txt = it }
			list.add(txt)
			(0 until txt.length).forEach { maze[counterM][it] = txt[it] }
			counterM++
			(0 until txt.length).forEach { if (txt[it] == '1') explorerLocationIndex = valueOf(txt.indexOf('1')) }
		}
	} catch (e: IOException) {
		e.printStackTrace()
	}
}

var frame: JFrame? = null
var list = ArrayList<String>()
var walls2D = ArrayList<Location>()
var walls = ArrayList<Wall>()
var maze = Array(33) { CharArray(74) }
var draw3D = false
var explorerLocationIndex = 0
var counterM = 0
val location = Location(2, 0)
const val squareSize = 15
var explorer = Explorer(location, 1, squareSize, RED)
const val shrink = 50

class Maze : JPanel(), KeyListener {
	public override fun paintComponent(g: Graphics) {
		super.paintComponent(g)
		val g2 = g as Graphics2D
		if (!draw3D) {
			for (i in list.indices) for (j in 0 until list[i].length) {
				val store = list[i].split("")
				if (store[j].equals("#")) {
					g.setColor(DARK_GRAY)
					g.fillRect(j * squareSize, i * squareSize, squareSize, squareSize)
					walls2D.add(explorer.location)
				} else if (store[j].equals(" ")) {
					g.setColor(YELLOW)
					g.fillRect(j * squareSize, i * squareSize, squareSize, squareSize)
				} else if (store[j].equals("1")) {
					g.setColor(YELLOW)
					g.fillRect(j * squareSize, i * squareSize, squareSize, squareSize)
				} else if (store[j].equals("E")) {
					g.setColor(BLUE)
					g.fillRect(j * squareSize, i * squareSize, squareSize, squareSize)
				}
			}
			g.setColor(explorer.color)
			g.fillRect(explorer.location.c * squareSize, explorer.location.r * squareSize, squareSize, squareSize)
		} else walls.forEach {
			if (!it.type.equals("Front")) {
				g2.paint = it.paint
				g2.fillPolygon(it.polygon)
				g2.color = it.color
				g2.drawPolygon(it.polygon)
			} else if (it.type.equals("Front")) {
				g2.paint = it.color
				g2.fillPolygon(it.polygon)
				g2.color = it.color
				g2.drawPolygon(it.polygon)
			}
		}
	}

	override fun keyPressed(e: KeyEvent) {
		if (e.keyCode == VK_SPACE) draw3D = !draw3D
		if (draw3D) createWalls()
		if (e.keyCode == VK_LEFT || e.keyCode == VK_UP || e.keyCode == VK_RIGHT || e.keyCode == VK_DOWN)
			explorer.move(e.keyCode, list)
		if (explorer.location.equals(Location(30, 72))) println("You win!")
		repaint()
	}

	override fun keyReleased(e: KeyEvent) {}
	override fun keyTyped(e: KeyEvent) {}

	init {
		frame = JFrame().also {
			it.add(this)
			it.defaultCloseOperation = EXIT_ON_CLOSE
			it.setSize(1500, 800)
			it.isVisible = true
			it.addKeyListener(this)
		}
		setBoard()
		/*Add support for MPEG Layer 3 files through plugins*/

//		createRealizedPlayer(File("Classwork/dataStructures/src/mazeAttempts/Background-Music.mp3").toURI().toURL()).start()
		startup(currentThread())
		MediaPlayer(
			Media(
				File("Classwork/dataStructures/src/mazeAttempts/Background-Music.mp3").toURI().toString()
			)
		).play()
	}
}

class Explorer(var location: Location, var direction: Int, var size: Int, var color: Color) {
	val rect: Rectangle = Rectangle(size, size, location.c, location.r)
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

}

data class Location(var r: Int, var c: Int) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false
		other as Location
		if (r != other.r) return false
		if (c != other.c) return false
		return true
	}

	override fun hashCode() = 31 * r + c
}

class Wall(val rows: IntArray, val cols: IntArray, val type: String, var r: Int, var g: Int, var b: Int) {
	val dist = 50
	val polygon = Polygon(cols, rows, rows.size)
	val color = Color(r, g, b)
	val paint: GradientPaint
		get() {
			var endR = r - dist
			var endG = g - dist
			var endB = b - dist
			if (r < 0) r = 0
			if (g < 0) g = 0
			if (b < 0) b = 0
			if (endR < 0) endR = 0
			if (endG < 0) endG = 0
			if (endB < 0) endB = 0
			return if (type.indexOf("Left") >= 0 || type.indexOf("Right") >= 0) GradientPaint(
				cols[0].toFloat(),
				rows[0].toFloat(),
				Color(r, b, g),
				cols[1].toFloat(),
				rows[0].toFloat(),
				Color(endR, endG, endB)
			) else GradientPaint(
				cols[0].toFloat(),
				rows[0].toFloat(),
				Color(r, b, g),
				cols[0].toFloat(),
				rows[1].toFloat(),
				Color(endR, endG, endB)
			)
		}
}
