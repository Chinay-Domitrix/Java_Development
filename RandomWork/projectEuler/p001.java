/*
 * Solution to Project Euler problem 1
 * Copyright (c) Project Nayuki. All rights reserved.
 *
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import org.jetbrains.annotations.NotNull;

public final class p001 implements EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p001().run());
	}

	/*
	 * Computers are fast, so we can implement this solution directly without any clever math.
	 * A conservative upper bound for the sum is 1000 * 1000, which fits in a Java int type.
	 */
	@NotNull
	public String run() {
		int sum = 0;
		for (int i = 0; i < 1000; i++) if (i % 3 == 0 || i % 5 == 0) sum += i;
		return Integer.toString(sum);
	}
}