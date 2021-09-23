package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.projects.myProjects.bugs.zBug;

import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.ActorWorld;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.grid.Location;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.grid.UnboundedGrid;

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
