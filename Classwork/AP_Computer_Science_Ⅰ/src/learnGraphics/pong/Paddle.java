package learnGraphics.pong;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import static java.awt.event.KeyEvent.*;
import static java.lang.System.exit;

class Paddle extends Sprite {
	private final Pong game;

	Paddle(@NotNull Pong pong, int y) {
		super((pong.getFrame().getWidth() - 60) / 2, y, 0, 0, 60, 10);
		game = pong;
	}

	void pressed(@NotNull KeyEvent e) {
		if (e.getKeyCode() == VK_LEFT)
			setXA(-2);
		else if (e.getKeyCode() == VK_RIGHT)
			setXA(2);
		else if (e.getKeyCode() == VK_W && e.getModifiersEx() == CTRL_DOWN_MASK)
			exit(0);
	}

	void released(@NotNull KeyEvent e) {
		if ((e.getKeyCode() == VK_LEFT) || (e.getKeyCode() == VK_RIGHT))
			setXA(0);
	}

	void updatePosition() {
		if (((getX() + getXA()) >= 0) && ((getX() + getXA()) < (game.getFrame().getWidth() - getWidth())))
			setX(getX() + getXA());
	}

	public void paint(@NotNull Graphics g) {
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}
