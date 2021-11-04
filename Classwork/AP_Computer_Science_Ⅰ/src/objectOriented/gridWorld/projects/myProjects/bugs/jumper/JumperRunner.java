package objectOriented.gridWorld.projects.myProjects.bugs.jumper;

import objectOriented.gridWorld.framework.info.gridworld.actor.ActorWorld;
import objectOriented.gridWorld.framework.info.gridworld.actor.Rock;
import objectOriented.gridWorld.framework.info.gridworld.grid.BoundedGrid;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.IntStream.range;

public class JumperRunner {
	public static void main(String[] args) {
		var world = new ActorWorld(new BoundedGrid<>(35, 60));
		out.print("How many Jumpers? ");
		try (Scanner s = new Scanner(in)) {
			range(0, s.nextInt()).mapToObj(i -> new Jumper()).collect(toCollection(ArrayList::new)).forEach(world::add);
			world.add(new CBug(5));
			world.add(new HBug(5));
			world.add(new EBug(7));
			world.add(new ShurikenBug(4, 10));
			var random = new Random();
			out.print("How many Rocks? ");
			range(0, s.nextInt())
					.mapToObj(i -> new Rock(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))))
					.forEachOrdered(world::add);
		}
		world.show();
	}
}
