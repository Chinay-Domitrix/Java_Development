package objectOriented.gridWorld.projects.myProjects.critters.medusaCritter;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

import static info.gridworld.grid.Location.NORTH;

public class MedusaCritter extends Critter {
	@Override
	public void processActors(ArrayList<Actor> actors) {
//		System.out.println(actors.size());
//		if (actors.size() != 0 && actors.get(0) != null) {
		Actor actor = actors.get(0);
		Location x = actor.getLocation();
		Rock y = new Rock(actor.getColor());
		getGrid().put(x, y);
//		}
	}

	@Override
	public ArrayList<Actor> getActors() {
		ArrayList<Actor> x = new ArrayList<>();
		Grid<Actor> grid = getGrid();
		Actor current = null;
		if (grid.isValid(getLocation().getAdjacentLocation(NORTH)))
			current = grid.get(getLocation().getAdjacentLocation(NORTH));
		for (int i = getLocation().getCol() - 1; i > 0; i--) {
			assert current != null;
			x.add(current);
			if (x.size() == 1) break;
//			try {
			if (grid.isValid(current.getLocation().getAdjacentLocation(NORTH)))
				current = grid.get(current.getLocation().getAdjacentLocation(NORTH));
			else break;
//			} catch (NullPointerException e) {
//				new NullPointerException().addSuppressed(e);
//			}
		}
		return x;
	}
}