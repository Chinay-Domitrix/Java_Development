package forLoops;

import java.util.Scanner;

class Practice1 {
	public static void main(final String[] args) {
		final Scanner in = new Scanner(System.in);
		int o = 0;
		int s = 0;
		int u = 0;
		System.out.print("How many scores would you like to input? ");
		final int sA = in.nextInt();
		int counter = 0;
		for (int i = 0; i < sA; i++) {
			counter++;
			System.out.print("Please enter score #" + counter + ": ");
			final double score = in.nextDouble();
			System.out.println();
			if (score >= 90 && score <= 100) {
				o++;
			} else if (score >= 70 && score <= 89) {
				s++;
			} else if (score >= 0 && score <= 69) {
				u++;
			} else {
				System.out.println("Error.");
				break;
			}
		}
		System.out.println("Number of outstanding scores: " + o);
		System.out.println("Number of satisfactory scores: " + s);
		System.out.println("Number of unsatisfactory scores: " + u);
		in.close();
	}
}
