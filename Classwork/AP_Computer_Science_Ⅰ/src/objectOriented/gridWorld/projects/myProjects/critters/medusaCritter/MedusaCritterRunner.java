package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.projects.myProjects.critters.medusaCritter;

import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Actor;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.ActorWorld;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Bug;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Flower;

import static java.awt.Color.*;

public class MedusaCritterRunner {
	public static void main(String[] args) {
		var world = new ActorWorld();
		world.add(new Bug());
		world.add(new Bug());
		world.add(new Actor());
		world.add(new Flower(PINK));
		world.add(new Flower(MAGENTA));
		world.add(new Flower(YELLOW));
		world.add(new MedusaCritter(GREEN));
		world.show();
	}
}
