package programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public final class p074 extends EulerSolution {
	private static final int LIMIT = Library.pow(10, 6);
	// Hard-coded values for factorial(0), factorial(1), ..., factorial(9)
	private static final int[] FACTORIAL = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

	public static void main(String[] args) {
		System.out.println(new p074().run());
	}

	private static int getChainLength(int n) {
		HashSet<Integer> seen = new HashSet<>();
		while (true) {
			if (!seen.add(n)) return seen.size();
			n = factorialize(n);
		}
	}

	@Contract(pure = true)
	private static int factorialize(int n) {
		int sum = 0;
		for (; n != 0; n /= 10) sum += FACTORIAL[n % 10];
		return sum;
	}

	@NotNull String run() {
		int count = 0;
		for (int i = 0; i < LIMIT; i++) if (getChainLength(i) == 60) count++;
		return Integer.toString(count);
	}
}