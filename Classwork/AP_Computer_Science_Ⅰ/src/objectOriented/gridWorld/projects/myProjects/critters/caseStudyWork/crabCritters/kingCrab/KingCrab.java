package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.projects.myProjects.critters.caseStudyWork.crabCritters.kingCrab;

import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.grid.Location;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.projects.critters.CrabCritter;
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
