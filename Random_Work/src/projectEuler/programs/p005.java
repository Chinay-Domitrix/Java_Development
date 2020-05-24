package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.valueOf;

public final class p005 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p005().run());
	}

	private static BigInteger lcm(@NotNull BigInteger x, BigInteger y) {
		return x.divide(x.gcd(y)).multiply(y);
	}

	/*
	 * The smallest number n that is evenly divisible by every number in a set {k1, k2, ..., k_m}
	 * is also known as the lowest common multiple (LCM) of the set of numbers.
	 * The LCM of two natural numbers x and y is given by LCM(x, y) = x * y / GCD(x, y).
	 * When LCM is applied to a collection of numbers, it is commutative, associative, and idempotent.
	 * Hence LCM(k1, k2, ..., k_m) = LCM(...(LCM(LCM(k1, k2), k3)...), k_m).
	 */
	String run() {
		BigInteger allLcm = ONE;
		for (int i = 1; i <= 20; i++) allLcm = lcm(valueOf(i), allLcm);
		return allLcm.toString();
	}
}