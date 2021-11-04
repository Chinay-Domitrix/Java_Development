package learnGraphics.pong2;

import java.awt.*;

public class Racket {
	private static final int WIDTH = 10, HEIGHT = 60;
	private final Pong game;
	private final int up, down;
	private final int x;
	private int y, ya;

	Racket(Pong game, int up, int down, int x) {
		this.game = game;
		this.x = x;
		this.y = game.getHeight() / 2;
		this.up = up;
		this.down = down;
	}

	void update() {
		if (y > 0 && y < (game.getHeight() - HEIGHT - 29))
			y += ya;
		else if (y == 0)
			y++;
		else if (y == (game.getHeight() - HEIGHT - 29))
			y--;
	}

	void pressed(int keyCode) {
		if (keyCode == up)
			ya = -1;
		else if (keyCode == down)
			ya = 1;
	}

	void released(int keyCode) {
		if ((keyCode == up) || (keyCode == down))
			ya = 0;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	public void paint(Graphics g) {
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
}
