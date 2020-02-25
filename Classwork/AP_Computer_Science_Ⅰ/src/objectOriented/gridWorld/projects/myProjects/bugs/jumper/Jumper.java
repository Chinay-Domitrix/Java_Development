package objectOriented.gridWorld.projects.myProjects.bugs.jumper;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static info.gridworld.grid.Location.RIGHT;
import static java.awt.Color.RED;

class Jumper extends Bug {
	private int step = 2;
	private int counter;

	public Jumper() {
		super(RED);
	}

	public Jumper(Color c) {
		setColor(c);
	}

	public void act() {
		if (this.canJump()) {
			move2();
			return;
		} else if (canMove()) move();
		if (this.canTurn(getDirection() + RIGHT)) this.turn();
		else for (int i = 0; i < 5; i++) this.turn();
	}

	//made to reduce redundant code
	private Location locAdder(@NotNull Location loc) {
		return loc.getAdjacentLocation(getDirection());
	}

	//Overrides
	private boolean canTurn(int loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null) return false;
		Location next = this.getLocation().getAdjacentLocation(loc);
		if (!gr.isValid(next)) return false;
		Actor neighbor = gr.get(next);
		return (neighbor == null) || (neighbor instanceof Flower);
	}

	public boolean canMove() {
		Grid<Actor> gr = getGrid();
		if (gr == null) return false;
		Location next = locAdder(getLocation());
		if (!gr.isValid(next)) return false;
		Actor neighbor = gr.get(next);
		return (neighbor == null) || (neighbor instanceof Flower);
	}

	private boolean canJump() {
		Grid<Actor> gr = getGrid();
		if (gr == null) return false;
		Location next = locAdder(getLocation()), next2 = locAdder(next);
		if (!gr.isValid(next2)) return false;
		Actor neighbor = gr.get(next2);
		return (neighbor == null) || (neighbor instanceof Flower);

	}

	private void move2() {
		Grid<Actor> gr = getGrid();
		if (gr == null) return;
		Location loc = getLocation(), next = loc.getAdjacentLocation(getDirection()), next2 = next.getAdjacentLocation(getDirection());
		if (gr.isValid(next2)) moveTo(next2);
		else removeSelfFromGrid();
	}

	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null) return;
		Location loc = getLocation(), next = loc.getAdjacentLocation(getDirection());
		if (gr.isValid(next)) moveTo(next);
		else removeSelfFromGrid();
	}
}