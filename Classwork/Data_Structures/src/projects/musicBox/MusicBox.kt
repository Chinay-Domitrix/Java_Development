package projects.musicBox

import projects.musicBox.MusicBox.Companion.instantiate
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.Insets
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.AdjustmentEvent
import java.awt.event.AdjustmentListener
import java.io.IOException
import java.lang.Thread.sleep
import java.net.URL
import javax.sound.sampled.AudioSystem.getAudioInputStream
import javax.sound.sampled.AudioSystem.getClip
import javax.sound.sampled.Clip
import javax.sound.sampled.LineUnavailableException
import javax.sound.sampled.UnsupportedAudioFileException
import javax.swing.*
import javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
import javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED

class MusicBox : JFrame(), Runnable, ActionListener, AdjustmentListener {
	private var arr: Array<Array<JToggleButton?>>
	private var buttons: JPanel
	private var octaves: ArrayList<String>
	private var button: JPanel = JPanel()
	private var col: Int
	private var timing: Thread
	private var notes: Array<String>
	private var clip: Array<Clip?>
	private var instrumentNames: Array<String>
	private var m: JMenuBar = JMenuBar()
	private var tone: JMenu
	private var tempo: JMenu
	private var playButton: JToggleButton
	private var currentlyPlaying = false

	init {
		button.layout = GridLayout(1, 2)
		playButton = JToggleButton("⏯")
		playButton.addActionListener(this)
		button.add(playButton)
		m.add(button)
		tone = JMenu("Tone")
		tempo = JMenu("Tempo")
		m.add(tone)
		m.add(tempo)
		timing = Thread()
		timing.start()
		col = 0
		arr = Array(37) { arrayOfNulls(50) }
		buttons = JPanel()
		octaves = ArrayList()
		notes = arrayOf(
			"C₄",
			"B₄",
			"A♯₄",
			"A₄",
			"G♯₃",
			"G₃",
			"F♯₃",
			"F₃",
			"E₃",
			"D♯₃",
			"D₃",
			"C♯₃",
			"C₃",
			"B₃",
			"A♯₃",
			"A₃",
			"G♯₂",
			"G₂",
			"F♯₂",
			"F₂",
			"E₂",
			"D♯₂",
			"D₂",
			"C♯₂",
			"C₂",
			"B₂",
			"A♯₂",
			"A₂",
			"G♯₁",
			"G₁",
			"F♯₁",
			"F₁",
			"E₁",
			"D♯₁",
			"D₁",
			"C♯₁",
			"C₁"
		)
		clip = arrayOfNulls(37)
		instrumentNames = arrayOf("Bell", "Glockenspiel", "Marimba", "Oboe", "Oh_Ah", "Piano")
		buttons.layout = GridLayout(37, 50)
		arr.indices.forEach { i ->
			arr[0].indices.forEach { j ->
				arr[i][j] = JToggleButton().apply {
					minimumSize = Dimension(30, 30)

//					preferredSize = Dimension(30, 30)
					margin = Insets(0, 0, 0, 0)
					buttons.add(this)
					text = notes[i]
				}
			}
		}
		val initInstrument = instrumentNames[0]
		try {
			clip.indices.forEach {
				clip[it] = getClip().apply {
					open(
						getAudioInputStream(
							URL(
								"file:/C:/Users/chira/OneDrive/Documents/Programming/Java_Development/Classwork/Data_Structures/src/projects/musicBox/sounds/$initInstrument/$initInstrument - ${
									if ('♯' !in notes[it]) notes[it].replace('₄', '4').replace('₃', '3')
										.replace('₂', '2').replace('₁', '1') else "${notes[it][0]}S${
										notes[it][2].toString().replace('₄', '4').replace('₃', '3').replace('₂', '2')
											.replace('₁', '1')
									}"
								}.wav"
							)
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
		m.add(JButton("Clear").also {
			it.addActionListener {
				arr.indices.forEach { i ->
					arr[i].indices.forEach { j ->
						arr[i][j]?.isSelected = false
					}
				}
			}
		})
		jMenuBar = m
		add(buttons)
		add(JScrollPane(buttons, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED))
		this.setSize(1500, 1110)
		defaultCloseOperation = EXIT_ON_CLOSE
		this.isVisible = true
		run()
	}

	override fun run() {
		while (true) try {
			if (!currentlyPlaying) sleep(0) else {
				arr.indices.filter { arr[it][col]!!.isSelected }.forEach { clip[it]!!.start() }
				sleep(150)
				arr.indices.filter { arr[it][col]!!.isSelected }.forEach {
					clip[it]!!.stop()
					clip[it]!!.framePosition = 0
				}
				if (col < arr[0].size - 1) col++ else col = 0
			}
		} catch (_: Exception) {
		}
	}

	override fun actionPerformed(e: ActionEvent) {
		if (e.source === playButton) {
			currentlyPlaying = !currentlyPlaying
			playButton.text = if (currentlyPlaying) "⏸" else "▶"
		}
	}

	override fun adjustmentValueChanged(e: AdjustmentEvent) {}

	companion object {
		fun instantiate() {
			MusicBox()
		}
	}
}

fun main() = instantiate()
