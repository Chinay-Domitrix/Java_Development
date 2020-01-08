package objectOriented.gridWorld.projects.plusBug;

import objectOriented.gridWorld.framework.info.gridworld.actor.ActorWorld;
import objectOriented.gridWorld.framework.info.gridworld.grid.Location;

import static java.awt.Color.BLUE;

public class PlusBugRunner {
	public static void main(String[] args) {
		var world = new ActorWorld();
		var loc = new Location[]{new Location(1, 1), new Location(3, 3), new Location(6, 6)};
		var plusBug = new PlusBug[]{new PlusBug(1, loc[0]), new PlusBug(2, loc[1]), new PlusBug(3, loc[2])};
		for (var bug : plusBug) bug.setColor(BLUE);
		for (int i = 0; i < plusBug.length; i++) world.add(loc[i], plusBug[i]);
		world.show();
	}
}
