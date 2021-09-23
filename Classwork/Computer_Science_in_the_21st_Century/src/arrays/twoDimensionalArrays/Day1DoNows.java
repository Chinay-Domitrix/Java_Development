package Classwork.Computer_Science_in_the_21st_Century.src.arrays.twoDimensionalArrays;

import static java.lang.System.out;

class Day1DoNows {
	public static void main(String[] args) {
		int[] factors = new int[100];
		int maxFact = 0, max = 0;
		for (int i = 1; i <= 100; i++) {
			for (int j = 1; j <= i; j++) if (i % j == 0) factors[i - 1]++;
			if (maxFact < factors[i - 1]) {
				maxFact = factors[i - 1];
				max = i;
			}
		}
		out.println(max);
	}
}
