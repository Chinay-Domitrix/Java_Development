package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Stack;

import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.valueOf;
import static java.math.RoundingMode.HALF_EVEN;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;
import static projectEuler.programs.Library.binomial;
import static projectEuler.programs.Library.factorial;

public final class p493 extends EulerSolution {
	private static final int NUM_COLORS = 7;
	private static final int BALLS_PER_COLOR = 10;
	private static final int NUM_PICKED = 20;
	private BigInteger numerator = ZERO;

	public static void main(String[] args) {
		System.out.println(new p493().run());
	}

	private static BigInteger divideExactly(@NotNull BigInteger x, BigInteger y) {
		BigInteger[] temp = x.divideAndRemainder(y);
		assert temp[1].signum() == 0 : "Not divisible";
		return temp[0];
	}

	String run() {
		explore(NUM_PICKED, BALLS_PER_COLOR, new Stack<>());
		BigInteger denominator = binomial(NUM_COLORS * BALLS_PER_COLOR, NUM_PICKED);
		BigDecimal num = new BigDecimal(numerator);
		BigDecimal den = new BigDecimal(denominator);
		return num.divide(den, 9, HALF_EVEN).toString();
	}

	private void explore(int remain, int limit, Stack<Integer> history) {
		if (remain == 0) {
			int[] hist = new int[NUM_COLORS];
			range(0, history.size()).forEachOrdered(i -> hist[i] = history.get(i));
			int[] histogram = new int[BALLS_PER_COLOR + 1];
			stream(hist).forEachOrdered(x -> histogram[x]++);
			BigInteger count = factorial(NUM_COLORS);
			for (int x : histogram) count = divideExactly(count, factorial(x));
			for (int x : hist) count = count.multiply(binomial(BALLS_PER_COLOR, x));
			int distinctColors = history.size();
			numerator = numerator.add(count.multiply(valueOf(distinctColors)));
		} else if (history.size() < NUM_COLORS) for (int i = Math.min(limit, remain); i > 0; i--) {
			history.push(i);
			explore(remain - i, i, history);
			history.pop();
		}
	}
}