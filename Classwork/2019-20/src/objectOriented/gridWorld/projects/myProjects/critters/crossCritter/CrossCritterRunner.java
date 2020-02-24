package objectOriented.gridWorld.projects.myProjects.critters.crossCritter;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

import static java.awt.Color.GREEN;
import static java.awt.Color.PINK;

public class CrossCritterRunner {
	public static void main(String[] args) {
		var world = new ActorWorld();
		for (int i = 0; i < 10; i++) for (int j = 0; j < 10; j++) world.add(new Location(i, j), new Flower(PINK));
		world.add(new Location(4, 4), new CrossCritter(GREEN));
		world.show();
	}
}