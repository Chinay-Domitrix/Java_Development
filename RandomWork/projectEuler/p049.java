import java.util.Arrays;

49

public final class p049 extends EulerSolution {
	private static final int LIMIT = 10000;

	public static void main(String[] args) {
		System.out.println(new p049().run());
	}

	private static boolean hasSameDigits(int x, int y) {
		char[] xdigits = Integer.toString(x).toCharArray();
		char[] ydigits = Integer.toString(y).toCharArray();
		Arrays.sort(xdigits);
		Arrays.sort(ydigits);
		return Arrays.equals(xdigits, ydigits);
	}

	String run() {
		boolean[] isPrime = Library.listPrimality(LIMIT - 1);
		for (int base = 1000; base < LIMIT; base++) {
			if (isPrime[base]) {
				for (int step = 1; step < LIMIT; step++) {
					int a = base + step;
					int b = a + step;
					if (a < LIMIT && isPrime[a] && hasSameDigits(a, base)
							&& b < LIMIT && isPrime[b] && hasSameDigits(b, base)
							&& (base != 1487 || a != 4817))
						return "" + base + a + b;
				}
			}
		}
		throw new RuntimeException("Not found");
	}
}