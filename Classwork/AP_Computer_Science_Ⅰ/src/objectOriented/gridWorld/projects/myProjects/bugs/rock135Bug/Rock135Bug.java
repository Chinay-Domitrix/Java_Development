package Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.projects.myProjects.bugs.rock135Bug;

import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Bug;
import Classwork.AP_Computer_Science_Ⅰ.src.objectOriented.gridWorld.framework.info.gridworld.actor.Rock;

import java.awt.*;
import java.util.Random;

class Rock135Bug extends Bug {
	public Rock135Bug() {
		this(new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
	}

	private Rock135Bug(Color bugColor) {
		super(bugColor);
	}

	/**
	 * Turn 135 degrees right if facing a {@linkplain Rock}, otherwise 45 degrees right
	 */
	public void turn() {
		if (getGrid().isValid(getLocation().getAdjacentLocation(getDirection())) && (getGrid().get(getLocation().getAdjacentLocation(getDirection())) instanceof Rock))
			setDirection(getDirection() + 135);
		else super.turn();
	}
}
