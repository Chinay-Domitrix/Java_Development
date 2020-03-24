package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

import static java.math.BigInteger.*;

public final class p065 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p065().run());
	}

	@Contract(pure = true)
	private static int continuedFractionTerm(int i) {
		if (i == 0) return 2;
		else if (i % 3 == 2) return i / 3 * 2 + 2;
		else return 1;
	}

	@NotNull String run() {
		BigInteger n = ONE;
		BigInteger d = ZERO;
		for (int i = 99; i >= 0; i--) {
			BigInteger temp = valueOf(continuedFractionTerm(i)).multiply(n).add(d);
			d = n;
			n = temp;
		}
		int sum = 0;
		while (!n.equals(ZERO)) {
			BigInteger[] divrem = n.divideAndRemainder(TEN);
			sum += divrem[1].intValue();
			n = divrem[0];
		}
		return Integer.toString(sum);
	}
}