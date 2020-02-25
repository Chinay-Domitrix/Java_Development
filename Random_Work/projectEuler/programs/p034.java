package programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class p034 extends EulerSolution {
	// Hard-coded values for factorial(0), factorial(1), ..., factorial(9)
	private static final int[] FACTORIAL = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

	public static void main(String[] args) {
		System.out.println(new p034().run());
	}

	@Contract(pure = true)
	private static int factorialDigitSum(int x) {
		int sum = 0;
		while (x != 0) {
			sum += FACTORIAL[x % 10];
			x /= 10;
		}
		return sum;
	}

	@NotNull String run() {
		// As stated in the problem, 1 = 1! and 2 = 2! are excluded.
		// If a number has at least n >= 8 digits, then even if every digit is 9,
		// n * 9! is still less than the number (which is at least 10^n).
		int sum = 0;
		for (int i = 3; i < 10000000; i++) if (i == factorialDigitSum(i)) sum += i;
		return Integer.toString(sum);
	}
}