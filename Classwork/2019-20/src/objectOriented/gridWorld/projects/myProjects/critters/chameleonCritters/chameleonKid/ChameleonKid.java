package objectOriented.gridWorld.projects.myProjects.critters.chameleonCritters.chameleonKid;

import info.gridworld.actor.Actor;
import objectOriented.gridWorld.projects.critters.chameleonCritters.chameleonCritter.ChameleonCritter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static info.gridworld.grid.Location.HALF_CIRCLE;

class ChameleonKid extends ChameleonCritter {
	@Override
	public void processActors(@NotNull ArrayList<Actor> actors) {
		var colors = new ArrayList<Color>();
		if (getGrid().get(getLocation().getAdjacentLocation(getDirection())) == null)
			colors.add(getGrid().get(getLocation().getAdjacentLocation(getDirection())).getColor());
		if (getGrid().get(getLocation().getAdjacentLocation(getDirection() + HALF_CIRCLE)) == null)
			colors.add(getGrid().get(getLocation().getAdjacentLocation(getDirection() + HALF_CIRCLE)).getColor());
		setColor(colors.get(new Random().nextInt() << 1));
	}
}