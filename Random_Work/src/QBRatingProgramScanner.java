import java.util.Scanner;

import static java.lang.System.out;

class QBRatingProgramScanner {
	public static void main(final String[] args) {
		final Scanner rater = new Scanner(System.in);
		out.println("What is your QB's name?");
		final String name = rater.nextLine();
		out.println("How many yards does your QB have?");
		final int yds = rater.nextInt();
		out.println("How many completions does your QB have?");
		final int comp = rater.nextInt();
		out.println("How many TDs does your QB have?");
		final int td = rater.nextInt();
		out.println("How many interceptions does your QB have?");
		final int inter = rater.nextInt();
		out.println("How many attempts does your QB have?");
		final int att = rater.nextInt();
		final double a = (((comp / att) * 100) - 30) / 20, b = ((td / att) * 100) / 5, c = (9.5 - ((inter / att) * 100)) / 4, d = (yds / att - 3) / 4, rating = (a + b + c + d) / 0.06;
		out.println(name + "'s Rating: " + rating);
		rater.close();
	}
}