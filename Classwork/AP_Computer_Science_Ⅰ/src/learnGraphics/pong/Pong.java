package Classwork.AP_Computer_Science_Ⅰ.src.learnGraphics.pong;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Pong extends Thread {
	private static final Dimension dimension = new Dimension(900, 500);
	private final Thread thread = new Thread(this);
	private final JFrame frame;

	private Pong() {
		frame = new JFrame("Pong");
	}

	public static void main(String[] args) {
		new Pong().thread.start();
	}

	@Override
	public void run() {
		frame.setSize(dimension);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.add(new PongPanel(this));
		frame.setVisible(true);
	}

	public Thread getThread() {
		return thread;
	}

	public JFrame getFrame() {
		return frame;
	}
}
