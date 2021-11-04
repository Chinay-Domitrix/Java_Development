package maze

import java.awt.Color.*
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.FileReader
import javax.swing.JFrame
import javax.swing.JPanel

class Maze : JPanel(), KeyListener, MouseListener {
	var frame: JFrame
	lateinit var maze: Array<Array<String?>>
	var explorer: Explorer? = null
	var isPainted = false
	var alreadyRead = false
	var lineArr = arrayOfNulls<String>(33)
	var k = 0
	var line: String? = null
	var lineCount = 0
	var draw3D = false
	var shrink = 50
	lateinit var walls: ArrayList<Wall>
	public override fun paintComponent(g: Graphics) {
		super.paintComponent(g)
		val g2 = g as Graphics2D
		g.setColor(BLACK)
		g.fillRect(0, 0, 1600, 1000)
		k = 0
		if (draw3D == false) {
			maze = Array(34) { arrayOfNulls<String>(34) }
//			maze = new String[lineArr.length][lineArr[0].split("").length];
			for (i in lineArr.indices) lineArr[0]?.split("".toRegex())?.toTypedArray()?.indices?.forEach {
				maze[i][it] = lineArr[i]?.split("".toRegex())?.toTypedArray()?.get(it)
				if (maze[i][it] == "#") g.setColor(WHITE) else if (maze[i][it] == "1" && isPainted == false) {
					explorer = Explorer(RED, Location(it, i), 15, 0)
					g.setColor(explorer?.color)
					isPainted = true
				} else if (maze[i][it] == "E") g.setColor(GREEN) else g.setColor(BLACK)
				g.fillRect(it * 15, i * 15, 15, 15)
			}
			if (isPainted == true) {
				g.setColor(explorer?.color)
				g2.fill(explorer?.rectangle)
			}
		} else if (draw3D) walls.indices.forEach {
			g2.color = WHITE
			g2.fillPolygon(walls[it].polygon)
			g2.color = RED
			g2.drawPolygon(walls[it].polygon)
		}
	}

	fun createWalls() {
		walls = ArrayList()
		for (n in 0..4) {
			walls.add(getCeil(n))
			walls.add(getFloor(n))
			//walls.add(getLeft(n));
			//walls.add(getRight(n));
		}
		val explorerX = explorer?.location?.x
		val explorerY = explorer?.location?.y
		val explorerDirection = explorer?.dir
		when (explorerDirection) {
			0 -> {
				var n = 0
				while (n < 5) {
					try {
						//if(maze[expR-1][expC-(5-n)] == "#"){
						//	walls.add(getFront(5-n));
						//}
						if (maze[explorerY!! + 1][explorerX!! + n] == "#") walls.add(getRight(n))
						if (maze[explorerY - 1][explorerX + n] == "#") walls.add(getLeft(n))
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
						//if(maze[expR-1][expC-(5-n)] == "#"){
						//	walls.add(getFront(5-n));
						//}
						if (maze[explorerY!! + n][explorerX!! - 1] == "#") walls.add(getRight(n))
						if (maze[explorerY + n][explorerX + 1] == "#") walls.add(getLeft(n))
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
						//if(maze[expR-1][expC-(5-n)] == "#"){
						//	walls.add(getFront(5-n));
						//}
						if (maze[explorerY!! + 1][explorerX!! - n] == "#") walls.add(getRight(n))
						if (maze[explorerY - 1][explorerX - n] == "#") walls.add(getLeft(n))
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
						//if(maze[expR-1][expC-(5-n)] == "#"){
						//	walls.add(getFront(5-n));
						//}
						if (maze[explorerY!! - n][explorerX!! + 1] == "#") walls.add(getRight(n))
						if (maze[explorerY - n][explorerX - 1] == "#") walls.add(getLeft(n))
					} catch (e: ArrayIndexOutOfBoundsException) {
						e.printStackTrace()
					}
					n++
				}
			}
			-1 -> {
				var n = 0
				while (n < 5) {
					try {
						//if(maze[expR-1][expC-(5-n)] == "#"){
						//	walls.add(getFront(5-n));
						//}
						if (maze[explorerY!! - n][explorerX!! + 1] == "#") walls.add(getRight(n))
						if (maze[explorerY - n][explorerX - 1] == "#") walls.add(getLeft(n))
					} catch (e: ArrayIndexOutOfBoundsException) {
						e.printStackTrace()
					}
					n++
				}
			}
			-2 -> {
				var n = 0
				while (n < 5) {
					try {
						//if(maze[expR-1][expC-(5-n)] == "#"){
						//	walls.add(getFront(5-n));
						//}
						if (maze[explorerY!! + 1][explorerX!! - n] == "#") walls.add(getRight(n))
						if (maze[explorerY - 1][explorerX - n] == "#") walls.add(getLeft(n))
					} catch (e: ArrayIndexOutOfBoundsException) {
						e.printStackTrace()
					}
					n++
				}
			}
			-3 -> {
				var n = 0
				while (n < 5) {
					try {
						//if(maze[expR-1][expC-(5-n)] == "#"){
						//	walls.add(getFront(5-n));
						//}
						if (maze[explorerY!! + n][explorerX!! - 1] == "#") walls.add(getRight(n))
						if (maze[explorerY + n][explorerX + 1] == "#") walls.add(getLeft(n))
					} catch (e: ArrayIndexOutOfBoundsException) {
						e.printStackTrace()
					}
					n++
				}
			}
		}
	}

