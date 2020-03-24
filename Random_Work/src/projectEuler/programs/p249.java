package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import static projectEuler.programs.Library.listPrimality;

public final class p249 extends EulerSolution {
	private static final int LIMIT = 5000;
	private static final long MODULUS = 10000000000000000L;

	public static void main(String[] args) {
		System.out.println(new p249().run());
	}

	@NotNull String run() {
		// Use dynamic programming
		boolean[] isPrime = listPrimality(12500000);
		long[] numSubsets = new long[12500000]; // numSubsets[i] is the number of subsets with sum i, mod 10^16
		numSubsets[0] = 1;
		int maxSum = 0; // Sum of all primes seen so far
		for (int i = 0; i < LIMIT; i++) {
			if (!isPrime[i]) continue;
			maxSum += i;
			for (int j = maxSum; j >= i; j--) {
				// Optimization of modulo because we know 0 <= numSubsets[j] + numSubsets[j - i] < 2 * MODULUS
				long temp = numSubsets[j] + numSubsets[j - i];
				numSubsets[j] = temp < MODULUS ? temp : temp - MODULUS;
			}
		}
		long sum = 0;
		for (int i = 0; i < numSubsets.length; i++) if (isPrime[i]) sum = (sum + numSubsets[i]) % MODULUS;
		return Long.toString(sum);
	}
}