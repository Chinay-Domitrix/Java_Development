package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import static java.util.stream.IntStream.range;
import static projectEuler.programs.Library.*;

public final class p381 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p381().run());
	}

	/*
	 * Note about the mathematical simplification:
	 * (p-5)! + (p-4)! + (p-3)! + (p-2)! + (p-1)!
	 * = (p-5)! * (1 + (p-4) + (p-4)(p-3) + (p-4)(p-3)(p-2) + (p-4)(p-3)(p-2)(p-1))
	 * = (p-5)! * (1 + (-4) + (-4)(-3) + (-4)(-3)(-2) + (-4)(-3)(-2)(-1))
	 * = (p-5)! * (1 + -4 + 12 + -24 + 24)
	 * = (p-5)! * 9
	 * = (p-1)! / ((p-1)(p-2)(p-3)(p-4)) * 9
	 * = (p-1)! / ((-1)(-2)(-3)(-4)) * 9
	 * = (p-1)! / 24 * 9
	 * = (p-1)! * (3 * 3) / (3 * 8)
	 * = (p-1)! * 3 / 8
	 * = -1 * 3 / 8 (by Wilson's theorem)
	 * = -3/8 mod p.
	 * Every part of the equation is modulo a prime p > 4.
	 */
	private static int s(int p) {
		return (int) (((long) (p - 3) * reciprocalMod(8 % p, p)) % p);
	}

	@NotNull String run() {
		boolean[] isPrime = listPrimality(pow(10, 8));
		long sum = range(5, isPrime.length).filter(i -> isPrime[i]).mapToLong(p381::s).sum();
		return Long.toString(sum);
	}
}