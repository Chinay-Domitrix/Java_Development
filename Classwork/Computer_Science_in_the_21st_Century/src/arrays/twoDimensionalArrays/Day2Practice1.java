package arrays.twoDimensionalArrays;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.System.out;

class Day2Practice1 {
	public static void main(String[] args) {
		int[][] a = new int[4][6];
		int sum = 0;
		int[] range = {MAX_VALUE, MIN_VALUE};
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 5; col++) {
				a[row][col] = (int) (Math.random() * 71) + 7;
				sum += a[row][col];
				if (a[row][col] < range[0])
					range[0] = a[row][col];
				if (a[row][col] > range[1])
					range[1] = a[row][col];
			}
			out.println();
		}
		out.println(sum + "\n" + range[1] + "\n" + range[0]);
	}
}
