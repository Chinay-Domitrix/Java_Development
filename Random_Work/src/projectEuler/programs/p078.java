package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

import static projectEuler.programs.Library.pow;

public final class p078 extends EulerSolution {
	private static final int MODULUS = pow(10, 6);

	public static void main(String[] args) {
		System.out.println(new p078().run());
	}

	@Contract(pure = true)
	private static int search(int limit) {
		/*
		 * partitions[i] is {the number of ways i can be written
		 * as an unordered sum of positive integers} mod 10^6.
		 * Note that the partition function P(n, k) can be computed with
		 * dynamic programming using only 1 dimension for memoization.
		 */
		int[] partitions = new int[limit];
		partitions[0] = 1;
		for (int i = 1; i < limit; i++)
			for (int j = i; j < limit; j++) partitions[j] = (partitions[j] + partitions[j - i]) % MODULUS;
		return IntStream.range(0, limit).filter(i -> partitions[i] == 0).findFirst().orElse(-1);
	}

	@NotNull String run() {
		for (int limit = 1; ; limit *= 2) {
			int result = search(limit);
			if (result != -1) return Integer.toString(result);
		}
	}
}