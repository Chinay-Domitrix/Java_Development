package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import static java.util.Arrays.fill;
import static java.util.stream.IntStream.iterate;
import static java.util.stream.IntStream.range;

public final class p179 extends EulerSolution {
	private static final int LIMIT = Library.pow(10, 7);

	public static void main(String[] args) {
		System.out.println(new p179().run());
	}

	@NotNull String run() {
		int[] numDivisors = new int[LIMIT + 1];
		fill(numDivisors, 2); // Invalid for indexes 0 and 1
		range(2, numDivisors.length).forEachOrdered(i -> iterate(i * 2, j -> j < numDivisors.length, j -> j + i).forEachOrdered(j -> numDivisors[j]++));
		int count = (int) range(2, numDivisors.length - 1).filter(i -> numDivisors[i] == numDivisors[i + 1]).count();
		return Integer.toString(count);
	}
}