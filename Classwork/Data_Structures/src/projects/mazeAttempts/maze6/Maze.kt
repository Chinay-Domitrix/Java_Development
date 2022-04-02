/*
package mazeAttempts.maze6

*/
/*import javafx.application.Platform.*
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer*//*

import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel
import java.awt.event.KeyListener
import java.awt.event.MouseListener
import javax.swing.JFrame
import java.io.File
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import java.lang.ArrayIndexOutOfBoundsException
import java.lang.Thread.*
import java.util.ArrayList
import kotlin.jvm.JvmStatic
import javax.swing.WindowConstants.*

class Maze1 : JPanel(), KeyListener, MouseListener {
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
	var explorer = Explorer(location, 0, 13, Color.WHITE)
	fun setBoard() {
		val name = File("Classwork/dataStructures/src/mazeAttempts/Maze.txt")
		try {
			val input = BufferedReader(FileReader(name))
			var txt: String
			while (input.readLine().also { txt = it } != null) {
				list.add(txt)
				for (i in 0 until txt.length) {
					maze[counterM][i] = txt[i]
				}
				counterM++
				for (i in 0 until txt.length) {
					if (txt[i] == '1') {
						explorerLocationIndex = Integer.valueOf(txt.indexOf('1'))
					}
				}
			}
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	public override fun paintComponent(g: Graphics) {
		super.paintComponent(g)
		val g2 = g as Graphics2D
		if (!draw3D) {
			for (i in list.indices) {
				for (j in 0 until list[i].length) {
					val store = list[i].split("").toTypedArray()
					if (store[j] == "#") {
						g.setColor(Color.BLACK)
						g.fillRect(j * 13, i * 13, 13, 13)
						walls2D.add(explorer.location)
					} else if (store[j] == " ") {
						g.setColor(Color.GREEN)
						g.fillRect(j * 13, i * 13, 13, 13)
					} else if (store[j] == "1") {
						g.setColor(Color.GREEN)
						g.fillRect(j * 13, i * 13, 13, 13)
					} else if (store[j] == "E") {
						g.setColor(Color(255, 215, 0))
						g.fillRect(j * 13, i * 13, 13, 13)
					}
				}
			}
			g.setColor(explorer.color)
			g.fillRect(explorer.location.c * 13, explorer.location.r * 13, 13, 13)
		} else {
			for (w in walls) {
				if (w.type != "Front") {
					g2.paint = w.paint
					g2.fillPolygon(w.polygon)
					g2.color = w.color
					g2.drawPolygon(w.polygon)
				}
			}
			for (w in walls) {
				if (w.type == "Front") {
					g2.paint = w.color
					g2.fillPolygon(w.polygon)
					g2.color = w.color
					g2.drawPolygon(w.polygon)
				}
			}
		}
	}

	fun createWalls() {
		walls = ArrayList()
		walls.add(getFront(5))
		for (n in 0..4) {
			walls.add(getCeiling(n))
			walls.add(getFloor(n))
			walls.add(getLeftPath(n + 1))
			walls.add(getLeftCeil(n))
			walls.add(getLeftFloor(n))
			walls.add(getRightPath(n + 1))
			walls.add(getRightCeil(n))
			walls.add(getRightFloor(n))
		}
		val explorerR = explorer.location.r
		val explorerC = explorer.location.c
		val explorerDirection = explorer.direction
		when (explorerDirection) {
			0 -> {
				var n = 0
				while (n < 5) {
					try {
						if (maze[explorerR - (5 - n)][explorerC] == '#') {
							walls.add(getFront(5 - n))
						}
						if (maze[explorerR - n][explorerC + 1] == '#') {
							walls.add(getRight(n))
						}
						if (maze[explorerR - n][explorerC - 1] == '#') {
							walls.add(getLeft(n))
						}
					} catch (e: ArrayIndexOutOfBoundsException) {
					}
					n++
				}
			}
			1 -> {
				var n = 0
				while (n < 5) {
					try {
						if (maze[explorerR][explorerC + (5 - n)] == '#') {
							walls.add(getFront(5 - n))
						}
						if (maze[explorerR + 1][explorerC + n] == '#') {
							walls.add(getRight(n))
						}
						if (maze[explorerR - 1][explorerC + n] == '#') {
							walls.add(getLeft(n))
						}
					} catch (e: ArrayIndexOutOfBoundsException) {
					}
					n++
				}
			}
			2 -> {
				var n = 0
				while (n < 5) {
					try {
						if (maze[explorerR + (5 - n)][explorerC] == '#') {
							walls.add(getFront(5 - n))
						}
						if (maze[explorerR + n][explorerC - 1] == '#') {
							walls.add(getRight(n))
						}
						if (maze[explorerR + n][explorerC + 1] == '#') {
							walls.add(getLeft(n))
						}
					} catch (e: ArrayIndexOutOfBoundsException) {
					}
					n++
				}
			}
			3 -> {
				var n = 0
				while (n < 5) {
					try {
						if (maze[explorerR][explorerC - (5 - n)] == '#') {
							walls.add(getFront(5 - n))
						}
						if (maze[explorerR - 1][explorerC - n] == '#') {
							walls.add(getRight(n))
						}
						if (maze[explorerR + 1][explorerC + n] == '#') {
							walls.add(getLeft(n))
						}
					} catch (e: ArrayIndexOutOfBoundsException) {
					}
					n++
				}
			}
		}
	}

	fun getCeiling(n: Int): Wall {
		val rLocs = intArrayOf(100 + n * shrink, 150 + n * shrink, 150 + n * shrink, 100 + n * shrink)
		val cLocs = intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink)
		return Wall(rLocs, cLocs, "Ceil", 255 - n * shrink, 255 - n * shrink, 255 - n * shrink)
	}

	fun getFloor(n: Int): Wall {
		val rLocs = intArrayOf(700 - n * shrink, 650 - n * shrink, 650 - n * shrink, 700 - n * shrink)
		val cLocs = intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink)
		return Wall(rLocs, cLocs, "Floor", 255 - n * shrink, 255 - n * shrink, 255 - n * shrink)
	}

	fun getLeft(n: Int): Wall {
		val rLocs = intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink)
		val cLocs = intArrayOf(100 + n * shrink, 150 + n * shrink, 150 + n * shrink, 100 + n * shrink)
		return Wall(rLocs, cLocs, "Left", 255 - n * shrink, 255 - n * shrink, 255 - n * shrink)
	}

	fun getRight(n: Int): Wall {
		val rLocs = intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink)
		val cLocs = intArrayOf(700 - n * shrink, 650 - n * shrink, 650 - n * shrink, 700 - n * shrink)
		return Wall(rLocs, cLocs, "Right", 255 - n * shrink, 255 - n * shrink, 255 - n * shrink)
	}

	fun getFront(n: Int): Wall {
		val rLocs = intArrayOf(100 + n * shrink, 100 + n * shrink, 700 - n * shrink, 700 - n * shrink)
		val cLocs = intArrayOf(100 + n * shrink, 700 - n * shrink, 700 - n * shrink, 100 + n * shrink)
		return Wall(rLocs, cLocs, "Front", 255 - n * shrink, 255 - n * shrink, 255 - n * shrink)
	}

	fun getLeftPath(n: Int): Wall {
		val rLocs = intArrayOf(100 + n * shrink, 100 + n * shrink, 700 - n * shrink, 700 - n * shrink)
		val cLocs = intArrayOf(50 + n * shrink, 100 + n * shrink, 100 + n * shrink, 50 + n * shrink)
		return Wall(rLocs, cLocs, "LeftPath", 255 - n * shrink, 255 - n * shrink, 255 - n * shrink)
	}

	fun getLeftFloor(n: Int): Wall {
		val rLocs = intArrayOf(700 - n * shrink, 650 - n * shrink, 650 - n * shrink)
		val cLocs = intArrayOf(100 + n * shrink, 100 + n * shrink, 150 + n * shrink)
		return Wall(rLocs, cLocs, "LeftFloor", 255 - n * shrink, 255 - n * shrink, 255 - n * shrink)
	}

	fun getLeftCeil(n: Int): Wall {
		val rLocs = intArrayOf(100 + n * shrink, 150 + n * shrink, 150 + n * shrink)
		val cLocs = intArrayOf(100 + n * shrink, 150 + n * shrink, 100 + n * shrink)
		return Wall(rLocs, cLocs, "LeftCeil", 255 - n * shrink, 255 - n * shrink, 255 - n * shrink)
	}

	fun getRightPath(n: Int): Wall {
		val rLocs = intArrayOf(100 + n * shrink, 100 + n * shrink, 700 - n * shrink, 700 - n * shrink)
		val cLocs = intArrayOf(700 - n * shrink, 750 - n * shrink, 750 - n * shrink, 700 - n * shrink)
		return Wall(rLocs, cLocs, "RightPath", 255 - n * shrink, 255 - n * shrink, 255 - n * shrink)
	}

	fun getRightFloor(n: Int): Wall {
		val rLocs = intArrayOf(700 - n * shrink, 650 - n * shrink, 650 - n * shrink)
		val cLocs = intArrayOf(700 - n * shrink, 700 - n * shrink, 650 - n * shrink)
		return Wall(rLocs, cLocs, "RightFloor", 255 - n * shrink, 255 - n * shrink, 255 - n * shrink)
	}

	fun getRightCeil(n: Int): Wall {
		val rLocs = intArrayOf(100 + n * shrink, 150 + n * shrink, 150 + n * shrink)
		val cLocs = intArrayOf(700 - n * shrink, 700 - n * shrink, 650 - n * shrink)
		return Wall(rLocs, cLocs, "RightCeil", 255 - n * shrink, 255 - n * shrink, 255 - n * shrink)
	}

	override fun keyPressed(e: KeyEvent) {
		if (e.keyCode == 32) {
			draw3D = !draw3D
			println("Switch screens!")
		}
		if (e.keyCode == 37 || e.keyCode == 38 || e.keyCode == 39 || e.keyCode == 40) {
//			e.keyCode.move(list)
			if (explorer.direction == 0) println("Direction: north")
			if (explorer.direction == 1) println("Direction: east")
			if (explorer.direction == 2) println("Direction: south")
			if (explorer.direction == 3) println("Direction: west")
		}
		if (draw3D) {
			createWalls()
		}
		if (explorer.location.r == 30 && explorer.location.c == 72) {
			println("You win!")
		}
		repaint()
	}

	override fun keyReleased(e: KeyEvent) {}
	override fun keyTyped(e: KeyEvent) {}
	override fun mouseClicked(e: MouseEvent) {}
	override fun mousePressed(e: MouseEvent) {}
	override fun mouseReleased(e: MouseEvent) {}
	override fun mouseEntered(e: MouseEvent) {}
	override fun mouseExited(e: MouseEvent) {}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			val app = Maze()
			app.setBoard()
		}
	}

	init {
		frame = JFrame()
		frame.add(this)
		frame.defaultCloseOperation = EXIT_ON_CLOSE
		frame.setSize(1500, 800)
		frame.isVisible = true
		frame.addKeyListener(this)
		frame.addMouseListener(this)
		startup(currentThread())
		MediaPlayer(
			Media(
				File("Classwork/dataStructures/src/mazeAttempts/Background-Music.mp3").toURI().toString()
			)
		).play()
	}
}*/
