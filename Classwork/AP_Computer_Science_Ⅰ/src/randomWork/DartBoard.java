package randomWork;

import java.util.Random;

import static java.lang.Math.*;
import static java.lang.System.out;

public class DartBoard {
	public static void main(String[] args) {
		var totalDarts = 0;
		var circleDarts = 0;
		var circleArea = 0.0;
		final var tolerance = 0.00000000000000000001;
		while (abs(PI - circleArea) > tolerance) {
			totalDarts++;
			var x = new Random();
			if (sqrt(pow(x.nextDouble(), 2) + pow(x.nextDouble(), 2)) < 1)
				circleDarts++;
			circleArea = (4 * circleDarts) / (double) totalDarts;
			out.println(circleArea);
		}
		out.println(circleArea);
	}
}
