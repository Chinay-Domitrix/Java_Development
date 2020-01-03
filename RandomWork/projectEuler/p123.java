import org.jetbrains.annotations.NotNull;

public final class p123 extends EulerSolution {
	private static final int PRIME_LIMIT = 1000000;
	private static final long THRESHOLD = 10000000000L;

	public static void main(String[] args) {
		System.out.println(new p123().run());
	}

	@NotNull String run() {
		int[] primes = Library.listPrimes(PRIME_LIMIT);
		for (int n = 5; n <= primes.length; n += 2) {
			/*
			 * Using the result from Project Euler #120, we know that
			 * (a-1)^n + (a+1)^n mod a^2 = if n is even then 2 else 2an.
			 * Since 2 is tiny, we can skip the n is even case.
			 * a is the n'th (1-based) prime number, so a > n. In fact for n >= 5,
			 * we have a > 2n, so we can take 2an directly without moduloing it by a^2.
			 */
			long rem = (long) n * primes[n - 1] * 2;
			if (rem > THRESHOLD) return Integer.toString(n);
		}
		throw new AssertionError("Not found");
	}
}