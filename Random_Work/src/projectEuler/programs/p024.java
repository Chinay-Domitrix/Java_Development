package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;
import static projectEuler.programs.Library.nextPermutation;

public final class p024 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p024().run());
	}

	/*
	 * We initialize an array as the lowest permutation of the given digits, which is the sequence
	 * (0,1,2,3,4,5,6,7,8,9). Then we call the next permutation algorithm on it 999 999 times
	 * (because the index in the problem is 1-based), and stringify the resulting sequence.
	 *
	 * The next permutation algorithm is well-known and a bit long to explain.
	 * See: https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
	 */

	@NotNull String run() {
		int[] array = range(0, 10).toArray();
		range(0, 999999).forEachOrdered(i -> {
			assert nextPermutation(array);
		});
		return stream(array).mapToObj(String::valueOf).collect(joining());
	}
}