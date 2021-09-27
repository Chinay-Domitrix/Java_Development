package ifStatements;

import java.util.Scanner;

class GreaterThanOrLessThan {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String start = "Please type two numbers to compare.";
		System.out.println(start);
		conditional(in.nextDouble(), in.nextDouble());
		in.close();
	}

	private static void conditional(double a, double b) {
		if (a > b) {
			String c = a + " is greater than " + b;
			System.out.println(c);
		} else if (a < b) {
			String d = a + " is less than " + b;
			System.out.println(d);
		}
	}
}
