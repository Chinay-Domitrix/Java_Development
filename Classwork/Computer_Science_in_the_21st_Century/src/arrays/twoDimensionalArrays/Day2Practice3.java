package Classwork.Computer_Science_in_the_21st_Century.src.arrays.twoDimensionalArrays;

import static java.lang.System.out;

class Day2Practice3 {
	public static void main(String[] args) {
		String[][] a = new String[3][3];
		String[] ast = {"*", "**", "***"};
		for (int row = 0; row < a.length; row++) {
			for (int col = 0; col < a[0].length; col++) {
				a[row][col] = ast[row];
				out.print(a[row][col] + "\t");
			}
			out.println();
		}
	}
}
