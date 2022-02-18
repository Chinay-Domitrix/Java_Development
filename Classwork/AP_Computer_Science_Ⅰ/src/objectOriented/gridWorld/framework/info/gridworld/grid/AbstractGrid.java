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
 * @author Cay Horstmann
 */

package objectOriented.gridWorld.framework.info.gridworld.grid;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

import static info.gridworld.grid.Location.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;

/**
 * <code>AbstractGrid</code> contains the methods that are common to grid
 * implementations. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public abstract class AbstractGrid<E> implements Grid<E> {
	public ArrayList<E> getNeighbors(Location loc) {
		return getOccupiedAdjacentLocations(loc).stream().map(this::get).collect(toCollection(ArrayList::new));
	}

	public ArrayList<Location> getValidAdjacentLocations(Location loc) {
		ArrayList<Location> locs = new ArrayList<>();
		int d = NORTH;
		for (int i = 0; i < (FULL_CIRCLE / HALF_RIGHT); i++) {
			if (isValid(loc.getAdjacentLocation(d)))
				locs.add(loc.getAdjacentLocation(d));
			d += HALF_RIGHT;
		}
		return locs;
	}

	public ArrayList<Location> getEmptyAdjacentLocations(Location loc) {
		return getValidAdjacentLocations(loc).stream().filter(neighborLoc -> get(neighborLoc) == null).collect(toCollection(ArrayList::new));
	}

	public ArrayList<Location> getOccupiedAdjacentLocations(Location loc) {
		return getValidAdjacentLocations(loc).stream().filter(neighborLoc -> get(neighborLoc) != null).collect(toCollection(ArrayList::new));
	}

	/**
	 * Creates a string that describes this grid.
	 *
	 * @return a string with descriptions of all objects in this grid (not
	 * necessarily in any particular order), in the format {loc=obj,
	 * loc=obj, ...}
	 */
	public String toString() {
		return getOccupiedLocations().stream().map(loc -> loc + "=" + get(loc)).collect(joining(", ", "{", "}"));
	}
}
