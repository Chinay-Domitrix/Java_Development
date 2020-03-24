package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import static projectEuler.programs.Library.powMod;

public final class p250 extends EulerSolution {
	private static final long MODULUS = 10000000000000000L;

	public static void main(String[] args) {
		System.out.println(new p250().run());
	}

	@NotNull String run() {
		// Use dynamic programming
		long[] numSubsets = new long[250]; // numSubsets[i] is {the number of subsets with sum equal to i mod 250} mod 10^16
		numSubsets[0] = 1;
		for (int i = 1; i <= 250250; i++) {
			int temp = powMod(i, i, 250);
			long[] newArray = new long[numSubsets.length];
			for (int j = 0; j < 250; j++)
				newArray[(j + temp) % 250] = (numSubsets[j] + numSubsets[(j + temp) % 250]) % MODULUS;
			numSubsets = newArray;
		}
		return Long.toString(((numSubsets[0] - 1) + MODULUS) % MODULUS);
	}
}