package projectEuler.programs;

import java.math.BigInteger;

import static java.lang.Math.toIntExact;
import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.valueOf;
import static java.util.stream.IntStream.rangeClosed;
import static projectEuler.programs.Library.sqrt;

public final class p387 extends EulerSolution {
	private static final long LIMIT = 100000000000000L;
	private BigInteger sum = ZERO;

	public static void main(String[] args) {
		System.out.println(new p387().run());
	}

	private static boolean isPrime(long x) {
		if (x < 0) throw new IllegalArgumentException("Negative number");
		else if ((x == 0) || (x == 1)) return false;
		for (int i = 2, end = toIntExact(sqrt(x)); i <= end; i++) if ((x % i) == 0) return false;
		return true;
	}

	String run() {
		// All one-digit numbers are trivially Harshad numbers
		rangeClosed(1, 9).forEachOrdered(i -> findHarshadPrimes(i, i, false));
		return sum.toString();
	}

	// Note: n must be a right-truncatable Harshad number, and the other arguments are properties of the number n.
	private void findHarshadPrimes(long n, int digitSum, boolean isStrong) {
		// Shift left by 1 digit, and try all 10 possibilities for the rightmost digit
		long m = n * 10;
		int s = digitSum;
		for (int i = 0; i < 10 && m < LIMIT; i++, m++, s++) {
			if (isStrong && isPrime(m)) sum = sum.add(valueOf(m));
			if ((m % s) == 0) findHarshadPrimes(m, s, isPrime(m / s));
		}
	}
}