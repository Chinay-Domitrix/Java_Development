package programs;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public final class p178 extends EulerSolution {
	private static final int LIMIT = 40;

	public static void main(String[] args) {
		System.out.println(new p178().run());
	}

	String run() {
		// Dynamic programming: ways[digits][head][low][high] is the number of pandigital
		// step numbers such that each number is 'digits' long, starts with the digit 'head'
		// (can be 0), and the union of all the digits equals the interval [low, high]
		BigInteger[][][][] ways = new BigInteger[LIMIT + 1][10][10][10];
		BigInteger sum = ZERO;
		for (int digits = 0; digits < ways.length; digits++)
			for (int head = 0; head < 10; head++)
				for (int low = 0; low < 10; low++)
					for (int high = 0; high < 10; high++) {
						BigInteger val;
						if (digits <= 1) val = (low == head && head == high) ? ONE : ZERO;
						else {
							val = ZERO;
							BigInteger[][][] prevWays = ways[digits - 1];
							if (head - 1 >= low) {
								val = val.add(prevWays[head - 1][low][high]);
								if (head == high) val = val.add(prevWays[head - 1][low][high - 1]);
							}
							if (head + 1 <= high) {
								val = val.add(prevWays[head + 1][low][high]);
								if (head == low) val = val.add(prevWays[head + 1][low + 1][high]);
							}
						}
						ways[digits][head][low][high] = val;
						if (head > 0 && low == 0 && high == 9) sum = sum.add(val);
					}
		return sum.toString();
	}
}