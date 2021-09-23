package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.projects.myProjects.critters.caseStudyWork.chameleonCritters.fadingChameleonCritter;

import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Actor;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.projects.critters.chameleonCritter.ChameleonCritter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class FadingChameleonCritter extends ChameleonCritter {
	@Override
	public void processActors(@NotNull ArrayList<Actor> actors) {
		setColor((actors.size() == 0) ? getColor().darker() : actors.get(new Random().nextInt(actors.size())).getColor());
	}
}
