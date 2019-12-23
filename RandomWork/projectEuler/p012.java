/*
 * Solution to Project Euler problem 12
 * Copyright (c) Project Nayuki. All rights reserved.
 *
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class p012 implements EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p012().run());
	}

	// Returns the number of integers in the range [1, n] that divide n.
	@Contract(pure = true)
	private static int countDivisors(int n) {
		int count = 0;
		int end = Library.sqrt(n);
		for (int i = 1; i < end; i++) if (n % i == 0) count += 2;
		if (end * end == n) count++; // Perfect square
		return count;
	}

	/*
	 * Computers are fast, so we can implement this solution directly without any clever math.
	 */
	@NotNull
	public String run() {
		int triangle = 0;
		for (int i = 1; ; i++) {
			if (Integer.MAX_VALUE - triangle < i) throw new ArithmeticException("Overflow");
			triangle += i;  // This is the ith triangle number, i.e. num = 1 + 2 + ... + i = i * (i + 1) / 2
			if (countDivisors(triangle) > 500) return Integer.toString(triangle);
		}
	}

}