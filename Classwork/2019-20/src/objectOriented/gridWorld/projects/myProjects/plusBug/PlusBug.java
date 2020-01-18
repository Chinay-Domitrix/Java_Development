package objectOriented.gridWorld.projects.myProjects.plusBug;

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

import java.util.Objects;

import static java.lang.Thread.sleep;

public class PlusBug extends Bug {
	private final int stemSize;
	private final Location origin;
	private int steps;

	public PlusBug() {
		this(1, new Location(1, 1));
	}

	protected PlusBug(int stemSize, Location origin) {
		steps = 0;
		this.stemSize = stemSize;
		this.origin = origin;
	}

	public void act() {
		if (steps < stemSize && canMove()) {
			move();
			steps++;
		} else {
			if (Objects.equals(origin, getLocation())) for (int i = 0; i < 2; i++) {
				turn();
				try {
					sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else for (int i = 0; i < 4; i++) {
				turn();
				try {
					sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			steps = 0;
		}
	}
}