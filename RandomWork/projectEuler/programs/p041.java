package programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class p041 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p041().run());
	}

	@Contract(pure = true)
	private static int toInteger(@NotNull int[] digits) {
		int result = 0;
		for (int x : digits) result = result * 10 + x;
		return result;
	}

	@NotNull String run() {
		for (int n = 9; n >= 1; n--) {
			int[] digits = new int[n];
			for (int i = 0; i < digits.length; i++) digits[i] = i + 1;
			int result = -1;
			do if (Library.isPrime(toInteger(digits))) result = toInteger(digits);
			while (Library.nextPermutation(digits));
			if (result != -1) return Integer.toString(result);
		}
		throw new RuntimeException("Not found");
	}
}