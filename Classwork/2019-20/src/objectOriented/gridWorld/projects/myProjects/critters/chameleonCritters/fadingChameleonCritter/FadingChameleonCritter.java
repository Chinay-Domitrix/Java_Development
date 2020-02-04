package objectOriented.gridWorld.projects.myProjects.critters.chameleonCritters.fadingChameleonCritter;

import info.gridworld.actor.Actor;
import objectOriented.gridWorld.projects.critters.chameleonCritters.chameleonCritter.ChameleonCritter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

class FadingChameleonCritter extends ChameleonCritter {
	@Override
	public void processActors(@NotNull ArrayList<Actor> actors) {
		setColor((actors.size() == 0) ? getColor().darker() : actors.get(new Random().nextInt() * actors.size()).getColor());
	}
}
