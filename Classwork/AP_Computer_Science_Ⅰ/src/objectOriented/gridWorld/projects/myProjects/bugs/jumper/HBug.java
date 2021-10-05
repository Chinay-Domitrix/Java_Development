package objectOriented.gridWorld.projects.myProjects.bugs.jumper;

import objectOriented.gridWorld.framework.info.gridworld.actor.Bug;

import static java.lang.Thread.sleep;
import static java.util.stream.IntStream.of;
import static objectOriented.gridWorld.framework.info.gridworld.grid.Location.SOUTH;

class HBug extends Bug {
	private final int length;
	private int steps;
	private int count = 0;

	HBug(int length) {
		assert (length % 2) != 0;
		this.length = length - 1;
		this.setDirection(SOUTH);
	}

	@Override
	public void act() {
		if (steps < length && count < 8) {
			move();
			steps++;
		} else {
			if (of(0, 2, 5).anyMatch(v -> count == v)) {
				for (int i = 0; i < 4; i++) {
					turn();
					try {
						sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				count++;
			} else if ((count == 1) || (count == 6)) {
				count++;
			} else if ((count == 3) || (count == 4)) {
				for (int i = 0; i < 2; i++) {
					turn();
					try {
						sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				count++;
			} else
				count++;
			steps = 0;
		}
	}
}
