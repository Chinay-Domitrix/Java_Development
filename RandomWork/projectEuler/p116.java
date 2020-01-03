import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class p116 extends EulerSolution {
	private static final int LENGTH = 50;

	public static void main(String[] args) {
		System.out.println(new p116().run());
	}

	/*
	 * How many ways can a row n units long be filled with black squares 1 unit long
	 * and colored tiles m units long? Denote this quantity as ways[n].
	 * Compute n = 0 manually as a base case.
	 *
	 * Now assume n >= 1. Look at the leftmost item and sum up the possibilities.
	 * - If the item is a black square, then the rest of the row
	 * is allowed to be anything of length n-1. Add ways[n-1].
	 * - If the item is a colored tile of length m where m <= n, then the
	 * rest of the row can be anything of length n-m. Add ways[n-m].
	 *
	 * At the end, return ways[length]-1 to exclude the case where the row is all black squares.
	 */
	@Contract(pure = true)
	private static long countWays(int m) { // m is the length of colored tiles
		// Dynamic programming
		long[] ways = new long[p116.LENGTH + 1];
		ways[0] = 1;
		for (int n = 1; n <= p116.LENGTH; n++) {
			ways[n] += ways[n - 1];
			if (n >= m) ways[n] += ways[n - m];
		}
		return ways[p116.LENGTH] - 1;
	}

	@NotNull String run() {
		return Long.toString(countWays(2) + countWays(3) + countWays(4));
	}
}