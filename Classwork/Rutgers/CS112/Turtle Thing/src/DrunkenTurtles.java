import java.awt.*;

import static edu.princeton.cs.algs4.StdOut.println;
import static edu.princeton.cs.algs4.StdRandom.uniform;
import static java.util.Arrays.setAll;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

public class DrunkenTurtles {
	public static void main(String[] args) {
		int numberOfTurtles = 100;
		int numberOfSteps = 200;
		double stepSize = 0.02;
		// allocate enough space for numberOfTurtles in an array
		Turtle[] turtles = new Turtle[numberOfTurtles];
		// running time? tilde notation + big o
		// instantiate the turtles, each turtle is one object
		setAll(turtles, i -> new Turtle(uniform(0D, 1D), uniform(0D, 1D), 0.0, new Color(uniform(256), uniform(256), uniform(256))));
		println("Number turtles " + numberOfTurtles); // come back to it next class
		// running time? tilde notation + big o
		// make each turtle take one step at a time for a total of numberOfSteps
		range(0, numberOfSteps).forEach(s -> stream(turtles).forEach(turtle -> {
			turtle.turnLeft(uniform(0.0, 360.0));
			turtle.moveForward(stepSize);
		}));
	}
}
