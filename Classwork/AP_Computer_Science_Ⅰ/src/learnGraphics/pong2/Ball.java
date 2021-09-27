package learnGraphics.pong2;

import java.awt.*;

import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class Ball {
	private static final int WIDTH = 30, HEIGHT = 30;
	private final Pong game;
	private int x, y, xa = 2, ya = 2;

	Ball(Pong game) {
		this.game = game;
		x = game.getWidth() / 2;
		y = game.getHeight() / 2;
	}

	void update() {
		updateLocation();
		checkCollisionWithSides();
		checkVictoryConditions();
		checkCollisionWithRacket();
	}

	private void checkCollisionWithRacket() {
		var panel = game.getPanel();
		if (panel.getPlayer(1).getBounds().intersects(getBounds())
				|| panel.getPlayer(2).getBounds().intersects(getBounds()))
			xa *= -1;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public void paint(Graphics g) {
		g.fillRect(x, y, WIDTH, HEIGHT);
	}

	private boolean hasHitTopOrBottom() {
		return (y < 0) || (y > (game.getHeight() - HEIGHT - 29));
	}

	private void updateLocation() {
		x += xa;
		y += ya;
	}

	private void checkCollisionWithSides() {
		if (x < 0) {
			game.getPanel().increaseScoreForPlayer(1);
			x = game.getWidth() / 2;
			xa *= -1;
		} else if (x > game.getWidth() - WIDTH - 7) {
			game.getPanel().increaseScoreForPlayer(2);
			x = game.getWidth() / 2;
			xa *= -1;
		} else if (hasHitTopOrBottom())
			ya *= -1;
	}

	private void checkVictoryConditions() {
		if (game.getPanel().getScore(1) == 10)
			showMessageDialog(null, "Player 1 wins", "Pong", PLAIN_MESSAGE);
		else if (game.getPanel().getScore(2) == 10)
			showMessageDialog(null, "Player 2 wins", "Pong", PLAIN_MESSAGE);
	}
}
