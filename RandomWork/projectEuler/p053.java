import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

import static java.math.BigInteger.TEN;

public final class p053 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p053().run());
	}

	@NotNull String run() {
		BigInteger MILLION = TEN.pow(6);
		int count = 0;
		for (int n = 1; n <= 100; n++)
			for (int r = 0; r <= n; r++) if (Library.binomial(n, r).compareTo(MILLION) > 0) count++;
		return Integer.toString(count);
	}
}