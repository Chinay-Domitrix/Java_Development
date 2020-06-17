package learnGraphics.pong;

import java.awt.*;

class Ball extends Sprite {
	Ball() {
		super(0, 0, 1, 1, 30, 30);
	}

	void updatePosition() {
		setX(getX() + getXA());
		setY(getY() + getYA());
	}

	public void paint(Graphics g) {
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}
