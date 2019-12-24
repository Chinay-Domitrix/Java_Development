import java.util.Arrays;

51

public final class p051 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p051().run());
	}

	private static int[] toDigits(int n) {
		int[] buf = new int[10];
		int i = buf.length;
		do {
			i--;
			buf[i] = n % 10;
			n /= 10;
		} while (n != 0);
		return Arrays.copyOfRange(buf, i, buf.length);
	}

	private static int[] doMask(int[] digits, int mask) {
		int[] result = new int[digits.length];
		for (int i = 0; i < digits.length; i++)
			result[i] = digits[i] * (~mask >>> i & 1);
		return result;
	}

	private static int[] addMask(int[] digits, int mask) {
		int[] result = new int[digits.length];
		for (int i = 0; i < digits.length; i++)
			result[i] = digits[i] + (mask >>> i & 1);
		return result;
	}

	private static int toNumber(int[] digits) {
		int result = 0;
		for (int x : digits)
			result = result * 10 + x;
		return result;
	}

	String run() {
		boolean[] isPrime = Library.listPrimality(1000000);
		for (int i = 0; i < isPrime.length; i++) {
			if (!isPrime[i])
				continue;

			int[] n = toDigits(i);
			for (int mask = 0; mask < (1 << n.length); mask++) {
				int[] digits = doMask(n, mask);
				int count = 0;
				for (int j = 0; j < 10; j++) {
					if (digits[0] != 0 && isPrime[toNumber(digits)])
						count++;
					digits = addMask(digits, mask);
				}

				if (count == 8) {
					digits = doMask(n, mask);
					for (int j = 0; j < 10; j++) {
						if (digits[0] != 0 && isPrime[toNumber(digits)])
							return Integer.toString(toNumber(digits));
						digits = addMask(digits, mask);
					}
				}
			}
		}
		throw new RuntimeException("Not found");
	}
}