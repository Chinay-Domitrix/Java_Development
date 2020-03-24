package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.valueOf;
import static projectEuler.programs.Library.pow;

public final class p014 extends EulerSolution {
	/*
	 * We compute the Collatz chain length for every integer in the range according to the iteration rule.
	 * Also, we cache the Collatz value for small integer arguments to speed up the computation.
	 */
	private static final int LIMIT = pow(10, 6);
	// Can be set to any non-negative number, but there are diminishing returns as you go larger
	private static final BigInteger CACHE_SIZE = valueOf(LIMIT);
	// Memoization
	private final int[] collatzChainLength = new int[CACHE_SIZE.intValue()];

	public static void main(String[] args) {
		System.out.println(new p014().run());
	}

	@NotNull String run() {
		int maxArg = -1;
		int maxChain = 0;
		for (int i = 1; i < LIMIT; i++) {
			int chainLen = collatzChainLength(valueOf(i));
			if (chainLen > maxChain) {
				maxArg = i;
				maxChain = chainLen;
			}
		}
		return Integer.toString(maxArg);
	}

	// Returns the Collatz chain length of the given integer with automatic caching.
	private int collatzChainLength(@NotNull BigInteger n) {
		assert n.signum() >= 0;
		if (n.compareTo(CACHE_SIZE) >= 0) return collatzChainLengthDirect(n); // Caching not available
		int index = n.intValue(); // Index in the cache
		if (collatzChainLength[index] == 0) collatzChainLength[index] = collatzChainLengthDirect(n);
		return collatzChainLength[index];
	}

	// Returns the Collatz chain length of the given integer, with the
	// first step uncached but the remaining steps using automatic caching.
	private int collatzChainLengthDirect(@NotNull BigInteger n) {
		// Else n is odd
		if (n.equals(ONE)) return 1; // Base case
		else
			return collatzChainLength(!n.testBit(0) ? n.shiftRight(1) : n.multiply(valueOf(3)).add(ONE)) + 1; // If n is even
	}
}