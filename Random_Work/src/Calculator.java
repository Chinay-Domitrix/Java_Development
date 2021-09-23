package Random_Work.src;

import java.util.Scanner;

import static java.lang.String.valueOf;
import static java.lang.System.in;
import static java.lang.System.out;
import static java.lang.Thread.sleep;

class Calculator {
	private static void addition(final double num1, final double num2) {
		out.printf("The answer is: %s%n", num1 + num2);
	}

	private static void division(final double num1, final double num2) {
		out.printf("The answer is: %s%n", num1 / num2);
	}

	public static void main(final String[] args) throws InterruptedException, IllegalStateException {
		int counter = 1;
		try (var calc = new Scanner(in)) {
			out.println("Welcome to Calculator!");
			out.printf("Please enter your %dst number%n", counter);
			final var one = calc.nextDouble();
			counter++;
			out.printf("Please enter your %dnd number%n", counter);
			final var two = calc.nextDouble();
			calc.nextLine();
			out.print("What is your operation? (Please type 'A' for Addition, 'S' for Subtraction, 'M' for Multiplication and 'D' for Division) ");
			final var operation = calc.nextLine().charAt(0);
			if (valueOf(operation).equalsIgnoreCase("A")) {
				sleep(1050);
				addition(one, two);
			} else if (valueOf(operation).equalsIgnoreCase("S")) {
				sleep(1050);
				subtraction(one, two);
			} else if (valueOf(operation).equalsIgnoreCase("M")) {
				sleep(1050);
				multiplication(one, two);
			} else if (valueOf(operation).equalsIgnoreCase("D")) {
				sleep(1050);
				division(one, two);
			} else throw new IllegalStateException("Unexpected value: " + operation);
		}
	}

	private static void multiplication(final double num1, final double num2) {
		out.printf("The answer is: %s%n", num1 * num2);
	}

	private static void subtraction(final double num1, final double num2) {
		out.printf("The answer is: %s%n", num1 - num2);
	}
}
