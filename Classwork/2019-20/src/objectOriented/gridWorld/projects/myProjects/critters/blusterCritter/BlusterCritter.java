package objectOriented.gridWorld.projects.myProjects.critters.blusterCritter;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static java.util.stream.IntStream.range;

public class BlusterCritter extends Critter {
	private int courage;

	public BlusterCritter(int courage) {
		this.courage = courage;
	}

	@Override
	public ArrayList<Actor> getActors() {
		var initialNeighbors = super.getActors();
		var returned = new ArrayList<>(initialNeighbors);
		var temp = new ArrayList<Actor>();
		range(0, returned.size()).mapToObj(i -> getGrid().getNeighbors(initialNeighbors.get(i).getLocation())).forEachOrdered(temp::addAll);
		temp.remove(getGrid().get(getLocation()));
		returned.addAll(temp);
		return returned;
	}

	@Override
	public void processActors(@NotNull ArrayList<Actor> actors) {
		this.setColor((actors.size() < courage) ? this.getColor().brighter() : this.getColor().darker());
		super.processActors(actors);
	}
}