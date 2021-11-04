package objectOriented.gridWorld.projects.myProjects.critters.caseStudyWork.rockHound;

import objectOriented.gridWorld.framework.info.gridworld.actor.Actor;
import objectOriented.gridWorld.framework.info.gridworld.actor.Critter;
import objectOriented.gridWorld.framework.info.gridworld.actor.Rock;

import java.util.ArrayList;

public class RockHound extends Critter {
	@Override
	public void processActors(ArrayList<Actor> actors) {
		for (Actor x : actors)
			if (x instanceof Rock)
				getGrid().get(x.getLocation()).removeSelfFromGrid();
	}
}
