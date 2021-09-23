package Classwork.AP_Computer_Science_Ⅰ.src.learnGraphics.blockBreak.blockBreak2;/* This is a container object that can hold additional properties assosciated with a Rectangle */

import java.awt.*;

public class Block {
	private Rectangle r;
	private boolean goal;
	private int x, y, w, h;

	Block(int x, int y, int w, int h, boolean goal) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.goal = goal;
		r = new Rectangle(x, y, w, h);
	}

	public Block(int x, int y, int w, int h) {
		this(x, y, w, h, false);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	boolean isGoal() {
		return goal;
	}

	void setGoal(boolean g) {
		goal = g;
	}

	public Rectangle getR() {
		return r;
	}

	void setPos(int x, int y) {
		this.x = x;
		this.y = y;
		r = new Rectangle(x, y, w, h);
	}

	public void setSize(int w, int h) {
		this.w = w;
		this.h = h;
		r = new Rectangle(x, y, w, h);
	}
}
