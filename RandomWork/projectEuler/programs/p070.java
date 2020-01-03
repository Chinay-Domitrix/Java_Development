package programs;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static java.util.Arrays.sort;

public final class p070 extends EulerSolution {
	private static final int LIMIT = Library.pow(10, 7);

	public static void main(String[] args) {
		System.out.println(new p070().run());
	}

	private static boolean hasSameDigits(int x, int y) {
		char[] xdigits = Integer.toString(x).toCharArray();
		char[] ydigits = Integer.toString(y).toCharArray();
		sort(xdigits);
		sort(ydigits);
		return Arrays.equals(xdigits, ydigits);
	}

	@NotNull String run() {
		int minNumer = 1; // Initially infinity
		int minDenom = 0;
		int[] totients = Library.listTotients(LIMIT - 1);
		for (int n = 2; n < totients.length; n++) {
			int tot = totients[n];
			if ((long) n * minDenom < (long) minNumer * tot && hasSameDigits(n, tot)) {
				minNumer = n;
				minDenom = tot;
			}
		}
		assert minDenom != 0 : "Not found";
		return Integer.toString(minNumer);
	}
}