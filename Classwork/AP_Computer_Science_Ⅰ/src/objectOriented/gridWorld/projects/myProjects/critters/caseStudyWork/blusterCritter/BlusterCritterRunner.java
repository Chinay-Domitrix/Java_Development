package objectOriented.gridWorld.projects.myProjects.critters.caseStudyWork.blusterCritter;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

import static java.awt.Color.*;

public class BlusterCritterRunner {
	public static void main(String[] args) {
		var world = new ActorWorld();
		world.add(/* new Location(7, 8), */new Rock());
		world.add(/* new Location(3, 3), */new Rock());
		world.add(/* new Location(2, 8), */new Flower(BLUE));
		world.add(/* new Location(5, 5), */new Flower(PINK));
		world.add(/* new Location(1, 5), */new Flower(RED));
		world.add(/* new Location(7, 2), */new Flower(YELLOW));
		world.add(/* new Location(4, 4), */new BlusterCritter(1));
		world.add(/* new Location(5, 8), */new BlusterCritter(1, RED));
		world.show();
	}
}
