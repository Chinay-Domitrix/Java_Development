package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

import static java.math.BigInteger.*;

public final class p057 extends EulerSolution {
	private static final int LIMIT = 1000;

	public static void main(String[] args) {
		System.out.println(new p057().run());
	}

	@NotNull String run() {
		BigInteger n = ZERO;
		BigInteger d = ONE;
		int count = 0;
		for (int i = 0; i < LIMIT; i++) {
			BigInteger temp = d.multiply(valueOf(2)).add(n);
			n = d;
			d = temp;
			// Now n/d is the i'th (0-based) continued fraction approximation of sqrt(2) - 1
			if (n.add(d).toString().length() > d.toString().length()) count++;
		}
		return Integer.toString(count);
	}
}