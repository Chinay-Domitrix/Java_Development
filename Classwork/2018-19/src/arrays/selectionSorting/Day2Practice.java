package arrays.selectionSorting;

import java.util.Scanner;

import static java.lang.System.out;

class Day2Practice {

	private static void charSort(final Scanner a, final int b, final char[] c, final String d) {
		for (int i = 0; i < b; i++) {
			print("Please enter a value: ");
			c[i] = a.nextLine().charAt(0);
		}
		char f;
		int e;
		if (d.equalsIgnoreCase("ascending")) {
			for (int i = 0; i < c.length - 1; i++) {
				e = i;
				for (int j = i + 1; j < c.length; j++)
					if (c[j] < c[e])
						e = j;
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
					if (c[j] > c[e])
						e = j;
				}
				f = c[i];
				c[i] = c[e];
				c[e] = f;
			}
		}
		for (final char value : c) {
			print(value + " ");
		}
	}

	private static void intSort(final Scanner a, final int b, final int[] c, final String d) {
		for (int i = 0; i < b; i++) {
			print("Please enter a value: ");
			c[i] = a.nextInt();
		}
		int f;
		int e;
		if (d.equalsIgnoreCase("acending")) {
			for (int i = 0; i < c.length - 1; i++) {
				e = i;
				for (int j = i + 1; j < c.length; j++)
					if (c[j] < c[e])
						e = j;
				f = c[i];
				c[i] = c[e];
				c[e] = f;
			}
		} else if (d.equalsIgnoreCase("Decending")) {
			for (int i = c.length - 1; i >= 0; i--) {
				e = i;
				//noinspection OverflowingLoopIndex
				for (int j = c.length - 1; j < i + 1; j--)
					if (c[j] > c[e])
						e = j;
				f = c[i];
				c[i] = c[e];
				c[e] = f;
			}
		}
		for (final int value : c) {
			print(value + " ");
		}
	}

	public static void main(final String[] args) {
		final Scanner in = new Scanner(System.in);
		int bounds;
		int[] arrI;
		char[] arrC;
		String order, datatype;
		print("What datatype do you want to sort, int or char? ");
		datatype = in.nextLine();
		print("How many values are you going to input? ");
		bounds = in.nextInt();
		in.nextLine();
		print("Will the order be acending or decending? ");
		order = in.nextLine();
		if (datatype.equalsIgnoreCase("int")) {
			arrI = new int[bounds];
			intSort(in, bounds, arrI, order);
		} else if (datatype.equalsIgnoreCase("char")) {
			arrC = new char[bounds];
			charSort(in, bounds, arrC, order);
		}
	}

	private static void print(final Object a) {
		out.print(a);
	}

}