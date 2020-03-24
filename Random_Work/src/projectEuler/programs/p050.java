package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import static projectEuler.programs.Library.*;

public final class p050 extends EulerSolution {
	private static final int LIMIT = pow(10, 6);

	public static void main(String[] args) {
		System.out.println(new p050().run());
	}

	@NotNull String run() {
		boolean[] isPrime = listPrimality(LIMIT);
		int[] primes = listPrimes(LIMIT);
		long maxSum = 0;
		int maxRun = -1;
		for (int i = 0; i < primes.length; i++) { // For each index of a starting prime number
			int sum = 0;
			for (int j = i; j < primes.length; j++) { // For each end index (inclusive)
				sum += primes[j];
				if (sum > LIMIT) break;
				else if (j - i > maxRun && sum > maxSum && isPrime[sum]) {
					maxSum = sum;
					maxRun = j - i;
				}
			}
		}
		return Long.toString(maxSum);
	}
}