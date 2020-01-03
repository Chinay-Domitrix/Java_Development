package programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class p023 extends EulerSolution {
	private static final int LIMIT = 28123;
	private final boolean[] isAbundant = new boolean[LIMIT + 1];

	public static void main(String[] args) {
		System.out.println(new p023().run());
	}

	private static boolean isAbundant(int n) {
		if (n < 1) throw new IllegalArgumentException();
		int sum = 1; // Sum of factors less than n
		int end = Library.sqrt(n);
		for (int i = 2; i <= end; i++) if (n % i == 0) sum += i + n / i;
		if (end * end == n) sum -= end;
		return sum > n;
	}

	@NotNull String run() {
		// Compute look-up table
		for (int i = 1; i < isAbundant.length; i++) isAbundant[i] = isAbundant(i);
		int sum = 0;
		for (int i = 1; i <= LIMIT; i++) if (!isSumOf2Abundants(i)) sum += i;
		return Integer.toString(sum);
	}

	@Contract(pure = true)
	private boolean isSumOf2Abundants(int n) {
		for (int i = 0; i <= n; i++) if (isAbundant[i] && isAbundant[n - i]) return true;
		return false;
	}
}