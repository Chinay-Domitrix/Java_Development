package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.projects.myProjects.critters.caseStudyWork.rockHound;

import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.ActorWorld;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Rock;

import static java.awt.Color.*;

public class RockHoundRunner {
	public static void main(String[] args) {
		var world = new ActorWorld();
		world.add(new Rock(BLUE));
		world.add(new Rock(PINK));
		world.add(new Rock(RED));
		world.add(new Rock(YELLOW));
		world.add(new RockHound());
		world.add(new RockHound());
		world.show();
	}
}
