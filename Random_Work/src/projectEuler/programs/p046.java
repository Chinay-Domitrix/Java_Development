package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

import static projectEuler.programs.Library.isPrime;

public final class p046 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p046().run());
	}

	private static boolean satisfiesConjecture(int n) {
		if (n % 2 == 0 || isPrime(n)) return true;
		// Now n is an odd composite number
		return IntStream.iterate(1, i -> (i * i * 2) <= n, i -> i + 1).anyMatch(i -> isPrime(n - (i * i * 2)));
	}

	@NotNull String run() {
		for (int i = 9; ; i += 2) if (!satisfiesConjecture(i)) return Integer.toString(i);
	}
}