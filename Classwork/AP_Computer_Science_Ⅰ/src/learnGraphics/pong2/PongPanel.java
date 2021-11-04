package learnGraphics.pong2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.Color.WHITE;
import static java.awt.event.KeyEvent.*;

public class PongPanel extends JPanel implements ActionListener, KeyListener {
	private final Pong game;
	private final Ball ball;
	private final Racket player1, player2;
	private int score1, score2;

	PongPanel(Pong game) {
		setBackground(WHITE);
		this.game = game;
		ball = new Ball(game);
		player1 = new Racket(game, VK_UP, VK_DOWN, game.getWidth() - 36);
		player2 = new Racket(game, VK_W, VK_S, 20);
		new Timer(5, this).start();
		addKeyListener(this);
		setFocusable(true);
	}

	Racket getPlayer(int playerNumber) {
		return (playerNumber == 1) ? player1 : player2;
	}

	void increaseScoreForPlayer(int playerNumber) {
		if (playerNumber == 1)
			score1++;
		else
			score2++;
	}

	int getScore(int playerNumber) {
		return (playerNumber == 1) ? score1 : score2;
	}

	private void update() {
		ball.update();
		player1.update();
		player2.update();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		player1.pressed(e.getKeyCode());
		player2.pressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player1.released(e.getKeyCode());
		player2.released(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(game.getPanel().getScore(1) + " : " + game.getPanel().getScore(2), game.getWidth() / 2, 10);
		ball.paint(g);
		player1.paint(g);
		player2.paint(g);
	}
}
