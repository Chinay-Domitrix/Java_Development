package programs;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public final class p076 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p076().run());
	}

	private static BigInteger partitions() {
		// Dynamic programming
		BigInteger[][] table = new BigInteger[100 + 1][100 + 1];
		for (int i = 0; i <= 100; i++)
			for (int j = 100; j >= 0; j--) {
				if (j == i) table[i][j] = ONE;
				else if (j > i) table[i][j] = ZERO;
				else if (j == 0) table[i][j] = table[i][j + 1];
				else table[i][j] = table[i][j + 1].add(table[i - j][j]);
			}
		return table[100][1];
	}

	String run() {
		return partitions().subtract(ONE).toString();
	}
}