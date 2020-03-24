package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.stream.IntStream;

import static projectEuler.programs.Library.listPrimes;
import static projectEuler.programs.Library.sqrt;

public final class p087 extends EulerSolution {
	private static final int LIMIT = 50000000;

	public static void main(String[] args) {
		System.out.println(new p087().run());
	}

	@NotNull String run() {
		int[] primes = listPrimes(sqrt(LIMIT));
		HashSet<Integer> sums = new HashSet<>();
		sums.add(0);
		for (int i = 2; i <= 4; i++) {
			HashSet<Integer> newSums = new HashSet<>();
			for (int p : primes) {
				long q = IntStream.range(0, i).mapToLong(j -> p).reduce(1, (a, b) -> a * b);
				// q = p^i
				if (q > LIMIT) break;
				int r = (int) q;
				sums.stream().mapToInt(x -> x).filter(x -> x + r <= LIMIT).mapToObj(x -> x + r).forEachOrdered(newSums::add);
			}
			sums = newSums;
		}
		return Integer.toString(sums.size());
	}
}