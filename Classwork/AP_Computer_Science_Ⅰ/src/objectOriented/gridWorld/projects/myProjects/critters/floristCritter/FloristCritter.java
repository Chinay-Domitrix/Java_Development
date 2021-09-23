package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.projects.myProjects.critters.floristCritter;

import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Actor;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Critter;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Flower;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Rock;

import java.util.ArrayList;

import static java.awt.Color.RED;
import static java.util.Objects.requireNonNull;

public class FloristCritter extends Critter {
	@Override
	public void processActors(ArrayList<Actor> actors) {
		for (var a : requireNonNull(actors)) {
			if (a instanceof Flower)
				getGrid().get(a.getLocation()).setColor(RED);
			else if (!(a instanceof Rock) && (a != null))
				a.removeSelfFromGrid();
		}
	}
}
