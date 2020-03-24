package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import static projectEuler.programs.Library.sqrt;

public final class p047 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p047().run());
	}

	private static boolean has4PrimeFactors(int n) {
		return countDistinctPrimeFactors(n) == 4;
	}

	private static int countDistinctPrimeFactors(int n) {
		int count = 0;
		for (int i = 2, end = sqrt(n); i <= end; i++)
			if (n % i == 0) {
				do n /= i;
				while (n % i == 0);
				count++;
				end = sqrt(n);
			}
		if (n > 1) count++;
		return count;
	}

	@NotNull String run() {
		for (int i = 2; ; i++)
			if (has4PrimeFactors(i) && has4PrimeFactors(i + 1) && has4PrimeFactors(i + 2) && has4PrimeFactors(i + 3))
				return Integer.toString(i);
	}
}