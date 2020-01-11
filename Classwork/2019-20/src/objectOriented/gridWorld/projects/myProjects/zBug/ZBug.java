package objectOriented.gridWorld.projects.myProjects.zBug;

import info.gridworld.actor.Bug;

import static info.gridworld.grid.Location.EAST;
import static info.gridworld.grid.Location.SOUTHWEST;

/**
 * A ZBug traces out a Z pattern of a given size.
 */
public class ZBug extends Bug {
	private int segmentLength; // the number of flowers in each segment
	private int steps; // the number of steps in the current side
	private int segment; // which segment of the Z the ZBug is on

	/**
	 * Constructs a ZBug that traces a Z pattern in which each segment
	 * of the Z has the given length
	 * When the Z is drawn, the ZBug stops.
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
			switch (segment) {
				case 1:
					setDirection(SOUTHWEST);
					steps = 0;
					segment++;
					break;
				case 2:
					setDirection(EAST);
					steps = 0;
					segment++;
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + segment);
			}
		}
	}
} 