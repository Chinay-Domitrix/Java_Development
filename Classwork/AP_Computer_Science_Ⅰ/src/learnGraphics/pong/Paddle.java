package learnGraphics.pong;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import static java.awt.event.KeyEvent.*;
import static java.lang.System.exit;

class Paddle extends Sprite {
	private final Pong game;

	Paddle(Pong game, int y) {
		super((game.getFrame().getWidth() - 60) / 2, y, 0, 0, 60, 10);
		this.game = game;
	}

	void pressed(KeyEvent e) {
		if (e.getKeyCode() == VK_LEFT) setXA(-4);
		else if (e.getKeyCode() == VK_RIGHT) setXA(4);
		else if (e.getKeyCode() == VK_W && e.getModifiersEx() == CTRL_DOWN_MASK) exit(0);
	}

	void released(KeyEvent e) {
		if ((e.getKeyCode() == VK_LEFT) || (e.getKeyCode() == VK_RIGHT)) setXA(0);
	}

	void updatePosition() {
		if (((getX() + getXA()) >= 0) && ((getX() + getXA()) < (game.getFrame().getWidth() - getWidth())))
			setX(getX() + getXA());
	}

	public void paint(Graphics g) {
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}