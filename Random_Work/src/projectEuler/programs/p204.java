package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static projectEuler.programs.Library.listPrimes;
import static projectEuler.programs.Library.pow;

public final class p204 extends EulerSolution {
	private static final long LIMIT = pow(10, 9);
	private final int[] primes = listPrimes(100);

	public static void main(String[] args) {
		System.out.println(new p204().run());
	}

	@NotNull String run() {
		return Integer.toString(count(0, 1));
	}

	@Contract(pure = true)
	private int count(int primeIndex, long product) {
		if (primeIndex == primes.length) return (product <= LIMIT) ? 1 : 0;
		else {
			int count = 0;
			while (product <= LIMIT) {
				count += count(primeIndex + 1, product);
				product *= primes[primeIndex];
			}
			return count;
		}
	}
}