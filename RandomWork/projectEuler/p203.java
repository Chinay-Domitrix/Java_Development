import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.HashSet;

import static java.lang.Math.*;

public final class p203 extends EulerSolution {
	private long[] primesSquared;

	public static void main(String[] args) {
		System.out.println(new p203().run());
	}

	@NotNull String run() {
		// Collect unique numbers in Pascal's triangle
		HashSet<Long> numbers = new HashSet<>();
		long max = 0;
		for (int n = 0; n <= 50; n++)
			for (int k = 0; k <= n; k++) {
				BigInteger x = Library.binomial(n, k);
				if (x.bitLength() >= 64) throw new AssertionError("Number too large to handle");
				numbers.add(x.longValue());
				max = max(x.longValue(), max);
			}
		// Prepare list of squared primes
		int[] primes = Library.listPrimes((int) Library.sqrt(max));
		primesSquared = new long[primes.length];
		for (int i = 0; i < primes.length; i++) primesSquared[i] = round(pow(primes[i], 2));
		// Sum up the squarefree numbers
		long sum = 0;
		for (long n : numbers) if (isSquarefree(n)) sum += n;
		return Long.toString(sum);
	}

	@Contract(pure = true)
	private boolean isSquarefree(long n) {
		for (long p2 : primesSquared) {
			if (p2 > n) break;
			if (n % p2 == 0) return false;
		}
		return true;
	}
}