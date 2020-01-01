import org.jetbrains.annotations.NotNull;

import static java.lang.Math.*;

public final class p174 extends EulerSolution {
	private static final int SIZE_LIMIT = 1000000;
	private static final int TYPE_LIMIT = 10;

	public static void main(String[] args) {
		System.out.println(new p174().run());
	}

	@NotNull String run() {
		// Generate all possible laminae with at most SIZE_LIMIT tiles
		int[] type = new int[SIZE_LIMIT + 1];
		// Outer square size
		for (int n = 3; (n - 1) * 4 <= SIZE_LIMIT; n++)
			for (int m = n - 2; m >= 1; m -= 2) { // Inner square hole size
				int tiles = toIntExact(round(pow(n, 2) - pow(m, 2))); // Intermediate computation may overflow, but result is correct
				if (tiles > SIZE_LIMIT) break;
				type[tiles]++;
			}

		// Examine the type of each total tiling
		int count = 0;
		for (int t : type) if (1 <= t && t <= TYPE_LIMIT) count++;
		return Integer.toString(count);
	}
}