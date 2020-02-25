package objectOriented.gridWorld.projects.myProjects.bugs.zBug;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import static java.awt.Color.MAGENTA;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ZBugRunner {
	private ZBugRunner() {
		var world = new ActorWorld(new UnboundedGrid<>());      // NOTE: You can do this to default to an UnboundedGrid
		var zBug = new ZBug(5);
		zBug.setColor(MAGENTA);
		world.add(new Location(3, 3), zBug);            // Sets starting Location
		world.show();
	}

	public static void main(String[] args) {
		new ZBugRunner();
	}
}