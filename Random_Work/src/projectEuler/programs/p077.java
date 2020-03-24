package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

import static projectEuler.programs.Library.isPrime;

public final class p077 extends EulerSolution {
	private static final int TARGET = 5000;

	public static void main(String[] args) {
		System.out.println(new p077().run());
	}

	/*
	 * This function searches for the smallest n in [0, limit)
	 * such that P(n, n) > target, or returns -1 if not found.
	 *
	 * Let P(i, n) denote the number of ways that n can be written as an
	 * unordered sum of prime numbers where no prime is greater than i.
	 *
	 * - P(i, 0) = 1 for all i.
	 * - P(0, n) = 0 for all n > 0.
	 * - If i is 1 or composite then P(i, n) = P(i - 1, n).
	 * - Otherwise i is prime:
	 * - If i <= n then P(i, n) = P(i - 1, n) + P(i, n - i).
	 * - Else P(i, n) = P(i - 1, n).
	 *
	 * Notice that when computing P(i, k), we only need values from the
	 * current row i for k' in [1, k) and values from the previous row i -1
	 * for k' in [k, n). Thus we only need to buffer one row of data for
	 * dynamic programming and can overwrite it in place.
	 */
	@Contract(pure = true)
	private static int search(int limit) {
		int[] partitions = new int[limit];
		partitions[0] = 1;
		for (int i = 0; i < partitions.length; i++) {
			if (!isPrime(i)) continue;
			for (int j = i; j < partitions.length; j++) partitions[j] += partitions[j - i];
		}
		return IntStream.range(0, limit).filter(i -> partitions[i] > TARGET).findFirst().orElse(-1);
	}

	@NotNull String run() {
		for (int limit = 1; ; limit *= 2) {
			int result = search(limit);
			if (result != -1) return Integer.toString(result);
		}
	}
}