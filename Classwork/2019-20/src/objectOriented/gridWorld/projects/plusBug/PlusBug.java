package objectOriented.gridWorld.projects.plusBug;

import objectOriented.gridWorld.framework.info.gridworld.actor.Bug;
import objectOriented.gridWorld.framework.info.gridworld.grid.Location;

import java.util.Objects;

class PlusBug extends Bug {
	private int steps;
	private final int stemSize;
	private final Location origin;

	PlusBug(int stemSize, Location origin) {
		steps = 0;
		this.stemSize = stemSize;
		this.origin = origin;
	}

	public void act() {
		if (steps < stemSize) {
			if (canMove()) {
				move();
				steps++;
			} else {
				if (Objects.equals(origin, this.getLocation())) for (int i = 0; i < 2; i++) turn();
				else for (int i = 0; i < 4; i++) turn();
				steps = 0;
			}
		} else {
			if (Objects.equals(origin, this.getLocation())) for (int i = 0; i < 2; i++) turn();
			else for (int i = 0; i < 4; i++) turn();
			steps = 0;
		}
	}
}