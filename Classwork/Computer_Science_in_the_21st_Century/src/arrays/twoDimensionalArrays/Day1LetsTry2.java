package arrays.twoDimensionalArrays;

import static java.lang.System.out;

class Day1LetsTry2 {
	public static void main(String[] args) {
		int[][] table = new int[4][5];
		int max = 0;
		int sum = 0;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 5; col++) {
				table[row][col] = (int) (Math.random() * 50) + 1;
				sum += table[row][col];
				max = Integer.max(max, table[row][col]);
				out.print(table[row][col] + "\t");
			}
			out.println();
		}
		out.println(sum + "\n" + max);
	}
}
