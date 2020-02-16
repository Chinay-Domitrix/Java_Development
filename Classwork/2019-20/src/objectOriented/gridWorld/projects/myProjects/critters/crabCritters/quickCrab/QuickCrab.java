package objectOriented.gridWorld.projects.myProjects.critters.crabCritters.quickCrab;

import info.gridworld.grid.Location;
import objectOriented.gridWorld.projects.critters.CrabCritter;

import java.util.ArrayList;

public class QuickCrab extends CrabCritter {
	@Override
	public ArrayList<Location> getMoveLocations() {
		var returned = new ArrayList<>(super.getMoveLocations());
		super.getMoveLocations().stream().filter(x -> getGrid().isValid(x.getAdjacentLocation(getLocation().getDirectionToward(x))) && (getGrid().get(x.getAdjacentLocation(getLocation().getDirectionToward(x))) == null)).map(x -> x.getAdjacentLocation(getLocation().getDirectionToward(x))).forEachOrdered(returned::add);
		return returned;
	}
}