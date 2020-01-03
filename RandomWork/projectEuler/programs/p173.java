package programs;

import org.jetbrains.annotations.NotNull;

public final class p173 extends EulerSolution {
	private static final int TILES = 1000000;

	public static void main(String[] args) {
		System.out.println(new p173().run());
	}

	@NotNull String run() {
		int count = 0;
		// Outer square length
		for (int n = 3; n <= TILES / 4 + 1; n++)
			for (int k = n - 2; k >= 1; k -= 2) { // Inner square length
				if ((long) n * n - (long) k * k > TILES) break;
				count++;
			}
		return Integer.toString(count);
	}
}