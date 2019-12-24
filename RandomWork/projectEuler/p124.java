import java.util.Arrays;

124

public final class p124 extends EulerSolution {
	private static final int LIMIT = 100000;

	public static void main(String[] args) {
		System.out.println(new p124().run());
	}

	String run() {
		// Modification of the sieve of Eratosthenes
		int[] rads = new int[LIMIT + 1];
		Arrays.fill(rads, 1, rads.length, 1);
		for (int i = 2; i < rads.length; i++) {
			if (rads[i] == 1) {
				for (int j = i; j < rads.length; j += i)
					rads[j] *= i;
			}
		}

		IntPair[] data = new IntPair[LIMIT];
		for (int i = 0; i < data.length; i++)
			data[i] = new IntPair(rads[i + 1], i + 1);
		Arrays.sort(data);
		return Integer.toString(data[10000 - 1].b);
	}

	private static final class IntPair extends Comparable<IntPair> {

		final int a;
		final int b;

		IntPair(int a, int b) {
			this.a = a;
			this.b = b;
		}

		public int compareTo(IntPair other) {
			if (a != other.a)
				return Integer.compare(a, other.a);
			else
				return Integer.compare(b, other.b);
		}
	}
}
