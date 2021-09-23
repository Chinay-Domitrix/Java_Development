package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.projects.critters.chameleonCritter;

/*
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

import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Actor;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Critter;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.grid.Location;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Objects.requireNonNull;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ChameleonCritter extends Critter {
	/**
	 * Randomly selects a neighbor and changes this critter's color to be the
	 * same as that neighbor's. If there are no neighbors, no action is taken.
	 */
	public void processActors(@NotNull ArrayList<Actor> actors) {
		assert requireNonNull(actors).size() != 0;
		setColor(actors.get(new Random().nextInt(actors.size())).getColor());
	}

	/**
	 * Turns towards the new location as it moves.
	 */
	public void makeMove(Location loc) {
		setDirection(getLocation().getDirectionToward(loc));
		super.makeMove(loc);
	}
}
