package projects.musicBox

import java.awt.BorderLayout
import java.awt.BorderLayout.SOUTH
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.Insets
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.AdjustmentEvent
import java.awt.event.AdjustmentListener
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.lang.System.getProperty
import java.lang.Thread.sleep
import java.util.*
import javax.sound.sampled.AudioSystem.getAudioInputStream
import javax.sound.sampled.AudioSystem.getClip
import javax.sound.sampled.Clip
import javax.sound.sampled.LineUnavailableException
import javax.sound.sampled.UnsupportedAudioFileException
import javax.swing.*
import javax.swing.JFileChooser.APPROVE_OPTION
import javax.swing.JScrollBar.HORIZONTAL
import javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
import javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
import javax.swing.filechooser.FileNameExtensionFilter

class MusicBox : JFrame(), Runnable, ActionListener, AdjustmentListener {
	private var arr: Array<Array<JToggleButton?>>
	private var buttons: JPanel
	private var octaves: ArrayList<String>
	private var p: JScrollPane
	private var button: JPanel
	private var col: Int
	private var timing: Thread
	private var notes: Array<String> = arrayOf(
		"C4",
		"B4",
		"AS4",
		"A4",
		"GS3",
		"G3",
		"FS3",
		"F3",
		"E3",
		"DS3",
		"D3",
		"CS3",
		"C3",
		"B3",
		"AS3",
		"A3",
		"GS2",
		"G2",
		"FS2",
		"F2",
		"E2",
		"DS2",
		"D2",
		"CS2",
		"C2",
		"B2",
		"AS2",
		"A2",
		"GS1",
		"G1",
		"FS1",
		"F1",
		"E1",
		"DS1",
		"D1",
		"CS1",
		"C1"
	)
	private var clip: Array<Clip?>
	private var instrumentNames: Array<String>
	private var m: JMenuBar = JMenuBar()
	private var instruments: JMenu
	private var playButton: JToggleButton
	private var clear: JToggleButton
	private var bell: JMenuItem
	private var glockenspiel: JMenuItem
	private var marimba: JMenuItem
	private var oboe: JMenuItem
	private var ohAh: JMenuItem
	private var piano: JMenuItem
	private var save: JMenuItem = JMenuItem("Save")
	private var load: JMenuItem = JMenuItem("Load")
	private var currentlyPlaying: Boolean
	private var tempo: JScrollBar
	private var tempoValue = 200
	private var file: JMenu = JMenu("File")
	private var currentDirectory: String = "${getProperty("user.dir")}/Data_Structures/src/projects/musicBox"
	private var fileChooser: JFileChooser

	init {
		save.addActionListener(this)
		load.addActionListener(this)
		file.apply {
			add(save)
			add(load)
		}
		m.add(file)
		currentlyPlaying = false
		button = JPanel().also { it.layout = GridLayout(1, 2) }
		playButton = JToggleButton("Play").also { it.addActionListener(this) }
		clear = JToggleButton("Clear")
		clear.addActionListener(this)
		button.apply {
			add(playButton)
			add(clear)
		}
		m.add(button)
		instruments = JMenu("Instruments")
		timing = Thread().also { it.start() }
		col = 0
		arr = Array(37) { arrayOfNulls(50) }
		buttons = JPanel()
		octaves = ArrayList()
		clip = arrayOfNulls(37)
		instrumentNames = arrayOf("Bell", "Glockenspiel", "Marimba", "Oboe", "Oh_Ah", "Piano")
		tempo = JScrollBar(HORIZONTAL, tempoValue, 0, 50, 350).apply { addAdjustmentListener(this@MusicBox) }
		fileChooser = JFileChooser("$currentDirectory/saves")
		bell = JMenuItem(instrumentNames[0]).apply {
			putClientProperty("name", instrumentNames[0])
			addActionListener(this@MusicBox)
			instruments.add(this)
		}
		glockenspiel = JMenuItem(instrumentNames[1]).apply {
			putClientProperty("name", instrumentNames[1])
			addActionListener(this@MusicBox)
			instruments.add(this)
		}
		marimba = JMenuItem(instrumentNames[2]).apply {
			putClientProperty("name", instrumentNames[2])
			addActionListener(this@MusicBox)
			instruments.add(this)
		}
		oboe = JMenuItem(instrumentNames[3]).apply {
			putClientProperty("name", instrumentNames[3])
			addActionListener(this@MusicBox)
			instruments.add(this)
		}
		ohAh = JMenuItem(instrumentNames[4]).apply {
			putClientProperty("name", instrumentNames[4])
			addActionListener(this@MusicBox)
			instruments.add(this)
		}
		piano = JMenuItem(instrumentNames[5]).apply {
			putClientProperty("name", instrumentNames[5])
			addActionListener(this@MusicBox)
			instruments.add(this)
		}
		m.add(instruments)
		buttons.layout = GridLayout(37, 50)
		arr.indices.forEach { i ->
			arr[i].indices.forEach { j ->
				arr[i][j] = JToggleButton().apply {
					preferredSize = Dimension(30, 30)
					margin = Insets(0, 0, 0, 0)
					buttons.add(this)
					text = notes[i]
				}
			}
		}
		val initInstrument = instrumentNames[0]
		loadTones(initInstrument)
		layout = BorderLayout()
		jMenuBar = m
		add(buttons)
		p = JScrollPane(buttons, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_ALWAYS)
		add(p)
		add(tempo, SOUTH)
		setSize(1500, 1110)
		defaultCloseOperation = EXIT_ON_CLOSE
		isVisible = true
		run()
	}

