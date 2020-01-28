package objectOriented.gridWorld.projects.myProjects.rock135Bug;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;


public class Rock135BugRunner {
	public static void main(String[] args) {
		var world = new ActorWorld();
		world.add(new Rock());
		world.add(new Rock());
		world.add(new Location(0, 7), new Actor());
		world.add(new Location(6, 4), new Rock135Bug());
		world.show();
	}
}