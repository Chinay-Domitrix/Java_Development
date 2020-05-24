package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.math.BigInteger.*;
import static java.util.stream.Stream.of;
import static projectEuler.programs.Library.isSquare;
import static projectEuler.programs.Library.sqrt;

public final class p066 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p066().run());
	}

	// Returns the smallest x such that x > 0 and there exists some y such that x^2 - n y^2 = 1.
	// Requires n to not be a perfect square.
	private static BigInteger smallestSolutionX(int n) {
		List<BigInteger>[] contFrac = sqrtToContinuedFraction(n);
		ArrayList<BigInteger> temp = new ArrayList<>();
		temp.addAll(contFrac[0]);
		temp.addAll(contFrac[1].subList(0, contFrac[1].size() - 1));
		Fraction val = new Fraction(temp.get(temp.size() - 1));
		for (int i = temp.size() - 2; i >= 0; i--)
			val = new Fraction(val.denominator, val.numerator).add(new Fraction(temp.get(i)));
		return ((contFrac[1].size() % 2) == 0) ? val.numerator : val.numerator.pow(2).add(val.denominator.pow(2).multiply(valueOf(n)));
	}

	// Returns the periodic continued fraction of sqrt(n). Requires n to not be a perfect square.
	// result[0] is the minimal non-periodic prefix, and result[1] is the minimal periodic tail.
	@Contract("_ -> new")
	private static List<BigInteger>[] sqrtToContinuedFraction(int n) {
		ArrayList<BigInteger> terms = new ArrayList<>();
		HashMap<QuadraticSurd, Integer> seen = new HashMap<>();
		QuadraticSurd val = new QuadraticSurd(ZERO, ONE, ONE, valueOf(n));
		do {
			seen.put(val, seen.size());
			BigInteger flr = val.floor();
			terms.add(flr);
			val = val.subtract(new QuadraticSurd(flr, ZERO, ONE, val.d)).reciprocal();
		} while (!seen.containsKey(val));
		return new List[]{terms.subList(0, seen.get(val)), terms.subList(seen.get(val), terms.size())};
	}

	/*
	 * Based on this insane theorem: Suppose D > 1 is an integer, non-perfect-square.
	 *
	 * Express sqrt(D) as the continued fraction (a0, a1, ..., a_{n-1}, (b0, b1, ..., b_{m-1})),
	 * where the sequence of b's is the periodic part.
	 *
	 * Let p/q (in lowest terms) = (a0, a1, ..., a_{n-1}, b0, b1, ..., b_{m-2}).
	 * (This is a truncation of the continued fraction with only one period minus the last term.)
	 *
	 * Then the minimum solution (x, y) for Pell's equation is given by:
	 * - (p, q) if m is even
	 * - (p^2 + D q^2, 2pq) if m is odd
	 */
	@NotNull String run() {
		int minN = -1;
		BigInteger maxX = ZERO;
		for (int n = 2; n <= 1000; n++) {
			if (isSquare(n)) continue;
			BigInteger x = smallestSolutionX(n);
			if (x.compareTo(maxX) > 0) {
				minN = n;
				maxX = x;
			}
		}
		return Integer.toString(minN);
	}

	// Represents (a + b * sqrt(d)) / c. d must not be a perfect square.
	private static final class QuadraticSurd {
		final BigInteger a;
		final BigInteger b;
		final BigInteger c;
		final BigInteger d;

		QuadraticSurd(BigInteger a, BigInteger b, @NotNull BigInteger c, BigInteger d) {
			assert c.signum() != 0;
			// Simplify
			if (c.signum() == -1) {
				a = a.negate();
				b = b.negate();
				c = c.negate();
			}
			BigInteger gcd = a.gcd(b).gcd(c);
			if (!gcd.equals(ONE)) {
				a = a.divide(gcd);
				b = b.divide(gcd);
				c = c.divide(gcd);
			}
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
		}

		@NotNull
		@Contract("_ -> new")
		QuadraticSurd subtract(@NotNull QuadraticSurd other) {
			assert d.equals(other.d);
			return new QuadraticSurd(a.multiply(other.c).subtract(other.a.multiply(c)), b.multiply(other.c).subtract(other.b.multiply(c)), c.multiply(other.c), d);
		}

		@NotNull
		@Contract(" -> new")
		QuadraticSurd reciprocal() {
			return new QuadraticSurd(a.multiply(c).negate(), b.multiply(c), b.multiply(b).multiply(d).subtract(a.multiply(a)), d);
		}

		BigInteger floor() {
			BigInteger temp = sqrt(b.multiply(b).multiply(d));
			if (b.signum() == -1) temp = temp.add(ONE).negate();
			temp = temp.add(a);
			if (temp.signum() == -1) temp = temp.subtract(c.subtract(ONE));
			return temp.divide(c);
		}

		@Contract(value = "null -> false", pure = true)
		public boolean equals(Object obj) {
			if (!(obj instanceof QuadraticSurd)) return false;
			else {
				var other = (QuadraticSurd) obj;
				return a.equals(other.a) && b.equals(other.b) && c.equals(other.c) && d.equals(other.d);
			}
		}

		public int hashCode() {
			return of(a, b, c, d).mapToInt(BigInteger::hashCode).sum();
		}

		public String toString() {
			return String.format("(%d + %d*sqrt(%d)) / %d", a, b, d, c);
		}
	}
}