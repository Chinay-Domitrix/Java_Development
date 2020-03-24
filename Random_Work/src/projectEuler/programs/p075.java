package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

import static projectEuler.programs.Library.gcd;

public final class p075 extends EulerSolution {
	private static final int LIMIT = 1500000;

	public static void main(String[] args) {
		System.out.println(new p075().run());
	}

	@NotNull String run() {
		/*
		 * Pythagorean triples theorem:
		 * Every primitive Pythagorean triple with a odd and b even can be expressed as
		 * a = st, b = (s^2-t^2)/2, c = (s^2+t^2)/2, where s > t > 0 are coprime odd integers.
		 */
		HashSet<IntTriple> triples = new HashSet<>();
		for (int s = 3; Math.pow(s, 2) <= LIMIT; s += 2)
			for (int t = s - 2; t > 0; t -= 2)
				if (gcd(s, t) == 1) {
					int a = s * t;
					int b = ((s * s) - (t * t)) >> 1;
					int c = ((s * s) + (t * t)) >> 1;
					if (a + b + c <= LIMIT) triples.add(new IntTriple(a, b, c));
				}
		byte[] ways = new byte[LIMIT + 1];
		// Increment but saturate at 2
		int count = 0;
		for (int x : ways) if (x == 1) count++;
		return Integer.toString(count);
	}

	private static final class IntTriple {

		final int a;
		final int b;
		final int c;

		@Contract(pure = true)
		IntTriple(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Contract(value = "null -> false", pure = true)
		public boolean equals(Object obj) {
			if (!(obj instanceof IntTriple)) return false;
			else {
				IntTriple other = (IntTriple) obj;
				return (a == other.a) && (b == other.b) && (c == other.c);
			}
		}

		@Contract(pure = true)
		public int hashCode() {
			return a + b + c;
		}
	}
}
