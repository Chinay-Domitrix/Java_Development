package Classwork.Computer_Science_in_the_21st_Century.src.parameteredMethods;

import org.jetbrains.annotations.Contract;

import java.util.Scanner;

import static java.lang.System.out;

class AverageFromParameteredScannerMethods {
	public static void main(String[] args) {
		double a;
		double b;
		double c;
		try (Scanner numbers = new Scanner(System.in)) {
			out.print("First number: ");
			a = first(numbers.nextDouble());
			out.print("Second number: ");
			b = second(numbers.nextDouble());
			out.print("Third number: ");
			c = third(numbers.nextDouble());
		}
		double average = ((a + b + c) / 3);
		out.println("The average value is " + average);
	}

	@Contract(value = "_ -> param1", pure = true)
	private static double first(double a) {
		return a;
	}

	@Contract(value = "_ -> param1", pure = true)
	private static double second(double a) {
		return a;
	}

	@Contract(value = "_ -> param1", pure = true)
	private static double third(double a) {
		return a;
	}
}
