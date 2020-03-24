package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static projectEuler.programs.Library.isPrime;
import static projectEuler.programs.Library.powMod;

public final class p132 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p132().run());
	}

	@Contract(pure = true)
	private static int repunitMod(int m) {
		return (powMod(10, 1000000000, m * 9) - 1) / 9;
	}

	@NotNull String run() {
		int sum = 0;
		int count = 0;
		for (int i = 2; count < 40; i++)
			if (isPrime(i) && repunitMod(i) == 0) {
				sum += i;
				count++;
			}
		return Integer.toString(sum);
	}
}