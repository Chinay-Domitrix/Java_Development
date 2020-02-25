package objectOriented.gridWorld.projects.myProjects.critters.caseStudyWork.chameleonCritters.chameleonKid;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;

import static java.awt.Color.*;

class ChameleonKidRunner {
	public static void main(String[] args) {
		var world = new ActorWorld();
		world.add(new Rock(BLUE));
		world.add(new Rock(PINK));
		world.add(new Rock(RED));
		world.add(new Rock(YELLOW));
		world.add(new ChameleonKid());
		world.add(new ChameleonKid());
		world.show();
	}
}