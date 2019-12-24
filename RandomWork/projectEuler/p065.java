import java.math.BigInteger;

65

public final class p065 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p065().run());
	}

	private static int continuedFractionTerm(int i) {
		if (i == 0)
			return 2;
		else if (i % 3 == 2)
			return i / 3 * 2 + 2;
		else
			return 1;
	}

	String run() {
		BigInteger n = BigInteger.ONE;
		BigInteger d = BigInteger.ZERO;
		for (int i = 99; i >= 0; i--) {
			BigInteger temp = BigInteger.valueOf(continuedFractionTerm(i)).multiply(n).add(d);
			d = n;
			n = temp;
		}

		int sum = 0;
		while (!n.equals(BigInteger.ZERO)) {
			BigInteger[] divrem = n.divideAndRemainder(BigInteger.TEN);
			sum += divrem[1].intValue();
			n = divrem[0];
		}
		return Integer.toString(sum);
	}
}