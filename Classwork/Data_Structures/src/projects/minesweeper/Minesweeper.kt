package projects.minesweeper

import projects.minesweeper.Minesweeper.Companion.instantiate
import java.awt.Color.RED
import java.awt.GridLayout
import java.awt.Image.SCALE_SMOOTH
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.MouseEvent
import java.awt.event.MouseEvent.BUTTON1
import java.awt.event.MouseEvent.BUTTON3
import java.awt.event.MouseListener
import javax.swing.*
import kotlin.properties.Delegates.notNull
import kotlin.random.Random.Default.nextInt

class Minesweeper : JFrame(), ActionListener, MouseListener {
	private var boardPanel: JPanel? = null
	private lateinit var board: Array<Array<JToggleButton?>>
	private var faceButton: JToggleButton = JToggleButton()
	private var firstClick = true
	private var gameOn = true
	private var dimR = 9
	private var dimC = 9
	private var numMines = 10
	private var flag = ImageIcon(
		ImageIcon("C:/Users/chira/OneDrive/Documents/Programming/Java_Development/Classwork/Data_Structures/src/projects/minesweeper/images/flag.png").image.getScaledInstance(
			35, 35, SCALE_SMOOTH
		)
	)
	private var mine = ImageIcon(
		ImageIcon("C:/Users/chira/OneDrive/Documents/Programming/Java_Development/Classwork/Data_Structures/src/projects/minesweeper/images/mine.png").image.getScaledInstance(
			35, 35, SCALE_SMOOTH
		)
	)
	private var smileFace = ImageIcon(
		ImageIcon("C:/Users/chira/OneDrive/Documents/Programming/Java_Development/Classwork/Data_Structures/src/projects/minesweeper/images/smile1.png").image.getScaledInstance(
			35, 35, SCALE_SMOOTH
		)
	)
	var deadFace = ImageIcon(
		ImageIcon("C:/Users/chira/OneDrive/Documents/Programming/Java_Development/Classwork/Data_Structures/src/projects/minesweeper/images/dead1.png").image.getScaledInstance(
			35, 35, SCALE_SMOOTH
		)
	)
	var winFace = ImageIcon(
		ImageIcon("C:/Users/chira/OneDrive/Documents/Programming/Java_Development/Classwork/Data_Structures/src/projects/minesweeper/images/win1.png").image.getScaledInstance(
			35, 35, SCALE_SMOOTH
		)
	)
	private var waitFace = ImageIcon(
		ImageIcon("C:/Users/chira/OneDrive/Documents/Programming/Java_Development/Classwork/Data_Structures/src/projects/minesweeper/images/wait1.png").image.getScaledInstance(
			35, 35, SCALE_SMOOTH
		)
	)
	private var one = ImageIcon(
		ImageIcon("C:/Users/chira/OneDrive/Documents/Programming/Java_Development/Classwork/Data_Structures/src/projects/minesweeper/images/1.png").image.getScaledInstance(
			35, 35, SCALE_SMOOTH
		)
	)
	private var two = ImageIcon(
		ImageIcon("C:/Users/chira/OneDrive/Documents/Programming/Java_Development/Classwork/Data_Structures/src/projects/minesweeper/images/2.png").image.getScaledInstance(
			35, 35, SCALE_SMOOTH
		)
	)
	private var three = ImageIcon(
		ImageIcon("C:/Users/chira/OneDrive/Documents/Programming/Java_Development/Classwork/Data_Structures/src/projects/minesweeper/images/3.png").image.getScaledInstance(
			35, 35, SCALE_SMOOTH
		)
	)
	private var four = ImageIcon(
		ImageIcon("C:/Users/chira/OneDrive/Documents/Programming/Java_Development/Classwork/Data_Structures/src/projects/minesweeper/images/4.png").image.getScaledInstance(
			35, 35, SCALE_SMOOTH
		)
	)
	private var five = ImageIcon(
		ImageIcon("C:/Users/chira/OneDrive/Documents/Programming/Java_Development/Classwork/Data_Structures/src/projects/minesweeper/images/5.png").image.getScaledInstance(
			35, 35, SCALE_SMOOTH
		)
	)
	private var six = ImageIcon(
		ImageIcon("C:/Users/chira/OneDrive/Documents/Programming/Java_Development/Classwork/Data_Structures/src/projects/minesweeper/images/6.png").image.getScaledInstance(
			35, 35, SCALE_SMOOTH
		)
	)
	private var seven = ImageIcon(
		ImageIcon("C:/Users/chira/OneDrive/Documents/Programming/Java_Development/Classwork/Data_Structures/src/projects/minesweeper/images/7.png").image.getScaledInstance(
			35, 35, SCALE_SMOOTH
		)
	)
	private var eight = ImageIcon(
		ImageIcon("C:/Users/chira/OneDrive/Documents/Programming/Java_Development/Classwork/Data_Structures/src/projects/minesweeper/images/8.png").image.getScaledInstance(
			35, 35, SCALE_SMOOTH
		)
	)
	private var menuBar = JMenuBar()
	private var beginner = JMenuItem("Beginner").apply { addActionListener(this@Minesweeper) }
	private var medium = JMenuItem("Medium").apply { addActionListener(this@Minesweeper) }
	private var expert = JMenuItem("Expert").apply { addActionListener(this@Minesweeper) }
	private var numClick by notNull<Int>()
	private var difficulty: JMenu = JMenu("Difficulty: Beginner").apply {
		add(beginner)
		add(medium)
		add(expert)
	}

