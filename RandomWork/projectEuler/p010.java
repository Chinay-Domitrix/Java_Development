import org.jetbrains.annotations.NotNull;

public final class p010 extends EulerSolution {
	/*
	 * Call the sieve of Eratosthenes and sum the primes found.
	 * A conservative upper bound for the sum is 2000000^2, which fits in a Java long type.
	 */
	private static final int LIMIT = 2000000;

	public static void main(String[] args) {
		System.out.println(new p010().run());
	}

	@NotNull String run() {
		long sum = 0;
		for (int p : Library.listPrimes(LIMIT - 1)) sum += p;
		return Long.toString(sum);
	}
}