package objectOriented.gridWorld.projects.myProjects.bugs.shyBug;

import objectOriented.gridWorld.framework.info.gridworld.actor.ActorWorld;
import objectOriented.gridWorld.framework.info.gridworld.actor.Rock;
import objectOriented.gridWorld.framework.info.gridworld.grid.Location;

import java.awt.*;
import java.util.Random;

/**
 * This class runs a world that contains a bug and a rock, added at random
 * <p>
 * See Javadoc
 * <a href="http://www.greenteapress.com/thinkapjava/javadoc/gridworld">here</a>
 * for reference
 */
public class ShyBugRunner {
	public static void main(String[] args) {
		var world = new ActorWorld();
		world.add(new Location(8, 7), new Rock());
		world.add(new Location(7, 2), new Rock());
		world.add(new Location(3, 2), new Rock());
		world.add(new Location(2, 2),
				new ShyBug(new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256))));
		world.show();
	}
}
