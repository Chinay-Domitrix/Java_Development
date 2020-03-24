package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import static java.lang.Math.min;
import static projectEuler.programs.Library.sqrt;

public final class p142 extends EulerSolution {
	private boolean[] isSquare;

	public static void main(String[] args) {
		System.out.println(new p142().run());
	}

	@NotNull String run() {
		int sumLimit = 10;
		// Raise the limit until a sum is found
		while (true) {
			isSquare = new boolean[sumLimit];
			for (int i = 0; i * i < sumLimit; i++) isSquare[i * i] = true;
			int sum = findSum(sumLimit);
			if (sum != -1) break;
			sumLimit *= 10;
		}
		// Lower the limit until no sum is found
		while (true) {
			int sum = findSum(sumLimit);
			// No smaller sum found
			if (sum == -1) return Integer.toString(sumLimit);
			sumLimit = sum;
		}
	}

	/*
	 * Finds any sum s = x+y+z such that s < limit, 0 < z < y < x, and these are
	 * perfect squares: x+y, x-y, x+z, x-z, y+z, y-z. Returns -1 if none is found.
	 *
	 * Suppose we let x + y = a^2 and x - y = b^2, so that they are always square.
	 * Then x = (a^2 + b^2) / 2 and y = (a^2 - b^2) / 2. By ensuring a > b > 0, we have x > y > 0.
	 * Now z < y and z < limit - x - y. Let y + z = c^2, then explicitly check
	 * if x+z, x-z, and y-z are square.
	 */
	private int findSum(int limit) {
		for (int a = 1; a * a < limit; a++)
			for (int b = a - 1; b > 0; b--) {
				// Need them to be both odd or both even so that we get integers for x and y
				if (((a + b) % 2) != 0) continue;
				int x = ((a * a) + (b * b)) >> 1, y = ((a * a) - (b * b)) >> 1;
				// Because z >= 1
				if ((x + y + 1) >= limit) continue;
				int zLimit = min(y, limit - x - y);
				for (int c = sqrt(y) + 1; ((c * c) - y) < zLimit; c++)
					if (isSquare[(x + (c * c)) - y] && isSquare[x - (c * c) - y] && isSquare[-(c * c)])
						return (x + y + (c * c)) - y;
			}
		return -1;
	}
}