package Classwork.mobileApp.src.anonymousClassesDemos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Demo4 extends JPanel {
	public Demo4() {
		var frame = new JFrame();
		frame.add(this);
		frame.setVisible(false);
		frame.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		setFocusable(true);
		addMouseListener(new MouseListener() {
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
				while (true) System.out.println("Mouse Coordinates: (" + e.getX() + ", " + e.getY() + ')');
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Demo4();
	}
}
