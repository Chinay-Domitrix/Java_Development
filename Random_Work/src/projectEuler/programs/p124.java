package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static java.lang.Integer.compare;
import static java.util.Arrays.fill;
import static java.util.Arrays.sort;
import static java.util.stream.IntStream.range;

public final class p124 extends EulerSolution {
	private static final int LIMIT = 100000;

	public static void main(String[] args) {
		System.out.println(new p124().run());
	}

	@NotNull String run() {
		// Modification of the sieve of Eratosthenes
		int[] rads = new int[LIMIT + 1];
		fill(rads, 1, rads.length, 1);
		for (int i = 2; i < rads.length; i++) if (rads[i] == 1) for (int j = i; j < rads.length; j += i) rads[j] *= i;
		IntPair[] data = range(0, LIMIT).mapToObj(i -> new IntPair(rads[i + 1], i + 1)).toArray(IntPair[]::new);
		sort(data);
		return Integer.toString(data[10000 - 1].b);
	}

	private static final class IntPair implements Comparable<IntPair> {
		final int a;
		final int b;

		@Contract(pure = true)
		IntPair(int a, int b) {
			this.a = a;
			this.b = b;
		}

		public int compareTo(@NotNull IntPair other) {
			return (a != other.a) ? compare(a, other.a) : compare(b, other.b);
		}
	}
}
