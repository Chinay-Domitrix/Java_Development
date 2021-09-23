package Classwork.Computer_Science_in_the_21st_Century.src.arrays.selectionSorting;

import java.util.Scanner;

import static java.lang.System.out;

class Day2Practice {
	private static void charSort(final Scanner a, final int b, final char[] c, final String d) {
		for (int i = 0; i < b; i++) {
			out.print("Please enter a value: ");
			c[i] = a.nextLine().charAt(0);
		}
		char f;
		int e;
		if (d.equalsIgnoreCase("ascending")) {
			for (int i = 0; i < c.length - 1; i++) {
				e = i;
				for (int j = i + 1; j < c.length; j++) if (c[j] < c[e]) e = j;
				f = c[i];
				c[i] = c[e];
				c[e] = f;
			}
		} else if (d.equalsIgnoreCase("descending")) {
			for (int i = c.length - 1; i >= 0; i--) {
				e = i;
				// noinspection OverflowingLoopIndex
				for (int j = c.length; j < i + 1; j--) {
					//noinspection ConstantConditions
					if (c[j] > c[e]) e = j;
				}
				f = c[i];
				c[i] = c[e];
				c[e] = f;
			}
		}
		for (final char value : c) out.print(value + " ");
	}

	private static void intSort(final Scanner a, final int b, final int[] c, final String d) {
		for (int i = 0; i < b; i++) {
			out.print("Please enter a value: ");
			c[i] = a.nextInt();
		}
		int f;
		int e;
		if (d.equalsIgnoreCase("ascending")) for (int i = 0; i < c.length - 1; i++) {
			e = i;
			for (int j = i + 1; j < c.length; j++) if (c[j] < c[e]) e = j;
			f = c[i];
			c[i] = c[e];
			c[e] = f;
		}
		else if (d.equalsIgnoreCase("descending")) for (var i = c.length - 1; i >= 0; i--) {
			e = i;
			//noinspection OverflowingLoopIndex
			for (var j = c.length - 1; j < i + 1; j--) if (c[j] > c[e]) e = j;
			f = c[i];
			c[i] = c[e];
			c[e] = f;
		}
		for (final var value : c) out.print(value + " ");
	}

	public static void main(final String[] args) {
		final var input = new Scanner(System.in);
		out.print("What datatype do you want to sort, int or char? ");
		var datatype = input.nextLine();
		out.print("How many values are you going to input? ");
		int bounds = input.nextInt();
		input.nextLine();
		out.print("Will the order be ascending or descending? ");
		var order = input.nextLine();
		if (datatype.equalsIgnoreCase("int")) intSort(input, bounds, new int[bounds], order);
		else if (datatype.equalsIgnoreCase("char")) charSort(input, bounds, new char[bounds], order);
	}
}
