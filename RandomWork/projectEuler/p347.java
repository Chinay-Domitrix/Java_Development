import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public final class p347 extends EulerSolution {
	private static final int LIMIT = 10000000;

	public static void main(String[] args) {
		System.out.println(new p347().run());
	}

	@NotNull String run() {
		Set<Integer> possible = new HashSet<>();
		int[] primes = Library.listPrimes(LIMIT / 2);
		for (int i = 0, end = Library.sqrt(LIMIT); i < primes.length; i++) {
			int p = primes[i];
			if (p > end) break;
			for (int j = i + 1; j < primes.length; j++) {
				int q = primes[j];
				int lcm = p * q;
				if (lcm > LIMIT) break;
				int multLimit = LIMIT / lcm;
				int multiplier = 1;
				while (multiplier * p <= multLimit) multiplier *= p;
				int maxMult = multiplier;
				while (multiplier % p == 0) {
					multiplier /= p;
					while (multiplier * q <= multLimit) multiplier *= q;
					maxMult = Math.max(multiplier, maxMult);
				}
				possible.add(maxMult * lcm);
			}
		}
		long sum = 0;
		for (int x : possible) sum += x;
		return Long.toString(sum);
	}
}