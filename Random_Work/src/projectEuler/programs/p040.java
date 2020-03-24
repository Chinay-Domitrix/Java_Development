package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

import static projectEuler.programs.Library.pow;

public final class p040 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p040().run());
	}

	@NotNull String run() {
		var sb = new StringBuilder();
		IntStream.range(1, 1000000).forEachOrdered(sb::append);
		int prod = IntStream.rangeClosed(0, 6).map(i -> sb.charAt(pow(10, i) - 1) - '0').reduce(1, (a, b) -> a * b);
		return Integer.toString(prod);
	}
}