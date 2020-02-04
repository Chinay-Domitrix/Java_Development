package objectOriented.gridWorld.projects.myProjects.bugs.shyBug;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

import java.awt.*;

import static info.gridworld.grid.Location.EAST;

class ShyBug extends Bug {
	/**
	 * The default constructor. It calls the {@link Bug#Bug()} constructor, and then sets the starting direction to {@value Location#EAST}.
	 */
	ShyBug() {
		super();
		setDirection(EAST);
	}

	/**
	 * The secondary constructor. It calls the {@link Bug#Bug(Color)} constructor, and then sets the starting direction to {@value Location#EAST}.
	 */
	ShyBug(Color bugColor) {
		super(bugColor);
		setDirection(EAST);
	}

	/**
	 * The overridden {@code canMove()} method. It is almost identical to {@link Bug#canMove()}.
	 */
	public boolean canMove() {
		if (getGrid() == null) return false;
//		The basis of the expanded Location checks. It allows for checking beyond the one-square checks in super.canMove().
		Location objectChecker2 = getLocation().getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
		Location objectChecker3 = objectChecker2.getAdjacentLocation(getDirection());
//		This is what allows for checking for objects as necessary.
		Actor objectCheckedObject = getGrid().get(objectChecker2);
		/*
		 * This is almost the same as the conditional statement in super.canMove(), but the first condition has been
		 * modified to check three squares ahead, and a second condition has been added to allow for the checking of
		 * other Actors (including its subclasses).
		 */
		if (!getGrid().isValid(objectChecker3) || !(objectCheckedObject instanceof Flower) && (objectCheckedObject != null))
			return false;
		return (getGrid().get(getLocation().getAdjacentLocation(getDirection())) == null) || (getGrid().get(getLocation().getAdjacentLocation(getDirection())) instanceof Flower);
	}
}