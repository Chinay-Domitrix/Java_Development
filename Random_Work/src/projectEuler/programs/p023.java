package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;
import static projectEuler.programs.Library.sqrt;

public final class p023 extends EulerSolution {
	private static final int LIMIT = 28123;
	private final boolean[] isAbundant = new boolean[LIMIT + 1];

	public static void main(String[] args) {
		System.out.println(new p023().run());
	}

	private static boolean isAbundant(int n) {
		assert n >= 1;
		int sum = 1; // Sum of factors less than n
		int end = sqrt(n);
		sum += rangeClosed(2, end).filter(i -> n % i == 0).map(i -> i + n / i).sum();
		if ((end * end) == n) sum -= end;
		return sum > n;
	}

	@NotNull String run() {
		// Compute look-up table
		range(1, isAbundant.length).forEachOrdered(i -> isAbundant[i] = isAbundant(i));
		int sum = rangeClosed(1, LIMIT).filter(i -> !isSumOf2Abundants(i)).sum();
		return Integer.toString(sum);
	}

	@Contract(pure = true)
	private boolean isSumOf2Abundants(int n) {
		return rangeClosed(0, n).anyMatch(i -> isAbundant[i] && isAbundant[n - i]);
	}
}