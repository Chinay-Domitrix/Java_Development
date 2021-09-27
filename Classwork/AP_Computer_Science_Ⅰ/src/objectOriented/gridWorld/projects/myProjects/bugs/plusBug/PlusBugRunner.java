package objectOriented.gridWorld.projects.myProjects.bugs.plusBug;

import objectOriented.gridWorld.framework.info.gridworld.actor.ActorWorld;
import objectOriented.gridWorld.framework.info.gridworld.grid.Location;
import objectOriented.gridWorld.framework.info.gridworld.grid.UnboundedGrid;

public class PlusBugRunner {
	private PlusBugRunner() {
		var world = new ActorWorld(new UnboundedGrid<>());
		var locations = new Location[] { new Location(1, 1), new Location(3, 3), new Location(6, 6) };
		var plusBugs = new PlusBug[] { new PlusBug(1, locations[0]), new PlusBug(2, locations[1]),
				new PlusBug(3, locations[2]) };
		for (int i = 0; i < plusBugs.length; i++)
			world.add(locations[i], plusBugs[i]);
		world.show();
	}

	public static void main(String[] args) {
		new PlusBugRunner();
	}
}
