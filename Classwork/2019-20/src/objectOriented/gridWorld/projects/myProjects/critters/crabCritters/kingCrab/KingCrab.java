package objectOriented.gridWorld.projects.myProjects.critters.crabCritters.kingCrab;

import info.gridworld.grid.Location;
import objectOriented.gridWorld.projects.critters.CrabCritter;
import org.jetbrains.annotations.NotNull;

public class KingCrab extends CrabCritter {
	@Override
	public void makeMove(@NotNull Location loc) {
		super.makeMove(loc);
		getGrid().getNeighbors(this.getLocation()).forEach(x -> {
			if (getGrid().isValid(x.getLocation().getAdjacentLocation(getLocation().getDirectionToward(x.getLocation()))))
				x.moveTo(x.getLocation().getAdjacentLocation(getLocation().getDirectionToward(x.getLocation())));
			else x.removeSelfFromGrid();
		});
	}
}