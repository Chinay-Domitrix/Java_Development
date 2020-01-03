package programs;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public final class p087 extends EulerSolution {
	private static final int LIMIT = 50000000;

	public static void main(String[] args) {
		System.out.println(new p087().run());
	}

	@NotNull String run() {
		int[] primes = Library.listPrimes(Library.sqrt(LIMIT));
		HashSet<Integer> sums = new HashSet<>();
		sums.add(0);
		for (int i = 2; i <= 4; i++) {
			HashSet<Integer> newSums = new HashSet<>();
			for (int p : primes) {
				long q = 1;
				for (int j = 0; j < i; j++) q *= p;
				// q = p^i
				if (q > LIMIT) break;
				int r = (int) q;
				for (int x : sums) if (x + r <= LIMIT) newSums.add(x + r);
			}
			sums = newSums;
		}
		return Integer.toString(sums.size());
	}
}