package programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public final class p155 extends EulerSolution {
	private static final int SIZE = 18;

	public static void main(String[] args) {
		System.out.println(new p155().run());
	}

	// Warning: Running this solution requires about 600 MiB of memory
	@NotNull String run() {
		@SuppressWarnings("unchecked")
		// possible[i] holds all the possible capacitance values of a series/parallel capacitor network that uses exactly i capacitors of 60 uF each
				Set<FastFraction>[] possible = new Set[SIZE + 1];
		possible[0] = new HashSet<>();
		possible[1] = new HashSet<>();
		possible[1].add(new FastFraction(60, 1));
		// Union of every possible[i]
		Set<FastFraction> all = new HashSet<>(possible[1]);
		for (int i = 2; i <= SIZE; i++) {
			Set<FastFraction> poss = new HashSet<>();
			for (int j = 1; j <= i - j; j++)
				for (FastFraction a : possible[j])
					for (FastFraction b : possible[i - j]) {
						poss.add(a.add(b)); // Parallel
						poss.add(a.reciprocalAdd(b)); // Series
					}
			possible[i] = poss;
			all.addAll(poss);
		}
		return Integer.toString(all.size());
	}

	// A fraction that uses int for storage and long for computation, but switches to BigInteger when necessary.
	private static final class FastFraction {
		final int numerator;
		final int denominator;
		final Fraction bigFraction;

		FastFraction(long num, long den) {
			if (den <= 0) throw new IllegalArgumentException();
			int n = (int) num;
			int d = (int) den;
			if (n == num && d == den) {
				int gcd = Library.gcd(n, d);
				if (gcd > 1) {
					n /= gcd;
					d /= gcd;
				}
				numerator = n;
				denominator = d;
				bigFraction = null;
			} else {
				FastFraction temp = new FastFraction(new Fraction(BigInteger.valueOf(num), BigInteger.valueOf(den)));
				numerator = temp.numerator;
				denominator = temp.denominator;
				bigFraction = temp.bigFraction;
			}
		}

		FastFraction(@NotNull Fraction frac) {
			if (frac.numerator.bitLength() <= 31 && frac.denominator.bitLength() <= 31) {
				numerator = frac.numerator.intValue();
				denominator = frac.denominator.intValue();
				bigFraction = null;
			} else {
				numerator = 0;
				denominator = 0;
				bigFraction = frac;
			}
		}

		Fraction toFraction() {
			return (bigFraction == null) ? new Fraction(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator)) : bigFraction;
		}

		@NotNull
		@Contract("_ -> new")
		FastFraction add(FastFraction other) {
			if (bigFraction == null && other.bigFraction == null) {
				long num = (long) numerator * other.denominator + (long) other.numerator * denominator;
				long den = (long) denominator * other.denominator;
				return new FastFraction(num, den);
			} else
				return new FastFraction(toFraction().add(other.toFraction()));
		}

		// Returns 1 / (1/this + 1/other), also equal to (this * other) / (this + other).
		@NotNull
		@Contract("_ -> new")
		FastFraction reciprocalAdd(FastFraction other) {
			if (bigFraction == null && other.bigFraction == null) {
				long num = (long) numerator * other.numerator, den = (long) numerator * other.denominator + (long) other.numerator * denominator;
				return new FastFraction(num, den);
			} else {
				Fraction x = this.toFraction(), y = other.toFraction();
				return new FastFraction(x.multiply(y).divide(x.add(y)));
			}
		}

		@Contract(value = "null -> false", pure = true)
		public boolean equals(Object obj) {
			if (!(obj instanceof FastFraction)) return false;
			FastFraction other = (FastFraction) obj;
			return ((bigFraction == null) && (other.bigFraction == null)) ? ((numerator == other.numerator) && (denominator == other.denominator)) : toFraction().equals(other.toFraction());
		}

		public int hashCode() {
			return (bigFraction == null) ? (numerator + (denominator * 1204805)) : bigFraction.hashCode(); // Some arbitrary constant to spread around the bits
		}
	}
}
