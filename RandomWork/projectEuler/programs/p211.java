package programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static java.util.Arrays.fill;

public final class p211 extends EulerSolution {
	private static final int LIMIT = 64000000;

	public static void main(String[] args) {
		System.out.println(new p211().run());
	}

	// Suppose i is the highest natural number such that k^i divides n.
	// Then this function returns k^(2*0) + k^(2*1) + k^(2*2) + ... + k^(2*i).
	// For example, with n=50 and k=5, i=2 because k^2 = 25 divides n = 50,
	// so the result is k^0 + k^2 + k^4 = 1 + 25 + 625 = 651.
	@Contract(pure = true)
	private static long powerSquareSum(int n, int k) {
		long result = 1;
		long k2 = (long) k * k;
		while (n % k == 0) {
			n /= k;
			result = result * k2 + 1;
		}
		return result;
	}

	// Requires about 512 MB of memory
	@NotNull String run() {
		// sigma2[i] is the sum of the square of i's divisors.
		// For example, sigma2[6] = 1^2 + 2^2 + 3^2 + 6^2 = 20.
		// Computing using a modification of the sieve of Eratosthenes.
		long[] sigma2 = new long[LIMIT];
		fill(sigma2, 1, sigma2.length, 1);
		for (int i = 2; i < sigma2.length; i++)
			if (sigma2[i] == 1) for (int j = i; j < sigma2.length; j += i) sigma2[j] *= powerSquareSum(j, i);
		long sum = 0;
		SquareTester sqt = new SquareTester(3 * 5 * 7 * 11 * 13 * 17);
		for (int i = 1; i < sigma2.length; i++) if (sqt.isPerfectSquare(sigma2[i])) sum += i;
		return Long.toString(sum);
	}

	// Consider the set of all squared natural numbers, i.e. {0, 1, 4, 9, 16, 25, ...}.
	// When this set is viewed modulo some number n, usually not every residue is in the set.
	// For example, all squares modulo 3 is {0, 1} - so a perfect square modulo 3 is never 2.
	// By choosing a suitably large modulus, we can quickly exclude many numbers that can't be perfect squares.
	private static final class SquareTester {
		// isResidue[i] is true iff there exists a natural number k such that i = k^2 mod modulus.
		// Hence for any i, if isResidue[i mod modulus] is false, then i is not a perfect square.
		private boolean[] isResidue;

		// Any product of unique small prime numbers excluding 2 makes a good modulus
		// that leads to fast tests. But the behavior is correct for any modulus >= 1.
		SquareTester(int modulus) {
			if (modulus < 1) throw new IllegalArgumentException();
			isResidue = new boolean[modulus];
			for (int i = 0; i < modulus; i++) isResidue[(int) ((long) i * i % modulus)] = true;
		}

		@Contract(pure = true)
		boolean isPerfectSquare(long x) {
			// Reject many but not all numbers that aren't a perfect square.
			// This speed optimization can be omitted without affecting correctness.
			if (!isResidue[(int) (x % isResidue.length)]) return false;
			// A complete algorithm for detecting squares
			long y = 0;
			for (long i = 1L << 31; i != 0; i >>>= 1) {
				y |= i;
				if (y > 3037000499L || y * y > x) y ^= i;
			}
			return y * y == x;
		}
	}
}
