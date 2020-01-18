package objectOriented.gridWorld.projects.myProjects.jumper;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;

import java.util.ArrayList;

public class JumperRunner {
	public static void main(String[] args) {
		var world = new ActorWorld(new BoundedGrid<>(35, 60));
		ArrayList<Jumper> jumpers = new ArrayList<>();
		for (int i = 0; i < 1; i++) {
			Jumper jumper1 = new Jumper();
			jumpers.add(jumper1);
		}
		for (Jumper jumper : jumpers) {
			world.add(jumper);
		}
		world.add(new CBug(5));
		world.add(new HBug(5));
		world.add(new EBug(7));
		world.add(new ShurikenBug(4, 10));
		world.show();
	}
}
