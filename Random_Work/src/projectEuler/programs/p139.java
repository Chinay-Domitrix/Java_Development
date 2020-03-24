package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import static projectEuler.programs.Library.gcd;

public final class p139 extends EulerSolution {
	private static final int LIMIT = 100000000;

	public static void main(String[] args) {
		System.out.println(new p139().run());
	}

	@NotNull String run() {
		/*
		 * Pythagorean triples theorem:
		 * Every primitive Pythagorean triple with a odd and b even can be expressed as
		 * a = st, b = (s^2-t^2)/2, c = (s^2+t^2)/2, where s > t > 0 are coprime odd integers.
		 */
		int count = 0;
		for (int s = 3; s * s / 2 < LIMIT; s += 2)
			for (int t = 1; t < s; t += 2) {
				int a = s * t;
				int b = ((s * s) - (t * t)) >> 1;
				int c = ((s * s) + (t * t)) >> 1;
				int p = a + b + c;
				if (p >= LIMIT) break;
				if (((c % (a - b)) == 0) && (gcd(s, t) == 1)) count += (LIMIT - 1) / p;
			}
		return Integer.toString(count);
	}
}