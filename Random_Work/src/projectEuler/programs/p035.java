package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import static java.lang.Integer.parseInt;
import static java.util.stream.IntStream.range;

public final class p035 extends EulerSolution {
	private static final int LIMIT = Library.pow(10, 6);
	private final boolean[] isPrime = Library.listPrimality(LIMIT - 1);

	public static void main(String[] args) {
		System.out.println(new p035().run());
	}

	@NotNull String run() {
		int count = (int) range(0, isPrime.length).filter(this::isCircularPrime).count();
		return Integer.toString(count);
	}

	private boolean isCircularPrime(int n) {
		String s = Integer.toString(n);
		return range(0, s.length()).allMatch(i -> isPrime[parseInt(s.substring(i) + s.substring(0, i))]);
	}
}