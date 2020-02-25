package programs;

import org.jetbrains.annotations.NotNull;

public final class p214 extends EulerSolution {
	private static final int LIMIT = 40000000;

	public static void main(String[] args) {
		System.out.println(new p214().run());
	}

	// Requires at least 320 MB of memory
	@NotNull String run() {
		int[] totient = Library.listTotients(LIMIT - 1);
		int[] totientChainLength = new int[totient.length];
		totientChainLength[0] = 0;
		long sum = 0;
		// Fill table in ascending order because totient chains are strictly decreasing
		for (int i = 1; i < totient.length; i++) {
			int chainlen = totientChainLength[totient[i]] + 1;
			totientChainLength[i] = chainlen;
			// i is prime iff totient(i) = i-1
			if (chainlen == 25 && totient[i] == i - 1) sum += i;
		}
		return Long.toString(sum);
	}
}