package maze;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Maze extends JPanel implements KeyListener, MouseListener {
	private final JFrame frame;
	private char[][] maze;

	private Maze() {
		frame = new JFrame();
		frame.add(this);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(1600, 1000);
		frame.setVisible(true);
		frame.addKeyListener(this);
		frame.addMouseListener(this);
		try (var scanner = new Scanner(new File("Classwork/dataStructures/src/maze/Maze.txt"))) {
			ArrayList<char[]> temp = new ArrayList<>();
			while (scanner.hasNextLine())
				temp.add(scanner.nextLine().toCharArray());
			maze = temp.toArray(new char[][]{});
		} catch (FileNotFoundException | NullPointerException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Maze();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
