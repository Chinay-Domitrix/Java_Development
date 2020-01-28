package objectOriented.gridWorld.projects.myProjects.jumper;

import info.gridworld.actor.Bug;

public class CBug extends Bug {
	private int steps;
	private int sideLength;
	private int count;
	private int dir = 270;
	private int d2 = 1;

	public CBug(int length) {
		steps = 0;
		sideLength = length;
		count = 2;
		setDirection(dir);
	}

	public void act() {
		if (canMove() && (count < 5))
			if ((count % 2) == 0) if (steps < sideLength) move();
			else {
				count++;
				dir += (d2 * 90);
				setDirection(dir);
				steps = 0;
			}
			else if (steps < sideLength * 2) move();
			else {
				count++;
				dir += d2 * 90;
				setDirection(dir);
				steps = -1;
			}
		else {
			dir += 90 * d2;
			setDirection(dir);
			d2 *= -1;
			count = 2;
			steps = -1;
		}
		steps++;
	}
}