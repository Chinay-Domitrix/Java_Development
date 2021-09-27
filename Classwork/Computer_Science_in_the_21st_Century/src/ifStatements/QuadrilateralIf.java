package ifStatements;

import java.util.Scanner;

import static java.lang.System.out;

class QuadrilateralIf {
	public static void main(String[] args) {
		var in = new Scanner(System.in);
		var a = "Please enter your four angles. Go from top left to top right to bottom right to bottom left: ";
		out.print(a);
		hmmm(in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble());
		in.close();
	}

	private static void hmmm(double a, double b, double c, double d) {
		String e;
		if (((a + b + c + d) == 360) && (a == 90) && (b == 90) && (c == 90) && (d == 90)) {
			e = "You have either a rectangle or a square.";
			out.println(e);
		} else if ((a < 180) && (b < 180) && (c < 180) && (d < 180) && ((a + b + c + d) == 360) && (a == c) && (b == d)
				&& (a != b) && (a != d) && (c != b) && (c != d)) {
			e = "You have a parallelogram.";
			out.println(e);
		} else if ((a < 180) && (b < 180) && (c < 180) && (d < 180) && ((a + b + c + d) == 360)) {
			e = "You have a quadrilateral with no special qualities.";
			out.println(e);
		} else
			out.println("Error.");
	}
}
