package objectOriented.gridWorld;

import info.gridworld.actor.Bug;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import static java.lang.System.out;

public class GridTester {
	public static void main(String[] args) {
		var bg = new BoundedGrid<>(3, 3);
		var ubg = new UnboundedGrid<>();
		out.printf("bg rows = %d, bg cols = %d%n", bg.getNumRows(), bg.getNumCols());
		out.printf("ubg rows = %d, ubg cols = %d%n", ubg.getNumRows(), ubg.getNumCols());
		out.printf("bg.isValid(4,4)=%s%nubg.isValid(4,4)=%s%n", bg.isValid(new Location(4, 4)), ubg.isValid(new Location(4, 4)));
		bg.put(new Location(1, 1), new Bug());
	}
}
