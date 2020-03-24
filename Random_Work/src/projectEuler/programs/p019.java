package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class p019 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p019().run());
	}

	// Return value: 0 = Sunday, 1 = Monday, ..., 6 = Saturday.
	@Contract(pure = true)
	private static int dayOfWeek(int year, int month) {
		assert (year >= 0) && (year <= 10000) && (month >= 1) && (month <= 12);
		// Zeller's congruence algorithm
		int m = (month - 3 + 4800) % 4800;
		int y = (year + m / 12) % 400;
		m %= 12;
		return (((y + (y >> 2)) - (y / 100)) + (((13 * m) + 2) / 5) + 1 + 2) % 7;
	}

	/**
	 * We use Zeller's congruence to compute the day of week when given the year, month, and day.
	 * Then we simply check the first day of all the months in the given range by brute force.
	 * <p>
	 * Zeller's congruence is well-known and a bit long to explain.
	 *
	 * @see <a href="http://en.wikipedia.org/wiki/Zeller%27s_congruence">http://en.wikipedia.org/wiki/Zeller%27s_congruence</a>
	 */
	@NotNull String run() {
		int count = 0;
		for (int y = 1901; y <= 2000; y++) for (int m = 1; m <= 12; m++) if (dayOfWeek(y, m) == 0) count++; // Sunday
		return Integer.toString(count);
	}
}