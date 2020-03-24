package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.stream.IntStream;

import static java.lang.Math.max;
import static java.math.BigInteger.valueOf;

public final class p056 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p056().run());
	}

	private static int digitSum(@NotNull BigInteger n) {
		int sum;
		String s = n.toString();
		sum = IntStream.range(0, s.length()).map(i -> s.charAt(i) - '0').sum();
		return sum;
	}

	@NotNull String run() {
		int max = 0;
		for (int a = 1; a < 100; a++)
			for (int b = 1; b < 100; b++) {
				BigInteger pow = valueOf(a).pow(b);
				max = max(digitSum(pow), max);
			}
		return Integer.toString(max);
	}
}