package objectOriented.gridWorld.projects.myProjects.bugs.jumper;

import info.gridworld.actor.Bug;

class EBug extends Bug {
	private final int steps;
	private final int sideLength;

	public EBug(int length) {
		steps = 0;
		sideLength = length;
	}

	public void act() {
		for (int a = 0; a < 2; a++) {
			setDirection(270);
			for (int j = 0; j < 2; j++) {
				for (int i = 0; i < sideLength; i++)
					move();
				for (int i = 0; i < 2; i++)
					turn();
			}
			for (int i = 0; i < sideLength; i++)
				move();
		}
		for (int a = 0; a < 2; a++) {
			while (getDirection() != 270)
				turn();
			for (int j = 0; j < 2; j++) {
				for (int i = 0; i < sideLength; i++)
					move();
				for (int i = 0; i < 6; i++)
					turn();
			}
			for (int i = 0; i < sideLength; i++)
				move();
		}
	}
}
