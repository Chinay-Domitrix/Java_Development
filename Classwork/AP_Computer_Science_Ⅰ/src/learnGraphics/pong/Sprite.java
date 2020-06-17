package learnGraphics.pong;

import java.awt.*;

class Sprite {
	private final int width, height;
	private int x, y, xa, ya;

	Sprite(int x, int y, int xa, int ya, int width, int height) {
		this.x = x;
		this.y = y;
		this.xa = xa;
		this.ya = ya;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	void setY(int y) {
		this.y = y;
	}

	int getXA() {
		return xa;
	}

	void setXA(int xa) {
		this.xa = xa;
	}

	int getYA() {
		return ya;
	}

	void setYA(int ya) {
		this.ya = ya;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}