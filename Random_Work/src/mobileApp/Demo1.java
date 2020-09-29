package mobileApp;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Demo1 extends JPanel implements KeyListener {
	private Demo1() {
		var frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		frame.setSize(800, 400);
		setFocusable(true);
		addKeyListener(this);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		var x =new Demo1();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("3 " + e.getKeyChar());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("2 " + e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("1 " + e.getKeyChar());
	}
}
