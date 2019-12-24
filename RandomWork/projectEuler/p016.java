import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

public final class p016 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p016().run());
	}

	/*
	 * We implement this solution in a straightforward way with help from BigInteger.
	 */
	@NotNull String run() {
		String temp = BigInteger.ONE.shiftLeft(1000).toString();
		int sum = 0;
		for (int i = 0; i < temp.length(); i++) sum += temp.charAt(i) - '0';
		return Integer.toString(sum);
	}
}