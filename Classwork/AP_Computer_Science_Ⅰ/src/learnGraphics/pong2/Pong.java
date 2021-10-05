package learnGraphics.pong2;

import javax.swing.*;

import static java.awt.Color.white;

public class Pong extends JFrame {
	private final PongPanel panel;

	private Pong() {
		setSize(700, 450);
		setTitle("Pong");
		setBackground(white);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new PongPanel(this);
		add(panel);
	}

	public static void main(String[] args) {
		new Pong();
	}

	PongPanel getPanel() {
		return panel;
	}
}
