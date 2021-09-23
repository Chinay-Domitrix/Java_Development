package Classwork.Computer_Science_in_the_21st_Century.src.arrays.twoDimensionalArrays;

import static java.lang.System.out;

class Day1LetsTry1 {
	public static void main(String[] args) {
		var table = new int[4][5];
		var counter = 1;
		out.println(table.length + "\n" + table[3].length + "\n");
		for (var row = 0; row < table.length; row++) {
			for (var col = 0; col < table[row].length; col++) {
				table[row][col] = counter;
				counter++;
				out.print(table[row][col] + "\t");
			}
			out.println();
		}
	}
}
