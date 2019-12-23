/*
 * Solution to Project Euler problem 7
 * Copyright (c) Project Nayuki. All rights reserved.
 *
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


import org.jetbrains.annotations.NotNull;

public final class p007 implements EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p007().run());
	}

	/*
	 * Computers are fast, so we can implement this solution by testing each number
	 * individually for primeness, instead of using the more efficient sieve of Eratosthenes.
	 */
	@NotNull
	public String run() {
		for (int i = 2, count = 0; ; i++)
			if (Library.isPrime(i)) {
				count++;
				if (count == 10001) return Integer.toString(i);
			}
	}
}