	fun getFloor(n: Int) = Wall(
		intArrayOf(700 - n * shrink, 650 - n * shrink, 650 - n * shrink, 700 - n * shrink),
		intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink),
		"Floor"
	)


	fun getCeil(n: Int) = Wall(
		intArrayOf(100 + n * shrink, 150 + n * shrink, 150 + n * shrink, 100 + n * shrink),
		intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink),
		"Ceil"
	)

	fun getLeft(n: Int) = Wall(
		intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink),
		intArrayOf(100 + n * shrink, 150 + n * shrink, 150 + n * shrink, 100 + n * shrink),
		"Left"
	)

	fun getRight(n: Int) = Wall(
		intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink),
		intArrayOf(700 - n * shrink, 650 - n * shrink, 650 - n * shrink, 700 - n * shrink),
		"Right"
	)

	fun getFront(n: Int) = Wall(
		intArrayOf(100 + n * shrink, 100 + n * shrink, 700 - n * shrink, 700 - n * shrink),
		intArrayOf(100 + n * shrink, 650 - n * shrink, 650 - n * shrink, 700 - n * shrink),
		"Ceil"
	)

	override fun keyPressed(e: KeyEvent) {
		if (e.keyCode == 32) draw3D = !draw3D
		if (draw3D) createWalls()
		explorer?.move(e.keyCode, maze, draw3D)
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
			Maze()
		}
	}

	init {
		frame = JFrame()
		frame.add(this)
		frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		frame.setSize(1600, 1000)
		frame.isVisible = true
		frame.addKeyListener(this)
		frame.addMouseListener(this)
		try {
			val reader = BufferedReader(FileReader("Classwork/dataStructures/src/maze/Maze.txt"))
			while (reader.readLine().also { line = it } != null) {
				lineArr[k] = line
				lineCount++
				k++
			}
		} catch (e: FileNotFoundException) {
			e.printStackTrace()
		}
	}
}
/*
import java.awt.Color.*
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.*
import java.awt.event.KeyListener
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.io.File
import java.io.IOException
import java.lang.System.err
import java.util.*
import javax.swing.JFrame
import javax.swing.JFrame.EXIT_ON_CLOSE
import javax.swing.JPanel

class Maze : JPanel(), KeyListener, MouseListener {
	var frame: JFrame
	var list = ArrayList<String>()
	var walls2D = ArrayList<Location>()
	var walls = ArrayList<Wall>()
	var draw3D = false
	var explorerLocationIndex = 0
	var shrink = 15
	var explorer = Explorer(Location(1, 2), 0, 13, white)
	fun setBoard() {
		try {
			Scanner(File("Classwork/dataStructures/src/maze/Maze.txt")).use {
				var txt: String
				do {
					txt = it.nextLine()
					list.add(txt)
					for (i in 0 until txt.length) if (txt[i] == '1') explorerLocationIndex = txt.indexOf('1')
					repaint()
				} while (it.hasNextLine())
			}
		} catch (e: IOException) {
			err.print("File not found!")
		}
	}

	public override fun paintComponent(g: Graphics) {
		super.paintComponent(g)
		val g2 = g as Graphics2D
		repaint()
		if (!draw3D) {
			for (i in list.indices) (0 until list[i].length).forEach { j ->
				with(list[i].split("")*/
