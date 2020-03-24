package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import static java.lang.Integer.MAX_VALUE;
import static java.util.stream.IntStream.range;
import static projectEuler.programs.Library.gcd;
import static projectEuler.programs.Library.powMod;

public final class p182 extends EulerSolution {
	private static final int P = 1009;
	private static final int Q = 3643;
	private static final int TOTIENT = (P - 1) * (Q - 1);

	public static void main(String[] args) {
		System.out.println(new p182().run());
	}

	@NotNull
	private static int[] countAllUnconcealed(int prime) {
		return range(0, prime - 1).map(e -> (gcd(e, prime - 1) == 1) ? countUnconcealed(prime, e) : MAX_VALUE).toArray();
	}

	private static int countUnconcealed(int modulus, int e) {
		return (int) range(0, modulus).filter(m -> powMod(m, e, modulus) == m).count();
	}

	@NotNull String run() {
		int[] numUnconcealedP = countAllUnconcealed(P);
		int[] numUnconcealedQ = countAllUnconcealed(Q);
		int minUnconcealedP = MAX_VALUE;
		for (int x : numUnconcealedP) minUnconcealedP = Math.min(x, minUnconcealedP);
		int minUnconcealedQ = MAX_VALUE;
		for (int x : numUnconcealedQ) minUnconcealedQ = Math.min(x, minUnconcealedQ);
		long sum = 0;
		for (int e = 0; e < TOTIENT; e++)
			if ((numUnconcealedP[e % (P - 1)] == minUnconcealedP) && (numUnconcealedQ[e % (Q - 1)] == minUnconcealedQ))
				sum += e;
		return Long.toString(sum);
	}
}