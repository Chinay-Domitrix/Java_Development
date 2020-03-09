package hackPHS2019.hackPHS2019;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static java.awt.Color.blue;
import static java.awt.Font.PLAIN;
import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

class ArcadeLogic {
	private static Container contentPane;
	private JFrame frame = new JFrame();

	private ArcadeLogic() {
		this(contentPane);
	}

	private ArcadeLogic(Container contentPane) {
		ArcadeLogic.contentPane = contentPane;
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.setVisible(true);
		var b = new JButton[]{new JButton("Tic-Tac-Toe"), new JButton("Hangman"), new JButton("Rock Paper Scissors Lizard Spock")};
		var bFont = new Font("Comic Sans MS", PLAIN, 20);
		var x = stream(b);
		x.forEachOrdered(jButton -> {
			jButton.setBounds(100, 150, 100, 100);
			jButton.setForeground(blue);
			jButton.setFont(bFont);
			setJButtonTextPosition(jButton);
		});
		x.forEachOrdered(i -> ArcadeLogic.contentPane.add(i));
		b[0].addActionListener(this::actionPerformedB1);
		b[1].addActionListener(this::actionPerformedB2);
		b[2].addActionListener(this::actionPerformedB3);
	}

	public static void main(String[] args) {
		new ArcadeLogic();
	}

	private void actionPerformedB1(ActionEvent e) {
		frame.setVisible(false);
		new TicTacToeProject();
	}

	private void actionPerformedB2(ActionEvent e) {
		frame.setVisible(false);
		try {
			new HangmanProject();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	private void actionPerformedB3(ActionEvent e) {
		frame.setVisible(false);
		try {
			new RockPaperScissorsLizardSpock();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void setJButtonTextPosition(@NotNull JButton jButton) {
		requireNonNull(jButton).setHorizontalTextPosition(0);
		requireNonNull(jButton).setVerticalTextPosition(0);
	}
}
