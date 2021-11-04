package objectOriented.gridWorld.projects.myProjects.bugs.zBug;

import objectOriented.gridWorld.framework.info.gridworld.actor.Bug;

import static objectOriented.gridWorld.framework.info.gridworld.grid.Location.EAST;
import static objectOriented.gridWorld.framework.info.gridworld.grid.Location.SOUTHWEST;

/**
 * A ZBug traces out a Z pattern of a given size.
 */
class ZBug extends Bug {
	private final int segmentLength; // the number of flowers in each segment
	private int steps; // the number of steps in the current side
	private int segment; // which segment of the Z the ZBug is on

	/**
	 * Constructs a ZBug that traces a Z pattern in which each segment of the Z has
	 * the given length When the Z is drawn, the ZBug stops.
	 *
	 * @param length the segment length
	 */
	public ZBug(int length) {
		setDirection(EAST);
		steps = 0;
		segment = 1;
		segmentLength = length;
	}

	public void act() {
		if ((segment <= 3) && (steps < segmentLength)) {
			if (canMove()) {
				move();
				steps++;
			}
		} else {
			if (segment == 1) {
				setDirection(SOUTHWEST);
				steps = 0;
				segment++;
			} else if (segment == 2) {
				setDirection(EAST);
				steps = 0;
				segment++;
			} else
				throw new IllegalStateException("Unexpected value: " + segment);
		}
	}
}
