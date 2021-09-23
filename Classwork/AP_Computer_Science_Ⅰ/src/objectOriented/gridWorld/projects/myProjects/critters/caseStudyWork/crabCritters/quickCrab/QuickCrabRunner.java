package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.projects.myProjects.critters.caseStudyWork.crabCritters.quickCrab;

import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.ActorWorld;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Bug;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Flower;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Rock;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.grid.Location;

public class QuickCrabRunner {
	public static void main(String[] args) {
		var world = new ActorWorld();
		world.add(new Location(7, 5), new Rock());
		world.add(new Location(5, 4), new Rock());
		world.add(new Location(5, 7), new Rock());
		world.add(new Location(7, 3), new Rock());
		world.add(new Location(7, 8), new Flower());
		world.add(new Location(2, 2), new Flower());
		world.add(new Location(3, 5), new Flower());
		world.add(new Location(3, 8), new Flower());
		world.add(new Location(6, 5), new Bug());
		world.add(new Location(5, 3), new Bug());
		world.add(new Location(4, 5), new QuickCrab());
		world.add(new Location(6, 1), new QuickCrab());
		world.add(new Location(7, 4), new QuickCrab());
		world.show();
	}
}
