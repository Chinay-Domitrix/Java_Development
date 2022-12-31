package learnGraphics.pong

import java.awt.Font
import java.awt.Font.PLAIN
import java.awt.Graphics
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JLabel
import javax.swing.JOptionPane.INFORMATION_MESSAGE
import javax.swing.JOptionPane.showMessageDialog
import javax.swing.JPanel
import javax.swing.Timer
import kotlin.system.exitProcess

internal class PongPanel(game: Pong) : JPanel(), KeyListener, ActionListener {
	private val paddle: Paddle
	private val ball: Ball
	private val scoreLabel: JLabel
	private var score = 0

	init {
		paddle = Paddle(game, game.frame.height - 100)
		ball = Ball()
		scoreLabel = JLabel(score.toString())
		scoreLabel.font = Font("Comic Sans MS", PLAIN, 30)
		add(scoreLabel)
		Timer(5, this).start()
		addKeyListener(this)
		isFocusable = true
	}

	private fun update() {
		paddle.updatePosition()
		ball.updatePosition()
		checkCollisionBallSides()
		checkCollisionBallRacket()
		repaint()
	}

	private fun checkCollisionBallSides() {
		if ((ball.x < 0) || (ball.x > (width - ball.width - (insets.left + insets.right)))) ball.xA =
			-ball.xA else if (ball.y < 0) ball.yA = -ball.yA else if (ball.y > height - ball.height) {
			showMessageDialog(
				this, "Game Over. You scored $score.", "Pong", INFORMATION_MESSAGE
			)
			exitProcess(0)
		}
	}

	private fun checkCollisionBallRacket() {
		if (ball.bounds.intersects(paddle.bounds)) {
			if (((ball.bounds.y + ball.height) > paddle.bounds.y) && (ball.bounds.intersects(
					paddle.bounds.x.toDouble(), (paddle.bounds.y + 1).toDouble(), 2.0, (paddle.height - 1).toDouble()
				) || ball.bounds.intersects(
					(((paddle.bounds.x + paddle.width) - 1)).toDouble(),
					(paddle.bounds.y + 1).toDouble(),
					2.0,
					(paddle.height - 1).toDouble()
				))
			) ball.xA = -ball.xA else {
				ball.yA = -ball.yA
				score++
			}
			scoreLabel.text = score.toString()
		}
	}

	override fun paint(g: Graphics) {
		super.paint(g)
		paddle.paint(g)
		ball.paint(g)
	}

	override fun keyPressed(e: KeyEvent) = paddle.pressed(e)

	override fun keyReleased(e: KeyEvent) = paddle.released(e)

	override fun keyTyped(e: KeyEvent) {}
	override fun actionPerformed(e: ActionEvent) = update()
}
