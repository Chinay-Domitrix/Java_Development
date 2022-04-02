package objectOriented.gridWorld.projects.myProjects.critters.crossCritter;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;

public class CrossCritter extends Critter {
	private CrossCritter() {
		super();
	}

	CrossCritter(Color color) {
		this();
		setColor(color);
	}

	@Override
	public ArrayList<Actor> getActors() {
		// The ArrayList of Actors to be removed
		ArrayList<Actor> removable = new ArrayList<>();
		// Adds every Actor in the same column as the CrossCritter to the ArrayList
		// removable
		for (int i = 0; i < getGrid().getNumRows(); i++)
			removable.add(getGrid().get(new Location(i, getLocation().getCol())));
		// Adds every Actor in the same row as the CrossCritter to the ArrayList
		// removable
		for (int i = 0; i < getGrid().getNumCols(); i++)
			removable.add(getGrid().get(new Location(getLocation().getRow(), i)));
		// Removes the CrossCritter from the ArrayList of Actors to be removed
		while (removable.contains(getGrid().get(getLocation())))
			removable.remove(getGrid().get(getLocation()));
		// Every Actor in the same row and column as the CrossCritter gets removed
		for (Actor actorInstance : removable)
			if (actorInstance != null)
				actorInstance.removeSelfFromGrid();
		// Returns super.getActors() in order for processActors() to work properly
		return super.getActors();
	}
}
