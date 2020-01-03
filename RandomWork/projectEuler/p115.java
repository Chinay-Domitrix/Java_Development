import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class p115 extends EulerSolution {
	private static final int M = 50;

	/*
	 * How many ways can a row n units long be filled, where red blocks are
	 * at least m units long? Denote this quantity as ways[n].
	 * Compute n = 0 manually as a base case.
	 *
	 * Now assume n >= 1. Look at the leftmost item and sum up the possibilities.
	 * - If the item is a black square, then the rest of the row is allowed
	 * to be anything of length n-1. Add ways[n-1].
	 * - If the item is a red block with length k where k >= m, then:
	 * - If k = n, then the whole row is filled by this red block. Add 1.
	 * - Otherwise k < n, this red block is followed by a black square, then followed
	 *  by anything of length n-k-1. So add ways[n-m-1] + ways[n-m-2] + ... + ways[0].
	 */

	public static void main(String[] args) {
		System.out.println(new p115().run());
	}

	@NotNull String run() {
		// Dynamic programming
		ArrayList<Long> ways = new ArrayList<>();
		ways.add(1L);
		for (int n = 1; ; n++) {
			long sum = ways.get(n - 1);
			for (int k = M; k < n; k++) sum += ways.get(n - k - 1);
			if (n >= M) sum++;
			ways.add(sum);
			if (sum > 1000000) return Long.toString(n);
		}
	}
}