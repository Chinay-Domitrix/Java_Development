package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.abs;
import static projectEuler.programs.Library.sqrt;

public final class p085 extends EulerSolution {
	private static final int TARGET = 2000000;

	public static void main(String[] args) {
		System.out.println(new p085().run());
	}

	@Contract(pure = true)
	private static int numberOfRectangles(int m, int n) {
		return ((m + 1) * m * (n + 1) * n) >> 2; // A bit more than m^2 n^2 / 4
	}

	@NotNull String run() {
		int bestDiff = MAX_VALUE;
		int bestArea = -1;
		int sqrt = sqrt(TARGET);
		for (int w = 1; w <= sqrt; w++)
			for (int h = 1; h <= sqrt; h++) {
				int diff = abs(numberOfRectangles(w, h) - TARGET);
				if (diff < bestDiff) {
					bestDiff = diff;
					bestArea = w * h;
				}
			}
		return Integer.toString(bestArea);
	}
}