package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

import static java.lang.System.out;
import static projectEuler.programs.Library.factorial;

public final class p020 extends EulerSolution {
	public static void main(String[] args) {
		out.println(new p020().run());
	}

	/*
	 * We do a straightforward product with help from Java's BigInteger type.
	 */
	@NotNull String run() {
		String temp = factorial(100).toString();
		int sum = IntStream.range(0, temp.length()).map(i -> temp.charAt(i) - '0').sum();
		return Integer.toString(sum);
	}
}
