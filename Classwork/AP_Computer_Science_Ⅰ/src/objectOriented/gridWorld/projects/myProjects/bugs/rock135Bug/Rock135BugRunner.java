package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.projects.myProjects.bugs.rock135Bug;

import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Actor;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.ActorWorld;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Rock;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.grid.Location;


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
