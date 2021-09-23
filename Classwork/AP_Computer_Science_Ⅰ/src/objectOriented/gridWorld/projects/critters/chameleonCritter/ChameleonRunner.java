package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.projects.critters.chameleonCritter;/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.ActorWorld;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Rock;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.grid.Location;

import static java.awt.Color.*;

/**
 * This class runs a world that contains chameleon critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ChameleonRunner {
	public static void main(String[] args) {
		var world = new ActorWorld();
		world.add(new Location(7, 8), new Rock());
		world.add(new Location(3, 3), new Rock());
		world.add(new Location(2, 8), new Rock(BLUE));
		world.add(new Location(5, 5), new Rock(PINK));
		world.add(new Location(1, 5), new Rock(RED));
		world.add(new Location(7, 2), new Rock(YELLOW));
		world.add(new Location(4, 4), new ChameleonCritter());
		world.add(new Location(5, 8), new ChameleonCritter());
		world.show();
	}
}
