package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static java.util.Arrays.*;
import static java.util.stream.IntStream.range;
import static projectEuler.programs.Library.*;

public final class p111 extends EulerSolution {
	private static final int DIGITS = 10;
	private int[] primes;

	public static void main(String[] args) {
		System.out.println(new p111().run());
	}

	@Contract(pure = true)
	private static long toInteger(@NotNull int[] digits) {
		long result = 0;
		for (int x : digits) result = (result * 10) + x;
		return result;
	}

	@Contract(pure = true)
	private static long pow(int x, int y) {
		return range(0, y).mapToLong(i -> x).reduce(1, (a, b) -> a * b);
	}

	@NotNull String run() {
		primes = listPrimes((int) sqrt(pow(10, DIGITS)));
		long total = 0;
		for (int digit = 0; digit < 10; digit++) { // For each repeating digit
			for (int rep = DIGITS; rep >= 0; rep--) { // Search by the number of repetitions in decreasing order
				long sum = 0;
				int[] digits = new int[DIGITS];
				long count = pow(9, DIGITS - rep);
				level2:
				for (long i = 0; i < count; i++) { // Try all possibilities for filling the non-repeating digits
					// Build initial array. For example, if DIGITS=7, digit=5, rep=4, i=123, then the array will be filled with 5,5,5,5,1,4,7.
					fill(digits, 0, rep, digit);
					long temp = i;
					for (int j = 0; j < DIGITS - rep; j++) {
						int d = (int) (temp % 9);
						// Skip the repeating digit
						if (d >= digit) d++;
						// If this is true, then after sorting, the array will be in an already-tried configuration
						if (j > 0 && d > digits[DIGITS - j]) continue level2;
						digits[DIGITS - 1 - j] = d;
						temp /= 9;
					}
					sort(digits); // Start at lowest permutation
					do { // Go through all permutations
						if (digits[0] > 0) { // Skip if the number has a leading zero, which means it has less than DIGIT digits
							long num = toInteger(digits);
							if (isPrime(num)) sum += num;
						}
					} while (nextPermutation(digits));
				}
				if (sum > 0) { // Primes found; skip all lesser repetitions
					total += sum;
					break;
				}
			}
		}
		return Long.toString(total);
	}

	// Only valid if 1 < n <= 10^DIGITS
	@Contract(pure = true)
	private boolean isPrime(long n) {
		return stream(primes).noneMatch(p -> n % p == 0);
	}
}