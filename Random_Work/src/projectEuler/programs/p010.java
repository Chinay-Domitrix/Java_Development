package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import static java.lang.System.out;
import static java.util.Arrays.stream;
import static projectEuler.programs.Library.listPrimes;

public final class p010 extends EulerSolution {
	/*
	 * Call the sieve of Eratosthenes and sum the primes found.
	 * A conservative upper bound for the sum is 2000000^2, which fits in a Java long type.
	 */
	private static final int LIMIT = 2000000;

	public static void main(String[] args) {
		out.println(new p010().run());
	}

	@NotNull String run() {
		long sum = stream(listPrimes(LIMIT - 1)).asLongStream().sum();
		return Long.toString(sum);
	}
}