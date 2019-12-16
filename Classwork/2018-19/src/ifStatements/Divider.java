package ifStatements;

import java.util.Scanner;

import static java.lang.System.out;

class Divider {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			String a = "Please enter an integer greater than -2,147,483,648, but less than 2,147,483,647. This calculator will then tell you which numbers out of 2, 3, 5, 7, and 11 your integer is evenly divisible by.";
			out.println(a);
			compute(in.nextInt());
		}
	}

	private static void compute(int a) {
		all(a);
		except(a);
	}

	private static void all(int a) {
		if ((a % 2 == 0) && (a % 3 == 0) && (a % 5 == 0) && (a % 7 == 0) && (a % 11 == 0)) {
			out.println("Your number is divisible evenly by all those numbers.");
		}
	}

	private static void except(int a) {
		if ((a % 2 == 0) && (a % 3 == 0) && (a % 5 == 0) && (a % 7 == 0) && (a % 11 != 0))
			out.println("Your number is divisible evenly by all those numbers except 11.");
		else if ((a % 2 != 0) && (a % 3 == 0) && (a % 5 == 0) && (a % 7 == 0) && (a % 11 == 0))
			out.println("Your number is divisible evenly by all those numbers except 2.");
		else if ((a % 2 == 0) && (a % 3 != 0) && (a % 5 == 0) && (a % 7 == 0) && (a % 11 == 0))
			out.println("Your number is divisible evenly by all those numbers except 3.");
		else if ((a % 2 == 0) && (a % 3 == 0) && (a % 5 != 0) && (a % 7 == 0) && (a % 11 == 0))
			out.println("Your number is divisible evenly by all those numbers except 5.");
		else if ((a % 2 == 0) && (a % 3 == 0) && (a % 5 == 0) && (a % 7 != 0) && (a % 11 == 0))
			out.println("Your number is divisible evenly by all those numbers except 7.");
		else if ((a % 2 != 0) && (a % 3 != 0) && (a % 5 == 0) && (a % 7 == 0) && (a % 11 == 0))
			out.println("Your number is divisible evenly by all those numbers except 2 and 3.");
		else if ((a % 2 != 0) && (a % 3 == 0) && (a % 5 != 0) && (a % 7 == 0) && (a % 11 == 0))
			out.println("Your number is divisible evenly by all those numbers except 2 and 5.");
		else if ((a % 2 != 0) && (a % 3 == 0) && (a % 5 == 0) && (a % 7 != 0) && (a % 11 == 0))
			out.println("Your number is divisible evenly by all those numbers except 2 and 7.");
		else if ((a % 2 != 0) && (a % 3 == 0) && (a % 5 == 0) && (a % 7 == 0) && (a % 11 != 0))
			out.println("Your number is divisible evenly by all those numbers except 2 and 11.");
		else if ((a % 2 == 0) && (a % 3 != 0) && (a % 5 != 0) && (a % 7 == 0) && (a % 11 == 0))
			out.println("Your number is divisible evenly by all those numbers except 3 and 5.");
		else if ((a % 2 == 0) && (a % 3 != 0) && (a % 5 == 0) && (a % 7 != 0) && (a % 11 == 0))
			out.println("Your number is divisible evenly by all those numbers except 3 and 7.");
		else if ((a % 2 == 0) && (a % 3 != 0) && (a % 5 == 0) && (a % 7 == 0) && (a % 11 != 0))
			out.println("Your number is divisible evenly by all those numbers except 3 and 11.");
		else if ((a % 2 == 0) && (a % 3 == 0) && (a % 5 != 0) && (a % 7 != 0) && (a % 11 == 0))
			out.println("Your number is divisible evenly by all those numbers except 5 and 7.");
		else if ((a % 2 == 0) && (a % 3 == 0) && (a % 5 != 0) && (a % 7 == 0) && (a % 11 != 0))
			out.println("Your number is divisible evenly by all those numbers except 5 and 11.");
		else if ((a % 2 == 0) && (a % 3 == 0) && (a % 5 == 0) && (a % 7 != 0) && (a % 11 != 0))
			out.println("Your number is divisible evenly by all those numbers except 7 and 11.");
		else if ((a % 2 != 0) && (a % 3 != 0) && (a % 5 != 0) && (a % 7 == 0) && (a % 11 == 0))
			out.println("Your number is divisible evenly by all those numbers except 2, 3, and 5.");
		else if ((a % 2 != 0) && (a % 3 != 0) && (a % 5 == 0) && (a % 7 != 0) && (a % 11 == 0))
			out.println("Your number is divisible evenly by all those numbers except 2, 3, and 7.");
		else if ((a % 2 != 0) && (a % 3 != 0) && (a % 5 == 0) && (a % 7 == 0) && (a % 11 != 0))
			out.println("Your number is divisible evenly by all those numbers except 2, 3, and 11.");
		else {
			boolean b = (a % 2 != 0) && (a % 3 == 0) && (a % 5 != 0) && (a % 7 != 0) && (a % 11 == 0);
			if (b)
				out.println("Your number is divisible evenly by all those numbers except 2, 5, and 7.");
			else {
				boolean b1 = (a % 2 != 0) && (a % 3 == 0) && (a % 5 != 0) && (a % 7 == 0) && (a % 11 != 0);
				//noinspection DuplicateCondition
				if (b1)
					out.println("Your number is divisible evenly by all those numbers except 2, 5, and 11.");
				else if ((a % 2 != 0) && (a % 3 == 0) && (a % 5 == 0) && (a % 7 != 0) && (a % 11 != 0))
					out.println("Your number is divisible evenly by all those numbers except 2, 7, and 11.");
				else if ((a % 2 == 0) && (a % 3 != 0) && (a % 5 != 0) && (a % 7 != 0) && (a % 11 == 0))
					out.println("Your number is divisible evenly by all those numbers except 3, 5, and 7.");
				else if ((a % 2 == 0) && (a % 3 != 0) && (a % 5 != 0) && (a % 7 == 0) && (a % 11 != 0))
					out.println("Your number is divisible evenly by all those numbers except 3, 5, and 11.");
				else if ((a % 2 == 0) && (a % 3 != 0) && (a % 5 == 0) && (a % 7 != 0) && (a % 11 != 0))
					out.println("Your number is divisible evenly by all those numbers except 3, 7, and 11.");
				else if ((a % 2 == 0) && (a % 3 == 0) && (a % 5 != 0) && (a % 7 != 0) && (a % 11 != 0))
					out.println("Your number is divisible evenly by all those numbers except 5, 7, and 11.");
				else if ((a % 2 != 0) && (a % 3 != 0) && (a % 5 != 0) && (a % 7 != 0) && (a % 11 == 0))
					out.println("Your number is only divisible evenly by 11.");
				else if ((a % 2 != 0) && (a % 3 != 0) && (a % 5 != 0) && (a % 7 == 0) && (a % 11 != 0))
					out.println("Your number is only divisible evenly by 7.");
				else if ((a % 2 != 0) && (a % 3 != 0) && (a % 5 == 0) && (a % 7 != 0) && (a % 11 != 0))
					out.println("Your number is only divisible evenly by 5.");
				else if ((a % 2 != 0) && (a % 3 == 0) && (a % 5 != 0) && (a % 7 != 0) && (a % 11 != 0))
					out.println("Your number is only divisible evenly by 3.");
				else if ((a % 2 != 0) && (a % 3 != 0) && (a % 5 != 0) && (a % 7 != 0) && (a % 11 != 0))
					out.println("Your number is not divisible evenly by of those numbers.");
				else out.println("Syntax or Computational Error.");
			}
		}
	}
}