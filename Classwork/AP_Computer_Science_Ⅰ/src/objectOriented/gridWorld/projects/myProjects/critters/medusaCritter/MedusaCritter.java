package objectOriented.gridWorld.projects.myProjects.critters.medusaCritter;

import objectOriented.gridWorld.framework.info.gridworld.actor.Actor;
import objectOriented.gridWorld.framework.info.gridworld.actor.Critter;
import objectOriented.gridWorld.framework.info.gridworld.actor.Rock;
import objectOriented.gridWorld.framework.info.gridworld.grid.AbstractGrid;
import objectOriented.gridWorld.framework.info.gridworld.grid.BoundedGrid;
import objectOriented.gridWorld.framework.info.gridworld.grid.Grid;
import objectOriented.gridWorld.framework.info.gridworld.grid.Location;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.IntStream.rangeClosed;

public class MedusaCritter extends Critter {
	MedusaCritter(Color color) {
		super();
		this.setColor(color);
	}

	@Override
	public void processActors(@NotNull ArrayList<Actor> actors) {
		// Only runs if there is an Actor in the ArrayList, and only if that Actor is
		// not a Rock (otherwise the put method throws a bizarre NullPointerException)
		if ((actors.size() != 0) && !(actors.get(0) instanceof Rock)) {
			// Requires the Location of the actor to not be null, for obvious reasons
			assert actors.get(0).getLocation() != null;
			// Puts a Rock to replace the Actor at that Location
			getGrid().put(actors.get(0).getLocation(), new Rock(actors.get(0).getColor()));
		}
	}

	@Override
	public ArrayList<Actor> getActors() {
		// The upper bound for the loop below; requires the loop to only run for the
		// number of rows left above the MedusaCritter
		var bound = getGrid().getNumRows() - getLocation().getRow();
		// Checks if the Location is valid and if that Location contains an Actor. If
		// there is an Actor at that Location, it is added to the actors ArrayList.
		return rangeClosed(1, bound)
				.filter(i -> checkLocationValidity(new Location(getLocation().getRow() - i, getLocation().getCol()))
						&& getActor(new Location(getLocation().getRow() - i, getLocation().getCol())) != null)
				.mapToObj(i -> getGrid().get(new Location(getLocation().getRow() - i, getLocation().getCol())))
				.collect(toCollection(ArrayList::new));
	}

	/**
	 * This method is a convenience method which checks the validity of a
	 * {@link Location}
	 *
	 * @return the validity of the {@linkplain Location}
	 * @see Grid#isValid(Location)
	 * @see AbstractGrid#isValid(Location)
	 */
	private boolean checkLocationValidity(Location location) {
		return getGrid().isValid(location);
	}

	/**
	 * This method is a convenience method which gets the {@linkplain Actor} at a
	 * certain {@link Location}
	 *
	 * @return the {@linkplain Actor} at a {@linkplain Location}
	 * @see Grid#get(Location)
	 * @see BoundedGrid#get(Location)
	 */
	private Actor getActor(Location location) {
		return getGrid().get(location);
	}
}
