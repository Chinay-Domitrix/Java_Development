import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;
import static java.lang.Thread.sleep;

class Calculator {
	private static void addition(final double num1, final double num2) {
		final double add = num1 + num2;
		out.printf("The answer is: %s%n", add);
	}

	private static void division(final double num1, final double num2) {
		final double division = num1 / num2;
		out.printf("The answer is: %s%n", division);
	}

	public static void main(final String[] args) throws InterruptedException {
		int counter = 1;
		try (Scanner calc = new Scanner(in)) {
			out.println("Welcome to Calculator!");
			out.println("Please enter your " + counter + "st number");
			final double one = calc.nextDouble();
			counter++;
			out.println("Please enter your " + counter + "nd number");
			final double two = calc.nextDouble();
			calc.nextLine();
			out.println("What is your operation? (Please type A for Addition, S for Subtraction, M for Multiplication and D for Division)");
			final String operation = calc.nextLine();
			switch (operation) {
				case "A":
					sleep(1050);
					addition(one, two);
					break;
				case "S":
					sleep(1050);
					subtraction(one, two);
					break;
				case "M":
					sleep(1050);
					multiplication(one, two);
					break;
				case "D":
					sleep(1050);
					division(one, two);
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + operation);
			}
		}
	}

	private static void multiplication(final double num1, final double num2) {
		final double multi = num1 * num2;
		out.println("The answer is: " + multi);
	}

	private static void subtraction(final double num1, final double num2) {
		final double subtract = num1 - num2;
		out.println("The answer is: " + subtract);
	}
}
