package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.HashMap;

import static java.math.BigInteger.*;
import static projectEuler.programs.Library.isSquare;
import static projectEuler.programs.Library.sqrt;

public final class p064 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p064().run());
	}

	// Returns the period of the continued fraction of sqrt(n)
	private static int getSqrtContinuedFractionPeriod(int n) {
		HashMap<QuadraticSurd, Integer> seen = new HashMap<>();
		QuadraticSurd val = new QuadraticSurd(ZERO, ONE, ONE, valueOf(n));
		do {
			seen.put(val, seen.size());
			val = val.subtract(new QuadraticSurd(val.floor(), ZERO, ONE, val.d)).reciprocal();
		} while (!seen.containsKey(val));
		return seen.size() - seen.get(val);
	}

	@NotNull String run() {
		int count = 0;
		for (int i = 1; i <= 10000; i++)
			if (!isSquare(i) && ((getSqrtContinuedFractionPeriod(i) % 2) == 1)) count++;
		return Integer.toString(count);
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
				QuadraticSurd other = (QuadraticSurd) obj;
				return a.equals(other.a) && b.equals(other.b) && c.equals(other.c) && d.equals(other.d);
			}
		}

		public int hashCode() {
			return a.hashCode() + b.hashCode() + c.hashCode() + d.hashCode();
		}

		public String toString() {
			return String.format("(%d + %d*sqrt(%d)) / %d", a, b, d, c);
		}
	}
}
