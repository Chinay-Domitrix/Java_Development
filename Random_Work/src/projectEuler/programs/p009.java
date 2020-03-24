package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

public final class p009 extends EulerSolution {
	/*
	 * Computers are fast, so we can implement a brute-force search to directly solve the problem.
	 * Note that a^2 + b^2 is bounded above by 2*(1000^2), which fits in a Java int type.
	 */
	private static final int PERIMETER = 1000;

	public static void main(String[] args) {
		System.out.println(new p009().run());
	}

	@NotNull String run() {
		for (int a = 1; a < PERIMETER; a++)
			for (int b = a + 1; b < PERIMETER; b++) {
				int c = PERIMETER - a - b;
				// It is now implied that b < c, because we have a > 0
				if (((a * a) + (b * b)) == (c * c)) return Integer.toString(a * b * c);
			}
		throw new AssertionError("Not found");
	}
}