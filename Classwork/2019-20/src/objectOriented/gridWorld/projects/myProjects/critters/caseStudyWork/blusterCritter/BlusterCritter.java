package objectOriented.gridWorld.projects.myProjects.critters.caseStudyWork.blusterCritter;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;

import static java.util.Arrays.setAll;
import static java.util.Arrays.sort;
import static java.util.stream.IntStream.range;

public class BlusterCritter extends Critter {
	private int courage;

	BlusterCritter(int courage) {
		this.courage = courage;
	}

	BlusterCritter(int courage, Color color) {
		this(courage);
		setColor(color);
	}

	@Override
	public ArrayList<Actor> getActors() {
		var initialNeighbors = super.getActors();
		var returned = new ArrayList<>(initialNeighbors);
		var temp = new ArrayList<Actor>();
		range(0, returned.size()).mapToObj(i -> getGrid().getNeighbors(initialNeighbors.get(i).getLocation())).forEachOrdered(temp::addAll);
		temp.remove(getGrid().get(getLocation()));
		var x = new Actor[temp.size()];
		setAll(x, temp::get);
		sort(x);
		range(0, x.length).forEachOrdered(i -> temp.set(i, x[i]));
		range(0, temp.size() - 1).filter(i -> temp.get(i).equals(temp.get(i + 1))).forEachOrdered(i -> temp.remove(i + 1));
		returned.addAll(temp);
		return returned;
	}

	@Override
	public void processActors(@NotNull ArrayList<Actor> actors) {
		setColor((actors.size() < courage) ? this.getColor().brighter() : this.getColor().darker());
		super.processActors(actors);
	}
}