	override fun run() {
		while (true) try {
			if (!currentlyPlaying) sleep(0) else {
				arr.indices.filter { arr[it][col]!!.isSelected }.forEach { clip[it]!!.start() }
				sleep(tempoValue.toLong())
				arr.indices.forEach {
					if (arr[it][col]!!.isSelected) clip[it]!!.apply {
						stop()
						framePosition = 0
					}
				}.also { if (col < (arr[0].size - 1)) col++ else col = 0 }
			}
		} catch (_: Exception) {
		}
	}

	override fun actionPerformed(e: ActionEvent) {
		if (e.source === playButton) {
			playButton.text = if (currentlyPlaying) "Play" else "Stop"
			currentlyPlaying = !currentlyPlaying
		} else if (e.source === clear) arr.forEachIndexed { i, j ->
			arr[i].indices.forEach { k ->
				j[k]!!.isSelected = false
				instruments.text = "Instruments"
			}
		}.also { reset() } else if (e.source === save) {
			reset()
			saveSong()
		} else if (e.source === load) {
			reset()
			loadFile()
		} else if (e.source === bell || e.source === glockenspiel || e.source === marimba || e.source === oboe || e.source === ohAh || e.source === piano) {
			loadTones(((e.source as JMenuItem).getClientProperty("name") as String))
			instruments.text = (e.source as JMenuItem).getClientProperty("name") as String
		}
	}

	private fun reset() {
		col = 0
		currentlyPlaying = false
		playButton.isSelected = false
		clear.isSelected = false
		playButton.text = "Play"
	}

	/*private fun loadFile() {
		try {
			if (fileChooser.apply { fileFilter = FileNameExtensionFilter("Text documents (*.txt)", ".txt") }
					.showOpenDialog(this) == APPROVE_OPTION) {
				val input = BufferedReader(
					FileReader(
						fileChooser.selectedFile
					)
				)
				var temporary = input.readLine()
				val t = temporary.split(" ")
				tempoValue = t[0].toInt()
				val columns = t[1].toInt()
				val song = Array(37) { CharArray(columns) }
				var row = 0
				while (input.readLine().also { temporary = it } != null) song[0].indices.forEach { i ->
					song[row][i] = temporary.split("")[i].toCharArray()[0]
				}.also { row++ }
				setNotes(song)
			}
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}*/
	private fun loadFile() = try {
		if (fileChooser.showOpenDialog(this) == APPROVE_OPTION) {
			val loadFile = fileChooser.selectedFile
			val input = Scanner(loadFile)
			val temporary = input.nextLine()
			val t = temporary.split(" ").dropLastWhile { it.isEmpty() }.toTypedArray()
			tempoValue = t[0].toInt()
			val columns = t[1].toInt()
			val song = Array(37) { CharArray(columns) }
			var row = 0
			while (input.hasNextLine()) with(input.nextLine()) {
				song[0].indices.forEach { i ->
					song[row][i] = this[i]
				}.also { row++ }
			}
			setNotes(song)
		} else Unit
	} catch (e: Exception) {
		e.printStackTrace()
	}

	private fun setNotes(c: Array<CharArray>) {
		p.remove(buttons)
		buttons = JPanel()
		arr = Array(c.size) { arrayOfNulls(c[0].size) }
		buttons.layout = GridLayout(arr.size, arr[0].size)
		arr.indices.forEach { i ->
			arr[i].indices.forEach { j ->
				arr[i][j] = JToggleButton().apply {
					preferredSize = Dimension(30, 30)
					margin = Insets(0, 0, 0, 0)
					buttons.add(this)
					text = notes[i]
				}
			}
		}
		this.remove(p)
		p = JScrollPane(buttons)
		this.add(p)
		c.indices.forEach { i ->
			c[i].indices.filter { c[i][it] == 'x' }.forEach { arr[i][it]!!.isSelected = true }
		}
		revalidate()
	}

	private fun saveSong() {
		var currSong = ""
		arrayOf(
			" ",
			"c ",
			"b ",
			"a-",
			"a ",
			"g-",
			"g ",
			"f-",
			"f ",
			"e ",
			"d-",
			"d ",
			"c-",
			"c ",
			"b ",
			"a-",
			"a ",
			"g-",
			"g ",
			"f-",
			"f ",
			"e ",
			"d-",
			"d ",
			"c-",
			"c ",
			"b ",
			"a-",
			"a ",
			"g-",
			"g ",
			"f-",
			"f ",
			"e ",
			"d-",
			"d ",
			"c-",
			"c"
		)
		fileChooser.fileFilter = FileNameExtensionFilter("Text documents (*.txt)", ".txt")
		if (fileChooser.showSaveDialog(null) == APPROVE_OPTION) try {
			with(fileChooser.selectedFile.absolutePath.substringBefore(".txt")) {
				arr.indices.forEach { i ->
					if (i == 0) currSong += "$tempoValue ${arr[0].size}\n"
					arr[i].indices.forEach { j ->
						currSong += if (arr[i][j]!!.isSelected) "x" else "-"
					}
					currSong += "\n"
				}
				BufferedWriter(FileWriter("$this.txt")).apply {
					write(currSong)
					close()
				}
			}
		} catch (_: Exception) {
		}
	}

	private fun loadTones(initInstrument: String) = try {
		clip.indices.forEach {
			clip[it] = getClip().apply {
				open(
					getAudioInputStream(
						File("$currentDirectory/sounds/$initInstrument/$initInstrument - ${notes[it]}.wav")
					)
				)
			}
		}
	} catch (e: UnsupportedAudioFileException) {
		e.printStackTrace()
	} catch (e: IOException) {
		e.printStackTrace()
	} catch (e: LineUnavailableException) {
		e.printStackTrace()
	}

	override fun adjustmentValueChanged(e: AdjustmentEvent) {
		if (e.source === tempo) tempoValue = tempo.value
	}
}

fun main() {
	MusicBox()
}

