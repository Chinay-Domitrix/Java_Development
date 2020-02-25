package twoDimensionalArrays;

import static java.lang.Math.pow;
import static java.lang.System.out;

class Day2Practice2 {
	public static void main(String[] args) {
		int[][] a = new int[5][4];
		for (int row = 0; row < a.length; row++) {
			for (int col = 0; col < a[0].length; col++) {
				int x = col + 2;
				if (row == 0) {
					a[row][col] = (int) pow(x, 2);
					out.print(a[row][col] + "\t");
				} else {
					a[row][col] = a[row - 1][col] * x;
					out.print(a[row][col] + "\t");
				}
			}
			out.println();
		}
	}
}
