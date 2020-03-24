package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import static java.lang.System.out;
import static java.util.stream.IntStream.range;

public final class p001 extends EulerSolution {
	public static void main(String[] args) {
		out.println(new p001().run());
	}

	/*
	 * Computers are fast, so we can implement this solution directly without any clever math.
	 * A conservative upper bound for the sum is 1000 * 1000, which fits in a Java int type.
	 */
	@NotNull String run() {
		int sum = range(0, 1000).filter(i -> i % 3 == 0 || i % 5 == 0).sum();
		return Integer.toString(sum);
	}
}