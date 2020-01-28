package objectOriented.gridWorld.projects.critters;

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

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static info.gridworld.grid.Location.*;
import static java.awt.Color.RED;
import static java.lang.Math.random;

/**
 * A <code>CrabCritter</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
class CrabCritter extends Critter {
	public CrabCritter() {
		setColor(RED);
	}

	/**
	 * A crab gets the actors in the three locations immediately in front, to its
	 * front-right and to its front-left
	 *
	 * @return a list of actors occupying these locations
	 */
	public ArrayList<Actor> getActors() {
		var actors = new ArrayList<Actor>();
		int[] dirs = {AHEAD, HALF_LEFT, HALF_RIGHT};
		for (var loc : getLocationsInDirections(dirs)) if (getGrid().get(loc) != null) actors.add(getGrid().get(loc));
		return actors;
	}

	/**
	 * @return list of empty locations immediately to the right and to the left
	 */
	public ArrayList<Location> getMoveLocations() {
		var locs = new ArrayList<Location>();
		int[] dirs = {LEFT, RIGHT};
		for (Location loc : getLocationsInDirections(dirs)) if (getGrid().get(loc) == null) locs.add(loc);
		return locs;
	}

	/**
	 * If the crab critter doesn't move, it randomly turns left or right.
	 */
	public void makeMove(@NotNull Location loc) {
		if (loc.equals(getLocation())) {
			double r = random();
			int angle;
			if (r < 0.5) angle = LEFT;
			else angle = RIGHT;
			setDirection(getDirection() + angle);
		} else
			super.makeMove(loc);
	}

	/**
	 * Finds the valid adjacent locations of this critter in different
	 * directions.
	 *
	 * @param directions - an array of directions (which are relative to the
	 *                   current direction)
	 * @return a set of valid locations that are neighbors of the current
	 * location in the given directions
	 */
	@NotNull
	private ArrayList<Location> getLocationsInDirections(@NotNull int[] directions) {
		var locs = new ArrayList<Location>();
		Grid<?> gr = getGrid();
		var loc = getLocation();
		for (int d : directions)
			if (gr.isValid(loc.getAdjacentLocation(getDirection() + d)))
				locs.add(loc.getAdjacentLocation(getDirection() + d));
		return locs;
	}
}
