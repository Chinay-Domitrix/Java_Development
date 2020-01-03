package programs;

import org.jetbrains.annotations.NotNull;

public final class p007 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p007().run());
	}

	/*
	 * Computers are fast, so we can implement this solution by testing each number
	 * individually for primeness, instead of using the more efficient sieve of Eratosthenes.
	 */
	@NotNull String run() {
		for (int i = 2, count = 0; ; i++)
			if (Library.isPrime(i)) {
				count++;
				if (count == 10001) return Integer.toString(i);
			}
	}
}