package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static projectEuler.programs.Library.listSmallestPrimeFactors;

public final class p231 extends EulerSolution {
	private static final int N = 20000000;
	private static final int K = 15000000;
	private int[] smallestPrimeFactor;

	public static void main(String[] args) {
		System.out.println(new p231().run());
	}

	@NotNull String run() {
		smallestPrimeFactor = listSmallestPrimeFactors(N);
		return Long.toString(factorialPrimeFactorSum(N) - factorialPrimeFactorSum(K) - factorialPrimeFactorSum(N - K));
	}

	@Contract(pure = true)
	private long factorialPrimeFactorSum(int n) {
		long sum = 0;
		for (int i = 1; i <= n; i++) {
			int j = i;
			while (j > 1) {
				int p = smallestPrimeFactor[j];
				sum += p;
				j /= p;
			}
		}
		return sum;
	}
}