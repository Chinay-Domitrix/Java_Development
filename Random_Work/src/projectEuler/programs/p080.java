package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.stream.IntStream;

import static java.math.BigInteger.*;

public final class p080 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p080().run());
	}

	private static BigInteger sqrt(BigInteger x) {
		// Find leftmost position
		int i = 0;
		while (TEN.pow(i * 2).compareTo(x) <= 0) i++;
		// Extract square root from left to right using an algorithm like long division
		BigInteger y = ZERO;
		for (; i >= 0; i--) {
			// Try every value for next digit
			int j;
			BigInteger delta = null;
			for (j = 9; j >= 0; j--) {
				BigInteger temp = valueOf(j).multiply(TEN.pow(i));
				delta = y.shiftLeft(1).add(temp).multiply(temp);
				if (delta.compareTo(x) <= 0) break;
			}
			assert j >= 0;
			x = x.subtract(delta); // Adjust the remainder
			y = y.add(valueOf(j).multiply(TEN.pow(i))); // Add the new digit
		}

		return y;
	}

	@NotNull String run() {
		int sum = 0;
		for (int i = 1; i <= 100; i++) {
			BigInteger x = valueOf(i).multiply(TEN.pow(100 * 2)); // Shift left so that we can obtain 100 digits after the decimal point
			BigInteger y = sqrt(x);
			if (!y.multiply(y).equals(x)) { // Skip perfect squares
				// Strip rightmost digits so that we have exactly 100 decimal digits (some are before the decimal point)
				String s = y.toString().substring(0, 100);
				sum += IntStream.range(0, s.length()).map(j -> s.charAt(j) - '0').sum();
			}
		}
		return Integer.toString(sum);
	}
}