	init {
		faceButton.icon = smileFace
		faceButton.addMouseListener(this)
		//topPanel = new JPanel();
		this.jMenuBar = menuBar.apply {
			layout = GridLayout(1, 2)
			add(difficulty)
			add(faceButton)
		}
		createBoard(dimR, dimC)
		this.isVisible = true
		this.defaultCloseOperation = EXIT_ON_CLOSE
	}

	private fun createBoard(row: Int, col: Int) {
		numClick = (row * col) - numMines
		firstClick = true
		gameOn = true
		if (boardPanel != null) this.remove(boardPanel)
		board = Array(row) { arrayOfNulls(col) }
		boardPanel = JPanel()
		boardPanel!!.layout = GridLayout(row, col)
		for (r in board.indices) for (c in board[r].indices) {
			board[r][c] = JToggleButton()
			board[r][c]!!.putClientProperty("row", r)
			board[r][c]!!.putClientProperty("col", c)
			board[r][c]!!.putClientProperty("state", 0)
			board[r][c]!!.addMouseListener(this)
			boardPanel!!.add(board[r][c])
		}
		this.setSize(board[0].size * 35, board.size * 35 + 10)
		this.add(boardPanel)
		revalidate()
	}

	private fun setBombsAndNums(selectedRow: Int, selectedCol: Int) {
		var count = numMines
		while (count > 0) {
			val row = nextInt(dimR)
			val col = nextInt(dimC)
			val state = "${board[row][col]!!.getClientProperty("state")}".toInt()
			if ((state == 0) && ((row != selectedRow) || (col != selectedCol))) {
				board[row][col]!!.putClientProperty("state", -1)
				count--
			}
		}
		(0 until dimR).forEach { i ->
			(0 until dimC).forEach { j ->
				count = 0
				var state = "${board[i][j]!!.getClientProperty("state")}".toInt()
				if (state != -1) {
					for (smallR in i - 1..i + 1) {
						for (smallC in j - 1..j + 1) {
							try {
								state = ("${board[smallR][smallC]!!.getClientProperty("state")}").toInt()
								if (state == -1 && (smallR != i || smallC != j)) count++
							} catch (_: ArrayIndexOutOfBoundsException) {
							}
						}
					}
					board[i][j]!!.putClientProperty("state", count)
				}
			}
		}
	}

