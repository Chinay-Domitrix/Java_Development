package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.projects.myProjects.critters.floristCritter;

import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.ActorWorld;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Bug;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Flower;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Rock;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.grid.Location;

import static java.awt.Color.*;

public class FloristCritterRunner {
	public static void main(String[] args) {
		var world = new ActorWorld();
		world.add(new Location(2, 8), new Flower(BLUE));
		world.add(new Location(5, 5), new Flower(PINK));
		world.add(new Location(1, 5), new Flower(CYAN));
		world.add(new Flower(PINK));
		world.add(new Flower(ORANGE));
		world.add(new Flower(MAGENTA));
		world.add(new Flower(GREEN));
		for (int i = 0; i < 5; i++) world.add(new Rock());
		world.add(new Bug());
		world.add(new Location(5, 8), new FloristCritter());
		world.show();
	}
}
