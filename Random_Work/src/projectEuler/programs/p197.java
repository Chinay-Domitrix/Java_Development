package projectEuler.programs;

import static java.lang.Math.floor;
import static java.lang.Math.pow;
import static java.lang.String.format;

public final class p197 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p197().run());
	}

	private static double f(double x) {
		return floor(pow(2, 30.403_243_784 - pow(x, 2))) / 1_000_000_000;
	}

	String run() {
		// Floyd's cycle-finding algorithm
		double x = -1;
		double y = -1;
		long i = 0;
		long ITERATIONS = 1000000000000L;
		for (; i < ITERATIONS; i++) {
			// Here at the top of the loop, x = f^i(-1) and y = f^{2i}(-1)
			// This means index i is part of the cycle, and (2i - i) = i is some multiple of the true cycle length
			if ((i > 0) && (x == y)) break;
			// Advance the states at different speeds
			x = f(x);
			y = f(f(y));
		}
		// Advance by many multiples of the cycle length, then deal with the remaining iterations
		long remain = (ITERATIONS - i) % i;
		for (; remain > 0; remain--) x = f(x);
		double answer = x + f(x);
		answer = floor(answer * 1000000000) / 1000000000; // Truncate to 9 digits after the decimal point
		return format("%.9f", answer);
	}
}