/*.toTypedArray()*//*
) {
					if (this[j] == "#") {
						g.setColor(black)
						g.fillRect(j * 13, i * 13, 13, 13)
						walls2D.add(explorer.location)
					} else if (this[j] == " ") {
						g.setColor(YELLOW)
						g.fillRect(j * 13, i * 13, 13, 13)
					} else if (this[j] == "1") {
						g.setColor(YELLOW)
						g.fillRect(j * 13, i * 13, 13, 13)
					} else if (this[j] == "E") {
						g.setColor(BLUE)
						g.fillRect(j * 13, i * 13, 13, 13)
					}
				}
			}
			g.setColor(explorer.color)
			g.fillRect(explorer.location.x * 13, explorer.location.y * 13, 13, 13)
		} else {
			for (w in walls) {
				g2.color = WHITE
				g2.fillPolygon(w.polygon)
				g2.color = BLACK
				g2.drawPolygon(w.polygon)
			}
		}
	}

	fun createWall() {
		walls = ArrayList()
		for (n in 0..4) {
			walls.add(getCeiling(n))
			walls.add(getFloor(n))
		}
	}

	fun getCeiling(n: Int) = Wall(
		intArrayOf(100 + n * shrink, 150 + n * shrink, 150 + n * shrink, 100 + n * shrink),
		intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink),
		"Ceil"
	)

	fun getFloor(n: Int) = Wall(
		intArrayOf(700 - n * shrink, 650 - n * shrink, 650 - n * shrink, 700 - n * shrink),
		intArrayOf(100 + n * shrink, 150 + n * shrink, 650 - n * shrink, 700 - n * shrink),
		"Floor"
	)

	override fun keyPressed(e: KeyEvent) {
		if (e.keyCode == VK_SPACE) draw3D = !draw3D
		if (draw3D) createWall()
		if (e.keyCode == VK_UP && list[explorer.location.y - 1][explorer.location.x] != '#') {
			println("up")
			explorer.move(0)
			println(explorer.location.y.toString() + " " + explorer.location.x)
			repaint()
		}
		if (e.keyCode == VK_LEFT && list[explorer.location.y][explorer.location.x - 1] != '#') {
			println("left")
			explorer.move(1)
			println(explorer.location.y.toString() + " " + explorer.location.x)
			repaint()
		}
		if (e.keyCode == VK_RIGHT && list[explorer.location.y][explorer.location.x + 1] != '#') {
			println("right")
			explorer.move(2)
			println(explorer.location.y.toString() + " " + explorer.location.x)
			repaint()
		}
		if (e.keyCode == VK_DOWN && list[explorer.location.y + 1][explorer.location.x] != '#') {
			println("down")
			explorer.move(3)
			println(explorer.location.y.toString() + " " + explorer.location.x)
			repaint()
		}
		if (explorer.location.y == 30 && explorer.location.x == 72) println("You win!")
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
			Maze().setBoard()
		}
	}

	init {
		frame = JFrame().apply {
			add(this@Maze)
			defaultCloseOperation = EXIT_ON_CLOSE
			setSize(1000, 600)
			isVisible = true
			addKeyListener(this@Maze)
			addMouseListener(this@Maze)
		}
	}
}*/