	private fun click(row: Int, col: Int) {
		if (!board[row][col]!!.isSelected) board[row][col]!!.isSelected = true
		val state = board[row][col]!!.getClientProperty("state") as Int
		if (state != 0) board[row][col]!!.apply {
			when (state) {
				1 -> {
					icon = one
					disabledIcon = one
					isEnabled = false
				}
				2 -> {
					icon = two
					disabledIcon = two
					isEnabled = false
				}
				3 -> {
					icon = three
					disabledIcon = three
					isEnabled = false
				}
				4 -> {
					icon = four
					disabledIcon = four
					isEnabled = false
				}
				5 -> {
					icon = five
					disabledIcon = five
					isEnabled = false
				}
				6 -> {
					icon = six
					disabledIcon = six
					isEnabled = false
				}
				7 -> {
					icon = seven
					disabledIcon = seven
					isEnabled = false
				}
				8 -> {
					icon = eight
					disabledIcon = eight
					isEnabled = false
				}
			}
		} else for (smallR in (row - 1) until (row + 1)) for (smallC in col - 1 until col + 1) try {
			if (!board[smallR][smallC]!!.isSelected) click(smallR, smallC)
		} catch (_: ArrayIndexOutOfBoundsException) {
		}
	}

	override fun actionPerformed(a: ActionEvent) {
		if (a.source === beginner) {
			numMines = 10
			dimR = 9
			dimC = 9
			difficulty.text = "Difficulty: Beginner"
		} else if (a.source === medium) {
			numMines = 35
			dimR = 16
			dimC = 16
			difficulty.text = "Difficulty: Medium"
		} else if (a.source === expert) {
			numMines = 99
			dimR = 16
			dimC = 35
			difficulty.text = "Difficulty: Expert"
		}
		createBoard(dimR, dimC)
	}

	override fun mouseExited(e: MouseEvent) {}
	override fun mouseClicked(e: MouseEvent) {}
	override fun mouseEntered(e: MouseEvent) {}
	override fun mouseReleased(e: MouseEvent) {
		faceButton.icon = smileFace
		if (e.component === faceButton) createBoard(dimR, dimC)
		val row: Int
		val col: Int
		if (e.component !== faceButton) {
			row = (e.component as JToggleButton).getClientProperty("row") as Int
			col = (e.component as JToggleButton).getClientProperty("col") as Int
			if (gameOn) if (e.button == BUTTON1) {
				if (firstClick) {
					setBombsAndNums(row, col)
					click(row, col)
					faceButton.icon = waitFace
					faceButton.icon = smileFace
					firstClick = false
				}
				val state = board[row][col]!!.getClientProperty("state") as Int
				if ((state == -1) && board[row][col]!!.isEnabled) {
					board[row][col]!!.apply {
						icon = mine
						disabledIcon = mine
						isEnabled = false
					}
					for (i in board.indices) for (j in board[0].indices) {
						board[i][j]!!.isEnabled = false
						if (((i != row) || (j != col)) && ((board[i][j]!!.getClientProperty("state") as Int) == -1)) board[i][j]!!.apply {
							icon = mine
							background = RED
							disabledIcon = mine
							isEnabled = false
							isSelected = true
							faceButton.icon = deadFace
						}
						board[i][j]!!.isSelected = true
					}
				} else if (board[row][col]!!.isEnabled) click(row, col)
			} else if (e.button == BUTTON3 && !board[row][col]!!.isSelected) if (board[row][col]!!.icon == null) {
				board[row][col]!!.icon = flag
				board[row][col]!!.disabledIcon = flag
				board[row][col]!!.isEnabled = false
			} else {
				board[row][col]!!.icon = null
				board[row][col]!!.isEnabled = true
			}
			if (checkWin()) faceButton.icon = winFace
		}
	}

	private fun checkWin(): Boolean {
		var countCorrectFlags = 0
		var numClicked = 0
		for (i in board.indices) for (j in 0 until board[0].size) if ((board[i][j]!!.icon === flag) && ((board[i][j]!!.getClientProperty(
				"state"
			) as Int) == -1)
		) countCorrectFlags++ else if (((board[i][j]!!.getClientProperty(
				"state"
			) as Int) != -1) && board[i][j]!!.isSelected
		) numClicked++
		return (countCorrectFlags == numMines) && (numClicked == numClick)
	}

	override fun mousePressed(e: MouseEvent) {
		faceButton.icon = waitFace
	}

	companion object {
		fun instantiate() {
			Minesweeper()
		}
	}
}

fun main() = instantiate()
