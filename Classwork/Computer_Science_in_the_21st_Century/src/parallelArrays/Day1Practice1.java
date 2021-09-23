package Classwork.Computer_Science_in_the_21st_Century.src.parallelArrays;

import java.util.Scanner;

import static java.lang.System.out;

class Day1Practice1 {
	private static final int[] no = new int[5];
	private static final String[] zip = new String[5], street = new String[5], apt = new String[5];
	private static final Scanner in = new Scanner(System.in);

	public static void main(final String[] args) {
		for (int i = 0; i < 5; i++) {
			print("Please enter the building number. ");
			no[i] = in.nextInt();
			in.nextLine();
			print("Please enter the street name along with the suffix. ");
			street[i] = in.nextLine();
			print("Please enter the apartment, if valid. Otherwise, enter \"-\". ");
			apt[i] = in.nextLine();
			if (apt[i].equals("-"))
				apt[i] = "";
			print("Please enter the zip code. ");
			zip[i] = in.nextLine();
			print("\n");
		}
		for (int i = 0; i < 5; i++)
			out.println(no[i] + " " + street[i] + " " + apt[i] + " " + zip[i]);
		in.close();
	}

	private static void print(final Object a) {
		out.print(a);
	}
}
