package learnGraphics.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.Font.PLAIN;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

class PongPanel extends JPanel implements KeyListener, ActionListener {
	private final Paddle paddle;
	private final Ball ball;
	private final JLabel scoreLabel;
	private int score = 0;

	PongPanel(Pong game) {
		paddle = new Paddle(game, game.getFrame().getHeight() - 100);
		ball = new Ball();
		scoreLabel = new JLabel(Integer.toString(score));
		scoreLabel.setFont(new Font("Comic Sans MS", PLAIN, 30));
		add(scoreLabel);
		new Timer(5, this).start();
		addKeyListener(this);
		setFocusable(true);
	}

	private void update() {
		paddle.updatePosition();
		ball.updatePosition();
		checkCollisionBallSides();
		checkCollisionBallRacket();
		repaint();
	}

	private void checkCollisionBallSides() {
		if ((ball.getX() < 0) || (ball.getX() > (getWidth() - ball.getWidth() - (getInsets().left + getInsets().right))))
			ball.setXA(-ball.getXA());
		else if (ball.getY() < 0)
			ball.setYA(-ball.getYA());
		else if (ball.getY() > (getHeight() - ball.getHeight())) {
			/*int x = */
			showMessageDialog(this, "Game Over. You scored " + score + "."/*\n\nDo you want to play again?*/, "Pong", INFORMATION_MESSAGE);
			/*if (x == 0) {
				Thread.currentThread().interrupt();
				Pong.main(new String[0]);
			} else if (x == 1)*/
			System.exit(0);
		}
	}

	private void checkCollisionBallRacket() {
		if (ball.getBounds().intersects(paddle.getBounds())) {
			if (ball.getBounds().y + ball.getHeight() > paddle.getBounds().y &&
					(ball.getBounds().intersects(paddle.getBounds().x, paddle.getBounds().y + 1, 10, paddle.getHeight() - 1) ||
							ball.getBounds().intersects(paddle.getBounds().x + paddle.getWidth() - 9, paddle.getBounds().y + 1, 10, paddle.getHeight() - 1)))
				ball.setXA(-ball.getXA());
			else {
				ball.setYA(-ball.getYA());
				score++;
			}
			scoreLabel.setText(Integer.toString(score));
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paddle.paint(g);
		ball.paint(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		paddle.pressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		paddle.released(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
	}
}
