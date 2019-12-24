import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

import static java.math.BigInteger.valueOf;

public final class p329 extends EulerSolution {
	private static final int START_NUM = 1;

	/*
	 * We simulate by brute force all the possible paths that the frog can take, and
	 * multiply and sum the probabilities along the way. The frog is initially positioned
	 * at one of the 500 squares and makes a croak. Then it jumps left or right 14 times
	 * and makes a croak after each jump (thus there are 15 croaks per simulation).
	 * Overall, there are exactly 500 * 2^14 = 8.192 million paths to examine.
	 *
	 * Let's look at how the probabilities work for a particular path:
	 * - Because the frog starts at each square with uniform probability and jumps left/right
	 * with equal probability, the probability of any particular path is 1 / (500 * 2^14).
	 * - For any given path, it can generate exactly 2^15 croak sequences, but each sequence
	 * doesn't have the same probability. Look at the sequence of numbers that the frog
	 * lands on in this path, e.g. {2, 1, 0, 1, 2, 3, 4, 3, ...}. Now look at sequence of
	 * whether each of these numbers is prime, i.e. {P, N, N, N, P, P, N, P, ...}.
	 * The probability of the desired croak sequence is equal to (2/3)^(number of indexes
	 * where the croak letter matches the primeness sequence) * (1/3)^(number of indexes
	 * where the croak letter mismatches the primeness sequence). We can simplify this
	 * expression to just 2^(number of matching indexes) / 3^15.
	 *
	 * So for all the 8192000 paths, we calculate the probability that each path matches
	 * the target croak sequence, sum all these probabilities, and divide by 8192000.
	 * We perform calculations in fraction or integer form (not floating point),
	 * and extract the numerator and denominator of the final simplified result.
	 *
	 * Note that this analysis is correct even considering the rule that when
	 * the frog is at one of the range endpoints, it only has one possible move
	 * (e.g. if the frog is at square 1, then it must move to square 2).
	 */
	private static final int END_NUM = 500;
	private static final String CROAK_SEQ = "PPPPNNPPPNPPNPN";
	private static final int NUM_JUMPS = CROAK_SEQ.length() - 1;
	private static final int NUM_TRIALS = 1 << NUM_JUMPS;

	public static void main(String[] args) {
		System.out.println(new p329().run());
	}

	@NotNull String run() {
		long globalNumerator = 0;
		boolean[] isPrime = Library.listPrimality(END_NUM);
		// For each starting square
		for (int i = START_NUM; i <= END_NUM; i++) {
			// For each sequence of jumps
			for (int j = 0; j < NUM_TRIALS; j++) {
				// Set initial position and croak
				int pos = i;
				int trialNumerator = 1;
				if (isPrime[pos]) trialNumerator *= 2;
				// Simulate each jump and croak
				for (int k = 0; k < NUM_JUMPS; k++) {
					if (pos <= START_NUM) pos++; // Forced move
					else if (pos >= END_NUM) pos--; // Forced move
					else if (((j >>> k) & 1) == 0) pos++; // Chosen move
					else pos--; // Chosen move
					// Multiply the running probability by 2/3 if primeness of current position
					// matches croak sequence at current index, otherwise multiply by 1/3
					if (isPrime[pos] == (CROAK_SEQ.charAt(k + 1) == 'P')) trialNumerator *= 2;
				}
				globalNumerator += trialNumerator;
			}
		}

		// Calculate final probability fraction
		BigInteger globalDenominator = valueOf(END_NUM + 1L - START_NUM).shiftLeft(NUM_JUMPS).multiply(valueOf(3).pow(CROAK_SEQ.length()));
		return new Fraction(valueOf(globalNumerator), globalDenominator).toString();
	}
}