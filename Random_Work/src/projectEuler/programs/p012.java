package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

import static java.lang.Integer.MAX_VALUE;
import static projectEuler.programs.Library.sqrt;

public final class p012 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p012().run());
	}

	// Returns the number of integers in the range [1, n] that divide n.
	@Contract(pure = true)
	private static int countDivisors(int n) {
		int end = sqrt(n);
		int count = IntStream.range(1, end).filter(i -> n % i == 0).map(i -> 2).sum();
		if ((end * end) == n) count++; // Perfect square
		return count;
	}

	/*
	 * Computers are fast, so we can implement this solution directly without any clever math.
	 */
	@NotNull String run() {
		int triangle = 0;
		for (int i = 1; ; i++) {
			assert (MAX_VALUE - triangle) >= i : "Overflow";
			triangle += i; // This is the ith triangle number, i.e. num = 1 + 2 + ... + i = i * (i + 1) / 2
			if (countDivisors(triangle) > 500) return Integer.toString(triangle);
		}
	}

}