package objectOriented.gridWorld.projects.myProjects.bugs.plusBug.braidingPlusBug;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;
import objectOriented.gridWorld.projects.myProjects.bugs.plusBug.PlusBug;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.stream.IntStream.range;

/**
 * This program is intended by a scalable iteration of PlusBug, hence why it
 * extends PlusBug. It allows the user to choose how many bugs they want on the
 * screen. The primary reason for the "Boxing" in the name is due to how the
 * bugs create boxes while executing their plus patterns.
 *
 * @see objectOriented.gridWorld.projects.myProjects.bugs.plusBug.PlusBug;
 */
class BraidingPlusBug extends PlusBug {
	/**
	 * The heavyweight of the class, it does all of the computation and bug
	 * placement, and uses {@linkplain #BraidingPlusBug(int, Location)} to access
	 * the superclass, {@linkplain PlusBug}.
	 */
	public BraidingPlusBug() {
		var world = new ActorWorld(new UnboundedGrid<>());
		var locations = new ArrayList<Location>();
		var previousLocation = new Location(0, 0);
		out.print("How many PlusBugs do you want? ");
		try (Scanner input = new Scanner(in)) {
			for (int i = 1; i <= input.nextInt(); i++) {
				var newLocation = new Location(previousLocation.getRow() + i, previousLocation.getCol() + i);
				locations.add(newLocation);
				previousLocation = newLocation;
			}
		}
		var plusBugs = new ArrayList<BraidingPlusBug>();
		range(0, locations.size()).forEachOrdered(i -> {
			plusBugs.add(new BraidingPlusBug(i + 1, locations.get(i)));
			world.add(locations.get(i), plusBugs.get(i));
		});
		world.show();
	}

	/**
	 * The proxy for {@linkplain #BraidingPlusBug()}, allowing it to access the
	 * superclass.
	 *
	 * @see PlusBug#PlusBug(int, Location)
	 */
	private BraidingPlusBug(int stemSize, Location origin) {
		super(stemSize, origin);
	}
}
