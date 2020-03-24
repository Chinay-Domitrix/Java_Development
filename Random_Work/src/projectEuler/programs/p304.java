package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

import static java.math.BigInteger.*;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.iterate;
import static projectEuler.programs.Library.listPrimes;
import static projectEuler.programs.Library.sqrt;

public final class p304 extends EulerSolution {
	private static final long BASE = 100000000000000L;
	private static final int SEARCH_RANGE = 10000000; // Number of candidates starting from BASE to search for primes. Hopefully there are 100 000 primes among here.
	private static final long MODULUS = 1234567891011L;
	private static final BigInteger MODULUS_BI = valueOf(MODULUS);
	private boolean[] isComposite; // isComposite[i] pertains to the number BASE + i

	public static void main(String[] args) {
		System.out.println(new p304().run());
	}

	private static long fibonacciMod(long n) {
		BigInteger a = ZERO;
		BigInteger b = ONE;
		for (int i = 63; i >= 0; i--) {
			BigInteger d = a.multiply(b.shiftLeft(1).subtract(a));
			BigInteger e = a.pow(2).add(b.pow(2));
			a = d;
			b = e;
			if (((n >>> i) & 1) != 0) {
				BigInteger c = a.add(b);
				a = b;
				b = c;
			}
			a = a.mod(MODULUS_BI);
			b = b.mod(MODULUS_BI);
		}
		return a.longValue();
	}

	@NotNull String run() {
		int[] primes = listPrimes((int) sqrt(BASE + SEARCH_RANGE));
		// Sieve of Eratosthenes, but starting at BASE
		isComposite = new boolean[SEARCH_RANGE];
		stream(primes).forEachOrdered(p -> iterate((int) (((((BASE + p) - 1) / p) * p) - BASE), i -> i < isComposite.length, i -> i + p).forEachOrdered(i -> isComposite[i] = true));
		long sum = 0;
		int p = 0;
		for (int i = 0; i < 100000; i++) {
			p = nextPrime(p);
			sum = (sum + fibonacciMod(BASE + p)) % MODULUS;
		}
		return Long.toString(sum);
	}

	// Returns p - BASE, where p is the next prime after n + BASE
	private int nextPrime(int n) {
		do {
			n++;
			assert n < isComposite.length : "Search range exhausted";
		} while (isComposite[n]);
		return n;
	}
}