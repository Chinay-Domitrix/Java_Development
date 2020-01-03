package programs;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

import static java.lang.Math.max;
import static java.math.BigInteger.valueOf;

public final class p056 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p056().run());
	}

	private static int digitSum(@NotNull BigInteger n) {
		int sum = 0;
		String s = n.toString();
		for (int i = 0; i < s.length(); i++) sum += s.charAt(i) - '0';
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