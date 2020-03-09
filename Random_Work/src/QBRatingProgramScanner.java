import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

class QBRatingProgramScanner {
	public static void main(final String[] args) {
		try (var rater = new Scanner(in)) {
			out.println("What is your QB's name?");
			var name = rater.nextLine();
			out.println("How many yards does your QB have?");
			var yds = rater.nextInt();
			out.println("How many completions does your QB have?");
			var comp = rater.nextInt();
			out.println("How many TDs does your QB have?");
			var td = rater.nextInt();
			out.println("How many interceptions does your QB have?");
			var inter = rater.nextInt();
			out.println("How many attempts does your QB have?");
			var att = rater.nextInt();
			var a = (((comp / (double) att) * 100) - 30) / 20;
			var b = ((td / (double) att) * 100) / 5;
			var c = (9.5 - ((inter / (double) att) * 100)) / 4;
			var d = (yds / (double) att - 3) / 4;
			var rating = (a + b + c + d) / 0.06;
			out.printf("%s's Rating: %s%n", name, rating);
		}
	}
}