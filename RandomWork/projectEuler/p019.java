/*
 * Solution to Project Euler problem 19
 * Copyright (c) Project Nayuki. All rights reserved.
 *
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class p019 implements EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p019().run());
	}

	// Return value: 0 = Sunday, 1 = Monday, ..., 6 = Saturday.
	@Contract(pure = true)
	private static int dayOfWeek(int year, int month, int day) {
		if ((year < 0) || (year > 10000) || (month < 1) || (month > 12) || (day < 1) || (day > 31))
			throw new IllegalArgumentException();
		// Zeller's congruence algorithm
		int m = (month - 3 + 4800) % 4800;
		int y = (year + m / 12) % 400;
		m %= 12;
		return (((y + (y / 4)) - (y / 100)) + (((13 * m) + 2) / 5) + day + 2) % 7;
	}

	/**
	 * We use Zeller's congruence to compute the day of week when given the year, month, and day.
	 * Then we simply check the first day of all the months in the given range by brute force.
	 * <p>
	 * Zeller's congruence is well-known and a bit long to explain.
	 *
	 * @see <a href="http://en.wikipedia.org/wiki/Zeller%27s_congruence">http://en.wikipedia.org/wiki/Zeller%27s_congruence</a>
	 */
	@NotNull
	public String run() {
		int count = 0;
		for (int y = 1901; y <= 2000; y++) for (int m = 1; m <= 12; m++) if (dayOfWeek(y, m, 1) == 0) count++; // Sunday
		return Integer.toString(count);
	}

}
