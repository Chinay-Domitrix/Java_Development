package objectOriented.gridWorld.projects.myProjects.critters.medusaCritter;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

import static java.awt.Color.*;

public class MedusaCritterRunner {
	public static void main(String[] args) {
		var world = new ActorWorld();
		world.add(new Location(0, 1), new Bug());
		world.add(new Location(4, 9), new Bug());
		world.add(new Location(2, 2), new Actor());
		world.add(new Location(3, 4), new Flower(PINK));
		world.add(new Location(4, 8), new Flower(MAGENTA));
		world.add(new Location(2, 7), new Flower(YELLOW));
		var m = new MedusaCritter();
		m.setColor(GREEN);
		world.add(new Location(9, 4), m);
		world.show();
	}
}