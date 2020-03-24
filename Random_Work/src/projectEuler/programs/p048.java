package projectEuler.programs;

import java.math.BigInteger;

import static java.math.BigInteger.*;

public final class p048 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p048().run());
	}

	String run() {
		BigInteger modulus = TEN.pow(10);
		BigInteger sum = ZERO;
		for (int i = 1; i <= 1000; i++) sum = sum.add(valueOf(i).modPow(valueOf(i), modulus));
		return sum.mod(modulus).toString();
	}
}