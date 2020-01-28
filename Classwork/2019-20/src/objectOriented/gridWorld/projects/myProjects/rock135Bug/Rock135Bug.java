package objectOriented.gridWorld.projects.myProjects.rock135Bug;

import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;

import java.awt.*;
import java.util.Random;

public class Rock135Bug extends Bug {
	public Rock135Bug() {
		this(new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
	}

	public Rock135Bug(Color bugColor) {
		super(bugColor);
	}

	/**
	 * Turn 135 degrees right if facing a {@linkplain Rock}, otherwise 45 degrees right
	 */
	public void turn() {
		var loc = getLocation().getAdjacentLocation(getDirection());
		if (getGrid().isValid(loc) && (getGrid().get(loc) instanceof Rock)) setDirection(getDirection() + 135);
		else super.turn();
	}
}