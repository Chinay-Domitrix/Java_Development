package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

import static projectEuler.programs.Library.isPrime;
import static projectEuler.programs.Library.nextPermutation;

public final class p041 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p041().run());
	}

	@Contract(pure = true)
	private static int toInteger(@NotNull int[] digits) {
		int result = 0;
		for (int x : digits) result = (result * 10) + x;
		return result;
	}

	@NotNull String run() {
		for (int n = 9; n >= 1; n--) {
			int[] digits = IntStream.range(0, n).map(i -> i + 1).toArray();
			int result = -1;
			do if (isPrime(toInteger(digits))) result = toInteger(digits);
			while (nextPermutation(digits));
			if (result != -1) return Integer.toString(result);
		}
		throw new RuntimeException("Not found");
	}
}