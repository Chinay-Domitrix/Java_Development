package objectOriented.gridWorld.projects.myProjects.critters.diagonalCritter;

import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.IntStream.rangeClosed;

public class DiagonalCritter extends Critter {
	@Override
	public ArrayList<Location> getMoveLocations() {
		var moveLocations = super.getMoveLocations();
		var diagonals = rangeClosed(1, 4)
				.mapToObj(i -> getLocation().getAdjacentLocation((getDirection() - 45) + (90 * i)))
				.collect(toCollection(ArrayList::new));
		var returned = new ArrayList<Location>();
		var counter = 0;
		for (var x : moveLocations)
			if (x.getDirectionToward(diagonals.get(counter)) % 45 == 0
					&& x.getDirectionToward(diagonals.get(counter)) % 90 != 0) {
				returned.add(x);
				counter++;
			}
		return returned;
	}
}
