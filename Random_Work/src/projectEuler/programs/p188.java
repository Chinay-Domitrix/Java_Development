package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import static projectEuler.programs.Library.*;

public final class p188 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p188().run());
	}

	private static int tetrationMod(int x, int y, int m) {
		// Fact: If x and m are coprime, then x^y mod m = x^(y mod totient(m)) mod m
		return (y == 1) ? (x % m) : powMod(x, tetrationMod(x, y - 1, totient(m)), m);
	}

	@NotNull String run() {
		return Integer.toString(tetrationMod(1777, 1855, pow(10, 8)));
	}
}