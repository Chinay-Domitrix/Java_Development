package twoDimensionalArrays;

import java.util.Scanner;

import static java.lang.System.*;

class Day1YouTry {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int[][] a = new int[10][10];
			out.print("Which program do you want to run? Original or Modified (O or M)? ");
			String program = in.nextLine();
			int counter;
			switch (program) {
				default:
					err.println("Error");
					exit(0);
					break;
				case "o":
				case "O":
					counter = 0;
					for (int row = 0; row < a.length; row++) {
						for (int col = 0; col < a[row].length; col++) {
							a[row][col] = counter;
							counter++;
							out.print(a[row][col] + "\t");
						}
						counter = 0;
						out.println();
					}
					break;
				case "m":
				case "M":
					counter = 1;
					for (int row = 0; row < a.length; row++) {
						for (int col = 0; col < a[row].length; col++) {
							a[row][col] = counter;
							counter++;
							out.print(a[row][col] + "\t");
						}
						counter = 1;
						out.println();
					}
			}
		}
	}
}