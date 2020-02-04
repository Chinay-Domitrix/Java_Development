package objectOriented.gridWorld.projects.myProjects.bugs.jumper;

import info.gridworld.actor.Bug;

import static info.gridworld.grid.Location.SOUTHEAST;

/**
 * This code made a bug make a box and cross pattern inside.
 * It is run by turning directions using a int variable
 * And it runs the length that this passed into the constructor
 * It starts initially at the SouthEast direction
 * There is a main if statement that is supposed to make sure the
 * program runs for the number of times told by the user
 * It takes in a length parameters between 5 and 10 to make a shuriken looking object
 */
class ShurikenBug extends Bug {
	private int steps;
	private int segment;
	private final int sideLength;
	private final int times;
	private final int turnsAtEnd = 6;
	private int dir = 135;
	private boolean firstTime = true;
	private int count = 0;

	public ShurikenBug(int length, int times) {
		setDirection(SOUTHEAST);
		segment = 1;
		sideLength = length;
		steps = sideLength;
		this.times = times;
	}

	public void act() {
		if (segment < times) {
			if (steps < sideLength && canMove()) {
				move();
				steps++;
			} else if ((segment % 2 == 1) && (!firstTime)) {
				setDirection(dir);
				steps = 0;
				segment++;
			} else if (segment % 2 == 0) {
				dir += 45;
				setDirection(dir);
				steps = sideLength / 2;
				segment++;
			}
			firstTime = false;
		} else if ((segment == times) && (count < turnsAtEnd)) {
			if (count != turnsAtEnd - 1) {
				turn();
				count++;
			} else if (count == turnsAtEnd - 1) {
				segment = 1;
				count = 0;
			}
		}
	}
}