package programs;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.math.BigInteger.*;
import static java.util.Arrays.asList;

public final class p169 extends EulerSolution {
	private static final BigInteger NUMBER = TEN.pow(25);
	// Memoization
	private final Map<List<BigInteger>, BigInteger> ways = new HashMap<>();

	public static void main(String[] args) {
		System.out.println(new p169().run());
	}

	/*
	 * ways(n, i, j) is the number of ways that the number n can be expressed as
	 * an unordered sum of powers of 2 such that all these conditions are true:
	 * - The highest possible power is 2^i
	 * - The 2^i term is used between 0 and j times
	 * - All lower powers of 2 are used no more than 2 times
	 */

	String run() {
		return countWays(NUMBER, NUMBER.bitLength() - 1, 2).toString();
	}

	private BigInteger countWays(BigInteger number, int exponent, int repetitions) {
		List<BigInteger> key = asList(number, valueOf(exponent), valueOf(repetitions));
		if (ways.containsKey(key)) return ways.get(key);
		BigInteger result;
		if (exponent < 0) result = number.equals(ZERO) ? ONE : ZERO;
		else {
			result = countWays(number, exponent - 1, 2);
			BigInteger pow = ONE.shiftLeft(exponent);
			BigInteger upper = pow.multiply(valueOf(repetitions + 2));
			if (repetitions > 0 && pow.compareTo(number) <= 0 && number.compareTo(upper) < 0)
				result = result.add(countWays(number.subtract(pow), exponent, repetitions - 1));
		}
		ways.put(key, result);
		return result;
	}
}