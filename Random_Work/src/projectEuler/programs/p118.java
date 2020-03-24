package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static projectEuler.programs.Library.listPrimality;
import static projectEuler.programs.Library.nextPermutation;

public final class p118 extends EulerSolution {
	private boolean[] isPrime; // Cache for small numbers
	private int count;

	public static void main(String[] args) {
		System.out.println(new p118().run());
	}

	@Contract(pure = true)
	private static int toInteger(int[] digits, int start, int end) {
		int result = 0;
		for (int i = start; i < end; i++) result = result * 10 + digits[i];
		return result;
	}

	@NotNull String run() {
		isPrime = listPrimality(10000);
		count = 0;
		var digits = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
		do countPrimeSets(digits, 0, 0); // Try all permutations, and try all splits within each permutation
		while (nextPermutation(digits));
		return Integer.toString(count);
	}

	private void countPrimeSets(@NotNull int[] digits, int startIndex, int prevNum) {
		if (startIndex == digits.length) count++;
		else for (int split = startIndex + 1; split <= digits.length; split++) {
			int num = toInteger(digits, startIndex, split);
			if (num > prevNum && isPrime(num)) countPrimeSets(digits, split, num);
		}
	}

	@Contract(pure = true)
	private boolean isPrime(int n) {
		return (n < isPrime.length) ? isPrime[n] : Library.isPrime(n);
	}
}