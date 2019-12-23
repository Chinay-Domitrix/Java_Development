/*
 * Solution to Project Euler problem 112
 * Copyright (c) Project Nayuki. All rights reserved.
 *
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

public final class p112 implements EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p112().run());
	}

	private static boolean isBouncy(int x) {
		if (x < 100)
			return false;
		else {
			boolean nonincreasing = true;
			boolean nondecreasing = true;
			int lastDigit = x % 10;
			x /= 10;
			while (x != 0) {
				int digit = x % 10;
				if (digit > lastDigit)
					nondecreasing = false;
				else if (digit < lastDigit)
					nonincreasing = false;
				lastDigit = digit;
				x /= 10;
			}
			return !nonincreasing && !nondecreasing;
		}
	}

	public String run() {
		int bouncy = 0;
		for (int i = 1; ; i++) {
			if (isBouncy(i))
				bouncy++;
			if (bouncy * 100 == i * 99)
				return Integer.toString(i);
		}
	}

}
