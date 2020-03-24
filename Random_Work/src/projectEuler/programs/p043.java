package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static projectEuler.programs.Library.nextPermutation;

public final class p043 extends EulerSolution {
	private static final int[] DIVISIBILITY_TESTS = {2, 3, 5, 7, 11, 13, 17}; // First 7 primes

	public static void main(String[] args) {
		System.out.println(new p043().run());
	}

	@Contract(pure = true)
	private static long toInteger(int[] digits, int off, int len) {
		long result = 0;
		for (int i = off; i < off + len; i++) result = (result * 10) + digits[i];
		return result;
	}

	@NotNull String run() {
		long sum = 0;
		int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		outer:
		do {
			for (int i = 0; i < DIVISIBILITY_TESTS.length; i++)
				if (toInteger(digits, i + 1, 3) % DIVISIBILITY_TESTS[i] != 0) continue outer;
			sum += toInteger(digits, 0, digits.length);
		} while (nextPermutation(digits));
		return Long.toString(sum);
	}
}