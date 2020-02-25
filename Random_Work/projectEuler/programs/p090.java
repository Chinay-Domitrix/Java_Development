package programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static java.lang.Integer.bitCount;

public final class p090 extends EulerSolution {
	private static final int[][] SQUARES = {{0, 1}, {0, 4}, {0, 9}, {1, 6}, {2, 5}, {3, 6}, {4, 9}, {6, 4}, {8, 1}};

	public static void main(String[] args) {
		System.out.println(new p090().run());
	}

	private static boolean isArrangementValid(int a, int b) {
		if (testBit(a, 6) || testBit(a, 9)) a |= (1 << 6) | (1 << 9);
		if (testBit(b, 6) || testBit(b, 9)) b |= (1 << 6) | (1 << 9);
		for (int[] sqr : SQUARES)
			if (!((testBit(a, sqr[0]) && testBit(b, sqr[1])) || (testBit(a, sqr[1]) && testBit(b, sqr[0]))))
				return false;
		return true;
	}

	@Contract(pure = true)
	private static boolean testBit(int x, int i) {
		return ((x >>> i) & 1) != 0;
	}

	@NotNull String run() {
		// Each die has (10 choose 6) arrangements, so we have at most 44100 arrangements to check
		int count = 0;
		// Ensure i <= j to force the dice to be orderless
		for (int i = 0; i < (1 << 10); i++)
			for (int j = i; j < (1 << 10); j++)
				if (bitCount(i) == 6 && bitCount(j) == 6 && isArrangementValid(i, j)) count++;
		return Integer.toString(count);
	}
}