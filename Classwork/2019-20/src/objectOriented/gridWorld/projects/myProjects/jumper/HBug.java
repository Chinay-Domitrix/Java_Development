package objectOriented.gridWorld.projects.myProjects.jumper;

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

import static info.gridworld.grid.Location.SOUTH;
import static java.lang.Thread.sleep;

public class HBug extends Bug {
	private final Location origin;
	private final int length;
	private int steps;
	private int count = 0;

	HBug(int length) {
		assert (length % 2) != 0;
		this.length = length - 1;
		this.setDirection(SOUTH);
		origin = this.getLocation();
	}

	@Override
	public void act() {
		if (steps < length && count < 8) {
			move();
			steps++;
		} else {
			if ((count == 0) || (count == 2 || count == 5)) {
				for (int i = 0; i < 4; i++) {
					turn();
					try {
						sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				count++;
			} else if (count == 1 || count == 6) {
				count++;
			} else if (count == 3 || count == 4) {
				for (int i = 0; i < 2; i++) {
					turn();
					try {
						sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				count++;
			} else count++;
			steps = 0;
		}
	}
}