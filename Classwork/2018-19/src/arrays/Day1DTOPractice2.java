package arrays;

import java.util.Scanner;

class Day1DTOPractice2 {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("Please input 6 names. ");
			String[] array = new String[6];
			for (int i = 0; i < array.length; i++) {
				array[i] = in.nextLine();
			}
			System.out.println("The people are " + array[0] + ", " + array[1] + ", " + array[2] + ", " + array[3] + ", "
					+ array[4] + ", and " + array[5]);
		}
	}
}
