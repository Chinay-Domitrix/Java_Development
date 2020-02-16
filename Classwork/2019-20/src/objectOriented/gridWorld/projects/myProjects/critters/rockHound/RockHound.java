package objectOriented.gridWorld.projects.myProjects.critters.rockHound;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

public class RockHound extends Critter {
	@Override
	public void processActors(ArrayList<Actor> actors) {
		for (Actor x : requireNonNull(actors)) {
			if (x instanceof Rock) getGrid().get(x.getLocation()).removeSelfFromGrid();
		}
	}
}