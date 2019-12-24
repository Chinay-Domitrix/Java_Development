import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

import static java.math.BigInteger.*;

public final class p267 extends EulerSolution {
	private static final int TRIALS = 1000;

	public static void main(String[] args) {
		System.out.println(new p267().run());
	}

	// Returns the cumulative binomial probability index.
	private static int calcBillionaireProbability(double betProportion) {
		double initCapital = 1;
		int i;
		for (i = 0; i <= TRIALS; i++) { // Number of losses
			double finalCapital = initCapital * Math.pow(1 - betProportion, i) * Math.pow(1 + betProportion * 2, TRIALS - i);
			if (finalCapital < 1e9) break;
		}
		return i; // Range [0, TRIALS + 1]
	}

	// Converts a fraction to a correctly rounded decimal string.
	@NotNull
	private static String roundToDecimal(@NotNull Fraction num, int places) {
		if (num.numerator.signum() == -1)
			return "-" + roundToDecimal(new Fraction(num.numerator.negate(), num.denominator), places);
		num = new Fraction(num.numerator.multiply(TEN.pow(places)), num.denominator);
		BigInteger whole = num.numerator.divide(num.denominator);
		Fraction frac = new Fraction(num.numerator.mod(num.denominator), num.denominator);
		Fraction HALF = new Fraction(ONE, valueOf(2));
		if (frac.compareTo(HALF) > 0 || frac.compareTo(HALF) == 0 && whole.testBit(0)) whole = whole.add(ONE);
		StringBuilder sb = new StringBuilder(whole.toString());
		while (sb.length() < places + 1) sb.insert(0, '0');
		sb.insert(sb.length() - places, '.');
		return sb.toString();
	}

	/*
	 * When you win a coin toss, your capital is multiplied by (1 + 2f).
	 * Whenever you lose, your capital is multiplied by (1 - f).
	 * Thus the game's result is independent of the order of wins and losses;
	 * what matters is only the total numbers of each outcome.
	 *
	 * Suppose you have n tosses and w wins. Then there are n - w losses.
	 * By the binomial theorem, this outcome happens (n choose w) times out of 2^n.
	 * Moreover, the final capital is 1 * (1 + 2f)^w * (1 - f)^(n - w).
	 *
	 * Some parts of this algorithm use accurate computations:
	 * - Sum of binomial coefficients in bigint, for the probability.
	 * - Conversion of the probability bigint fraction to decimal string.
	 * Some parts are inaccurate or are based on heuristics:
	 * - Calculating the final capital using floating-point arithmetic,
	 * for each bet proportion and number of wins+losses.
	 * - Sampling the continuous input interval of [0.0, 1.0]
	 * to try to maximize the value of the function.
	 * Overall this solution is not provably mathematically correct.
	 */
	@NotNull String run() {
		// Heuristic sampling algorithm.
		// At level 1 we test {1/2}. At level 2 we test {1/4, 3/4}.
		// At level 3 we test {1/8, 3/8, 5/8, 7/8}. Et cetera.
		int maxIndex = -1;
		int prevChangeLevel = 1;
		for (int level = 1; level - prevChangeLevel <= 8; level++) {
			if (level > 30) throw new AssertionError();
			double scaler = Math.pow(2, -level);
			for (int i = 1, end = 1 << level; i < end; i += 2) {
				int index = calcBillionaireProbability(i * scaler);
				if (index > maxIndex) {
					maxIndex = index;
					prevChangeLevel = level;
				}
			}
		}
		// Calculate the cumulative probability: binomialSum = sum (n choose k) for 0 <= k < maxIndex
		BigInteger binomialSum = ZERO;
		for (int i = 0; i < maxIndex; i++) binomialSum = binomialSum.add(Library.binomial(TRIALS, i));
		BigInteger denominator = ONE.shiftLeft(TRIALS);
		return roundToDecimal(new Fraction(binomialSum, denominator), 12);
	}
}