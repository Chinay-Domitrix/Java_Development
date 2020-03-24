package projectEuler.programs;

import java.util.stream.IntStream;

public final class p021 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p021().run());
	}

	/*
	 * We find the sum of proper divisors of a number by brute force,
	 * and apply the definition of an amicable number straightforwardly.
	 */

	private static boolean isAmicable(int n) {
		int m = divisorSum(n);
		return (m != n) && (divisorSum(m) == n);
	}

	private static int divisorSum(int n) {
		return IntStream.range(1, n).filter(i -> (n % i) == 0).sum();
	}

	String run() {
		int sum = IntStream.range(1, 10000).filter(p021::isAmicable).sum();
		return Integer.toString(sum);
	}
}