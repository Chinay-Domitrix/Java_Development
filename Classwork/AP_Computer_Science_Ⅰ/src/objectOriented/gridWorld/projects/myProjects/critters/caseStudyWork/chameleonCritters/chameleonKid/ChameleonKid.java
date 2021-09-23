package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.projects.myProjects.critters.caseStudyWork.chameleonCritters.chameleonKid;

import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Actor;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.projects.myProjects.critters.caseStudyWork.chameleonCritters.fadingChameleonCritter.FadingChameleonCritter;

import java.util.ArrayList;

import static Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.grid.Location.HALF_CIRCLE;

class ChameleonKid extends FadingChameleonCritter {
	public ArrayList<Actor> getActors() {
		var x = new ArrayList<Actor>();
		Actor ahead = null, behind = null;
		try {
			ahead = getActor(getDirection());
			behind = getActor(getDirection() + HALF_CIRCLE);
		} catch (IllegalArgumentException ignored) {
		}
		if (getValid(getDirection()) && (ahead != null)) x.add(ahead);
		if (getValid(getDirection() + HALF_CIRCLE) && (behind != null)) x.add(behind);
		return x;
	}

	private Actor getActor(int x) {
		return getGrid().get(getLocation().getAdjacentLocation(x));
	}

	private boolean getValid(int x) {
		return getGrid().isValid(getLocation().getAdjacentLocation(x));
	}
}
