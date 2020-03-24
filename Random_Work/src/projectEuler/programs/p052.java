package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static java.util.Arrays.sort;
import static java.util.stream.IntStream.rangeClosed;

public final class p052 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p052().run());
	}

	private static boolean multiplesHaveSameDigits(int x) {
		return rangeClosed(2, 6).allMatch(i -> Arrays.equals(toSortedDigits(x), toSortedDigits(i * x)));
	}

	@NotNull
	private static char[] toSortedDigits(int x) {
		char[] result = Integer.toString(x).toCharArray();
		sort(result);
		return result;
	}

	@NotNull String run() {
		for (int i = 1; ; i++) {
			assert i <= 357913941 : "Overflow";
			if (multiplesHaveSameDigits(i)) return Integer.toString(i);
		}
	}
}