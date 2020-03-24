package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static java.util.Arrays.binarySearch;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.iterate;
import static projectEuler.programs.Library.listPrimes;
import static projectEuler.programs.Library.sqrt;

public final class p146 extends EulerSolution {
	private static final int LIMIT = 150000000;

	/*
	 * Right off the bat, we can exclude 90% of the candidates by the following observations:
	 * - If n = 1 mod 2, then n^2 + 1 = 0 mod 2 which is composite.
	 * - Thus we require n = 0 mod 2.
	 * - If n = 1 mod 5, then n^2 + 9 = 0 mod 5 which is composite.
	 * - If n = 2 mod 5, then n^2 + 1 = 0 mod 5 which is composite.
	 * - If n = 3 mod 5, then n^2 + 1 = 0 mod 5 which is composite.
	 * - If n = 4 mod 5, then n^2 + 9 = 0 mod 5 which is composite.
	 * - Thus we require n = 0 mod 5.
	 * - Taking these two together and using the Chinese remainder theorem (CRT), we require n = 0 mod 10.
	 *
	 * For each value of n, after we generate the set {n^2 + 1, n^2 + 3, ..., n^2 + 27}, it's more efficient to take each
	 * prime number and test whether it divides any number, rather than take each number and test it against all prime numbers.
	 * This is because some numbers in this set are prime so the latter method tests some numbers against all the primes;
	 * the former method will bail out early as soon as ~any~ number in the set has a small prime factor.
	 *
	 * The rest of the algorithm is implemented straightforwardly.
	 */
	private static final long[] INCREMENTS = {1, 3, 7, 9, 13, 27}; // Must be in non-decreasing order
	private static final long maxNumber = (long) LIMIT * LIMIT + INCREMENTS[INCREMENTS.length - 1];
	private static final int[] primes = listPrimes((int) sqrt(maxNumber));

	public static void main(String[] args) {
		System.out.println(new p146().run());
	}

	private static boolean hasConsecutivePrimes(int n) {
		// Generate the set of numbers to test for primality
		long n2 = (long) n * n;
		long[] temp = stream(INCREMENTS).map(increment -> n2 + increment).toArray();

		// Test that each number is prime.
		// Note: The nesting of the loops can be reversed, but this way is much faster.
		for (int p : primes) for (long x : temp) if (x != p && x % p == 0) return false;

		// Test that each number that is not an increment is composite.
		// This checks that the prime numbers we found are in fact consecutive.
		return iterate(1, i -> i < INCREMENTS[INCREMENTS.length - 1], i -> i + 1).noneMatch(i -> (binarySearch(INCREMENTS, i) < 0) && isPrime(n2 + i));
	}

	@Contract(pure = true)
	private static boolean isPrime(long n) {
		int end = (int) sqrt(n);
		return stream(primes).takeWhile(p -> p <= end).noneMatch(p -> (n != p) && ((n % p) == 0));
	}

	@NotNull String run() {
		long sum = iterate(0, n -> n < LIMIT, n -> n + 10).filter(p146::hasConsecutivePrimes).asLongStream().sum();
		return Long.toString(sum);
	}
}