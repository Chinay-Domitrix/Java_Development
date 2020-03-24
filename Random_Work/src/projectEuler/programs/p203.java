package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.HashSet;

import static java.lang.Math.max;
import static java.lang.Math.round;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;
import static projectEuler.programs.Library.*;

public final class p203 extends EulerSolution {
	private long[] primesSquared;

	public static void main(String[] args) {
		System.out.println(new p203().run());
	}

	@NotNull String run() {
		// Collect unique numbers in Pascal's triangle
		var numbers = new HashSet<Long>();
		long max = 0;
		for (int n = 0; n <= 50; n++)
			for (int k = 0; k <= n; k++) {
				BigInteger x = binomial(n, k);
				assert x.bitLength() < 64 : "Number too large to handle";
				numbers.add(x.longValue());
				max = max(x.longValue(), max);
			}
		// Prepare list of squared primes
		int[] primes = listPrimes((int) sqrt(max));
		primesSquared = new long[primes.length];
		range(0, primes.length).forEachOrdered(i -> primesSquared[i] = round(pow(primes[i], 2)));
		// Sum up the squarefree numbers
		long sum = numbers.stream().mapToLong(n -> n).filter(this::isSquarefree).sum();
		return Long.toString(sum);
	}

	@Contract(pure = true)
	private boolean isSquarefree(long n) {
		return stream(primesSquared).takeWhile(p2 -> p2 <= n).noneMatch(p2 -> n % p2 == 0);
	}
}