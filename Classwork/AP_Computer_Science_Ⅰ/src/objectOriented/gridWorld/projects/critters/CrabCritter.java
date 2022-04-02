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
import info.gridworld.grid.Location;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static java.awt.Color.RED;
import static java.lang.Math.random;
import static java.util.stream.Collectors.toCollection;
import static objectOriented.gridWorld.framework.info.gridworld.grid.Location.*;

/**
 * A <code>CrabCritter</code> looks at a limited set of neighbors when it eats
 * and moves. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class CrabCritter extends Critter {
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
		return getLocationsInDirections(new int[]{AHEAD, HALF_LEFT, HALF_RIGHT}).stream()
				.filter(loc -> getGrid().get(loc) != null).map(loc -> getGrid().get(loc))
				.collect(toCollection(ArrayList::new));
	}

	/**
	 * @return list of empty locations immediately to the right and to the left
	 */
	public ArrayList<Location> getMoveLocations() {
		ArrayList<Location> locationArrayList = new ArrayList<>();
		for (Location loc : getLocationsInDirections(new int[]{LEFT, RIGHT})) {
			if (getGrid().get(loc) == null) {
				locationArrayList.add(loc);
			}
		}
		return locationArrayList;
	}

	/**
	 * If the crab critter doesn't move, it randomly turns left or right.
	 */
	public void makeMove(@NotNull Location loc) {
		if (loc.equals(getLocation())) {
			double r = random();
			int angle = (r < 0.5) ? LEFT : RIGHT;
			setDirection(getDirection() + angle);
		} else
			super.makeMove(loc);
	}

	/**
	 * Finds the valid adjacent locations of this critter in different directions.
	 *
	 * @param directions - an array of directions (which are relative to the current
	 *                   direction)
	 * @return a set of valid locations that are neighbors of the current location
	 * in the given directions
	 */
	@NotNull
	private ArrayList<Location> getLocationsInDirections(int[] directions) {
		var loc = getLocation();
		ArrayList<Location> locationArrayList = new ArrayList<>();
		for (int d : directions) {
			if (getGrid().isValid(loc.getAdjacentLocation(getDirection() + d))) {
				locationArrayList.add(loc.getAdjacentLocation(getDirection() + d));
			}
		}
		return locationArrayList;
	}
}
