package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

import static java.math.BigInteger.ONE;

public final class p016 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p016().run());
	}

	/*
	 * We implement this solution in a straightforward way with help from BigInteger.
	 */
	@NotNull String run() {
		String temp = ONE.shiftLeft(1000).toString();
		int sum = IntStream.range(0, temp.length()).map(i -> temp.charAt(i) - '0').sum();
		return Integer.toString(sum);
	}
}