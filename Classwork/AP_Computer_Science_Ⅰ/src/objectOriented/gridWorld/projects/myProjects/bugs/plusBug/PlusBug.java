package objectOriented.gridWorld.projects.myProjects.bugs.plusBug;

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

import java.util.Objects;

import static java.lang.Thread.sleep;
import static java.util.stream.IntStream.range;

public class PlusBug extends Bug {
	private final int stemSize;
	private final Location origin;
	private int steps;

	protected PlusBug() {
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
			if (Objects.equals(origin, getLocation())) range(0, 2).forEachOrdered(i -> {
				turn();
				try {
					sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			else range(0, 4).forEachOrdered(i -> {
				turn();
				try {
					sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			steps = 0;
		}
	}
}