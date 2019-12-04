package twoDimensionalArrays;

import static java.lang.System.out;

class Day1LetsTry1 {
	public static void main(String[] args) {
		int[][] table = new int[4][5];
		int counter = 1;
		out.println(table.length + "\n" + table[3].length + "\n");
		for (int row = 0; row < table.length; row++) {
			for (int col = 0; col < table[row].length; col++) {
				table[row][col] = counter;
				counter++;
				out.print(table[row][col] + "\t");
			}
			out.println();
		}
	}
}
