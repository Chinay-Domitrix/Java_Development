import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static java.util.Arrays.sort;

public final class p052 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p052().run());
	}

	private static boolean multiplesHaveSameDigits(int x) {
		for (int i = 2; i <= 6; i++) if (!Arrays.equals(toSortedDigits(x), toSortedDigits(i * x))) return false;
		return true;
	}

	@NotNull
	private static char[] toSortedDigits(int x) {
		char[] result = Integer.toString(x).toCharArray();
		sort(result);
		return result;
	}

	@NotNull String run() {
		for (int i = 1; ; i++) {
			assert i <= Integer.MAX_VALUE / 6 : "Overflow";
			if (multiplesHaveSameDigits(i)) return Integer.toString(i);
		}
	}
}