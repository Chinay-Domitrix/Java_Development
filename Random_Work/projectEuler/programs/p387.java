package programs;

import java.math.BigInteger;

public final class p387 extends EulerSolution {
	private static final long LIMIT = 100000000000000L;
	private BigInteger sum = BigInteger.ZERO;

	public static void main(String[] args) {
		System.out.println(new p387().run());
	}

	private static boolean isPrime(long x) {
		if (x < 0) throw new IllegalArgumentException("Negative number");
		else if (x == 0 || x == 1) return false;
		for (long i = 2, end = Library.sqrt(x); i <= end; i++) if (x % i == 0) return false;
		return true;
	}

	String run() {
		// All one-digit numbers are trivially Harshad numbers
		for (int i = 1; i <= 9; i++) findHarshadPrimes(i, i, false);
		return sum.toString();
	}

	// Note: n must be a right-truncatable Harshad number, and the other arguments are properties of the number n.
	private void findHarshadPrimes(long n, int digitSum, boolean isStrong) {
		// Shift left by 1 digit, and try all 10 possibilities for the rightmost digit
		long m = n * 10;
		int s = digitSum;
		for (int i = 0; i < 10 && m < LIMIT; i++, m++, s++) {
			if (isStrong && isPrime(m)) sum = sum.add(BigInteger.valueOf(m));
			if (m % s == 0) findHarshadPrimes(m, s, isPrime(m / s));
		}
	}
}