import org.jetbrains.annotations.NotNull;

public final class p006 extends EulerSolution {
	/*
	 * Computers are fast, so we can implement this solution directly without any clever math.
	 * Note that sum^2 is bounded above by (100*100)^2 and sum2 is
	 * bounded above by 100*(100^2), both of which fit in a Java int type.
	 *
	 * However for the mathematically inclined, there are closed-form formulas:
	 * sum = N(N + 1) / 2.
	 * sum2 = N(N + 1)(2N + 1) / 6.
	 * Hence sum^2 - sum2 = (N^4 / 4) + (N^3 / 6) - (N^2 / 4) - (N / 6).
	 */
	private static final int N = 100;

	public static void main(String[] args) {
		System.out.println(new p006().run());
	}

	@NotNull String run() {
		int sum = 0;
		int sum2 = 0;
		for (int i = 1; i <= N; i++) {
			sum += i;
			sum2 += i * i;
		}
		return Integer.toString(sum * sum - sum2);
	}
}