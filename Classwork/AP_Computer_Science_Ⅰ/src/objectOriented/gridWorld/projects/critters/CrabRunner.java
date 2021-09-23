package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.projects.critters;/*
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
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Bug;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Flower;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Rock;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.grid.Location;

/**
 * This class runs a world that contains crab critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class CrabRunner {
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
		world.add(new Location(4, 5), new CrabCritter());
		world.add(new Location(6, 1), new CrabCritter());
		world.add(new Location(7, 4), new CrabCritter());
		world.show();
	}
}
